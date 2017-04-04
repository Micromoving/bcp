package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.Assessment;
import cn.micromoving.bcp.modules.hr.entity.ReportPerformance;
import cn.micromoving.bcp.modules.hr.entity.ReportedWorkloade;

/**
 * 上报绩效明细表Dao接口
 * 
 * @author Administrator
 * @version wenayng
 */
@MyBatisDao
public interface ReportedWorkloadeDao extends CrudDao<ReportedWorkloade> {

	/**
	 * 根据流程实例ID，取得请假实体记录。
	 * 
	 * @param procInsId
	 *            请假实体中的流程实例ID
	 * @return 请假实体记录。
	 */
	 ReportedWorkloade getByProcInsId(ReportedWorkloade reportedWorkloade);
	
	void deleteByReportId(ReportedWorkloade reportedWorkloade);

	int count(ReportedWorkloade data);
	
	Integer sumWorkHalfYear(ReportedWorkloade data);
}
