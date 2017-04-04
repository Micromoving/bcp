package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 培训经历表 Entity
 * 
 * @author luyihao
 * @version 2016-06-01
 */
public class Train extends DataEntity<Train> {

	private static final long serialVersionUID = 1L;

	/* 用户编号 */
	private User user;
	/* 用户编号 */
	private Employee employee;
	/*职称*/
	private ProTechPosition proTechPosition;
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
	/* 结束时间 */
	private Date endDate;
	/* 入学时间字符 */
	private String startDateString;
	/* 毕业时间 字符 */
	private String endDateString;
	/* 培训方式 */
	private String trainingMode;
	/* 有无证书 */
	private String hasCertificate;
	/* 证书编号 */
	private String certificateId;
	/* 培训心得或总结 */
	private String trainingEtc;
	/* 培训费用 */
	private String trainingFunds;
	/* 经费来源 */
	private String fundsOrigin;
	/* 离校时间 */
	private Date leaveDate;
	/* 返校时间 */
	private Date returnDate;
	/* 借款金额 */
	private Double loan;
	/* 借款时间 */
	private Date loanDate;
	/* 报销金额 */
	private Double wipeout;
	/* 报销时间 */
	private Date wipeoutDate;
	/* 报销明细 */
	private String wipeoutDetail;

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

	public String getTrainingEtc() {
		return trainingEtc;
	}

	public void setTrainingEtc(String trainingEtc) {
		this.trainingEtc = trainingEtc;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ProTechPosition getProTechPosition() {
		return proTechPosition;
	}

	public void setProTechPosition(ProTechPosition proTechPosition) {
		this.proTechPosition = proTechPosition;
	}

}
