package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.SalaryInstanceTaskDao;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstanceTask;

@Service
@Transactional(readOnly = true)
public class SalaryInstanceTaskService extends CrudService<SalaryInstanceTaskDao, SalaryInstanceTask>{

}
