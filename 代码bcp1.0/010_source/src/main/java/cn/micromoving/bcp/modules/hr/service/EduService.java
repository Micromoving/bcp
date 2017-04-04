package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.EduDao;
import cn.micromoving.bcp.modules.hr.entity.Edu;

@Service
@Transactional(readOnly = true)
public class EduService extends CrudService<EduDao, Edu> {

    public List<Edu> findAllList(Edu edu) {
        edu.getSqlMap().put("dsf", dataScopeFilter(edu.getCurrentUser(), "o", "a"));
        List<Edu> eduList = dao.findAllList(edu);
        return eduList;
    }

}
