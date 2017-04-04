package cn.micromoving.bcp.modules.hr.dao;
import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.PostAppointment;

/**
 * 岗位聘任Dao接口
 * @author Administrator
 * @version mujinyao
 */
 
@MyBatisDao
public interface PostAppointmentDao extends CrudDao<PostAppointment> {
	
}
