package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

/**
 * 教师用户表 Entity
 * 
 * @author 教师用户表
 * @version 2016-04-5
 */
public class Teacher extends DataEntity<Teacher> {

    private static final long serialVersionUID = 1L;

    /* 编号 */
    private String id;

    /* 用户 */
    private User user;

    /* 部门 */
    private Office office;

    /* 校区 */
    private Office company;

    /* 工号 */
    private String num;

    /* 姓名 */
    private String name;

    /* 性别 */
    private String gender;

    /* 出生日期 */
    private Date birthDate;

    /* 专业 */
    private String specialty;

    /* 最终学历 */
    private String endEduBackground;

    /* 最终学位 */
    private String academicDegree;

    /* 毕业院校 */
    private String graduateSchool;

    /* 研究方向 */
    private String researchArea;

    /* 职称 */
    private String professionalTitle;

    /* 职级类型 */
    private String professionalType;

    /* 职级级别 */
    private String professionalLevel;

    /* 获取职称时间 */
    private Date jobTitleDate;

    /* 参加工作日期 */
    private Date inWorkDate;

    /* 来校日期 */
    private Date inSchoolDate;

    /* 是否双师型 */
    private String dproTitled;

    /* 政治面貌 */
    private String politicsStatus;

    /* 证件类型 */
    private String papersType;

    /* 证件编号 */
    private String papersNumber;

    /* qq号 */
    private String qqId;

    /* 邮编 */
    private String postcode;

    /* 微信号 */
    private String wechatId;

    /* 家庭住址 */
    private String homeAdd;

    /* 邮箱 */
    private String email;

    /* 婚姻状况 */
    private String maritalStatus;

    /* 电话 */
    private String phone;

    /* 手机 */
    private String mobile;

    /* 用户头像 */
    private String photo;

    /* 个人简介 */
    private String resume;

    /* 个人主页 */
    private String homepage;

    /* 出生日期字符型 */
    private String birthDateString;

    /* 获取职称时间字符型 */
    private String jobTitleDateString;

    /* 参加工作日期字符型 */
    private String inWorkDateString;

    /* 来校日期字符型 */
    private String inSchoolDateString;

    /* 民族 */
    private String nation;

    /* 籍贯 */
    private String nativePlace;

    /* 职务 */
    private String duties;
    
    
    //根据部门信息，设置用户所属系部，教研室或者处室，科室。
    private String deptName;
    private String officeName;
    
    

    public Teacher() {
        super();
        this.office = new Office();
    }

    public Teacher(String id) {
        this();
        this.id = id;

    }

    public Teacher(Office office) {
        this();
        this.office = office;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ExcelField(title = "登录名（工号）", type = 0, align = 2, sort = 10)
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @ExcelField(title = "姓名", type = 2, align = 2, sort = 20)
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

    @ExcelField(title = "出生日期", type =2, align = 2, sort = 40)
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

    @ExcelField(title = "证件类型", type = 0, align = 2, sort = 70, dictType = "papers_type")
    public String getPapersType() {
        return papersType;
    }

    public void setPapersType(String papersType) {
        this.papersType = papersType;
    }

    @ExcelField(title = "证件编号", type = 0, align = 2, sort = 80)
    public String getPapersNumber() {
        return papersNumber;
    }

    public void setPapersNumber(String papersNumber) {
        this.papersNumber = papersNumber;
    }

    @ExcelField(title = "婚姻状况", type = 0, align = 2, sort = 90, dictType = "marital_status")
    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @ExcelField(title = "部门", type =2, align = 2, sort = 100)
    public Office getOffice() {
        return office;
    }
    @ExcelField(title = "院系（处室）", type = 1, align = 2, sort = 100)
    public String getDeptName(){
    	if(null!=this.office){
    		if("2".equals(this.office.getGrade())){
    			deptName= this.office.getName();
        	}else if("3".equals(this.office.getGrade())){
        		/*如果当前用户所属部门为3级部门，取得其对应的2级部门信息。*/
        		deptName = UserUtils.getOfficeById(this.office.getId()).getParent().getName();
        				
        	}
    	}
    	
    	return this.deptName;
    }
    @ExcelField(title = "教研组（科室）", type = 1, align = 2, sort = 105)
    
    public String getOfficeName(){
    	 /*设置三级部门名称*/
    	if(null!=this.office&&"3".equals(this.office.getGrade())){
    		officeName = this.office.getName();
    	}
    	return officeName;
    }

   

	public void setDeptName(String deptName) {
		
		this.deptName = deptName;
	}

	public void setOfficeName(String officeName) {
		
		this.officeName = officeName;
	}

	public void setOffice(Office office) {
        this.office = office;
       
        
    }

    @ExcelField(title = "所学专业", type = 0, align = 2, sort = 110)
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @ExcelField(title = "最终学历", type = 0, align = 2, sort = 120, dictType = "edu_background")
    public String getEndEduBackground() {
        return endEduBackground;
    }

    public void setEndEduBackground(String endEduBackground) {
        this.endEduBackground = endEduBackground;
    }

    @ExcelField(title = "最终学位", type = 0, align = 2, sort = 130, dictType = "academic_degree")
    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    @ExcelField(title = "毕业院校", type = 0, align = 2, sort = 140)
    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    @ExcelField(title = "研究方向", type = 0, align = 2, sort = 150)
    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    @ExcelField(title = "职务", type = 0, align = 2, sort = 160)
    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }

    @ExcelField(title = "职称", type = 0, align = 2, sort = 170, dictType = "professional_title")
    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    @ExcelField(title = "职级类型", type = 0, align = 2, sort = 180, dictType = "professional_type")
    public String getProfessionalType() {
        return professionalType;
    }

    public void setProfessionalType(String professionalType) {
        this.professionalType = professionalType;
    }

    @ExcelField(title = "职级级别", type = 0, align = 2, sort = 190, dictType = "professional_level")
    public String getProfessionalLevel() {
        return professionalLevel;
    }

    public void setProfessionalLevel(String professionalLevel) {
        this.professionalLevel = professionalLevel;
    }

    @ExcelField(title = "获取职称时间", type = 0, align = 2, sort = 200)
    public Date getJobTitleDate() {
        return jobTitleDate;
    }

    public void setJobTitleDate(Date jobTitleDate) {
        this.jobTitleDate = jobTitleDate;
    }

    @ExcelField(title = "参加工作日期", type = 0, align = 2, sort = 210)
    public Date getInWorkDate() {
        return inWorkDate;
    }

    public void setInWorkDate(Date inWorkDate) {
        this.inWorkDate = inWorkDate;
    }

    @ExcelField(title = "来校日期", type = 0, align = 2, sort = 220)
    public Date getInSchoolDate() {
        return inSchoolDate;
    }

    public void setInSchoolDate(Date inSchoolDate) {
        this.inSchoolDate = inSchoolDate;
    }

    @ExcelField(title = "是否双师型", type = 0, align = 2, sort = 230, dictType = "dpro_titled")
    public String getDproTitled() {
        return dproTitled;
    }

    public void setDproTitled(String dproTitled) {
        this.dproTitled = dproTitled;
    }

    @ExcelField(title = "政治面貌", type = 0, align = 2, sort = 240, dictType = "politics_status")
    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    @ExcelField(title = "邮箱", type = 0, align = 2, sort = 250)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ExcelField(title = "手机", type = 0, align = 2, sort = 260)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @ExcelField(title = "电话", type = 0, align = 2, sort = 270)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ExcelField(title = "qq号", type = 0, align = 2, sort = 280)
    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    @ExcelField(title = "微信号", type = 0, align = 2, sort = 290)
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @ExcelField(title = "家庭住址", type = 0, align = 2, sort = 300)
    public String getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    @ExcelField(title = "邮编", type = 0, align = 2, sort = 310)
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @ExcelField(title = "个人主页", type = 0, align = 2, sort = 320)
    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @ExcelField(title = "个人简介", type = 0, align = 2, sort = 330)
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @ExcelField(title = "出生日期", type = 1, align = 2, sort = 40)
    public String getBirthDateString() {
    	if(null==this.birthDate){
    		return "";
    	}
        this.birthDateString = DateUtils.formatDate(birthDate, Global.getConfig("web.dateFormatCHN"));
        return this.birthDateString;
    }

    public void setBirthDateString(String birthDateString) {
        this.birthDate = StringUtils.toDate(birthDateString, Global.getConfig("web.dateFormatCHN"));
    }

    public String getJobTitleDateString() {
        this.jobTitleDateString = DateUtils.formatDate(jobTitleDate, Global.getConfig("web.dateShortFormatCHN"));
        return this.jobTitleDateString;
    }

    public void setJobTitleDateString(String jobTitleDateString) {
        this.jobTitleDate = StringUtils.toDate(jobTitleDateString, Global.getConfig("web.dateShortFormatCHN"));
    }

    public String getInWorkDateString() {
        this.inWorkDateString = DateUtils.formatDate(inWorkDate, Global.getConfig("web.dateShortFormatCHN"));
        return this.inWorkDateString;
    }

    public void setInWorkDateString(String inWorkDateString) {
        this.inWorkDate = StringUtils.toDate(inWorkDateString, Global.getConfig("web.dateShortFormatCHN"));
    }

    public String getInSchoolDateString() {
        this.inSchoolDateString = DateUtils.formatDate(inSchoolDate, Global.getConfig("web.dateShortFormatCHN"));
        return this.inSchoolDateString;
    }

    public void setInSchoolDateString(String inSchoolDateString) {
        this.inSchoolDate = StringUtils.toDate(inSchoolDateString, Global.getConfig("web.dateShortFormatCHN"));
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @ExcelField(title = "姓名", type = 1, align = 2, sort = 20)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.name = user.getName();
    }

    public Office getCompany() {
        return company;
    }

    public void setCompany(Office company) {
        this.company = company;
    }

}
