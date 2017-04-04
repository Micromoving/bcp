package cn.micromoving.bcp.modules.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.PostAppointmentDao;
import cn.micromoving.bcp.modules.hr.entity.Honor;
import cn.micromoving.bcp.modules.hr.entity.PostAppointment;

@Service
@Transactional(readOnly = true)
public class PostAppointmentService extends CrudService<PostAppointmentDao, PostAppointment> {

	@Autowired
	private PostAppointmentDao postAppointmentDao;

	public Page<PostAppointment> findPostAppointment(Page<PostAppointment> page, PostAppointment postAppointment) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		postAppointment.getSqlMap().put("dsf", dataScopeFilter(postAppointment.getCurrentUser(), "o", "a"));
		// 设置分页参数
		postAppointment.setPage(page);
		// 执行分页查询
		page.setList(postAppointmentDao.findList(postAppointment));
		return page;
	}

}