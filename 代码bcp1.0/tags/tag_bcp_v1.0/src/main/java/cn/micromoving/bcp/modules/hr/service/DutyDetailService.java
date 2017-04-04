package cn.micromoving.bcp.modules.hr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import cn.micromoving.bcp.modules.hr.dao.ReportRecordDao;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.entity.AuditRecords;
import cn.micromoving.bcp.modules.hr.entity.Duty;
import cn.micromoving.bcp.modules.hr.entity.DutyDetail;
import cn.micromoving.bcp.modules.hr.entity.EmpPerformanceLevel;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.ReportedWorkloade;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.entity.Warm;
import cn.micromoving.bcp.modules.sys.dao.UserDao;
import cn.micromoving.bcp.modules.sys.entity.Role;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.mapper.Mapper.Null;

@Service
@Transactional(readOnly = true)
public class DutyDetailService extends CrudService<DutyDetailDao, DutyDetail> {
	@Autowired
	private ReportRecordDao recordDao;
	@Autowired
	private SystemService systemService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	private AuditRecordsDao auditRecordsDao;
	@Autowired
	private SalEmpViewDao salEmpViewDao;
	@Autowired
	private DutyService dutyService;
	@Autowired
	private ReportRecordService recordService;

	public Page<SalEmpView> findSalEmpView(Page<SalEmpView> page,
			SalEmpView salEmpView) {
		// TODO Auto-generated method stub
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		salEmpView.getSqlMap().put("dsf",
				dataScopeFilter(salEmpView.getCurrentUser(), "o", "a"));
		// 设置分页参数

		// 执行分页查询
		page.setList(salEmpViewDao.findList(salEmpView));
		return page;

	}

	public void batchSave(DutyDetail dutyDetail) {
		/* 保存上报1条记录 */
		ReportRecord data = dutyDetail.getReportRecord();
		if(StringUtils.isNotBlank(data.getId())){
		    data = recordService.get(data);
		    data.setCreateBy(data.getUser());
		}
		data.setOffice(UserUtils.getUser().getOffice());
		/* 提交状态。1：草稿；2：审批中（走流程中）；3：审批完成（审核结束） */
		/* 此处状态只能为1：暂存（草稿），2：提交，也就是审批中（走流程中）。 */
		data.setDataState(dutyDetail.getDataState());
		if (!StringUtils.isBlank(data.getId())) {
			/* 当第二次保存时，先将之前保存的相关记录删除，再保存新的记录。 */
			dao.deleteByReportId(dutyDetail);
		}
		recordService.save(data);

		/* 保存上报值班详情的n条记录 */
		for (int index = 0; index < dutyDetail.getEmployeeIdList().size(); index++) {
			String userId = dutyDetail.getEmployeeIdList().get(index);
			Integer noondutydays = dutyDetail.getNoonDutyDaysList().get(index);
			Integer nightdutydays = dutyDetail.getNightDutyDaysList()
					.get(index);
			Integer weekendsDutyDays = dutyDetail.getWeekwedsDutyDaysList()
					.get(index);
			Integer holidayDutyDays = dutyDetail.getHolidayDatyDuysList().get(
					index);
			/* 如果此人值班不填，不上报。 */

			String id = dutyDetail.getDataIdList().get(index);
			User user = new User(userId);
			if ("1".equals(dutyDetail.getDataState())
					|| ("2".equals(dutyDetail.getDataState()) && noondutydays != null)) {
				DutyDetail entity1 = new DutyDetail();
				/* 上报值班ID */
				entity1.setId(id);
				/* 上报记录ID */
				entity1.setReportRecord(data);
				/* 职员ID */
				entity1.setUser(user);
				/* 值班类型 */
				entity1.setDutyType("1");
				entity1.setDutyDays(noondutydays);
				this.save(entity1);
			}
			if ("1".equals(dutyDetail.getDataState())
					|| ("2".equals(dutyDetail.getDataState()) && nightdutydays != null)) {
				DutyDetail entity2 = new DutyDetail();
				entity2.setId(id);
				entity2.setReportRecord(data);
				entity2.setUser(user);
				entity2.setDutyType("2");
				entity2.setDutyDays(nightdutydays);
				this.save(entity2);
			}
			if ("1".equals(dutyDetail.getDataState())
					|| ("2".equals(dutyDetail.getDataState()) && weekendsDutyDays != null)) {
				DutyDetail entity3 = new DutyDetail();
				entity3.setId(id);
				entity3.setReportRecord(data);
				entity3.setUser(user);
				entity3.setDutyType("3");
				entity3.setDutyDays(weekendsDutyDays);
				this.save(entity3);
			}
			if ("1".equals(dutyDetail.getDataState())
					|| ("2".equals(dutyDetail.getDataState()) && holidayDutyDays != null)) {
				DutyDetail entity4 = new DutyDetail();
				entity4.setId(id);
				entity4.setReportRecord(data);
				entity4.setUser(user);
				entity4.setDutyType("4");
				entity4.setDutyDays(holidayDutyDays);
				this.save(entity4);
			}
		}
	}

	public boolean existReportedWorkloade(DutyDetail data) {

		return dao.count(data) > 0 ? true : false;
	}
	public boolean ccTime(DutyDetail dutyDetail)
			throws ParseException {
		boolean data = false;
		List<ReportRecord> list = recordService.findList(dutyDetail
				.getReportRecord());
		list.remove(dutyDetail.getReportRecord());
		for (ReportRecord reportRecord : list) {
			if(dutyDetail.getReportRecord().getYearMonth().equals(reportRecord.getYearMonth())){
				data = true;
			}
		}
		return data;
	}

}
