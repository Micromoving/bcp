package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.SubsidiaryStandardDao;
import cn.micromoving.bcp.modules.hr.entity.SubsidiaryStandard;

@Service
@Transactional(readOnly = true)
public class SubsidiaryStandardService extends CrudService<SubsidiaryStandardDao, SubsidiaryStandard> {

}
