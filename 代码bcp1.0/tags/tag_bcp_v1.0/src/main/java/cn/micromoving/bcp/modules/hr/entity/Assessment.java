package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 考核表Entity
 * @author 考核表
 * @version 2016-04-09
 */
public class Assessment extends DataEntity<Assessment>{
	
	private static final long serialVersionUID = 1L;
	private User user;
	/*工号*/
	private Employee employee;
	/*年*/
	private String assessmentYear;
	/*考核等级*/
	private String assessmentLevel;
	/*未考核原因*/
	private String noCheckrise;
	/*备注*/
	private String comments;
	/**/
	private String userNo;
	@ExcelField(title = "姓名", type = 0, align = 2, sort = 20, value = "user.name")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	@ExcelField(title = "考核年份", type = 0, align = 2, sort = 30)
	public String getAssessmentYear() {
		return assessmentYear;
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
	public void setAssessmentYear(String assessmentYear) {
		this.assessmentYear = assessmentYear;
	}
	@ExcelField(title = "未考核原因", type = 0, align = 2, sort = 50)
	public String getNoCheckrise() {
		return noCheckrise;
	}
	public void setNoCheckrise(String noCheckrise) {
		this.noCheckrise = noCheckrise;
	}
	@ExcelField(title = "备注", type = 0, align = 2, sort = 60)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@ExcelField(title = "考核等级", type = 0, align = 2, sort = 40,dictType="assessment_level")
	public String getAssessmentLevel() {
		return assessmentLevel;
	}
	public void setAssessmentLevel(String assessmentLevel) {
		this.assessmentLevel = assessmentLevel;
	}
}
