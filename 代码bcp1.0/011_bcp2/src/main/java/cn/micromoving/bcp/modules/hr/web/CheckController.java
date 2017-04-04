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
import cn.micromoving.bcp.modules.hr.entity.Check;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;
import cn.micromoving.bcp.modules.hr.service.CheckService;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/check")
public class CheckController extends BaseController {
	@Autowired
	private CheckService checkService;
	@Autowired
	private EmployeeService employeeService;

	/**
	 * 根据主键， 取得考核信息记录。
	 * 
	 * @param id
	 *            primary key
	 * @return CheckDetail entity
	 */
	@ModelAttribute
	public Check get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的考核信息记录。否则创建一个新的考核信息对象。 */
		if (StringUtils.isNotBlank(id)) {
			return checkService.get(id);
		} else {
			return new Check();
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
	public String list(Check check, Model model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		Page<Check> page = checkService.findCheck(new Page<Check>(request,
				response), check);
		/* page信息保存到对应的模型中 */
		model.addAttribute("page", page);

		/* 返回list页面 */
		return "modules/hr/checkList";
	}

	@RequestMapping(value = "form")
	public String form(Check check, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("check", check);
		return "modules/hr/checkForm1";
	}

	@RequestMapping(value = "form1")
	public String form1(Check check, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// 通过loginName 获取到对应的user
		User user = UserUtils.getByLoginName(check.getUser().getLoginName());
		// 通过user 的ID获取到对应的employee
		Employee employee = employeeService.get(user.getId());
		// 将获取到的employee放到user中
		check.setEmployee(employee);
		// 将user放到honor
		check.setUser(user);
		// 将honor对象放入到session中去
		request.getSession().setAttribute("check", check);
		model.addAttribute("check", check);
		return "modules/hr/checkForm2";
	}

	@RequestMapping(value = "form2")
	public String form2(Check check, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// 获取到session中保存的honor
		Check tempCheck = (Check) request.getSession().getAttribute("check");
		// 一下俩步其实就是相当于更新了honor 下的User与Employee
		check.setUser(tempCheck.getUser());
		check.setEmployee(tempCheck.getEmployee());
		// 将honor对象放入到session中去
		request.getSession().setAttribute("check", check);
		return "modules/hr/checkForm3";
	}
	@RequestMapping(value = "delete")
	public String delete(Check check,
			RedirectAttributes redirectAttributes) {
		/* 调用delete删除当前teacherQualification值 */
		checkService.delete(check);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/check/list?repage";
	}

	@RequestMapping(value = "save")
	public String save(Check check, Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		// 获取到form2 中新添加的这个honor
		check = (Check) request.getSession().getAttribute("check");
		// 将honor保存到数据库中
		checkService.save(check);
		// 将honor从session中移除
		request.getSession().removeAttribute("check");
		return "redirect:" + adminPath + "/hr/check/list?repage";
	}

	
}
