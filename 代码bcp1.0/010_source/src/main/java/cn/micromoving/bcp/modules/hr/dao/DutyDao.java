package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.Duty;
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;


/**
 *  离职表Dao接口
 * @author wangzhichen
 * @version 2016-06-03
 */

@MyBatisDao
public interface DutyDao extends CrudDao<Duty>{
    /**
     * 根据工资方案编号，删除对应的标准记录。
    * @param planId 工资方案编号
    */
   void deleteByPlanId(SalaryPlan entity);
}