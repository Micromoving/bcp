package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.ClassTeacherFeeDao;
import cn.micromoving.bcp.modules.hr.entity.ClassTeacherFee;
@Service
@Transactional(readOnly = true)
public class ClassTeacherFeeService extends CrudService<ClassTeacherFeeDao, ClassTeacherFee>{
    @Autowired
    private ClassTeacherFeeDao classTeacherFeeDao;

    public Page<ClassTeacherFee> findClassTeacherFee(Page<ClassTeacherFee> page, ClassTeacherFee ClassTeacherFee) {
        // 设置分页参数
        ClassTeacherFee.setPage(page);
        // 执行分页查询
        page.setList(classTeacherFeeDao.findList(ClassTeacherFee));
        return page;
    }

    public void batchSave(ClassTeacherFee entity) {
        // TODO Auto-generated method stub
        List<String> dataIdList = entity.getDataIdList();
        List<String> classNumberList = entity.getClassNumberList();
        for(int i = 0;i<dataIdList.size();i++){
            String classNumber = classNumberList.get(i);
            ClassTeacherFee  entity1= new ClassTeacherFee();
            entity1.setId(dataIdList.get(i));
            entity1.getEmployee().getId();
            entity1.setClassNumber(classNumber);
            classTeacherFeeDao.insert(entity1);
        }
    }
}
