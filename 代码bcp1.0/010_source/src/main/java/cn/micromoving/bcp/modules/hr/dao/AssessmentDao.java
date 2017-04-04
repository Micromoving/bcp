package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.Assessment;

/**
 * 考核表Dao接口
 * @author Administrator
 * @version mujinyao
 */
 
@MyBatisDao
public interface AssessmentDao extends CrudDao<Assessment> {
	int count(Assessment data);
}
