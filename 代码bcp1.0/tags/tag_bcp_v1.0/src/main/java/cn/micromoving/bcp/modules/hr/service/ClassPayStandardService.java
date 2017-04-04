package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.ClassPayStandardDao;
import cn.micromoving.bcp.modules.hr.entity.ClassPayStandard;

@Service
@Transactional(readOnly = true)
public class ClassPayStandardService extends CrudService<ClassPayStandardDao,ClassPayStandard> {
	@Autowired
	private ClassPayStandardDao classPayStandardDao;

	public void bathSave(ClassPayStandard entity){
		List<String>  dataIdList  =entity.getDataIdList();
		List<Double>  classPayList = entity.getClassPayList();
		
		for(int index = 0;index < dataIdList.size(); index++ ){
			String id = dataIdList.get(index);
			Double classPay = classPayList.get(index);
			ClassPayStandard data = classPayStandardDao.get(id);
			data.setClassPay(classPay);
			classPayStandardDao.update(data);
		}
	}
}
