package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;

/**
 * 社保转移表Entity
 * 
 * @author wangzhichen
 * @version 2016-06-07
 * 
 */
public class InsuranceTransfer extends DataEntity<InsuranceTransfer> {
	private static final long serialVersionUID = 1L;

	/* 编号 */
	private String id;
	/* 职员编号 */
	private String employeeId;
	/* 险种编号 */
	private String insuranceId;
	/* 缴费开始时间 */
	private Date startDate;
	/* 缴费结束时间 */
	private Date endDate;
	/* 原参保单位 */
	private String originalInsuredUnit;
	/* 删除标记 */
	private String delFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getOriginalInsuredUnit() {
		return originalInsuredUnit;
	}

	public void setOriginalInsuredUnit(String originalInsuredUnit) {
		this.originalInsuredUnit = originalInsuredUnit;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
