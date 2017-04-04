package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.EmpPerformance4ViewDao;
import cn.micromoving.bcp.modules.hr.entity.EmpPerformance4View;

@Service
@Transactional(readOnly = true)
public class EmpPerformance4ViewService extends CrudService<EmpPerformance4ViewDao, EmpPerformance4View> {

    @Autowired
    private EmpPerformance4ViewDao empPerformance4ViewDao;

    @Override
    public List<EmpPerformance4View> findList(EmpPerformance4View entity) {
        entity.getSqlMap().put("dsf", dataScopeFilter(entity.getCurrentUser(), "o", "a"));

        return empPerformance4ViewDao.findList(entity);
    }

}
