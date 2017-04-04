package cn.micromoving.bcp.modules.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.TeacherQualificationDao;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;

@Service
@Transactional(readOnly = true)
public class TeacherQualificationService extends CrudService<TeacherQualificationDao, TeacherQualification> {

    @Autowired
    private TeacherQualificationDao teacherQualificationDao;
    
    public Page<TeacherQualification> findTeacherQualification(Page<TeacherQualification> page, TeacherQualification teacherQualification) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        teacherQualification.getSqlMap().put("dsf", dataScopeFilter(teacherQualification.getCurrentUser(), "o", "a"));
        // 设置分页参数
        teacherQualification.setPage(page);
        // 执行分页查询
        page.setList(teacherQualificationDao.findList(teacherQualification));
        return page;
    }

	public TeacherQualification getId(String id) {
		// TODO Auto-generated method stub
		TeacherQualification teacherQualification = new TeacherQualification();
		teacherQualification = dao.getId(id);
		return teacherQualification;
	}
}