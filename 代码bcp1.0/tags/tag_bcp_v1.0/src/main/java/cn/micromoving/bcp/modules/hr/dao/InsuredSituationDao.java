package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.InsuredSituation;

/**
 * 险种规则表Dao接口
 * @author wangzhichen
 * @version 2016-06-03
 */

@MyBatisDao
public interface InsuredSituationDao extends CrudDao<InsuredSituation> {
	int count(InsuredSituation insuredSituation);
}
