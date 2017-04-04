package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.Honor;

/**
 * 基本资料Dao接口
 * @author Administrator
 * @version mujinyao
 */
 
@MyBatisDao
public interface HonorDao extends CrudDao<Honor> {

}
