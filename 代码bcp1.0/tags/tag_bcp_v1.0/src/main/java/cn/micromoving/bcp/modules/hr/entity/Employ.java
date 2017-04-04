package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.CommonConstant;

/**
 * 招聘表Entity
 * 
 * @author wangshuting
 * @version 2016-06-14
 */
public class Employ extends DataEntity<Employ> {

    private static final long serialVersionUID = 1L;

    /* 用户 */
    private User user;

    /* 申报岗位 */
    private String declarePositionNumber;

    /* 岗位类型 */
    private String postType;

    /* 姓名 */
    private String name;

    /* 身份证号 */
    private String IDCardNumber;

    /* 性别 */
    private String gender;

    /* 出生日期 */
    private Date birthDate;

    /* 出生年月字符串 */
    private String birthDateString;

    /* 户籍地 */
    private String placeDomicile;

    /* 政治面貌 */
    private String politicsStatus;

    /* 民族 */
    private String nation;

    /* 婚姻状况 */
    private String maritalStatus;

    /* 最高学历 */
    private String highestEduBackground;

    /* 最高学位 */
    private String highestDegree;

    /* 毕业院校 */
    private String highestGraduateSchool;

    /* 所学专业 */
    private String highestMajor;

    /* 最高学历开始学习时间 */
    private Date highestStartDate;

    /* 最高学历结束学习时间 */
    private Date highestEndDate;

    /* 最高学历开始学习时间字符串 */
    private String highestStartDateString;

    /* 最高学历结束学习时间字符串 */
    private String highestEndDateString;
    
    /* 最高学位开始学习时间 */
    private Date highestDegreeStartDate;

    /* 最高学位结束学习时间 */
    private Date highestDegreeEndDate;

    /* 最高学位开始学习时间字符串 */
    private String highestDegreeStartDateString;

    /* 最高学位结束学习时间字符串 */
    private String highestDegreeEndDateString;

    /* 第一学历 */
    private String firstEduBackground;

    /* 第一学位 */
    private String firstDegree;

    /* 毕业院校 */
    private String firstGraduateSchool;

    /* 所学专业 */
    private String firstMajor;

    /* 第一学历开始学习时间 */
    private Date firstStartDate;

    /* 第一学历结束学习时间 */
    private Date firstEndDate;

    /* 第一学历开始学习时间字符串 */
    private String firstStartDateString;

    /* 第一学历结束学习时间字符串 */
    private String firstEndDateString;
    
    /* 第一学位开始学习时间 */
    private Date firstDegreeStartDate;

    /* 第一学位结束学习时间 */
    private Date firstDegreeEndDate;

    /* 第一学位开始学习时间字符串 */
    private String firstDegreeStartDateString;

    /* 第一学位结束学习时间字符串 */
    private String firstDegreeEndDateString;

    /* 是否有工作经历 */
    private String isWorkExperience;

    /* 现工作单位 */
    private String workingUnit;

    /* 现工作岗位 */
    private String workingPost;

    /* 参加工作时间 */
    private Date inWorkDate;

    /* 参加工作时间历字符串 */
    private String inWorkDateString;

    /* 执（职）业资格证 */
    private String qualifications;

    /* 技术职称 */
    private String techPosition;

    /* 个人简介 */
    private String personalProfile;

    /* 家庭状况 */
    private String familyStatus;

    /* 审核意见 */
    private String comments;

    /* 审核是否通过 */
    private String auditingStatus;

    /* 移动电话 */
    private String mobile;

    /* 通讯地址 */
    private String correspondenceAddress;

    /* 电子邮箱 */
    private String email;

    /* 固定电话 */
    private String fixedPhone;

    /* 申请时间 */
    private Date applyDate;

    /* 更改时间 */
    private Date updateDate;

    /* 审核时间 */
    private Date auditDate;
    
    /*备注*/
    private String auditComments;

    public Employ() {
        super();
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
    
    public String getAuditComments() {
		return auditComments;
	}

	public void setAuditComments(String auditComments) {
		this.auditComments = auditComments;
	}

	public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Employ(String id) {
        super(id);
    }

    @ExcelField(title = "序号", type = 0, align = 2, sort = 10)
    public int getIndex() {
        return CommonConstant.EXPORT_INDEX_VALUE++;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ExcelField(title = "申报岗位", type = 0, align = 2, sort = 20, dictType = "declare_position_number")
    public String getDeclarePositionNumber() {
        return declarePositionNumber;
    }

    public void setDeclarePositionNumber(String declarePositionNumber) {
        this.declarePositionNumber = declarePositionNumber;
    }

    @ExcelField(title = "岗位类型", type = 0, align = 2, sort = 30, dictType = "post_type")
    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ExcelField(title = "姓名", type = 0, align = 2, sort = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title = "身份证号", type = 0, align = 2, sort = 50)
    public String getIDCardNumber() {
        return IDCardNumber;
    }

    public void setIDCardNumber(String iDCardNumber) {
        IDCardNumber = iDCardNumber;
    }

    @ExcelField(title = "性别", type = 0, align = 2, sort = 60, dictType = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @ExcelField(title = "出生日期", type = 0, align = 2, sort = 70)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDateString() {

        this.birthDateString = DateUtils.formatDate(birthDate, Global.getConfig("web.dateFormat"));
        return this.birthDateString;
    }

    public void setBirthDateString(String birthDateString) {
        this.birthDate = StringUtils.toDate(birthDateString, Global.getConfig("web.dateFormat"));
    }

    @ExcelField(title = "户籍地", type = 0, align = 2, sort = 90)
    public String getPlaceDomicile() {
        return placeDomicile;
    }

    public void setPlaceDomicile(String placeDomicile) {
        this.placeDomicile = placeDomicile;
    }


    @ExcelField(title = "政治面貌", type = 0, align = 2, sort = 110, dictType = "politics_status")
    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    @ExcelField(title = "民族", type = 0, align = 2, sort = 120, dictType = "nation")
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @ExcelField(title = "婚姻状况", type = 0, align = 2, sort = 130, dictType = "marital_status")
    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @ExcelField(title = "最高学历", type = 0, align = 2, sort = 140, dictType = "highest_edu_background")
    public String getHighestEduBackground() {
        return highestEduBackground;
    }

    public void setHighestEduBackground(String highestEduBackground) {
        this.highestEduBackground = highestEduBackground;
    }

    @ExcelField(title = "最高学位", type = 0, align = 2, sort = 150, dictType = "academic_degree")
    public String getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }
    @ExcelField(title = "毕业院校（最高）", type = 0, align = 2, sort = 151)
    public String getHighestGraduateSchool() {
        return highestGraduateSchool;
    }

    public void setHighestGraduateSchool(String highestGraduateSchool) {
        this.highestGraduateSchool = highestGraduateSchool;
    }
    @ExcelField(title = "所学专业（最高）", type = 0, align = 2, sort = 152)
    public String getHighestMajor() {
    	if(null==highestMajor){
    		return "无";
    	}
        return highestMajor;
    }

    public void setHighestMajor(String highestMajor) {
        this.highestMajor = highestMajor;
    }

    public Date getHighestStartDate() {
        return highestStartDate;
    }

    public void setHighestStartDate(Date highestStartDate) {
        this.highestStartDate = highestStartDate;
    }
    
    public Date getHighestEndDate() {
        return highestEndDate;
    }

    public void setHighestEndDate(Date highestEndDate) {
        this.highestEndDate = highestEndDate;
    }
    @ExcelField(title = "开始学习时间（最高）", type = 0, align = 2, sort = 153)
    public String getHighestStartDateString() {
        this.highestStartDateString = DateUtils
                .formatDate(highestStartDate, Global.getConfig("web.dateShortFormatCHN"));
        return this.highestStartDateString;
    }

    public void setHighestStartDateString(String highestStartDateString) {
        this.highestStartDate = StringUtils.toDate(highestStartDateString, Global.getConfig("web.dateShortFormatCHN"));
    }

    @ExcelField(title = "结束学习时间（最高）", type = 0, align = 2, sort = 154)
    public String getHighestEndDateString() {
        this.highestEndDateString = DateUtils.formatDate(highestEndDate, Global.getConfig("web.dateShortFormatCHN"));
        return this.highestEndDateString;
    }

    public void setHighestEndDateString(String highestEndDateString) {
        this.highestEndDate = StringUtils.toDate(highestEndDateString, Global.getConfig("web.dateShortFormatCHN"));
    }

    @ExcelField(title = "第一学历", type = 0, align = 2, sort = 200, dictType = "first_edu_background")
    public String getFirstEduBackground() {
        return firstEduBackground;
    }

    public void setFirstEduBackground(String firstEduBackground) {
        this.firstEduBackground = firstEduBackground;
    }

    @ExcelField(title = "第一学位", type = 0, align = 2, sort = 210, dictType = "academic_degree")
    public String getFirstDegree() {
        return firstDegree;
    }

    public void setFirstDegree(String firstDegree) {
        this.firstDegree = firstDegree;
    }

    @ExcelField(title = "毕业院校（第一）", type = 0, align = 2, sort = 220)
    public String getFirstGraduateSchool() {
        return firstGraduateSchool;
    }

    public void setFirstGraduateSchool(String firstGraduateSchool) {
        this.firstGraduateSchool = firstGraduateSchool;
    }

    @ExcelField(title = "所学专业（第一）", type = 0, align = 2, sort = 230)
    public String getFirstMajor() {
    	if(null==firstMajor){
    		return "无";
    	}
        return firstMajor;
    }

    public void setFirstMajor(String firstMajor) {
        this.firstMajor = firstMajor;
    }


    public Date getFirstStartDate() {
        return firstStartDate;
    }

    public void setFirstStartDate(Date firstStartDate) {
        this.firstStartDate = firstStartDate;
    }

    @ExcelField(title = "开始学习时间（第一）", type = 0, align = 2, sort = 240)
    public String getFirstStartDateString() {
        this.firstStartDateString = DateUtils.formatDate(firstStartDate, Global.getConfig("web.dateShortFormatCHN"));
        return this.firstStartDateString;
    }

    public void setFirstStartDateString(String firstStartDateString) {
        this.firstStartDate = StringUtils.toDate(firstStartDateString, Global.getConfig("web.dateShortFormatCHN"));
    }

    public Date getFirstEndDate() {
        return firstEndDate;
    }

    public void setFirstEndDate(Date firstEndDate) {
        this.firstEndDate = firstEndDate;
    }
    @ExcelField(title = "结束学习时间（第一）", type = 0, align = 2, sort = 250)
    public String getFirstEndDateString() {
        this.firstEndDateString = DateUtils.formatDate(firstEndDate, Global.getConfig("web.dateShortFormatCHN"));
        return this.firstEndDateString;
    }

    public void setFirstEndDateString(String firstEndDateString) {
        this.firstEndDate = StringUtils.toDate(firstEndDateString, Global.getConfig("web.dateShortFormatCHN"));
    }

    @ExcelField(title = "是否有工作经历", type = 0, align = 2, sort = 260,dictType = "yes_no")
    public String getIsWorkExperience() {
        return isWorkExperience;
    }

    public void setIsWorkExperience(String isWorkExperience) {
        this.isWorkExperience = isWorkExperience;
    }

    @ExcelField(title = "现工作单位", type = 0, align = 2, sort = 270)
    public String getWorkingUnit() {
        return workingUnit;
    }

    public void setWorkingUnit(String workingUnit) {
        this.workingUnit = workingUnit;
    }

    @ExcelField(title = "现工作岗位", type = 0, align = 2, sort = 280)
    public String getWorkingPost() {
        return workingPost;
    }

    public void setWorkingPost(String workingPost) {
        this.workingPost = workingPost;
    }

    public Date getInWorkDate() {
        return inWorkDate;
    }

    public void setInWorkDate(Date inWorkDate) {
        this.inWorkDate = inWorkDate;
    }

    @ExcelField(title = "参加工作时间", type = 0, align = 2, sort = 290)
    public String getInWorkDateString() {
        this.inWorkDateString = DateUtils.formatDate(inWorkDate, Global.getConfig("web.dateShortFormatCHN"));
        return this.inWorkDateString;
    }

    public void setInWorkDateString(String inWorkDateString) {
        this.inWorkDate = StringUtils.toDate(inWorkDateString, Global.getConfig("web.dateShortFormatCHN"));
    }

    @ExcelField(title = "执（职）业资格证", type = 0, align = 2, sort = 300)
    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    @ExcelField(title = "技术职称", type = 0, align = 2, sort = 310)
    public String getTechPosition() {
        return techPosition;
    }

    public void setTechPosition(String techPosition) {
        this.techPosition = techPosition;
    }

    @ExcelField(title = "个人简介", type = 0, align = 2, sort = 400)
    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    @ExcelField(title = "家庭状况", type = 0, align = 2, sort = 330)
    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    @ExcelField(title = "审核是否通过", type = 0, align = 2, sort = 340, dictType = "auditing_status")
    public String getAuditingStatus() {
        return auditingStatus;
    }

    public void setAuditingStatus(String auditingStatus) {
        this.auditingStatus = auditingStatus;
    }

    @ExcelField(title = "审核意见", type = 0, align = 2, sort = 410)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @ExcelField(title = "移动电话", type = 0, align = 2, sort = 360)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @ExcelField(title = "通讯地址", type = 0, align = 2, sort = 370)
    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    @ExcelField(title = "电子邮箱", type = 0, align = 2, sort = 380)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ExcelField(title = "固定电话", type = 0, align = 2, sort = 390)
    public String getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(String fixedPhone) {
        this.fixedPhone = fixedPhone;
    }

	public Date getHighestDegreeStartDate() {
		return highestDegreeStartDate;
	}

	public void setHighestDegreeStartDate(Date highestDegreeStartDate) {
		this.highestDegreeStartDate = highestDegreeStartDate;
	}

	public Date getHighestDegreeEndDate() {
		return highestDegreeEndDate;
	}

	public void setHighestDegreeEndDate(Date highestDegreeEndDate) {
		this.highestDegreeEndDate = highestDegreeEndDate;
	}

	public Date getFirstDegreeStartDate() {
		return firstDegreeStartDate;
	}

	public void setFirstDegreeStartDate(Date firstDegreeStartDate) {
		this.firstDegreeStartDate = firstDegreeStartDate;
	}

	public Date getFirstDegreeEndDate() {
		return firstDegreeEndDate;
	}

	public void setFirstDegreeEndDate(Date firstDegreeEndDate) {
		this.firstDegreeEndDate = firstDegreeEndDate;
	}
    
	  public String getHighestDegreeStartDateString() {
	        this.highestDegreeStartDateString = DateUtils
	                .formatDate(highestDegreeStartDate, Global.getConfig("web.dateShortFormatCHN"));
	        return this.highestDegreeStartDateString;
	    }

	    public void setHighestDegreeStartDateString(String highestDegreeStartDateString) {
	        this.highestDegreeStartDate = StringUtils.toDate(highestDegreeStartDateString, Global.getConfig("web.dateShortFormatCHN"));
	    }
	    
	    public String getHighestDegreeEndDateString() {
	        this.highestDegreeEndDateString = DateUtils
	                .formatDate(highestDegreeEndDate, Global.getConfig("web.dateShortFormatCHN"));
	        return this.highestDegreeEndDateString;
	    }

	    public void setHighestDegreeEndDateString(String highestDegreeEndDateString) {
	        this.highestDegreeEndDate = StringUtils.toDate(highestDegreeEndDateString, Global.getConfig("web.dateShortFormatCHN"));
	    }
	    
	    public String getFirstDegreeStartDateString() {
	        this.firstDegreeStartDateString = DateUtils
	                .formatDate(firstDegreeStartDate, Global.getConfig("web.dateShortFormatCHN"));
	        return this.firstDegreeStartDateString;
	    }

	    public void setFirstDegreeStartDateString(String firstDegreeStartDateString) {
	        this.firstDegreeStartDate = StringUtils.toDate(firstDegreeStartDateString, Global.getConfig("web.dateShortFormatCHN"));
	    }
	    
	    public String getFirstDegreeEndDateString() {
	        this.firstDegreeEndDateString = DateUtils
	                .formatDate(firstDegreeEndDate, Global.getConfig("web.dateShortFormatCHN"));
	        return this.firstDegreeEndDateString;
	    }

	    public void setFirstDegreeEndDateString(String firstDegreeEndDateString) {
	        this.firstDegreeEndDate = StringUtils.toDate(firstDegreeEndDateString, Global.getConfig("web.dateShortFormatCHN"));
	    }

}
