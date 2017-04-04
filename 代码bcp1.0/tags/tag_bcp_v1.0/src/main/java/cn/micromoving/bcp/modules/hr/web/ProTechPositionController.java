/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.modules.hr.web;

import java.io.IOException;
import java.io.PrintWriter;
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
import cn.micromoving.bcp.modules.hr.entity.ProTechPosition;
import cn.micromoving.bcp.modules.hr.service.ProTechPositionService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.sys.entity.Dict;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.DictUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/proTechPosition")
public class ProTechPositionController extends BaseController {
	@Autowired
	private ProTechPositionService proTechPositionService;
	@Autowired
	private SystemService systemService;

	/**
	 * 根据主键，取得用户职称情况
	 * 
	 * @param id
	 *            primary key
	 * @return Attachment entity
	 */
	@ModelAttribute
	public ProTechPosition get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的附件。否则创建一个新的附件。 */
		if (StringUtils.isNotBlank(id)) {
			return proTechPositionService.get(id);
		} else {
			return new ProTechPosition();
		}

	}

	/**
	 * 根据主键，查询到职称情况，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param protechposition
	 *            职称情况entity，传递数据。
	 * @param model
	 * @return
	 */
	@RequiresPermissions("hr:proTechPosition:update")
	@RequestMapping(value = "form")
	public String form(ProTechPosition proTechPosition, Model model,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		/* 获取当前用户 */
		proTechPosition.setUser(user);
		proTechPosition.setEmployee(emp);
		model.addAttribute("proTechPosition", proTechPosition);
		return "modules/hr/proTechPositionForm";
	}

	/**
	 * 添加、更新职称情况记录。操作成功后，转至列表页面。
	 * 
	 * @param protechposition
	 *            , Model model) { return null; 职称情况entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions({"hr:proTechPosition:create","hr:proTechPosition:update"})
	@RequestMapping(value = "save")
	public String save(ProTechPosition proTechPosition, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		proTechPosition.setUser(user);
		/* 判断当前用户编号和专业技术职务是否具备编辑条件 */
		if (org.apache.commons.lang3.StringUtils.isEmpty(proTechPosition
				.getId())
				&& proTechPositionService.existProTechPosition(proTechPosition)) {
			errorMessages.add(0, "数据验证失败：");
			errorMessages.add("同一专业技术职务不可重复提交");
			addMessage(model);
			errorMessages.removeAll(errorMessages);
			return form(proTechPosition, model,request);

		}
		//!dateValidator(model, "0",proTechPosition.getGainDate(), proTechPosition.getAppointDate())
		/* 在model中获取起始日期和终止日期，进行服务端参数有效性验证（既比较日期的先后顺序，又与今天的日期作比较） */
		if (timeCompare(proTechPosition.getGainDate(), ">", proTechPosition.getAppointDate())) {
			errorMessages.add(0, "数据验证失败：");
			errorMessages.add("获取职称时间应在聘任时间之前");
			addMessage(model);
			errorMessages.removeAll(errorMessages);
			return form(proTechPosition, model,request);
		}

		/* 保存职称情况信息 */
		proTechPositionService.save(proTechPosition);
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "保存成功");
		/* 重定向list页面 */
		return "redirect:" + adminPath + "/hr/proTechPosition/list?repage";
	}

	/**
	 * 删除记录。操作成功后，转至列表页面。
	 * 
	 * @param protechposition
	 *            职称情况entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("hr:proTechPosition:delete")
	@RequestMapping(value = "delete")
	public String delete(ProTechPosition proTechPosition,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		/* 调用delete删除当前proTechPosition值 */
		proTechPositionService.delete(proTechPosition);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/hr/proTechPosition/list?repage";
	}

	/**
	 * 查询用户的全部职称情况信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param protechposition
	 *            职称
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部职称情况信息（不分页）
	 */
	@RequiresPermissions("hr:proTechPosition:search")
	@RequestMapping(value = { "list", "" })
	public String list(ProTechPosition proTechPosition,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		/* 获取当前用户proTechPosition信息 */
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		model.addAttribute("user", user);
		model.addAttribute("employee", emp);
		proTechPosition.setUser(user);
		List<ProTechPosition> list = proTechPositionService.findList(proTechPosition);
		/* 通过service将proTechPosition信息保存至list */
		model.addAttribute("list",list);
		/* 返回list页面 */
		return "modules/hr/proTechPositionList";
	}
	  @RequestMapping(value = "subList")
	    public void getSubList(HttpServletResponse response, HttpServletRequest request) {

	        String mainSelect=request.getParameter("mainSelect");
	        String[] select = mainSelect.split(",");
	       
	        List<Dict> list = DictUtils.getSubDictList("tech_position_type", select[0]);
	        List<Dict> list2 = Lists.newArrayList();
	        for (Dict dict : list) {
				if(dict.getValue().substring(0, 1).equals(select[1])){
					list2.add(dict);
				}
			}
	        try {
	            PrintWriter pw = response.getWriter();
	            pw.write(JsonMapper.toJsonString(list2));
	        } catch (IOException ioe) {

	        }
	    }

}