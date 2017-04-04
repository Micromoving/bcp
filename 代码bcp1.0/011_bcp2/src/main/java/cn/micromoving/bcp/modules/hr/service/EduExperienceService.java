package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.EduExperienceDao;
import cn.micromoving.bcp.modules.hr.entity.EduExperience;

@Service
@Transactional(readOnly = true)
public class EduExperienceService extends CrudService<EduExperienceDao, EduExperience> {

}