package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;

/**
 *工资方案表Dao接口
 * @author Administrator
 * @version wenayng
 */
 @MyBatisDao
public interface SalaryPlanDao extends CrudDao<SalaryPlan> {
     void enable(String id);
     void disable();
}
