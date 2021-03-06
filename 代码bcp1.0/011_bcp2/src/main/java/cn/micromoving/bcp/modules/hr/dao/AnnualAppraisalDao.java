package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.AnnualAppraisal;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;

/**
 * 基本资料表Dao接口
 * 
 * @author Administrator
 * @version Luyihao
 */
@MyBatisDao
public interface AnnualAppraisalDao extends CrudDao<AnnualAppraisal> {
	AnnualAppraisal getId(String id);
}
