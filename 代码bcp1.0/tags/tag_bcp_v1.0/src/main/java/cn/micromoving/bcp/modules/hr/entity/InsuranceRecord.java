package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 参保记录表Entity
 * 
 * @author wangzhichen
 * @version 2016-06-03
 * 
 */
public class InsuranceRecord extends DataEntity<InsuranceRecord> {
	private static final long serialVersionUID = 1L;
	private User user;
	/* 工号 */
	private Employee employee;
	/**/
	private String userNo;
	/* 教师编号 */
	private String employeeId;
	/* 险种编号 */
	private String insuredType;

	/* 单位缴费金额 */
	private Double unitPayment;
	/* 个人缴费金额 */
	private Double peoplePayment;
	/* 缴费时间 */
	private Date paymentDate;

	/* 备注 */
	private String comments;
	/* 险种 */
	private InsuranceRule insuranceRule;

	@ExcelField(title = "备注", type = 0, align = 2, sort = 70)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@ExcelField(title = "姓名", type = 0, align = 2, sort = 20, value = "user.name")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ExcelField(title = "工号", type = 1, align = 2, sort = 10, value = "employee.userNo")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ExcelField(title = "工号", type = 2, align = 2, sort = 10)
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@ExcelField(title = "单位", type = 0, align = 2, sort = 40)
	public Double getUnitPayment() {
		return unitPayment;
	}

	@ExcelField(title = "险种", type = 2, align = 2, sort = 30, dictType = "insurance_type")
	public String getInsuredType() {
		return insuredType;
	}

	public void setInsuredType(String insuredType) {
		this.insuredType = insuredType;
	}

	public void setUnitPayment(Double unitPayment) {
		this.unitPayment = unitPayment;
	}

	@ExcelField(title = "个人", type = 0, align = 2, sort = 50)
	public Double getPeoplePayment() {
		return peoplePayment;
	}

	public void setPeoplePayment(Double peoplePayment) {
		this.peoplePayment = peoplePayment;
	}

	@ExcelField(title = "缴费时间", type = 0, align = 2, sort = 60)
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@ExcelField(title = "险种", type = 1, align = 2, sort = 30, value = "insuranceRule.insuranceType")
	public InsuranceRule getInsuranceRule() {
		return insuranceRule;
	}

	public void setInsuranceRule(InsuranceRule insuranceRule) {
		this.insuranceRule = insuranceRule;
	}
}
