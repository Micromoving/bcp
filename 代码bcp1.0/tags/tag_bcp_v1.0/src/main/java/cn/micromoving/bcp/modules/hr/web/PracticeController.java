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
import cn.micromoving.bcp.modules.hr.entity.Practice;
import cn.micromoving.bcp.modules.hr.service.PracticeService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/practice")
public class PracticeController extends BaseController{
	
	@Autowired
	private  PracticeService  practiceService;
	
	/**
	 * 根据主键，取得实践经验记录
	 * @param id primary key
	 * @return Practice entity
	 */
	@ModelAttribute
	public  Practice get(@RequestParam(required=false) String id) {
		/*判断id是否为空，如果有值，调用service来取得id对应的实践经验记录。否则创建一个新的实践经验记录。*/
		if (StringUtils.isNotBlank(id)){
			return practiceService.get(id);
		}else{
			return new Practice();
		}
	}
	/**
	 * 查询用户的全部实践经验信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * @param practice 实践经验
	 * @param request http请求
	 * @param response http 响应
	 * @param model  视图模型
	 * @return  用户的全部实践经验信息（不分页）
	 */

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "list")
	public String list( Practice  practice, Model model,HttpServletRequest request,HttpServletResponse response) {
		/*获取当前用户*/
		practice.setUser(UserUtils.getUser());
		/*获取实践经验信息以list方式显示放入model中*/
		model.addAttribute("list", practiceService.findList(practice));
		/*返回list页面*/
		return "modules/hr/practiceList";
	}
	/**
	 * 根据主键，查询到实践经验信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * @param practice 实践经验entity，传递数据。
	 * @param model 视图模型
	 * @return 返回实践经验编辑页面
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form( Practice practice, Model model) {
		/*将实践经验信息绑定到model中*/
		model.addAttribute("practice", practice);
		/*返回form页面*/
		return "modules/hr/practiceForm";
	}
	
	/**
	 * 添加、更新实践经验记录。操作成功后，转至列表页面。
	 * 
	 * @param practice, Model model) {
		return null;
	 *            实践经验entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "save")
	public String save( Practice  practice, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		/*判断数据是否符合服务器端约束规则*/
		if (!beanValidator(model, practice)) {
			return form(practice, model);
		}
		/*判断结束时间是否为空，为空则获取当前日期*/
		if(practice.getEndDate() == null)  {
			Date a = new Date();
			practice.setEndDate(a);
		}
		/*判断时间是否合法  1：只和今天的日期比较；2：只比较俩个日期的先后顺序;0：既比较日期的先后顺序，又与今天的日期作比较。验证成功：返回true；严重失败：将错误信息添加到 message 中*/
		if(!dateValidator(model,"0",practice.getStartDate(),practice.getEndDate())) {
			return form(practice,model);
		}
		/*获取当前用户*/
		practice.setUser(UserUtils.getUser());
		/*保存实践经验信息*/
		practiceService.save(practice);
		/*重定向显示消息*/
		addMessage(redirectAttributes, "保存成功");
		/*重定向list页面*/
		return "redirect:" + adminPath + "/hr/practice/list?repage";
	}
	/**
	 * 删除实践经验记录。操作成功后，转至列表页面。
	 * 
	 * @param practice
	 *            实践经验entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "delete")
	public String delete( Practice  practice, RedirectAttributes redirectAttributes) {
		/*删除实践经验信息*/
		practiceService.delete(practice);
		/*重定向显示消息*/
		addMessage(redirectAttributes, "删除成功");
		/*重定向list页面*/
		return "redirect:" + adminPath + "/hr/practice/list?repage";
	}

}
