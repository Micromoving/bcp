package cn.micromoving.bcp.modules.sr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.User;


/**
 * 科研管理表Entity
 * 
 * @author lirui
 * @version 2016-07-15
 */
public class Project  extends DataEntity<Project> {

	private static final long serialVersionUID = 1L;
	/* 项目负责人 */
	private User user;
	/* 项目编号 */
	private String projectId;
	/*项目名称*/
	private String projectName;
	/*项目来源*/
	private String projectSource;
	/*研究类别*/
	private String researchType;
	/*学科门类*/
	private String subjectCategory;
	/*合作形式*/
	private String cooperationForm;
	/*申报批准时间*/
	private Date declareApprovalDate;
	/*批准号*/
	private String  approvalNo;
	/*申请经费*/
	private Double declareFunds;
	/*批准经费*/
	private Double approvalFunds;
	/*追加经费*/
	private Double appendFunds;
	/*预留保证金*/
	private Double beforehandDeposit;
	/*计划完成时间*/
	private Double planFinishDate;
	/*预期成果形式*/
	private String expectationAchievement;
	/*实际完成时间*/
	private Date actualFinishDate;
	/*最终成果形式*/
	private String actualAchievement;
	/*中检与否*/
	private String isMidTermInspection;
	/*项目状态*/
	private String projectStatus;
	/*社会经济目标*/
	private String socialEconomicGoal ;
	/*服务的国民经济行业*/
	private String  nationalEconomyTrade ;
	/*分值*/
	private Double score;

	private Date createDate;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectSource() {
		return projectSource;
	}
	public void setProjectSource(String projectSource) {
		this.projectSource = projectSource;
	}
	public String getResearchType() {
		return researchType;
	}
	public void setResearchType(String researchType) {
		this.researchType = researchType;
	}
	public String getSubjectCategory() {
		return subjectCategory;
	}
	public void setSubjectCategory(String subjectCategory) {
		this.subjectCategory = subjectCategory;
	}
	public String getCooperationForm() {
		return cooperationForm;
	}
	public void setCooperationForm(String cooperationForm) {
		this.cooperationForm = cooperationForm;
	}
	public Date getDeclareApprovalDate() {
		return declareApprovalDate;
	}
	public void setDeclareApprovalDate(Date declareApprovalDate) {
		this.declareApprovalDate = declareApprovalDate;
	}
	public String getApprovalNo() {
		return approvalNo;
	}
	public void setApprovalNo(String approvalNo) {
		this.approvalNo = approvalNo;
	}
	public Double getDeclareFunds() {
		return declareFunds;
	}
	public void setDeclareFunds(Double declareFunds) {
		this.declareFunds = declareFunds;
	}
	public Double getApprovalFunds() {
		return approvalFunds;
	}
	public void setApprovalFunds(Double approvalFunds) {
		this.approvalFunds = approvalFunds;
	}
	public Double getAppendFunds() {
		return appendFunds;
	}
	public void setAppendFunds(Double appendFunds) {
		this.appendFunds = appendFunds;
	}
	public Double getBeforehandDeposit() {
		return beforehandDeposit;
	}
	public void setBeforehandDeposit(Double beforehandDeposit) {
		this.beforehandDeposit = beforehandDeposit;
	}
	public Double getPlanFinishDate() {
		return planFinishDate;
	}
	public void setPlanFinishDate(Double planFinishDate) {
		this.planFinishDate = planFinishDate;
	}
	public String getExpectationAchievement() {
		return expectationAchievement;
	}
	public void setExpectationAchievement(String expectationAchievement) {
		this.expectationAchievement = expectationAchievement;
	}
	public Date getActualFinishDate() {
		return actualFinishDate;
	}
	public void setActualFinishDate(Date actualFinishDate) {
		this.actualFinishDate = actualFinishDate;
	}
	public String getActualAchievement() {
		return actualAchievement;
	}
	public void setActualAchievement(String actualAchievement) {
		this.actualAchievement = actualAchievement;
	}
	public String getIsMidTermInspection() {
		return isMidTermInspection;
	}
	public void setIsMidTermInspection(String isMidTermInspection) {
		this.isMidTermInspection = isMidTermInspection;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getSocialEconomicGoal() {
		return socialEconomicGoal;
	}
	public void setSocialEconomicGoal(String socialEconomicGoal) {
		this.socialEconomicGoal = socialEconomicGoal;
	}
	public String getNationalEconomyTrade() {
		return nationalEconomyTrade;
	}
	public void setNationalEconomyTrade(String nationalEconomyTrade) {
		this.nationalEconomyTrade = nationalEconomyTrade;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	}

