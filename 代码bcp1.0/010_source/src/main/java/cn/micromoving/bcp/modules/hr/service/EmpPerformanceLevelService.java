package cn.micromoving.bcp.modules.hr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.hr.dao.EmpPerformanceLevelDao;
import cn.micromoving.bcp.modules.hr.dao.ReportRecordDao;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.entity.EmpPerformanceLevel;
import cn.micromoving.bcp.modules.hr.entity.ReportPerformance;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.sys.dao.UserDao;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Service
@Transactional(readOnly = true)
public class EmpPerformanceLevelService extends CrudService<EmpPerformanceLevelDao, EmpPerformanceLevel> {

    @Autowired
    /* 定义一个EmpPerformanceLevelDao类型的字段 */
    private EmpPerformanceLevelDao empPerformanceLevelDao;

    @Autowired
    private ReportRecordService recordService;

    @Autowired
    private SalEmpViewDao salEmpViewDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReportRecordDao reportRecordDao;

    public void batchSave(EmpPerformanceLevel empPerformanceLevel) {
        /* 保存上报1条记录 */
        ReportRecord data = empPerformanceLevel.getReportRecord();

        if (StringUtils.isNotBlank(data.getId())) {
            data = recordService.get(data);
            data.setCreateBy(data.getUser());
        }

        data.setOffice(UserUtils.getUser().getOffice());
        /* 提交状态。1：草稿；2：审批中（走流程中）；3：审批完成（审核结束） */
        /* 此处状态只能为1：暂存（草稿），2：提交，也就是审批中（走流程中）。 */
        data.setDataState(empPerformanceLevel.getDataState());
        /* 如果用户是初次上报，则不做删除相关记录。如果是暂存后，再次提交（暂存），则删除之前的相关记录。 */
        if (!StringUtils.isBlank(data.getId())) {
            /* 当第二次保存时，先将之前保存的相关记录删除，再保存新的记录。 */
            dao.deleteByReportId(empPerformanceLevel);
        }
        recordService.save(data);

        /* 保存上报工作量详情的n条记录 */
        for (int index = 0; index < empPerformanceLevel.getEmployeeIdList().size(); index++) {

            String userId = empPerformanceLevel.getEmployeeIdList().get(index);
            String performanceLevel = "";
            if (index < empPerformanceLevel.getPerformanceLevelList().size()) {

                performanceLevel = empPerformanceLevel.getPerformanceLevelList().get(index);
            }

            User user = new User(userId);

            EmpPerformanceLevel entity = new EmpPerformanceLevel();
            /* 上报记录ID */
            entity.setReportRecord(data);
            /* 职员ID */
            entity.setUser(user);
            /* 绩效档 */
            entity.setPerformanceLevel(performanceLevel);
            /* 生效日期 */
            entity.setStartDate(empPerformanceLevel.getStartDate());
            entity.setEndDate(empPerformanceLevel.getEndDate());
            this.save(entity);
        }

    }

    public Page<SalEmpView> findSalEmpView(Page<SalEmpView> page, SalEmpView salEmpView) {
        // TODO Auto-generated method stub
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        salEmpView.getSqlMap().put("dsf", dataScopeFilter(salEmpView.getCurrentUser(), "o", "a"));
        // 设置分页参数

        // 执行分页查询
        page.setList(salEmpViewDao.findList(salEmpView));
        return page;
    }

    public boolean existEmpPerformanceLevel(ReportRecord data) {
        /* 设置DataClassification值为“4”，绩效档 */
        data.setDataClassification("4");
        /* 调用reportRecordDao中的count方法，如果结果大于零，返回true，如果结果小于零，返回false是 */
        return reportRecordDao.count(data) > 0 ? true : false;
    }

    public boolean ccTime(EmpPerformanceLevel empPerformanceLevel) throws ParseException {
        boolean data = false;
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMM");
        Date date1 = sim.parse(empPerformanceLevel.getReportRecord().getYearMonth());
        String dates1 = String.format("%tY", date1);
        List<ReportRecord> list = reportRecordDao.findList(empPerformanceLevel.getReportRecord());
        list.remove(empPerformanceLevel.getReportRecord());
        for (ReportRecord reportRecord : list) {
            Date date2 = sim.parse(reportRecord.getYearMonth());
            String dates2 = String.format("%tY", date2);
            if (dates2.equals(dates1)) {
                data = true;
            }
        }
        return data;
    }

}
