package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.ActEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 培训经历表 Entity
 * 
 * @author luyihao
 * @version 2016-06-01
 */
public class TrainExperience extends ActEntity<TrainExperience> {

	private static final long serialVersionUID = 1L;
	/* 用户编号 */
	private User user;
	private Employee employee;
	/* 部门 */
	private Office office;
	/* 培训类型 */
	private String trainingType;
	/* 培训周期 */
	private String trainingCycle;
	/* 国内外 */
	private String isHome;
	/* 培训机构 */
	private String trainingAgency;
	/* 培训地点 */
	private String trainingPlace;
	/* 培训课程 */
	private String trainingCourse;
	/* 培训内容 */
	private String trainingContent;
	/* 起始时间 */
	private Date startDate;
	private String startDateString;
	/* 结束时间 */
	private Date endDate;
	private String endDateString;
	/* 培训方式 */
	private String trainingMode;
	/* 有无证书 */
	private String hasCertificate;
	/* 证书编号 */
	private String certificateId;
	/* 培训心得或总结 */
	private Double trainingEtc;
	/* 培训费用 */
	private String trainingFunds;
	/* 经费来源 */
	private String fundsOrigin;
	/* 离校时间 */
	private Date leaveDate;
	private String leaveDateString;
	/* 返校时间 */
	private Date returnDate;
	private String returnDateString;
	/* 借款金额 */
	private Double loan;
	/* 借款时间 */
	private Date loanDate;
	private String loanDateString;
	/* 报销金额 */
	private Double wipeout;
	/* 报销时间 */
	private Date wipeoutDate;
	private String wipeoutDateString;
	/* 报销明细 */
	private String wipeoutDetail;
	private String processInstanceId;
	/* 数据状态 */
	private String dataState;

	/* 职称情况 */
	private ProTechPosition protp;
	private SalEmpView salEmpView;
	public TrainExperience() {
		super();
		office = new Office();
		user = new User();
	}

	public SalEmpView getSalEmpView() {
		return salEmpView;
	}

	public void setSalEmpView(SalEmpView salEmpView) {
		this.salEmpView = salEmpView;
	}

	public String getStartDateString() {
		 this.startDateString = DateUtils.formatDate(startDate, Global.getConfig("web.dateFormat"));
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDate =StringUtils.toDate(startDateString, Global.getConfig("web.dateFormat"));
	}

	public String getEndDateString() {
		 this.endDateString = DateUtils.formatDate(endDate, Global.getConfig("web.dateFormat"));
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDate = StringUtils.toDate(endDateString, Global.getConfig("web.dateFormat"));
	}

	public String getLeaveDateString() {
		 this.leaveDateString = DateUtils.formatDate(leaveDate, Global.getConfig("web.dateFormat"));
		return leaveDateString;
	}

	public void setLeaveDateString(String leaveDateString) {
		this.leaveDate = StringUtils.toDate(leaveDateString, Global.getConfig("web.dateFormat"));
	}

	public String getReturnDateString() {
		 this.returnDateString = DateUtils.formatDate(returnDate, Global.getConfig("web.dateFormat"));
		return returnDateString;
	}

	public void setReturnDateString(String returnDateString) {
		this.returnDate = StringUtils.toDate(returnDateString, Global.getConfig("web.dateFormat"));
	}

	public String getLoanDateString() {
		 this.loanDateString = DateUtils.formatDate(loanDate, Global.getConfig("web.dateFormat"));
		return loanDateString;
	}

	public void setLoanDateString(String loanDateString) {
		this.loanDate = StringUtils.toDate(loanDateString, Global.getConfig("web.dateFormat"));
	}

	public String getWipeoutDateString() {
		 this.wipeoutDateString = DateUtils.formatDate(wipeoutDate, Global.getConfig("web.dateFormat"));
		return wipeoutDateString;
	}

	public void setWipeoutDateString(String wipeoutDateString) {
		this.wipeoutDate = StringUtils.toDate(wipeoutDateString, Global.getConfig("web.dateFormat"));
	}

	public ProTechPosition getProtp() {
		return protp;
	}

	public void setProtp(ProTechPosition protp) {
		this.protp = protp;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}

	public String getTrainingAgency() {
		return trainingAgency;
	}

	public void setTrainingAgency(String trainingAgency) {
		this.trainingAgency = trainingAgency;
	}

	public String getTrainingPlace() {
		return trainingPlace;
	}

	public void setTrainingPlace(String trainingPlace) {
		this.trainingPlace = trainingPlace;
	}

	public String getTrainingCourse() {
		return trainingCourse;
	}

	public void setTrainingCourse(String trainingCourse) {
		this.trainingCourse = trainingCourse;
	}

	public String getTrainingContent() {
		return trainingContent;
	}

	public void setTrainingContent(String trainingContent) {
		this.trainingContent = trainingContent;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getWipeoutDate() {
		return wipeoutDate;
	}

	public void setWipeoutDate(Date wipeoutDate) {
		this.wipeoutDate = wipeoutDate;
	}

	public String getWipeoutDetail() {
		return wipeoutDetail;
	}

	public void setWipeoutDetail(String wipeoutDetail) {
		this.wipeoutDetail = wipeoutDetail;
	}

	public String getTrainingCycle() {
		return trainingCycle;
	}

	public void setTrainingCycle(String trainingCycle) {
		this.trainingCycle = trainingCycle;
	}

	public String getIsHome() {
		return isHome;
	}

	public void setIsHome(String isHome) {
		this.isHome = isHome;
	}

	public String getTrainingMode() {
		return trainingMode;
	}

	public void setTrainingMode(String trainingMode) {
		this.trainingMode = trainingMode;
	}

	public String getHasCertificate() {
		return hasCertificate;
	}

	public void setHasCertificate(String hasCertificate) {
		this.hasCertificate = hasCertificate;
	}

	public String getTrainingFunds() {
		return trainingFunds;
	}

	public void setTrainingFunds(String trainingFunds) {
		this.trainingFunds = trainingFunds;
	}

	public String getFundsOrigin() {
		return fundsOrigin;
	}

	public void setFundsOrigin(String fundsOrigin) {
		this.fundsOrigin = fundsOrigin;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Double getWipeout() {
		return wipeout;
	}

	public void setWipeout(Double wipeout) {
		this.wipeout = wipeout;
	}

	public Double getTrainingEtc() {
		return trainingEtc;
	}

	public void setTrainingEtc(Double trainingEtc) {
		this.trainingEtc = trainingEtc;
	}

	public Double getLoan() {
		return loan;
	}

	public void setLoan(Double loan) {
		this.loan = loan;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

}
