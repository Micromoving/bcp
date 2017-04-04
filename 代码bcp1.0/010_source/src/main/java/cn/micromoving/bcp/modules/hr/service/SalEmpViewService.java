package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;

@Service
@Transactional(readOnly = true)
public class SalEmpViewService extends CrudService<SalEmpViewDao, SalEmpView> {

    @Autowired
    private SalEmpViewDao salEmpViewDao;

    @Override
    public List<SalEmpView> findList(SalEmpView salEmpView) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        salEmpView.getSqlMap().put("dsf", dataScopeFilter(salEmpView.getCurrentUser(), "o", "a"));
        
        return salEmpViewDao.findList(salEmpView);

    }

    public Page<SalEmpView> findSalEmp(Page<SalEmpView> page, SalEmpView salEmpView) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        salEmpView.getSqlMap().put("dsf", dataScopeFilter(salEmpView.getCurrentUser(), "o", "a"));
        // 设置分页参数
        salEmpView.setPage(page);
        // 执行分页查询
        page.setList(salEmpViewDao.findList(salEmpView));
        return page;
    }

}
