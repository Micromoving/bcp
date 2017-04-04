package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.PersonnelAgencyDao;
import cn.micromoving.bcp.modules.hr.entity.PersonnelAgency;
@Service
@Transactional(readOnly = true)
public class PersonnelAgencyService extends CrudService<PersonnelAgencyDao,PersonnelAgency> {

    public List<PersonnelAgency> findAllList(PersonnelAgency personnelAgency) {
        personnelAgency.getSqlMap().put("dsf", dataScopeFilter(personnelAgency.getCurrentUser(), "o", "a"));
        List<PersonnelAgency> personnelAgencyList = dao.findAllList(personnelAgency);
        return personnelAgencyList;
    }

}
