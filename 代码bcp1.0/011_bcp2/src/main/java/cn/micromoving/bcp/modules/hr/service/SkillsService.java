package cn.micromoving.bcp.modules.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.HonorDao;
import cn.micromoving.bcp.modules.hr.dao.SkillsDao;
import cn.micromoving.bcp.modules.hr.entity.Skills;

@Service
@Transactional(readOnly = true)
public class SkillsService extends CrudService<SkillsDao, Skills> {

	 @Autowired
    private SkillsDao skillsDao;
	 
	public Page<Skills> findSkills(Page<Skills> page, Skills skills) {
		   // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        skills.getSqlMap().put("dsf", dataScopeFilter(skills.getCurrentUser(), "o", "a"));
        // 设置分页参数
        skills.setPage(page);
        // 执行分页查询
        page.setList(skillsDao.findList(skills));
		return page;
	}

}