
package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 奖项表	Entity
 * @author 奖项表	
 * @version 2016-04-5
 */
public class Award extends DataEntity<Award>{
	private static final long serialVersionUID = 1L;
	 /* 编号*/
	private String id; 
	 /*	用户编号*/
	private User user;  
	 /*	获得时间*/
	private Date gainDate; 
     /*	奖项*/
	private String awardsName; 
	 /*	级别*/
	private String levelOne;       
	 /*级别2*/
	private String levelTwo;         
	/*获得时间字符串*/
	private String gainDateString;
	
	public String getGainDateString() {
		this.gainDateString = DateUtils.formatDate(gainDate, Global.getConfig("web.dateFormat"));
		return this.gainDateString;
	}
	public void setGainDateString(String gainDateString) {
		this.gainDate=StringUtils.toDate(gainDateString, Global.getConfig("web.dateFormat"));
	}
	public String getAwardsName() {
		return awardsName;
	}
	public void setAwardsName(String awardsName) {
		this.awardsName = awardsName;
	}
	public String getLevelOne() {
		return levelOne;
	}
	public void setLevelOne(String levelOne) {
		this.levelOne = levelOne;
	}
	public String getLevelTwo() {
		return levelTwo;
	}
	public void setLevelTwo(String levelTwo) {
		this.levelTwo = levelTwo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public  String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
