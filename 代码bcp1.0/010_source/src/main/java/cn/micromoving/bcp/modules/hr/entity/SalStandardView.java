package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;

/**
 * 工资标准视图Entity
 * @author 仇左临
 * @version 2016-08-03
 */
public class SalStandardView extends DataEntity<SalStandardView>{
	private static final long serialVersionUID = 1L;
	/*岗位类别*/
	public String postType;
	/*岗位等级*/
	public String postLevel;
	/*职级*/
	public String professionalLevel;
	/*学历*/
	public String eduBackground;
	/*职级工资*/
	public double professionalLevelSalary;
	/*岗位工资*/
	public double postSalary;
	/*绩效等级*/
	public String performanceLevel;
	/*绩效工资*/
	public double performanceSalary;
	/*岗位津贴*/
	public double postSubside;
	
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
	public String getProfessionalLevel() {
		return professionalLevel;
	}
	public void setProfessionalLevel(String professionalLevel) {
		this.professionalLevel = professionalLevel;
	}
	public String getEduBackground() {
		return eduBackground;
	}
	public void setEduBackground(String eduBackground) {
		this.eduBackground = eduBackground;
	}
	public double getProfessionalLevelSalary() {
		return professionalLevelSalary;
	}
	public void setProfessionalLevelSalary(double professionalLevelSalary) {
		this.professionalLevelSalary = professionalLevelSalary;
	}
	public double getPostSalary() {
		return postSalary;
	}
	public void setPostSalary(double postSalary) {
		this.postSalary = postSalary;
	}
	public String getPerformanceLevel() {
		return performanceLevel;
	}
	public void setPerformanceLevel(String performanceLevel) {
		this.performanceLevel = performanceLevel;
	}
	public double getPerformanceSalary() {
		return performanceSalary;
	}
	public void setPerformanceSalary(double performanceSalary) {
		this.performanceSalary = performanceSalary;
	}
	public double getPostSubside() {
		return postSubside;
	}
	public void setPostSubside(double postSubside) {
		this.postSubside = postSubside;
	}

}
