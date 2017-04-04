package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.InsuranceRuleDao;
import cn.micromoving.bcp.modules.hr.entity.InsuranceRule;

@Service
@Transactional(readOnly = true)
public class InsuranceRuleService extends CrudService<InsuranceRuleDao, InsuranceRule>{

	
}
