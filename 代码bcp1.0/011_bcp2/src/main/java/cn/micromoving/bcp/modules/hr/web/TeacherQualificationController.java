package cn.micromoving.bcp.modules.hr.web;

import java.util.Date;

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
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.hr.service.TeacherQualificationService;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/teacherQualification")
public class TeacherQualificationController extends BaseController {
	@Autowired
	private TeacherQualificationService teacherQualificationService;

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 根据主键， 取得教师资格信息记录。
	 * 
	 * @param id
	 *            primary key
	 * @return TeacherQualificationDetail entity
	 */
	@ModelAttribute
	public TeacherQualification get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的教师资格信息记录。否则创建一个新的教师资格信息对象。 */
		if (StringUtils.isNotBlank(id)) {
			return teacherQualificationService.get(id);
		} else {
			return new TeacherQualification();
		}

	}

	/**
	 * 查询用户的全部教师资格信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param teacherQualification
	 *            教师资格信息
	 * @param model
	 *            视图模型
	 * @return 用户的全部教师资格信息（不分页）
	 */
	@RequestMapping(value = { "list", "" })
	public String list(TeacherQualification teacherQualification, Model model,
			HttpServletResponse response, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		Page<TeacherQualification> page = teacherQualificationService
				.findTeacherQualification(new Page<TeacherQualification>(
						request, response), teacherQualification);
		/* page信息保存到对应的模型中 */
		model.addAttribute("page", page);
		/* 返回list页面 */
		return "modules/hr/teacherQualificationList";
	}

	/**
	 * 根据主键，查询到教师资格信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param TeacherQualification
	 *            教师资格相关信息entity，传递数据。
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(TeacherQualification teacherQualification, Model model,
			HttpServletRequest request) {
		/* 获取当前用户 */
		// teacherQualification.setUser(UserUtils.getUser());
		model.addAttribute("teacherQualification", teacherQualification);
		return "modules/hr/teacherQualificationForm";
	}

	@RequestMapping(value = "form1")
	public String form1(TeacherQualification teacherQualification, Model model,
			HttpServletRequest request) {
		model.addAttribute("teacherQualification", teacherQualification);
		return "modules/hr/teacherQualificationForm1";
	}

	@RequestMapping(value = "form2")
	public String form2(TeacherQualification teacherQualification, Model model,
			HttpServletRequest request) {
		// 通过loginName 获取到对应的user
		User user = UserUtils.getByLoginName(teacherQualification.getUser()
				.getLoginName());
		if(user.getLoginName()!=null&&user.getId()==null){
			addMessage(model, "工号不存在，请重新输入");
			return form2(teacherQualification, model, request);
		}
		// 通过user 的ID获取到对应的employee
		Employee employee = employeeService.get(user.getId());
		// 将user放到teacherQualification
		teacherQualification.setUser(user);
		// 将获取到的employee放到user中
		teacherQualification.setEmployee(employee);
		// 将teacherQualification对象放入到session中去
		request.getSession().setAttribute("teacherQualification",
				teacherQualification);
		return "modules/hr/teacherQualificationForm2";
	}

	@RequestMapping(value = "form3")
	public String form3(TeacherQualification teacherQualification, Model model,
			HttpServletRequest request) {
		/* 获取时间，与今天的日期作比较 */
		if (teacherQualification.getCertificateIssueDateString() != null
				&& teacherQualification.getCertificateIssueDate().after(
						new Date())) {
			addMessage(model, "证书颁发日期时间晚于现在");
			return form2(teacherQualification, model, request);
		}
		/* 获取时间，与今天的日期作比较 */
		if (teacherQualification.getAuditDateString() != null
				&& teacherQualification.getAuditDate().after(new Date())) {
			addMessage(model, "审核时间晚于现在");
			return form2(teacherQualification, model, request);
		}
		// 获取到session中保存的honor
		TeacherQualification tempTeacherQualification = (TeacherQualification) request
				.getSession().getAttribute("teacherQualification");
		// 一下俩步其实就是相当于更新了honor 下的User与Employee
		teacherQualification.setUser(tempTeacherQualification.getUser());
		teacherQualification
				.setEmployee(tempTeacherQualification.getEmployee());
		// 将honor对象放入到session中去
		request.getSession().setAttribute("teacherQualification",
				teacherQualification);
		return "modules/hr/teacherQualificationForm3";
	}

	/**
	 * 删除教师资格记录。操作成功后，转至列表页面。
	 * 
	 * @param teacherQualification
	 *            教师资格entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequestMapping(value = "delete")
	public String delete(TeacherQualification teacherQualification,
			RedirectAttributes redirectAttributes) {
		/* 调用delete删除当前teacherQualification值 */
		teacherQualificationService.delete(teacherQualification);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/teacherQualification/list?repage";
	}

	/**
	 * @param teacherQualification
	 *            教师资格证信息
	 * @param model
	 *            视图模型
	 * @param request
	 *            http请求
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequestMapping(value = "save")
	public String save(TeacherQualification teacherQualification, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// 获取到form2 中新添加的这个honor
		teacherQualification = (TeacherQualification) request.getSession()
				.getAttribute("teacherQualification");
		// 将honor保存到数据库中
		teacherQualificationService.save(teacherQualification);
		// 将honor从session中移除
		request.getSession().removeAttribute("teacherQualification");
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "保存成功");
		/* 重定向list页面 */
		return "redirect:" + adminPath + "/hr/teacherQualification/list?repage";
	}

}
