package cn.micromoving.bcp.modules.hr.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.entity.Attendance;
import cn.micromoving.bcp.modules.hr.entity.DegreeEdu;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.entity.TrainExperience;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.hr.service.RecruitmentService;
import cn.micromoving.bcp.modules.hr.service.TrainExperienceService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/trainExperience")
public class TrainExperienceController extends BaseController {
	@Autowired
	private TrainExperienceService trainExperienceService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RecruitmentService recruitmentService;
	@Autowired
	private EmployeeService employeeService;

	/**
	 * 根据主键， 取得培训经验记录。
	 * 
	 * @param id
	 *            primary key
	 * @return Work entity
	 */
	@ModelAttribute
	public TrainExperience get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的工作经验记录。否则创建一个新的工作经验对象。 */
		if (StringUtils.isNotBlank(id)) {
			return trainExperienceService.get(id);
		} else {
			return new TrainExperience();
		}
	}

	/**
	 * 根据主键，查询到培训经验信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param work
	 *            培训经验entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回培训经验编辑页面
	 */
	// @RequiresPermissions("hr:trainExperience:update")
	@RequestMapping(value = "form")
	public String form(TrainExperience trainExperience, Model model,
			HttpServletRequest request) {
		/* 获取当前用户 */
		trainExperience.setUser(UserUtils.get(trainExperience.getUser().getId()));
		trainExperience.setEmployee(employeeService.get(trainExperience.getUser().getId()));
		model.addAttribute("trainExperience", trainExperience);
		return "modules/hr/trainExperienceForm";
	}
	
	@RequestMapping(value = { "middle" })
	public String Middle(HttpServletRequest request, Model model,
			HttpServletResponse response,Employee employee,
			TrainExperience trainExperience) {
		/* 通过employeeService.findemploy()方法将employ信息进行分页 */
		Page<Employee> page = employeeService.findEdu(new Page<Employee>(
				request, response), employee);
		/* page信息保存到对应的模型中 */
		model.addAttribute("employeepage", page);
		return "modules/hr/trainExperienceMiddle";
	}

	/**
	 * 添加、更新工作经验记录。操作成功后，转至列表页面。
	 * 
	 * @param work
	 *            培训经验entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	// @RequiresPermissions("hr:trainExperience:create")
	@RequestMapping(value = "save")
	public String save(TrainExperience trainExperience,
			HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, trainExperience)) {
			return form(trainExperience, model, request);
		}
//		trainExperience.setUser(UserUtils.getUser());
//		try {
//			String[] str = { "department_leader", "personnel_clerk",
//					"academic_leadership", "studepartment_leadership" };
//			trainExperienceService.save(trainExperience,
//					ActUtils.PD_HR_TRAIN_EXPERIENCE, str, "");
//			addMessage(redirectAttributes, "操作成功，数据已经提交。");
//		} catch (Exception e) {
//			logger.error("启动流程失败：", e);
//			addMessage(redirectAttributes, "系统内部错误！");
//		}
		trainExperience.setUser(UserUtils.get(trainExperience.getUser().getId()));
		trainExperienceService.save(trainExperience);
		addMessage(redirectAttributes, "操作成功，数据已经提交。");
		return "redirect:" + adminPath + "/hr/trainExperience/list?repage";
	}

	/**
	 * 删除培训经验记录。操作成功后，转至列表页面。
	 * 
	 * @param trainExperience
	 *            培训经验entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	// @RequiresPermissions("hr:trainExperience:delete")
	@RequestMapping(value = "delete")
	public String delete(TrainExperience trainExperience,
			RedirectAttributes redirectAttributes) {
		
		trainExperienceService.delete(trainExperience);
		addMessage(redirectAttributes, "删除成功");

		return "redirect:" + adminPath + "/hr/trainExperience/list?repage";
	}

	/**
	 * 查询用户的全部培训经验信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param trainExperience
	 *            培训经验
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部工作经验信息（不分页）
	 */
	// @RequiresPermissions("hr:trainExperience:search")
	@RequestMapping(value = { "list", "" })
	public String list(TrainExperience trainExperience,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
//		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		if (StringUtils.isNotBlank(trainExperience.getUser().getId())) {
			trainExperience.setUser(UserUtils.get(trainExperience.getUser().getId()));
		}
		List<TrainExperience> list = trainExperienceService.findList(trainExperience);
		for (TrainExperience te : list) {
		    if (te.getOffice().getGrade().equals("3")) {
                te.setOffice(UserUtils.getOfficeById(te
                        .getOffice().getParent().getId()));
            }
		    
        }

//		List<ProcessDefinition> pdfList = repositoryService
//				.createProcessDefinitionQuery().latestVersion().active()
//				.orderByProcessDefinitionKey().asc().list();
//		for (ProcessDefinition processDefinition : pdfList) {
//			if (processDefinition.getKey().equals(
//					ActUtils.PD_HR_TRAIN_EXPERIENCE[0])) {
//				model.addAttribute("process", processDefinition);
//			}
//		}

		model.addAttribute("list", list);
		return "modules/hr/trainExperienceList";
	}

	@RequestMapping(value = "detail")
	public String detail(TrainExperience trainExperience,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		model.addAttribute("list",
				trainExperienceService.get(trainExperience));
		return "modules/hr/trainExperienceDetail";
	}

//	@RequestMapping(value = "selfForm")
//	public String selfForm(TrainExperience trainExperience, Model model,
//			HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute(HrConstant.USER);
//		/* 获取当前用户 */
//		trainExperience.setUser(user);
//		model.addAttribute("trainExperience", trainExperience);
//		return "modules/hr/trainExperienceSelfForm";
//	}
//
//	@RequestMapping(value = "selfInputForm")
//	public String selfInputForm(TrainExperience trainExperience, Model model,
//			HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute(HrConstant.USER);
//		/* 获取当前用户 */
//		trainExperience.setUser(user);
//		model.addAttribute("trainExperience", trainExperience);
//		return "modules/hr/trainExperienceSelfInputForm";
//	}
//
//	@RequestMapping(value = "selfList")
//	public String selfList(TrainExperience trainExperience, Model model,
//			HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute(HrConstant.USER);
//		/* 获取当前用户 */
//		trainExperience.setUser(user);
//		model.addAttribute("trainExperience", trainExperience);
//		return "modules/hr/trainExperienceSelfList";
//	}
//
//	@RequestMapping(value = "selfAudit")
//	public String selfAudit(TrainExperience trainExperience, Model model,
//			HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute(HrConstant.USER);
//		/* 获取当前用户 */
//		trainExperience.setUser(user);
//
//		List<TrainExperience> list = trainExperienceService
//				.findList(trainExperience);
//		model.addAttribute("list", list);
//		model.addAttribute("trainExperience", trainExperience);
//		return "modules/hr/trainExperienceSelfAudit";
//	}
//
//	/**
//	 * 工单执行（完成任务）
//	 * 
//	 * @param attendance
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "saveAudit")
//	public String saveAudit(TrainExperience trainExperience, Model model,
//			HttpServletRequest request) {
//		/* 审批 */
//		trainExperienceService.saveAudit(trainExperience);
//		return "redirect:" + adminPath + "/sys/task/todolist";
//	}

}
