package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.AwardDao;
import cn.micromoving.bcp.modules.hr.entity.Award;
@Service
@Transactional(readOnly = true)
public class AwardService extends CrudService<AwardDao, Award>{

}
