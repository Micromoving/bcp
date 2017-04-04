package cn.micromoving.bcp.modules.hr.entity;

import java.sql.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;

public class TechnicalPosts extends DataEntity<TechnicalPosts> {
	private static final long serialVersionUID = 1L;
	/*教师 */
	private Employee employee;
	/* 聘任专业技术职务 */
	private String appointmentPositions;
	/* 聘任开始年月 */
	private Date startYear;
	/* 聘任结束年月 */
	private Date endYear;
	/* 聘任单位名称 */
	private String unitName;
	/* 删除标记 */
	private String delFlag;


	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getAppointmentPositions() {
		return appointmentPositions;
	}

	public void setAppointmentPositions(String appointmentPositions) {
		this.appointmentPositions = appointmentPositions;
	}

	public Date getStartYear() {
		return startYear;
	}

	public void setStartYear(Date startYear) {
		this.startYear = startYear;
	}

	public Date getEndYear() {
		return endYear;
	}

	public void setEndYear(Date endYear) {
		this.endYear = endYear;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
