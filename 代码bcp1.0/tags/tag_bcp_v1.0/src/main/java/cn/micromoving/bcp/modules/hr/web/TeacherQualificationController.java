package cn.micromoving.bcp.modules.hr.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import cn.micromoving.bcp.modules.hr.dao.TeacherQualificationDao;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;
import cn.micromoving.bcp.modules.hr.service.TeacherQualificationService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.hr.utils.HrUtils;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/teacherQualification")
public class TeacherQualificationController extends BaseController {
	@Autowired
	private TeacherQualificationService teacherQualificationService;

	/**
	 * 根据主键， 取得认定资格。
	 * 
	 * @param id
	 *            primary key
	 * @return title entity
	 */
	@ModelAttribute
	public TeacherQualification get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的校内职务。否则创建一个新的校内职务对象。 */
		if (StringUtils.isNotBlank(id)) {

			return teacherQualificationService.get(id);
		} else {
			return new TeacherQualification();
		}
	}

	/**
	 * 根据主键，查询到教师资格认定信息，将此信息绑定到model中 ，在JSP页面中可以读取。
	 * 
	 * @param TeacherQualification
	 *            entity，传递数据。
	 * @param model
	 * @return
	 */
	@RequiresPermissions("hr:teacherQualification:update")
	@RequestMapping(value = "form")
	public String form(TeacherQualification teacherQualification, Model model,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
        /*根据用户ID查询教师资格认定记录*/
		TeacherQualification data = teacherQualificationService.getId(user.getId());
		if(data!=null){
		    teacherQualification = data;
		}
		/* 获取当前用户 */
		teacherQualification.setUser(user);
        teacherQualification.setEmployee(emp);
//		teacherQualification.setUser(UserUtils.getUser());
		/* 获取校内职务信息绑定到model中 */
		model.addAttribute("teacherQualification", teacherQualification);
		/* 返回form页面 */
		return "modules/hr/teacherQualificationForm";

	}

	/**
	 * 添加、更新实践经验记录。操作成功后，转至列表页面。
	 * 
	 * @param teacherQualification
	 *            , Model model) { return null; 资格认定entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions({"hr:teacherQualification:create","hr:teacherQualification:update"})
	@RequestMapping(value = "save")
	public String save(TeacherQualification teacherQualification, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		/* 获取当前用户 */
		teacherQualification.setUser(user);
		teacherQualification.setEmployee(emp);
		/* 判断数据是否符合服务器端约束规则 */
		if (!beanValidator(model, teacherQualification)) {
			return form(teacherQualification, model,request);
		}
		/* 审核时间更新 */
		teacherQualification.setAuditingDate(new Date());

		/* 时间先后验证 */
		if (!dateValidator(model, "0", teacherQualification.getGainDate(),
				teacherQualification.getAuditingDate())) {
			return form(teacherQualification, model,request);
		}

		/* 获取当前用户 */
//		teacherQualification.setCurrentUser(UserUtils.getUser());
		/* 保存实践经验信息 */
		teacherQualificationService.save(teacherQualification);
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "保存成功");
		/* 重定向form页面 */
		return "modules/hr/teacherQualificationForm";
	}

	/**
	 * 查询用户的全部教育经历信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param teacherQualification
	 *            教育经历
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部教育经历信息（不分页）
	 */
	@RequiresPermissions("hr:teacherQualification:search")
	@RequestMapping(value = { "list", "" })
	public String list(TeacherQualification teacherQualification,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		/* 获取当前用户teacherQualification */
		teacherQualification.setUser(UserUtils.getUser());
		/* 通过service将teacherQualification信息保存至list中 */
		model.addAttribute("list",
				teacherQualificationService.findList(teacherQualification));
		/* 返回list页面 */
		return "modules/hr/teacherQualificationForm";
	}

}
