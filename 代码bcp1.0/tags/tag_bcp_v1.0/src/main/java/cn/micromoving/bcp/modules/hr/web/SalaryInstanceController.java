package cn.micromoving.bcp.modules.hr.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.ExportExcel;
import cn.micromoving.bcp.common.utils.excel.ImportExcel;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.dao.SalViewDao;
import cn.micromoving.bcp.modules.hr.dao.SalaryDetailsDao;
import cn.micromoving.bcp.modules.hr.entity.SalaryDetails;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstance;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstanceTask;
import cn.micromoving.bcp.modules.hr.entity.SalaryItems;
import cn.micromoving.bcp.modules.hr.service.SalaryDetailsService;
import cn.micromoving.bcp.modules.hr.service.SalaryInstanceService;
import cn.micromoving.bcp.modules.hr.service.SalaryInstanceTaskService;
import cn.micromoving.bcp.modules.hr.service.SalaryItemsService;
import cn.micromoving.bcp.modules.hr.utils.HrUtils;
import cn.micromoving.bcp.modules.sys.dao.UserDao;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Lists;

/**
 * 工资实例Controller
 * 
 * @author wangzhichen
 * @version 2016-7-23
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/salaryInstance")
public class SalaryInstanceController extends BaseController {

	@Autowired
	private SalaryInstanceService salaryInstanceService;

	@Autowired
	private SalaryDetailsService salaryDetailsService;

	@Autowired
	private SalaryDetailsDao salaryDetailsDao;

	@Autowired
	private SalaryInstanceTaskService salaryInstanceTaskService;

	@Autowired
	private SalaryItemsService salaryItemsService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private SalViewDao salViewDao;

	/**
	 * 根据主键， 取得工资信息。
	 * 
	 * @param id
	 *            primary key
	 * @return SalaryInstance entity
	 */
	@ModelAttribute
	public SalaryInstance get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return salaryInstanceService.get(id);
		} else {
			return new SalaryInstance();
		}
	}

	/**
	 * 查询工资信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param salaryInstance
	 *            工资
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部教育经历信息（不分页）
	 */
	@RequestMapping(value = { "list", "" })
	public String list(SalaryInstance salaryInstance,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		/* 从数据库中取出工资实例详细信息，并分页 */
		Page<SalaryInstance> page = salaryInstanceService.find(
				new Page<SalaryInstance>(request, response), salaryInstance);
		/*
		 * 通过repositoryService取出所有已部署的， 版本号为最新（因为可能相同的流程会发布多次）的 且状态为激活的流程定义实例，
		 * 并按流程定义的key排序（asc升序，即从小到大）
		 */
		List<ProcessDefinition> processList = repositoryService
				.createProcessDefinitionQuery().latestVersion().active()
				.orderByProcessDefinitionKey().asc().list();
		/* 遍历所有的流程定义实例 */
		for (ProcessDefinition processDefinition : processList) {
			/* 通过流程定义key来获得对应的流程定义实例 */
			/* 其中流程定义的key是写死在ActUtils中的，对应的是流程图中定义的流程id */
			if (processDefinition.getKey().equals(ActUtils.PD_HR_SALARY[0])) {
				/* 将对应的流程定义实例对象放入model，这样在页面中就可以同过流程定义id来区分采用的是哪一种流程 */
				model.addAttribute("process", processDefinition);
			}
		}

		model.addAttribute("salaryInstance", salaryInstance);
		model.addAttribute("page", page);
		return "modules/hr/salaryInstanceList";
	}

	/**
	 * 根据主键，查询到工资信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param salaryInstance
	 *            工资entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回教育经历编辑页面
	 */
	@RequestMapping(value = "form")
	public String form(SalaryInstance salaryInstance, Model model) {
		/* 若工资实例id有值则说明是更改操作，若没有则说明是新增操作 */
		/* 若有则取出对应的工资实例 */
		if (salaryInstance.getId() != null) {
			salaryInstance = salaryInstanceService.get(salaryInstance.getId());
		}
		/* 取出所有的工资项 */
		List<SalaryItems> list = salaryItemsService.findList(new SalaryItems());
		/* 初始化SalaryInstanceTaskList */
		salaryInstance
				.setSalaryInstanceTaskList(new ArrayList<SalaryInstanceTask>());
		/* 遍历所有工资项，将所有工资项添加到工资实例的SalaryInstanceTaskList中 */
		for (SalaryItems temp : list) {
			SalaryInstanceTask sit = new SalaryInstanceTask();
			sit.setSalaryItems(temp);
			salaryInstance.getSalaryInstanceTaskList().add(sit);
		}
		/* 通过工资实例id来找到对应工资任务 */
		SalaryInstanceTask sit = new SalaryInstanceTask();
		sit.setSalaryInstance(new SalaryInstance());
		sit.getSalaryInstance().setId(salaryInstance.getId());
		List<SalaryInstanceTask> taskList = salaryInstanceTaskService
				.findList(sit);
		/* 遍历所有工资项 */
		for (SalaryItems items : list) {
			/* 遍历所有已勾选的工资实例任务 */
			for (SalaryInstanceTask task : taskList) {
				/* 如果以勾选设置useable为1，否则为0 */
				if (items.getId().equals(task.getSalaryItems().getId())) {
					items.setUseable("1");
					break;
				} else {
					items.setUseable("0");
				}
			}
		}
		/* 同步工资实例的SalaryInstanceTaskList的勾选项的useable属性 */
		for (int i = 0; i < list.size(); i++) {

			salaryInstance.getSalaryInstanceTaskList().get(i).getSalaryItems()
					.setUseable(list.get(i).getUseable());
		}
		/* 同步以勾选项的系数，在更改的时候显示 */
		for (int i = 0; i < salaryInstance.getSalaryInstanceTaskList().size(); i++) {
			/* 判断工资类型，若为工资项 */
			if (salaryInstance.getSalaryInstanceTaskList().get(i)
					.getSalaryItems().getSalaryType().equals("1")) {
				/* 遍历工资任务 */
				for (SalaryInstanceTask task : taskList) {
					/* 如果该任务对应的工资项以勾选，则同步系数。 */
					if (task.getSalaryItems()
							.getId()
							.equals(salaryInstance.getSalaryInstanceTaskList()
									.get(i).getSalaryItems().getId())) {

						salaryInstance.getSalaryInstanceTaskList().get(i)
								.setCoefficient(task.getCoefficient());
					}
				}
			}
			/* 判断工资类型，若为福利项，则设置对应的系数为空 */
			else {
				salaryInstance.getSalaryInstanceTaskList().get(i)
						.setCoefficient(null);
			}

		}
		model.addAttribute("salaryInstance", salaryInstance);
		return "modules/hr/salaryInstanceForm";
	}

	/**
	 * 启动工资
	 * 
	 * @param attendance
	 */

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(SalaryInstance salaryInstance,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		/* 设置启动人，启动时间属性 */
		salaryInstance.setCreateBy(UserUtils.getUser());
		salaryInstance.setCreateDate(new Date());
		/* 重新设置yearMonth格式为yyyyMM */
		String monthStr = null;
		if (StringUtils.isBlank(salaryInstance.getId())) {
			String str = salaryInstance.getYearMonth();
			String yearStr = str.substring(0, 4);
			monthStr = str.substring(5, 7);
			String newStr = yearStr.concat(monthStr);
			salaryInstance.setYearMonth(newStr);
		} else {
			monthStr = salaryInstance.getYearMonth().substring(4, 6);
		}
		/* 初始化SalaryInstanceTaskList */
		salaryInstance
				.setSalaryInstanceTaskList(new ArrayList<SalaryInstanceTask>());
		try {
			/* 保存工资实例，工资任务，计算工资初试结果 */
			salaryInstanceService.save(salaryInstance, monthStr);
			addMessage(redirectAttributes, "操作成功，数据已经提交。");
		} catch (Exception e) {
			logger.error("操作失败！");
			addMessage(redirectAttributes, "操作失败，系统内部错误！");
		}

		return "redirect:" + adminPath + "/hr/salaryInstance/list";
	}

	@RequestMapping(value = "commitActiviti")
	public String commitActiviti(SalaryInstance salaryInstance, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		salaryInstance = salaryInstanceService.get(salaryInstance);
		try {
			/* 设置启动工资状态 */
			salaryInstance.setDataState("2");
			/* 启动工资流程 */
			salaryInstanceService.saveActiviti(salaryInstance);
			addMessage(redirectAttributes, "工资流程已启动。请耐心等待。");
		} catch (Exception e) {
			logger.error("操作失败！");
			addMessage(redirectAttributes, "操作失败，系统内部错误！");
		}
		return "redirect:" + adminPath + "/hr/salaryInstance/list";
	}

	/**
	 * 工单执行（完成任务）
	 * 
	 * @param salaryInstancce
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveAudit")
	public String saveAudit(SalaryInstance salaryInstance, Model model,
			HttpServletRequest request) {
		/* 获取审核状态 */
		salaryInstance.getAct().setFlag(request.getParameter("flag"));
		/* 审批 */
		salaryInstanceService.saveAudit(salaryInstance);
		if ("3".equals(salaryInstance.getDataState())) {
			SalaryDetails sd = new SalaryDetails();
			sd.setSalaryInstance(salaryInstance);
			List<SalaryDetails> list = salaryDetailsService.findList(sd);
			for (SalaryDetails salaryDetails : list) {
				salaryDetails.setWithholdTaxes(HrUtils
						.taxDeduction(salaryDetails));
				salaryDetailsService.save(salaryDetails);
			}
		}
		return "redirect:" + adminPath + "/sys/task/todolist";
	}

	/**
	 * 上报明细
	 * 
	 * @param salaryInstance
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "reportRecord")
	public String reportRecord(SalaryInstance salaryInstance, Model model) {
		model.addAttribute("salaryInstance", salaryInstance);
		return "modules/hr/reportRecordList";
	}

	/**
	 * 初始结果
	 * 
	 * @param salaryInstance
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "initialResults")
	public String initialResults(SalaryInstanceTask salaryInstanceTask,
			SalaryDetails salaryDetails, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		/* 通过工资实例id找出对应的工资实例任务 */
		List<SalaryInstanceTask> taskList = salaryInstanceTaskService
				.findList(salaryInstanceTask);
		/* 通过工资实例id找出对应的工资详情 */
		List<SalaryDetails> salaryList = salaryDetailsService
				.findList(salaryDetails);
		SalaryDetails sumData = new SalaryDetails();
		for (SalaryDetails tempData : salaryList) {
			/* 职级工资 */
			sumData.setProfessionalLevelSalary(sumData
					.getProfessionalLevelSalary()
					+ tempData.getProfessionalLevelSalary());

			/* 岗位工资 */
			sumData.setPostSalary(sumData.getPostSalary()
					+ tempData.getPostSalary());
			/* 岗位津贴 */
			sumData.setPostSubside(sumData.getPostSubside()
					+ tempData.getPostSubside());
			/* 新增补贴 */
			sumData.setNewSubsidies(sumData.getNewSubsidies()
					+ tempData.getNewSubsidies());
			/* 院龄补贴 */
			sumData.setSchoolAgeSubside(sumData.getSchoolAgeSubside()
					+ tempData.getSchoolAgeSubside());

			/* 职务补助 */
			sumData.setPositionSubsidies(sumData.getPositionSubsidies()
					+ tempData.getPositionSubsidies());
			/* 卫生费 */
			sumData.setHealthCosts(sumData.getHealthCosts()
					+ tempData.getHealthCosts());
			/* 独子费 */
			sumData.setTheOnlyFee(sumData.getTheOnlyFee()
					+ tempData.getTheOnlyFee());
			/* 班主任费 */
			sumData.setTeacherChargeFee(sumData.getTeacherChargeFee()
					+ tempData.getTeacherChargeFee());
			/* 课酬金 */
			sumData.setClassFee(sumData.getClassFee() + tempData.getClassFee());
			/* 补房基金 */
			sumData.setFillHousingFund(sumData.getFillHousingFund()
					+ tempData.getFillHousingFund());
			/* 扣税金 */
			sumData.setWithholdTaxes(sumData.getWithholdTaxes()
					+ tempData.getWithholdTaxes());
			/* 扣房积金a */
			sumData.setBuckleRoomA(sumData.getBuckleRoomA()
					+ tempData.getBuckleRoomA());
			/* 扣房积金b */
			sumData.setBuckleRoomB(sumData.getBuckleRoomB()
					+ tempData.getBuckleRoomB());
			/* 扣医保 */
			sumData.setBuckleHealthCare(sumData.getBuckleHealthCare()
					+ tempData.getBuckleHealthCare());
			/* 扣失保 */
			sumData.setBuckleUnemploymentInsurance(sumData
					.getBuckleUnemploymentInsurance()
					+ tempData.getBuckleUnemploymentInsurance());
			/* 扣养老 */
			sumData.setBuckleEndowmentInsurance(sumData
					.getBuckleEndowmentInsurance()
					+ tempData.getBuckleEndowmentInsurance());
			/* 补缴社保 */
			sumData.setPaymentSocialSecurity(sumData.getPaymentSocialSecurity()
					+ tempData.getPaymentSocialSecurity());
			/* 扣房租 */
			sumData.setBuckleRent(sumData.getBuckleRent()
					+ tempData.getBuckleRent());
			/* 扣借款 */
			sumData.setBuckleBorrowing(sumData.getBuckleBorrowing()
					+ tempData.getBuckleBorrowing());
			/* 扣缺勤 */
			sumData.setBuckleAbsenteeism(sumData.getBuckleAbsenteeism()
					+ tempData.getBuckleAbsenteeism());

			/* 扣取暖 */
			sumData.setBuckleWarm(sumData.getBuckleWarm()
					+ tempData.getBuckleWarm());
			/* 扣多发 */
			sumData.setBuckleExtraWages(sumData.getBuckleExtraWages()
					+ tempData.getBuckleExtraWages());
			/* 扣职业年金 */
			sumData.setBuckOccupationalAnnuity(sumData
					.getBuckOccupationalAnnuity()
					+ tempData.getBuckOccupationalAnnuity());
			/* 扣生育 */
			sumData.setBuckMaternityInsurance(sumData
					.getBuckMaternityInsurance()
					+ tempData.getBuckMaternityInsurance());
			/* 扣工伤 */
			sumData.setBuckEmpInjuryInsurance(sumData
					.getBuckEmpInjuryInsurance()
					+ tempData.getBuckEmpInjuryInsurance());
			/* 扣公务员医疗补助 */
			sumData.setBuckgongyi(sumData.getBuckgongyi()
					+ tempData.getBuckgongyi());
			/* 值班 */
			sumData.setDuty(sumData.getDuty() + tempData.getDuty());
			/* 合计 */
			sumData.setTotal(sumData.getTotal() + tempData.getTotal());
			/* 备用1 */
			sumData.setDummy1(sumData.getDummy1() + tempData.getDummy1());
			/* 备用2 */
			sumData.setDummy2(sumData.getDummy2() + tempData.getDummy2());
			/* 备用3 */
			sumData.setDummy3(sumData.getDummy3() + tempData.getDummy3());
			/* 备用4 */
			sumData.setDummy4(sumData.getDummy4() + tempData.getDummy4());
			/* 备用5 */
			sumData.setDummy5(sumData.getDummy5() + tempData.getDummy5());
			/* 备用6 */
			sumData.setDummy6(sumData.getDummy6() + tempData.getDummy6());
			/* 备用7 */
			sumData.setDummy7(sumData.getDummy7() + tempData.getDummy7());
			/* 备用8 */
			sumData.setDummy8(sumData.getDummy8() + tempData.getDummy8());
			/* 备用9 */
			sumData.setDummy9(sumData.getDummy9() + tempData.getDummy9());
			/* 备用10 */
			sumData.setDummy10(sumData.getDummy10() + tempData.getDummy10());
			/* 备用11 */
			sumData.setDummy11(sumData.getDummy11() + tempData.getDummy11());
			/* 备用12 */
			sumData.setDummy12(sumData.getDummy12() + tempData.getDummy12());
			/* 备用13 */
			sumData.setDummy13(sumData.getDummy13() + tempData.getDummy13());
			/* 备用14 */
			sumData.setDummy14(sumData.getDummy14() + tempData.getDummy14());
			/* 备用15 */
			sumData.setDummy15(sumData.getDummy15() + tempData.getDummy15());
			/* 备用16 */
			sumData.setDummy16(sumData.getDummy16() + tempData.getDummy16());
			/* 备用17 */
			sumData.setDummy17(sumData.getDummy17() + tempData.getDummy17());
			/* 备用18 */
			sumData.setDummy18(sumData.getDummy18() + tempData.getDummy18());
			/* 备用19 */
			sumData.setDummy19(sumData.getDummy19() + tempData.getDummy19());
			/* 备用20 */
			sumData.setDummy20(sumData.getDummy20() + tempData.getDummy20());
			/* 绩效工资 */
			sumData.setPerformanceSalary(sumData.getPerformanceSalary()
					+ tempData.getPerformanceSalary());
			/* 年终绩效 */

			sumData.setYearEndPerformance(sumData.getYearEndPerformance()
					+ tempData.getYearEndPerformance());
			/* 精神文明奖1 */
			sumData.setSpiritualCivilization1(sumData
					.getSpiritualCivilization1()
					+ tempData.getSpiritualCivilization1());
			/* 精神文明奖2 */
			sumData.setSpiritualCivilization2(sumData
					.getSpiritualCivilization2()
					+ tempData.getSpiritualCivilization2());
			/* 精神文明奖3 */
			sumData.setSpiritualCivilization3(sumData
					.getSpiritualCivilization3()
					+ tempData.getSpiritualCivilization3());
			/* 取暖费用 */
			sumData.setHeatingCosts(sumData.getHeatingCosts()
					+ tempData.getHeatingCosts());
			/* 扣生保 */
			sumData.setBirthInsurance(sumData.getBirthInsurance()
					+ tempData.getBirthInsurance());

			/* 扣除工资 */
			sumData.setBuckleWages(sumData.getBuckleWages()
					+ tempData.getBuckleWages());
			/* 应发工资 */
			sumData.setInitialWages(sumData.getInitialWages()
					+ tempData.getInitialWages());
		}
		/* 获取对应的工资实例 */
		SalaryInstance salaryInstance = salaryInstanceService
				.get(salaryInstanceTask.getSalaryInstance());
		/* 若工资实例不为空，将其对应的流程相关信息保存进去，因为页面中需要进行判断 */
		if (salaryInstance != null) {
			/* 通过流程实例id，获取当前人员对应的流程任务信息 */
			List<Task> taskList1 = taskService.createTaskQuery()
					.processInstanceId(salaryInstance.getAct().getProcInsId())
					.list();
			if (taskList1.size() > 0) {
				Task task = taskList1.get(0);
				/* 将流程task信息保存到工资实例中 */
				salaryInstance.getAct().setTaskDefKey(
						task.getTaskDefinitionKey());
				salaryInstance.getAct().setTaskId(task.getId());
				salaryInstance.getAct().setTaskName(task.getName());
			}
		}
		model.addAttribute("salaryDetails", salaryDetails);
		model.addAttribute("salaryInstance", salaryInstance);
		model.addAttribute("taskList", taskList);
		model.addAttribute("salaryList", salaryList);
		model.addAttribute("sumData", sumData);

		return "modules/hr/initialResults";
	}

	/**
	 * 导出工资信息
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "export")
	public void exportFile(SalaryInstanceTask salaryInstanceTask,
			SalaryDetails salaryDetails, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws Exception {
		/* 设置表头列表 */
		List<String> list = Lists.newArrayList();
		List<SalaryItems> siList = Lists.newArrayList();
		list.add("登录名");
		list.add("姓名");
		list.add("部门");
		/* 通过salaryInstanceId查询到salaryInstance对象 */
		salaryDetails.setSalaryInstance(salaryInstanceService.get(salaryDetails
				.getSalaryInstance()));
		/* 同步上报年月 */
		salaryDetails.setYearMonth(salaryDetails.getSalaryInstance()
				.getYearMonth());
		/* 同步工资实例 */
		salaryInstanceTask.setSalaryInstance(salaryDetails.getSalaryInstance());
		/* 查询到对应的勾选项信息 */
		List<SalaryInstanceTask> sitList = salaryInstanceTaskService
				.findList(salaryInstanceTask);
		/* 生成表头 */
		for (SalaryInstanceTask temp : sitList) {
			SalaryItems si = salaryItemsService.get(temp.getSalaryItems());
			list.add(si.getSalaryItemsName());
			siList.add(si);
		}

		list.add("总计");
		/* 查询到所有的工资详情对象 */
		salaryDetails.getSalaryInstance().setDataState("");
		List<SalaryDetails> sdList = salaryDetailsService
				.findList(salaryDetails);
		try {
			/* 定义生成的excel文件名 */
			String fileName = "工资信息" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			/* 初始化ExportExcel对象 */
			/* 这是该sheet的第一、二行内容 */
			ExportExcel ee = new ExportExcel(salaryDetails.getYearMonth()
					.substring(0, 4)
					+ "年"
					+ salaryDetails.getYearMonth().substring(4) + "月" + "工资信息",
					list);
			/* 填充ExportExcel对象的内容 */
			ee.getBodyList(salaryInstanceTask, salaryDetails, sitList, siList,
					sdList);
			/* 写出excel内容 */
			ee.write(response, fileName).dispose();
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出工资信息失败！失败信息：" + e.getMessage());
		}
	}

	/**
	 * 下载导入工资信息模板
	 * 
	 * @param salaryInstanceTask
	 * @param salaryDetails
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "import/template")
	public String importFileTemplate(SalaryInstanceTask salaryInstanceTask,
			SalaryDetails salaryDetails, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		/* 设置表头列表 */
		List<String> list = Lists.newArrayList();
		list.add("登录名");
		list.add("姓名");
		list.add("部门");
		/* 通过salaryInstanceId查询到salaryInstance对象 */
		salaryDetails.setSalaryInstance(salaryInstanceService.get(salaryDetails
				.getSalaryInstance()));
		salaryInstanceTask.setSalaryInstance(salaryDetails.getSalaryInstance());
		/* 同步上报年月 */
		salaryDetails.setYearMonth(salaryDetails.getSalaryInstance()
				.getYearMonth());
		/* 同步工资实例 */
		salaryInstanceTask.setSalaryInstance(salaryDetails.getSalaryInstance());
		/* 查询到对应的勾选项信息 */
		List<SalaryInstanceTask> sitList = salaryInstanceTaskService
				.findList(salaryInstanceTask);
		/* 生成表头 */
		for (SalaryInstanceTask temp : sitList) {
			SalaryItems si = salaryItemsService.get(temp.getSalaryItems());
			list.add(si.getSalaryItemsName());
		}
		/* 任何时候无论勾选不勾选都导出社保项的信息 */
		List<String> list2 = Lists.newArrayList();
		list2.add("住房公积金");
		list2.add("基本医疗保险");
		list2.add("失业保险");
		list2.add("基本养老保险");
		list2.add("生育保险");
		for (String temp : list2) {
			if (!list.contains(temp)) {
				list.add(temp);
			}
		}
		list.add("总计");
		try {
			/* 定义生成的excel文件名 */
			String fileName = "工资信息" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			/* 初始化ExportExcel对象 */
			/* 这是该sheet的第一、二行内容 */
			ExportExcel ee = new ExportExcel(salaryDetails.getYearMonth()
					.substring(0, 4)
					+ "年"
					+ salaryDetails.getYearMonth().substring(4) + "月" + "工资信息",
					list);
			/* 写出 */
			ee.write(response, fileName).dispose();
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出工资信息模板失败！失败信息：" + e.getMessage());
		}
		return null;
	}

	/**
	 * 导入用户工资信息
	 * 
	 * @param file
	 * @param salaryInstanceTask
	 * @param salaryDetails
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "import")
	public String importFile(MultipartFile file, Model model,
			SalaryInstanceTask salaryInstanceTask, SalaryDetails salaryDetails,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws Exception {
		if (file.getSize() == 0) {
			return "redirect:" + adminPath
					+ "/hr/salaryInstance/initialResults?salaryInstance.id="
					+ salaryDetails.getSalaryInstance().getId() + "&repage";
		}
		List<SalaryItems> siList = Lists.newArrayList();
		/* 通过salaryInstanceId查询到salaryInstance对象 */
		salaryDetails.setSalaryInstance(salaryInstanceService.get(salaryDetails
				.getSalaryInstance()));
		/* 同步上报年月 */
		salaryDetails.setYearMonth(salaryDetails.getSalaryInstance()
				.getYearMonth());
		/* 设置salaryInstanceTask的salaryInstance */
		salaryInstanceTask.setSalaryInstance(salaryDetails.getSalaryInstance());
		/* 查询出所有的勾选项 */
		List<SalaryInstanceTask> sitList = salaryInstanceTaskService
				.findList(salaryInstanceTask);
		/* 查询出所有勾选项的详细信息 */
		for (SalaryInstanceTask temp : sitList) {
			SalaryItems si = salaryItemsService.get(temp.getSalaryItems());
			siList.add(si);
		}
		/* 查询从所有的工资详情实例 */
		salaryDetails.getSalaryInstance().setDataState("");
		List<SalaryDetails> sdList = salaryDetailsService
				.findList(salaryDetails);
		/* 初始化ei对象 */
		ImportExcel ei = new ImportExcel(file, 1, 0);
		/* 获取到excel对应的工资详情信息 */
		List<SalaryDetails> list = ei.getSalaryDetailsList(siList,
				salaryDetails.getSalaryInstance());
		/* 若导入的是已经存在的用户，则先delete调其原来的记录，在insert;否则直接insert */
		for (SalaryDetails temp : list) {
			for (SalaryDetails sd : sdList) {
				if (temp.getUser().getId().equals(sd.getUser().getId())) {
					salaryDetailsService.delete(sd);
					break;
				}
			}
			salaryDetailsService.save(temp);
		}
		/* 重定向 */
		return "redirect:" + adminPath
				+ "/hr/salaryInstance/initialResults?salaryInstance.id="
				+ salaryDetails.getSalaryInstance().getId() + "&repage";
	}
}
