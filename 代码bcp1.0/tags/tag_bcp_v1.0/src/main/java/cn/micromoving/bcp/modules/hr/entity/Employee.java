package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;
import java.util.List;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

import com.google.common.collect.Lists;

/**
 * 职员用户表 Entity
 * 
 * @author 职员用户表
 * @version 2016-06-03
 */

public class Employee extends DataEntity<Employee> {

	private static final long serialVersionUID = 1L;
	/* 姓名 */
	private String name;
	/* 用户 */
	private User user;
	/* 工号 */
	private String userNo;
	/* 部门 */
	private Office office;
	/* 性别 */
	private String gender;
	/* 出生日期 */
	private Date birthDate;
	/* 民族 */
	private String nation;
	/* 籍贯 */
	private String nativePlace;
	/* 出生地 */
	private String bornPlace;
	/* 证件类型 */
	private String papersType;
	/* 证件号码 */
	private String papersNumber;
	/* 户籍所在地 */
	private String placeDomicile;
	/* 婚姻状况 */
	private String maritalStatus;
	/* 政治面貌 */
	private String politicsStatus;
	/* 加入时间 */
	private Date addTime;
	/* 参加工作时间 */
	private Date inWorkDate;
	/* 到校时间 */
	private Date inSchoolDate;
	/* 正式聘任时间 */
	private Date engagingDate;
	/* 院龄（满） */
	private int schoolAgeFull;
	/* 院龄（年度） */
	private int schoolAge;
	/* 更改时间 */
	private Date updateDate;
	/* 就业类型 */
	private String jobType;
	/* 到校就业类型 */
	private String schoolJobType;
	/* 本人身份 */
	private String selfIdentity;
	/* 人员类型 */
	private String staffType;
	/* 编制情况 */
	private String establishmentSituation;
	/*是否享受国务院特殊津贴*/
	private String isSpecial ;
	/* 人事代理人员 */
	private String staffingAgent;
	/* 手机 */
	private String mobile;
	/* 办公电话 */
	private String phone;
	/* QQ */
	private String qq;
	/* 家庭住址 */
	private String familyAddress;
	/* 邮编 */
	private String postcode;
	/* 个人主页 */
	private String personalHomepage;
	/* 个人简介 */
	private String personalProfile;
	/* 学缘结构 */
	private String learningEdgeRafters;
	/* 在职状态 */
	private String incumbencyStatus;
	/* 差值 */
	private String differenceValue;
	/* 代理编号 */
	private String agentId;
	/* 转入时间 */
	private Date agentIntoDate;
	/* 独子到期时间 */
	private Date onlyChildEndDate;
	/* 是否兼职 */
	private String isPartTime;
	/* 出生日期字符串 */
	private String birthDateString;
	/* 加入时间字符串 */
	private String addTimeString;
	/* 参加工作时间字符串 */
	private String inWorkDateString;
	/* 到校时间字符串 */
	private String inSchoolDateString;
	/* 正式聘任时间字符串 */
	private String engagingDateString;
	/* 更改时间字符串 */
	private String updateDateString;
	/* 转入时间字符串 */
	private String agentIntoDateString;
	/* 独子到期时间字符串 */
	private String onlyChildEndDateString;
	/* 勾选的导出项 */
	private List<String> selectedItem = Lists.newArrayList();
	/* 勾选的导入项 */
	private String selectedImportItem;
	/* 要下载的模板项 */
	private String selectedImportTemplateItem;
	/* 邮箱 */
	private String email;

	public Employee() {
		super();
		office = new Office();
		user = new User();
	}

	public Employee(String id) {
		super(id);
	}

	public String getSelectedImportItem() {
		return selectedImportItem;
	}

	public void setSelectedImportItem(String selectedImportItem) {
		this.selectedImportItem = selectedImportItem;
	}

	public String getSelectedImportTemplateItem() {
		return selectedImportTemplateItem;
	}

	public void setSelectedImportTemplateItem(String selectedImportTemplateItem) {
		this.selectedImportTemplateItem = selectedImportTemplateItem;
	}

	public List<String> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<String> selectedItem) {
		this.selectedItem = selectedItem;
	}

	@ExcelField(title = "工号", type = 0, align = 2, sort = 10)
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	@ExcelField(title = "姓名", type = 0, align = 2, sort = 20, value = "user.name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ExcelField(title = "性别", type = 0, align = 2, sort = 30, dictType = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@ExcelField(title = "出生日期", type = 0, align = 2, sort = 40)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@ExcelField(title = "民族", type = 0, align = 2, sort = 50, dictType = "nation")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@ExcelField(title = "籍贯", type = 0, align = 2, sort = 60)
	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	@ExcelField(title = "出生地", type = 0, align = 2, sort = 70)
	public String getBornPlace() {
		return bornPlace;
	}

	public void setBornPlace(String bornPlace) {
		this.bornPlace = bornPlace;
	}

	@ExcelField(title = "证件类型", type = 0, align = 2, sort = 80, dictType = "papers_type")
	public String getPapersType() {
		return papersType;
	}

	public void setPapersType(String papersType) {
		this.papersType = papersType;
	}

	@ExcelField(title = "证件号码", type = 0, align = 2, sort = 90)
	public String getPapersNumber() {
		return papersNumber;
	}

	public void setPapersNumber(String papersNumber) {
		this.papersNumber = papersNumber;
	}

	@ExcelField(title = "户籍所在地", type = 0, align = 2, sort = 100)
	public String getPlaceDomicile() {
		return placeDomicile;
	}

	public void setPlaceDomicile(String placeDomicile) {
		this.placeDomicile = placeDomicile;
	}

	@ExcelField(title = "婚姻状况", type = 0, align = 2, sort = 110, dictType = "marital_status")
	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@ExcelField(title = "政治面貌", type = 0, align = 2, sort = 120, dictType = "politics_status")
	public String getPoliticsStatus() {
		return politicsStatus;
	}

	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}

	@ExcelField(title = "加入时间", type = 0, align = 2, sort = 130)
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@ExcelField(title = "参加工作时间", type = 0, align = 2, sort = 140)
	public Date getInWorkDate() {
		return inWorkDate;
	}

	public void setInWorkDate(Date inWorkDate) {
		this.inWorkDate = inWorkDate;
	}

	@ExcelField(title = "到校时间", type = 0, align = 2, sort = 150)
	public Date getInSchoolDate() {
		return inSchoolDate;
	}

	public void setInSchoolDate(Date inSchoolDate) {
		this.inSchoolDate = inSchoolDate;
	}

	@ExcelField(title = "正式聘任时间", type = 0, align = 2, sort = 160)
	public Date getEngagingDate() {
		return engagingDate;
	}

	public void setEngagingDate(Date engagingDate) {
		this.engagingDate = engagingDate;
	}

	@ExcelField(title = "院龄（满）", type = 1, align = 2, sort = 170)
	public int getSchoolAgeFull() {
		return schoolAgeFull;
	}

	public void setSchoolAgeFull(int schoolAgeFull) {
		this.schoolAgeFull = schoolAgeFull;
	}

	@ExcelField(title = "院龄（年度）", type = 1, align = 2, sort = 180)
	public int getSchoolAge() {
		return schoolAge;
	}

	public void setSchoolAge(int schoolAge) {
		this.schoolAge = schoolAge;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@ExcelField(title = "就业类型", type = 0, align = 2, sort = 190, dictType = "job_type")
	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	@ExcelField(title = "到校就业类型", type = 0, align = 2, sort = 200, dictType = "school_job_type")
	public String getSchoolJobType() {
		return schoolJobType;
	}

	public void setSchoolJobType(String schoolJobType) {
		this.schoolJobType = schoolJobType;
	}

	@ExcelField(title = "本人身份", type = 0, align = 2, sort = 210, dictType = "self_identity")
	public String getSelfIdentity() {
		return selfIdentity;
	}

	public void setSelfIdentity(String selfIdentity) {
		this.selfIdentity = selfIdentity;
	}

	@ExcelField(title = "人员类型", type = 0, align = 2, sort = 220, dictType = "staff_type")
	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	@ExcelField(title = "编制情况", type = 0, align = 2, sort = 230, dictType = "establishment_situation")
	public String getEstablishmentSituation() {
		return establishmentSituation;
	}

	public void setEstablishmentSituation(String establishmentSituation) {
		this.establishmentSituation = establishmentSituation;
	}

	@ExcelField(title = "人事代理人员", type = 0, align = 2, sort = 240, dictType = "staffing_agent")
	public String getStaffingAgent() {
		return staffingAgent;
	}

	public void setStaffingAgent(String staffingAgent) {
		this.staffingAgent = staffingAgent;
	}

	@ExcelField(title = "手机", type = 0, align = 2, sort = 250)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ExcelField(title = "办公电话", type = 0, align = 2, sort = 260)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@ExcelField(title = "QQ", type = 0, align = 2, sort = 270)
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@ExcelField(title = "家庭住址", type = 0, align = 2, sort = 280)
	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	@ExcelField(title = "邮编", type = 0, align = 2, sort = 290)
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPersonalHomepage() {
		return personalHomepage;
	}

	public void setPersonalHomepage(String personalHomepage) {
		this.personalHomepage = personalHomepage;
	}

	public String getPersonalProfile() {
		return personalProfile;
	}

	@ExcelField(title = "学缘结构", type = 0, align = 2, sort = 300, dictType = "learning_edge_rafters")
	public String getLearningEdgeRafters() {
		return learningEdgeRafters;
	}

	public void setLearningEdgeRafters(String learningEdgeRafters) {
		this.learningEdgeRafters = learningEdgeRafters;
	}

	@ExcelField(title = "在职状态", type = 0, align = 2, sort = 310, dictType = "incumbency_status")
	public String getIncumbencyStatus() {
		return incumbencyStatus;
	}

	public void setIncumbencyStatus(String incumbencyStatus) {
		this.incumbencyStatus = incumbencyStatus;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Date getAgentIntoDate() {
		return agentIntoDate;
	}

	public String getIsPartTime() {
		return isPartTime;
	}

	public void setIsPartTime(String isPartTime) {
		this.isPartTime = isPartTime;
	}

	public void setAgentIntoDate(Date agentIntoDate) {
		this.agentIntoDate = agentIntoDate;
	}

	@ExcelField(title = "部门", type = 0, align = 2, sort = 19)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getDifferenceValue() {
		return differenceValue;
	}

	public void setDifferenceValue(String differenceValue) {
		this.differenceValue = differenceValue;
	}

	public void setPersonalProfile(String personalProfile) {
		this.personalProfile = personalProfile;
	}

	public String getAddTimeString() {
		this.addTimeString = DateUtils.formatDate(addTime,
				Global.getConfig("web.dateFormat"));
		return this.addTimeString;
	}

	public void setAddTimeString(String addTimeString) {
		this.addTime = StringUtils.toDate(addTimeString,
				Global.getConfig("web.dateFormat"));
	}

	public String getBirthDateString() {
		this.birthDateString = DateUtils.formatDate(birthDate,
				Global.getConfig("web.dateFormat"));
		return this.birthDateString;
	}

	public void setBirthDateString(String birthDateString) {
		this.birthDate = StringUtils.toDate(birthDateString,
				Global.getConfig("web.dateFormat"));
	}

	public String getInWorkDateString() {
		this.inWorkDateString = DateUtils.formatDate(inWorkDate,
				Global.getConfig("web.dateFormat"));
		return this.inWorkDateString;
	}

	public void setInWorkDateString(String inWorkDateString) {
		this.inWorkDate = StringUtils.toDate(inWorkDateString,
				Global.getConfig("web.dateFormat"));
	}

	public String getInSchoolDateString() {
		this.inSchoolDateString = DateUtils.formatDate(inSchoolDate,
				Global.getConfig("web.dateFormat"));
		return this.inSchoolDateString;
	}

	public void setInSchoolDateString(String inSchoolDateString) {
		this.inSchoolDate = StringUtils.toDate(inSchoolDateString,
				Global.getConfig("web.dateFormat"));
	}

	public String getEngagingDateString() {
		this.engagingDateString = DateUtils.formatDate(engagingDate,
				Global.getConfig("web.dateFormat"));
		return this.engagingDateString;
	}

	public void setEngagingDateString(String engagingDateString) {
		this.engagingDate = StringUtils.toDate(engagingDateString,
				Global.getConfig("web.dateFormat"));
	}

	public String getUpdateDateString() {
		this.updateDateString = DateUtils.formatDate(updateDate,
				Global.getConfig("web.dateFormat"));
		return this.updateDateString;
	}

	public void setUpdateDateString(String updateDateString) {
		this.updateDate = StringUtils.toDate(updateDateString,
				Global.getConfig("web.dateFormat"));
	}

	public String getAgentIntoDateString() {
		this.agentIntoDateString = DateUtils.formatDate(agentIntoDate,
				Global.getConfig("web.dateFormat"));
		return this.agentIntoDateString;
	}

	public void setAgentIntoDateString(String agentIntoDateString) {
		this.agentIntoDate = StringUtils.toDate(agentIntoDateString,
				Global.getConfig("web.dateFormat"));
	}

	@ExcelField(title = "独子到期时间", type = 0, align = 2, sort = 311)
	public Date getOnlyChildEndDate() {
		return onlyChildEndDate;
	}

	public void setOnlyChildEndDate(Date onlyChildEndDate) {
		this.onlyChildEndDate = onlyChildEndDate;
	}

	public String getOnlyChildEndDateString() {
		this.onlyChildEndDateString = DateUtils.formatDate(onlyChildEndDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return this.onlyChildEndDateString;
	}

	public void setOnlyChildEndDateString(String onlyChildEndDateString) {
		this.onlyChildEndDate = StringUtils.toDate(onlyChildEndDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ExcelField(title = "邮箱", type = 0, align = 2, sort = 330,value="user.email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@ExcelField(title = "是否享受国务院特殊津贴", type = 0, align = 2, sort = 340,dictType="is_special")
    public String getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(String isSpecial) {
        this.isSpecial = isSpecial;
    }
	

}
