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
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;
import cn.micromoving.bcp.modules.hr.entity.SubsidiaryStandard;
import cn.micromoving.bcp.modules.hr.service.SalaryPlanService;
import cn.micromoving.bcp.modules.hr.service.SubsidiaryStandardService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/subsidiaryStandard")
public class SubsidiaryStandardController extends BaseController {
	@Autowired
	private SubsidiaryStandardService subsidiarystandardService;
	@Autowired
	private SalaryPlanService salaryPlanService;

	/**
	 * 根据主键， 取得津贴标准。
	 * 
	 * @param id
	 *            primary key
	 * @return subsidiarystandard entity
	 */
	@ModelAttribute
	public SubsidiaryStandard get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的津贴标准表。否则创建一个新的津贴标准表对象。 */
		if (StringUtils.isNotBlank(id)) {
			return subsidiarystandardService.get(id);
		} else {
			return new SubsidiaryStandard();
		}
	}

	/**
	 * 根据主键，查询到津贴标准表信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param subsidiarystandard
	 *            津贴标准entity，传递数据。
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "form")
	public String form(SubsidiaryStandard subsidiarystandard,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		/* 方案编号 */
		List<SalaryPlan> list = salaryPlanService.findList(new SalaryPlan());
		for (SalaryPlan sp : list) {
			if(sp.getPlanStatus().equals("1")){
				subsidiarystandard.setSalaryPlan(new SalaryPlan());
				subsidiarystandard.getSalaryPlan().setId(sp.getId());
			}
		}
		subsidiarystandard.setUser(UserUtils.getUser());
		/* 获取津贴标准信息绑定到model中 */
		model.addAttribute("subsidiarystandard", subsidiarystandard);
		/* 返回form页面 */
		return "modules/hr/subsidiaryStandardForm";

	}

	/**
	 * 查询用户的全部津贴标准信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param subsidiarystandard
	 *            津贴标准
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部津贴标准信息（不分页）
	 */

	@RequestMapping(value = { "list", "" })
	public String list(SubsidiaryStandard subsidiarystandard,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		/* 方案编号 */
		 String salaryPlanId = request.getParameter("salaryPlan.id");
		 if (StringUtils.isNotBlank(salaryPlanId)) {
		 request.getSession().setAttribute("salaryPlan.id", salaryPlanId);
		 } else {
		 SalaryPlan sPlan = new SalaryPlan();
		 sPlan.setId(request.getSession().getAttribute("salaryPlan.id").toString());
		 subsidiarystandard.setSalaryPlan(sPlan);
		 }

		/* 获取津贴标准信息以list方式返回放入model中 */
		List<SubsidiaryStandard> list = subsidiarystandardService
				.findList(subsidiarystandard);
		model.addAttribute("list", list);
		model.addAttribute("subsidiarystandard", subsidiarystandard);
		/* 返回list页面 */
		return "modules/hr/subsidiaryStandardList";
	}

	/**
	 * 添加、更新津贴标准记录。操作成功后，转至列表页面。
	 * 
	 * @param subsidiarystandard
	 *            津贴标准entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(SubsidiaryStandard subsidiarystandard,RedirectAttributes redirectAttributes) {
		List<SalaryPlan> list = salaryPlanService.findList(new SalaryPlan());
		for (SalaryPlan sp : list) {
			if(sp.getPlanStatus().equals("1")){
				subsidiarystandard.setSalaryPlan(new SalaryPlan());
				subsidiarystandard.getSalaryPlan().setId(sp.getId());
			}
		}
		/* 保存津贴标准信息 */
		subsidiarystandardService.save(subsidiarystandard);
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "保存成功");
		/* 重定向到list页面 */
		return "redirect:" + adminPath + "/hr/subsidiaryStandard/list?repage";
	}

	/**
	 * 删除津贴标准表。操作成功后，转至列表页面。
	 * 
	 * @param subsidiarystandard
	 *            津贴标准entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("hr:user:view")
	@RequestMapping(value = "delete")
	public String delete(SubsidiaryStandard subsidiarystandard,
			RedirectAttributes redirectAttributes) {
		/* 删除津贴标准信息 */
		subsidiarystandardService.delete(subsidiarystandard);
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向到list页面 */
		return "redirect:" + adminPath + "/hr/subsidiaryStandard/list?repage";
	}

}
