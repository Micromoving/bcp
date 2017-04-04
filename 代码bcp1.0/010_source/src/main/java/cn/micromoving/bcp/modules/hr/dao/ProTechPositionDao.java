package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.ProTechPosition;

/**
 * 离退休人员联系方式表Dao接口
 * @author Administrator
 * @version Luyihao
 */

@MyBatisDao
public interface ProTechPositionDao extends CrudDao<ProTechPosition> {
	int count(ProTechPosition data);
	ProTechPosition getMax(ProTechPosition data);
}
