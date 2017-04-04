package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.DegreeEdu;
import cn.micromoving.bcp.modules.hr.entity.TrainExperience;

/**
 * 培训经历表Dao接口
 * @author Administrator
 * @version Luyihao
 */

@MyBatisDao
public interface TrainExperienceDao extends CrudDao<TrainExperience> {
	
	/**
	 * 根据流程实例ID，取得请假实体记录。
	 * @param procInsId 请假实体中的流程实例ID
	 * @return 请假实体记录。
	 */
	public TrainExperience getByProcInsId(TrainExperience trainExperience);
	
}
