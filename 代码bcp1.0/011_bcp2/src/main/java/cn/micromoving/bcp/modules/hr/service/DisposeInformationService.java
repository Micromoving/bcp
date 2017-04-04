package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.DisposeInformationDao;
import cn.micromoving.bcp.modules.hr.entity.DisposeInformation;

@Service
@Transactional(readOnly = true)
public class DisposeInformationService extends CrudService<DisposeInformationDao, DisposeInformation> {

}