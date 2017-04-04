package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.ReportPerformance;
import cn.micromoving.bcp.modules.hr.entity.ReportedWorkloade;


/**
 *  离职表Dao接口
 * @author wangzhichen
 * @version 2016-06-03
 */

@MyBatisDao
public interface ReportPerformanceDao extends CrudDao<ReportPerformance>{
	
	/**
	 * 根据流程实例ID，取得请假实体记录。
	 * @param procInsId 请假实体中的流程实例ID
	 * @return 请假实体记录。
	 */
	public ReportPerformance getByProcInsId(ReportPerformance reportPerformance);
	
	void deleteByReportId(ReportPerformance reportPerformance);
	
	int count(ReportPerformance data);
}