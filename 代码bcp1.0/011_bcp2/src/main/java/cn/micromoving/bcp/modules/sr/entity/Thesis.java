package cn.micromoving.bcp.modules.sr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;


/**
 * 学术交流论文表	 Entity
 * @author 学术交流论文表	
 * @version 2016-04-5
 */

public class Thesis extends DataEntity<Thesis>{
	private static final long serialVersionUID = 1L;
	/*论文名称*/
	private String thesis;
	/*作者*/
	private String employeeId;
	/*发表时间*/
	private String publishDate;
	/*发表刊期*/
	private String publishIssue;
	/*学科分类*/
	private String subjectCategory;
	/*主办会议*/
	private String conference;
	/*会议地区*/
	private String conferencePlace;
	/*论文摘要*/
	private String thesisSummary;
	/*论文详细情况*/
	private String thesisCondition;
	/*备注*/
	private String comments;
	public String getThesis() {
		return thesis;
	}
	public void setThesis(String thesis) {
		this.thesis = thesis;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getPublishIssue() {
		return publishIssue;
	}
	public void setPublishIssue(String publishIssue) {
		this.publishIssue = publishIssue;
	}
	public String getSubjectCategory() {
		return subjectCategory;
	}
	public void setSubjectCategory(String subjectCategory) {
		this.subjectCategory = subjectCategory;
	}
	public String getConference() {
		return conference;
	}
	public void setConference(String conference) {
		this.conference = conference;
	}
	public String getConferencePlace() {
		return conferencePlace;
	}
	public void setConferencePlace(String conferencePlace) {
		this.conferencePlace = conferencePlace;
	}
	public String getThesisSummary() {
		return thesisSummary;
	}
	public void setThesisSummary(String thesisSummary) {
		this.thesisSummary = thesisSummary;
	}
	public String getThesisCondition() {
		return thesisCondition;
	}
	public void setThesisCondition(String thesisCondition) {
		this.thesisCondition = thesisCondition;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
