package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;



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
	/*曾用名*/
    private String nameUsedBefore;
    /*国籍/地区*/
    private String nationalityRegion;
	/* 性别 */
	private String gender;
	/* 出生日期 */
	private Date birthDate;
	/* 民族 */
	private String nation;
	/* 籍贯 */
	private String nativePlace;
	/* 出生地 (行政区划代码)*/
	private String birthPlace;
	/* 证件类型 */
	private String identityDocumentType;
	/* 证件号码 */
	private String idNumber;
	/* 户籍所在地 */
	private String placeDomicile;
	/* 婚姻状况 */
	private String maritalStatus;
	/* 政治面貌 */
	private String politicalLandscape;
	/*健康状况*/
	private String health;
	/*参加工作年月*/
	private Date inWorkDate;
	/*进本校年月*/
	private Date inSchoolDate;
	/*教职工来源*/
	private String staffSource;
	/*教职工类别*/
	private String staffType;
	/*编制情况*/
	private String preparationSituation;
	/*是否在编*/
	private String whetherInSeries;
	/*用人形式*/
    private String theHumanForm;
    /*人员状态*/
    private String personnelStatus;
    /*签订合同情况*/
    private String signTheContract;
    /*学缘结构*/
    private String learningEdgeStructure;
    /*信息技术应用能力*/
    private String informationTechnology;
    /*是否“双师型”教师*/
    private String whetherDoubleTeacher;
    /*是否具备职业技能等级证书 */
    private String certificateOfVocational;
    /*是否是特级教师 */
    private String specialTeacher;
    /*企业工作(实践)时长 */
    private String enterpriseWork;
    /*正式聘任时间*/
    private Date engagingDate;
    /*就业类型*/
    private String employmentType;
    /*到校就业类型*/
    private String schoolEmployment;
    /*本人身份*/
    private String myIdentity;
    /*人事代理人员*/
    private String personnelAgency;
    /*手机*/
    private String mobilePhone;
    /*办公电话*/
    private String officeTelephone;
    /*QQ*/
    private String QQ;
    /*家庭住址*/
    private String homeAddress;
    /*邮编*/
    private String zipCode;
    /*在职状态*/
    private String incumbencyStatus;
    /*独子到期时间*/
    private Date onlyChildEndDate;
    /*加入时间*/
    private Date addDate;
    /*人员类型*/
    private String PersonnelType;
    /*个人简介*/
    private String personalProfile;
    /*是否享受国务院特殊津贴*/
    private String isSpecial;
    /*出生日期字符串*/
    private String birthDateString;
    /*参加工作年月字符串*/
	private String inWorkDateString;
	/*进本校年月字符串*/
	private String inSchoolDateString;
	/*正式聘任时间字符串*/
    private String engagingDateString;
    /*独子到期时间字符串*/
    private String onlyChildEndDateString;
    /*加入时间字符串*/
    private String addDateString;

	
    
    public String getAddDateString() {
		this.addDateString = DateUtils.formatDate(addDate,
				Global.getConfig("web.dateFormat"));
		return this.addDateString;
	}

	public void setAddDateString(String addDateString) {
		this.addDate = StringUtils.toDate(addDateString,
				Global.getConfig("web.dateFormat"));
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
    public String getEngagingDateString() {
		this.engagingDateString = DateUtils.formatDate(engagingDate,
				Global.getConfig("web.dateFormat"));
		return this.engagingDateString;
	}

	public void setEngagingDateString(String engagingDateString) {
		this.engagingDate = StringUtils.toDate(engagingDateString,
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
	public String getInWorkDateString() {
		this.inWorkDateString = DateUtils.formatDate(inWorkDate,
				Global.getConfig("web.dateFormat"));
		return this.inWorkDateString;
	}

	public void setInWorkDateString(String inWorkDateString) {
		this.inWorkDate = StringUtils.toDate(inWorkDateString,
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
    public Employee() {
        // TODO Auto-generated constructor stub
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
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
    public String getNameUsedBefore() {
        return nameUsedBefore;
    }
    public void setNameUsedBefore(String nameUsedBefore) {
        this.nameUsedBefore = nameUsedBefore;
    }
    public String getNationalityRegion() {
        return nationalityRegion;
    }
    public void setNationalityRegion(String nationalityRegion) {
        this.nationalityRegion = nationalityRegion;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public String getNation() {
        return nation;
    }
    public void setNation(String nation) {
        this.nation = nation;
    }
    public String getNativePlace() {
        return nativePlace;
    }
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }
    public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

    public String getIdentityDocumentType() {
        return identityDocumentType;
    }
    public void setIdentityDocumentType(String identityDocumentType) {
        this.identityDocumentType = identityDocumentType;
    }
   
    public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getPlaceDomicile() {
        return placeDomicile;
    }
    public void setPlaceDomicile(String placeDomicile) {
        this.placeDomicile = placeDomicile;
    }
    public String getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public String getHealth() {
        return health;
    }
    public void setHealth(String health) {
        this.health = health;
    }
    public Date getInWorkDate() {
        return inWorkDate;
    }
    public void setInWorkDate(Date inWorkDate) {
        this.inWorkDate = inWorkDate;
    }
    public Date getInSchoolDate() {
        return inSchoolDate;
    }
    public void setInSchoolDate(Date inSchoolDate) {
        this.inSchoolDate = inSchoolDate;
    }
    public String getStaffSource() {
        return staffSource;
    }
    public void setStaffSource(String staffSource) {
        this.staffSource = staffSource;
    }
    public String getStaffType() {
        return staffType;
    }
    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }
    public String getPreparationSituation() {
        return preparationSituation;
    }
    public void setPreparationSituation(String preparationSituation) {
        this.preparationSituation = preparationSituation;
    }
    public String getWhetherInSeries() {
        return whetherInSeries;
    }
    public void setWhetherInSeries(String whetherInSeries) {
        this.whetherInSeries = whetherInSeries;
    }
    public String getTheHumanForm() {
        return theHumanForm;
    }
    public void setTheHumanForm(String theHumanForm) {
        this.theHumanForm = theHumanForm;
    }
    public String getPersonnelStatus() {
        return personnelStatus;
    }
    public void setPersonnelStatus(String personnelStatus) {
        this.personnelStatus = personnelStatus;
    }
    public String getSignTheContract() {
        return signTheContract;
    }
    public void setSignTheContract(String signTheContract) {
        this.signTheContract = signTheContract;
    }
    public String getLearningEdgeStructure() {
        return learningEdgeStructure;
    }
    public void setLearningEdgeStructure(String learningEdgeStructure) {
        this.learningEdgeStructure = learningEdgeStructure;
    }
    
    public String getInformationTechnology() {
		return informationTechnology;
	}

	public void setInformationTechnology(String informationTechnology) {
		this.informationTechnology = informationTechnology;
	}

	public String getWhetherDoubleTeacher() {
        return whetherDoubleTeacher;
    }
    public void setWhetherDoubleTeacher(String whetherDoubleTeacher) {
        this.whetherDoubleTeacher = whetherDoubleTeacher;
    }
    public String getCertificateOfVocational() {
        return certificateOfVocational;
    }
    public void setCertificateOfVocational(String certificateOfVocational) {
        this.certificateOfVocational = certificateOfVocational;
    }
    public String getSpecialTeacher() {
        return specialTeacher;
    }
    public void setSpecialTeacher(String specialTeacher) {
        this.specialTeacher = specialTeacher;
    }
    public String getEnterpriseWork() {
        return enterpriseWork;
    }
    public void setEnterpriseWork(String enterpriseWork) {
        this.enterpriseWork = enterpriseWork;
    }
    public Date getEngagingDate() {
        return engagingDate;
    }
    public void setEngagingDate(Date engagingDate) {
        this.engagingDate = engagingDate;
    }
    public String getEmploymentType() {
        return employmentType;
    }
    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }
    public String getSchoolEmployment() {
        return schoolEmployment;
    }
    public void setSchoolEmployment(String schoolEmployment) {
        this.schoolEmployment = schoolEmployment;
    }
    public String getMyIdentity() {
        return myIdentity;
    }
    public void setMyIdentity(String myIdentity) {
        this.myIdentity = myIdentity;
    }
    public String getPersonnelAgency() {
        return personnelAgency;
    }
    public void setPersonnelAgency(String personnelAgency) {
        this.personnelAgency = personnelAgency;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getOfficeTelephone() {
        return officeTelephone;
    }
    public void setOfficeTelephone(String officeTelephone) {
        this.officeTelephone = officeTelephone;
    }
    public String getQQ() {
        return QQ;
    }
    public void setQQ(String qQ) {
        QQ = qQ;
    }
    public String getHomeAddress() {
        return homeAddress;
    }
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getIncumbencyStatus() {
        return incumbencyStatus;
    }
    public void setIncumbencyStatus(String incumbencyStatus) {
        this.incumbencyStatus = incumbencyStatus;
    }
    public Date getOnlyChildEndDate() {
        return onlyChildEndDate;
    }
    public void setOnlyChildEndDate(Date onlyChildEndDate) {
        this.onlyChildEndDate = onlyChildEndDate;
    }
    public Date getAddDate() {
        return addDate;
    }
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
    
	public String getPersonnelType() {
		return PersonnelType;
	}
	public void setPersonnelType(String personnelType) {
		PersonnelType = personnelType;
	}
	public String getPersonalProfile() {
		return personalProfile;
	}
	public void setPersonalProfile(String personalProfile) {
		this.personalProfile = personalProfile;
	}
	public String getPoliticalLandscape() {
		return politicalLandscape;
	}
	public void setPoliticalLandscape(String politicalLandscape) {
		this.politicalLandscape = politicalLandscape;
	}

	public String getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}
	
}
