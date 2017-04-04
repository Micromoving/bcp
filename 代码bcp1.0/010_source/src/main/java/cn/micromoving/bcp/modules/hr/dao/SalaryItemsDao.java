package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.SalaryItems;

/**
 * 工资项表Dao接口
 * @author Administrator
 * @version wenayng
 */
 @MyBatisDao
public interface SalaryItemsDao extends CrudDao<SalaryItems> {
	
}
