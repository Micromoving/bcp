package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.Attendance;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstance;

/**
 * 工资实例表Dao接口
 * @author Administrator
 * @version wenayng
 */
 @MyBatisDao
public interface SalaryInstanceDao extends CrudDao<SalaryInstance> {
	 
	 
	 /**
		 * 根据流程实例ID，取得请假实体记录。
		 * @param procInsId 请假实体中的流程实例ID
		 * @return 请假实体记录。
		 */
		public SalaryInstance getByProcInsId(SalaryInstance salaryInstance);
	
}
