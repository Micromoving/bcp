package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.ClassPayStandardDao;
import cn.micromoving.bcp.modules.hr.dao.DutyDao;
import cn.micromoving.bcp.modules.hr.dao.PerformanceLevelDao;
import cn.micromoving.bcp.modules.hr.dao.SalaryPlanDao;
import cn.micromoving.bcp.modules.hr.dao.SalaryStandardDao;
import cn.micromoving.bcp.modules.hr.dao.SubsidiaryStandardDao;
import cn.micromoving.bcp.modules.hr.dao.WarmDao;
import cn.micromoving.bcp.modules.hr.entity.ClassPayStandard;
import cn.micromoving.bcp.modules.hr.entity.Duty;
import cn.micromoving.bcp.modules.hr.entity.PerformanceLevel;
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;
import cn.micromoving.bcp.modules.hr.entity.SalaryStandard;
import cn.micromoving.bcp.modules.hr.entity.SubsidiaryStandard;
import cn.micromoving.bcp.modules.hr.entity.Warm;

@Service
@Transactional(readOnly = true)
public class SalaryPlanService extends CrudService<SalaryPlanDao, SalaryPlan> {

    /**
     * 系列工资标准DAO实例<br/>
     * <code>salaryStandardDao课酬标准DAO实例</code> 的注释
     */
    @Autowired
    private SalaryStandardDao salaryStandardDao;
    /**
     * 绩效等级表DAO实例
     * <code>performanceLevelDao 绩效等级表DAO</code> 的注释
     */
    @Autowired
    private PerformanceLevelDao performanceLevelDao;

    /**
     * 课酬标准DAO实例<br/>
     * <code>classPayStandardDao课酬标准DAO实例</code> 的注释
     */
    @Autowired
    private ClassPayStandardDao classPayStandardDao;

    /**
     * 津贴标准DAO实例<br/>
     * <code>津贴标准DAO实例</code> 的注释
     */
    @Autowired
    private SubsidiaryStandardDao subsidiaryStandardDao;

    /**
     * 值班DAO实例<br/>
     * <code>dutyDao值班DAO实例</code> 的注释
     */
    @Autowired
    private DutyDao dutyDao;
    
    /**
     * 取暖DAO实例<br/>
     * <code>warmDao取暖DAO实例</code> 的注释
     */
    @Autowired
    private WarmDao warmDao;
    

    /**
     * 启用选中的工资方案。本系统中，正在启动的工资方案只有一个。
     * 
     * @param id 工资方案ID
     */
    public void enable(String id) {
        dao.disable();
        dao.enable(id);

    }

    public void delete(SalaryPlan entity){
      
        salaryStandardDao.deleteByPlanId( entity);
        performanceLevelDao.deleteByPlanId(entity);
        classPayStandardDao.deleteByPlanId(entity);
        subsidiaryStandardDao.deleteByPlanId(entity);
        dutyDao.deleteByPlanId(entity);
        warmDao.deleteByPlanId(entity);
        dao.delete(entity);
        
    }
    /**
     * 保存新的工资方案。<br/>
     * 新方案中的所有标准，来源于现行方案中的标准。可在此基础上对标准的值进行调整。
     */
    public void save(SalaryPlan entity) {

        if (entity.getIsNewRecord()){
            entity.preInsert();
            dao.insert(entity);
            /* 1取得现行方案中的所有标准。包括：A系列工资，B课酬标准，C津贴标准，D值班标准，E取暖标准。 */
            /* 2将现行方案的标准，无条件地置于新方案中。即新方案中的标准，来源于现行方案。 */

            /* A取得现行方案中的系列工资。 */
            List<SalaryStandard> salaryStandardList = salaryStandardDao.findList(new SalaryStandard());
            for (SalaryStandard data : salaryStandardList) {

                /* 将现行方案中的某系列工资主键置为空，保存到新方案中。 */
                data.setSalaryPlan(entity);
                data.setId("");

                data.preInsert();
                salaryStandardDao.insert(data);

            }
            /* A取得现行方案中的系列工资(绩效工资)。 */
            List<PerformanceLevel> performanceLevelList = performanceLevelDao.findList(new PerformanceLevel());
            for (PerformanceLevel data : performanceLevelList) {

                /* 将现行方案中的绩效工资主键置为空，保存到新方案中。 */
                data.setSalaryPlan(entity);
                data.setId("");

                data.preInsert();
                performanceLevelDao.insert(data);

            }
            /* B取得现行方案中的课酬标准。 */
            List<ClassPayStandard> classPayStandardList = classPayStandardDao.findList(new ClassPayStandard());
            for (ClassPayStandard data : classPayStandardList) {

                /* 将现行方案中的某课酬标准主键置为空，保存到新方案中。 */
                data.setSalaryPlan(entity);
                data.setId("");

                data.preInsert();
                classPayStandardDao.insert(data);

            }
            
            
            /* C取得现行方案中的津贴标准。 */
            List<SubsidiaryStandard> subsidiaryStandardList = subsidiaryStandardDao.findList(new SubsidiaryStandard());
            for (SubsidiaryStandard data : subsidiaryStandardList) {

                /* 将现行方案中的某津贴标准主键置为空，保存到新方案中。 */
                data.setSalaryPlan(entity);
                data.setId("");

                data.preInsert();
                subsidiaryStandardDao.insert(data);

            }
            /* D取得现行方案中的值班标准。 */
            List<Duty> dutyList = dutyDao.findList(new Duty());
            for (Duty data : dutyList) {

                /* 将现行方案中的某值班标准主键置为空，保存到新方案中。 */
                data.setSalaryPlan(entity);
                data.setId("");

                data.preInsert();
                dutyDao.insert(data);

            }
            /* E取得现行方案中的取暖标准。 */
            List<Warm> warmList = warmDao.findList(new Warm());
            for (Warm data : warmList) {

                /* 将现行方案中的某取暖标准主键置为空，保存到新方案中。 */
                data.setSalaryPlan(entity);
                data.setId("");

                data.preInsert();
                warmDao.insert(data);

            }
        }else{
            entity.preUpdate();
            dao.update(entity);
        }
        

    }
}
