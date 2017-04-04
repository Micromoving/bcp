package cn.micromoving.bcp.modules.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.CheckDao;
import cn.micromoving.bcp.modules.hr.dao.HonorDao;
import cn.micromoving.bcp.modules.hr.entity.Check;
import cn.micromoving.bcp.modules.hr.entity.Honor;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;

@Service
@Transactional(readOnly = true)
public class CheckService extends CrudService<CheckDao, Check> {
	@Autowired
	private CheckDao checkDao;

	public Page<Check> findCheck(Page<Check> page, Check check) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		check.getSqlMap().put("dsf",dataScopeFilter(check.getCurrentUser(), "o", "a"));
		// 设置分页参数
		check.setPage(page);
		// 执行分页查询
		page.setList(checkDao.findList(check));
		return page;
	}

}
