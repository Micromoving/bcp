package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.ClassPayStandard;
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;

/**
 * 课酬标准表Dao接口
 * 
 * @author Administrator
 * @version wenayng
 */
@MyBatisDao
public interface ClassPayStandardDao extends CrudDao<ClassPayStandard> {
    /**
     * 根据工资方案编号，删除对应的标准记录。
     * 
     * @param planId 工资方案编号
     */
    void deleteByPlanId(SalaryPlan entity);

    /**
     * 根据职称，聘任方式在标准中取得对应的课酬标准。<br/>
     * 职称级别:1 :初级; 2 : 中级; 3 : 副高;4: 正高; <br/>
     * 聘任方式:1 : 内聘; 2 : 外聘; <br/>
     * 特别指出，计算见习期人员的课酬标准时，将其【职称】值设置为‘0’，【聘任方式】为‘1’即可。
     * @param entity 课酬标准实体
     * @return 课酬标准
     */
    ClassPayStandard getClassPayStandard(ClassPayStandard entity);
}
