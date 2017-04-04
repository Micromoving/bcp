package cn.micromoving.bcp.modules.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.HonorDao;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.Honor;

@Service
@Transactional(readOnly = true)
public class HonorService extends CrudService<HonorDao, Honor> {
    
    @Autowired
    private HonorDao honorDao;
    
    public Page<Honor> findHonor(Page<Honor> page, Honor honor) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        honor.getSqlMap().put("dsf", dataScopeFilter(honor.getCurrentUser(), "o", "a"));
        // 设置分页参数
        honor.setPage(page);
        // 执行分页查询
        page.setList(honorDao.findList(honor));
        return page;
    }

}
