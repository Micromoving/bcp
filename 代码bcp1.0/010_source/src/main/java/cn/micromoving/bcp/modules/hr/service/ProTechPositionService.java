package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.ProTechPositionDao;
import cn.micromoving.bcp.modules.hr.entity.ProTechPosition;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;

@Service
@Transactional(readOnly = true)
public class ProTechPositionService extends
		CrudService<ProTechPositionDao, ProTechPosition> {

	public boolean existProTechPosition(ProTechPosition data) {

		return dao.count(data) > 0 ? true : false;
	}

    public List<ProTechPosition> findAllList(ProTechPosition proTechPosition) {
        proTechPosition.getSqlMap().put("dsf", dataScopeFilter(proTechPosition.getCurrentUser(), "o", "a"));
        List<ProTechPosition> proTechPositionList = dao.findAllList(proTechPosition);
        return proTechPositionList;
    }

}
