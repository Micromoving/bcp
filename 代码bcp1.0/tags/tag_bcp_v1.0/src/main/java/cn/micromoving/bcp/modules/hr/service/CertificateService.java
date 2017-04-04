package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.CertificateDao;
import cn.micromoving.bcp.modules.hr.entity.Certificate;
@Service
@Transactional(readOnly = true)
public class CertificateService extends CrudService<CertificateDao, Certificate>{

}
