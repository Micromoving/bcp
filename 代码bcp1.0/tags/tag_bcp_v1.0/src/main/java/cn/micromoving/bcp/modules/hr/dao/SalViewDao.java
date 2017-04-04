package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.entity.SalView;

/**
 * 总工资表Dao
 * 
 * @author 王志辰
 * @version 2016-8-03
 */
@MyBatisDao
public interface SalViewDao extends CrudDao<SalView> {

    
    SalView findSalByUser(SalEmpView salEmp);
}
