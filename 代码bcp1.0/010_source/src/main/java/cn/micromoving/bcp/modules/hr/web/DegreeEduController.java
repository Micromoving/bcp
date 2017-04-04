package cn.micromoving.bcp.modules.hr.web;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.mapper.JsonMapper;
import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.entity.Attendance;
import cn.micromoving.bcp.modules.hr.entity.AuditRecords;
import cn.micromoving.bcp.modules.hr.entity.DegreeEdu;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.service.AuditRecordsService;
import cn.micromoving.bcp.modules.hr.service.DegreeEduService;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.hr.service.ReportRecordService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/degreeEdu")
public class DegreeEduController extends BaseController {

	@Autowired
	private DegreeEduService degreeEduService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private ReportRecordService recordService;

	@Autowired
	private AuditRecordsService auditRecordsService;

	@Autowired
	protected TaskService taskService;
	@Autowired
	private EmployeeService employeeService;


	/**
	 * 根据主键， 取得工资信息。
	 * 
	 * @param id
	 *            primary key
	 * @return DegreeEduInstance entity
	 */
	@ModelAttribute
	public DegreeEdu get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return degreeEduService.get(id);
		} else {
			return new DegreeEdu();
		}
	}

	/**
	 * 查询工资信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param DegreeEduInstance
	 *            工资
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部教育经历信息（不分页）
	 */
	@RequestMapping(value = { "list" })
	public String list(DegreeEdu degreeEdu, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		degreeEdu.setUser(UserUtils.getUser());
		Page<DegreeEdu> page = degreeEduService.find(new Page<DegreeEdu>(
				request, response), degreeEdu);
		for(DegreeEdu de: page.getList()){
		    if (de.getOffice().getGrade().equals("3")) {
                de.setOffice(UserUtils.getOfficeById(de
                        .getOffice().getParent().getId()));
            }
		}

		List<ProcessDefinition> list = repositoryService
				.createProcessDefinitionQuery().latestVersion().active()
				.orderByProcessDefinitionKey().asc().list();
		for (ProcessDefinition processDefinition : list) {
			if (processDefinition.getKey().equals(ActUtils.PD_HR_DEGREEEDU[0])) {
				model.addAttribute("process", processDefinition);
			}
		}

		model.addAttribute("page", page);
		return "modules/hr/degreeEduList";
	}

	/**
	 * 根据主键，查询到上报工作量信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param reportedWorkloade
	 *            上报工作量上报工作量entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回上报工作量编辑页面
	 */

	@RequestMapping(value = { "form" })
	public String form(DegreeEdu degreeEdu, Model model,HttpServletRequest request) {
		// degreeEdu = degreeEduService.get(degreeEdu);
		// model.addAttribute("degreeEdu", degreeEdu);
		// /*此处为0说明在并行网关处，只有一个人审批完成*/
		// if (degreeEdu.getDataState().equals("0")) {
		// return "modules/hr/degreeEduForm6";
		// }
		// if (Integer.valueOf(degreeEdu.getDataState()) <= 6) {
		//
		// return "modules/hr/degreeEduForm" + degreeEdu.getDataState();
		// }
		// return "modules/hr/degreeEduAudit";
		degreeEdu.setUser(UserUtils.get(degreeEdu.getUser().getId()));
		degreeEdu.setEmployee(employeeService.get(degreeEdu.getUser().getId()));
		model.addAttribute("degreeEdu", degreeEdu);
		return "modules/hr/degreeEduForm";
	}
	
	@RequestMapping(value = { "middle" })
	public String Middle(HttpServletRequest request, Model model,
			HttpServletResponse response,Employee employee,
			DegreeEdu degreeEdu) {
		/* 通过employeeService.findemploy()方法将employ信息进行分页 */
		Page<Employee> page = employeeService.findEdu(new Page<Employee>(
				request, response), employee);
		/* page信息保存到对应的模型中 */
		model.addAttribute("employeepage", page);
		return "modules/hr/degreeEduMiddle";
	}

	/**
	 * 保存学历教育信息
	 * 
	 * @param attendance
	 */
	@RequestMapping(value = "save")
	public String save(DegreeEdu degreeEdu, HttpServletRequest request,
			RedirectAttributes redirectAttributes,Model model) {
		if (!beanValidator(model, degreeEdu)) {
			return form(degreeEdu, model, request);
		}
		degreeEdu.setUser(UserUtils.get(degreeEdu.getUser().getId()));
		degreeEdu.setEmployee(employeeService.get(degreeEdu.getUser().getId()));
		degreeEduService.save(degreeEdu);
		addMessage(redirectAttributes, "操作成功，数据已经提交。");
		return "redirect:" + adminPath + "/hr/degreeEdu/list";

	}

	/**
	 * 启动学历教育报名流程
	 * 
	 * @param attendance
	 */
	@RequestMapping(value = "commit")
	public String commit(DegreeEdu degreeEdu, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		degreeEdu.setUser(UserUtils.getUser());
		if (degreeEdu.getDegreeIdList() != null) {
			for (int i = 0; i < degreeEdu.getDegreeIdList().size(); i++) {
				if ("1".equals(degreeEdu.getFlagList().get(i))) {
					try {
						degreeEdu.setId(degreeEdu.getDegreeIdList().get(i));
						degreeEduService.commit(degreeEdu);
						addMessage(redirectAttributes, "操作成功，数据已经提交。");
					} catch (Exception e) {
						logger.error("启动学历教育流程失败：", e);
						addMessage(redirectAttributes, "系统内部错误！");
					}
				}
			}
		}

		return "redirect:" + adminPath + "/hr/degreeEdu/list";

	}

	/**
	 * 启动学历教育报名流程
	 * 
	 * @param attendance
	 */
	@RequestMapping(value = "save1", method = RequestMethod.POST)
	public String save1(DegreeEdu degreeEdu, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		degreeEdu.setUser(UserUtils.getUser());

		degreeEduService.save(degreeEdu);

		return "redirect:" + adminPath + "/hr/degreeEdu/list";
	}

	/**
	 * 撤销申请
	 */
	@RequestMapping(value = "delete")
	public String delete(DegreeEdu degreeEdu,
			RedirectAttributes redirectAttributes) {

//		degreeEdu.setUser(UserUtils.getUser());

		degreeEduService.delete(degreeEdu);
//		addMessage(redirectAttributes, "流程已删除，流程ID:"
//				+ degreeEdu.getAct().getProcInsId());
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/hr/degreeEdu/list";
	}

	/**
	 * 读取详细数据
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	public String getDegreeEdu(@PathVariable("id") String id) {
		DegreeEdu degreeEdu = degreeEduService.get(id);
		return JsonMapper.getInstance().toJson(degreeEdu);
	}

	/**
	 * 工单执行（完成任务）
	 * 
	 * @param attendance
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveAudit")
	public String saveAudit(DegreeEdu degreeEdu, Model model,
			HttpServletRequest request) {
		/* 审批 */
		degreeEduService.saveAudit(degreeEdu);
		return "redirect:" + adminPath + "/sys/task/todolist";
	}

	@RequestMapping(value = { "detail" })
	public String detail(DegreeEdu degreeEdu, AuditRecords auditRecords,
			Model model) {

		List<AuditRecords> list = auditRecordsService.findList(auditRecords);
		for (int i = 0; i < list.size(); i++) {
			Attendance attendances = new Attendance();
			attendances.setAuditRecords(list.get(i));
			model.addAttribute("attendances" + i, attendances);
		}

		Task task = taskService.createTaskQuery()
				.processInstanceId(degreeEdu.getAct().getProcInsId())
				.singleResult();
		if (task != null) {
			degreeEdu.getAct().setTaskDefKey(task.getTaskDefinitionKey());
		}

		List<ProcessDefinition> listPD = repositoryService
				.createProcessDefinitionQuery().latestVersion().active()
				.orderByProcessDefinitionKey().asc().list();
		for (ProcessDefinition processDefinition : listPD) {
			if (processDefinition.getKey().equals(ActUtils.PD_HR_DEGREEEDU[0])) {
				model.addAttribute("process", processDefinition);
				degreeEdu.getAct().setProcDefId(processDefinition.getId());
			}
		}

		model.addAttribute("degreeEdu", degreeEdu);
		return "modules/hr/leaveDetail";
	}

}
