package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.TeacherQualificationDao;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;
@Service
@Transactional(readOnly = true)
public class TeacherQualificationService extends CrudService<TeacherQualificationDao, TeacherQualification>{
	
	public TeacherQualification getId(String id){
		TeacherQualification teacherQualification = new TeacherQualification();
		teacherQualification = dao.getId(id);
		return teacherQualification;
	}

    public List<TeacherQualification> findAllList(TeacherQualification teacherQualification) {
        teacherQualification.getSqlMap().put("dsf", dataScopeFilter(teacherQualification.getCurrentUser(), "o", "a"));
        List<TeacherQualification> teacherQualificationList = dao.findAllList(teacherQualification);
        return teacherQualificationList;
    }

}
