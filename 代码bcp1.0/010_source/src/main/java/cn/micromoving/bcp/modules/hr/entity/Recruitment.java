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
 * 岗位聘任管理表Entity
 * 
 * @author
 * @version 2016-06-01
 */
public class Recruitment extends DataEntity<Recruitment> {
	private static final long serialVersionUID = 1L;

	/* 用户编号 */
	private User user;
	/* 职员编号 */
	private Employee employee;
	/**/
	private String userNo;
	/* 部门 */
	private Office office;
	/* 职务 */
	private String duties;
	/* 岗位类型 */
	private String postType;
	/* 岗位等级 */
	private String postLevel;
	/* 职级等级 */
	private String professionalLevel;
	/* 职务等级 */
	private String dutiesLevel;
	/* 新选拔中层干部试用期期限 */
	private Date tryOutDate;
	/* 聘任情况 */
	private String engageSituation;
	/* 聘任开始时间 */
	private Date startDate;
	/* 聘任截止时间 */
	private Date endDate;
	/* 聘任开始时间字符串 */
	private String startDateString;
	/* 聘任截止时间字符串 */
	private String endDateString;
	/* 更改时间 */
	private Date updateDate;
	/* 是否签订聘用合同 */
	private String isSignContract;
	/* 新选拔中层干部试用期期限字符串 */
	private String tryOutDateString;
	/* 备注(导出使用) */
	private String remarks;

	public Recruitment() {
		super();
		office = new Office();
		user = new User();
	}

	public Recruitment(String id) {
		super(id);
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@ExcelField(title = "姓名", type = 0, align = 2, sort = 20, value = "user.name")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ExcelField(title = "部门", type = 0, align = 2, sort = 30, value = "office.name")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@ExcelField(title = "职务", type = 0, align = 2, sort = 40,dictType="post_name")
	public String getDuties() {
		return duties;
	}

	public void setDuties(String duties) {
		this.duties = duties;
	}

	@ExcelField(title = "岗位类型", type = 0, align = 2, sort = 50, dictType = "post_type")
	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	@ExcelField(title = "职务等级", type = 0, align = 2, sort = 80, dictType = "duties_level")
	public String getDutiesLevel() {
		return dutiesLevel;
	}

	public void setDutiesLevel(String dutiesLevel) {
		this.dutiesLevel = dutiesLevel;
	}

	@ExcelField(title = "职级等级", type = 0, align = 2, sort = 70, dictType = "professional_level")
	public String getProfessionalLevel() {
		return professionalLevel;
	}

	public void setProfessionalLevel(String professionalLevel) {
		this.professionalLevel = professionalLevel;
	}

	@ExcelField(title = "岗位等级", type = 0, align = 2, sort = 60, dictType = "post_level")
	public String getPostLevel() {
		return postLevel;
	}

	public void setPostLevel(String postLevel) {
		this.postLevel = postLevel;
	}

	@ExcelField(title = "聘任情况", type = 0, align = 2, sort = 100, dictType = "engage_situation")
	public String getEngageSituation() {
		return engageSituation;
	}

	public void setEngageSituation(String engageSituation) {
		this.engageSituation = engageSituation;
	}

	@ExcelField(title = "聘任开始时间", type = 0, align = 2, sort = 110)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@ExcelField(title = "聘任截止时间", type = 0, align = 2, sort = 120)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@ExcelField(title = "是否签订聘用合同", type = 0, align = 2, sort = 130, dictType = "is_sign_contract")
	public String getIsSignContract() {
		return isSignContract;
	}

	public void setIsSignContract(String isSignContract) {
		this.isSignContract = isSignContract;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getStartDateString() {
		this.startDateString = DateUtils.formatDate(startDate,
				Global.getConfig("web.dateFormat"));
		return this.startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDate = StringUtils.toDate(startDateString,
				Global.getConfig("web.dateFormat"));
	}

	public String getEndDateString() {
		this.endDateString = DateUtils.formatDate(endDate,
				Global.getConfig("web.dateFormat"));
		return this.endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDate = StringUtils.toDate(endDateString,
				Global.getConfig("web.dateFormat"));
	}

	@ExcelField(title = "新选拔中层干部试用期期限", type = 0, align = 2, sort = 90)
	public Date getTryOutDate() {
		return tryOutDate;
	}

	public void setTryOutDate(Date tryOutDate) {
		this.tryOutDate = tryOutDate;
	}

	public String getTryOutDateString() {
		this.tryOutDateString = DateUtils.formatDate(tryOutDate,
				Global.getConfig("web.dateFormat"));
		return this.tryOutDateString;
	}

	public void setTryOutDateString(String tryOutDateString) {
		this.tryOutDate = StringUtils.toDate(tryOutDateString,
				Global.getConfig("web.dateFormat"));
	}

	@ExcelField(title = "备注", type = 0, align = 2, sort = 140)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
