package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;

public class LanguageAbility extends DataEntity<LanguageAbility>{
	private static final long serialVersionUID = 1L;
	/* 教师 */
	private Employee employee;
	/*语种*/
	private String languages;
	/*掌握程度*/
	private String masterDegree;
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getMasterDegree() {
		return masterDegree;
	}
	public void setMasterDegree(String masterDegree) {
		this.masterDegree = masterDegree;
	}
	
	
	
	
	

}
