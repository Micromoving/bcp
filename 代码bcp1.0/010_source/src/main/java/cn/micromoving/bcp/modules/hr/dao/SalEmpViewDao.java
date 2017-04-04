package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;

/**
 * 多表综合视图Dao接口
 * @author Administrator
 * @version mujinyao
 */
 
@MyBatisDao
public interface SalEmpViewDao extends CrudDao<SalEmpView> {
	
}
