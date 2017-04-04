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
import cn.micromoving.bcp.modules.hr.entity.Honor;
import cn.micromoving.bcp.modules.hr.entity.PostAppointment;
import cn.micromoving.bcp.modules.hr.service.PostAppointmentService;

@Controller
@RequestMapping(value = "${adminPath}/hr/postAppointment")
public class PostAppointmentController extends BaseController {
	@Autowired
	private PostAppointmentService postAppointmentService;

	@ModelAttribute
	public PostAppointment get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return postAppointmentService.get(id);
		} else {
			return new PostAppointment();
		}
	}

	@RequestMapping(value = "form")
	public String form(PostAppointment postAppointment, Model model) {
		return "modules/hr/postAppointmentForm";

	}

	@RequestMapping(value = { "list", "" })
	public String list(PostAppointment postAppointment, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		Page<PostAppointment> page = postAppointmentService.findPostAppointment(new Page<PostAppointment>(request, response), postAppointment);
		/* page信息保存到对应的模型中 */
		model.addAttribute("page", page);

		return "modules/hr/postAppointmentList";
	}

	@RequestMapping(value = "save")
	public String save(PostAppointment postAppointment, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		return "redirect:" + adminPath + "/hr/postAppointment/list?repage";

	}

	@RequestMapping(value = "delete")
	public String delete(PostAppointment postAppointment, RedirectAttributes redirectAttributes) {
		/* 调用delete删除当前languageAbility值 */
		postAppointmentService.delete(postAppointment);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/postAppointment/list?repage";
	}

}
