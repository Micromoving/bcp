package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.WorkDao;
import cn.micromoving.bcp.modules.hr.entity.Work;
@Service
@Transactional(readOnly = true)
public class WorkService extends CrudService<WorkDao, Work>{

    public List<Work> findAllList(Work work) {
        work.getSqlMap().put("dsf", dataScopeFilter(work.getCurrentUser(), "o", "a"));
        List<Work> workList = dao.findAllList(work);
        return workList;
    }

}
