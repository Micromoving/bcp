
package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 证书表   Entity
 * @author 证书表	
 * @version 2016-04-5
 */
public class Certificate extends DataEntity<Certificate>{

	private static final long serialVersionUID = 1L;
	/*编号*/
	private String id; 
    /*用户编号*/
	private User user;
	/* 获得时间*/
	private Date gainDate;  
    /* 证书名称*/
	private String certificateName;
	/*成绩*/
	private String grade;
	/*获得时间字符串*/
	private String gainDateString;
	
	public String getGainDateString() {
		this.gainDateString = DateUtils.formatDate(gainDate, Global.getConfig("web.dateShortFormatCHN"));
		return this.gainDateString;
	}
	public void setGainDateString(String gainDateString) {
		this.gainDate=StringUtils.toDate(gainDateString, Global.getConfig("web.dateShortFormatCHN"));
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCertificateName() {
		return certificateName;
	}
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Date getGainDate() {
		return gainDate;
	}
	public void setGainDate(Date gainDate) {
		this.gainDate = gainDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
