package cn.micromoving.bcp.modules.hr.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;

import cn.micromoving.bcp.modules.hr.dao.WorkExperienceDao;

import cn.micromoving.bcp.modules.hr.entity.WorkExperience;


@Service
@Transactional(readOnly = true)
public class WorkExperienceService extends CrudService<WorkExperienceDao, WorkExperience> {
    @Autowired
    private WorkExperienceDao workExperienceDao;
    public Page<WorkExperience> findWorkExperience(Page<WorkExperience> page, WorkExperience workExperience) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        workExperience.getSqlMap().put("dsf", dataScopeFilter(workExperience.getCurrentUser(), "o", "a"));
        // 设置分页参数
        workExperience.setPage(page);
        page.setList(workExperienceDao.findList(workExperience));
        // 执行分页查询
        return page;
    }
   
}