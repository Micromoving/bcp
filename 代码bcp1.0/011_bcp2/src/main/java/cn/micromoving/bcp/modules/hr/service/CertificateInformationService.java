package cn.micromoving.bcp.modules.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.CertificateInformationDao;
import cn.micromoving.bcp.modules.hr.dao.EmployeeDao;
import cn.micromoving.bcp.modules.hr.entity.CertificateInformation;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.Honor;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;
import cn.micromoving.bcp.modules.sys.dao.UserDao;
import cn.micromoving.bcp.modules.sys.entity.User;

@Service
@Transactional(readOnly = true)
public class CertificateInformationService extends CrudService<CertificateInformationDao, CertificateInformation> {

	@Autowired
	private CertificateInformationDao certificateInformationDao;
	
	
	
	public Page<CertificateInformation> findCertificateInformation(Page<CertificateInformation> page, CertificateInformation certificateInformation) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		certificateInformation.getSqlMap().put("dsf", dataScopeFilter(certificateInformation.getCurrentUser(), "o", "a"));
		// 设置分页参数
		certificateInformation.setPage(page);
		// 执行分页查询
		page.setList(certificateInformationDao.findList(certificateInformation));
		return page;
		
		
	}
	public CertificateInformation getId(String id) {
		// TODO Auto-generated method stub
		CertificateInformation certificateInformation = new CertificateInformation();
		certificateInformation = dao.getId(id);
		return certificateInformation;
	}
	
	
	

}