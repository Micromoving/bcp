
package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;

/**
 * 工资任务实例表	Entity
 * @author  工资任务实例表	
 * @version 2016-04-5
 */
public class SalaryInstanceTask extends DataEntity<SalaryInstanceTask>{
	private static final long serialVersionUID = 1L;
	/*	工资实例编号*/
	private SalaryInstance salaryInstance;
	/*工资项编号*/
	private SalaryItems salaryItems;
	/*系数*/
	private Double coefficient;
	
	public SalaryInstance getSalaryInstance() {
		return salaryInstance;
	}
	public void setSalaryInstance(SalaryInstance salaryInstance) {
		this.salaryInstance = salaryInstance;
	}
	
	public Double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}
	public SalaryItems getSalaryItems() {
		return salaryItems;
	}
	public void setSalaryItems(SalaryItems salaryItems) {
		this.salaryItems = salaryItems;
	}												

	
	
}
