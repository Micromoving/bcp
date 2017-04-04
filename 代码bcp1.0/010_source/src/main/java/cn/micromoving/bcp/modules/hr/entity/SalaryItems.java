package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;

/**
 * 工资项表Entity
 * 
 * @author wenyang
 * @version 2016-07-14
 */

public class SalaryItems extends DataEntity<SalaryItems> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* 工资标识 */
	private String salaryMark;
	/* 工资项名称 */
	private String salaryItemsName;
	/* 类型 */
	private String salaryType;
	/* 是否系统数据 */
	private String isSys;
	/* 是否可用 */
	private String useable;
	/* 是否为非在编工资项 */
	private String isNotSeries;

	public String getIsNotSeries() {
		return isNotSeries;
	}

	public void setIsNotSeries(String isNotSeries) {
		this.isNotSeries = isNotSeries;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public String getSalaryType() {
		return salaryType;
	}

	public void setSalaryType(String salaryType) {
		this.salaryType = salaryType;
	}

	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}

	public String getSalaryMark() {
		return salaryMark;
	}

	public void setSalaryMark(String salaryMark) {
		this.salaryMark = salaryMark;
	}

	public String getSalaryItemsName() {
		return salaryItemsName;
	}

	public void setSalaryItemsName(String salaryItemsName) {
		this.salaryItemsName = salaryItemsName;
	}

}