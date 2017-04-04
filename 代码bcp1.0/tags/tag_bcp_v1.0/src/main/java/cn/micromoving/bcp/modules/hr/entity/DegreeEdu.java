package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;
import java.util.List;

import cn.micromoving.bcp.common.persistence.ActEntity;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 学历教育表Entity
 * 
 * @author luyihao
 * @version 2016-06-01
 */
public class DegreeEdu extends ActEntity<DegreeEdu> {
	private static final long serialVersionUID = 1L;
	/* 用户编号 */
	private User user;
	/* 部门 */
	private Office office;
	/* 职员 */
	private Employee employee;
	/* 攻读学历 */
	private String diploma;
	/* 攻读学位 */
	private String degree;
	/* 攻读方式 */
	private String eduMode;
	/* 攻读类型 */
	private String eduType;
	/* 是否脱产 */
	private String isOffjob;
	/* 协议签订情况 */
	private String signAgreement;
	/* 攻读最短年限 */
	private String ageLimit;
	/* 攻读院校 */
	private String school;
	/* 攻读专业 */
	private String major;
	/* 攻读方向 */
	private String researchArea;
	/* 攻读起始时间 */
	private Date startDate;
	/* 攻读终止时间 */
	private Date endDate;
	/* 报考是否盖章 */
	private String isConfirm;
	/* 学费 */
	private Double tuition;
	/* 借款金额 */
	private Double loan;
	/* 借款时间 */
	private Date loanDate;
	/* 报销金额 */
	private Double wipeout;
	/* 报销时间 */
	private Date wipeoutDate;
	/* 启动读研（博）资金 */
	private String hasSalary;
	/* 恢复工资时间 */
	private Date recoverSalaryDate;
	/* 是否延期 */
	private String isDelay;
	/* 取得证书时间 */
	private Date getCertificateDate;
	/* 毕业证编号 */
	private String diplomaNum;
	/* 学位证编号 */
	private String degreeNum;
	/* 备注（特殊情况标注） */
	private String comments;
	private String processInstanceId;
	/* 数据状态 */
	private String dataState;

	/* 职称情况 */
	private ProTechPosition protp;

	private List<String> degreeIdList;
	private List<String> flagList;

	public ProTechPosition getProtp() {
		return protp;
	}

	public void setProtp(ProTechPosition protp) {
		this.protp = protp;
	}

	public List<String> getFlagList() {
		return flagList;
	}

	public void setFlagList(List<String> flagList) {
		this.flagList = flagList;
	}

	public List<String> getDegreeIdList() {
		return degreeIdList;
	}

	public void setDegreeIdList(List<String> degreeIdList) {
		this.degreeIdList = degreeIdList;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIsOffjob() {
		return isOffjob;
	}

	public void setIsOffjob(String isOffjob) {
		this.isOffjob = isOffjob;
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

	public void setGetCertificateDate(Date getCertificateDate) {
		this.getCertificateDate = getCertificateDate;
	}

	public String getDiplomaNum() {
		return diplomaNum;
	}

	public void setDiplomaNum(String diplomaNum) {
		this.diplomaNum = diplomaNum;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSignAgreement() {
		return signAgreement;
	}

	public void setSignAgreement(String signAgreement) {
		this.signAgreement = signAgreement;
	}

	public String getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getResearchArea() {
		return researchArea;
	}

	public void setResearchArea(String researchArea) {
		this.researchArea = researchArea;
	}

	public String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(String isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getEduMode() {
		return eduMode;
	}

	public void setEduMode(String eduMode) {
		this.eduMode = eduMode;
	}

	public String getEduType() {
		return eduType;
	}

	public void setEduType(String eduType) {
		this.eduType = eduType;
	}

	public Double getTuition() {
		return tuition;
	}

	public void setTuition(Double tuition) {
		this.tuition = tuition;
	}

	public Double getLoan() {
		return loan;
	}

	public void setLoan(Double loan) {
		this.loan = loan;
	}

	public Double getWipeout() {
		return wipeout;
	}

	public void setWipeout(Double wipeout) {
		this.wipeout = wipeout;
	}

	public String getHasSalary() {
		return hasSalary;
	}

	public void setHasSalary(String hasSalary) {
		this.hasSalary = hasSalary;
	}

	public Date getRecoverSalaryDate() {
		return recoverSalaryDate;
	}

	public void setRecoverSalaryDate(Date recoverSalaryDate) {
		this.recoverSalaryDate = recoverSalaryDate;
	}

	public String getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}

	public String getDegreeNum() {
		return degreeNum;
	}

	public void setDegreeNum(String degreeNum) {
		this.degreeNum = degreeNum;
	}

	public Date getGetCertificateDate() {
		return getCertificateDate;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}
}
