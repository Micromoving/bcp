package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.WorkExperience;

/**
 * 基本资料表Dao接口
 * @author Administrator
 * @version mujinyao
 */
 
@MyBatisDao
public interface WorkExperienceDao extends CrudDao<WorkExperience> {

}
