package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

public class Check extends DataEntity<Check> {
	
	private static final long serialVersionUID = 1L;
	
	/*教师*/
	private Employee employee;
    private User user;
    private Office office;
	
	/*师德考核时间*/
	private Date checkDate;
	/*师德考核时间字符串*/
	private String checkDateString;
	/*师德考核结论*/
	private String checkConclusion;
	/*师德考核单位名称*/
	private String checkUnit;
	
	public String getCheckDateString() {
		 checkDateString =DateUtils.formatDate(
				checkDate, Global.getConfig("web.dateShortFormatCHN"));
		return this.checkDateString;
	}
	public void setCheckDateString(String checkDateString) {
		this.checkDate = StringUtils.toDate(checkDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckConclusion() {
		return checkConclusion;
	}
	public void setCheckConclusion(String checkConclusion) {
		this.checkConclusion = checkConclusion;
	}
	public String getCheckUnit() {
		return checkUnit;
	}
	public void setCheckUnit(String checkUnit) {
		this.checkUnit = checkUnit;
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
