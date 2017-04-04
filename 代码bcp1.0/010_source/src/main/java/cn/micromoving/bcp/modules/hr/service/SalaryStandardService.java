package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.SalaryStandardDao;
import cn.micromoving.bcp.modules.hr.entity.PerformanceLevel;
import cn.micromoving.bcp.modules.hr.entity.SalaryStandard;

/**
 * 系列工资标准Service
 * @author 微动信息 Thinkpad
 * @version $Revision:  $ $Date:  $
 * <pre>
 * ■变更记录■
 * 2016年7月28日 创建
 * </pre>
 */
@Service
@Transactional(readOnly = true)
public class SalaryStandardService extends CrudService<SalaryStandardDao, SalaryStandard> {
    
    /**
     * 批量保存系列工资。
     * @param data
     */
    public void saveBatch(SalaryStandard data) {
         List<String> idList =data.getIdList();
        /* 职级工资list */
         List<Double> professionalLevelSalaryList = data.getProfessionalLevelSalaryList();
        /* 岗位工资list */
         List<Double> postSalaryList =data.getPostSalaryList();
        /* 岗位津贴list */
         List<Double> postSubsideList =data.getPostSubsideList();
        for (int index = 0; index < idList.size(); index++) {
            SalaryStandard entity = new SalaryStandard();

            String id = idList.get(index);
            /*职级工资*/
            Double professionalLevelSalary = professionalLevelSalaryList.get(index);
            /*岗位工资*/
            Double postSalary = postSalaryList.get(index);
            /*岗位津贴*/
            Double postSubside = postSubsideList.get(index);
            entity.setId(id);
            entity = dao.get(entity);
            entity.setProfessionalLevelSalary(professionalLevelSalary);
            entity.setPostSalary(postSalary);
            entity.setPostSubside(postSubside);
            dao.update(entity);
        }
    }
    
}
