package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;

public class Skills extends DataEntity<Skills>{
	private static final long serialVersionUID = 1L;
	/*教师 */
	private Employee employee;
	/*其他技能名称*/
	private String otherSkills;
    /*其他技能掌握程度*/
	private String masterDegree;
	
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getOtherSkills() {
		return otherSkills;
	}
	public void setOtherSkills(String otherSkills) {
		this.otherSkills = otherSkills;
	}
	public String getMasterDegree() {
		return masterDegree;
	}
	public void setMasterDegree(String masterDegree) {
		this.masterDegree = masterDegree;
	}
	
	
	
	

}
