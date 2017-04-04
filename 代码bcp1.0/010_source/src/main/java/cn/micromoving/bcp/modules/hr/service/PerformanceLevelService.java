package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.PerformanceLevelDao;
import cn.micromoving.bcp.modules.hr.entity.PerformanceLevel;

/**
 * 绩效工资Service
 * 
 * @author 微动信息 Thinkpad
 * @version $Revision: $ $Date: $
 * 
 * <pre>
 * ■变更记录■
 * 2016年7月28日 创建
 * </pre>
 */
@Service
@Transactional(readOnly = true)
public class PerformanceLevelService extends CrudService<PerformanceLevelDao, PerformanceLevel> {

    /**
     * 批量保存绩效工资。
     * @param data
     */
    public void saveBatch(PerformanceLevel data) {
        /* 待保存的绩效工资ID列表 */
        List<String> perIdList = data.getPerIdList();
        /* 待保存的绩效工资值列表 */
        List<Double> performanceSalaryList =data.getPerformanceSalaryList();
        for (int index = 0; index < perIdList.size(); index++) {
            PerformanceLevel entity = new PerformanceLevel();

            String id = perIdList.get(index);
            Double performanceSalary = performanceSalaryList.get(index);
            entity.setId(id);
            entity = dao.get(entity);
            entity.setPerformanceSalary(performanceSalary);
            dao.update(entity);
        }
    }
}
