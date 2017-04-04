package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.SalaryDetails;


/**
 *  离职表Dao接口
 * @author wangzhichen
 * @version 2016-06-03
 */

@MyBatisDao
public interface SalaryDetailsDao extends CrudDao<SalaryDetails>{

	Double getLatestMaxInsure(SalaryDetails data);
	Double getHalfYearPostSalary(SalaryDetails data);
	Double getHalfYearClassFee(SalaryDetails data);
   
}