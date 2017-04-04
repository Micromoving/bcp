package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 离退休人员联系方式表Entity
 * @author luyihao
 * @version 2016-06-01
 */
public class RetireContact extends DataEntity<RetireContact>{
	
	private static final long serialVersionUID = 1L;
	 /*编号*/
	private String id;       
	 /*用户编号*/
	private User user;
    /* 部门 */
    private Office office;
    /*离退休*/
    private Retire retire;
	/*姓名*/
	private String retireName;
	/*与本人关系*/
	private String relation;
	/*联系类型*/
	private String RetireType;
	/*联系方式*/
	private String retireMode;
	/*删除标记*/
	private String delFlag;
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getRetireName() {
		return retireName;
	}
	public void setRetireName(String retireName) {
		this.retireName = retireName;
	}
	public String getRetireType() {
		return RetireType;
	}
	public void setRetireType(String retireType) {
		RetireType = retireType;
	}
	public String getRetireMode() {
		return retireMode;
	}
	public void setRetireMode(String retireMode) {
		this.retireMode = retireMode;
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
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public Retire getRetire() {
		return retire;
	}
	public void setRetire(Retire retire) {
		this.retire = retire;
	}

}