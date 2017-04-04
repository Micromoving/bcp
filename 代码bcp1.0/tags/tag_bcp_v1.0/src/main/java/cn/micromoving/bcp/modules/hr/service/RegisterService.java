package cn.micromoving.bcp.modules.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.SafeQuestionDao;
import cn.micromoving.bcp.modules.hr.entity.SafeQuestion;
import cn.micromoving.bcp.modules.sys.dao.UserDao;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.SystemService;
@Service
@Transactional(readOnly = true)
public class RegisterService extends CrudService<UserDao, User>{
	
	@Autowired
	private SafeQuestionDao safeQuestionDao;
	
	
	public void saveUser(User user){
		
		super.save(user);
		
		SafeQuestion safe = user.getSafeQuestion();
		safe.setId(user.getId());
		safeQuestionDao.insert(safe);
	}
	
	public SafeQuestion getQuestion(String id){
		SafeQuestion entity = new SafeQuestion();
		entity.setId(id);
		return safeQuestionDao.get(entity);
	}
}
