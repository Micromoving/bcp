package cn.micromoving.bcp.modules.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.EmployeeDao;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.sys.dao.UserDao;
import cn.micromoving.bcp.modules.sys.entity.User;
@Service
@Transactional(readOnly = true)
public class EmployeeService extends CrudService<EmployeeDao, Employee>{
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private UserDao userDao;
	
	
	public Page<Employee> findEmployee(Page<Employee> page, Employee employee) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
	    employee.getSqlMap().put("dsf", dataScopeFilter(employee.getCurrentUser(), "o", "a"));
		// 设置分页参数
	    employee.setPage(page);
		// 执行分页查询
		page.setList(employeeDao.findList(employee));
		return page;
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(Employee employee) {
		super.save(employee);
		User data = new User();
		data.setId(employee.getId());
		data = userDao.get(data);
		if(employee.getUser().getEmail()!=null){
			data.setEmail(employee.getUser().getEmail());
		}
		userDao.update(data);
	}
	

}
