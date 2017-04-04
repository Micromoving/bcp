package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.SalStandardView;

/**
 * 工资标准视图Dao接口
 * @author 仇左临
 * @version 2016-08-03
 */
@MyBatisDao
public interface SalStandardViewDao extends CrudDao<SalStandardView> {

}
