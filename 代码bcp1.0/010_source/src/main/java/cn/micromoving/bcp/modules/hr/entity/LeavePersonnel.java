package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;
import java.util.List;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 请假人员Entity
 * 
 * @author 微动信息 王志辰
 * @version $Revision: $ $Date: $
 * 
 *          <pre>
 * ■变更记录■
 * 2016年12月11日 创建
 * </pre>
 */
public class LeavePersonnel extends DataEntity<LeavePersonnel> {

	private static final long serialVersionUID = 1L;

	/* 用户编号 */
	private User user;
	/* 部门 */
	private Office office;
	/* 请假类型 */
	private String leaveType;
	/* 请假开始时间 */
	/* 请假结束时间 */
	private String leaveDate;
	/* 是否已销假 */
	private String reportStatus;
	/* 销假时间 */
	private Date reportDate;
	/* 人员id */
	private List<String> employeeList;
	/* 扣考勤费List */
	private List<Double> buckleAbsenteeismList;
	/* 扣考勤费List */
	private Double buckleAbsenteeism;
	/* 岗位类型 */
	private String postType;
	/* 对应的流程id */
	private String salaryInstanceId;

	public String getSalaryInstanceId() {
		return salaryInstanceId;
	}

	public void setSalaryInstanceId(String salaryInstanceId) {
		this.salaryInstanceId = salaryInstanceId;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
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

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public List<String> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<String> employeeList) {
		this.employeeList = employeeList;
	}

	public List<Double> getBuckleAbsenteeismList() {
		return buckleAbsenteeismList;
	}

	public void setBuckleAbsenteeismList(List<Double> buckleAbsenteeismList) {
		this.buckleAbsenteeismList = buckleAbsenteeismList;
	}

	public Double getBuckleAbsenteeism() {
		return buckleAbsenteeism;
	}

	public void setBuckleAbsenteeism(Double buckleAbsenteeism) {
		this.buckleAbsenteeism = buckleAbsenteeism;
	}

}
