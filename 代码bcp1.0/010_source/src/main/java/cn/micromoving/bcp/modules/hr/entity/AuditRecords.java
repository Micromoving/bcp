package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;
/**
 * 审核记录表Entity
 * @author luyihao
 * @version 2016-06-01
 */
public class AuditRecords extends DataEntity<AuditRecords> {

	private static final long serialVersionUID = 1L;

	/* 业务编号 */
	private String businessKey;
	/* 记录编号 */
	private String recordId;
	/*任务编号*/
	private String taskKey;
	/* 审核意见 */
	private String auditOpinion;
//	/* 审核时间 */
//	private Date updateDate;
	
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	public String getTaskKey() {
		return taskKey;
	}
	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	


}
