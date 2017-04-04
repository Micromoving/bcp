package cn.micromoving.bcp.modules.hr.dao;

import java.util.List;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.Employee;


/**
 *  参保记录表Dao接口
 * @author wangzhichen
 * @version 2016-06-03
 */

@MyBatisDao
public interface EmployeeDao extends CrudDao<Employee>{
List<Employee>findEdu(Employee date);
}
