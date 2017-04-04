package cn.micromoving.bcp.modules.hr.entity;



import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

public class TeacherQualification extends DataEntity<TeacherQualification> {
	private static final long serialVersionUID = 1L;
	/* 教师 */
	private Employee employee;
	/* 用户 */
	private User user;
	/* 部门 */
	private Office office;
	/* 教师资格证种类 */
	private String certificateTypes;
	/* 教师资格证号码 */
	private String certificationNumber;
	/* 任教学科 */
	private String teachingSubjects;
	/* 证书颁发日期 */
	private Date certificateIssueDate;
	/* 颁发机构 */
	private String issuingAgency;
	/* 申请单位 */
	private String applicationUnit;
	/* 审核时间 */
	private Date auditDate;
	/* 备注 */
	private String remark;
	/* 证书颁发日期字符串 */
	private String certificateIssueDateString;
	/* 审核时间字符串 */
	private String auditDateString;

	public String getCertificateIssueDateString() {
		this.certificateIssueDateString = DateUtils.formatDate(
				certificateIssueDate, Global.getConfig("web.dateFormat"));
		return this.certificateIssueDateString;
	}

	public void setCertificateIssueDateString(String certificateIssueDateString) {
		this.certificateIssueDate = StringUtils.toDate(certificateIssueDateString,
				Global.getConfig("web.dateFormat"));
	}
	public String getAuditDateString() {
		this.auditDateString = DateUtils
                .formatDate(auditDate, Global.getConfig("web.dateFormat"));
        return this.auditDateString;
	}

	public void setAuditDateString(String auditDateString) {
		this.auditDate = StringUtils.toDate(auditDateString, Global.getConfig("web.dateFormat"));
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getCertificateTypes() {
		return certificateTypes;
	}

	public void setCertificateTypes(String certificateTypes) {
		this.certificateTypes = certificateTypes;
	}

	public String getCertificationNumber() {
		return certificationNumber;
	}

	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}

	public String getTeachingSubjects() {
		return teachingSubjects;
	}

	public void setTeachingSubjects(String teachingSubjects) {
		this.teachingSubjects = teachingSubjects;
	}

	public Date getCertificateIssueDate() {
		return certificateIssueDate;
	}

	public void setCertificateIssueDate(Date certificateIssueDate) {
		this.certificateIssueDate = certificateIssueDate;
	}

	public String getIssuingAgency() {
		return issuingAgency;
	}

	public void setIssuingAgency(String issuingAgency) {
		this.issuingAgency = issuingAgency;
	}

	public String getApplicationUnit() {
		return applicationUnit;
	}

	public void setApplicationUnit(String applicationUnit) {
		this.applicationUnit = applicationUnit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

}
