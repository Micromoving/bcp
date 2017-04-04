package cn.micromoving.bcp.modules.hr.web;

import java.util.List;

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
import cn.micromoving.bcp.modules.hr.entity.Duty;
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;
import cn.micromoving.bcp.modules.hr.service.DutyService;
import cn.micromoving.bcp.modules.hr.service.SalaryPlanService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/duty")
public class DutyController extends BaseController {
	@Autowired
	private DutyService dutyService;
	@Autowired
	private SalaryPlanService salaryPlanService;

	/**
	 * 根据主键， 取得上报值班记录。
	 * 
	 * @param id
	 *            primary key
	 * @return DutyDetail entity
	 */
	@ModelAttribute
	public Duty get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的上报值班记录。否则创建一个新的上报值班对象。 */
		if (StringUtils.isNotBlank(id)) {
			return dutyService.get(id);
		} else {
			return new Duty();
		}

	}

	/**
	 * 查询用户的全部教育经历信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param dutyDetail
	 *            上报值班
	 * @param model
	 *            视图模型
	 * @return 用户的全部上报值班信息（不分页）
	 */
	@RequestMapping(value = { "list", "" })
	public String list(Duty  duty, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		 /* 方案编号 */
        String salaryPlanId = request.getParameter("salaryPlan.id");
        if (StringUtils.isNotBlank(salaryPlanId)) {
            request.getSession().setAttribute("salaryPlan.id", salaryPlanId);
        } else {
            SalaryPlan sPlan = new SalaryPlan();
            sPlan.setId(request.getSession().getAttribute("salaryPlan.id").toString());
            duty.setSalaryPlan(sPlan);
        }
		/* 通过service将duty信息保存至list中 */
		model.addAttribute("list", dutyService.findList(duty));
		/* 返回list页面 */
		return "modules/hr/dutyList";
	}

	/**
	 * 根据主键，查询到附件相关信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param Duty
	 *            附件相关信息entity，传递数据。
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(Duty  duty, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		 String salaryPlanId = request.getParameter("salaryPlan.id");
	        if (StringUtils.isNotBlank(salaryPlanId)) {
	            request.getSession().setAttribute("salaryPlan.id", salaryPlanId);
	        } else {
	            SalaryPlan sPlan = new SalaryPlan();
	            sPlan.setId(request.getSession().getAttribute("salaryPlan.id").toString());
	            duty.setSalaryPlan(sPlan);
	        }
		model.addAttribute("duty", duty);
		return "modules/hr/dutyForm";
	}

	/**
	 * 删除教育经历记录。操作成功后，转至列表页面。
	 * 
	 * @param duty
	 *            教育经历entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequestMapping(value = "delete")
	public String delete(Duty duty, RedirectAttributes redirectAttributes) {
		/* 调用delete删除当前duty值 */
		dutyService.delete(duty);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/duty/list?repage";
	}

	
	/**
	 * 添加、更新上报工作量记录。操作成功后，转至列表页面。
	 * 
	 * @param reportedWorkloade, Model model) {
		return null;
	 *            实践经验entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequestMapping(value = "save")
	public String save( Duty  duty, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		List<SalaryPlan> list = salaryPlanService.findList(new SalaryPlan());
		for (SalaryPlan sp : list) {
			if(sp.getPlanStatus().equals("1")){
				duty.setSalaryPlan(new SalaryPlan());
				duty.getSalaryPlan().setId(sp.getId());
			}
		}
		/*保存上报工作量信息*/
		dutyService.save(duty);
		/*重定向显示消息*/
		addMessage(redirectAttributes, "保存成功");
		/*重定向list页面*/
		return "redirect:" + adminPath + "/hr/duty/list?repage";
	}
	
	

}
