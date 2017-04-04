package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.LoanRecords;

/**
 * 借款记录表Dao接口
 * @author Administrator
 * @version Luyihao
 */

@MyBatisDao
public interface LoanRecordsDao extends CrudDao<LoanRecords> {
	
}

