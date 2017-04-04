package cn.micromoving.bcp.modules.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.AnnualAppraisalDao;
import cn.micromoving.bcp.modules.hr.dao.HonorDao;
import cn.micromoving.bcp.modules.hr.entity.AnnualAppraisal;
import cn.micromoving.bcp.modules.hr.entity.Honor;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;

@Service
@Transactional(readOnly = true)
public class AnnualAppraisalService extends CrudService<AnnualAppraisalDao, AnnualAppraisal> {
	@Autowired
    private AnnualAppraisalDao annualAppraisalDao;
    
    public Page<AnnualAppraisal> findAnnualAppraisal(Page<AnnualAppraisal> page, AnnualAppraisal annualAppraisal) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
    	annualAppraisal.getSqlMap().put("dsf", dataScopeFilter(annualAppraisal.getCurrentUser(), "o", "a"));
        // 设置分页参数
    	annualAppraisal.setPage(page);
        // 执行分页查询
        page.setList(annualAppraisalDao.findList(annualAppraisal));
        return page;
    }
	public AnnualAppraisal getId(String id) {
		// TODO Auto-generated method stub
		AnnualAppraisal annualAppraisal = new AnnualAppraisal();
		annualAppraisal = dao.getId(id);
		return annualAppraisal;
	}
}

