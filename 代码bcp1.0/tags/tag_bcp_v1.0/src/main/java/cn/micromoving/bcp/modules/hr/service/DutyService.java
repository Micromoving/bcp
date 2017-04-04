package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.DutyDao;
import cn.micromoving.bcp.modules.hr.entity.Duty;

@Service
@Transactional(readOnly = true)
public class DutyService extends CrudService<DutyDao, Duty> {

}
