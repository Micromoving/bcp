/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.modules.sys.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.TreeEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;

/**
 * 机构Entity
 * 
 * @author songcm
 * @version 2013-05-15
 */
public class Office extends TreeEntity<Office> {

	private static final long serialVersionUID = 1L;

	private String code; // 机构编码

	private String levels; // 机构层次，（在系统中，决定机构的隶属关系）

	private String foundingDocNumber; // 成立文号

	private Date foundingDate; // 成立时间

	private String type; // 机构类型（1：公司；2：部门；3：小组）

	private String grade; // 机构等级（1：一级；2：二级；3：三级；4：四级；5：五级；6：六级）

	private String address; // 联系地址

	private String zipCode; // 邮政编码

	private String master; // 主管院领导分管

	private String phone; // 电话

	private String fax; // 传真

	private String email; // 邮箱

	private String useable;// 是否可用

	private User primaryPerson;// 主负责人

	private User deputyPerson;// 副负责人

	private String remarks; // 备注

	/* 聘任文号 */
	private String engageDocNumber;
	/* 聘任时间 */
	private Date engageDate;
	/* 部门印章名称 */
	private String sealName;
	/* 印章启用时间 */
	private Date sealDate;
	/* 成立时间字符串 */
	private String foundingDateString;
	/* 聘任时间字符串 */
	private String engageDateString;
	/* 印章启用时间字符串 */
	private String sealDateString;
	private String delFlag;

	public String getSealDateString() {
		this.sealDateString = DateUtils.formatDate(sealDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return this.sealDateString;
	}

	public void setSealDateString(String sealDateString) {
		this.sealDate = StringUtils.toDate(sealDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}

	public String getFoundingDateString() {
		this.foundingDateString = DateUtils.formatDate(foundingDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return this.foundingDateString;
	}

	public void setFoundingDateString(String foundingDateString) {
		this.foundingDate = StringUtils.toDate(foundingDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}

	public String getEngageDateString() {
		this.engageDateString = DateUtils.formatDate(engageDate,
				Global.getConfig("web.dateShortFormatCHN"));
		return this.engageDateString;
	}

	public void setEngageDateString(String engageDateString) {
		this.engageDate = StringUtils.toDate(engageDateString,
				Global.getConfig("web.dateShortFormatCHN"));
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public String getEngageDocNumber() {
		return engageDocNumber;
	}

	public void setEngageDocNumber(String engageDocNumber) {
		this.engageDocNumber = engageDocNumber;
	}

	public Date getEngageDate() {
		return engageDate;
	}

	public void setEngageDate(Date engageDate) {
		this.engageDate = engageDate;
	}

	public String getSealName() {
		return sealName;
	}

	public void setSealName(String sealName) {
		this.sealName = sealName;
	}

	public Date getSealDate() {
		return sealDate;
	}

	public void setSealDate(Date sealDate) {
		this.sealDate = sealDate;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setFoundingDate(Date foundingDate) {
		this.foundingDate = foundingDate;
	}

	private List<String> childDeptList;// 快速添加子部门

	/**
	 * 获取。
	 * 
	 * @return level
	 */
	public String getLevels() {
		return levels;
	}

	/**
	 * 设置level。
	 * 
	 * @param level
	 */
	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getFoundingDocNumber() {
		return foundingDocNumber;
	}

	public void setFoundingDocNumber(String foundingDocNumber) {
		this.foundingDocNumber = foundingDocNumber;
	}

	public Office() {
		super();
		// this.sort = 30;
		this.type = "2";
	}

	public Office(String id) {
		super(id);
	}

	public List<String> getChildDeptList() {
		return childDeptList;
	}

	public void setChildDeptList(List<String> childDeptList) {
		this.childDeptList = childDeptList;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public User getPrimaryPerson() {
		return primaryPerson;
	}

	public void setPrimaryPerson(User primaryPerson) {
		this.primaryPerson = primaryPerson;
	}

	public User getDeputyPerson() {
		return deputyPerson;
	}

	public void setDeputyPerson(User deputyPerson) {
		this.deputyPerson = deputyPerson;
	}

	// @JsonBackReference
	// @NotNull
	public Office getParent() {
		return parent;
	}

	public void setParent(Office parent) {
		this.parent = parent;
	}

	//
	// @Length(min=1, max=2000)
	// public String getParentIds() {
	// return parentIds;
	// }
	//
	// public void setParentIds(String parentIds) {
	// this.parentIds = parentIds;
	// }

	//
	// @Length(min=1, max=100)
	// public String getName() {
	// return name;
	// }
	//
	// public void setName(String name) {
	// this.name = name;
	// }
	//
	// public Integer getSort() {
	// return sort;
	// }
	//
	// public void setSort(Integer sort) {
	// this.sort = sort;
	// }
	@ExcelField(title = "机构类型", type = 1, align = 2, sort = 30, dictType = "sys_office_type")
	@Length(min = 1, max = 1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min = 1, max = 1)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@ExcelField(title = "联系地址", type = 1, align = 2, sort = 40)
	@Length(min = 0, max = 255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ExcelField(title = "邮政编码", type = 1, align = 2, sort = 50)
	@Length(min = 0, max = 100)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@ExcelField(title = "负责人", type = 1, align = 2, sort = 60)
	@Length(min = 0, max = 100)
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	@ExcelField(title = "电话", type = 1, align = 2, sort = 70)
	@Length(min = 0, max = 200)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@ExcelField(title = "传真", type = 1, align = 2, sort = 80)
	@Length(min = 0, max = 200)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@ExcelField(title = "邮箱", type = 1, align = 2, sort = 90)
	@Length(min = 0, max = 200)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFoundingDate() {
		return foundingDate;
	}

	// public String getParentId() {
	// return parent != null && parent.getId() != null ? parent.getId() : "0";
	// }
	@ExcelField(title = "机构名称", type = 1, align = 2, sort = 20)
	@Override
	public String toString() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}