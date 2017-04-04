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
 * 职称情况表Entity
 * 
 * @author luyihao
 * @version 2016-06-01
 */
public class ProTechPosition extends DataEntity<ProTechPosition> {
	private static final long serialVersionUID = 1L;
	/* 用户编号 */
	private User user;

	private Employee employee;

	// private String employeeId;
	/* 部门 */
	private Office office;
	/* 职称类型 */
	private String techPositionType;
	/* 职称级别 */
	private String techPositionLevel;
	/* 专业技术职务（职称） */
	private String name;
	/* 获取职称时间 */
	private Date gainDate;
	/* 聘任时间 */
	private Date appointDate;
	/* 更改时间 */
	private Date updateDate;
	/* 聘任时间字符 */
	private String appointDateString;
	/* 获取时间字符 */
	private String gainDateString;
	/* 备注(导出使用) */
	private String remarks;
	/**/
	private String userNo;

	public ProTechPosition() {
		super();
	}

	public ProTechPosition(String id) {
		super(id);
	}

	public String getAppointDateString() {
		this.appointDateString = DateUtils.formatDate(appointDate,
				Global.getConfig("web.dateShortFormatCHN"));

		return appointDateString;
	}

	public void setAppointDateString(String appointDateString) {

		this.appointDate = StringUtils.toDate(appointDateString,
				Global.getConfig("web.dateShortFormatCHN"));

	}

	public String getGainDateString() {
		this.gainDateString = DateUtils.formatDate(gainDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return gainDateString;
	}

	public void setGainDateString(String gainDateString) {
		this.gainDate = StringUtils.toDate(gainDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}

	@ExcelField(title = "姓名", type = 0, align = 2, sort = 20, value = "user.name")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@ExcelField(title = "职称类型", type = 0, align = 2, sort = 30, dictType = "tech_position_type")
	public String getTechPositionType() {
		return techPositionType;
	}

	public void setTechPositionType(String techPositionType) {
		this.techPositionType = techPositionType;
	}

	@ExcelField(title = "职称级别", type = 0, align = 2, sort = 40, dictType = "tech_position_level")
	public String getTechPositionLevel() {
		return techPositionLevel;
	}

	public void setTechPositionLevel(String techPositionLevel) {
		this.techPositionLevel = techPositionLevel;
	}

	@ExcelField(title = "专业技术职务（职称）", type = 0, align = 2, sort = 50, dictType = "tech_position")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ExcelField(title = "获取职称时间", type = 0, align = 2, sort = 60)
	public Date getGainDate() {
		return gainDate;
	}

	public void setGainDate(Date gainDate) {
		this.gainDate = gainDate;
	}

	@ExcelField(title = "聘任时间", type = 0, align = 2, sort = 70)
	public Date getAppointDate() {
		return appointDate;
	}

	public void setAppointDate(Date appointDate) {
		this.appointDate = appointDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@ExcelField(title = "工号", type = 1, align = 2, sort = 10, value = "employee.userNo")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

	public String getUserNo() {
		return userNo;
	}
	@ExcelField(title = "工号", type = 2, align = 2, sort = 10)
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	@ExcelField(title = "备注", type = 0, align = 2, sort = 80)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	// public String getEmployeeId() {
	// return employeeId;
	// }
	// public void setEmployeeId(String employeeId) {
	// this.employeeId = employeeId;
	// }

}
