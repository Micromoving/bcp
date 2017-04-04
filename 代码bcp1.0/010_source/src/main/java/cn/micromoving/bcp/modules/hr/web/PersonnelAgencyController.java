package cn.micromoving.bcp.modules.hr.web;

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

import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.PersonnelAgency;
import cn.micromoving.bcp.modules.hr.service.PersonnelAgencyService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.hr.utils.HrUtils;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.SystemService;

@Controller
@RequestMapping(value = "${adminPath}/hr/personnelAgency")
public class PersonnelAgencyController extends BaseController {
	@Autowired
	private PersonnelAgencyService personnelAgencyService;
	@Autowired
	private SystemService systemService;

	/**
	 * 根据主键， 取得人事代理。
	 * 
	 * @param id
	 *            primary key
	 * @return personnelAgency entity
	 */
	@ModelAttribute
	public PersonnelAgency get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的人事代理表。否则创建一个新的人事代理表对象。 */
		if (StringUtils.isNotBlank(id)) {
			return personnelAgencyService.get(id);
		} else {
			return new PersonnelAgency();
		}
	}

	/**
	 * 根据主键，查询到人事代理表信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param personnelAgency
	 *            人事代理entity，传递数据。
	 * @param model
	 * @return
	 */
	@RequiresPermissions("hr:personnelAgency:update")
	@RequestMapping(value = "form")
	public String form(PersonnelAgency personnelAgency, Model model,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		/* 获取当前用户 */
		personnelAgency.setUser(user);
		/* 获取人事代理信息绑定到model中 */
		model.addAttribute("personnelAgency", personnelAgency);
		/* 返回form页面 */
		return "modules/hr/personnelAgencyForm";

	}

	/**
	 * 查询用户的全部人事代理信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param personnelAgency
	 *            人事代理
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部人事代理信息（不分页）
	 */
	@RequiresPermissions("hr:personnelAgency:search")
	@RequestMapping(value = { "list", "" })
	public String list(PersonnelAgency personnelAgency,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		/* 获取当前用户信息 */
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		model.addAttribute("user", user);
		model.addAttribute("employee", emp);
		personnelAgency.setUser(user);
		/* 获取人事代理信息以list方式返回放入model中 */
		List<PersonnelAgency> list = personnelAgencyService
				.findList(personnelAgency);
		if (list.size() == 0) {
			PersonnelAgency pa = new PersonnelAgency();
			pa.setArchivesIsComplete("");
			pa.setMissingMaterial("");
			pa.setArchivesList("");
			pa.setComments("");
			list.add(pa);
		}
		model.addAttribute("list", list);
		/* 返回list页面 */
		return "modules/hr/personnelAgencyList";
	}

	/**
	 * 添加、更新人事代理记录。操作成功后，转至列表页面。
	 * 
	 * @param personnelAgency
	 *            人事代理entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return
	 */
	@RequiresPermissions({"hr:personnelAgency:create","hr:personnelAgency:update"})
	@RequestMapping(value = "save")
	public String save(PersonnelAgency personnelAgency,
			HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		/* 服务器端验证约束规则 */
		if (!beanValidator(model, personnelAgency)) {
			return form(personnelAgency, model,request);
		}
		User user = (User) request.getSession().getAttribute(HrConstant.USER);

		/* 获取当前用户信息 */
		personnelAgency.setUser(user);
		/* 保存人事代理信息 */
		personnelAgencyService.save(personnelAgency);
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "保存成功");
		/* 重定向到list页面 */
		return "redirect:" + adminPath + "/hr/personnelAgency/list?repage";
	}

	/**
	 * 删除人事代理表。操作成功后，转至列表页面。
	 * 
	 * @param personnelAgency
	 *            人事代理entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("hr:personnelAgency:delete")
	@RequestMapping(value = "delete")
	public String delete(PersonnelAgency personnelAgency,
			RedirectAttributes redirectAttributes) {
		/* 删除人事代理信息 */
		personnelAgencyService.delete(personnelAgency);
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向到list页面 */
		return "redirect:" + adminPath + "/hr/personnelAgency/list?repage";
	}

}
