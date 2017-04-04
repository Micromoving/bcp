package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;

/**
 * 险种规则表Entity
 * 
 * @author wangzhichen
 * @version 2016-06-03
 * 
 */
public class InsuranceRule extends DataEntity<InsuranceRule> {
	private static final long serialVersionUID = 1L;

	
	/* 险种 */
	private String insuranceType;
	/* 单位比例 */
	private double unitProportion;
	/* 个人比例 */
	private double peopleProportion;
	
	
	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public double getUnitProportion() {
		return unitProportion;
	}

	public void setUnitProportion(double unitProportion) {
		this.unitProportion = unitProportion;
	}

	public double getPeopleProportion() {
		return peopleProportion;
	}

	public void setPeopleProportion(double peopleProportion) {
		this.peopleProportion = peopleProportion;
	}

}
