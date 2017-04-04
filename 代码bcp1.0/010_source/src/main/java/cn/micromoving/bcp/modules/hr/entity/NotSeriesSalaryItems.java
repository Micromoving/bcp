package cn.micromoving.bcp.modules.hr.entity;

import java.util.List;

import cn.micromoving.bcp.common.persistence.DataEntity;

/**
 * 工作经验表 Entity
 * 
 * @author 工作经验表
 * @version 2016-04-5
 */
public class NotSeriesSalaryItems extends DataEntity<NotSeriesSalaryItems> {

	private static final long serialVersionUID = 1L;
	/**/
	private SalEmpView sal;
	/* 非在编工资项 */
	private String notSeriesSalaryItems;
	/**/
	private List<String> selectedItems;
	/**/
	private List<SalaryItems> salaryItemsList;
	/*人员ID*/
	private List<String> employeeList;
	

	public List<String> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<String> employeeList) {
		this.employeeList = employeeList;
	}

	public List<SalaryItems> getSalaryItemsList() {
		return salaryItemsList;
	}

	public void setSalaryItemsList(List<SalaryItems> salaryItemsList) {
		this.salaryItemsList = salaryItemsList;
	}

	public SalEmpView getSal() {
		return sal;
	}

	public void setSal(SalEmpView sal) {
		this.sal = sal;
	}

	public String getNotSeriesSalaryItems() {
		return notSeriesSalaryItems;
	}

	public void setNotSeriesSalaryItems(String notSeriesSalaryItems) {
		this.notSeriesSalaryItems = notSeriesSalaryItems;
	}

	public List<String> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<String> selectedItems) {
		this.selectedItems = selectedItems;
	}

}
