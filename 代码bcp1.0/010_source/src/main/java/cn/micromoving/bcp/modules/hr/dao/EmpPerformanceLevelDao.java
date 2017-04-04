package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.EmpPerformanceLevel;
import cn.micromoving.bcp.modules.hr.entity.ReportPerformance;


/**
 *  教师绩效等级Dao接口
 * @author wangzhichen
 * @version 2016-06-03
 */

@MyBatisDao
public interface EmpPerformanceLevelDao extends CrudDao<EmpPerformanceLevel>{
	void deleteByReportId(EmpPerformanceLevel empPerformanceLevel);
}
