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
import cn.micromoving.bcp.modules.hr.entity.CertificateInformation;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.Honor;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;
import cn.micromoving.bcp.modules.hr.service.CertificateInformationService;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/cert")
public class CertificateInformationController extends BaseController {
	@Autowired
	private CertificateInformationService certificateInformationService;
	@Autowired
	private EmployeeService employeeService;


	@ModelAttribute
	public CertificateInformation get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return certificateInformationService.get(id);
		} else {
			return new CertificateInformation();
		}
	}
	@RequestMapping(value = "form1")
	public String form1(CertificateInformation certificateInformation, Model model,HttpServletRequest request) {
		model.addAttribute("certificateInformation", certificateInformation);
		return "modules/hr/certificateInformationForm1";

	}
	@RequestMapping(value = "form2")
	public String form2(CertificateInformation certificateInformation, Model model,HttpServletRequest request) {
		User user= UserUtils.getByLoginName(certificateInformation.getUser().getLoginName());
		// 通过user 的ID获取到对应的employee
		Employee employee = employeeService.get(user.getId());
		// 将user放到certificateInformation
		certificateInformation.setUser(user);
		// 将获取到的employee放到user中
		certificateInformation.setEmployee(employee);
		// 将certificateInformation对象放入到session中去
		request.getSession().setAttribute("certificateInformation", certificateInformation);
		return "modules/hr/certificateInformationForm2";

	}
	@RequestMapping(value = "form3")
	public String form3(CertificateInformation certificateInformation, Model model,HttpServletRequest request ) {
		// 获取到session中保存的honor
		CertificateInformation tempcertificateInformation = (CertificateInformation) request.getSession().getAttribute("certificateInformation");
				// 一下俩步其实就是相当于更新了honor 下的User与Employee
				certificateInformation.setUser(tempcertificateInformation.getUser());
				certificateInformation.setEmployee(tempcertificateInformation.getEmployee());
				// 将honor对象放入到session中去
				request.getSession().setAttribute("certificateInformation", certificateInformation);
		return "modules/hr/certificateInformationForm3";

	}

	@RequestMapping(value = { "list", "" })
	public String list(CertificateInformation certificateInformation, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<CertificateInformation> page =certificateInformationService.findCertificateInformation(new Page<CertificateInformation>(request, response), certificateInformation);
        /* page信息保存到对应的模型中 */
        model.addAttribute("page", page);
        /* 返回certificateInformationList页面 */
		return "modules/hr/certificateInformationList";
	}

	@RequestMapping(value = "save")
	public String save(CertificateInformation certificateInformation, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		// 获取到form2 中新添加的这个honor
		certificateInformation = (CertificateInformation) request.getSession().getAttribute("certificateInformation");
				// 将honor保存到数据库中
		certificateInformationService.save(certificateInformation);
				// 将honor从session中移除
				request.getSession().removeAttribute("certificateInformation");
				/* 重定向显示消息 */
				addMessage(redirectAttributes, "保存成功");
				/* 重定向list页面 */
		return "redirect:" + adminPath + "/hr/cert/list?repage";

	}

	@RequestMapping(value = "delete")
	public String delete(CertificateInformation certificateInformation, RedirectAttributes redirectAttributes) {
		certificateInformationService.delete(certificateInformation);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/hr/cert/list?repage";
	}

}
