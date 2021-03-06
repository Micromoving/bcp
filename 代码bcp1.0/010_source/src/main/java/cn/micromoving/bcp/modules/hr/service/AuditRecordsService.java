package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.AuditRecordsDao;
import cn.micromoving.bcp.modules.hr.entity.AuditRecords;

@Service
@Transactional(readOnly = true)
public class AuditRecordsService extends CrudService<AuditRecordsDao, AuditRecords>{
	
}