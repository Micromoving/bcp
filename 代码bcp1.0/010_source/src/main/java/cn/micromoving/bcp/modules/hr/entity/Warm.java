package cn.micromoving.bcp.modules.hr.entity;

import java.util.List;

import com.google.common.collect.Lists;

import cn.micromoving.bcp.common.persistence.DataEntity;


/**
 * 取暖表Entity
 * @author wenyang
 * @version 2016-07-13
 */
public class Warm extends DataEntity<Warm>{

	
	private static final long serialVersionUID = 1L;
	
	
	/*方案编号*/
	private SalaryPlan salaryPlan;
	/*岗位类型*/
	private String postType;
	/*分类*/
	private String classify;
	/*费用标准*/
	private  Double chargeStandard;
	
	private List<String> dataIdList1  = Lists.newArrayList();
	private List<Double> chargeStandardList1= Lists.newArrayList();
	private List<String> dataIdList2  = Lists.newArrayList();
	private List<Double> chargeStandardList2= Lists.newArrayList();
	private List<String> dataIdList3  = Lists.newArrayList();
	private List<Double> chargeStandardList3= Lists.newArrayList();
	private List<String> dataIdList4  = Lists.newArrayList();
	private List<Double> chargeStandardList4= Lists.newArrayList();
	private List<String> dataIdList5  = Lists.newArrayList();
	private List<Double> chargeStandardList5= Lists.newArrayList();
	public List<String> getDataIdList1() {
		return dataIdList1;
	}
	public void setDataIdList1(List<String> dataIdList1) {
		this.dataIdList1 = dataIdList1;
	}
	public List<Double> getChargeStandardList1() {
		return chargeStandardList1;
	}
	public void setChargeStandardList1(List<Double> chargeStandardList1) {
		this.chargeStandardList1 = chargeStandardList1;
	}
	public List<String> getDataIdList2() {
		return dataIdList2;
	}
	public void setDataIdList2(List<String> dataIdList2) {
		this.dataIdList2 = dataIdList2;
	}
	public List<Double> getChargeStandardList2() {
		return chargeStandardList2;
	}
	public void setChargeStandardList2(List<Double> chargeStandardList2) {
		this.chargeStandardList2 = chargeStandardList2;
	}
	public List<String> getDataIdList3() {
		return dataIdList3;
	}
	public void setDataIdList3(List<String> dataIdList3) {
		this.dataIdList3 = dataIdList3;
	}
	public List<Double> getChargeStandardList3() {
		return chargeStandardList3;
	}
	public void setChargeStandardList3(List<Double> chargeStandardList3) {
		this.chargeStandardList3 = chargeStandardList3;
	}
	public List<String> getDataIdList4() {
		return dataIdList4;
	}
	public void setDataIdList4(List<String> dataIdList4) {
		this.dataIdList4 = dataIdList4;
	}
	public List<Double> getChargeStandardList4() {
		return chargeStandardList4;
	}
	public void setChargeStandardList4(List<Double> chargeStandardList4) {
		this.chargeStandardList4 = chargeStandardList4;
	}
	public List<String> getDataIdList5() {
		return dataIdList5;
	}
	public void setDataIdList5(List<String> dataIdList5) {
		this.dataIdList5 = dataIdList5;
	}
	public List<Double> getChargeStandardList5() {
		return chargeStandardList5;
	}
	public void setChargeStandardList5(List<Double> chargeStandardList5) {
		this.chargeStandardList5 = chargeStandardList5;
	}
	public SalaryPlan getSalaryPlan() {
		return salaryPlan;
	}
	public void setSalaryPlan(SalaryPlan salaryPlan) {
		this.salaryPlan = salaryPlan;
	}
	
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public Double getChargeStandard() {
		return chargeStandard;
	}
	public void setChargeStandard(Double chargeStandard) {
		this.chargeStandard = chargeStandard;
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	
}