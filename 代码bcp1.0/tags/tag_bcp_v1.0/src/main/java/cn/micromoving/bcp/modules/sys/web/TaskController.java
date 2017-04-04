/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.modules.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.act.entity.Act;
import cn.micromoving.bcp.modules.act.service.ActTaskService;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.dao.AttendanceDao;
import cn.micromoving.bcp.modules.hr.dao.DegreeEduDao;
import cn.micromoving.bcp.modules.hr.dao.DutyDetailDao;
import cn.micromoving.bcp.modules.hr.dao.ReportPerformanceDao;
import cn.micromoving.bcp.modules.hr.dao.ReportRecordDao;
import cn.micromoving.bcp.modules.hr.dao.ReportedWorkloadeDao;
import cn.micromoving.bcp.modules.hr.dao.SalaryInstanceDao;
import cn.micromoving.bcp.modules.hr.dao.TrainExperienceDao;
import cn.micromoving.bcp.modules.hr.entity.Attendance;
import cn.micromoving.bcp.modules.hr.entity.DegreeEdu;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstance;
import cn.micromoving.bcp.modules.hr.entity.TrainExperience;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

/**
 * 用户任务Controller
 * 
 * @author songcm
 * @version 2016-7-14
 */
@Controller
@RequestMapping(value = { "${adminPath}/sys/task" })
public class TaskController extends BaseController {

	@Autowired
	private ActTaskService service;

	@Autowired
	private AttendanceDao attendanceDao;

	@Autowired
	private SalaryInstanceDao salaryInstanceDao;

	@Autowired
	private DutyDetailDao dutyDetailDao;

	@Autowired
	private ReportPerformanceDao reportPerformanceDao;

	@Autowired
	private ReportedWorkloadeDao reportedWorkloadeDao;

	@Autowired
	private SystemService systemService;

	@Autowired
	private ReportRecordDao reportRecordDao;

	@Autowired
	private DegreeEduDao degreeEduDao;
	
	@Autowired
	private TrainExperienceDao trainExperienceDao;

	@RequestMapping(value = { "todolist", "" })
	public String todolist(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		List<Act> list = service.todoList(new Act());
		for (Act act : list) {
			StringBuffer sb = new StringBuffer();
			String procKey = act.getProcDef().getKey();
			String procInsId = act.getTask().getProcessInstanceId();
			/* 判断流程key,如果当前task为人事（请假）流程，取出Attendance对象，绑定申请者，申请时间。 */
			if (ActUtils.PD_HR_LEAVE[0].equals(procKey)) {
				Attendance entity = new Attendance();
				entity.setProcInsId(procInsId);
				entity = attendanceDao.getByProcInsId(entity);
				/* 绑定申请者，申请时间。 */
				if (null != entity) {
					act.setApplyDate(entity.getApplyDate());
					act.setName(entity.getUser().getName());
					act.setRecordId(entity.getId());
				}
				continue;
			}
			if (ActUtils.PD_HR_SALARY[0].equals(procKey)) {
				SalaryInstance entity = new SalaryInstance();
				entity.setProcInsId(procInsId);
				entity = salaryInstanceDao.getByProcInsId(entity);
				/* 绑定申请者，申请时间。 */
				if (null != entity) {
					entity.setCreateBy(UserUtils.get(entity.getCreateBy()
							.getId()));
					sb.append("[").append(act.getProcDef().getName())
					.append("] ");
					sb.append(entity.getCreateBy().getOffice().getName() + " ");
					sb.append(entity.getYearMonth());
					act.setTitle(sb.toString());
					act.setApplyDate(entity.getCreateDate());
					act.setName(entity.getCreateBy().getName());
					act.setRecordId(entity.getId());
					model.addAttribute("salaryInstance", entity);
				}
				continue;
			}

			// if () {
			// ReportRecord entity = new ReportRecord();
			// entity.setProcInsId(procInsId);
			// entity = reportRecordDao.getByProcInsId(entity);
			// /* 绑定申请者，申请时间。 */
			// if (null != entity) {
			// act.setApplyDate(entity.getUpdateDate());
			// act.setName(entity.getUser().getName());
			// act.setRecordId(entity.getId());
			// model.addAttribute("dutyDetail", entity);
			// }
			// }
			/* 上报绩效、辅导员绩效、值班、工作量流程 */
			if (ActUtils.PD_HR_PERFORMANCE[0].equals(procKey)
					|| ActUtils.PD_HR_DUTY[0].equals(procKey)
					|| ActUtils.PD_HR_WORKLOAD[0].equals(procKey)) {
				ReportRecord entity = new ReportRecord();
				entity.setProcInsId(procInsId);
				entity = reportRecordDao.getByProcInsId(entity);
				/* 绑定申请者，申请时间。 */
				if (null != entity) {
					sb.append("[").append(act.getProcDef().getName())
							.append("] ");
					if (entity.getDataClassification().equals("1")) {
						sb.append("职员 ");
					} else if (entity.getDataClassification().equals("5")) {
						sb.append("辅导员 ");
					}else if (entity.getDataClassification().equals("4")) {
						sb.append("教师 ");
						act.setFlag("4");
					}
					sb.append(entity.getOffice().getName() + " ");
					sb.append(entity.getYearMonth());
					act.setTitle(sb.toString());
					act.setApplyDate(entity.getUpdateDate());
					act.setFlag(entity.getDataClassification());
					if(entity.getUser()!=null){
						act.setName(entity.getUser().getName());
					}
					act.setRecordId(entity.getId());

					model.addAttribute("reportedPerformance", entity);
				}
				continue;
			}

			// if () {
			// ReportRecord entity = new ReportRecord();
			// entity.setProcInsId(procInsId);
			// entity = reportRecordDao.getByProcInsId(entity);
			// /* 绑定申请者，申请时间。 */
			// if (null != entity) {
			// act.setApplyDate(entity.getUpdateDate());
			// act.setName(entity.getUser().getName());
			// act.setRecordId(entity.getId());
			// model.addAttribute("reportedWorkloade", entity);
			// }
			// }

			if (ActUtils.PD_HR_DEGREEEDU[0].equals(procKey)) {
				DegreeEdu entity = new DegreeEdu();
				entity.setProcInsId(procInsId);
				entity = degreeEduDao.getByProcInsId(entity);
				/* 绑定申请者，申请时间。 */
				if (null != entity) {
					entity.setCreateBy(UserUtils.get(entity.getUser()
							.getId()));
					sb.append("[").append(act.getProcDef().getName())
					.append("] ");
					sb.append(entity.getCreateBy().getOffice().getName());
					act.setTitle(sb.toString());
					act.setApplyDate(entity.getCreateDate());
					act.setName(entity.getUser().getName());
					act.setRecordId(entity.getId());
					model.addAttribute("degreeEdu", entity);
				}
				continue;
			}
			if (ActUtils.PD_HR_TRAIN_EXPERIENCE[0].equals(procKey)) {
				TrainExperience entity = new TrainExperience();
				entity.setProcInsId(procInsId);
				entity = trainExperienceDao.getByProcInsId(entity);
				/* 绑定申请者，申请时间。 */
				if (null != entity) {
					entity.setCreateBy(UserUtils.get(entity.getCreateBy()
							.getId()));
					sb.append("[").append(act.getProcDef().getName())
					.append("] ");
					sb.append(entity.getCreateBy().getOffice().getName());
					act.setTitle(sb.toString());
					act.setApplyDate(entity.getCreateDate());
					act.setName(entity.getUser().getName());
					act.setRecordId(entity.getId());
					model.addAttribute("degreeEdu", entity);
				}
				continue;
			}

		}

		model.addAttribute("list", list);
		return "modules/sys/taskList";
	}

	@RequestMapping(value = { "hislist", "" })
	public String hislist(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Act act = new Act();
		Page<Act> page = service.historicList(new Page<Act>(), act);

		for (Act a : page.getList()) {
			String procKey = a.getProcDef().getKey();
			String procInsId = a.getHistTask().getProcessInstanceId();
			/* 判断流程key,如果当前task为人事（请假）流程，取出Attendance对象，绑定申请者，申请时间。 */
			if (ActUtils.PD_HR_LEAVE[0].equals(procKey)) {
				Attendance entity = new Attendance();
				entity.setProcInsId(procInsId);
				entity = attendanceDao.getByProcInsId(entity);
				/* 绑定申请者，申请时间。 */
				if (null != entity) {
					a.setApplyDate(entity.getApplyDate());
					a.setName(entity.getUser().getName());
					a.setRecordId(entity.getId());
				}
			}
			if (ActUtils.PD_HR_DEGREEEDU[0].equals(procKey)) {
				DegreeEdu entity = new DegreeEdu();
				entity.setProcInsId(procInsId);
				entity = degreeEduDao.getByProcInsId(entity);
				/* 绑定申请者，申请时间。 */
				if (null != entity) {
					a.setApplyDate(entity.getCreateDate());
					a.setName(entity.getUser().getName());
					a.setRecordId(entity.getId());
				}
			}
			if (ActUtils.PD_HR_SALARY[0].equals(procKey)) {
				SalaryInstance entity = new SalaryInstance();
				entity.setProcInsId(procInsId);
				entity = salaryInstanceDao.getByProcInsId(entity);
				/* 绑定申请者，申请时间。 */
				if (null != entity) {
					entity.setCreateBy(UserUtils.get(entity.getCreateBy()
							.getId()));
					a.setApplyDate(entity.getCreateDate());
					a.setName(entity.getCreateBy().getName());
					a.setRecordId(entity.getId());
				}
			}

			if (ActUtils.PD_HR_DUTY[0].equals(procKey)) {
				ReportRecord entity = new ReportRecord();
				entity.setProcInsId(procInsId);
				entity = reportRecordDao.getByProcInsId(entity);
				/* 绑定申请者，申请时间。 */
				if (null != entity) {
					act.setApplyDate(entity.getUpdateDate());
					act.setName(entity.getUser().getName());
					act.setRecordId(entity.getId());
					model.addAttribute("reportRecord", entity);
				}
			}

			if (ActUtils.PD_HR_PERFORMANCE[0].equals(procKey)) {
				ReportRecord entity = new ReportRecord();
				entity.setProcInsId(procInsId);
				entity = reportRecordDao.getByProcInsId(entity);
				/* 绑定申请者，申请时间。 */
				if (null != entity) {
					act.setApplyDate(entity.getUpdateDate());
					act.setName(entity.getUser().getName());
					act.setRecordId(entity.getId());
					model.addAttribute("reportRecord", entity);
				}
			}

			if (ActUtils.PD_HR_WORKLOAD[0].equals(procKey)) {
				ReportRecord entity = new ReportRecord();
				entity.setProcInsId(procInsId);
				entity = reportRecordDao.getByProcInsId(entity);
				/* 绑定申请者，申请时间。 */
				if (null != entity) {
					act.setApplyDate(entity.getUpdateDate());
					act.setName(entity.getUser().getName());
					act.setRecordId(entity.getId());
					model.addAttribute("reportRecord", entity);
				}
			}

		}
		model.addAttribute("page", page);
		return "modules/sys/taskList";
	}

}
