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
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.Honor;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.hr.service.HonorService;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/honor")
public class HonorController extends BaseController {

	@Autowired
	private HonorService honorService;

	@Autowired
	private EmployeeService employeeService;

	@ModelAttribute
	public Honor get(@RequestParam(required = false) String id) {

		if (StringUtils.isNotBlank(id)) {
			return honorService.get(id);
		} else {
			return new Honor();
		}
	}

	@RequestMapping(value = { "list", "" })
	public String list(Honor honor, Model model, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		Page<Honor> page = honorService.findHonor(new Page<Honor>(request, response), honor);
		/* page信息保存到对应的模型中 */
		model.addAttribute("page", page);
		/* 返回employList页面 */
		return "modules/hr/honorList";
	}

	@RequestMapping(value = "form")
	public String form(Honor honor, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		System.out.println("form1");
		return "modules/hr/honorForm1";
	}

	@RequestMapping(value = "form1")
	public String form1(Honor honor, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// 通过loginName 获取到对应的user
		User user = UserUtils.getByLoginName(honor.getUser().getLoginName());
		// 通过user 的ID获取到对应的employee
		Employee employee = employeeService.get(user.getId());
		// 将获取到的employee放到user中
		honor.setEmployee(employee);
		// 将user放到honor
		honor.setUser(user);
		// 将honor对象放入到session中去
		request.getSession().setAttribute("honor", honor);
		return "modules/hr/honorForm2";
	}

	@RequestMapping(value = "form2")
	public String form2(Honor honor, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// 获取到session中保存的honor
		Honor tempHonor = (Honor) request.getSession().getAttribute("honor");
		// 一下俩步其实就是相当于更新了honor 下的User与Employee
		honor.setUser(tempHonor.getUser());
		honor.setEmployee(tempHonor.getEmployee());
		// 将honor对象放入到session中去
		request.getSession().setAttribute("honor", honor);
		return "modules/hr/honorForm3";
	}

	@RequestMapping(value = "save")
	public String save(Honor honor, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// 获取到form2 中新添加的这个honor
		honor = (Honor) request.getSession().getAttribute("honor");

		// 将honor保存到数据库中
		honorService.save(honor);
		// 将honor从session中移除
		request.getSession().removeAttribute("honor");
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/hr/honor/list?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(Honor honor, RedirectAttributes redirectAttributes) {
		/* 调用delete删除当前teacherQualification值 */
		honorService.delete(honor);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/honor/list?repage";
	}

}
