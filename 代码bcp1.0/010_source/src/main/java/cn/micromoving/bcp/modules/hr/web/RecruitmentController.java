package cn.micromoving.bcp.modules.hr.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

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

import com.google.common.collect.Lists;

import cn.micromoving.bcp.common.mapper.JsonMapper;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.Post;
import cn.micromoving.bcp.modules.hr.entity.Recruitment;
import cn.micromoving.bcp.modules.hr.service.PostService;
import cn.micromoving.bcp.modules.hr.service.RecruitmentService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.sys.entity.Dict;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.DictUtils;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/recruitment")
public class RecruitmentController extends BaseController {
	@Autowired
	private RecruitmentService recruitmentService;

	@Autowired
	private PostService postService;
	/**
	 * 根据主键， 取得岗位聘任管理记录。
	 * 
	 * @param id
	 *            primary key
	 * @return Recruitment entity
	 */
	@ModelAttribute
	public Recruitment get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的岗位履历记录。否则创建一个新的岗位履历对象。 */
		if (StringUtils.isNotBlank(id)) {
			return recruitmentService.get(id);
		} else {
			return new Recruitment();
		}

	}

	/**
	 * 添加、更新岗位履历记录。操作成功后，转至列表页面。
	 * 
	 * @param recruitment
	 *            岗位履历entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions({"hr:recruitment:create","hr:recruitment:update"})
	@RequestMapping(value = "save")
	public String save(Recruitment recruitment, HttpServletRequest request,
			Model model, RedirectAttributes redirectAttributes) {
		request.getSession().getAttribute("employee.id");
		/* 在model中取得recruitment的相关值，进行服务端参数有效性验证。 */
		if (!beanValidator(model, recruitment)) {
			return form(recruitment, model,request);
		}
		/*
		 * 判断时间是否合法
		 * 1：只和今天的日期比较；2：只比较俩个日期的先后顺序;0：既比较日期的先后顺序，又与今天的日期作比较。验证成功：返回true
		 * ；严重失败：将错误信息添加到 message 中
		 */
		if (!dateValidator(model, "2", recruitment.getStartDate(),
				recruitment.getEndDate())) {
			return "modules/hr/recruitmentForm";
		}
		/* 在model中获取起始日期和终止日期，进行服务端参数有效性验证（既比较日期的先后顺序，又与今天的日期作比较） */

		if (!dateValidator(model, "2", recruitment.getStartDate(),
				recruitment.getEndDate())) {
			return "modules/hr/recruitmentForm";
		}
		/* 如果recruitment中获取的终止日期为空，则新建date类型的对象a，将终止日期获取为当前日期 */
		if (recruitment.getEndDate() == null) {
			Date a = new Date();
			recruitment.setEndDate(a);
		}
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		/* 获取当前用户recruitment信息 */
		recruitment.setUser(user);
		/* 调用save保存获取到的recruitment值 */
		if(recruitmentService.date(recruitment)){
			recruitmentService.save(recruitment);
			/* 添加Flash信息 */
			addMessage(redirectAttributes, "保存成功");
		}else{
			errorMessages.add(0, "数据验证失败：");
			errorMessages.add("聘任起止日期与记录有交集！");
			addMessage(model);
			errorMessages.removeAll(errorMessages);
			return "modules/hr/recruitmentForm";
		}
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/recruitment/list?repage";
	}

	/**
	 * 删除岗位履历记录。操作成功后，转至列表页面。
	 * 
	 * @param recruitment
	 *            岗位履历entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("hr:recruitment:delete")
	@RequestMapping(value = "delete")
	public String delete(Recruitment recruitment,
			RedirectAttributes redirectAttributes) {
		/* 调用delete删除当前recruitment值 */
		recruitmentService.delete(recruitment);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/recruitment/list?repage";
	}

	/**
	 * 根据主键，查询到岗位履历信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param edu
	 *            岗位履历entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回岗位履历编辑页面
	 */
	@RequiresPermissions("hr:recruitment:update")
	@RequestMapping(value = "form")
	public String form(Recruitment recruitment, Model model,
			HttpServletRequest request) {
		/* 将传入的edu值存入model中，返回form页面 */
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		/* 获取当前用户 */
		recruitment.setUser(user);
		recruitment.setEmployee(emp);
		model.addAttribute("recruitment", recruitment);
		return "modules/hr/recruitmentForm";

	}

	/**
	 * 查询用户的全部岗位履历信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param recruitment
	 *            岗位履历
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部岗位履历信息（不分页）
	 */
	@RequiresPermissions("hr:recruitment:search")
	@RequestMapping(value = { "list", "" })
	public String list(Recruitment recruitment, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		model.addAttribute("user", user);
		model.addAttribute("employee", emp);
		/* 获取当前用户recruitment */
		recruitment.setUser(user);
		/* 通过service将recruitment信息保存至list中 */
		model.addAttribute("list", recruitmentService.findList(recruitment));
		/* 返回list页面 */
		return "modules/hr/recruitmentList";
	}
	
	@RequestMapping(value = "subList")
    public void getSubList(HttpServletResponse response, HttpServletRequest request) {

		String mainSelect=request.getParameter("mainSelect");
		
	    List<Dict> list = Lists.newArrayList();
	    Post p = new Post();
	    p.setOffice(UserUtils.getOfficeById(mainSelect));
	    List<Post> postNameList = postService.findPostNameList(p);
	    
	    for (Post temp : postNameList) {
			String dictValue = temp.getPostName();
			String dictLabel = DictUtils.getDictLabel(dictValue, "post_name", "");
			Dict d = new Dict();
			d.setLabel(dictLabel);
			d.setValue(dictValue);
			list.add(d);
		}
        try {
            PrintWriter pw = response.getWriter();
            pw.write(JsonMapper.toJsonString(list));
        } catch (IOException ioe) {

        }
    }

}
