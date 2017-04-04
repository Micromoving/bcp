package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;

public class PostAppointment extends DataEntity<PostAppointment>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * <code>serialVersionUID</code> 的注释
	 */
	/*教师*/
	private Employee employee;
	/*岗位类别*/
	private String postType;
	/*岗位等级*/
	private String postLevel;
	/*是否兼任其他岗位*/
	private String serveOtherPositions;
	/*兼任岗位类别*/
	private String adjunctPostCategory;
	/*兼任岗位等级*/
	private String adjunctPostRank;
	/*校级职务*/
	private String theDuty;
	/*任职开始年月*/
	private Date startDate;
	/*党政职务*/
	private String partyAndGovernmentPosts;
	/*党政级别*/
	private String partyAndGovernmentLevel;
	/*是否专职从事心理咨询工作*/
	private String psychologicalCounseling;
	/*是否持有心理咨询资格证书*/
	private String psychologicalCertificate;
	/*是否为辅导员*/
	private String whetherCounselor;
	/*部门*/
	private String office;
	/*职务*/
	private String duty;
	/*职级等级*/
	private String professionalLevel;
	/*职务等级*/
	private String dutiesLevel;
	/*新选拔中层干部试用期期限*/
	private Date tryOutDate;
	/*聘任情况*/
	private String engageSituation;
	/*聘任截止时间*/
	private Date endDate;
	/*是否签订聘用合同*/
	private String isSignContract;
	/*备注*/
	private String remarks;
	/*删除标记*/
	private String delFlag;
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public String getPostLevel() {
		return postLevel;
	}
	public void setPostLevel(String postLevel) {
		this.postLevel = postLevel;
	}
	public String getServeOtherPositions() {
		return serveOtherPositions;
	}
	public void setServeOtherPositions(String serveOtherPositions) {
		this.serveOtherPositions = serveOtherPositions;
	}
	public String getAdjunctPostCategory() {
		return adjunctPostCategory;
	}
	public void setAdjunctPostCategory(String adjunctPostCategory) {
		this.adjunctPostCategory = adjunctPostCategory;
	}
	public String getAdjunctPostRank() {
		return adjunctPostRank;
	}
	public void setAdjunctPostRank(String adjunctPostRank) {
		this.adjunctPostRank = adjunctPostRank;
	}
	public String getTheDuty() {
		return theDuty;
	}
	public void setTheDuty(String theDuty) {
		this.theDuty = theDuty;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getPartyAndGovernmentPosts() {
		return partyAndGovernmentPosts;
	}
	public void setPartyAndGovernmentPosts(String partyAndGovernmentPosts) {
		this.partyAndGovernmentPosts = partyAndGovernmentPosts;
	}
	public String getPartyAndGovernmentLevel() {
		return partyAndGovernmentLevel;
	}
	public void setPartyAndGovernmentLevel(String partyAndGovernmentLevel) {
		this.partyAndGovernmentLevel = partyAndGovernmentLevel;
	}
	public String getPsychologicalCounseling() {
		return psychologicalCounseling;
	}
	public void setPsychologicalCounseling(String psychologicalCounseling) {
		this.psychologicalCounseling = psychologicalCounseling;
	}
	public String getPsychologicalCertificate() {
		return psychologicalCertificate;
	}
	public void setPsychologicalCertificte(String psychologicalCertificate) {
		this.psychologicalCertificate = psychologicalCertificate;
	}
	public String getWhetherCounselor() {
		return whetherCounselor;
	}
	public void setWhetherCounselor(String whetherCounselor) {
		this.whetherCounselor = whetherCounselor;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getProfessionalLevel() {
		return professionalLevel;
	}
	public void setProfessionalLevel(String professionalLevel) {
		this.professionalLevel = professionalLevel;
	}
	public String getDutiesLevel() {
		return dutiesLevel;
	}
	public void setDutiesLevel(String dutiesLevel) {
		this.dutiesLevel = dutiesLevel;
	}
	public Date getTryOutDate() {
		return tryOutDate;
	}
	public void setTryOutDate(Date tryOutDate) {
		this.tryOutDate = tryOutDate;
	}
	public String getEngageSituation() {
		return engageSituation;
	}
	public void setEngageSituation(String engageSituation) {
		this.engageSituation = engageSituation;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getIsSignContract() {
		return isSignContract;
	}
	public void setIsSignContract(String isSignContract) {
		this.isSignContract = isSignContract;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	

}
