package cn.micromoving.bcp.modules.hr.entity;

import java.util.List;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 工作经验表 Entity
 * 
 * @author 工作经验表
 * @version 2016-04-5
 */
public class AnnualSalaryPersonnel extends
		DataEntity<AnnualSalaryPersonnel> {

	private static final long serialVersionUID = 1L;
	/* 工号 */
	private Employee employee;
	/* 部门 */
	private Office office;
	/*用户编号*/
	private User user;
	/* 年薪/年 */
	private String yearlySalary;
	
	private SalEmpView salEmpView;
	
	
	public AnnualSalaryPersonnel() {
		super();
		office = new Office();
		user = new User();
		employee = new Employee();
	}



	

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getYearlySalary() {
		return yearlySalary;
	}

	public void setYearlySalary(String yearlySalary) {
		this.yearlySalary = yearlySalary;
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

	public SalEmpView getSalEmpView() {
		return salEmpView;
	}

	public void setSalEmpView(SalEmpView salEmpView) {
		this.salEmpView = salEmpView;
	}

    
	
	
}
