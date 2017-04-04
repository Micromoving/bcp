package cn.micromoving.bcp.modules.hr.service;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.act.service.ActTaskService;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.dao.AuditRecordsDao;
import cn.micromoving.bcp.modules.hr.dao.TrainExperienceDao;
import cn.micromoving.bcp.modules.hr.entity.AuditRecords;
import cn.micromoving.bcp.modules.hr.entity.TrainExperience;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Maps;

@Service
@Transactional(readOnly = true)
public class TrainExperienceService extends
		CrudService<TrainExperienceDao, TrainExperience> {

	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private AuditRecordsDao auditRecordsDao;
	@Autowired
	private TrainExperienceDao trainExperienceDao;

	/**
	 * 启动流程
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(TrainExperience trainExperience, String[] PD,
			String[] str, String applyUser) {

		// 申请发起
		if (StringUtils.isBlank(trainExperience.getProcessInstanceId())) {
			trainExperience.setDataState("1");
			this.save(trainExperience);
			// 启动流程
			trainExperience.getAct().setProcInsId(
					actTaskService.startProcess(PD[0], PD[1],
							trainExperience.getId(), str));

		}

		// 重新编辑申请
		else {
			trainExperience = this.get(trainExperience);
			trainExperience.setCreateBy(trainExperience.getUser());
			Task task = taskService.createTaskQuery()
					.processInstanceId(trainExperience.getAct().getProcInsId())
					.list().get(0);

			trainExperience.getAct().setTaskDefKey(task.getTaskDefinitionKey());
			trainExperience.getAct().setTaskId(task.getId());
			trainExperience.getAct().setTaskName(task.getName());
			trainExperience.preUpdate();
			dao.update(trainExperience);
			if (trainExperience.getAct().getTaskDefKey().equals(applyUser)) {

				trainExperience
						.getAct()
						.setComment(
								("yes".equals(trainExperience.getAct()
										.getFlag()) ? "[重申] " : "[销毁] ")
										+ trainExperience.getAct().getComment());

				// 完成流程任务
				Map<String, Object> vars = Maps.newHashMap();
				vars.put("pass", "yes".equals(trainExperience.getAct()
						.getFlag()) ? "1" : "0");
				actTaskService.complete(trainExperience.getAct().getTaskId(),
						trainExperience.getAct().getProcInsId(),
						trainExperience.getAct().getComment(), vars);
			}

		}

	}

	/**
	 * 审核审批保存
	 * 
	 * @param salaryInstance
	 */
	@Transactional(readOnly = false)
	public void saveAudit(TrainExperience trainExperience) {

		// 设置意见
		trainExperience.getAct()
				.setComment(
						("yes".equals(trainExperience.getAct().getFlag()) ? "[同意] "
								: "[驳回] ") + trainExperience.getAct().getComment());

		/* 获取当前人员对应的task */
		Task task = taskService.createTaskQuery()
				.processInstanceId(trainExperience.getAct().getProcInsId()).list()
				.get(0);
		/* 设置工资实例的task星系 */
		trainExperience.getAct().setTaskDefKey(task.getTaskDefinitionKey());
		trainExperience.getAct().setTaskId(task.getId());
		trainExperience.getAct().setTaskName(task.getName());
		/* 设置意见的详细信息 */
		AuditRecords ar = new AuditRecords();
		ar.setBusinessKey(ActUtils.PD_HR_TRAIN_EXPERIENCE[0]);
		ar.setTaskKey(trainExperience.getAct().getTaskDefKey());
		ar.setRecordId(trainExperience.getId());
		ar.setId(trainExperience.getId());
		ar.setUpdateBy(UserUtils.getUser());
		ar.setUpdateDate(new Date());

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = trainExperience.getAct().getTaskDefKey();
		// 审核环节
		if ("usertask1".equals(taskDefKey)) {
			trainExperience.setDataState("2");
		} else if ("usertask2".equals(taskDefKey)) {
			trainExperience.setDataState("3");
		} else if ("usertask3".equals(taskDefKey)) {
			trainExperience.setDataState("4");
		} else if ("usertask4".equals(taskDefKey)) {
			trainExperience.setDataState("5");
		} else if ("usertask5".equals(taskDefKey)) {
			trainExperience.setDataState("6");
		} else if ("usertask6".equals(taskDefKey)) {
			trainExperience.setDataState("7");
		} 
		// 未知环节，直接返回
		else {
			return;
		}
		/* 保存意见 */
		ar.setAuditOpinion(trainExperience.getAct().getComment());
		trainExperience.preUpdate();
		trainExperienceDao.update(trainExperience);
		ar.preInsert();
		auditRecordsDao.insert(ar);

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();

		if ("yes".equals(trainExperience.getAct().getFlag())) {
			vars.put("pass", "1");
		} else if ("no".equals(trainExperience.getAct().getFlag())) {
			vars.put("pass", "0");
		}
		// vars.put("pass", "yes".equals(attendance.getAct().getFlag()) ? "1"
		// : "0");
		/* 如果一切顺利，则将流程走到下一个节点去 */
		actTaskService
				.complete(trainExperience.getAct().getTaskId(), trainExperience.getAct()
						.getProcInsId(), trainExperience.getAct().getComment(), vars);

	}

}
