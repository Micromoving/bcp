package cn.micromoving.bcp.modules.sr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.sr.entity.Achievement;

/**
 * 成果管理表Dao接口
 * @author Administrator
 * @version 卢奕豪
 */
 
@MyBatisDao
public interface AchievementDao extends CrudDao<Achievement> {
	
}