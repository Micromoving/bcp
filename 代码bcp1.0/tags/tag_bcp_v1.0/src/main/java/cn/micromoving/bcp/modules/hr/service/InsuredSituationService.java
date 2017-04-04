package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.InsuredSituationDao;
import cn.micromoving.bcp.modules.hr.entity.InsuredSituation;

@Service
@Transactional(readOnly = true)
public class InsuredSituationService extends CrudService<InsuredSituationDao, InsuredSituation>{
	public boolean exist(InsuredSituation insuredSituation){
		return dao.count(insuredSituation)>0?true:false;
	}
}
