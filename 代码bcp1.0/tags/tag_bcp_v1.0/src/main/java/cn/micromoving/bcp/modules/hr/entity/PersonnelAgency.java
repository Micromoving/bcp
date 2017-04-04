package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 人事代理表Entity
 * @author luyihao
 * @version 2016-06-01
 */

public class PersonnelAgency extends DataEntity<PersonnelAgency>{
	
	private static final long serialVersionUID = 1L;
	
	/*用户编号*/
	private User user;
	/*工号*/
	private Employee employee;
	/**/
	private String userNo;
	/*人事档案材料是否齐全*/
	private String archivesIsComplete;
	/*人事档案缺失材料*/
	private String missingMaterial;
	/*人事档案内容清单*/
	private String archivesList;
	/*备注*/
	private String comments;
	
	
	@ExcelField(title = "姓名", type = 0, align = 2, sort = 20, value = "user.name")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ExcelField(title = "人事档案材料是否齐全", type = 0, align = 2, sort = 30,dictType="yes_no")
	public String getArchivesIsComplete() {
		return archivesIsComplete;
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
	public void setArchivesIsComplete(String archivesIsComplete) {
		this.archivesIsComplete = archivesIsComplete;
	}
	@ExcelField(title = "人事档案缺失材料", type = 0, align = 2, sort = 40)
	public String getMissingMaterial() {
		return missingMaterial;
	}
	public void setMissingMaterial(String missingMaterial) {
		this.missingMaterial = missingMaterial;
	}
	@ExcelField(title = "人事档案内容清单", type = 0, align = 2, sort = 50)
	public String getArchivesList() {
		return archivesList;
	}
	public void setArchivesList(String archivesList) {
		this.archivesList = archivesList;
	}
	@ExcelField(title = "备注", type = 0, align = 2, sort = 60)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
	
	
	