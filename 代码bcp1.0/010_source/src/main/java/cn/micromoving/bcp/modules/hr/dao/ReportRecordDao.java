package cn.micromoving.bcp.modules.hr.dao;

import java.util.List;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.EmpPerformanceLevel;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;


/**
 *  上报记录表Dao接口
 * @author wangzhichen
 * @version 2016-06-03
 */

@MyBatisDao
public interface ReportRecordDao extends CrudDao<ReportRecord>{
	/**
	 * 根据流程实例ID，取得请假实体记录。
	 * 
	 * @param procInsId
	 *            请假实体中的流程实例ID
	 * @return 请假实体记录。
	 */
	ReportRecord getByProcInsId(ReportRecord reportRecord);
	
	void deleteByReportId(ReportRecord reportRecord);

	int count(ReportRecord data);
	
}