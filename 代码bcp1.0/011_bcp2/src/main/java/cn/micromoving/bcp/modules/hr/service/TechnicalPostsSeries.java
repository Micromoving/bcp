package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.TechnicalPostsDao;
import cn.micromoving.bcp.modules.hr.entity.TechnicalPosts;

@Service
@Transactional(readOnly = true)
public class TechnicalPostsSeries extends CrudService<TechnicalPostsDao, TechnicalPosts> {

}