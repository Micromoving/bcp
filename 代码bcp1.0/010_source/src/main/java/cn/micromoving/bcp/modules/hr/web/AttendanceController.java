package cn.micromoving.bcp.modules.hr.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.ExportExcel;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.act.service.ActTaskService;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.entity.AnnualSalaryPersonnel;
import cn.micromoving.bcp.modules.hr.entity.Attendance;
import cn.micromoving.bcp.modules.hr.entity.DegreeEdu;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.service.AttendanceService;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Maps;

/**
 * 请假Controller
 * 
 * @author liuj
 * @version 2013-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/attendance")
public class AttendanceController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected AttendanceService attendanceService;

	@Autowired
	protected RuntimeService runtimeService;

	@Autowired
	protected TaskService taskService;

	@Autowired
	protected ActTaskService actTaskService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SalEmpViewDao salEmpViewDao;

	@ModelAttribute
	public Attendance get(@RequestParam(required = false) String id) {

		if (StringUtils.isNotBlank(id)) {
			return attendanceService.get(id);
		} else {
			return new Attendance();
		}

	}

	/**
	 * 撤销申请
	 */
	@RequestMapping(value = "delete")
	public String delete(Attendance attendance,
			RedirectAttributes redirectAttributes) {

		attendanceService.delete(attendance);
		return "redirect:" + adminPath + "/hr/attendance/list";
	}

	/**
	 * 保存考勤管理信息
	 * 
	 * @param attendance
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Attendance attendance, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		attendance.setUser(UserUtils.get(attendance.getUser().getId()));
		attendance.setApplyDate(new Date());
		attendanceService.save(attendance);
		attendance = attendanceService.get(attendance);
		return "redirect:" + adminPath + "/hr/attendance/list";

	}

	@RequestMapping(value = { "form" })
	public String form(Attendance attendance, Model model) {
		SalEmpView salTemp = new SalEmpView();
		salTemp = salEmpViewDao.get(attendance.getUser().getId());
		attendance.setSalEmpView(salTemp);
		model.addAttribute("attendance", attendance);
		return "modules/hr/attendanceForm";
	}

	@RequestMapping(value = { "middle" })
	public String Middle(HttpServletRequest request, Model model,
			HttpServletResponse response, Employee employee,
			Attendance attendance) {
		if (StringUtils.isNotBlank(attendance.getUser().getName())) {
			employee.setName(attendance.getUser().getName());
		}
		/* 通过employeeService.findemploy()方法将employ信息进行分页 */
		Page<Employee> page = employeeService.findEmployee(new Page<Employee>(
				request, response), employee);
		SalEmpView salEmpView = salEmpViewDao.get(attendance.getUser().getId());
		attendance.setSalEmpView(salEmpView);
		/* page信息保存到对应的模型中 */
		model.addAttribute("employeepage", page);
		return "modules/hr/attendanceMiddle";
	}

	/**
	 * 工单执行（完成任务）
	 * 
	 * @param attendance
	 * @param model
	 * @return
	 */
	// @RequestMapping(value = "saveAudit")
	// public String saveAudit(Attendance attendance, Model model) {
	// if (!attendance.getAct().getTaskDefKey().equals("leaveaudit5")) {
	// if (StringUtils.isBlank(attendance.getAct().getFlag())
	// || StringUtils.isBlank(attendance.getAct().getComment())) {
	// addMessage(model, "请填写审核意见。");
	// return form(attendance, model);
	// }
	// }
	// int isPass = attendanceService.photonumber(attendanceService
	// .route(attendance));
	// attendanceService.auditSave(attendance, isPass);
	// return "redirect:" + adminPath + "/sys/task/todolist";
	// }

	/**
	 * 销假
	 * 
	 * @param attendance
	 */
	@RequestMapping(value = "reportSave")
	public String reportSave(Attendance attendance, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		attendance.setReportPeople(UserUtils.getUser().getName());
		attendance.setReportDate(new Date());
		try {
			attendanceService.reportSave(attendance);
			addMessage(redirectAttributes, "销假流程已启动，流程ID："
					+ attendance.getAct().getProcInsId());
		} catch (Exception e) {
			logger.error("启动销假流程失败：", e);
			addMessage(redirectAttributes, "系统内部错误！");
		}
		return "redirect:" + adminPath + "/hr/attendance/list";
	}

	/**
	 * 任务列表
	 * 
	 * @param attendance
	 */

	@RequestMapping(value = { "list/task", "" })
	public String taskList(HttpSession session, Model model) {
		String userId = UserUtils.getUser().getLoginName();// ObjectUtils.toString(UserUtils.getUser().getId());
		// List<Attendance> results = attendanceService.findTodoTasks(userId);
		// model.addAttribute("attendances", results);
		return "modules/hr/attendanceTask";
	}

	@RequestMapping(value = "audit")
	public String audit(Attendance attendance, HttpServletRequest request,
			Model model, RedirectAttributes redirectAttributes) {
		/* 调用save保存获取到的Employ值 */
		attendanceService.save(attendance);
		/* 重定向至list页面 */
		return "modules/hr/attendanceAudit";
	}

	/**
	 * 读取所有流程
	 * 
	 * @return
	 */

	@RequestMapping(value = { "list" })
	public String list(Attendance attendance, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (StringUtils.isNotBlank(attendance.getUser().getId())) {
			attendance.setUser(UserUtils.get(attendance.getUser().getId()));
		}
		Page<Attendance> page = attendanceService.find(new Page<Attendance>(
				request, response), attendance);
		for (Attendance a : page.getList()) {
		    if (a.getOffice().getGrade().equals("3")) {
                a.setOffice(UserUtils.getOfficeById(a
                        .getOffice().getParent().getId()));
            }
        }
		model.addAttribute("page", page);
		return "modules/hr/attendanceList";
	}

	/**
	 * 读取详细数据
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	public String getAttendance(@PathVariable("id") String id) {
		Attendance attendance = attendanceService.get(id);
		return JsonMapper.getInstance().toJson(attendance);
	}

	/**
	 * 读取详细数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "detail-with-vars/{id}/{taskId}")
	@ResponseBody
	public String getAttendanceWithVars(@PathVariable("id") String id,
			@PathVariable("taskId") String taskId) {
		Attendance attendance = attendanceService.get(id);
		Map<String, Object> variables = taskService.getVariables(taskId);
		attendance.getAct().setVars(variables);
		return JsonMapper.getInstance().toJson(attendance);
	}

	/**
	 * 导出请假记录
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "export")
	public String exportFile(Attendance attendance, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "请假记录" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			Page<Attendance> page = new Page<Attendance>();
			page = attendanceService.find(page, attendance);
			new ExportExcel("请假记录", Attendance.class)
					.setDataList(page.getList()).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出请假记录失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/hr/attendance/list?repage";
	}

}
