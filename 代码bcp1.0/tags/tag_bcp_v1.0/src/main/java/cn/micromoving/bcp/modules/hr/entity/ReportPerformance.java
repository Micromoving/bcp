package cn.micromoving.bcp.modules.hr.entity;

import java.util.List;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

import com.google.common.collect.Lists;

/**
 * 上报绩效明细Entity
 * 
 * @author baoke
 * @version 2016-07-13
 */
/**
 * @author mujinyao
 * 
 */
public class ReportPerformance extends DataEntity<ReportPerformance> {

	private static final long serialVersionUID = 1L;
	private String dataState;
	/* 上报记录编号 */
	private ReportRecord reportRecord;
	/* 员工编号 */
	private Employee employee;

	private User user;

	private String loginName;
	private String name;

	private Office office;
	/* 绩效工资 */
	private Double performanceSalary;
	/* 数据来源 */
	private String dataFrom;
	/* 上月发的绩效 */
	private Double latestSal;
	/* 应发绩效postType=2 */
	private Double stdSal;
	/* 应发绩效postType=4 */
	private Double stdSal1;
	/* 应发绩效postType=4 */
	private Double stdSal2;
	/* 应发绩效postType=4 */
	private Double stdSal3;

	private String flag;
	private List<Double> performanceSalaryList = Lists.newArrayList();
	private List<String> dataIdList = Lists.newArrayList();
	private List<String> employeeIdList = Lists.newArrayList();

	public ReportPerformance() {
		super();
		office = new Office();
		user = new User();
		reportRecord = new ReportRecord();
	}

	public Double getStdSal1() {
		return stdSal1;
	}

	public void setStdSal1(Double stdSal1) {
		this.stdSal1 = stdSal1;
	}

	public Double getStdSal2() {
		return stdSal2;
	}

	public void setStdSal2(Double stdSal2) {
		this.stdSal2 = stdSal2;
	}

	public Double getStdSal3() {
		return stdSal3;
	}

	public void setStdSal3(Double stdSal3) {
		this.stdSal3 = stdSal3;
	}

	public Double getLatestSal() {
		return latestSal;
	}

	public void setLatestSal(Double latestSal) {
		this.latestSal = latestSal;
	}

	public Double getStdSal() {
		return stdSal;
	}

	public void setStdSal(Double stdSal) {
		this.stdSal = stdSal;
	}

	public String getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<String> getEmployeeIdList() {
		return employeeIdList;
	}

	public void setEmployeeIdList(List<String> employeeIdList) {
		this.employeeIdList = employeeIdList;
	}

	private List<Double> performanceList = Lists.newArrayList();

	public List<String> getDataIdList() {
		return dataIdList;
	}

	public void setDataIdList(List<String> dataIdList) {
		this.dataIdList = dataIdList;
	}

	public List<Double> getPerformanceList() {
		return performanceList;
	}

	public void setPerformanceList(List<Double> performanceList) {
		this.performanceList = performanceList;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ExcelField(title = "绩效工资", type = 0, align = 2, sort = 50)
	public Double getPerformanceSalary() {
		return performanceSalary;
	}

	public void setPerformanceSalary(Double performanceSalary) {
		this.performanceSalary = performanceSalary;
	}

	public ReportRecord getReportRecord() {
		return reportRecord;
	}

	public void setReportRecord(ReportRecord reportRecord) {
		this.reportRecord = reportRecord;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ExcelField(title = "院系", type = 0, align = 2, sort = 40, value = "user.office.name")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

	public List<Double> getPerformanceSalaryList() {
		return performanceSalaryList;
	}

	public void setPerformanceSalaryList(List<Double> performanceSalaryList) {
		this.performanceSalaryList = performanceSalaryList;
	}

	/**
	 * 获取。
	 * 
	 * @return loginName
	 */
	@ExcelField(title = "职员编号", type = 0, align = 2, sort = 20, value = "user.loginName")
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 设置loginName。
	 * 
	 * @param loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@ExcelField(title = "姓名", type = 0, align = 2, sort = 30, value = "user.name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
