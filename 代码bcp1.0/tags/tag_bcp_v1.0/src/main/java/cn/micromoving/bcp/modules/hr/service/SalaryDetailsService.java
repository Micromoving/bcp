package cn.micromoving.bcp.modules.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.SalaryDetailsDao;
import cn.micromoving.bcp.modules.hr.entity.SalaryDetails;

@Service
@Transactional(readOnly = true)
public class SalaryDetailsService extends
		CrudService<SalaryDetailsDao, SalaryDetails> {

	@Autowired
	private SalaryDetailsDao salarydetailsDao;

	public Page<SalaryDetails> findSalaryDetails(Page<SalaryDetails> page,
			SalaryDetails salarydetails) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		salarydetails.getSqlMap().put("dsf",
				dataScopeFilter(salarydetails.getCurrentUser(), "o", "a"));
		// 设置分页参数
		salarydetails.setPage(page);
		// 执行分页查询
		page.setList(salarydetailsDao.findList(salarydetails));
		return page;
	}
}
