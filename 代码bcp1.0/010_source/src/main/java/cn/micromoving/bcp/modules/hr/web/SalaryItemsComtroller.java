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
import cn.micromoving.bcp.modules.hr.entity.SalaryItems;
import cn.micromoving.bcp.modules.hr.service.SalaryItemsService;

/**
 * 工资项Controller
 * @author wangzhichen
 * @version 2016-7-23
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/salaryItems")
public class SalaryItemsComtroller extends BaseController{
	@Autowired
	private SalaryItemsService salaryItemsService;
	
	
	/**
	 * 根据主键， 取得工资项。
	 * 
	 * @param id
	 *            primary key
	 * @return Work entity
	 */
	@ModelAttribute
	public SalaryItems get(@RequestParam(required=false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的工资项。否则创建一个新的工资项。 */
	
		if (StringUtils.isNotBlank(id)) {
			return salaryItemsService.get(id);
		} else {
			return new SalaryItems();
		}
	}
	
	
	/**
	 * 根据主键，查询到工资项信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param work
	 *            工资项entity，传递数据。
	 * @param model 视图模型
	 * @return 返回工资项编辑页面
	 */

	@RequestMapping(value = "form")
	public String form(SalaryItems salaryItems, Model model) {
		/*将传入的salaryItems值存入model中*/
		model.addAttribute("salaryItems", salaryItems); //?
		/*返回form页面*/
		return "modules/hr/salaryItemsForm"; 
	}
	
	
	/**
	 * 添加、更新工资项。操作成功后，转至列表页面。
	 * 
	 * @param salaryItems
	 *            工资项entity
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
	public String save(SalaryItems salaryItems, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		
		
		/*调用save保存获取到的salaryItems值*/
		salaryItemsService.save(salaryItems);
		/*添加Flash信息*/
		addMessage(redirectAttributes, "提交成功");
		/*重定向至list页面*/
		return "redirect:" + adminPath + "/hr/salaryItems/list?repage";
	}
	
	
	/**
	 * 删除工资项。操作成功后，转至列表页面。
	 * 
	 * @param salaryItems
	 *            工资项entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "delete")
	public String delete(SalaryItems salaryItems, RedirectAttributes redirectAttributes) {
		/*调用delete删除当前salaryItems值*/
		salaryItemsService.delete(salaryItems);
		/*添加Flash信息*/
		addMessage(redirectAttributes, "删除成功");
		/*重定向至list页面*/
		return "redirect:" + adminPath + "/hr/salaryItems/list?repage";
	}
	
	
	/**
	 * 查询全部工资项（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * @param salaryItems 工资项
	 * @param request http请求
	 * @param response http 响应
	 * @param model  视图模型
	 * @return  工资项信息（不分页）
	 */
	@RequestMapping(value = { "list", "" })
	public String list(SalaryItems salaryItems, HttpServletRequest request,
			HttpServletResponse response, Model model) {
	
		/*通过service将salaryItems信息保存至list*/
		model.addAttribute("list", salaryItemsService.findList(salaryItems));
		/*返回list页面*/
		return "modules/hr/salaryItemsList";
	}
}


