package cn.micromoving.bcp.modules.hr.service;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.common.utils.Reflections;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.act.service.ActTaskService;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.dao.AuditRecordsDao;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.dao.SalaryInstanceDao;
import cn.micromoving.bcp.modules.hr.entity.AuditRecords;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.entity.SalView;
import cn.micromoving.bcp.modules.hr.entity.SalaryDetails;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstance;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstanceTask;
import cn.micromoving.bcp.modules.hr.entity.SalaryItems;
import cn.micromoving.bcp.modules.hr.utils.HrUtils;
import cn.micromoving.bcp.modules.sys.dao.UserDao;
import cn.micromoving.bcp.modules.sys.entity.Role;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
@Transactional(readOnly = true)
public class SalaryInstanceService extends
		CrudService<SalaryInstanceDao, SalaryInstance> {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	protected TaskService taskService;

	@Autowired
	protected HistoryService historyService;

	@Autowired
	protected RepositoryService repositoryService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private ActTaskService actTaskService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SalaryInstanceDao salaryInstanceDao;

	@Autowired
	private AuditRecordsDao auditRecordsDao;

	@Autowired
	private SalaryInstanceTaskService salaryInstanceTaskService;

	@Autowired
	private SalaryDetailsService salaryDetailsService;

	@Autowired
	private SalEmpViewDao salEmpViewDao;

	@Autowired
	private SalaryItemsService salaryItemsService;

	/**
	 * 启动流程
	 * 
	 * @param procDefKey
	 *            流程定义KEY
	 * @param businessTable
	 *            业务表表名
	 * @param businessId
	 *            业务表编号
	 * @param title
	 *            流程标题，显示在待办任务标题
	 * @return 流程实例ID
	 */
	@Transactional(readOnly = false)
	public String startProcess(String procDefKey, String businessTable,
			String businessId) {
		Map<String, Object> vars = Maps.newHashMap();
		/**
		 * 根据角色ID取到对应的用户信息
		 */
		User user = new User();
		Role role = new Role();
		String[] str = { "salary_submitter", "section_chief",
				"deputy_director", "division_head", "header_school_leadership" };
		for (int i = 0; i < str.length; i++) {
			StringBuilder sb = new StringBuilder();
			role = systemService.getRoleByEnname(str[i]);
			user.setRole(role);
			for (User temp : userDao.findList(user)) {
				sb.append(temp.getLoginName() + ",");
			}
			vars.put(str[i], sb.toString());
		}
		/**
		 * 启动流程
		 */
		String procInsId = actTaskService.startProcess(procDefKey,
				businessTable, businessId, vars);

		return procInsId;

	}

	@Transactional(readOnly = false)
	public void save(SalaryInstance salaryInstance, String monthStr,String newStr) {
		/* 若是更改重新上报，则先删除相关信息 */
		if (StringUtils.isNotBlank(salaryInstance.getId())) {
			/* 删除工资实例 */
			salaryInstanceDao.delete(salaryInstance);
			/* 删除工资基础数据 */
			SalaryDetails sd = new SalaryDetails();
			sd.setSalaryInstance(salaryInstance);
			List<SalaryDetails> sdList = salaryDetailsService.findList(sd);
			for (SalaryDetails salaryDetails : sdList) {
				salaryDetailsService.delete(salaryDetails);
			}
			/* 删除工资实例任务 */
			SalaryInstanceTask sit = new SalaryInstanceTask();
			sit.setSalaryInstance(salaryInstance);
			List<SalaryInstanceTask> sitList = salaryInstanceTaskService
					.findList(sit);
			for (SalaryInstanceTask salaryInstanceTask : sitList) {
				salaryInstanceTaskService.delete(salaryInstanceTask);
			}
		}
		/* 保存工资实例信息 */
		this.save(salaryInstance);
		/* 保存以勾选的工资想对应的工资任务信息 */
		for (int i = 0; i < salaryInstance.getSelectedItem().size(); i++) {
			/**/
			if (salaryInstance.getSelectedItem().get(i) != null) {
				SalaryInstanceTask sitTemp = new SalaryInstanceTask();
				/* 设置工资实例任务的工资实例id */
				sitTemp.setSalaryInstance(salaryInstance);
				sitTemp.getSalaryInstance().setId(salaryInstance.getId());
				/* 设置工资实例任务的工资项id */
				sitTemp.setSalaryItems(new SalaryItems());
				sitTemp.getSalaryItems().setId(
						salaryInstance.getSelectedItem().get(i));
				/* 通过工资项id查到相关的工资项信息 */
				sitTemp.setSalaryItems(salaryItemsService.get(sitTemp
						.getSalaryItems().getId()));
				/* 若if条件成立，代表此时循环已经到了福利项，所以直接设置其系数为null */
				if (i >= salaryInstance.getSelectedCoefficient().size()) {
					sitTemp.setCoefficient(null);
				}
				/* 设置工资势实例任务对应的系数 */
				else {
					sitTemp.setCoefficient(salaryInstance
							.getSelectedCoefficient().get(i));
				}
				/* 将工资实例任务添加到工资实例的SalaryInstanceTaskList中 */
				salaryInstance.getSalaryInstanceTaskList().add(sitTemp);
				/* 保存 */
				salaryInstanceTaskService.save(sitTemp);
			}
		}
		/* 计算工资初始结果 */
		List<SalaryDetails> list = HrUtils.calculateSalary(newStr);
		/* 将已经勾选的工资项的值和系数，以及系数保存到数据库中 */
		this.calculateNewSalary(list, salaryInstance, monthStr);
	}

	/**
	 * 启动流程
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void saveActiviti(SalaryInstance salaryInstance) {

		// 申请发起
		if (StringUtils.isBlank(salaryInstance.getProcessInstanceId())) {
			// 启动流程
			this.save(salaryInstance);
			salaryInstance.getAct().setProcInsId(
					this.startProcess(ActUtils.PD_HR_SALARY[0],
							ActUtils.PD_HR_SALARY[1], salaryInstance.getId()));

		}

		// 重新编辑申请
		else {

			Task task = taskService.createTaskQuery()
					.processInstanceId(salaryInstance.getAct().getProcInsId())
					.list().get(0);

			salaryInstance.getAct().setTaskDefKey(task.getTaskDefinitionKey());
			salaryInstance.getAct().setTaskId(task.getId());
			salaryInstance.getAct().setTaskName(task.getName());

			salaryInstance.preUpdate();
			dao.update(salaryInstance);
			if (salaryInstance.getAct().getTaskDefKey()
					.equals("salarymanager1")) {

				salaryInstance
						.getAct()
						.setComment(
								("yes".equals(salaryInstance.getAct().getFlag()) ? "[重申] "
										: "[销毁] ")
										+ salaryInstance.getAct().getComment());

				// 完成流程任务
				Map<String, Object> vars = Maps.newHashMap();
				vars.put("pass", "yes"
						.equals(salaryInstance.getAct().getFlag()) ? "1" : "0");
				actTaskService.complete(salaryInstance.getAct().getTaskId(),
						salaryInstance.getAct().getProcInsId(), salaryInstance
								.getAct().getComment(), vars);
			}

		}

	}

	/**
	 * 审核审批保存
	 * 
	 * @param salaryInstance
	 */
	@Transactional(readOnly = false)
	public void saveAudit(SalaryInstance salaryInstance) {

		// 设置意见
		salaryInstance.getAct().setComment(
				("yes".equals(salaryInstance.getAct().getFlag()) ? "[同意] "
						: "[驳回] ") + salaryInstance.getAct().getComment());

		salaryInstance.preUpdate();
		/* 获取当前人员对应的task */
		Task task = taskService.createTaskQuery()
				.processInstanceId(salaryInstance.getAct().getProcInsId())
				.list().get(0);
		/* 设置工资实例的task星系 */
		salaryInstance.getAct().setTaskDefKey(task.getTaskDefinitionKey());
		salaryInstance.getAct().setTaskId(task.getId());
		salaryInstance.getAct().setTaskName(task.getName());
		/* 设置意见的详细信息 */
		AuditRecords ar = new AuditRecords();
		ar.setBusinessKey(ActUtils.PD_HR_SALARY[0]);
		ar.setTaskKey(salaryInstance.getAct().getTaskDefKey());
		ar.setRecordId(salaryInstance.getId());
		ar.setId(salaryInstance.getId());
		ar.setUpdateBy(UserUtils.getUser());
		ar.setUpdateDate(new Date());

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = salaryInstance.getAct().getTaskDefKey();
		// 审核环节
		if ("salarymanager1".equals(taskDefKey)) {

		} else if ("salarymanager2".equals(taskDefKey)) {

		} else if ("salarymanager3".equals(taskDefKey)) {

		} else if ("salarymanager4".equals(taskDefKey)) {

		} else if ("salarymanager5".equals(taskDefKey)) {
			salaryInstance.setDataState("3");
		}
		// 未知环节，直接返回
		else {
			return;
		}
		/* 保存意见 */
		ar.setAuditOpinion(salaryInstance.getAct().getComment());
		salaryInstance.preUpdate();
		salaryInstanceDao.update(salaryInstance);
		ar.preInsert();
		auditRecordsDao.insert(ar);

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();

		if ("yes".equals(salaryInstance.getAct().getFlag())) {
			vars.put("pass", "1");
		} else if ("no".equals(salaryInstance.getAct().getFlag())) {
			vars.put("pass", "0");
		}
		// vars.put("pass", "yes".equals(attendance.getAct().getFlag()) ? "1"
		// : "0");
		/* 如果一切顺利，则将流程走到下一个节点去 */
		actTaskService.complete(salaryInstance.getAct().getTaskId(),
				salaryInstance.getAct().getProcInsId(), salaryInstance.getAct()
						.getComment(), vars);

	}

	public Page<SalaryInstance> find(Page<SalaryInstance> page,
			SalaryInstance salaryInstance) {

		salaryInstance.setPage(page);
		List<SalaryInstance> list = salaryInstanceDao.findList(salaryInstance);
		page.setList(list);
		for (SalaryInstance entity : list) {
			SalaryInstanceTask data = new SalaryInstanceTask();
			data.setSalaryInstance(entity);
			entity.setSalaryInstanceTaskList(salaryInstanceTaskService
					.findList(data));
			entity.setCreateBy(UserUtils.get(entity.getCreateBy().getId()));
		}
		for (SalaryInstance data : page.getList()) {
			List<Task> tasks = taskService.createTaskQuery()
					.processInstanceId(data.getAct().getProcInsId()).list();
			for (Task task : tasks) {

				data.getAct().setTaskDefKey(task.getTaskDefinitionKey());
				data.getAct().setTaskId(task.getId());
				data.getAct().setTaskName(task.getName());
			}
		}
		return page;
	}

	/**
	 * 计算工资
	 * 
	 * @param list
	 * @param salaryInstance
	 */
	public void calculateNewSalary(List<SalaryDetails> list,
			SalaryInstance salaryInstance, String month) {
		/* 取出工资任务，即勾选项和系数 */
		List<SalaryInstanceTask> salaryInstanceTaskList = salaryInstance
				.getSalaryInstanceTaskList();
		/* 遍历两者 */
		for (SalaryDetails tempSd : list) {
			/* 重新创建一个工资详情对象 */
			SalaryDetails sd = new SalaryDetails();
			for (SalaryInstanceTask tempSit : salaryInstanceTaskList) {
				/* 设置工资实例以、工资实例id以及年月 */
				tempSd.setSalaryInstance(salaryInstance);
				tempSd.setYearMonth(salaryInstance.getYearMonth());
				/* 设置相关属性 */
				sd.setId(tempSd.getId());
				sd.setUser(tempSd.getUser());
				sd.setYearMonth(tempSd.getYearMonth());
				sd.setSalaryInstance(tempSd.getSalaryInstance());
				/* 取出此人对应的信息 */
				SalEmpView sev = salEmpViewDao.get(tempSd.getUser().getId());
				   if(tempSd.getUser().getId().equals("01322b854e7247c7bba7373273d04e58")){
					   System.out.println();
				   }
				/* 将一年发放两次的岗位类型放入List中 */
				List<String> postTypeList = Lists.newArrayList();
				postTypeList.add("1");
				postTypeList.add("4");
				postTypeList.add("6");
				/* 若没有系数，则默认为1 */
				if (tempSit.getCoefficient() == null) {
					tempSit.setCoefficient(1.0);
				}
				try {
					/* 加载SalaryDetail类 */
					Class<?> clazz = Class
							.forName("cn.micromoving.bcp.modules.hr.entity.SalaryDetails");
					/* 取出所有声明的属性 */
					Field[] fields = clazz.getDeclaredFields();
					for (Field f : fields) {

						/* 若该属性为已经勾选了的 */
						if (f.getName().equals(
								tempSit.getSalaryItems().getSalaryMark())) {
							/* 先取出对应的原值 */
							Double oldValue = (Double) Reflections
									.getFieldValue(tempSd, f.getName());
							/* 若原值不为空，则将原值乘以系数 */
							if (oldValue != null) {
								Reflections.setFieldValue(tempSd, f.getName(),
										oldValue * tempSit.getCoefficient());
							}

							/* 若月份为8月，岗位津贴已经勾选 */
							if (sev != null) {
								if (month.equals("08")
										&& f.getName().equals("postSubside")
										&& !postTypeList.contains(sev
												.getPostType())) {
									Reflections.setFieldValue(tempSd,
											f.getName(), 0.0);
								}
							}
							/* 通过反射为sd赋值 */
							Reflections.setFieldValue(
									sd,
									f.getName(),
									Reflections.getFieldValue(tempSd,
											f.getName()));
							break;
						}
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			/* 保存对应的SalaryDetail信息 */
			salaryDetailsService.save(sd);
		}

	}
}
