package cn.micromoving.bcp.modules.hr.web;

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
import cn.micromoving.bcp.modules.hr.entity.Edu;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.service.EduService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.sys.entity.User;



@Controller
@RequestMapping(value = "${adminPath}/hr/edu")
public class EduController extends BaseController {
	@Autowired
	private EduService eduService;

	/**
	 * 根据主键， 取得教育经历记录。
	 * 
	 * @param id
	 *            primary key
	 * @return Edu entity
	 */
	@ModelAttribute
	public Edu get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的教育经历记录。否则创建一个新的教育经历对象。 */
		if (StringUtils.isNotBlank(id)) {
			return eduService.get(id);
		} else {
			return new Edu();
		}

	}

	/**
	 * 根据主键，查询到教育经历信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param edu
	 *            教育经历entity，传递数据。
	 * @param model 视图模型
	 * @return 返回教育经历编辑页面
	 */
	@RequiresPermissions("hr:edu:update")
	@RequestMapping(value = "form")
	public String form(Edu edu, Model model,HttpServletRequest request) {
		/*将传入的edu值存入model中，返回form页面*/
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		/* 获取当前用户 */
		edu.setUser(user);
		edu.setEmployee(emp);
		model.addAttribute("edu", edu);
		return "modules/hr/eduForm";

	}

	/**
	 * 添加、更新教育经历记录。操作成功后，转至列表页面。
	 * 
	 * @param edu
	 *            教育经历entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions({"hr:edu:create","hr:edu:update"})
	@RequestMapping(value = "save")
	public String save(Edu edu, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		
		/*在model中取得edu的相关值，进行服务端参数有效性验证。*/
		if (!beanValidator(model, edu)) {
			return form(edu, model,request);
		}
		/*如果edu中获取的终止日期为空，则新建date类型的对象a，将终止日期获取为当前日期*/
		if(edu.getEndDate()  == null)  {
			Date a = new Date();
			edu.setEndDate(a);
		}
		/*在model中获取起始日期和终止日期，进行服务端参数有效性验证（既比较日期的先后顺序，又与今天的日期作比较）*/
		if(!dateValidator(model,"0",edu.getStartDate(),edu.getEndDate())) {
			return form(edu,model,request);
		}
		/*获取学历取得时间，与今天的日期作比较*/	
		if(edu.getEduBackgroundDate()!=null&&edu.getEduBackgroundDate().after(new Date())){
			addMessage(model, "学历取得时间晚于现在");
			return form(edu,model,request);
		}
		/*获取学位取得时间，与今天的日期作比较*/
		if(edu.getGetDegreeDate()!=null&&edu.getGetDegreeDate().after(new Date())){
			addMessage(model, "学位取得时间晚于现在");
			return form(edu,model,request);
		}
		
		/*获取当前用户edu信息*/
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		edu.setUser(user);
		/*调用save保存获取到的edu值*/
		eduService.save(edu);
		/*添加Flash信息*/
		addMessage(redirectAttributes, "保存成功");
		/*重定向至list页面*/
		return "redirect:" + adminPath + "/hr/edu/list?repage";
	}

	/**
	 * 删除教育经历记录。操作成功后，转至列表页面。
	 * 
	 * @param edu
	 *            教育经历entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("hr:edu:delete")
	@RequestMapping(value = "delete")
	public String delete(Edu edu, RedirectAttributes redirectAttributes) {
		/*调用delete删除当前edu值*/
		eduService.delete(edu);
		/*添加Flash信息*/
		addMessage(redirectAttributes, "删除成功");
		/*重定向至list页面*/
		return "redirect:" + adminPath + "/hr/edu/list?repage";
	}

	/**
	 * 查询用户的全部教育经历信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * @param edu 教育经历
	 * @param request http请求
	 * @param response http 响应
	 * @param model  视图模型
	 * @return  用户的全部教育经历信息（不分页）
	 */
	@RequiresPermissions("hr:edu:search")
	@RequestMapping(value = { "list", "" })
	public String list(Edu edu, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		model.addAttribute("user", user);
		model.addAttribute("employee", emp);
		/*获取当前用户edu*/
		edu.setUser(user);
		/*通过service将edu信息保存至list中*/
		model.addAttribute("list", eduService.findList(edu));
		/*返回list页面*/
		return "modules/hr/eduList";
	}

}
