package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.Attachment;

/**
 * 附件表Dao接口
 * @author Administrator
 * @version Luyihao
 */
 @MyBatisDao
public interface AttachmentDao extends CrudDao<Attachment> {
	
}
