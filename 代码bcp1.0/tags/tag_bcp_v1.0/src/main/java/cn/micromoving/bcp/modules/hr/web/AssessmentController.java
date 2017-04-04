package cn.micromoving.bcp.modules.hr.web;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.micromoving.bcp.modules.hr.entity.Assessment;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.service.AssessmentService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.hr.utils.HrUtils;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 区域Controller
 * 
 * @author mujinyao
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/assessment")
public class AssessmentController extends BaseController {

	@Autowired
	private AssessmentService assessmentService;// 一个对象
	private User user;

	/**
	 * 根据主键，取得历年考核
	 * 
	 * @param id
	 *            primary key
	 * @return assessment entity
	 */
	@ModelAttribute
	public Assessment get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的教育经历记录。否则创建一个新的教育经历对象。 */
		if (StringUtils.isNotBlank(id)) {
			return assessmentService.get(id);
		} else {
			return new Assessment();
		}
	}

	/**
	 * 根据主键，查询历年考核信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param assessment
	 *            历年考核entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return
	 */
	@RequiresPermissions("hr:assessment:update")
	@RequestMapping(value = "form")
	public String form(Assessment assessment, Model model,
			HttpServletRequest request) {
		/* 获取历年考核信息绑定到model中 */
		if (null == assessment.getAssessmentYear()) {
			int year = GregorianCalendar.getInstance().get(Calendar.YEAR) - 1;
			assessment.setAssessmentYear(String.valueOf(year));
		}
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		/* 获取当前用户 */
		assessment.setUser(user);
		model.addAttribute("assessment", assessment);
		/* 返回到assessmentform页面 */
		return "modules/hr/assessmentForm";
	}

	/**
	 * 查询用户的全部历年考核信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param assessment
	 *            历年考核
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部历年考核信息（不分页）
	 */
	@RequiresPermissions("hr:assessment:search")
	@RequestMapping(value = { "list", " " })
	public String list(Assessment assessment, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		/* 获取当前用户assessment信息 */
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);

		model.addAttribute("user", user);
		model.addAttribute("employee", emp);

		assessment.setUser(user);
		/* 获取历年考核信息以list方式显示放入model中 */
		model.addAttribute("list", assessmentService.findList(assessment));
		/* 返回assessmentlist页面 */
		return "modules/hr/assessmentList";
	}

	/**
	 * 添加、更新历年考核纪录。操作成功后，转至列表页面.
	 * 
	 * @param assessment
	 *            历年考核entity
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions({ "hr:assessment:create", "hr:assessment:update" })
	@RequestMapping(value = "save")
	public String save(Assessment assessment, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		/* 获取当前用户 */
		assessment.setUser(user);
		/* 判断当前用户编号和年份是否具备编辑条件 */
		if (org.apache.commons.lang3.StringUtils.isEmpty(assessment.getId())
				&& assessmentService.existAssessment(assessment)) {
			addMessage(model, "同一年份不可重复考核");
			return "modules/hr/assessmentForm";
		}
		/* 考核原因 ： NoCheckrise 、考核等级 ： assessmentLevel */
		String assLevel = assessment.getAssessmentLevel();
		String noCheckrise = assessment.getNoCheckrise();
		if (StringUtils.isNotBlank(noCheckrise) && StringUtils.isNotBlank(assLevel)) {
			errorMessages.add(0, "数据验证失败：");
			errorMessages.add("考核等级与未考核原因选填一项");
			addMessage(model);
			errorMessages.removeAll(errorMessages);
			return "modules/hr/assessmentForm";
		} else if(StringUtils.isBlank(noCheckrise) && StringUtils.isBlank(assLevel)) {
			errorMessages.add(0, "数据验证失败：");
			errorMessages.add("考核等级与未考核原因必须选填一项");
			addMessage(model);
			errorMessages.removeAll(errorMessages);
			return "modules/hr/assessmentForm";
		}
		/* 保存历年考核信息 */
		assessmentService.save(assessment);
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "提交成功");
		/* 重定向list页面 */
		return "redirect:" + adminPath + "/hr/assessment/list?repage";
	}

	/**
	 * 删除历年考核纪录。操作成功后，转至列表页面。
	 * 
	 * @param assessment
	 *            历年考核entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("hr:assessment:delete")
	@RequestMapping(value = "delete")
	public String delete(Assessment assessment,
			RedirectAttributes redirectAttributes) {
		/* 删除当前历年考核信息 */
		assessmentService.delete(assessment);
		/* 重定向显示提示信息 */
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/hr/assessment/list?repage";
	}

}
