package cn.micromoving.bcp.modules.hr.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
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
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.entity.Attendance;
import cn.micromoving.bcp.modules.hr.entity.AuditRecords;
import cn.micromoving.bcp.modules.hr.service.AttendanceService;
import cn.micromoving.bcp.modules.hr.service.AuditRecordsService;
import cn.micromoving.bcp.modules.oa.dao.LeaveDao;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

/**
 * 请假Controller
 * 
 * @author liuj
 * @version 2013-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/leave")
public class LeaveController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected AttendanceService attendanceService;

	@Autowired
	protected SystemService systemService;

	@Autowired
	protected RuntimeService runtimeService;

	@Autowired
	protected TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private AuditRecordsService auditRecordsService;
	@Autowired
	private LeaveDao leaveDao;

	
	@ModelAttribute
	public Attendance get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的教育经历记录。否则创建一个新的教育经历对象。 */
		if (StringUtils.isNotBlank(id)) {
			return attendanceService.get(id);
		} else {
			return new Attendance();
		}

	}

	@RequestMapping(value = { "form" })
	public String form(Attendance attendance, Model model) {
		model.addAttribute("attendance", attendance);
		return "modules/hr/leaveForm";
	}

	/**
	 * 启动请假流程
	 * 
	 * @param attendance
	 */

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Attendance attendance, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		attendance.setUser(UserUtils.getUser());
		attendance.setApplyDate(new Date());
		attendanceService.save(attendance);
//		try {
//			attendanceService.save(attendance);
//			addMessage(redirectAttributes, "流程已启动，流程ID："
//					+ attendance.getAct().getProcInsId());
//		} catch (Exception e) {
//			logger.error("启动请假流程失败：", e);
//			addMessage(redirectAttributes, "系统内部错误！");
//		}
		return "redirect:" + adminPath + "/hr/leave/list";
	}

	/**
	 * 撤销申请
	 */
	@RequestMapping(value = "delete")
	public String delete(Attendance attendance,
			RedirectAttributes redirectAttributes) {

		attendance.setUser(UserUtils.getUser());

		attendanceService.delete(attendance);
		addMessage(redirectAttributes, "流程已删除，流程ID:"
				+ attendance.getAct().getProcInsId());
		return "redirect:" + adminPath + "/hr/leave/list";

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
		// model.addAttribute("leaves", results);
		return "modules/oa/leaveTask";
	}

	/**
	 * 读取所有流程
	 * 
	 * @return
	 */

	@RequestMapping(value = { "list" })
	public String list(Attendance attendance, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		attendance.setUser(UserUtils.getUser());
		Page<Attendance> page = attendanceService.find(new Page<Attendance>(
				request, response), attendance);

		List<ProcessDefinition> list = repositoryService
				.createProcessDefinitionQuery().latestVersion().active()
				.orderByProcessDefinitionKey().asc().list();
		for (ProcessDefinition processDefinition : list) {
			if (processDefinition.getKey().equals(ActUtils.PD_HR_LEAVE[0])) {
				model.addAttribute("process", processDefinition);
			}
		}

		model.addAttribute("page", page);
		return "modules/hr/leaveList";
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

	@RequestMapping(value = { "detail" })
	public String detail(Attendance attendance, AuditRecords auditRecords,
			Model model) {
		
		List<AuditRecords> list = auditRecordsService.findList(auditRecords);
		for (int i = 0;i<list.size();i++) {
			Attendance attendances = new Attendance();
				attendances.setAuditRecords(list.get(i));
				model.addAttribute("attendances"+i, attendances);
		}
		
		Task  task= taskService.createTaskQuery().processInstanceId(attendance.getAct().getProcInsId()).singleResult();
		if(task!=null){
			attendance.getAct().setTaskDefKey(task.getTaskDefinitionKey());
		}
		
		List<ProcessDefinition> listPD = repositoryService
				.createProcessDefinitionQuery().latestVersion().active()
				.orderByProcessDefinitionKey().asc().list();
		for (ProcessDefinition processDefinition : listPD) {
			if (processDefinition.getKey().equals(ActUtils.PD_HR_LEAVE[0])) {
				model.addAttribute("process", processDefinition);
				attendance.getAct().setProcDefId(processDefinition.getId());
			}
		}
		
 		model.addAttribute("attendance", attendance);
		model.addAttribute("route", attendanceService.route(attendance));
		model.addAttribute("photonumber", attendanceService.photonumber(attendanceService.route(attendance)));
		return "modules/hr/leaveDetail";
	}

}
