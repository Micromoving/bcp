package cn.micromoving.bcp.modules.hr.service;

import java.text.ParseException;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.hr.dao.ReportPerformanceDao;
import cn.micromoving.bcp.modules.hr.dao.ReportRecordDao;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.dao.SalaryInstanceDao;
import cn.micromoving.bcp.modules.hr.entity.DutyDetail;
import cn.micromoving.bcp.modules.hr.entity.ReportPerformance;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.ReportedWorkloade;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.sys.dao.UserDao;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Service
@Transactional(readOnly = true)
public class ReportPerformanceService extends
		CrudService<ReportPerformanceDao, ReportPerformance> {

	@Autowired
	private ReportRecordService recordService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SalEmpViewDao salEmpViewDao;

	public Page<SalEmpView> findSalEmpView(Page<SalEmpView> page,
			SalEmpView salEmpView) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		salEmpView.getSqlMap().put("dsf",
				dataScopeFilter(salEmpView.getCurrentUser(), "o", "a"));
		// 设置分页参数

		// 执行分页查询
		page.setList(salEmpViewDao.findList(salEmpView));
		return page;

	}

	public void batchSave(ReportPerformance reportPerformance) {
		/* 保存上报1条记录 */
		ReportRecord data = reportPerformance.getReportRecord();
		if(StringUtils.isNotBlank(data.getId())){
		    data = recordService.get(data);
		    data.setCreateBy(data.getUser());
		}
		data.setOffice(UserUtils.getUser().getOffice());
		/* 提交状态。1：草稿；2：审批中（走流程中）；3：审批完成（审核结束） */
		/* 此处状态只能为1：暂存（草稿），2：提交，也就是审批中（走流程中）。 */
		data.setDataState(reportPerformance.getDataState());
		if(!StringUtils.isBlank(data.getId())){
			dao.deleteByReportId(reportPerformance);
		}

		recordService.save(data);
		/* 保存上报绩效详情的n条记录 */
		for (int index = 0; index < reportPerformance.getEmployeeIdList()
				.size(); index++) {
			String userId = reportPerformance.getEmployeeIdList().get(index);
			Double performanceSalary = reportPerformance
					.getPerformanceSalaryList().get(index);
			/* 如果此人绩效不填，不上报。 */
			if (null == performanceSalary
					|| performanceSalary.doubleValue() == 0.0) {
				continue;
			}

			User user = new User(userId);

			ReportPerformance entity = new ReportPerformance();
			/* 上报记录ID */
			entity.setReportRecord(data);
			/* 职员ID */
			entity.setUser(user);
			/* 绩效工资 */
			entity.setPerformanceSalary(performanceSalary);
			this.save(entity);

		}
		
		
	}
	/* 判断是否第一次添加纪录 */
	public boolean existReportPerformance(ReportPerformance data) {
		/* 存在上报工作量，返回到dao中，看值是否大于0 */
		return dao.count(data) > 0 ? true : false;
	}
	public boolean ccTime(ReportPerformance reportPerformance)
			throws ParseException {
		boolean data = false;
		List<ReportRecord> list = recordService.findList(reportPerformance
				.getReportRecord());
		list.remove(reportPerformance.getReportRecord());
		for (ReportRecord reportRecord : list) {
			if(reportPerformance.getReportRecord().getYearMonth().equals(reportRecord.getYearMonth())){
				data = true;
			}
		}
		return data;
	}

}
