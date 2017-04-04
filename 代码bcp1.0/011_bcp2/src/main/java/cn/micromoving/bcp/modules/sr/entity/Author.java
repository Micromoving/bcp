package cn.micromoving.bcp.modules.sr.entity;

import cn.micromoving.bcp.modules.hr.entity.Employee;

/**
 * 作者表Entity
 * @author 作者表
 * @version 2016-07-15
 */
public class Author {
	private static final long serialVersionUID = 1L;
	/*用户编号*/
	private Employee employeeId;
	/*成果编号*/
	private Achievement achievementId;
	/*排名*/
	private String idRank;
	/*参与情况说明*/
	private String participationSituation;
	/*分值*/
	private Double score;
	public Employee getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}
	public Achievement getAchievementId() {
		return achievementId;
	}
	public void setAchievementId(Achievement achievementId) {
		this.achievementId = achievementId;
	}
	public String getIdRank() {
		return idRank;
	}
	public void setIdRank(String idRank) {
		this.idRank = idRank;
	}
	public String getParticipationSituation() {
		return participationSituation;
	}
	public void setParticipationSituation(String participationSituation) {
		this.participationSituation = participationSituation;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
}
   