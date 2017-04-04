package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;

/**
 * 高校资格认定Dao接口
 * @author Administrator
 * @version Luyihao
 */

@MyBatisDao
public interface TeacherQualificationDao extends CrudDao<TeacherQualification> {
	TeacherQualification getId(String id);
}
