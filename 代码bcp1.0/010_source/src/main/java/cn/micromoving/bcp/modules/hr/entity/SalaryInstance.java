package cn.micromoving.bcp.modules.hr.entity;

import java.util.List;

import com.google.common.collect.Lists;

import cn.micromoving.bcp.common.persistence.ActEntity;

/**
 * 工资实例表Entity
 * @author wenyang
 * @version 2016-07-14
 */

/**
 * @author 王志辰
 */
public class SalaryInstance extends ActEntity<SalaryInstance> {

	private static final long serialVersionUID = 1L;

	/* 年月 */
	private String yearMonth;

	/* 状态 */
	private String dataState;

	/* 流程编号 */
	private String processInstanceId;

	/* 工资说明 */
	private String salaryExplain;

	/* 参考工作量 */
	private Integer referenceWorkLoade;
	/* 特殊人员每月补工作量 */
	private Integer specialPersonnelWorkLoade;

	private List<String> selectedItem = Lists.newArrayList();

	private List<Double> selectedCoefficient = Lists.newArrayList();

	/**
	 * 工资实例中，对应的工资详情。 <code>
	 * salaryDetailsList</code> 的注释
	 */
	private List<SalaryDetails> salaryDetailsList = Lists.newArrayList();

	/* 工资实例任务 */
	private List<SalaryInstanceTask> salaryInstanceTaskList;

	public String getSalaryExplain() {
		return salaryExplain;
	}

	public void setSalaryExplain(String salaryExplain) {
		this.salaryExplain = salaryExplain;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	private String flag;

	public List<SalaryInstanceTask> getSalaryInstanceTaskList() {
		return salaryInstanceTaskList;
	}

	/**
	 * 获取。
	 * 
	 * @return salaryDetailsList
	 */
	public List<SalaryDetails> getSalaryDetailsList() {
		return salaryDetailsList;
	}

	/**
	 * 设置salaryDetailsList。
	 * 
	 * @param salaryDetailsList
	 */
	public void setSalaryDetailsList(List<SalaryDetails> salaryDetailsList) {
		this.salaryDetailsList = salaryDetailsList;
	}

	public void setSalaryInstanceTaskList(
			List<SalaryInstanceTask> salaryInstanceTaskList) {
		this.salaryInstanceTaskList = salaryInstanceTaskList;
	}

	public String getDataState() {
		return dataState;
	}

	public void setDataState(String dataState) {
		this.dataState = dataState;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public List<String> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<String> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<Double> getSelectedCoefficient() {
		return selectedCoefficient;
	}

	public void setSelectedCoefficient(List<Double> selectedCoefficient) {
		this.selectedCoefficient = selectedCoefficient;
	}

	public Integer getReferenceWorkLoade() {
		return referenceWorkLoade;
	}

	public void setReferenceWorkLoade(Integer referenceWorkLoade) {
		this.referenceWorkLoade = referenceWorkLoade;
	}

	public Integer getSpecialPersonnelWorkLoade() {
		return specialPersonnelWorkLoade;
	}

	public void setSpecialPersonnelWorkLoade(Integer specialPersonnelWorkLoade) {
		this.specialPersonnelWorkLoade = specialPersonnelWorkLoade;
	}

}