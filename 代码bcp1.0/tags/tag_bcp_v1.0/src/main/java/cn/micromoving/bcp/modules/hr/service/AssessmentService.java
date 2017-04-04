package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.AssessmentDao;
import cn.micromoving.bcp.modules.hr.entity.Assessment;
@Service
@Transactional(readOnly = true)
public class AssessmentService extends CrudService<AssessmentDao, Assessment> {
	
	public boolean existAssessment(Assessment data){
		
		return dao.count(data)>0?true:false;
	}
}
