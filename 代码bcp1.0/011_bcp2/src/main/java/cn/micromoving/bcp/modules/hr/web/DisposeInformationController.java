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
import cn.micromoving.bcp.modules.hr.entity.DisposeInformation;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.Honor;
import cn.micromoving.bcp.modules.hr.service.DisposeInformationService;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/disposeInformation")
public class DisposeInformationController extends BaseController {
	@Autowired
	private DisposeInformationService disposeInformationService;

	@Autowired
	private EmployeeService employeeService;


	@ModelAttribute
	public DisposeInformation get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return disposeInformationService.get(id);
		} else {
			return new DisposeInformation();
		}
	}
	@RequestMapping(value = "form")
	public String form(DisposeInformation disposeInformation, Model model) {
		
		return "modules/hr/disposeInformationForm1";

	}
	@RequestMapping(value = "form1")
	public String form1(DisposeInformation disposeInformation, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// 通过loginName 获取到对应的user
				User user = UserUtils.getByLoginName(disposeInformation.getUser().getLoginName());
				// 通过user 的ID获取到对应的employee
				Employee employee = employeeService.get(user.getId());
				// 将获取到的employee放到user中
				disposeInformation.setEmployee(employee);
				// 将user放到honor
				disposeInformation.setUser(user);
				// 将honor对象放入到session中去
				request.getSession().setAttribute("disposeInformation", disposeInformation);
		return "modules/hr/disposeInformationForm2";

	}
	@RequestMapping(value = "form2")
	public String form2(DisposeInformation disposeInformation, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// 获取到session中保存的honor
		DisposeInformation tempDisposeInformation = (DisposeInformation) request.getSession().getAttribute("disposeInformation");
		// 一下俩步其实就是相当于更新了honor 下的User与Employee
		disposeInformation.setUser(tempDisposeInformation.getUser());
		disposeInformation.setEmployee(tempDisposeInformation.getEmployee());
		// 将honor对象放入到session中去
		request.getSession().setAttribute("disposeInformation", disposeInformation);
		return "modules/hr/disposeInformationForm3";
	}
	@RequestMapping(value = { "list", "" })
	public String list(DisposeInformation disposeInformation, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return "modules/hr/disposeInformationList";
	}
	@RequestMapping(value = "save")
	public String save(DisposeInformation disposeInformation, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// 获取到form2 中新添加的这个honor
		disposeInformation = (DisposeInformation) request.getSession().getAttribute("disposeInformation");
		
		// 将honor保存到数据库中
		disposeInformationService.save(disposeInformation);
		// 将honor从session中移除
		request.getSession().removeAttribute("disposeInformation");
		return "redirect:" + adminPath + "/hr/disposeInformation/list?repage";
	}
	

	@RequestMapping(value = "delete")
	public String delete(DisposeInformation disposeInformation, RedirectAttributes redirectAttributes) {
		return "redirect:" + adminPath + "/hr/disposeInformation/list?repage";
	}

}
