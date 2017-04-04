package cn.micromoving.bcp.modules.hr.service;

import java.util.ArrayList;
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
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.common.utils.Collections3;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.act.entity.Act;
import cn.micromoving.bcp.modules.act.service.ActTaskService;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.act.utils.ProcessDefCache;
import cn.micromoving.bcp.modules.hr.dao.AttendanceDao;
import cn.micromoving.bcp.modules.hr.dao.AuditRecordsDao;
import cn.micromoving.bcp.modules.hr.entity.Attendance;
import cn.micromoving.bcp.modules.hr.entity.AuditRecords;
import cn.micromoving.bcp.modules.sys.dao.UserDao;
import cn.micromoving.bcp.modules.sys.entity.Role;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Maps;

@Service
@Transactional(readOnly = true)
public class AttendanceService extends CrudService<AttendanceDao, Attendance> {
	@Autowired
	private AttendanceDao attendanceDao;

	@Autowired
	private AuditRecordsDao auditRecordsDao;

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

	/**
	 * 获取流程详细及工作流参数
	 * 
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public Attendance get(String id) {
		Attendance attendance = attendanceDao.get(id);
		Map<String, Object> variables = null;
		HistoricProcessInstance historicProcessInstance = historyService
				.createHistoricProcessInstanceQuery()
				.processInstanceId(attendance.getAct().getProcInsId())
				.singleResult();
		if (historicProcessInstance != null) {
			variables = Collections3.extractToMap(
					historyService.createHistoricVariableInstanceQuery()
							.processInstanceId(historicProcessInstance.getId())
							.list(), "variableName", "value");
		} else {
			variables = runtimeService.getVariables(runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(attendance.getAct().getProcInsId())
					.active().singleResult().getId());
		}
		attendance.getAct().setVars(variables);
		return attendance;
	}

	/**
	 * 启动流程
	 * 
	 * @param entity
	 */
	// @Transactional(readOnly = false)
	// public void save(Leave leave, Map<String, Object> variables) {
	//
	// // 保存业务数据
	// if (StringUtils.isBlank(leave.getId())){
	// leave.preInsert();
	// leaveDao.insert(leave);
	// }else{
	// leave.preUpdate();
	// leaveDao.update(leave);
	// }
	// logger.debug("save entity: {}", leave);
	//
	// // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
	// identityService.setAuthenticatedUserId(leave.getCurrentUser().getLoginName());
	//
	// // 启动流程
	// String businessKey = leave.getId().toString();
	// variables.put("type", "leave");
	// variables.put("busId", businessKey);
	// /**
	// *
	// * @param ActUtils.PD_LEAVE[0] 流程名
	// * @param businessKey 当前请假信息的主键
	// * @param variables 将类型和主键放至变量中
	// *
	// * */
	// ProcessInstance processInstance =
	// runtimeService.startProcessInstanceByKey(ActUtils.PD_LEAVE[0],
	// businessKey, variables);
	// /*将启动的流程实例与leave关联起来*/
	// leave.setProcessInstance(processInstance);
	//
	// // 更新流程实例ID
	// leave.setProcessInstanceId(processInstance.getId());
	// leaveDao.updateProcessInstanceId(leave);
	//
	// logger.debug("start process of {key={}, bkey={}, pid={}, variables={}}",
	// new Object[] {
	// ActUtils.PD_LEAVE[0], businessKey, processInstance.getId(), variables });
	//
	// }
	
	/**
	 * 启动流程
	 * @param procDefKey 流程定义KEY
	 * @param businessTable 业务表表名
	 * @param businessId	业务表编号
	 * @param title			流程标题，显示在待办任务标题
	 * @return 流程实例ID
	 */
	@Transactional(readOnly = false)
	public String startProcess(String procDefKey, String businessTable, String businessId) {
		Map<String, Object> vars = Maps.newHashMap();
		/**
		 * 根据角色ID取到对应的用户信息
		 */
		User user = new User();
		Role role = new Role();
		String[] str = {"department_leader","leave_edu_leader","leave_gov_leader","hr_leader","dean","personnel_record"};
		for(int i = 0;i<str.length;i++){
			StringBuilder sb = new StringBuilder();
			role = systemService.getRoleByEnname(str[i]);
			user.setRole(role);
			for(User temp:userDao.findList(user)){
				sb.append(temp.getLoginName()+",");
			}
			vars.put(str[i], sb.toString());
		}
		/**
		 * 启动流程
		 */
		String procInsId = actTaskService.startProcess(procDefKey, businessTable, businessId, vars);
		/**
		 * 如果是分管领导，自动签收
		 */
		for(Role roles: UserUtils.getUser().getRoleList()){
			if(roles.getEnname().equals("department_leader")){
				vars.put("pass", "1");
				actTaskService.completeFirstTask(procInsId,"","",vars);
			}
		}
		return procInsId;
	}
	
	/**
	 * 启动销假流程
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void reportSave(Attendance attendance) {
		// 启动销假流程
		String procInsId = this.startProcess(ActUtils.PD_HR_LEAVE[0],
				ActUtils.PD_HR_LEAVE[1], attendance.getId());
		attendance.getAct().setProcInsId(procInsId);
		dao.update(attendance);
	}
	
	

	/**
	 * 启动流程
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(Attendance attendance) {
		
		
		
		// 申请发起
		if (StringUtils.isBlank(attendance.getId())) {
			attendance.preInsert();
			dao.insert(attendance);

			// 启动流程
			this.startProcess(ActUtils.PD_HR_LEAVE[0],
					ActUtils.PD_HR_LEAVE[1], attendance.getId());

		}

		// 重新编辑申请
		else {
			
				
				Task task = taskService.createTaskQuery()
						.processInstanceId(attendance.getAct().getProcInsId()).list()
						.get(0);
				
				attendance.getAct().setTaskDefKey(task.getTaskDefinitionKey());
				attendance.getAct().setTaskId(task.getId());
				attendance.getAct().setTaskName(task.getName());
			
			
			attendance.preUpdate();
			dao.update(attendance);
			if (attendance.getAct().getTaskDefKey().equals("usertask1")) {

				attendance.getAct().setComment(
						("yes".equals(attendance.getAct().getFlag()) ? "[重申] "
								: "[销毁] ") + attendance.getAct().getComment());

				// 完成流程任务
				Map<String, Object> vars = Maps.newHashMap();
				vars.put("pass",
						"yes".equals(attendance.getAct().getFlag()) ? "1" : "0");
				actTaskService.complete(attendance.getAct().getTaskId(),
						attendance.getAct().getProcInsId(), attendance.getAct()
								.getComment(), vars);
			}

		}

	}

	/**
	 * 撤销请假申请
	 * @param attendance
	 * @param variables
	 */
	@Transactional(readOnly = false)
	public void delete(Attendance attendance) {
		attendance.preUpdate();
		dao.delete(attendance);
		
		runtimeService.deleteProcessInstance(attendance.getAct().getProcInsId(),"");
		
	}

	/**
	 * 审核审批保存
	 * 
	 * @param attendance
	 */
	@Transactional(readOnly = false)
	public void auditSave(Attendance attendance,int isPass) {

		// 设置意见
		attendance.getAct().setComment(
				("yes".equals(attendance.getAct().getFlag()) ? "[同意] "
						: "[驳回] ") + attendance.getAct().getComment());

		attendance.preUpdate();

		Task task = taskService.createTaskQuery()
				.processInstanceId(attendance.getAct().getProcInsId()).list()
				.get(0);

		attendance.getAct().setTaskDefKey(task.getTaskDefinitionKey());
		attendance.getAct().setTaskId(task.getId());
		attendance.getAct().setTaskName(task.getName());

		AuditRecords ar = new AuditRecords();
		ar.setBusinessKey(ActUtils.PD_HR_LEAVE[0]);
		ar.setTaskKey(attendance.getAct().getTaskDefKey());
		ar.setRecordId(attendance.getId());
		ar.setId(attendance.getId());
		ar.setUpdateBy(UserUtils.getUser());
		ar.setUpdateDate(new Date());

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = attendance.getAct().getTaskDefKey();
		// 审核环节
		if ("leaveaudit1".equals(taskDefKey)) {
			if(isPass == 3){
				attendance.getAct().setFlag("complete");
			}
			
		}else if ("leaveaudit2".equals(taskDefKey)) {
			if(isPass == 5){
				attendance.getAct().setFlag("complete");
			}
			
		}else if ("leaveaudit3".equals(taskDefKey)) {
			if(isPass == 6){
				attendance.getAct().setFlag("complete");
			}
			
		}else if ("leaveaudit4".equals(taskDefKey)) {
			
		}else if ("leaveaudit5".equals(taskDefKey)) {
			attendance.setLeaveStatus("1");
		}
		// 未知环节，直接返回
		else {
			return;
		}
		ar.setAuditOpinion(attendance.getAct().getComment());
		attendanceDao.update(attendance);
		ar.preInsert();
		auditRecordsDao.insert(ar);

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();

		if ("yes".equals(attendance.getAct().getFlag())) {
			vars.put("pass", "1");
		} else if ("no".equals(attendance.getAct().getFlag())) {
			vars.put("pass", "0");
		} else if ("complete".equals(attendance.getAct().getFlag())) {
			vars.put("pass", "2");
		}
		// vars.put("pass", "yes".equals(attendance.getAct().getFlag()) ? "1"
		// : "0");
		actTaskService.complete(attendance.getAct().getTaskId(), attendance
				.getAct().getProcInsId(), attendance.getAct().getComment(),
				vars);

	}

	/**
	 * 获取待办列表
	 * 
	 * @param procDefKey
	 *            流程定义标识
	 * @return
	 */
	public List<Act> todoList(Act act) {
		String userId = UserUtils.getUser().getLoginName();// ObjectUtils.toString(UserUtils.getUser().getId());

		List<Act> result = new ArrayList<Act>();

		// =============== 已经签收的任务 ===============
		TaskQuery todoTaskQuery = taskService.createTaskQuery()
				.taskAssignee(userId).active().includeProcessVariables()
				.orderByTaskCreateTime().desc();

		// 设置查询条件
		if (StringUtils.isNotBlank(act.getProcDefKey())) {
			todoTaskQuery.processDefinitionKey(act.getProcDefKey());
		}
		if (act.getBeginDate() != null) {
			todoTaskQuery.taskCreatedAfter(act.getBeginDate());
		}
		if (act.getEndDate() != null) {
			todoTaskQuery.taskCreatedBefore(act.getEndDate());
		}

		// 查询列表
		List<Task> todoList = todoTaskQuery.list();
		for (Task task : todoList) {
			Act e = new Act();
			e.setTask(task);
			e.setVars(task.getProcessVariables());
			// e.setTaskVars(task.getTaskLocalVariables());
			// System.out.println(task.getId()+"  =  "+task.getProcessVariables()
			// + "  ========== " + task.getTaskLocalVariables());
			e.setProcDef(ProcessDefCache.get(task.getProcessDefinitionId()));
			// e.setProcIns(runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult());
			// e.setProcExecUrl(ActUtils.getProcExeUrl(task.getProcessDefinitionId()));
			e.setStatus("todo");
			result.add(e);
		}

		// =============== 等待签收的任务 ===============
		TaskQuery toClaimQuery = taskService.createTaskQuery()
				.taskCandidateUser(userId).includeProcessVariables().active()
				.orderByTaskCreateTime().desc();

		// 设置查询条件
		if (StringUtils.isNotBlank(act.getProcDefKey())) {
			toClaimQuery.processDefinitionKey(act.getProcDefKey());
		}
		if (act.getBeginDate() != null) {
			toClaimQuery.taskCreatedAfter(act.getBeginDate());
		}
		if (act.getEndDate() != null) {
			toClaimQuery.taskCreatedBefore(act.getEndDate());
		}

		// 查询列表
		List<Task> toClaimList = toClaimQuery.list();
		for (Task task : toClaimList) {
			Act e = new Act();
			e.setTask(task);
			e.setVars(task.getProcessVariables());
			// e.setTaskVars(task.getTaskLocalVariables());
			// System.out.println(task.getId()+"  =  "+task.getProcessVariables()
			// + "  ========== " + task.getTaskLocalVariables());
			e.setProcDef(ProcessDefCache.get(task.getProcessDefinitionId()));
			// e.setProcIns(runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult());
			// e.setProcExecUrl(ActUtils.getProcExeUrl(task.getProcessDefinitionId()));
			e.setStatus("claim");
			result.add(e);
		}
		return result;
	}

	// /**
	// * 查询待办任务
	// *
	// * @param userId
	// * 用户ID
	// * @return
	// */
	// public List<Attendance> findTodoTasks(String userId) {
	//
	// List<Attendance> results = new ArrayList<Attendance>();
	// List<Task> tasks = new ArrayList<Task>();
	// // 根据当前人的ID查询代办任务
	// List<Task> todoList = taskService.createTaskQuery()
	// .processDefinitionKey(ActUtils.PD_LEAVE_01[0])
	// .taskAssignee(userId).active().orderByTaskPriority().desc()
	// .orderByTaskCreateTime().desc().list();
	// // 根据当前人未签收的任务
	// List<Task> unsignedTasks = taskService.createTaskQuery()
	// .processDefinitionKey(ActUtils.PD_LEAVE_01[0])
	// .taskCandidateGroup(userId).active().orderByTaskPriority()
	// .desc().orderByTaskCreateTime().desc().list();
	// // 合并
	// tasks.addAll(todoList);
	// tasks.addAll(unsignedTasks);
	// // 根据流程的业务ID查询实体并关联
	// for (Task task : tasks) {
	// String processInstanceId = task.getProcessInstanceId();
	// ProcessInstance processInstance = runtimeService
	// .createProcessInstanceQuery()
	// .processInstanceId(processInstanceId).active()
	// .singleResult();
	// // String businessKey = processInstance.getBusinessKey();
	// // Attendance attendance = attendanceDao.get();
	// Attendance attendance = new Attendance();
	// attendance.getAct().setProcInsId(processInstanceId);
	// if (processInstance.getActivityId().equals("leaveaudit1")
	// || processInstance.getActivityId().equals("leaveaudit2")) {
	// attendance.getSqlMap().put("dsf",
	// dataScopeFilter(UserUtils.getUser(), "o", "a"));
	// }
	// attendance = attendanceDao.getByProcInsId(attendance);
	// if (attendance != null) {
	//
	// attendance.getAct().setTask(task);
	// attendance.getAct().setProcIns(processInstance);
	// attendance.getAct().setProcDef(
	// repositoryService
	// .createProcessDefinitionQuery()
	// .processDefinitionId(
	// (processInstance
	// .getProcessDefinitionId()))
	// .singleResult());
	// results.add(attendance);
	// }
	// }
	//
	// return results;
	// }

	public Page<Attendance> find(Page<Attendance> page, Attendance attendance) {

		attendance.setPage(page);
		page.setList(attendanceDao.findList(attendance));
		for (Attendance data : page.getList()) {
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
	
	public String[] route(Attendance attendance) {
		String[] process = new String[8];
		int days = (int) -((attendance.getStartDate().getTime() - attendance
				.getEndDate().getTime()) / (1000 * 60 * 60 * 24));
		String type = attendance.getLeaveType();
		process[0] = "提交申请";
		process[1] = "部门负责人审批";

		// 如果请假类型是5婚假、2病假，而且请假时间小于等于一天。
		if (("4".equals(type) || "2".equals(type)) && days <= 1) {
			process[2] = "成功";
		}

		// 如果请假类型是5婚假或2病假，而且时间小于等于三天。
		if (("4".equals(type) || "2".equals(type)) && days <= 3) {
			process[2] = "分管院领导审批";
			process[3] = "人事处备案";
			process[4] = "成功";
		}

		// 如果请假类型是5婚假或2病假，而且时间大于三天，或者是3事假，而且请假事假小于等于五天。
		if (((("4".equals(type) || "2".equals(type)) && days > 3))
				|| ("3".equals(type) && days <= 5)) {
			process[2] = "分管院领导审批";
			process[3] = "人事主管审批";
			process[4] = "人事处备案";
			process[5] = "成功";
		}

		// 如果请假类型为事假，而且请假时间大于五天。
		if ("3".equals(type) && days > 5) {
			process[2] = "分管院领导审批";
			process[3] = "人事主管审批";
			process[4] = "院长审批";
			process[5] = "人事处备案";
			process[6] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成功";
		}
		return process;
	}
	
	public int photonumber(String[] process){
		int sss=0;
		for(int i=0;i<process.length;i++) {
			if(process[i] != null) {
				sss++;
			}
		}
		return sss;
	}
}
