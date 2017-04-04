package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 高校教师资格认定表Entity
 * 
 * @author luyihao
 * @version 2016-06-01
 */
public class TeacherQualification extends DataEntity<TeacherQualification> {

	private static final long serialVersionUID = 1L;

	/*用户编号*/
	private User user = new User();
	/* 职员编号 */
	private Employee employee = new Employee();
	@ExcelField(title = "姓名", type = 0, align = 2, sort = 20,value="user.name")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/* 资格类型 */
	private String qualificationsType;
	/* 证书编号 */
	private String certificateId;
	/* 取得时间 */
	private Date gainDate;
	/* 审核状态 */
	private String auditingStatus;
	/* 审核时间 */
	private Date auditingDate;
	/* 备注 */
	private String comments;
	/*认定机构*/
	private String certifyingBody;
	/*申请单位*/
	private String applicationUnit;
	/* 取得时间字符串*/
	private String gainDateString;
	/* 审核时间字符串 */
	private String auditingDateString;
	/**/
	private String userNo;
	
	@ExcelField(title = "工号", type = 2, align = 2, sort = 10)
	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public TeacherQualification() {
		 super();
		 user = new User();
		 employee = new Employee();
	}
	@NotNull(message="取得时间不能为空")
	public String getGainDateString() {
        this.gainDateString = DateUtils.formatDate(gainDate, Global.getConfig("web.dateShortFormatCHN"));
        return this.gainDateString;
    }

    public void setGainDateString(String gainDateString) {
        this.gainDate = StringUtils.toDate(gainDateString, Global.getConfig("web.dateShortFormatCHN"));
    }
    public String getAuditingDateString() {
        this.auditingDateString = DateUtils.formatDate(auditingDate, Global.getConfig("web.dateShortFormatCHN"));
        return this.auditingDateString;
    }

    public void setAuditingDateString(String auditingDateString) {
        this.auditingDate = StringUtils.toDate(auditingDateString, Global.getConfig("web.dateShortFormatCHN"));
    }
   
    @ExcelField(title = "资格类型", type = 0, align = 2, sort = 30,dictType="qualifications_type")
	public String getQualificationsType() {
		return qualificationsType;
	}

	public void setQualificationsType(String qualificationsType) {
		this.qualificationsType = qualificationsType;
	}
	@NotNull(message="证书编号不能为空")
	@ExcelField(title = "证书编号", type = 0, align = 2, sort = 40)
	public String getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}
	@ExcelField(title = "取得时间", type = 0, align = 2, sort = 50)
	public Date getGainDate() {
		return gainDate;
	}

	public void setGainDate(Date gainDate) {
		this.gainDate = gainDate;
	}
	
	public String getAuditingStatus() {
		return auditingStatus;
	}

	public void setAuditingStatus(String auditingStatus) {
		this.auditingStatus = auditingStatus;
	}
	@ExcelField(title = "审核时间", type = 0, align = 2, sort = 60)
	public Date getAuditingDate() {
		return auditingDate;
	}

	public void setAuditingDate(Date auditingDate) {
		this.auditingDate = auditingDate;
	}
	@ExcelField(title = "备注", type = 0, align = 2, sort = 90)
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	@NotNull(message="认定机构不能为空")
	@ExcelField(title = "认定机构", type = 0, align = 2, sort = 70)
	public String getCertifyingBody() {
		return certifyingBody;
	}

	public void setCertifyingBody(String certifyingBody) {
		this.certifyingBody = certifyingBody;
	}
	@NotNull(message="申请单位不能为空")
	@ExcelField(title = "申请单位", type = 0, align = 2, sort = 80)
	public String getApplicationUnit() {
		return applicationUnit;
	}

	public void setApplicationUnit(String applicationUnit) {
		this.applicationUnit = applicationUnit;
	}
	@ExcelField(title = "工号", type = 1, align = 2, sort = 10,value="employee.userNo")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

}
