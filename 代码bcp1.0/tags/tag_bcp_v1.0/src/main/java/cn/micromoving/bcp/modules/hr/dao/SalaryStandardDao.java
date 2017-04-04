package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;
import cn.micromoving.bcp.modules.hr.entity.SalaryStandard;

/**
 *系列工资标准表Dao接口
 * @author Administrator
 * @version wenayng
 */
 @MyBatisDao
public interface SalaryStandardDao extends CrudDao<SalaryStandard> {
	
     /**
      * 根据工资方案编号，删除对应的标准记录。
     * @param planId 工资方案编号
     */
    void deleteByPlanId(SalaryPlan entity);
}
