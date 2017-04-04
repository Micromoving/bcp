package cn.micromoving.bcp.modules.sr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;

/**
 * 成果奖励表Entity
 * @author 成果奖励表
 * @version 2016-04-09
 */
public class AchievementAward extends DataEntity<AchievementAward>{
	
	private static final long serialVersionUID = 1L;
	/*成果编号*/
	private Achievement achievement;
	/*奖励名称*/
	private String rewardName;
	/*奖励时间*/
	private Date rewardDate;
	/*奖励等级*/
	private String rewardGrade;
	/*奖励级别*/
	private String rewardLevel;
	/*奖励批准号*/
	private String rewardNo;
	/*奖励单位*/
	private String rewardUnit;
	/*备注*/
	private String comments;
	
	public String getRewardName() {
		return rewardName;
	}
	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}
	public Date getRewardDate() {
		return rewardDate;
	}
	public void setRewardDate(Date rewardDate) {
		this.rewardDate = rewardDate;
	}
	public String getRewardGrade() {
		return rewardGrade;
	}
	public void setRewardGrade(String rewardGrade) {
		this.rewardGrade = rewardGrade;
	}
	public String getRewardLevel() {
		return rewardLevel;
	}
	public void setRewardLevel(String rewardLevel) {
		this.rewardLevel = rewardLevel;
	}
	public String getRewardNo() {
		return rewardNo;
	}
	public void setRewardNo(String rewardNo) {
		this.rewardNo = rewardNo;
	}
	public String getRewardUnit() {
		return rewardUnit;
	}
	public void setRewardUnit(String rewardUnit) {
		this.rewardUnit = rewardUnit;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Achievement getAchievement() {
		return achievement;
	}
	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}
	
	
}
	