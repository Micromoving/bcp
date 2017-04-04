package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;

/**
 * 视图Entity
 * 
 * @author 视图
 * @version 2016-04-09
 */
public class EmpPerformance4View extends DataEntity<EmpPerformance4View> {

	private static final long serialVersionUID = 1L;
	private String name;
	private String loginName;
	private String officeId;
	private String officeName;
	private Double latestSal;
	private Double stdSal;
	private String performanceLevel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Double getLatestSal() {
		return latestSal;
	}

	public void setLatestSal(Double latestSal) {
		this.latestSal = latestSal;
	}

	public Double getStdSal() {
		return stdSal;
	}

	public void setStdSal(Double stdSal) {
		this.stdSal = stdSal;
	}

	public String getPerformanceLevel() {
		return performanceLevel;
	}

	public void setPerformanceLevel(String performanceLevel) {
		this.performanceLevel = performanceLevel;
	}

}