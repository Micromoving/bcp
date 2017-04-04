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
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.Work;
import cn.micromoving.bcp.modules.hr.service.WorkService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.sys.entity.User;

@Controller
@RequestMapping(value = "${adminPath}/hr/work")
public class WorkController extends BaseController {
	@Autowired
	private WorkService workService;

	/**
	 * 根据主键， 取得工作经验记录。
	 * 
	 * @param id
	 *            primary key
	 * @return Work entity
	 */
	@ModelAttribute
	public Work get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的工作经验记录。否则创建一个新的工作经验对象。 */
		if (StringUtils.isNotBlank(id)) {
			return workService.get(id);
		} else {
			return new Work();
		}
	}

	/**
	 * 根据主键，查询到工作经验信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param work
	 *            工作经验entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回工作经验编辑页面
	 */
	@RequiresPermissions("hr:work:update")
	@RequestMapping(value = "form")
	public String form(Work work, Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		/* 获取当前用户 */
		work.setUser(user);
		/* 将传入的work值存入model中 */
		model.addAttribute("work", work);
		/* 返回form页面 */
		return "modules/hr/workForm";
	}

	/**
	 * 添加、更新工作经验记录。操作成功后，转至列表页面。
	 * 
	 * @param work
	 *            工作经验entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions({"hr:work:create","hr:work:update"})
	@RequestMapping(value = "save")
	public String save(Work work, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		/* 在model中取得work的相关值，进行服务端参数有效性验证。 */
		if (!beanValidator(model, work)) {
			return form(work, model,request);
		}
		if(work.getStartDate() != null && work.getEndDate() != null){
			/* 在model中获取起始日期和终止日期，进行服务端参数有效性验证（既比较日期的先后顺序，又与今天的日期作比较） */
			if (!dateValidator(model, "0", work.getStartDate(), work.getEndDate())) {
				return form(work, model,request);
			}
		}
		
		/* 获取当前用户work信息 */
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		work.setUser(user);
		/* 调用save保存获取到的work值 */
		workService.save(work);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "保存成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/work/list?repage";
	}

	/**
	 * 删除教工作经验记录。操作成功后，转至列表页面。
	 * 
	 * @param work
	 *            工作经验entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("hr:work:delete")
	@RequestMapping(value = "delete")
	public String delete(Work work, RedirectAttributes redirectAttributes) {
		/* 调用delete删除当前work值 */
		workService.delete(work);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/work/list?repage";
	}

	/**
	 * 查询用户的全部工作经验信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param work
	 *            工作经验
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部工作经验信息（不分页）
	 */
	@RequiresPermissions("hr:work:search")
	@RequestMapping(value = { "list", "" })
	public String list(Work work, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		model.addAttribute("user", user);
		model.addAttribute("employee", emp);
		/* 获取当前用户work信息 */
		work.setUser(user);
		/* 通过service将work信息保存至list */
		model.addAttribute("list", workService.findList(work));
		/* 返回list页面 */
		return "modules/hr/workList";
	}
}
