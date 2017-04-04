package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 值班表Entity
 * 
 * @author baoke
 * @version 2016-07-13
 */
public class Duty extends DataEntity<Duty> {

	private static final long serialVersionUID = 1L;
	/* 方案编号 */
	private SalaryPlan salaryPlan;
	/* 值班类型 */
	private String dutyType;
	/* 绩效工资 */
	private Double chargeStandard;
	/* 用户 */
	private User user;
	
	public User getUser() {
		return user;
	}

	public Double getChargeStandard() {
		return chargeStandard;
	}

	public void setChargeStandard(Double chargeStandard) {
		this.chargeStandard = chargeStandard;
	}

	public SalaryPlan getSalaryPlan() {
		return salaryPlan;
	}

	public void setSalaryPlan(SalaryPlan salaryPlan) {
		this.salaryPlan = salaryPlan;
	}

	public String getDutyType() {
		return dutyType;
	}

	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
