package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 职称情况表Entity
 * 
 * @author luyihao
 * @version 2016-06-01
 */
public class ProTechPosition extends DataEntity<ProTechPosition> {
	private static final long serialVersionUID = 1L;
	/* 用户编号 */
	private User user;

	private Employee employee;

    private String appointmentPositions;
	private Date startYear;
	private Date endYear;
	private String unitName;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
