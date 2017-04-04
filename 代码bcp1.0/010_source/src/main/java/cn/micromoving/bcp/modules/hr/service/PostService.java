package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.PostDao;
import cn.micromoving.bcp.modules.hr.entity.Post;

@Service
@Transactional(readOnly = true)
public class PostService extends CrudService<PostDao, Post> {

	@Autowired
	private PostDao postDao;

	public Page<Post> findPost(Page<Post> page, Post post) {
		// 设置分页参数
		post.setPage(page);
		// 执行分页查询
		page.setList(postDao.findList(post));
		return page;
	}
	
	public List<Post> findPostNameList(Post post){
		 List<Post> list = postDao.findPostNameList(post);
		return list;
	}
}
