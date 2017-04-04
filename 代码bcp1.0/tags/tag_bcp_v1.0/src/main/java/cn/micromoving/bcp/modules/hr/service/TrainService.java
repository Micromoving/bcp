package cn.micromoving.bcp.modules.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.TrainDao;
import cn.micromoving.bcp.modules.hr.entity.Train;
@Service
@Transactional(readOnly = true)
public class TrainService extends CrudService<TrainDao, Train>{

	@Autowired
	private TrainDao trainDao;
	


	public Page<Train> findtrain(Page<Train> page, Train train) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		train.getSqlMap().put("dsf", dataScopeFilter(train.getCurrentUser(), "o", "a"));
		// 设置分页参数
		train.setPage(page);
		// 执行分页查询
		page.setList(trainDao.findList(train));
		return page;
	}
	}