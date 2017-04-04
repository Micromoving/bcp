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
import cn.micromoving.bcp.modules.hr.entity.EduExperience;
import cn.micromoving.bcp.modules.hr.service.EduExperienceService;

@Controller
@RequestMapping(value = "${adminPath}/hr/eduExperience")
public class EduExperienceController extends BaseController {
	@Autowired
	private EduExperienceService eduExperienceService;

	
	@ModelAttribute
	public EduExperience get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return eduExperienceService.get(id);
		} else {
			return new EduExperience();
		}
	}
	@RequestMapping(value = "form")
	public String form(EduExperience eduExperience, Model model) {
		return "modules/hr/eduExperienceForm";

	}

	@RequestMapping(value = { "list", "" })
	public String list(EduExperience eduExperience, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return "modules/hr/eduExperienceList";
	}

	@RequestMapping(value = "save")
	public String save(EduExperience eduExperience, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		return "redirect:" + adminPath + "/hr/eduExperience/list?repage";

	}

	@RequestMapping(value = "delete")
	public String delete(EduExperience eduExperience, RedirectAttributes redirectAttributes) {
		return "redirect:" + adminPath + "/hr/eduExperience/list?repage";
	}

}
