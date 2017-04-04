package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;

/**
 * 总工资Entity
 * 
 * @author 王志辰
 * @version 2016-08-03
 */
public class SalView extends DataEntity<SalView> {
	private static final long serialVersionUID = 1L;
	
	private SalEmpView salEmp;
	/*职级工资*/
	private double professionalLevelSalary;
	/*岗位工资*/
	private double postSalary;
	/*绩效工资*/
	private double performanceSalary;
	/*岗位津贴*/
	private double postSubside;

    public double getProfessionalLevelSalary() {
		return professionalLevelSalary;
	}

	public void setProfessionalLevelSalary(double professionalLevelSalary) {
		this.professionalLevelSalary = professionalLevelSalary;
	}

	public double getPostSalary() {
		return postSalary;
	}

	public void setPostSalary(double postSalary) {
		this.postSalary = postSalary;
	}

	public double getPerformanceSalary() {
		return performanceSalary;
	}

	public void setPerformanceSalary(double performanceSalary) {
		this.performanceSalary = performanceSalary;
	}

	public double getPostSubside() {
		return postSubside;
	}

	public void setPostSubside(double postSubside) {
		this.postSubside = postSubside;
	}

	/**
     * 获取。
     * @return salEmp 
     */
    public SalEmpView getSalEmp() {
        return salEmp;
    }

    /**
     * 设置salEmp。
     * @param salEmp
     */
    public void setSalEmp(SalEmpView salEmp) {
        this.salEmp = salEmp;
    }

}
