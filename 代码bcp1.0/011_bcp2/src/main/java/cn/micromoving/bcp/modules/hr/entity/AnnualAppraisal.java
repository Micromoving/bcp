package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 
 * 
 * @author 微动信息 Administrator
 * @version $Revision:  $ $Date:  $
 * <pre>
 * ■变更记录■
 * 2017年2月25日 创建
 * </pre>
 */
public class AnnualAppraisal extends DataEntity<AnnualAppraisal> {
	
	private static final long serialVersionUID = 1L;
	/*教师*/
	private Employee employee;
	/* 用户 */
	private User user;
	/*部门*/
	private Office office;
	/*考核年度*/
	private String checkYear;
	/*考核结果*/
	private String checkResult;
	/*考核单位名称*/
	private String checkUnit;
	/*未考核原因*/
	private String notCheckReason;
	/*备注*/
	private String comments;
	/*荣誉级别*/
	private String annualAppraisalLevel;
	public String getAnnualAppraisalLevel() {
		return annualAppraisalLevel;
	}
	public void setAnnualAppraisalLevel(String annualAppraisalLevel) {
		this.annualAppraisalLevel = annualAppraisalLevel;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getCheckYear() {
		return checkYear;
	}
	public void setCheckYear(String checkYear) {
		this.checkYear = checkYear;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getCheckUnit() {
		return checkUnit;
	}
	public void setCheckUnit(String checkUnit) {
		this.checkUnit = checkUnit;
	}
	public String getNotCheckReason() {
		return notCheckReason;
	}
	public void setNotCheckReason(String notCheckReason) {
		this.notCheckReason = notCheckReason;
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
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
}
