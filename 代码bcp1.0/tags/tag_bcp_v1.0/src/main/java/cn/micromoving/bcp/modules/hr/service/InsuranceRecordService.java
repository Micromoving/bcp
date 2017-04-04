package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.InsuranceRecordDao;
import cn.micromoving.bcp.modules.hr.entity.InsuranceRecord;
@Service
@Transactional(readOnly = true)
public class InsuranceRecordService extends CrudService<InsuranceRecordDao, InsuranceRecord>{

}
