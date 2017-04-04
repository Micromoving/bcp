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
import cn.micromoving.bcp.modules.hr.entity.Award;
import cn.micromoving.bcp.modules.hr.service.AwardService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

/**
 * 区域Controller
 * @author songcm
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/award")
public class AwardController extends BaseController{
		
	@Autowired
	private AwardService awardService;
	
	/**
	 *  根据主键，取得奖项表
	 * @param id primary key
	 * @return award entity
	 */
	@ModelAttribute
	public Award get(@RequestParam(required=false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的教育经历记录。否则创建一个新的教育经历对象。 */
		if (StringUtils.isNotBlank(id)){
			return awardService.get(id);
		}else{
			return new Award();
		}
	}
	
	/**
	 * 根据主键，查询奖项信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * @param award 奖项entity，传递数据。
	 * @param model 视图模型
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(Award award, Model model) {
		/*获取获奖信息绑定到model中*/
		model.addAttribute("award", award);
		/*返回到awardform页面*/
		return "modules/hr/awardForm";
	}
	
	/**
	 * 查询用户的全部教育经历信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * @param edu 奖项纪录
	 * @param request http请求
	 * @param response http 响应
	 * @param model  视图模型
	 * @return 用户的全部教育经历信息（不分页）
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"list", " "})
	public String list(Award award, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		award.setUser(UserUtils.getUser());
		/*获取获奖信息以list方式显示放入model中*/
		model.addAttribute("list", awardService.findList(award));
		/*返回awardlist页面*/
		return "modules/hr/awardList";
	}
	
	/**
	 * 添加、更新奖项纪录。操作成功后，转至列表页面.
	 * @param award 奖项纪录entity
	 * @param model 视图模型
	 * @param redirectAttributes   重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "save")
	public String save(Award award, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
				/*判断数据是否符合服务器端约束规则*/
				if (!beanValidator(model, award)) {
					return form(award, model);
				}
				/*判断获奖时间GainDate是否为空，如果为空则为当前时间*/
				if(award.getGainDate() == null)  {
					Date a = new Date();
					award.setGainDate(a);
				}
				/*判断时间是否合法  1：只和今天的日期比较；2：只比较俩个日期的先后顺序;0：既比较日期的先后顺序，又与今天的日期作比较。验证成功：返回true；严重失败：将错误信息添加到 message 中*/
				if(!dateValidator(model,"1",award.getGainDate())) {
					return form(award,model);
				}
				/*修改当前获奖信息*/
				award.setUser(UserUtils.getUser());
				/*保存获奖信息*/
				awardService.save(award);
				/*重定向显示消息*/
				addMessage(redirectAttributes, "保存成功");
				return "redirect:" + adminPath + "/hr/award/list?repage";
	}
		
	/**
	 * 删除奖项纪录。操作成功后，转至列表页面。
	 * @param award 奖项纪录entity
	 * @param redirectAttributes  重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "delete")
	public String delete(Award award, RedirectAttributes redirectAttributes) {
		/*删除当前获奖信息*/
		awardService.delete(award);
		/*重定向显示提示信息*/
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/hr/award/list?repage";
	}
	
}
	
	
	
	
	


