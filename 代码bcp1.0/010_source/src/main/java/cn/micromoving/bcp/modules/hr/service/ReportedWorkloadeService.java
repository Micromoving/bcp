package cn.micromoving.bcp.modules.hr.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.hr.dao.ReportedWorkloadeDao;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
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
		/* 提交状态。流程1: 1：草稿；2：审批中（走流程中）；3：审批完成（审核结束） 
		 * 		  流程2:  4：草稿 5:审批中 6:审批完成*/
		if("0".equals(reportedWorkloade.getFlag())&& "1".equals(reportedWorkloade.getDataState())){
			data.setDataState("4");
		} else if("0".equals(reportedWorkloade.getFlag())&& "2".equals(reportedWorkloade.getDataState())){
			data.setDataState("5");
		}else{
			data.setDataState(reportedWorkloade.getDataState());
		}
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
			Double teaching = reportedWorkloade.getTeachingList().get(index);
			Double dissertation = reportedWorkloade.getDissertationList().get(index);
			Double tutorialSystem = reportedWorkloade.getTutorialSystemList().get(index);
			Double optional = reportedWorkloade.getOptionalList().get(index);
			Double termPaper = reportedWorkloade.getTermPaperList().get(index);
			Double marking = reportedWorkloade.getMarkingList().get(index);
			Double theTopic = reportedWorkloade.getTheTopicList().get(index);
			Double invigilator = reportedWorkloade.getInvigilatorList().get(index);
			Double smallClassDiscussion = reportedWorkloade.getSmallClassDiscussionList().get(index);
			Double allKindsOfCompetition = reportedWorkloade.getAllKindsOfCompetitionList().get(index);
			Double other = reportedWorkloade.getOtherList().get(index);

			User user = new User(userId);

			ReportedWorkloade entity = new ReportedWorkloade();
			/* 上报记录ID */
			entity.setReportRecord(data);
			entity.setCreateBy(UserUtils.getUser());
			entity.setCreateDate(new Date());
			entity.setWorkloadeDataClassification("1");
			/* 职员ID */
			entity.setUser(user);
			entity.setTeaching(teaching);
			entity.setDissertation(dissertation);
			entity.setTutorialSystem(tutorialSystem);
			entity.setOptional(optional);
			entity.setTermPaper(termPaper);
			entity.setMarking(marking);
			entity.setTheTopic(theTopic);
			entity.setInvigilator(invigilator);
			entity.setSmallClassDiscussion(smallClassDiscussion);
			entity.setAllKindsOfCompetition(allKindsOfCompetition);
			entity.setOther(other);
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
			if (reportedWorkloade.getReportRecord().getYearMonth()
					.equals(reportRecord.getYearMonth())) {
				data = true;
			}
		}
		return data;
	}

}