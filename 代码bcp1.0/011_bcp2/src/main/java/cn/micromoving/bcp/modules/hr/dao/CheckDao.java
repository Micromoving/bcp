package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.Check;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;


@MyBatisDao
public interface CheckDao extends CrudDao<Check>{
}
