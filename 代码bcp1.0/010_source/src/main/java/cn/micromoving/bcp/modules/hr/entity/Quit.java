package cn.micromoving.bcp.modules.hr.entity;

import java.sql.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 离职表Entity
 * @author shihengji
 *
 */
public class Quit extends DataEntity<Assessment>{

	private static final long serialVersionUID = 1L;
	/* 编号 */
	private String id;
	/* 用户编号 */
	private User user;
	/* 部门 */
	private Office office;
	/* 职工编号 */
	private String teacherId;
	/* 申请时间 */
	private Date applicationDate;
	/* 申请内容 */
	private String application;
	/* 离职时间 */
	private Date quitDate;
	/* 离职类型 */
	private String quitType;
	/* 离职去向 */
	private String quitWhere;
	/* 服务期违约 */
	private String breakContract;
	/* 退还培养费金额 */
	private Double returnTheTrainingFee;
	/* 社保补缴金额 */
	private Double socialInsuranceBackpay;
	/* 其它金额 */
	private Double otherFee;
	/* 工资停发时间 */
	private Date  salaryStopDate;
	/* 社会保险停缴时间 */
	private Date socialInsuranceStopDate;
	/* 人事档案转出类型*/
	private String archivesTransferType;
	/* 人事档案转出时间 */
	private Date archivesTransferDate;
	/* 删除标记 */
	private String delFlag;
	
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
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public String getteacherId() {
		return teacherId;
	}
	public void setteacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Date getapplicationDate() {
		return applicationDate;
	}
	public void setapplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getapplication() {
		return application;
	}
	public void setapplication(String application) {
		this.application = application;
	}
	public Date getQuitDate() {
		return quitDate;
	}
	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}
	public String getQuitType() {
		return quitType;
	}
	public void setQuitType(String quitType) {
		this.quitType = quitType;
	}
	public String getQuitWhere() {
		return quitWhere;
	}
	public void setQuitWhere(String quitWhere) {
		this.quitWhere = quitWhere;
	}
	public String getBreakContract() {
		return breakContract;
	}
	public void setBreakContract(String breakContract) {
		this.breakContract = breakContract;
	}
	public Double getReturnTheTrainingFee() {
		return returnTheTrainingFee;
	}
	public void setReturnTheTrainingFee(Double returnTheTrainingFee) {
		this.returnTheTrainingFee = returnTheTrainingFee;
	}
	public Double getSocialInsuranceBackpay() {
		return socialInsuranceBackpay;
	}
	public void setSocialInsuranceBackpay(Double socialInsuranceBackpay) {
		this.socialInsuranceBackpay = socialInsuranceBackpay;
	}
	public Double getOtherFee() {
		return otherFee;
	}
	public void setOtherFee(Double otherFee) {
		this.otherFee = otherFee;
	}
	public Date getSalaryStopDate() {
		return salaryStopDate;
	}
	public void setSalaryStopDate(Date salaryStopDate) {
		this.salaryStopDate = salaryStopDate;
	}
	public Date getSocialInsuranceStopDate() {
		return socialInsuranceStopDate;
	}
	public void setSocialInsuranceStopDate(Date socialInsuranceStopDate) {
		this.socialInsuranceStopDate = socialInsuranceStopDate;
	}
	public String getArchivesTransferType() {
		return archivesTransferType;
	}
	public void setArchivesTransferType(String archivesTransferType) {
		this.archivesTransferType = archivesTransferType;
	}
	public Date getArchivesTransferDate() {
		return archivesTransferDate;
	}
	public void setArchivesTransferDate(Date archivesTransferDate) {
		this.archivesTransferDate = archivesTransferDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
