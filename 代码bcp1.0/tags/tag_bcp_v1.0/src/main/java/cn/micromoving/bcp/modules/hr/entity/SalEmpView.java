package cn.micromoving.bcp.modules.hr.entity;



import java.util.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 视图Entity
 * @author 视图
 * @version 2016-04-09
 */
public class SalEmpView extends DataEntity<SalEmpView>{
	
	private static final long serialVersionUID = 1L;
	private User user;
	private Office office;
	
	private String loginName;
	/*姓名*/
	private String name;
	/*性别*/
	private String gender;
	/**
	 * <code>staffType人员类型</code> 的注释
	 */
	private String staffType;
	/*独子到期时间*/
	private Date onlyChildEndDate;
	/*在职状态*/
	private String incumbencyStatus ;
	/*到校时间*/
	private Date inSchoolDate;
	/*差值*/
	private String differenceValue;
	/*绩效档级*/
	private String proformanceLevel;
	/*职务*/
	private String duties;
	/*岗位类型*/
	private String postType;
	/*岗位等级*/
	private String postLevel;
	/*职级等级*/
	private String professionalLevel;
	/*职务等级*/
	private String dutiesLevel;
	/*新选拔中层干部试用期期限*/
	private Date tryOutDate;
	/*聘任情况*/
	private String engageSituation;
	/*聘任截止时间*/
	private Date endDate;
	/*职称类型*/
	private String maxTechPositionType;
	/*职称级别*/
	private String maxTechPositionLevel;
	/*最高学历*/
	private String maxEduBackground;
	/*最高学位*/
	private String maxDegree;
	/*部门编号*/
	private String officeId;
	/*机构名称*/
	private String officeName;
	/*是否脱产*/
	private String isOffJob;
	/*组合*/
	private String group;
	/* 编制情况 */
	private String establishmentSituation;
	/*是否享受国务院特殊津贴*/
	private String isSpecial ;
	
	
	public String getEstablishmentSituation() {
		return establishmentSituation;
	}
	public void setEstablishmentSituation(String establishmentSituation) {
		this.establishmentSituation = establishmentSituation;
	}
	public String getIsSpecial() {
		return isSpecial;
	}
	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ExcelField(title = "部门", type =1, align = 2, sort =30,value="office.name")
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	
	@ExcelField(title = "职员编号", type =0, align = 2, sort =10)
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@ExcelField(title = "姓名", type =1, align = 2, sort =20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Date getTryOutDate() {
		return tryOutDate;
	}
	public void setTryOutDate(Date tryOutDate) {
		this.tryOutDate = tryOutDate;
	}
	public Date getOnlyChildEndDate() {
		return onlyChildEndDate;
	}
	public void setOnlyChildEndDate(Date onlyChildEndDate) {
		this.onlyChildEndDate = onlyChildEndDate;
	}
	public String getIncumbencyStatus() {
		return incumbencyStatus;
	}
	public void setIncumbencyStatus(String incumbencyStatus) {
		this.incumbencyStatus = incumbencyStatus;
	}
	
	public Date getInSchoolDate() {
		return inSchoolDate==null?new Date():this.inSchoolDate;
	}
	
	public String getIsOffJob() {
		return isOffJob;
	}
	public void setIsOffJob(String isOffJob) {
		this.isOffJob = isOffJob;
	}
	public void setInSchoolDate(Date inSchoolDate) {
		this.inSchoolDate = inSchoolDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDifferenceValue() {
		return differenceValue;
	}
	public void setDifferenceValue(String differenceValue) {
		this.differenceValue = differenceValue;
	}

	public String getProformanceLevel() {
		return proformanceLevel;
	}
	public void setProformanceLevel(String proformanceLevel) {
		this.proformanceLevel = proformanceLevel;
	}
	public String getDuties() {
		return duties;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public String getPostLevel() {
		return postLevel;
	}
	public void setPostLevel(String postLevel) {
		this.postLevel = postLevel;
	}
	public String getProfessionalLevel() {
		return professionalLevel;
	}
	public void setProfessionalLevel(String professionalLevel) {
		this.professionalLevel = professionalLevel;
	}
	public String getDutiesLevel() {
		return dutiesLevel;
	}
	public void setDutiesLevel(String dutiesLevel) {
		this.dutiesLevel = dutiesLevel;
	}
	
	public String getEngageSituation() {
		return engageSituation;
	}
	public void setEngageSituation(String engageSituation) {
		this.engageSituation = engageSituation;
	}
	
	public String getMaxTechPositionType() {
		return maxTechPositionType;
	}
	public void setMaxTechPositionType(String maxTechPositionType) {
		this.maxTechPositionType = maxTechPositionType;
	}
	public String getMaxTechPositionLevel() {
		return maxTechPositionLevel;
	}
	public void setMaxTechPositionLevel(String maxTechPositionLevel) {
		this.maxTechPositionLevel = maxTechPositionLevel;
	}
	public String getMaxEduBackground() {
		return maxEduBackground;
	}
	public void setMaxEduBackground(String maxEduBackground) {
		this.maxEduBackground = maxEduBackground;
	}
	public String getMaxDegree() {
		return maxDegree;
	}
	public void setMaxDegree(String maxDegree) {
		this.maxDegree = maxDegree;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	
	public String getStaffType() {
        return staffType;
    }
    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }
    public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	@ExcelField(title = "工作量", type = 0, align = 2, sort = 40)
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	public Date getEndDate() {
		return endDate;
	}
   
	
}