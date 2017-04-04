package cn.micromoving.bcp.modules.hr.entity;

import java.util.List;

import cn.micromoving.bcp.common.persistence.DataEntity;

import com.google.common.collect.Lists;

/**
 * 绩效等级表Entity
 * 
 * @author wenyang
 * @version 2016-07-14
 */

public class PerformanceLevel extends DataEntity<PerformanceLevel> {

    private static final long serialVersionUID = 1L;

    /* 方案编号 */
    private SalaryPlan salaryPlan;

    /* 工资标准编号 */
    private SalaryStandard salarystandard;

    /* 绩效等级 */
    private String performanceLevel;

    /* 绩效工资 */
    private Double performanceSalary;

    private List<String> perIdList = Lists.newArrayList();

    private List<Double> performanceSalaryList = Lists.newArrayList();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取。
     * @return perIdList 
     */
    public List<String> getPerIdList() {
        return perIdList;
    }

    /**
     * 设置perIdList。
     * @param perIdList
     */
    public void setPerIdList(List<String> perIdList) {
        this.perIdList = perIdList;
    }

    /**
     * 获取。
     * @return performanceSalaryList 
     */
    public List<Double> getPerformanceSalaryList() {
        return performanceSalaryList;
    }

    /**
     * 设置performanceSalaryList。
     * @param performanceSalaryList
     */
    public void setPerformanceSalaryList(List<Double> performanceSalaryList) {
        this.performanceSalaryList = performanceSalaryList;
    }

    public SalaryStandard getSalarystandard() {
        return salarystandard;
    }

    public void setSalarystandard(SalaryStandard salarystandard) {
        this.salarystandard = salarystandard;
    }

    public String getPerformanceLevel() {
        return performanceLevel;
    }

    public void setPerformanceLevel(String performanceLevel) {
        this.performanceLevel = performanceLevel;
    }

    public Double getPerformanceSalary() {
        return performanceSalary;
    }

    public void setPerformanceSalary(Double performanceSalary) {
        this.performanceSalary = performanceSalary;
    }

    public SalaryPlan getSalaryPlan() {
        return salaryPlan;
    }

    public void setSalaryPlan(SalaryPlan salaryPlan) {
        this.salaryPlan = salaryPlan;
    }

}