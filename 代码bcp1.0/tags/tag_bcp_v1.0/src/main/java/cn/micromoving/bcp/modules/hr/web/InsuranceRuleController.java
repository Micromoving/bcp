package cn.micromoving.bcp.modules.hr.web;

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
import cn.micromoving.bcp.modules.hr.entity.InsuranceRule;

import cn.micromoving.bcp.modules.hr.service.InsuranceRuleService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/insuranceRule")
public class InsuranceRuleController extends BaseController{
	@Autowired
	private InsuranceRuleService insuranceRuleService;
	
	/**
	 * 根据主键， 取得工作经验记录。
	 * 
	 * @param id
	 *            primary key
	 * @return Work entity
	 */
	@ModelAttribute
	public InsuranceRule get(@RequestParam(required=false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的工作经验记录。否则创建一个新的工作经验对象。 */
	
		if (StringUtils.isNotBlank(id)) {
			return insuranceRuleService.get(id);
		} else {
			return new InsuranceRule();
		}
	}
	
	/**
	 * 根据主键，查询到工作经验信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param work
	 *            工作经验entity，传递数据。
	 * @param model 视图模型
	 * @return 返回工作经验编辑页面
	 */

	@RequestMapping(value = "form")
	public String form(InsuranceRule insuranceRule, Model model) {
		/*将传入的insuranceRule值存入model中*/
		model.addAttribute("insuranceRule", insuranceRule); //?
		/*返回form页面*/
		return "modules/hr/insuranceRuleForm"; 
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
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "save")
	public String save(InsuranceRule insuranceRule, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		/*在model中取得insuranceRule的相关值，进行服务端参数有效性验证。*/
		if (!beanValidator(model, insuranceRule)) {
			return form(insuranceRule, model);
		}
		
		/*在model中获取险种类型，如果为空，返回至form页面*/
		if(insuranceRule.getInsuranceType() == null) {
			return form(insuranceRule, model);
		}
		
		
		/*获取当前用户insuranceRule信息*/
		//work.setUser(UserUtils.getUser());	 ???????
		
		/*调用save保存获取到的insuranceRule值*/
		insuranceRuleService.save(insuranceRule);
		/*添加Flash信息*/
		addMessage(redirectAttributes, "提交成功");
		/*重定向至list页面*/
		return "redirect:" + adminPath + "/hr/insuranceRule/list?repage";
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
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "delete")
	public String delete(InsuranceRule insuranceRule, RedirectAttributes redirectAttributes) {
		/*调用delete删除当前insuranceRule值*/
		insuranceRuleService.delete(insuranceRule);
		/*添加Flash信息*/
		addMessage(redirectAttributes, "删除成功");
		/*重定向至list页面*/
		return "redirect:" + adminPath + "/hr/insuranceRule/list?repage";
	}
	
	/**
	 * 查询用户的全部工作经验信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * @param insuranceRule 险种规则
	 * @param request http请求
	 * @param response http 响应
	 * @param model  视图模型
	 * @return  险种规则信息（不分页）
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "list", "" })
	public String list(InsuranceRule insuranceRule, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		/*获取当前用户insuranceRule信息*/
		//work.setUser(UserUtils.getUser());
		
		/*通过service将insuranceRule信息保存至list*/
		model.addAttribute("list", insuranceRuleService.findList(insuranceRule));
		/*返回list页面*/
		return "modules/hr/insuranceRuleList";
	}
}
