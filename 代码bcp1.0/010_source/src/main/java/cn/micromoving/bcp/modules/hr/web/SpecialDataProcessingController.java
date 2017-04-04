package cn.micromoving.bcp.modules.hr.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.SpecialDataProcessing;

@Controller
@RequestMapping(value = "${adminPath}/hr/specialDataProcessing")
public class SpecialDataProcessingController extends BaseController {


	@RequestMapping(value = { "list" })
	public String list(SpecialDataProcessing specialDataProcessing,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return "modules/hr/specialDataProcessingList";
	}

	/**
	 * 根据数据分类确定要转到那个页面
	 * 
	 * 1.请假人员 2.学历教育 3.培训 4.年薪制 5.辅导员带班数 6.非在编工资项
	 * 
	 * @param specialDataProcessing
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "form" })
	public String form(SpecialDataProcessing specialDataProcessing,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String dataClassification = specialDataProcessing
				.getDataClassification();
		if (dataClassification.equals("1")) {
			return "redirect:" + adminPath + "/hr/leavePersonnel/list?salaryInstanceId="+specialDataProcessing.getSalaryInstanceId();
		} else if (dataClassification.equals("2")) {
			return "redirect:" + adminPath + "/hr/degreeEdu/list";
		} else if (dataClassification.equals("3")) {
			return "redirect:" + adminPath + "/hr/trainExperience/list";
		} else if (dataClassification.equals("4")) {
			return "redirect:" + adminPath + "/hr/annualSalaryPersonnel/list";
		} else if (dataClassification.equals("5")) {
			return "redirect:" + adminPath + "/hr/classTeacherFee/list";
		} else if (dataClassification.equals("6")) {
			return "redirect:" + adminPath + "/hr/notSeriesSalaryItems/list";
		}
		return null;

	}

}
