package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.InsuranceTransfer;

/**
 * 社保转移情况表Dao接口
 * 
 * @author wangzhichen
 * @version 2016-06-07
 */

@MyBatisDao
public interface InsuranceTransferDao extends CrudDao<InsuranceTransfer>{

}
