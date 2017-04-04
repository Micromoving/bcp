package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import org.activiti.engine.history.HistoricProcessInstance;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.ActEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.hr.entity.AuditRecords;

/**
 * 考勤表Entity
 * 
 * @author shihengji
 * 
 */
public class Attendance extends ActEntity<Attendance> {
	private static final long serialVersionUID = 1L;

	/* 用户编号 */
	private User user;
	/* 部门 */
	private Office office;
	/* 职员编号 */
	private String employeeId;
	/* 申请时间 */
	private Date applyDate;
	/* 请假开始时间 */
	private Date startDate;
	/* 请假结束时间 */
	private Date endDate;
	/* 请假类型 */
	private String leaveType;
	/* 请假原因 */
	private String leaveReason;
	/* 请假状态 */
	private String leaveStatus;
	/* 销假时间 */
	private Date reportDate;
	/* 请假实际工作日*/
	private double leaveDays;
	/*销假人*/
	private String reportPeople;
	/* 申请时间字符 */
	private String applyDateString;
	/* 请假开始时间字符 */
	private String startDateString;
	/* 请假结束时间字符 */
	private String endDateString;
	/* 销假时间字符 */
	private String reportDateString;
//	/* 流程实例编号 */
//	private String processInstanceId;
	/* 审核记录 */
	private AuditRecords auditRecords;
	

	// -- 临时属性 --//
	// 流程任务
//	private Task task;
//	private Map<String, Object> variables;
//	// 运行中的流程实例
//	private ProcessInstance processInstance;
	// 历史的流程实例
	private HistoricProcessInstance historicProcessInstance;
//	// 流程定义
//	private ProcessDefinition processDefinition;
	public double getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(double leaveDays) {
		this.leaveDays = leaveDays;
	}

	public AuditRecords getAuditRecords() {
		return auditRecords;
	}

	public void setAuditRecords(AuditRecords auditRecords) {
		this.auditRecords = auditRecords;
	}

//	public String getProcessInstanceId() {
//		return processInstanceId;
//	}
//
//	public void setProcessInstanceId(String processInstanceId) {
//		this.processInstanceId = processInstanceId;
//	}
//
//	public Task getTask() {
//		return task;
//	}
//
//	public void setTask(Task task) {
//		this.task = task;
//	}
//
//	public Map<String, Object> getVariables() {
//		return variables;
//	}
//
//	public void setVariables(Map<String, Object> variables) {
//		this.variables = variables;
//	}
//
//	public ProcessInstance getProcessInstance() {
//		return processInstance;
//	}
//
//	public void setProcessInstance(ProcessInstance processInstance) {
//		this.processInstance = processInstance;
//	}
//
	public HistoricProcessInstance getHistoricProcessInstance() {
		return historicProcessInstance;
	}

	public void setHistoricProcessInstance(
			HistoricProcessInstance historicProcessInstance) {
		this.historicProcessInstance = historicProcessInstance;
	}
//
//	public ProcessDefinition getProcessDefinition() {
//		return processDefinition;
//	}
//
//	public void setProcessDefinition(ProcessDefinition processDefinition) {
//		this.processDefinition = processDefinition;
//	}

	public Attendance() {
		super();
		office = new Office();
		user = new User();
	}

	public Attendance(String id) {
		super(id);
	}
	
	@ExcelField(title = "姓名", type = 0, align = 2, sort = 30)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ExcelField(title = "部门", type = 0, align = 2, sort = 20)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@ExcelField(title = "请假开始时间", type = 0, align = 2, sort = 50)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@ExcelField(title = "请假结束时间", type = 0, align = 2, sort = 60)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	@ExcelField(title = "请假原因", type = 0, align = 2, sort = 40)
	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	@ExcelField(title = "请假状态", type = 0, align = 2, sort = 70)
	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	@ExcelField(title = "销假时间", type = 0, align = 2, sort = 80)
	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyDateString() {
		return applyDateString;
	}

	public void setApplyDateString(String applyDateString) {
		this.applyDateString = applyDateString;
	}

	public String getStartDateString() {
		this.startDateString = DateUtils.formatDate(startDate,
				Global.getConfig("web.dateFormatCHN"));
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDate = StringUtils.toDate(startDateString,
				Global.getConfig("web.dateFormatCHN"));
	}

	public String getEndDateString() {
		this.endDateString = DateUtils.formatDate(endDate,
				Global.getConfig("web.dateFormatCHN"));
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDate = StringUtils.toDate(endDateString,
				Global.getConfig("web.dateFormatCHN"));
	}

	public String getReportDateString() {
		return reportDateString;
	}

	public void setReportDateString(String reportDateString) {
		this.reportDateString = reportDateString;
	}

	public String getReportPeople() {
		return reportPeople;
	}

	public void setReportPeople(String reportPeople) {
		this.reportPeople = reportPeople;
	}

}
