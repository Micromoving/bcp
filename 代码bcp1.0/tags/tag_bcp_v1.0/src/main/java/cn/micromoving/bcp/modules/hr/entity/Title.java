
package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 校内职务表	 Entity
 * @author 校内职务表	
 * @version 2016-04-5
 */
public class Title extends DataEntity<Title>{
	private static final long serialVersionUID = 1L;
	/*编号*/
	private String id;  
	/*用户编号*/
	private User user;      
	/*起始时间*/
	private Date startDate;     
	/*终止时间*/
	private Date endDate;       
	/*职务名称*/
	private String jobName;      
	/*职务描述*/
	private String remarks; 
	/* 起始时间字符*/
	private String startDateString;   
	/* 终止时间字符*/
	private String endDateString   ;   
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

}
