package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 津贴标准表Entity
 * @author baoke
 * @version 2016-07-13
 */
public class SubsidiaryStandard extends DataEntity<SubsidiaryStandard>{
	
	private static final long serialVersionUID = 1L;	
	private User user;
	/*方案编号*/
	private SalaryPlan salaryPlan;
	/*津贴类型*/
	private String allowanceType;
	/*津贴主体*/
	private String allowanceMainBody;													
	/*费用标准*/
	private Double chargeStandard;													
	/*津贴单位*/
	private String allowanceUnit; 													
	/*备注*/
	private String comments;
	
	public SalaryPlan getSalaryPlan() {
		return salaryPlan;
	}
	public void setSalaryPlan(SalaryPlan salaryPlan) {
		this.salaryPlan = salaryPlan;
	}
	public String getAllowanceType() {
		return allowanceType;
	}
	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}
	public String getAllowanceMainBody() {
		return allowanceMainBody;
	}
	public void setAllowanceMainBody(String allowanceMainBody) {
		this.allowanceMainBody = allowanceMainBody;
	}
	public Double getChargeStandard() {
		return chargeStandard;
	}
	public void setChargeStandard(Double chargeStandard) {
		this.chargeStandard = chargeStandard;
	}
	public String getAllowanceUnit() {
		return allowanceUnit;
	}
	public void setAllowanceUnit(String allowanceUnit) {
		this.allowanceUnit = allowanceUnit;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

	
}
