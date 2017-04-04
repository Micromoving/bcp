package cn.micromoving.bcp.modules.hr.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.common.utils.Collections3;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.act.service.ActTaskService;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.dao.AuditRecordsDao;
import cn.micromoving.bcp.modules.hr.dao.DegreeEduDao;
import cn.micromoving.bcp.modules.hr.entity.AuditRecords;
import cn.micromoving.bcp.modules.hr.entity.DegreeEdu;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstance;
import cn.micromoving.bcp.modules.sys.dao.UserDao;
import cn.micromoving.bcp.modules.sys.entity.Role;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Maps;

@Service
@Transactional(readOnly = true)
public class DegreeEduService extends CrudService<DegreeEduDao, DegreeEdu> {

	@Autowired
	private DegreeEduDao degreeEduDao;

	protected HistoryService historyService;

	@Autowired
	protected RepositoryService repositoryService;

	@Autowired
	private AuditRecordsDao auditRecordsDao;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	protected TaskService taskService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private ActTaskService actTaskService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private UserDao userDao;

	public Page<DegreeEdu> find(Page<DegreeEdu> page, DegreeEdu degreeEdu) {
		degreeEdu.setPage(page);
		page.setList(degreeEduDao.findList(degreeEdu));

		for (DegreeEdu data : page.getList()) {
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

	// /**
	// * 获取流程详细及工作流参数
	// *
	// * @param id
	// */
	// @SuppressWarnings("unchecked")
	// public DegreeEdu get(String id) {
	// DegreeEdu degreeEdu = degreeEduDao.get(id);
	// Map<String, Object> variables = null;
	// HistoricProcessInstance historicProcessInstance = historyService
	// .createHistoricProcessInstanceQuery()
	// .processInstanceId(degreeEdu.getAct().getProcInsId())
	// .singleResult();
	// if (historicProcessInstance != null) {
	// variables = Collections3.extractToMap(
	// historyService.createHistoricVariableInstanceQuery()
	// .processInstanceId(historicProcessInstance.getId())
	// .list(), "variableName", "value");
	// } else {
	// variables = runtimeService.getVariables(runtimeService
	// .createProcessInstanceQuery()
	// .processInstanceId(degreeEdu.getAct().getProcInsId())
	// .active().singleResult().getId());
	// }
	// degreeEdu.getAct().setVars(variables);
	// return degreeEdu;
	// }

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
		String[] str = { "personnel_clerk", "department_leader",
				"academic_leadership", "studepartment_leadership",
				"edu_department" };
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

	/**
	 * 审核审批保存
	 * 
	 * @param degreeEdu
	 */
	@Transactional(readOnly = false)
	public void auditSave(DegreeEdu degreeEdu, int isPass) {

		// 设置意见
		degreeEdu.getAct()
				.setComment(
						("yes".equals(degreeEdu.getAct().getFlag()) ? "[同意] "
								: "[驳回] ") + degreeEdu.getAct().getComment());

		degreeEdu.preUpdate();

		Task task = taskService.createTaskQuery()
				.processInstanceId(degreeEdu.getAct().getProcInsId()).list()
				.get(0);

		degreeEdu.getAct().setTaskDefKey(task.getTaskDefinitionKey());
		degreeEdu.getAct().setTaskId(task.getId());
		degreeEdu.getAct().setTaskName(task.getName());

		AuditRecords ar = new AuditRecords();
		ar.setBusinessKey(ActUtils.PD_HR_DEGREEEDU[0]);
		ar.setTaskKey(degreeEdu.getAct().getTaskDefKey());
		ar.setRecordId(degreeEdu.getId());
		ar.setId(degreeEdu.getId());
		ar.setUpdateBy(UserUtils.getUser());
		ar.setUpdateDate(new Date());

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = degreeEdu.getAct().getTaskDefKey();
		// 审核环节
		if ("degreeaudit1".equals(taskDefKey)) {

		} else if ("degreeaudit2".equals(taskDefKey)) {

		} else if ("degreeaudit3".equals(taskDefKey)) {

		} else if ("degreeaudit4".equals(taskDefKey)) {

		} else if ("degreeaudit5".equals(taskDefKey)) {

		} else if ("degreeaudit6".equals(taskDefKey)) {

		} else if ("degreeaudit7".equals(taskDefKey)) {

		} else if ("degreeaudit8".equals(taskDefKey)) {

		}
		// 未知环节，直接返回
		else {
			return;
		}
		ar.setAuditOpinion(degreeEdu.getAct().getComment());
		degreeEduDao.update(degreeEdu);
		ar.preInsert();
		auditRecordsDao.insert(ar);

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();

		if ("yes".equals(degreeEdu.getAct().getFlag())) {
			vars.put("pass", "1");
		} else if ("no".equals(degreeEdu.getAct().getFlag())) {
			vars.put("pass", "0");
		}
		// vars.put("pass", "yes".equals(degreeEdu.getAct().getFlag()) ? "1"
		// : "0");
		actTaskService
				.complete(degreeEdu.getAct().getTaskId(), degreeEdu.getAct()
						.getProcInsId(), degreeEdu.getAct().getComment(), vars);

	}

	/**
	 * 启动流程
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void commit(DegreeEdu degreeEdu) {

		// 申请发起
		if (StringUtils.isBlank(degreeEdu.getProcessInstanceId())) {
			degreeEdu = this.get(degreeEdu);
			degreeEdu.setDataState("1");
			this.save(degreeEdu);
			// 启动流程
			degreeEdu.getAct().setProcInsId(
					this.startProcess(ActUtils.PD_HR_DEGREEEDU[0],
							ActUtils.PD_HR_DEGREEEDU[1], degreeEdu.getId()));
		}

		// 重新编辑申请
		else {

			Task task = taskService.createTaskQuery()
					.processInstanceId(degreeEdu.getAct().getProcInsId())
					.list().get(0);

			degreeEdu.getAct().setTaskDefKey(task.getTaskDefinitionKey());
			degreeEdu.getAct().setTaskId(task.getId());
			degreeEdu.getAct().setTaskName(task.getName());

			degreeEdu.preUpdate();
			dao.update(degreeEdu);
		}

	}

	/**
	 * 审核审批保存
	 * 
	 * @param salaryInstance
	 */
	@Transactional(readOnly = false)
	public void saveAudit(DegreeEdu degreeEdu) {

		// 设置意见
		degreeEdu.getAct()
				.setComment(
						("yes".equals(degreeEdu.getAct().getFlag()) ? "[同意] "
								: "[驳回] ") + degreeEdu.getAct().getComment());

		/* 获取当前人员对应的task */
		Task task = taskService.createTaskQuery()
				.processInstanceId(degreeEdu.getAct().getProcInsId()).list()
				.get(0);
		/* 设置工资实例的task星系 */
		degreeEdu.getAct().setTaskDefKey(task.getTaskDefinitionKey());
		degreeEdu.getAct().setTaskId(task.getId());
		degreeEdu.getAct().setTaskName(task.getName());
		/* 设置意见的详细信息 */
		AuditRecords ar = new AuditRecords();
		ar.setBusinessKey(ActUtils.PD_HR_SALARY[0]);
		ar.setTaskKey(degreeEdu.getAct().getTaskDefKey());
		ar.setRecordId(degreeEdu.getId());
		ar.setId(degreeEdu.getId());
		ar.setUpdateBy(UserUtils.getUser());
		ar.setUpdateDate(new Date());

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = degreeEdu.getAct().getTaskDefKey();
		// 审核环节
		if ("degreeaudit2".equals(taskDefKey)) {
			degreeEdu.setDataState("2");
		} else if ("degreeaudit3".equals(taskDefKey)) {
			degreeEdu.setDataState("3");
		} else if ("degreeaudit4".equals(taskDefKey)) {
			degreeEdu.setDataState("4");
		} else if ("degreeaudit5".equals(taskDefKey)) {
			degreeEdu.setDataState("5");
		} else if ("degreeaudit6".equals(taskDefKey)) {
			degreeEdu.setDataState("6");
		} else if ("degreeaudit71".equals(taskDefKey)
				|| "degreeaudit72".equals(taskDefKey)) {
			degreeEduDao.get(degreeEdu);
			if (degreeEdu.getDataState().equals("6")) {
				degreeEdu.setDataState("0");
			} else if (degreeEdu.getDataState().equals("0")) {
				degreeEdu.setDataState("7");
			}
		} else if ("degreeaudit8".equals(taskDefKey)) {
			degreeEdu.setDataState("8");
		} else if ("degreeaudit9".equals(taskDefKey)) {
			degreeEdu.setDataState("9");
		}
		// 未知环节，直接返回
		else {
			return;
		}
		/* 保存意见 */
		ar.setAuditOpinion(degreeEdu.getAct().getComment());
		degreeEdu.preUpdate();
		degreeEduDao.update(degreeEdu);
		ar.preInsert();
		auditRecordsDao.insert(ar);

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();

		if ("yes".equals(degreeEdu.getAct().getFlag())) {
			vars.put("pass", "1");
		} else if ("no".equals(degreeEdu.getAct().getFlag())) {
			vars.put("pass", "0");
		}
		// vars.put("pass", "yes".equals(attendance.getAct().getFlag()) ? "1"
		// : "0");
		/* 如果一切顺利，则将流程走到下一个节点去 */
		actTaskService
				.complete(degreeEdu.getAct().getTaskId(), degreeEdu.getAct()
						.getProcInsId(), degreeEdu.getAct().getComment(), vars);

	}

}
