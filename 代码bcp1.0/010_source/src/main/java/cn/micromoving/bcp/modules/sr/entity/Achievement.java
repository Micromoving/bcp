package cn.micromoving.bcp.modules.sr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.hr.entity.Employee;

/**
 * 成果管理表Entity
 * @author 成果管理表
 * @version 2016-04-09
 */
public class Achievement extends DataEntity<Achievement>{
	
	private static final long serialVersionUID = 1L;
	/*成果编号*/
	private String achievementId;
	/*成果名称*/
	private String achievementName;
	 /*用户编号*/
	private  Employee employee;
	/*第一作者*/
	private String employeeId;
	/*成果来源*/
	private String achievementSource;
	/*成果形式*/
	private String achievementType;
	/*发表范围*/
	private String publishScope;
	/*是否译成外文*/
	private String foreignLanguage;
	/*研究类别*/
	private String researchType;
	/*是否提交有关部门*/
	private String submit;
	/*是否被采纳*/
	private String adopt;
	/*学科门类*/
	private String subjectCategory;
	/*二级学科*/
	private String secondarySubject;
	/*出版发表使用单位*/
	private String useUnit;
	/*出版发表使用时间*/
	private Date useDate;
	/*成果英文名称*/
	private String achievementEnglishName;
	/*所属项目*/
	private String Project;
	/*发行代码*/
	private String issueCode;
	/*关键字*/
	private String Keyword;
	/*英文关键字*/
	private String englishKeyword;
	/*成果引用采纳情况*/
	private String achievementQuote;
	/*成果字数*/
	private String achievementWords;
	/*成果摘要*/
	private String achievementSummary;
	/*更新时间*/
	private Date updateDate;
	/*创建时间*/
	private Date createDate;
	public String getAchievementId() {
		return achievementId;
	}
	public void setAchievementId(String achievementId) {
		this.achievementId = achievementId;
	}
	public String getAchievementName() {
		return achievementName;
	}
	public void setAchievementName(String achievementName) {
		this.achievementName = achievementName;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getAchievementSource() {
		return achievementSource;
	}
	public void setAchievementSource(String achievementSource) {
		this.achievementSource = achievementSource;
	}
	public String getAchievementType() {
		return achievementType;
	}
	public void setAchievementType(String achievementType) {
		this.achievementType = achievementType;
	}
	public String getPublishScope() {
		return publishScope;
	}
	public void setPublishScope(String publishScope) {
		this.publishScope = publishScope;
	}
	public String getForeignLanguage() {
		return foreignLanguage;
	}
	public void setForeignLanguage(String foreignLanguage) {
		this.foreignLanguage = foreignLanguage;
	}
	public String getResearchType() {
		return researchType;
	}
	public void setResearchType(String researchType) {
		this.researchType = researchType;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public String getAdopt() {
		return adopt;
	}
	public void setAdopt(String adopt) {
		this.adopt = adopt;
	}
	public String getSubjectCategory() {
		return subjectCategory;
	}
	public void setSubjectCategory(String subjectCategory) {
		this.subjectCategory = subjectCategory;
	}
	public String getSecondarySubject() {
		return secondarySubject;
	}
	public void setSecondarySubject(String secondarySubject) {
		this.secondarySubject = secondarySubject;
	}
	public String getUseUnit() {
		return useUnit;
	}
	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	public String getAchievementEnglishName() {
		return achievementEnglishName;
	}
	public void setAchievementEnglishName(String achievementEnglishName) {
		this.achievementEnglishName = achievementEnglishName;
	}
	public String getProject() {
		return Project;
	}
	public void setProject(String project) {
		Project = project;
	}
	public String getIssueCode() {
		return issueCode;
	}
	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
	}
	public String getKeyword() {
		return Keyword;
	}
	public void setKeyword(String keyword) {
		Keyword = keyword;
	}
	public String getEnglishKeyword() {
		return englishKeyword;
	}
	public void setEnglishKeyword(String englishKeyword) {
		this.englishKeyword = englishKeyword;
	}
	public String getAchievementQuote() {
		return achievementQuote;
	}
	public void setAchievementQuote(String achievementQuote) {
		this.achievementQuote = achievementQuote;
	}
	public String getAchievementWords() {
		return achievementWords;
	}
	public void setAchievementWords(String achievementWords) {
		this.achievementWords = achievementWords;
	}
	public String getAchievementSummary() {
		return achievementSummary;
	}
	public void setAchievementSummary(String achievementSummary) {
		this.achievementSummary = achievementSummary;
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
	