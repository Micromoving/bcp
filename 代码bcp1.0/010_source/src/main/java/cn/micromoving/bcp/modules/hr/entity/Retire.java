package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;
/**
 * 离退休表Entity
 * @author luyihao
 * @version 2016-06-01
 */



import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 离退休表Entity
 * @author luyihao
 * @version 2016-06-01
 */
public class Retire extends DataEntity<Retire>{
	
	private static final long serialVersionUID = 1L;
	 /*编号*/
	private String id;       
	 /*用户编号*/
	private User user;
    /* 部门 */
    private Office office;
    /*离退休人员联系方式*/
    private RetireContact retireContact;
	/*性别*/
	private String gender;
	/*民族*/
	private String nation;
	/*籍贯*/
	private String nativePlace;
	/*出生年月*/
	private Date birthDate;
	/*类别*/
	private String retireType;
	/*离退休时职务*/
	private String retirePosition;
	/*离退休时岗位*/
	private String retireStation;
	/*享受待遇（针对离休）*/
	private String treatment;
	/*离退休时部门*/
	private String officeId;
	/*离退休时间*/
	private Date retireDate;
	/*参加工作时间*/
	private Date inWorkDate;
	/*政治面貌*/
	private String politicsStatus;
	/*入党时间*/
	private Date joinedDate;
	/*学历*/
	private String eduBackground;
	/*学位*/
	private String degree;
	/*职称*/
	private String techPosition;
	/*身体状况*/
	private String physicalCondition;
	/*自理能力*/
	private String selfcareAbility;
	/*独居*/
	private String islivingAlong;
	/*配偶状况*/
	private String spouseStatus;
	/*现住址*/
	private String address;
	/*详细地址*/
	private String amplyPlace;
	/*宿舍区*/
	private String dormArea;
	/*去世时间*/
	private Date dieDate;
	/*删除标记*/
	private String delFlag;
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public Date getInWorkDate() {
		return inWorkDate;
	}
	public void setInWorkDate(Date inWorkDate) {
		this.inWorkDate = inWorkDate;
	}
	public String getPoliticsStatus() {
		return politicsStatus;
	}
	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}
	public String getEduBackground() {
		return eduBackground;
	}
	public void setEduBackground(String eduBackground) {
		this.eduBackground = eduBackground;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getSelfcareAbility() {
		return selfcareAbility;
	}
	public void setSelfcareAbility(String selfcareAbility) {
		this.selfcareAbility = selfcareAbility;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRetirePosition() {
		return retirePosition;
	}
	public void setRetirePosition(String retirePosition) {
		this.retirePosition = retirePosition;
	}
	public String getRetireStation() {
		return retireStation;
	}
	public void setRetireStation(String retireStation) {
		this.retireStation = retireStation;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public Date getRetireDate() {
		return retireDate;
	}
	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
	}
	public Date getJoinDate() {
		return joinedDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinedDate = joinDate;
	}
	public String getTechPosition() {
		return techPosition;
	}
	public void setTechPosition(String techPosition) {
		this.techPosition = techPosition;
	}
	public String getPhysicalCondition() {
		return physicalCondition;
	}
	public void setPhysicalCondition(String physicalCondition) {
		this.physicalCondition = physicalCondition;
	}
	public String getIslivingAlong() {
		return islivingAlong;
	}
	public void setIslivingAlong(String islivingAlong) {
		this.islivingAlong = islivingAlong;
	}
	public String getSpouseStatus() {
		return spouseStatus;
	}
	public void setSpouseStatus(String spouseStatus) {
		this.spouseStatus = spouseStatus;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAmplyPlace() {
		return amplyPlace;
	}
	public void setAmplyPlace(String amplyPlace) {
		this.amplyPlace = amplyPlace;
	}
	public String getDormArea() {
		return dormArea;
	}
	public void setDormArea(String dormArea) {
		this.dormArea = dormArea;
	}
	public Date getDieDate() {
		return dieDate;
	}
	public void setDieDate(Date dieDate) {
		this.dieDate = dieDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getRetireType() {
		return retireType;
	}
	public void setRetireType(String retireType) {
		this.retireType = retireType;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public RetireContact getRetireContact() {
		return retireContact;
	}
	public void setRetireContact(RetireContact retireContact) {
		this.retireContact = retireContact;
	}
	
}