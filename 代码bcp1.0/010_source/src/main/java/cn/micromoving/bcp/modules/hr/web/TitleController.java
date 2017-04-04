package cn.micromoving.bcp.modules.hr.web;

import java.util.Date;

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

import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.Title;
import cn.micromoving.bcp.modules.hr.service.TitleService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/title")
public class TitleController extends BaseController {
	@Autowired
	private TitleService titleService;

	/**
	 * 根据主键， 取得校内职务。
	 * 
	 * @param id
	 *            primary key
	 * @return title entity
	 */
	@ModelAttribute
	public Title get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的校内职务。否则创建一个新的校内职务对象。 */
		if (StringUtils.isNotBlank(id)) {
			return titleService.get(id);
		} else {
			return new Title();
		}
	}

	/**
	 * 根据主键，查询到校内职务信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param title
	 *            校内职务entity，传递数据。
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(Title title, Model model) {
		/*获取校内职务信息绑定到model中*/
		model.addAttribute("title", title);
		/*返回form页面*/
		return "modules/hr/titleForm";

	}

	/**
	 * 查询用户的全部校内职务信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param title
	 *            校内职务
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部校内职务信息（不分页）
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "list", "" })
	public String list(Title title, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		/*获取当前用户信息*/
		title.setUser(UserUtils.getUser());
		/*获取校内职务信息以list方式返回放入model中*/
		model.addAttribute("list", titleService.findList(title));
		/*返回list页面*/
		return "modules/hr/titleList";
	}

	/**
	 * 添加、更新校内职务记录。操作成功后，转至列表页面。
	 * 
	 * @param title
	 *            校内职务entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "save")
	public String save(Title title, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		/*服务器端验证约束规则*/
		if (!beanValidator(model, title)) {
			return form(title, model);
		}
		/*判断结束时间是否为空，为空则获取当前日期*/
		if(title.getEndDate() == null)  {
			Date a = new Date();
			title.setEndDate(a);
		}
		/*判断时间是否合法 1：只和今天的日期比较；2：只比较俩个日期的先后顺序;0：既比较日期的先后顺序，又与今天的日期作比较。验证成功：返回true；严重失败：将错误信息添加到 message 中*/
		if(!dateValidator(model,"0",title.getStartDate(),title.getEndDate())) {
			return form(title,model);
		}
		/*获取当前用户信息*/
		title.setUser(UserUtils.getUser());
		/*保存校内职务信息*/
		titleService.save(title);
		/*重定向显示消息*/
		addMessage(redirectAttributes, "保存成功");
		/*重定向到list页面*/
		return "redirect:" + adminPath + "/hr/title/list?repage";
	}

	/**
	 * 删除校内职务表。操作成功后，转至列表页面。
	 * 
	 * @param title
	 *            校内职务entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "delete")
	public String delete(Title title, RedirectAttributes redirectAttributes) {
		/*删除校内职务信息*/
		titleService.delete(title);
		/*重定向显示消息*/
		addMessage(redirectAttributes, "删除成功");
		/*重定向到list页面*/
		return "redirect:" + adminPath + "/hr/title/list?repage";
	}

}
