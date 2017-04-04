package cn.micromoving.bcp.modules.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.SelectedTalentProjectDao;
import cn.micromoving.bcp.modules.hr.entity.SelectedTalentProject;

@Service
@Transactional(readOnly = true)
public class SelectedTalentProjectService extends CrudService<SelectedTalentProjectDao, SelectedTalentProject> {

	 @Autowired
    private SelectedTalentProjectDao selectedTalentProjectDao;
	 
	public Page<SelectedTalentProject> findSelectedTalentProject(Page<SelectedTalentProject> page, SelectedTalentProject selectedTalentProject) {
		   // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		selectedTalentProject.getSqlMap().put("dsf", dataScopeFilter(selectedTalentProject.getCurrentUser(), "o", "a"));
        // 设置分页参数
		selectedTalentProject.setPage(page);
        // 执行分页查询
        page.setList(selectedTalentProjectDao.findList(selectedTalentProject));
		return page;
	}

}