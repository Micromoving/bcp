package cn.micromoving.bcp.modules.hr.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.ClassPayStandard;
import cn.micromoving.bcp.modules.hr.entity.ReportedWorkloade;
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;
import cn.micromoving.bcp.modules.hr.service.ClassPayStandardService;


@Controller
@RequestMapping(value = "${adminPath}/hr/classPayStandard")

public class ClassPayStandardController extends BaseController {
	@Autowired
	
	private ClassPayStandardService classPayStandardService;//一个对象
	


	
	/**
	 *  根据主键，取得课酬标准
	 * @param id primary key
	 * @return ClassPayStandard entity
	 */
	@ModelAttribute

	public  ClassPayStandard get(@RequestParam(required=false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的课酬标准记录。否则创建一个新的课酬标准对象。 */
		if (StringUtils.isNotBlank(id)){
			return  classPayStandardService.get(id);
		}else{
			return new  ClassPayStandard();
		}
	}
	
	
	/**
	 * 查询用户的全部工作经验信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * @param classPayStandard 险种规则
	 * @param request http请求
	 * @param response http 响应
	 * @param model  视图模型
	 * @return  险种规则信息（不分页）
	 */

	@RequestMapping(value = { "list", "" })
	public String list(ClassPayStandard classPayStandard, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		 
		String salaryPlanId = request.getParameter("salaryPlan.id");
	        if (StringUtils.isNotBlank(salaryPlanId)) {
	            request.getSession().setAttribute("salaryPlan.id", salaryPlanId);
	        } else {
	            SalaryPlan sPlan = new SalaryPlan();
	            sPlan.setId(request.getSession().getAttribute("salaryPlan.id").toString());
	            classPayStandard.setSalaryPlan(sPlan);
	        }
		
		/*获取当前用户classPayStandard信息*/
		//work.setUser(UserUtils.getUser());
		
		/*通过service将classPayStandard信息保存至list*/
		model.addAttribute("list", classPayStandardService.findList(classPayStandard));
		/*返回list页面*/
		return "modules/hr/classPayStandardList";
	}
	
	
	@RequestMapping(value = "batchSave")
	public String batchSave( ClassPayStandard  classPayStandard, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		/*保存上报工作量信息*/
		classPayStandardService.bathSave(classPayStandard);
		/*重定向显示消息*/
		addMessage(redirectAttributes, "保存成功");
		/*重定向list页面*/
		return "redirect:" + adminPath + "/hr/classPayStandard/list?repage";
	}
	
	
}
