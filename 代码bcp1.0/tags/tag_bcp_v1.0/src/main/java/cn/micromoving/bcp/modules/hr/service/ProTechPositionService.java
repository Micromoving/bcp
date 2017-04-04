package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.ProTechPositionDao;
import cn.micromoving.bcp.modules.hr.entity.ProTechPosition;

@Service
@Transactional(readOnly = true)
public class ProTechPositionService extends
		CrudService<ProTechPositionDao, ProTechPosition> {

	public boolean existProTechPosition(ProTechPosition data) {

		return dao.count(data) > 0 ? true : false;
	}

}
