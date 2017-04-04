package cn.micromoving.bcp.modules.hr.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.hr.dao.ReportedWorkloadeDao;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.entity.ReportPerformance;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.ReportedWorkloade;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.sys.dao.UserDao;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Service
@Transactional(readOnly = true)
public class ReportedWorkloadeService extends
		CrudService<ReportedWorkloadeDao, ReportedWorkloade> {
	/* 上报几记录Service */
	@Autowired
	private ReportRecordService recordService;
	/* 多表综合视图DAO */
	@Autowired
	private SalEmpViewDao salEmpViewDao;
	/* 用户DAO */
	@Autowired
	private UserDao userDao;

	/* 从视图中分页查询信息 */
	public Page<SalEmpView> findSalEmpView(Page<SalEmpView> page,
			SalEmpView salEmpView) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		salEmpView.getSqlMap().put("dsf",
				dataScopeFilter(salEmpView.getCurrentUser(), "o", "a"));
		// 执行分页查询
		page.setList(salEmpViewDao.findList(salEmpView));
		return page;

	}

	/* 批量保存 */
	public void batchSave(ReportedWorkloade reportedWorkloade) {
		/* 保存上报1条记录 */
		ReportRecord data = reportedWorkloade.getReportRecord();
		if (StringUtils.isNotBlank(data.getId())) {
			data = recordService.get(data);
			data.setCreateBy(data.getUser());
		}
		data.setOffice(UserUtils.getUser().getOffice());
		data.setDataClassification("2");
		/* 提交状态。1：草稿；2：审批中（走流程中）；3：审批完成（审核结束） */
		/* 此处状态只能为1：暂存（草稿），2：提交，也就是审批中（走流程中）。 */
		data.setDataState(reportedWorkloade.getDataState());
		/* 如果用户是初次上报，则不做删除相关记录。如果是暂存后，再次提交（暂存），则删除之前的相关记录。 */
		if (!StringUtils.isBlank(data.getId())) {
			/* 当第二次保存时，先将之前保存的相关记录删除，再保存新的记录。 */
			dao.deleteByReportId(reportedWorkloade);
		}
		recordService.save(data);
		for (int index = 0; index < reportedWorkloade.getEmployeeIdList()
				.size(); index++) {
			String userId = reportedWorkloade.getEmployeeIdList().get(index);
			Double workloade = reportedWorkloade.getWorkloadList().get(index);
			/* 如果此人绩效不填，不上报。 */
			if (null == workloade || workloade.doubleValue() == 0.0) {
				continue;
			}

			User user = new User(userId);

			ReportedWorkloade entity = new ReportedWorkloade();
			/* 上报记录ID */
			entity.setReportRecord(data);
			/* 职员ID */
			entity.setUser(user);
			/* 绩效工资 */
			entity.setWorkload(workloade);
			this.save(entity);

		}

	}

	/* 判断是否第一次添加纪录 */
	public boolean existReportedWorkloade(ReportedWorkloade data) {
		/* 存在上报工作量，返回到dao中，看值是否大于0 */
		return dao.count(data) > 0 ? true : false;
	}

	public void saveFile(MultipartFile file,
			MultipartHttpServletRequest request, String path) {

	}
	public boolean ccTime(ReportedWorkloade reportedWorkloade)
			throws ParseException {
		boolean data = false;
		List<ReportRecord> list = recordService.findList(reportedWorkloade
				.getReportRecord());
		list.remove(reportedWorkloade.getReportRecord());
		for (ReportRecord reportRecord : list) {
			if(reportedWorkloade.getReportRecord().getYearMonth().equals(reportRecord.getYearMonth())){
				data = true;
			}
		}
		return data;
	}

}