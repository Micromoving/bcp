package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.AnnualSalaryPersonnelDao;
import cn.micromoving.bcp.modules.hr.entity.AnnualSalaryPersonnel;
import cn.micromoving.bcp.modules.sys.entity.User;

@Service
@Transactional(readOnly = true)
public class AnnualSalaryPersonnelService extends CrudService<AnnualSalaryPersonnelDao, AnnualSalaryPersonnel> {

	
}