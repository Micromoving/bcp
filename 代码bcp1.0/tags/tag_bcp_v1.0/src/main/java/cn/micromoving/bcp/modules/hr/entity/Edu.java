package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 教育经历表Entity
 * 
 * @author luyihao
 * @version 2016-06-01
 */
public class Edu extends DataEntity<Edu> {

	private static final long serialVersionUID = 1L;
	/* 职员编号 */
	private Employee employee;
	/**/
	private String userNo;
	/* 入学时间 */
	private Date startDate;
	/* 毕业时间 */
	private Date endDate;
	/* 学历 */
	private String eduBackground;
	/* 学历取得时间 */
	private Date eduBackgroundDate;
	/* 学位 */
	private String degree;
	/* 学位取得时间 */
	private Date getDegreeDate;
	/* 毕业院校 */
	private String graduateSchool;
	/* 专业 */
	private String specialty;
	/* 研究方向 */
	private String researchArea;
	/* 就读类型 */
	private String studyType;
	/* 是否海外 */
	private String isOverseas;
	/* 专业描述 */
	private String remarks;
	/* 用户 */
	private User user;
	/* 部门 */
	private Office office;
	/* 入学时间字符 */
	private String startDateString;
	/* 毕业时间 字符 */
	private String endDateString;
	/* 学历取得时间字符 */
	private String eduBackgroundDateString;
	/* 学位取得时间 字符 */
	private String getDegreeDateString;
	/* 备注 */
	private String comment;

	public Edu() {
		super();
	}

	public Edu(String id) {
		super(id);
	}

	public String getEduBackgroundDateString() {
		this.eduBackgroundDateString = DateUtils.formatDate(eduBackgroundDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return eduBackgroundDateString;
	}

	public void setEduBackgroundDateString(String eduBackgroundDateString) {
		this.eduBackgroundDate = StringUtils.toDate(eduBackgroundDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}

	public String getGetDegreeDateString() {
		this.getDegreeDateString = DateUtils.formatDate(getDegreeDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return getDegreeDateString;
	}

	public void setGetDegreeDateString(String getDegreeDateString) {
		this.getDegreeDate = StringUtils.toDate(getDegreeDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}

	public String getStartDateString() {
		this.startDateString = DateUtils.formatDate(startDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return this.startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDate = StringUtils.toDate(startDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}

	public String getEndDateString() {
		this.endDateString = DateUtils.formatDate(endDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return this.endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDate = StringUtils.toDate(endDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}

	@ExcelField(title = "入学时间", type = 0, align = 2, sort = 30)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@ExcelField(title = "毕业时间", type = 0, align = 2, sort = 40)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@ExcelField(title = "学历", type = 0, align = 2, sort = 50, dictType = "edu_background")
	public String getEduBackground() {
		return eduBackground;
	}

	public void setEduBackground(String eduBackground) {
		this.eduBackground = eduBackground;
	}

	@ExcelField(title = "学历取得时间", type = 0, align = 2, sort = 60)
	public Date getEduBackgroundDate() {
		return eduBackgroundDate;
	}

	public void setEduBackgroundDate(Date eduBackgroundDate) {
		this.eduBackgroundDate = eduBackgroundDate;
	}

	@ExcelField(title = "学位", type = 0, align = 2, sort = 70, dictType = "academic_degree")
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@ExcelField(title = "学位取得时间", type = 0, align = 2, sort = 80)
	public Date getGetDegreeDate() {
		return getDegreeDate;
	}

	public void setGetDegreeDate(Date getDegreeDate) {
		this.getDegreeDate = getDegreeDate;
	}

	@ExcelField(title = "毕业院校", type = 0, align = 2, sort = 90)
	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	@ExcelField(title = "专业", type = 0, align = 2, sort = 100)
	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@ExcelField(title = "研究方向", type = 0, align = 2, sort = 110)
	public String getResearchArea() {
		return researchArea;
	}

	public void setResearchArea(String researchArea) {
		this.researchArea = researchArea;
	}

	@ExcelField(title = "就读类型", type = 0, align = 2, sort = 120, dictType = "study_type")
	public String getStudyType() {
		return studyType;
	}

	@ExcelField(title = "姓名", type = 0, align = 2, sort = 20, value = "user.name")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setStudyType(String studyType) {
		this.studyType = studyType;
	}

	@ExcelField(title = "是否海外", type = 0, align = 2, sort = 130, dictType = "is_overseas")
	public String getIsOverseas() {
		return isOverseas;
	}

	public void setIsOverseas(String isOverseas) {
		this.isOverseas = isOverseas;
	}

	@ExcelField(title = "专业描述", type = 0, align = 2, sort = 140)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@ExcelField(title = "工号", type = 1, align = 2, sort = 10, value = "employee.userNo")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ExcelField(title = "工号", type = 2, align = 2, sort = 10)
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@ExcelField(title = "备注", type = 0, align = 2, sort = 150)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
