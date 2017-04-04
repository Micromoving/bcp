package cn.micromoving.bcp.modules.hr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.AttachmentDao;
import cn.micromoving.bcp.modules.hr.entity.Attachment;
@Service
@Transactional(readOnly = true)
public class AttachmentService extends CrudService<AttachmentDao, Attachment> {

}
