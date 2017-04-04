package cn.micromoving.bcp.modules.hr.entity;



import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

public class DisposeInformation extends DataEntity<DisposeInformation>{
	/**
	 * <code>serialVersionUID</code> 的注释
	 */
	private static final long serialVersionUID = 1L;
	
	/*教师*/
	private Employee employee;
	private User user;
	private Office office;
	/*处分类别*/
	private String disciplinaryCategories;
	/*处分原因*/
	private String disciplinaryReasons;
	/*处分发生日期*/
	private Date disposeStartDate;
	/*处分记录描述*/
	private String disposeRecordDescription;
	/*处分单位名称*/
	private String disposeUnit;
	/*处分日期*/
	private Date disposeDate;
	/*处分撤销日期*/
	private Date disposeEndDate;
	/*处分撤销原因*/
	private String disposeEndReason;
	
	private String disposeStartDateString;
	
	private String disposeDateString;
	
	private String disposeEndDateString;

	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getDisciplinaryCategories() {
		return disciplinaryCategories;
	}
	public void setDisciplinaryCategories(String disciplinaryCategories) {
		this.disciplinaryCategories = disciplinaryCategories;
	}
	public String getDisciplinaryReasons() {
		return disciplinaryReasons;
	}
	public void setDisciplinaryReasons(String disciplinaryReasons) {
		this.disciplinaryReasons = disciplinaryReasons;
	}
	public Date getDisposeStartDate() {
		return disposeStartDate;
	}
	public void setDisposeStartDate(Date disposeStartDate) {
		this.disposeStartDate = disposeStartDate;
	}
	public String getDisposeRecordDescription() {
		return disposeRecordDescription;
	}
	public void setDisposeRecordDescription(String disposeRecordDescription) {
		this.disposeRecordDescription = disposeRecordDescription;
	}
	public String getDisposeUnit() {
		return disposeUnit;
	}
	public void setDisposeUnit(String disposeUnit) {
		this.disposeUnit = disposeUnit;
	}
	public Date getDisposeDate() {
		return disposeDate;
	}
	public void setDisposeDate(Date disposeDate) {
		this.disposeDate = disposeDate;
	}
	public Date getDisposeEndDate() {
		return disposeEndDate;
	}
	public void setDisposeEndDate(Date disposeEndDate) {
		this.disposeEndDate = disposeEndDate;
	}
	public String getDisposeEndReason() {
		return disposeEndReason;
	}
	public void setDisposeEndReason(String disposeEndReason) {
		this.disposeEndReason = disposeEndReason;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getDisposeStartDateString() {
		this.disposeStartDateString = DateUtils.formatDate(
				disposeStartDate, Global.getConfig("web.dateFormat"));
		return this.disposeStartDateString;
	}

	public void setDisposeStartDateString(String disposeStartDateString) {
		this.disposeStartDate = StringUtils.toDate(disposeStartDateString, Global.getConfig("web.dateFormat"));
	
	}
	public String getDisposeDateString() {
		this.disposeDateString = DateUtils.formatDate(
				disposeDate, Global.getConfig("web.dateFormat"));
		return this.disposeDateString;
	}

	public void setDisposeDateString(String disposeDateString) {
		this.disposeDate = StringUtils.toDate(disposeDateString, Global.getConfig("web.dateFormat"));
	
	}
	public String getDisposeEndDateString() {
		this.disposeEndDateString = DateUtils.formatDate(
				disposeEndDate, Global.getConfig("web.dateFormat"));
		return this.disposeEndDateString;
	}

	public void setDisposeEndDateString(String disposeEndDateString) {
		this.disposeEndDate = StringUtils.toDate(disposeEndDateString, Global.getConfig("web.dateFormat"));
	
	}
}
