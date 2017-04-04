package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.TitleDao;
import cn.micromoving.bcp.modules.hr.entity.Title;
@Service
@Transactional(readOnly = true)
public class TitleService extends CrudService<TitleDao, Title>{

}
