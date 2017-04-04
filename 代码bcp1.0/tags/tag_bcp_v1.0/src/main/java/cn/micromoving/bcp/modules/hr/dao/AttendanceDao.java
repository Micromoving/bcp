package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.Attendance;
import cn.micromoving.bcp.modules.oa.entity.Leave;

/**
 * 考勤表Dao接口
 * @author Administrator
 * @version Luyihao
 */
 
@MyBatisDao
public interface AttendanceDao extends CrudDao<Attendance> {
	/**
	 * 更新流程实例ID
	 * @param leave
	 * @return
	 */
	public int updateProcessInstanceId(Attendance attendance);
	
	/**
	 * 更新实际开始结束时间
	 * @param leave
	 * @return
	 */
	public int updateRealityTime(Attendance attendance);
	
	/**
	 * 根据流程实例ID，取得请假实体记录。
	 * @param procInsId 请假实体中的流程实例ID
	 * @return 请假实体记录。
	 */
	public Attendance getByProcInsId(Attendance attendance);
}
