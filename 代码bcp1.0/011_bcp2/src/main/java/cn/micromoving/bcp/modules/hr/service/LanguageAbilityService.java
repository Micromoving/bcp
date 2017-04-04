package cn.micromoving.bcp.modules.hr.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.LanguageAbilityDao;
import cn.micromoving.bcp.modules.hr.entity.LanguageAbility;

@Service
@Transactional(readOnly = true)
public class LanguageAbilityService extends CrudService<LanguageAbilityDao, LanguageAbility> {

}