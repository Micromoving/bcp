package cn.micromoving.bcp.modules.hr.entity;

import java.util.List;

import cn.micromoving.bcp.common.persistence.DataEntity;

import com.google.common.collect.Lists;

/**
 * 系列工资标准表Entity
 * 
 * @author wenyang
 * @version 2016-07-14
 */

public class SalaryStandard extends DataEntity<SalaryStandard> {

    private static final long serialVersionUID = 1L;

    /* 方案编号 */
    private SalaryPlan salaryPlan;

    /**
     * 绩效工资entity <code>performanceLevel</code> 的注释
     */
    private PerformanceLevel performanceLevel;

    /* 岗位类型 */
    private String postType;

    /* 岗位等级 */
    private String postLevel;


    /* 职级 */
    private String professionalLevel;

    /* 职级工资 */
    private Double professionalLevelSalary;

    /* 岗位工资 */
    private Double postSalary;

    /* 岗位津贴 */
    private Double postSubside;

    /* 学历 */
    private String eduBackground;

  

    private List<String> idList = Lists.newArrayList();

    /* 职级工资list */
    private List<Double> professionalLevelSalaryList = Lists.newArrayList();

    /* 岗位工资list */
    private List<Double> postSalaryList = Lists.newArrayList();

    /* 岗位津贴list */
    private List<Double> postSubsideList = Lists.newArrayList();

    /**
     * 获取。
     * 
     * @return idList
     */
    public List<String> getIdList() {
        return idList;
    }

    /**
     * 设置idList。
     * 
     * @param idList
     */
    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    /**
     * 获取。
     * 
     * @return professionalLevelSalaryList
     */
    public List<Double> getProfessionalLevelSalaryList() {
        return professionalLevelSalaryList;
    }

    /**
     * 设置professionalLevelSalaryList。
     * 
     * @param professionalLevelSalaryList
     */
    public void setProfessionalLevelSalaryList(List<Double> professionalLevelSalaryList) {
        this.professionalLevelSalaryList = professionalLevelSalaryList;
    }

    /**
     * 获取。
     * @return performanceLevel 
     */
    public PerformanceLevel getPerformanceLevel() {
        return performanceLevel;
    }

    /**
     * 设置performanceLevel。
     * @param performanceLevel
     */
    public void setPerformanceLevel(PerformanceLevel performanceLevel) {
        this.performanceLevel = performanceLevel;
    }

    /**
     * 获取。
     * 
     * @return postSalaryList
     */
    public List<Double> getPostSalaryList() {
        return postSalaryList;
    }

    /**
     * 设置postSalaryList。
     * 
     * @param postSalaryList
     */
    public void setPostSalaryList(List<Double> postSalaryList) {
        this.postSalaryList = postSalaryList;
    }

    /**
     * 获取。
     * 
     * @return postSubsideList
     */
    public List<Double> getPostSubsideList() {
        return postSubsideList;
    }

    /**
     * 设置postSubsideList。
     * 
     * @param postSubsideList
     */
    public void setPostSubsideList(List<Double> postSubsideList) {
        this.postSubsideList = postSubsideList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostLevel() {
        return postLevel;
    }

    public void setPostLevel(String postLevel) {
        this.postLevel = postLevel;
    }

   
    public String getProfessionalLevel() {
        return professionalLevel;
    }

    public void setProfessionalLevel(String professionalLevel) {
        this.professionalLevel = professionalLevel;
    }

    public Double getProfessionalLevelSalary() {
        return professionalLevelSalary;
    }

    public void setProfessionalLevelSalary(Double professionalLevelSalary) {
        this.professionalLevelSalary = professionalLevelSalary;
    }

    public Double getPostSalary() {
        return postSalary;
    }

    public void setPostSalary(Double postSalary) {
        this.postSalary = postSalary;
    }

    public Double getPostSubside() {
        return postSubside;
    }

    public void setPostSubside(Double postSubside) {
        this.postSubside = postSubside;
    }

    public String getEduBackground() {
        return eduBackground;
    }

    public void setEduBackground(String eduBackground) {
        this.eduBackground = eduBackground;
    }

  

    public SalaryPlan getSalaryPlan() {
        return salaryPlan;
    }

    public void setSalaryPlan(SalaryPlan salaryPlan) {
        this.salaryPlan = salaryPlan;
    }

}