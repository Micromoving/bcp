package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;
import java.util.List;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.ActEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 上报记录表Entity
 * @author baoke
 * @version 2016-07-13
 */
public class ReportRecord extends ActEntity<ReportRecord>{
	
	private static final long serialVersionUID = 1L;
	private User user;
	/*工资实例编号*/
	private SalaryInstance salaryInstance;
	/*数据分类*/
	private String dataClassification;
	/*部门*/
	private Office office;
	/*年月*/
	private String yearMonth;
	/*开始时间*/
	private Date startDate;
	/*结束时间*/
	private Date endDate;
	/*开始时间字符串*/
	private String startDateString;
	/*结束时间字符串*/
	private String endDateString;
	/*数据状态*/
	private String dataState;
	/**/
	private String processInstanceId;
	private String flag;
	private String postType;
	
	private List<String> recordIdList;
	private List<String> salaryIdList;
	
	
	public List<String> getSalaryIdList() {
		return salaryIdList;
	}
	public void setSalaryIdList(List<String> salaryIdList) {
		this.salaryIdList = salaryIdList;
	}
	public List<String> getRecordIdList() {
		return recordIdList;
	}
	
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public void setRecordIdList(List<String> recordIdList) {
		this.recordIdList = recordIdList;
	}
	public SalaryInstance getSalaryInstance() {
		return salaryInstance;
	}
	public void setSalaryInstance(SalaryInstance salaryInstance) {
		this.salaryInstance = salaryInstance;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getStartDateString() {
		this.startDateString = DateUtils.formatDate(startDate, Global.getConfig("web.dateShortFormatCHN"));
		return this.startDateString;
	}
	public void setStartDateString(String startDateString) {
		this.startDate=StringUtils.toDate(startDateString, Global.getConfig("web.dateShortFormatCHN"));
	}
	public String getEndDateString() {
		this.endDateString= DateUtils.formatDate(endDate, Global.getConfig("web.dateShortFormatCHN"));
		return this.endDateString;
	}
	public void setEndDateString(String endDateString) {
		this.endDate=StringUtils.toDate(endDateString, Global.getConfig("web.dateShortFormatCHN"));
		}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	/*数据状态*/
	public String getDataClassification() {
		return dataClassification;
	}
	public void setDataClassification(String dataClassification) {
		this.dataClassification = dataClassification;
	}
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
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
