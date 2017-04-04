package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.PersonnelAgencyDao;
import cn.micromoving.bcp.modules.hr.entity.PersonnelAgency;

@Service
@Transactional(readOnly = true)
public class PersonnelAgencyService extends CrudService<PersonnelAgencyDao,PersonnelAgency> {

}
