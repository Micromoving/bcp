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

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.Honor;
import cn.micromoving.bcp.modules.hr.entity.Skills;
import cn.micromoving.bcp.modules.hr.service.SkillsService;


@Controller
@RequestMapping(value = "${adminPath}/hr/skills")
public class SkillsController extends BaseController {
	 @Autowired
	    private SkillsService skillsService;
	 
	 
	 	/**
		 * 根据主键， 取得技能信息记录。
		 * 
		 * @param id
		 *            primary key
		 * @return SkillsDetail entity
		 */
	 @ModelAttribute
		public Skills get(@RequestParam(required = false) String id) {
			/* 判断id是否为空，如果有值，调用service来取得id对应的技能信息记录。否则创建一个新的技能信息对象。 */
			if (StringUtils.isNotBlank(id)) {
				return skillsService.get(id);
			} else {
				return new Skills();
			}

		}
	    /**
		 * 查询用户的全部技能信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
		 * 
		 * @param skills
		 *            技能信息
		 * @param model
		 *            视图模型
		 * @return 用户的全部技能信息（不分页）
		 */
		@RequestMapping(value = { "list", "" })
		public String list(Skills  skills, Model model,HttpServletRequest request,HttpServletResponse response,  RedirectAttributes redirectAttributes) {
			  Page<Skills> page = skillsService.findSkills(new Page<Skills>(request, response), skills);
		        /* page信息保存到对应的模型中 */
		        model.addAttribute("page", page);
		      
			/* 返回list页面 */
			return "modules/hr/skillsList";
		}
		
		
		/**
		 * 根据主键，查询到技能信息，将此信息绑定到model中，在JSP页面中可以读取。
		 * 
		 * @param Skills
		 *            技能相关信息entity，传递数据。
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "form")
		public String form(Skills  skills, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
			model.addAttribute("skills", skills);
			return "modules/hr/skillsForm";
		}
		
		/**
		 * 删除技能记录。操作成功后，转至列表页面。
		 * 
		 * @param skills
		 *            技能entity
		 * @param redirectAttributes
		 *            重定向属性集
		 * @return 操作完成后，重定向到列表页面。
		 */
		@RequestMapping(value = "delete")
		public String delete(Skills skills, RedirectAttributes redirectAttributes) {
			
			/* 调用delete删除当前skills值 */
			skillsService.delete(skills);
			/* 添加Flash信息 */
			addMessage(redirectAttributes, "删除成功");
			/* 重定向至list页面 */
			return "redirect:" + adminPath + "/hr/skills/list?repage";
		}
		
		/**
		 * 
		 * @param skills
		 *             技能信息
		 * @param model
		 *             视图模型
		 * @param request 
		 *             http请求
		 * @param redirectAttributes  
		 *             重定向属性集
		 * @return  
		 *        操作完成后，重定向到列表页面。
		 */
		@RequestMapping(value = "save")
		public String save( Skills  skills, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
			/*保存技能信息*/
			skillsService.save(skills);
			/*重定向显示消息*/
			addMessage(redirectAttributes, "保存成功");
			/*重定向list页面*/
			return "redirect:" + adminPath + "/hr/skills/list?repage";
		}

}
