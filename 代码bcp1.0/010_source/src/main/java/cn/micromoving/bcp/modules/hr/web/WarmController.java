package cn.micromoving.bcp.modules.hr.web;


import javax.servlet.http.HttpServletRequest;

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
import cn.micromoving.bcp.modules.hr.entity.Warm;
import cn.micromoving.bcp.modules.hr.entity.Warm;
import cn.micromoving.bcp.modules.hr.service.SalaryPlanService;
import cn.micromoving.bcp.modules.hr.service.WarmService;

@Controller
@RequestMapping(value = "${adminPath}/hr/warm")
public class WarmController extends BaseController{
	
	@Autowired
	private WarmService warmService;
	
	@Autowired
	private SalaryPlanService salaryPlanService;
	
	/**
	 * 根据主键， 取得取暖表记录。
	 * 
	 * @param id
	 *            primary key
	 * @return Warm entity
	 */
	@ModelAttribute
	public Warm get(@RequestParam(required=false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的取暖表记录。否则创建一个新的取暖表对象。 */
		if (StringUtils.isNotBlank(id)) {
			return warmService.get(id);
		} else {
			return new Warm();
		}
	}



	/**
	 * 添加、更新取暖标准记录。操作成功后，转至列表页面。
	 * 
	 * @param warm, Model model) {
		return null;
	 *            取暖标准entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */

	@RequestMapping(value = "batchSave")
	public String batchSave( Warm  warm, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		/*保存上报工作量信息*/
		warmService.batchSave(warm);
		/*重定向显示消息*/
		addMessage(redirectAttributes, "保存成功");
		/*重定向list页面*/
		return "redirect:" + adminPath + "/hr/warm/list?repage";
	}
	
	
	/**
	 * 查询用户的全部取暖表信息，以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * @param warm 取暖表
	 * @param request http请求
	 * @param response http 响应
	 * @param model  视图模型
	 * @return  用户的全部取暖表信息
	 */
	@RequestMapping(value = { "list", "" }) 
	public String list(Warm warm, HttpServletRequest request,Model model) {
	    /* 方案编号,并关联查询方案信息 */
        String salaryPlanId = request.getParameter("salaryPlan.id");
        if (StringUtils.isNotBlank(salaryPlanId)) {
            request.getSession().setAttribute("salaryPlan.id", salaryPlanId);
            SalaryPlan sPlan = new SalaryPlan();
            sPlan.setId(salaryPlanId);
            warm.setSalaryPlan(salaryPlanService.get(sPlan));
        } else {
            SalaryPlan sPlan = new SalaryPlan();
            sPlan.setId(request.getSession().getAttribute("salaryPlan.id").toString());
            warm.setSalaryPlan(salaryPlanService.get(sPlan));
        }
        model.addAttribute("warm",warm);
	    /*设置职员类型为1（教师）*/
		warm.setPostType("1");
		/*通过service将warm信息保存至list*/ 
		model.addAttribute("list1", warmService.findList(warm));
		
		/*设置职员类型为3（教辅）*/
		warm.setPostType("3");
		/*通过service将warm信息保存至list*/
		model.addAttribute("list3", warmService.findList(warm));
		
		/*设置职员类型为2（职员）*/
		warm.setPostType("2");
		/*通过service将warm信息保存至list*/
		model.addAttribute("list2", warmService.findList(warm));
		
		/*设置职员类型为5（工勤）*/
		warm.setPostType("5");
		/*通过service将warm信息保存至list*/
		model.addAttribute("list5", warmService.findList(warm));
		
		/*设置职员类型为4（专职辅导员）*/
		warm.setPostType("4");
		/*通过service将warm信息保存至list*/
		model.addAttribute("list4", warmService.findList(warm));
		/*返回list页面*/
		return "modules/hr/warmList";
	}
}
