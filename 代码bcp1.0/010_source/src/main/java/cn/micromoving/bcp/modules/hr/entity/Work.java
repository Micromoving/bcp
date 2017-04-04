package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 工作经验表 Entity
 * 
 * @author 工作经验表
 * @version 2016-04-5
 */
public class Work extends DataEntity<Work> {

	private static final long serialVersionUID = 1L;
	/* 用户编号 */
	private User user;
	/* 工号 */
	private Employee employee;
	/**/
	private String userNo;
	/* 起始时间 */
	private Date startDate;
	/* 终止时间 */
	private Date endDate;
	/* 单位名称 */
	private String companyName;
	/* 单位性质 */
	private String companyNature;
	/* 归属部门 */
	private String companyDepartment;
	/* 职位名称 */
	private String position;
	/* 工作描绘 */
	private String remarks;
	/* 工作类型 */
	private String workSort;
	/* 起始时间字符 */
	private String startDateString;
	/* 终止时间字符 */
	private String endDateString;
	/* 备注 */
	private String comments;

	@ExcelField(title = "起始时间", type = 0, align = 2, sort = 30)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@ExcelField(title = "终止时间", type = 0, align = 2, sort = 40)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@ExcelField(title = "单位名称", type = 0, align = 2, sort = 50)
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@ExcelField(title = "单位性质", type = 0, align = 2, sort = 60,dictType="company_nature")
	public String getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	@ExcelField(title = "归属部门", type = 0, align = 2, sort = 70)
	public String getCompanyDepartment() {
		return companyDepartment;
	}

	public void setCompanyDepartment(String companyDepartment) {
		this.companyDepartment = companyDepartment;
	}

	@ExcelField(title = "工作类型", type = 0, align = 2, sort = 100,dictType="work_sort")
	public String getWorkSort() {
		return workSort;
	}

	public void setWorkSort(String workSort) {
		this.workSort = workSort;
	}

	@ExcelField(title = "职位名称", type = 0, align = 2, sort = 80)
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@ExcelField(title = "工作描绘", type = 0, align = 2, sort = 90)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	
	public String getStartDateString() {
		this.startDateString = DateUtils.formatDate(startDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return this.startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDate = StringUtils.toDate(startDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}

	public String getEndDateString() {
		this.endDateString = DateUtils.formatDate(endDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return this.endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDate = StringUtils.toDate(endDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}

	@ExcelField(title = "备注", type = 0, align = 2, sort = 110)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
