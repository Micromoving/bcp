package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;
import java.util.List;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 参保职工情况表Entity
 * 
 * @author wangzhichen
 * @version 2016-06-03
 * 
 */
public class InsuredSituation extends DataEntity<InsuredSituation> {
	private static final long serialVersionUID = 1L;

	/* 用户 */
	private User user;
	private Employee employee;
	/**/
	private String userNo;
	/* 参保规则 */
	private InsuranceRule insuranceRule;
	/* 社保编号 */
	private String socialInsuranceId;
	/* 险种编号 */
	private String insuranceId;
	/* 参保时间 */
	private Date insuredDate;
	/* 参保时间字符串 */
	private String insuredDateString;
	/* 学院参保时间 */
	private Date schoolInsuredDate;
	/* 学校参保时间 */
	private String schoolInsuredDateString;
	/* 参保年限 */
	private String insuredYear;
	/* 基数 */
	private double base;
	/*备注*/
	private String remarks;
	
	private List<InsuranceRule> insList;
	private String[] insuranceIdArray = new String[30];
	private double[] baseArray = new double[30]; 

	
	
	public String getSocialInsuranceId() {
		return socialInsuranceId;
	}

	public void setSocialInsuranceId(String socialInsuranceId) {
		this.socialInsuranceId = socialInsuranceId;
	}
	@ExcelField(title = "备注", type = 0, align = 2, sort =80)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@ExcelField(title = "险种编号", type = 0, align = 2, sort = 30)
	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}
	@ExcelField(title = "参保时间", type = 0, align = 2, sort = 50)
	public Date getInsuredDate() {
		return insuredDate;
	}

	public void setInsuredDate(Date insuredDate) {
		this.insuredDate = insuredDate;
	}

	public List<InsuranceRule> getInsList() {
		return insList;
	}

	public void setInsList(List<InsuranceRule> insList) {
		this.insList = insList;
	}
	@ExcelField(title = "学院参保时间", type = 0, align = 2, sort = 60)
	public Date getSchoolInsuredDate() {
		return schoolInsuredDate;
	}

	public void setSchoolInsuredDate(Date schoolInsuredDate) {
		this.schoolInsuredDate = schoolInsuredDate;
	}
	@ExcelField(title = "参保年限", type = 0, align = 2, sort = 70)
	public String getInsuredYear() {
		return insuredYear;
	}

	public void setInsuredYear(String insuredYear) {
		this.insuredYear = insuredYear;
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}
	@ExcelField(title = "姓名", type = 0, align = 2, sort = 20,value="user.name")
	public User getUser() {
		return user;
	}

	public String[] getInsuranceIdArray() {
		return insuranceIdArray;
	}

	public void setInsuranceIdArray(String[] insuranceIdArray) {
		this.insuranceIdArray = insuranceIdArray;
	}

	public double[] getBaseArray() {
		return baseArray;
	}

	public void setBaseArray(double[] baseArray) {
		this.baseArray = baseArray;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@ExcelField(title = "险种", type = 0, align = 2, sort = 40,value="insuranceRule.insuranceType")
	public InsuranceRule getInsuranceRule() {
		return insuranceRule;
	}

	public void setInsuranceRule(InsuranceRule insuranceRule) {
		this.insuranceRule = insuranceRule;
	}

	public String getSchoolInsuredDateString() {
		this.schoolInsuredDateString = DateUtils.formatDate(schoolInsuredDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return schoolInsuredDateString;
	}

	public void setSchoolInsuredDateString(String schoolInsuredDateString) {
		this.schoolInsuredDate = StringUtils.toDate(schoolInsuredDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}

	public String getInsuredDateString() {
		this.insuredDateString = DateUtils.formatDate(insuredDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return insuredDateString;
	}

	public void setInsuredDateString(String insuredDateString) {
		this.insuredDate = StringUtils.toDate(insuredDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}
	@ExcelField(title = "工号", type = 1, align = 2, sort = 10,value="employee.userNo")
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
	

}
