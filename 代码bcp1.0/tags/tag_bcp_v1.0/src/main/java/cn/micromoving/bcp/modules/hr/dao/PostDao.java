package cn.micromoving.bcp.modules.hr.dao;

import java.util.List;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.Post;


/**
 *  参保记录表Dao接口
 * @author wangzhichen
 * @version 2016-06-03
 */

@MyBatisDao
public interface PostDao extends CrudDao<Post>{
		List<Post> findPostNameList(Post post);

}
