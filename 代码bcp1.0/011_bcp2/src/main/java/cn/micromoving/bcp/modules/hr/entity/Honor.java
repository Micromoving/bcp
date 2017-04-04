package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

public class Honor extends DataEntity<Honor> {
	private static final long serialVersionUID = 1L;
	/* 教师 */
	private Employee employee;
	/*用户*/
	private User user;
	/*部门*/
	private Office office;
	/* 荣誉级别 */
	private String honorLevel;
	/* 获得荣誉称号 */
	private String receiveHonoraryTitle;
	/* 荣誉记录描述 */
	private String honorRecordDescription;
	/* 荣誉授予单位名称 */
	private String honoraryAwardunit;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

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

    public String getHonorLevel() {
		return honorLevel;
	}

	public void setHonorLevel(String honorLevel) {
		this.honorLevel = honorLevel;
	}

	public String getReceiveHonoraryTitle() {
		return receiveHonoraryTitle;
	}

	public void setReceiveHonoraryTitle(String receiveHonoraryTitle) {
		this.receiveHonoraryTitle = receiveHonoraryTitle;
	}

	public String getHonorRecordDescription() {
		return honorRecordDescription;
	}

	public void setHonorRecordDescription(String honorRecordDescription) {
		this.honorRecordDescription = honorRecordDescription;
	}

	public String getHonoraryAwardunit() {
		return honoraryAwardunit;
	}

	public void setHonoraryAwardunit(String honoraryAwardunit) {
		this.honoraryAwardunit = honoraryAwardunit;
	}

}
