package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.TeacherQualificationDao;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;
@Service
@Transactional(readOnly = true)
public class TeacherQualificationService extends CrudService<TeacherQualificationDao, TeacherQualification>{
	
	public TeacherQualification getId(String id){
		TeacherQualification teacherQualification = new TeacherQualification();
		teacherQualification = dao.getId(id);
		return teacherQualification;
	}

}
