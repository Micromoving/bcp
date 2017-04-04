package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.Office;

/**
 * 离职表Entity
 * @author shihengji
 *
 */
public class SalaryInstancePerformance extends DataEntity<SalaryInstancePerformance>{

	private static final long serialVersionUID = 1L;
	
	/* 部门 */
	private Office office;
	/*工资实例*/
	private SalaryInstance SalaryInstance;
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public SalaryInstance getSalaryInstance() {
		return SalaryInstance;
	}
	public void setSalaryInstance(SalaryInstance salaryInstance) {
		SalaryInstance = salaryInstance;
	}
	
}
