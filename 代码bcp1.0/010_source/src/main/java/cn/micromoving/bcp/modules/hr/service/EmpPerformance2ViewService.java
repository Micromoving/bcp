package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.EmpPerformance2ViewDao;
import cn.micromoving.bcp.modules.hr.entity.EmpPerformance2View;

@Service
@Transactional(readOnly = true)
public class EmpPerformance2ViewService extends CrudService<EmpPerformance2ViewDao, EmpPerformance2View> {

    @Autowired
    private EmpPerformance2ViewDao empPerformance2ViewDao;

    @Override
    public List<EmpPerformance2View> findList(EmpPerformance2View entity) {
        entity.getSqlMap().put("dsf", dataScopeFilter(entity.getCurrentUser(), "o", "a"));

        return empPerformance2ViewDao.findList(entity);
    }

}
