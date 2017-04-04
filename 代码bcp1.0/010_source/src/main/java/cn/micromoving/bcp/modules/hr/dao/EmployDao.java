package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.Employ;

/**
 * 教育情况表Dao接口
 * @author Administrator
 * @version Luyihao
 */

@MyBatisDao
public interface EmployDao extends CrudDao<Employ> {
	
    Employ  getByUser(String userId);
}
