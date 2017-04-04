package cn.micromoving.bcp.modules.hr.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.LanguageAbility;
import cn.micromoving.bcp.modules.hr.entity.LanguageAbility;
import cn.micromoving.bcp.modules.hr.service.LanguageAbilityService;


@Controller
@RequestMapping(value = "${adminPath}/hr/language")
public class LanguageAbilityController extends BaseController {
	 @Autowired
	    private LanguageAbilityService languageAbilityService;
	 
	 
	 	/**
		 * 根据主键， 取得考核信息记录。
		 * 
		 * @param id
		 *            primary key
		 * @return LanguageAbilityDetail entity
		 */
	 @ModelAttribute
		public LanguageAbility get(@RequestParam(required = false) String id) {
			/* 判断id是否为空，如果有值，调用service来取得id对应的考核信息记录。否则创建一个新的考核信息对象。 */
			if (StringUtils.isNotBlank(id)) {
				return languageAbilityService.get(id);
			} else {
				return new LanguageAbility();
			}

		}
	    /**
		 * 查询用户的全部考核信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
		 * 
		 * @param languageAbility
		 *            考核信息
		 * @param model
		 *            视图模型
		 * @return 用户的全部考核信息（不分页）
		 */
		@RequestMapping(value = { "list", "" })
		public String list(LanguageAbility  languageAbility, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
			
			/* 返回list页面 */
			return "modules/hr/languageAbilityList";
		}
		
		
		/**
		 * 根据主键，查询到考核信息，将此信息绑定到model中，在JSP页面中可以读取。
		 * 
		 * @param LanguageAbility
		 *            考核相关信息entity，传递数据。
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "form")
		public String form(LanguageAbility  languageAbility, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
			model.addAttribute("languageAbility", languageAbility);
			return "modules/hr/languageAbilityForm";
		}
		
		/**
		 * 删除考核记录。操作成功后，转至列表页面。
		 * 
		 * @param languageAbility
		 *            考核entity
		 * @param redirectAttributes
		 *            重定向属性集
		 * @return 操作完成后，重定向到列表页面。
		 */
		@RequestMapping(value = "delete")
		public String delete(LanguageAbility languageAbility, RedirectAttributes redirectAttributes) {
			
			/* 调用delete删除当前languageAbility值 */
			languageAbilityService.delete(languageAbility);
			/* 添加Flash信息 */
			addMessage(redirectAttributes, "删除成功");
			/* 重定向至list页面 */
			return "redirect:" + adminPath + "/hr/languageAbility/list?repage";
		}
		
		/**
		 * 
		 * @param languageAbility 
		 *             考核信息
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
		public String save( LanguageAbility  languageAbility, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
			/*保存考核信息*/
			languageAbilityService.save(languageAbility);
			/*重定向显示消息*/
			addMessage(redirectAttributes, "保存成功");
			/*重定向list页面*/
			return "redirect:" + adminPath + "/hr/languageAbility/list?repage";
		}

}
