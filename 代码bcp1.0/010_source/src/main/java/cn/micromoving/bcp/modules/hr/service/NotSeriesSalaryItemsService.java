package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.NotSeriesSalaryItemsDao;
import cn.micromoving.bcp.modules.hr.entity.NotSeriesSalaryItems;

@Service
@Transactional(readOnly = true)
public class NotSeriesSalaryItemsService extends
		CrudService<NotSeriesSalaryItemsDao, NotSeriesSalaryItems> {
}
