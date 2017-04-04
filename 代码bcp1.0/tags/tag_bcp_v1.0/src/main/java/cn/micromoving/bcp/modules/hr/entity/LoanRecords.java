package cn.micromoving.bcp.modules.hr.entity;
import java.util.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;
/**
 * 借款记录表Entity
 * @author luyihao
 * @version 2016-06-01
 */
public class LoanRecords extends DataEntity<LoanRecords> {

	private static final long serialVersionUID = 1L;

	/* 编号 */
	private String id;
	/* 业务编号 */
	private String businessId;
	/* 记录编号 */
	private String recodeId;
	/*借款类别*/
	private String loanType;
	/*借款人*/
	private String employeeId;
	/*借款时间*/
	private Date LoanDate;
	/*借款金额*/
	private Double loan;
	/*备注*/ 
	private String comments;
	/* 删除标记 */
	private String delFlag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getRecodeId() {
		return recodeId;
	}
	public void setRecodeId(String recodeId) {
		this.recodeId = recodeId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Date getLoanDate() {
		return LoanDate;
	}
	public void setLoanDate(Date loanDate) {
		LoanDate = loanDate;
	}
	public Double getLoan() {
		return loan;
	}
	public void setLoan(Double loan) {
		this.loan = loan;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	


}
