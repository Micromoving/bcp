package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.EmpPerformance3ViewDao;
import cn.micromoving.bcp.modules.hr.entity.EmpPerformance3View;

@Service
@Transactional(readOnly = true)
public class EmpPerformance3ViewService extends CrudService<EmpPerformance3ViewDao, EmpPerformance3View> {

    @Autowired
    private EmpPerformance3ViewDao empPerformance3ViewDao;

    @Override
    public List<EmpPerformance3View> findList(EmpPerformance3View entity) {
        entity.getSqlMap().put("dsf", dataScopeFilter(entity.getCurrentUser(), "o", "a"));
        return empPerformance3ViewDao.findList(entity);
    }

}
