package cn.micromoving.bcp.modules.hr.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import cn.micromoving.bcp.common.mapper.JsonMapper;
import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.dao.PostDao;
import cn.micromoving.bcp.modules.hr.entity.Post;
import cn.micromoving.bcp.modules.hr.service.PostService;
import cn.micromoving.bcp.modules.sys.entity.Dict;
import cn.micromoving.bcp.modules.sys.entity.Role;
import cn.micromoving.bcp.modules.sys.utils.DictUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/post")
public class PostController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private PostDao postDao;

	/**
	 * 根据主键， 取得岗位职数
	 * 
	 * @param id
	 *            primary key
	 * @return post entity
	 */
	@ModelAttribute
	public Post get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的岗位职数。否则创建一个新的岗位职数对象。 */
		if (StringUtils.isNotBlank(id)) {
			return postService.get(id);
		} else {
			return new Post();
		}
	}

	/**
	 * 根据主键，查询到岗位职数信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param post
	 *            岗位职数entity，传递数据。
	 * @param model
	 * @return
	 */
	@RequiresPermissions("hr:user:view")
	@RequestMapping(value = "form")
	public String form(Post post, Model model) {
		if (post.getPostLevel() != null) {

			List<String> postLevelList = Lists.newArrayList();
			String[] postLevels = post.getPostLevel().split(",");
			for (String temp : postLevels) {
				postLevelList.add(temp);
			}
			post.setPostLevelList(postLevelList);
		}
		/* 获取岗位职数信息绑定到model中 */
		model.addAttribute("post", post);
		/* 返回form页面 */
		return "modules/hr/postForm";

	}

	/**
	 * 查询用户的全部岗位职数信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param post
	 *            岗位职数
	 * @param request
	 *            http 请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部岗位职数信息（不分页）
	 */
	@RequiresPermissions("hr:user:view")
	@RequestMapping(value = { "list", "" })
	public String list(Post post, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		/* 通过postService.findPost()方法将post信息进行分页 */
		Page<Post> page = postService.findPost(
				new Page<Post>(request, response), post);

		/* 获取岗位职数信息以list方式返回放入model中 */
		model.addAttribute("page", page);
		/* 返回list页面 */
		return "modules/hr/postList";
	}

	/**
	 * 添加、更新岗位职数记录。操作成功后，转至列表页面。
	 * 
	 * @param post
	 *            岗位职数 entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return
	 */
	@RequiresPermissions("hr:user:view")
	@RequestMapping(value = "save")
	public String save(Post post, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		/* 服务器端验证约束规则 */
		if (!beanValidator(model, post)) {
			return form(post, model);
		}
		if (null != post.getPostLevelList()) {
			StringBuffer sb = new StringBuffer();

			for (String temp : post.getPostLevelList()) {
				sb.append(temp);
				sb.append(",");
			}

			post.setPostLevel(sb.toString());
		}
		/* 保存岗位职数信息 */
		postService.save(post);
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "保存成功");
		/* 重定向到list页面 */
		return "redirect:" + adminPath + "/hr/post/list?repage";

	}

	/**
	 * 删除岗位职数表。操作成功后，转至列表页面。
	 * 
	 * @param post
	 *            岗位职数entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("hr:user:view")
	@RequestMapping(value = "delete")
	public String delete(Post post, RedirectAttributes redirectAttributes) {
		/* 删除岗位职数信息 */
		postService.delete(post);
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向到list页面 */
		return "redirect:" + adminPath + "/hr/post/list?repage";
	}

}
