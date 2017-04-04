package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.Work;

/**
 * 工作经验表Dao接口
 * @author Administrator
 * @version Luyihao
 */
 @MyBatisDao
public interface WorkDao extends CrudDao<Work> {
	
}
