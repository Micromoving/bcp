package cn.micromoving.bcp.modules.hr.entity;

import java.util.List;

import com.google.common.collect.Lists;

import cn.micromoving.bcp.common.persistence.DataEntity;

/**
 * 课酬标准表Entity
 * @author wenyang
 * @version 2016-07-14
 * 
 */

public class  ClassPayStandard extends DataEntity<ClassPayStandard>{


	private static final long serialVersionUID = 1L;
	
	
	/*方案编号*/
	private SalaryPlan salaryPlan;
	/*职称*/
	private String techPosition;
	/*课酬*/
	private Double classPay;
	/*聘任方式*/
	private String engageMode;
	
	private List<String> dataIdList  = Lists.newArrayList();
	private List<Double> classPayList= Lists.newArrayList();
	
	
	public String getTechPosition() {
		return techPosition;
	}
	public void setTechPosition(String techPosition) {
		this.techPosition = techPosition;
	}
	public Double getClassPay() {
		return classPay;
	}
	public void setClassPay(Double classPay) {
		this.classPay = classPay;
	}
	public String getEngageMode() {
		return engageMode;
	}
	public void setEngageMode(String engageMode) {
		this.engageMode = engageMode;
	}
	public SalaryPlan getSalaryPlan() {
		return salaryPlan;
	}
	public void setSalaryPlan(SalaryPlan salaryPlan) {
		this.salaryPlan = salaryPlan;
	}
	
	public List<String> getDataIdList() {
		return dataIdList;
	}

	public void setDataIdList(List<String> dataIdList) {
		this.dataIdList = dataIdList;
	}

	public List<Double> getClassPayList() {
		return classPayList;
	}

	public void setClassPayList(List<Double> classPayList) {
		this.classPayList = classPayList;
	}
	
	
	
	
	
}