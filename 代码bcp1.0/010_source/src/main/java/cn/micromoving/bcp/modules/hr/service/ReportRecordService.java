package cn.micromoving.bcp.modules.hr.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.act.service.ActTaskService;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.dao.AuditRecordsDao;
import cn.micromoving.bcp.modules.hr.dao.DutyDetailDao;
import cn.micromoving.bcp.modules.hr.dao.ReportPerformanceDao;
import cn.micromoving.bcp.modules.hr.dao.ReportRecordDao;
import cn.micromoving.bcp.modules.hr.dao.ReportedWorkloadeDao;
import cn.micromoving.bcp.modules.hr.entity.AuditRecords;
import cn.micromoving.bcp.modules.hr.entity.DutyDetail;
import cn.micromoving.bcp.modules.hr.entity.ReportPerformance;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.ReportedWorkloade;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstance;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Maps;

@Service
@Transactional(readOnly = true)
public class ReportRecordService extends
		CrudService<ReportRecordDao, ReportRecord> {

	@Autowired
	private ReportRecordDao reportRecordDao;

	@Autowired
	private ActTaskService actTaskService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private ReportedWorkloadeDao reportedWorkloadeDao;

	@Autowired
	private ReportPerformanceDao reportPerformanceDao;

	@Autowired
	private DutyDetailDao dutyDetailDao;

	@Autowired
	private AuditRecordsDao auditRecordsDao;

	public Page<ReportRecord> findreportRecord(Page<ReportRecord> page,
			ReportRecord reportRecord) {
		reportRecord.getSqlMap().put("dsf",
				dataScopeFilter(reportRecord.getCurrentUser(), "o", "a"));
		// 设置分页参数
		reportRecord.setPage(page);
		// 执行分页查询
		page.setList(reportRecordDao.findList(reportRecord));

		for (ReportRecord data : page.getList()) {
			List<Task> tasks = taskService.createTaskQuery()
					.processInstanceId(data.getAct().getProcInsId()).list();
			for (Task task : tasks) {
				data.getAct().setTaskDefKey(task.getTaskDefinitionKey());
				data.getAct().setTaskId(task.getId());
				data.getAct().setTaskName(task.getName());
			}
		}
		return page;

	}

	@Override
	public List<ReportRecord> findList(ReportRecord reportRecord) {
		reportRecord.getSqlMap().put("dsf",
				dataScopeFilter(reportRecord.getCurrentUser(), "o", "a"));

		List<ReportRecord> recordList = dao.findList(reportRecord);

		// 设置相关流程实例任务
		for (ReportRecord data : recordList) {
			List<Task> tasks = taskService.createTaskQuery()
					.processInstanceId(data.getAct().getProcInsId()).list();
			for (Task task : tasks) {
				data.getAct().setTaskDefKey(task.getTaskDefinitionKey());
				data.getAct().setTaskId(task.getId());
				data.getAct().setTaskName(task.getName());
			}
		}

		return recordList;
	}

	/**
	 * 启动流程
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(ReportRecord reportRecord, String[] PD, String[] str,
			String applyUser) {

		// 申请发起
		if (StringUtils.isBlank(reportRecord.getAct().getProcInsId())) {

			// 启动流程
			reportRecord.getAct().setProcInsId(
					actTaskService.startProcess(PD[0], PD[1],
							reportRecord.getId(), str));

		}

		// 重新编辑申请
		else {
			reportRecord = this.get(reportRecord);
			reportRecord.setCreateBy(reportRecord.getUser());
			Task task = taskService.createTaskQuery()
					.processInstanceId(reportRecord.getAct().getProcInsId())
					.list().get(0);

			reportRecord.getAct().setTaskDefKey(task.getTaskDefinitionKey());
			reportRecord.getAct().setTaskId(task.getId());
			reportRecord.getAct().setTaskName(task.getName());
			reportRecord.preUpdate();
			dao.update(reportRecord);
			if (reportRecord.getAct().getTaskDefKey().equals(applyUser)) {

				reportRecord
						.getAct()
						.setComment(
								("yes".equals(reportRecord.getAct().getFlag()) ? "[重申] "
										: "[销毁] ")
										+ reportRecord.getAct().getComment());

				// 完成流程任务
				Map<String, Object> vars = Maps.newHashMap();
				vars.put("pass",
						"yes".equals(reportRecord.getAct().getFlag()) ? "1"
								: "0");
				actTaskService.complete(reportRecord.getAct().getTaskId(),
						reportRecord.getAct().getProcInsId(), reportRecord
								.getAct().getComment(), vars);
			}

		}

	}

	/**
	 * 审核审批保存
	 * 
	 * @param reportedWorkloade
	 */
	@Transactional(readOnly = false)
	public void saveAudit(ReportedWorkloade reportedWorkloade) {

		// 设置意见
		reportedWorkloade
				.getReportRecord()
				.getAct()
				.setComment(
						("yes".equals(reportedWorkloade.getReportRecord()
								.getAct().getFlag()) ? "[同意] " : "[驳回] ")
								+ reportedWorkloade.getReportRecord().getAct()
										.getComment());

		reportedWorkloade.preUpdate();

		Task task = taskService
				.createTaskQuery()
				.processInstanceId(
						reportedWorkloade.getReportRecord().getAct()
								.getProcInsId()).list().get(0);

		reportedWorkloade.getReportRecord().getAct()
				.setTaskDefKey(task.getTaskDefinitionKey());
		reportedWorkloade.getReportRecord().getAct().setTaskId(task.getId());
		reportedWorkloade.getReportRecord().getAct()
				.setTaskName(task.getName());

		AuditRecords ar = new AuditRecords();
		ar.setBusinessKey(ActUtils.PD_HR_WORKLOAD_1[0]);
		ar.setTaskKey(reportedWorkloade.getReportRecord().getAct()
				.getTaskDefKey());
		ar.setRecordId(reportedWorkloade.getReportRecord().getId());
		ar.setId(reportedWorkloade.getReportRecord().getId());
		ar.setUpdateBy(UserUtils.getUser());
		ar.setUpdateDate(new Date());

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = reportedWorkloade.getReportRecord().getAct()
				.getTaskDefKey();
		// 审核环节
		if ("workloade1".equals(taskDefKey)) {

		} else if ("workloade2".equals(taskDefKey)) {
			reportedWorkloade.getReportRecord().setDataState("3");
			this.save(reportedWorkloade.getReportRecord());
		} else if ("workloade3".equals(taskDefKey)) {
			reportedWorkloade.getReportRecord().setDataState("6");
			this.save(reportedWorkloade.getReportRecord());
		}

		// 未知环节，直接返回
		else {
			return;
		}
		ar.setAuditOpinion(reportedWorkloade.getReportRecord().getAct()
				.getComment());
		ar.preInsert();
		auditRecordsDao.insert(ar);

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();

		if ("yes"
				.equals(reportedWorkloade.getReportRecord().getAct().getFlag())) {
			vars.put("pass", "1");
		} else if ("no".equals(reportedWorkloade.getReportRecord().getAct()
				.getFlag())) {
			reportedWorkloade.getReportRecord().setDataState("1");
			this.save(reportedWorkloade.getReportRecord());
			vars.put("pass", "0");
		}

		// vars.put("pass", "yes".equals(attendance.getAct().getFlag()) ? "1"
		// : "0");
		actTaskService.complete(reportedWorkloade.getReportRecord().getAct()
				.getTaskId(), reportedWorkloade.getReportRecord().getAct()
				.getProcInsId(), reportedWorkloade.getReportRecord().getAct()
				.getComment(), vars);

	}

	/**
	 * 审核审批保存
	 * 
	 * @param dutyDetail
	 */
	@Transactional(readOnly = false)
	public void saveAudit(DutyDetail dutyDetail) {

		// 设置意见
		dutyDetail
				.getReportRecord()
				.getAct()
				.setComment(
						("yes".equals(dutyDetail.getReportRecord().getAct()
								.getFlag()) ? "[同意] " : "[驳回] ")
								+ dutyDetail.getReportRecord().getAct()
										.getComment());

		dutyDetail.preUpdate();

		Task task = taskService
				.createTaskQuery()
				.processInstanceId(
						dutyDetail.getReportRecord().getAct().getProcInsId())
				.list().get(0);

		dutyDetail.getReportRecord().getAct()
				.setTaskDefKey(task.getTaskDefinitionKey());
		dutyDetail.getReportRecord().getAct().setTaskId(task.getId());
		dutyDetail.getReportRecord().getAct().setTaskName(task.getName());

		AuditRecords ar = new AuditRecords();
		ar.setBusinessKey(ActUtils.PD_HR_DUTY[0]);
		ar.setTaskKey(dutyDetail.getReportRecord().getAct().getTaskDefKey());
		ar.setRecordId(dutyDetail.getReportRecord().getId());
		ar.setId(dutyDetail.getReportRecord().getId());
		ar.setUpdateBy(UserUtils.getUser());
		ar.setUpdateDate(new Date());

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = dutyDetail.getReportRecord().getAct()
				.getTaskDefKey();
		// 审核环节
		if ("performancemanager1".equals(taskDefKey)) {

		} else if ("performancemanager2".equals(taskDefKey)) {
			if (dutyDetail.getReportRecord().getAct().getFlag().equals("yes")) {
				dutyDetail.getReportRecord().setDataState("3");
				this.save(dutyDetail.getReportRecord());
			}

		}
		// 未知环节，直接返回
		else {
			return;
		}

		ar.setAuditOpinion(dutyDetail.getReportRecord().getAct().getComment());
		ar.preInsert();
		auditRecordsDao.insert(ar);

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();

		if ("yes".equals(dutyDetail.getReportRecord().getAct().getFlag())) {
			vars.put("pass", "1");
		} else if ("no".equals(dutyDetail.getReportRecord().getAct().getFlag())) {
			dutyDetail.getReportRecord().setDataState("1");
			this.save(dutyDetail.getReportRecord());
			vars.put("pass", "0");
		}
		// vars.put("pass", "yes".equals(attendance.getAct().getFlag()) ? "1"
		// : "0");
		actTaskService.complete(dutyDetail.getReportRecord().getAct()
				.getTaskId(), dutyDetail.getReportRecord().getAct()
				.getProcInsId(), dutyDetail.getReportRecord().getAct()
				.getComment(), vars);

	}

	/**
	 * 审核审批保存
	 * 
	 * @param reportPerformance
	 */
	@Transactional(readOnly = false)
	public void saveAudit(ReportPerformance reportPerformance, String[] aduitStr) {

		// 设置意见
		reportPerformance
				.getReportRecord()
				.getAct()
				.setComment(
						("yes".equals(reportPerformance.getReportRecord()
								.getAct().getFlag()) ? "[同意] " : "[驳回] ")
								+ reportPerformance.getReportRecord().getAct()
										.getComment());

		reportPerformance.preUpdate();

		Task task = taskService
				.createTaskQuery()
				.processInstanceId(
						reportPerformance.getReportRecord().getAct()
								.getProcInsId()).list().get(0);

		reportPerformance.getReportRecord().getAct()
				.setTaskDefKey(task.getTaskDefinitionKey());
		reportPerformance.getReportRecord().getAct().setTaskId(task.getId());
		reportPerformance.getReportRecord().getAct()
				.setTaskName(task.getName());

		AuditRecords ar = new AuditRecords();
		ar.setBusinessKey(aduitStr[0]);
		ar.setTaskKey(reportPerformance.getReportRecord().getAct()
				.getTaskDefKey());
		ar.setRecordId(reportPerformance.getReportRecord().getId());
		ar.setId(reportPerformance.getReportRecord().getId());
		ar.setUpdateBy(UserUtils.getUser());
		ar.setUpdateDate(new Date());

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = reportPerformance.getReportRecord().getAct()
				.getTaskDefKey();

		// 审核环节
		if ("performancemanager1".equals(taskDefKey)) {

		} else if ("performancemanager2".equals(taskDefKey)) {
			reportPerformance.getReportRecord().setDataState("3");
			this.save(reportPerformance.getReportRecord());
		} else if ("performancemanager_2".equals(taskDefKey)) {
			reportPerformance.getReportRecord().setDataState("3");
			this.save(reportPerformance.getReportRecord());
		} else if ("usertask1".equals(taskDefKey)) {

		}
		// 未知环节，直接返回
		else {
			return;
		}

		ar.setAuditOpinion(reportPerformance.getReportRecord().getAct()
				.getComment());
		ar.preInsert();
		auditRecordsDao.insert(ar);

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();

		if ("yes"
				.equals(reportPerformance.getReportRecord().getAct().getFlag())) {
			vars.put("pass", "1");
		} else if ("no".equals(reportPerformance.getReportRecord().getAct()
				.getFlag())) {
			reportPerformance.getReportRecord().setDataState("1");
			this.save(reportPerformance.getReportRecord());
			vars.put("pass", "0");
		}
		// vars.put("pass", "yes".equals(attendance.getAct().getFlag()) ? "1"
		// : "0");
		actTaskService.complete(reportPerformance.getReportRecord().getAct()
				.getTaskId(), reportPerformance.getReportRecord().getAct()
				.getProcInsId(), reportPerformance.getReportRecord().getAct()
				.getComment(), vars);

	}
}
