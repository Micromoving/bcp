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

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.AnnualAppraisal;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.service.AnnualAppraisalService;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/hr/annualAppraisal")
public class AnnualAppraisalController extends BaseController {
	 @Autowired
    private AnnualAppraisalService annualAppraisalService;
	 
	 @Autowired
		private EmployeeService employeeService;
	 
	 
	 
	 	/**
		 * 根据主键， 取得考核信息记录。
		 * 
		 * @param id
		 *            primary key
		 * @return AnnualAppraisalDetail entity
		 */
	 @ModelAttribute
		public AnnualAppraisal get(@RequestParam(required = false) String id) {
			/* 判断id是否为空，如果有值，调用service来取得id对应的考核信息记录。否则创建一个新的考核信息对象。 */
			if (StringUtils.isNotBlank(id)) {
				return annualAppraisalService.get(id);
			} else {
				return new AnnualAppraisal();
			}

		}
	    /**
		 * 查询用户的全部考核信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
		 * 
		 * @param check
		 *            考核信息
		 * @param model
		 *            视图模型
		 * @return 用户的全部考核信息（不分页）
		 */
		@RequestMapping(value = { "list", "" })
		public String list(AnnualAppraisal  annualAppraisal, Model model,
				HttpServletRequest request,HttpServletResponse response, 
				RedirectAttributes redirectAttributes) {
			Page<AnnualAppraisal> page = annualAppraisalService
					.findAnnualAppraisal(new Page<AnnualAppraisal>(request, response), annualAppraisal);
		        /* page信息保存到对应的模型中 */
		        model.addAttribute("page", page);
		        /* 返回employList页面 */
			/* 返回list页面 */
			return "modules/hr/annualAppraisalList";
		}
		
		
		/**
		 * 根据主键，查询到考核信息，将此信息绑定到model中，在JSP页面中可以读取。
		 * 
		 * @param AnnualAppraisal
		 *            考核相关信息entity，传递数据。
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "form")
		public String form(AnnualAppraisal  annualAppraisal, Model model,
				HttpServletRequest request, RedirectAttributes redirectAttributes) {
			/*获取当前用户*/
			//annualAppraisal.setUser(UserUtils.getUser());
			model.addAttribute("annualAppraisal", annualAppraisal);
			return "modules/hr/annualAppraisalForm";
		}
		
		
		@RequestMapping(value = "form1")
		public String form1(AnnualAppraisal annualAppraisal,Model model,
				HttpServletRequest request,RedirectAttributes redirectAttributes){
			/*获取当前用户*/
			//annualAppraisal.setUser(UserUtils.getUser());
			model.addAttribute("annualAppraisal", annualAppraisal);
			return "modules/hr/annualAppraisalForm1";
		}
		
		@RequestMapping(value = "form2")
		public String form2(AnnualAppraisal annualAppraisal,Model model,
				HttpServletRequest request,RedirectAttributes redirectAttributes){
			//通过loginName获取到对应的user
			User user= UserUtils.getByLoginName(annualAppraisal.getUser().getLoginName());
			//通过user的ID获取到对应的employee
			Employee employee = employeeService.get(user.getId());
			//将user放到annualAppraisal
			annualAppraisal.setUser(user);
			//将获取到的employee放到user中
			annualAppraisal.setEmployee(employee);
			//将annualAppraisal对象放到session中去
			request.getSession().setAttribute("annualAppraisal", annualAppraisal);
			return "modules/hr/annualAppraisalForm2";
		}
		
		
		@RequestMapping(value = "form3")
		public String form3(AnnualAppraisal annualAppraisal,Model model,
				HttpServletRequest request,RedirectAttributes redirectAttributes){
			//获取到session中保存的honor
			AnnualAppraisal annualAppraisalTemp= (AnnualAppraisal) request.getSession().getAttribute("annualAppraisal");
			//一下两步就相当于更新了honor下的user和Empolyee
			annualAppraisal.setUser(annualAppraisalTemp.getUser());
			annualAppraisal.setEmployee(annualAppraisalTemp.getEmployee());
			//将honor对象放到session中去
			request.getSession().setAttribute("annualAppraisal", annualAppraisal);
			return "modules/hr/annualAppraisalForm3";
		}
		
		/**
		 * 删除考核记录。操作成功后，转至列表页面。
		 * 
		 * @param annualAppraisal
		 *            考核entity
		 * @param redirectAttributes
		 *            重定向属性集
		 * @return 操作完成后，重定向到列表页面。
		 */
		@RequestMapping(value = "delete")
		public String delete(AnnualAppraisal annualAppraisal, RedirectAttributes redirectAttributes) {
			
			/* 调用delete删除当前annualAppraisal值 */
			annualAppraisalService.delete(annualAppraisal);
			/* 添加Flash信息 */
			addMessage(redirectAttributes, "删除成功");
			/* 重定向至list页面 */
			return "redirect:" + adminPath + "/hr/annualAppraisal/list?repage";
		}
		
		/**
		 * 
		 * @param annualAppraisal 
		 *             考核信息
		 * @param model
		 *             视图模型
		 * @param request 
		 *             http请求
		 * @param redirectAttributes  
		 *             重定向属性集
		 * @return  
		 *        操作完成后，重定向到列表页面。
		 */
		@RequestMapping(value = "save")
		public String save(AnnualAppraisal annualAppraisal, Model model,
				HttpServletRequest request, RedirectAttributes redirectAttributes) {
			// 获取到form2 中新添加的这个honor
			annualAppraisal = (AnnualAppraisal) request.getSession().getAttribute("annualAppraisal");
			// 将honor保存到数据库中
			annualAppraisalService.save(annualAppraisal);
			// 将honor从session中移除
			request.getSession().removeAttribute("annualAppraisal");
			/* 重定向显示消息 */
			addMessage(redirectAttributes, "保存成功");
			/* 重定向list页面 */
			return "redirect:" + adminPath + "/hr/annualAppraisal/list?repage";
		}
}
