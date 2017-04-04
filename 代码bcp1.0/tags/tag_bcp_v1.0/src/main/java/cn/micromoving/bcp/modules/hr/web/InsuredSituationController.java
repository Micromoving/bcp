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
import cn.micromoving.bcp.modules.hr.entity.InsuranceRule;
import cn.micromoving.bcp.modules.hr.entity.InsuredSituation;
import cn.micromoving.bcp.modules.hr.service.InsuranceRuleService;
import cn.micromoving.bcp.modules.hr.service.InsuredSituationService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.hr.utils.HrUtils;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/insuredSituation")
public class InsuredSituationController extends BaseController {
	@Autowired
	private InsuredSituationService insuredSituationService;
	@Autowired
	private InsuranceRuleService insuranceRuleService;
	@Autowired
	private SystemService systemService;

	/**
	 * 根据主键， 取得工作经验记录。
	 * 
	 * @param id
	 *            primary key
	 * @return Work entity
	 */
	@ModelAttribute
	public InsuredSituation get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的工作经验记录。否则创建一个新的工作经验对象。 */

		if (StringUtils.isNotBlank(id)) {
			return insuredSituationService.get(id);
		} else {
			return new InsuredSituation();
		}
	}

	/**
	 * 根据主键，查询到工作经验信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param work
	 *            工作经验entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回工作经验编辑页面
	 */
	@RequiresPermissions("hr:insuredSituation:update")
	@RequestMapping(value = "form")
	public String form(InsuredSituation insuredSituation, Model model,HttpServletRequest request) {

		List<InsuranceRule> list = insuranceRuleService
				.findList(new InsuranceRule());
		insuredSituation.setInsList(list);
		/* 将传入的insuranceRule值存入model中 */
		model.addAttribute("insuredSituation", insuredSituation); // ?
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		/* 获取当前用户 */
		insuredSituation.setUser(user);
		insuredSituation.setEmployee(emp);
		/* 返回form页面 */
		return "modules/hr/insuredSituationForm";
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "saveBatch")
	public String saveBatch(InsuredSituation insuredSituation,
			HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		double[] baseList = insuredSituation.getBaseArray();
		String[] insuranceIdArray = insuredSituation.getInsuranceIdArray();
		for (int index = 0; index < insuranceIdArray.length; index++) {
			if (null != insuranceIdArray[index]) {
				InsuredSituation data = insuredSituationService
						.get(insuranceIdArray[index]);
				data.setBase(baseList[index]);
				insuredSituationService.save(data);
			}

		}
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "提交成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/insuredSituation/list?repage";
	}

	/**
	 * 添加、更新险种规则记录。操作成功后，转至列表页面。
	 * 
	 * @param insuranceRule
	 *            险种规则entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions({"hr:insuredSituation:create","hr:insuredSituation:update"})
	@RequestMapping(value = "save")
	public String save(InsuredSituation insuredSituation,
			HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		/* 在model中取得insuranceRule的相关值，进行服务端参数有效性验证。 */
		if (!beanValidator(model, insuredSituation)) {
			return form(insuredSituation, model,request);
		}
		/* 在model中获取起始日期和终止日期，进行服务端参数有效性验证（既比较日期的先后顺序，又与今天的日期作比较） */
		if (!dateValidator(model, "0", insuredSituation.getInsuredDate(), insuredSituation.getSchoolInsuredDate())) {
			return form(insuredSituation, model,request);
		}
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee employee = (Employee) request.getSession().getAttribute(HrConstant.EMPLOYEE);
		/* 获取当前用户insuranceRule信息 */
		insuredSituation.setUser(user);
		insuredSituation.setEmployee(employee);
		if(insuredSituationService.exist(insuredSituation)){
			addMessage(model, "险种已存在！");
			return form(insuredSituation, model,request);
		}
		/* 调用save保存获取到的insuranceRule值 */
		insuredSituationService.save(insuredSituation);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "提交成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/insuredSituation/list?repage";
	}

	/**
	 * 删除险种规则。操作成功后，转至列表页面。
	 * 
	 * @param insuranceRule
	 *            险种规则entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("hr:insuredSituation:delete")
	@RequestMapping(value = "delete")
	public String delete(InsuredSituation insuredSituation,
			RedirectAttributes redirectAttributes) {
		/* 调用delete删除当前insuranceRule值 */
		insuredSituationService.delete(insuredSituation);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/insuredSituation/list?repage";
	}

	/**
	 * 查询用户的全部工作经验信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param insuranceRule
	 *            险种规则
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 险种规则信息（不分页）
	 */
	@RequiresPermissions("hr:insuredSituation:search")
	@RequestMapping(value = { "list", "" })
	public String list(InsuredSituation insuredSituation,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		/* 获取当前用户insuranceRule信息 */
		// work.setUser(UserUtils.getUser());

		User user = (User)request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee)request.getSession().getAttribute(HrConstant.EMPLOYEE);
		
		model.addAttribute("user", user);
		model.addAttribute("employee", emp);
		insuredSituation.setUser(user);
		/* 通过service将insuranceRule信息保存至list */
		model.addAttribute("list",
				insuredSituationService.findList(insuredSituation));
		/* 返回list页面 */
		return "modules/hr/insuredSituationList";
	}
}
