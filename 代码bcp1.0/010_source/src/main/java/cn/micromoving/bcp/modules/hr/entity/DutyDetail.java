package cn.micromoving.bcp.modules.hr.entity;

import java.util.List;

import cn.micromoving.bcp.common.persistence.ActEntity;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;

import com.google.common.collect.Lists;

/**
 * 值班明细表Entity
 * 
 * @author wenyang
 * @version 2016-07-14
 */

public class DutyDetail extends DataEntity<DutyDetail> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* 用户编号 */
	private User user;
	/* 部门编号 */
	private Office office;
	/* 上报记录编号 */
	private ReportRecord reportRecord;
	/* 状态 */
	private String dataState;
	/* 值班类型 */
	private String dutyType;
	/* 值班天数 */
	private Integer dutyDays;
	/* 职员姓名 */
	private String name;
	/* 中午值班 */
	private Integer noonDutyDays;
	/* 晚上值班 */
	private Integer nightDutyDays;
	/* 周末值班 */
	private Integer weekendsDutyDays;
	/* 法定节假日 */
	private Integer holidayDutyDays;
	/* 登录名 */
	private String loginName;

	private String flag;

	private List<String> dataIdList = Lists.newArrayList();

	private List<Integer> dutyDaysList = Lists.newArrayList();

	private List<String> employeeIdList = Lists.newArrayList();

	private List<Integer> noonDutyDaysList = Lists.newArrayList();

	private List<Integer> nightDutyDaysList = Lists.newArrayList();

	private List<Integer> weekwedsDutyDaysList = Lists.newArrayList();

	private List<Integer> holidayDatyDuysList = Lists.newArrayList();

	public DutyDetail() {
		super();
		office = new Office();
		user = new User();
		reportRecord = new ReportRecord();

	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 获取。
	 * 
	 * @return loginName
	 */
	@ExcelField(title = "职员编号", type = 0, align = 2, sort = 10,value="user.loginName")
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

	@ExcelField(title = "中午值班", type = 0, align = 2, sort = 40)
	public Integer getNoonDutyDays() {
		return noonDutyDays;
	}

	public void setNoonDutyDays(Integer noonDutyDays) {
		this.noonDutyDays = noonDutyDays;
	}

	@ExcelField(title = "晚上值班", type = 0, align = 2, sort = 50)
	public Integer getNightDutyDays() {
		return nightDutyDays;
	}

	public void setNightDutyDays(Integer nightDutyDays) {
		this.nightDutyDays = nightDutyDays;
	}

	@ExcelField(title = "周末值班", type = 0, align = 2, sort = 60)
	public Integer getWeekendsDutyDays() {
		return weekendsDutyDays;
	}

	public void setWeekendsDutyDays(Integer weekendsDutyDays) {
		this.weekendsDutyDays = weekendsDutyDays;
	}

	@ExcelField(title = "节假日值班", type = 0, align = 2, sort = 70)
	public Integer getHolidayDutyDays() {
		return holidayDutyDays;
	}

	public void setHolidayDutyDays(Integer holidayDutyDays) {
		this.holidayDutyDays = holidayDutyDays;
	}

	public List<Integer> getNoonDutyDaysList() {
		return noonDutyDaysList;
	}

	public void setNoonDutyDaysList(List<Integer> noonDutyDaysList) {
		this.noonDutyDaysList = noonDutyDaysList;
	}

	public List<Integer> getNightDutyDaysList() {
		return nightDutyDaysList;
	}

	public void setNightDutyDaysList(List<Integer> nightDutyDaysList) {
		this.nightDutyDaysList = nightDutyDaysList;
	}

	public List<Integer> getWeekwedsDutyDaysList() {
		return weekwedsDutyDaysList;
	}

	public void setWeekwedsDutyDaysList(List<Integer> weekwedsDutyDaysList) {
		this.weekwedsDutyDaysList = weekwedsDutyDaysList;
	}

	public List<Integer> getHolidayDatyDuysList() {
		return holidayDatyDuysList;
	}

	public void setHolidayDatyDuysList(List<Integer> holidayDatyDuysList) {
		this.holidayDatyDuysList = holidayDatyDuysList;
	}

	public List<Integer> getDutyDaysList() {
		return dutyDaysList;
	}

	public void setDutyDaysList(List<Integer> dutyDaysList) {
		this.dutyDaysList = dutyDaysList;
	}

	@ExcelField(title = "院系", type = 0, align = 2, sort = 30,value="user.office.name")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public List<String> getEmployeeIdList() {
		return employeeIdList;
	}

	public void setEmployeeIdList(List<String> employeeIdList) {
		this.employeeIdList = employeeIdList;
	}

	@ExcelField(title = "姓名", type = 0, align = 2, sort = 20,value="user.name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

	public List<String> getDataIdList() {
		return dataIdList;
	}

	public void setDataIdList(List<String> dataIdList) {
		this.dataIdList = dataIdList;
	}

	public ReportRecord getReportRecord() {
		return reportRecord;
	}

	public void setReportRecord(ReportRecord reportRecord) {
		this.reportRecord = reportRecord;
	}

	public String getDutyType() {
		return dutyType;
	}

	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
	}

	public Integer getDutyDays() {
		return dutyDays;
	}

	public void setDutyDays(Integer dutyDays) {
		this.dutyDays = dutyDays;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}