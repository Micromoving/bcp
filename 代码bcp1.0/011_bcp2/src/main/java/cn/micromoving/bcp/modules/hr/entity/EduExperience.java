package cn.micromoving.bcp.modules.hr.entity;



import java.sql.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;

public class EduExperience extends DataEntity<EduExperience>{
	private static final long serialVersionUID = 1L;
	/* 教师 */
	private Employee employee;
	/*获得学历*/
	private String diploma;
	/*获得学历的国家（地区）*/
	private String diplomaCountry;
	/*获得学历的院校或机构*/
	private String diplomaInstitutions;
	/*所学专业*/
	private String major;
	/*是否师范类专业*/
	private String whetherNormalMajors;
	/*入学年月*/
	private Date schoolYears;
	/*毕业年月*/
	private Date graduateYears;
	/*学位层次*/
	private String degreeLevel;
	/*学位名称*/
	private String degreeName;
	/*获得学位的国家（地区）*/
	private String degreeCountries;
	/*获得学位的院校或机构*/
	private String degreeInstitutions;
	/*学位授予年月*/
	private Date degreeGrantingYears;
	/*学习方式*/
	private String learningStyle;
	/*在学单位类别*/
	private String categoryLearningUnit;
	/*学位取得时间*/
	private Date degreeInTime;
	/*研究方向*/
	private String researchDirection;
	/*是否海外*/
	private String whetherOverseas;
	/*专业描述*/
	private String professionalDescription;
	/*备注*/
	private String remark;
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
	public String getDiplomaCountry() {
		return diplomaCountry;
	}
	public void setDiplomaCountry(String diplomaCountry) {
		this.diplomaCountry = diplomaCountry;
	}
	public String getDiplomaInstitutions() {
		return diplomaInstitutions;
	}
	public void setDiplomaInstitutions(String diplomaInstitutions) {
		this.diplomaInstitutions = diplomaInstitutions;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getWhetherNormalMajors() {
		return whetherNormalMajors;
	}
	public void setWhetherNormalMajors(String whetherNormalMajors) {
		this.whetherNormalMajors = whetherNormalMajors;
	}
	public Date getSchoolYears() {
		return schoolYears;
	}
	public void setSchoolYears(Date schoolYears) {
		this.schoolYears = schoolYears;
	}
	public Date getGraduateYears() {
		return graduateYears;
	}
	public void setGraduateYears(Date graduateYears) {
		this.graduateYears = graduateYears;
	}
	public String getDegreeLevel() {
		return degreeLevel;
	}
	public void setDegreeLevel(String degreeLevel) {
		this.degreeLevel = degreeLevel;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	public String getDegreeCountries() {
		return degreeCountries;
	}
	public void setDegreeCountries(String degreeCountries) {
		this.degreeCountries = degreeCountries;
	}
	public String getDegreeInstitutions() {
		return degreeInstitutions;
	}
	public void setDegreeInstitutions(String degreeInstitutions) {
		this.degreeInstitutions = degreeInstitutions;
	}
	public Date getDegreeGrantingYears() {
		return degreeGrantingYears;
	}
	public void setDegreeGrantingYears(Date degreeGrantingYears) {
		this.degreeGrantingYears = degreeGrantingYears;
	}
	public String getLearningStyle() {
		return learningStyle;
	}
	public void setLearningStyle(String learningStyle) {
		this.learningStyle = learningStyle;
	}
	public String getCategoryLearningUnit() {
		return categoryLearningUnit;
	}
	public void setCategoryLearningUnit(String categoryLearningUnit) {
		this.categoryLearningUnit = categoryLearningUnit;
	}
	public Date getDegreeInTime() {
		return degreeInTime;
	}
	public void setDegreeInTime(Date degreeInTime) {
		this.degreeInTime = degreeInTime;
	}
	public String getResearchDirection() {
		return researchDirection;
	}
	public void setResearchDirection(String researchDirection) {
		this.researchDirection = researchDirection;
	}
	public String getWhetherOverseas() {
		return whetherOverseas;
	}
	public void setWhetherOverseas(String whetherOverseas) {
		this.whetherOverseas = whetherOverseas;
	}
	public String getProfessionalDescription() {
		return professionalDescription;
	}
	public void setProfessionalDescription(String professionalDescription) {
		this.professionalDescription = professionalDescription;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
