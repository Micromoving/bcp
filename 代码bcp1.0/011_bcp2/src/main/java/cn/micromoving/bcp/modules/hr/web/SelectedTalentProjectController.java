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
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.SelectedTalentProject;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.hr.service.SelectedTalentProjectService;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/hr/selectedTalentProject")
public class SelectedTalentProjectController extends BaseController {
	 @Autowired
    private SelectedTalentProjectService selectedTalentProjectService;
	 @Autowired
	private EmployeeService employeeService;

	 
	 	/**
		 * 根据主键， 取得入选人才项目信息记录。
		 * 
		 * @param id
		 *            primary key
		 * @return SelectedTalentProjectDetail entity
		 */
	 @ModelAttribute
		public SelectedTalentProject get(@RequestParam(required = false) String id) {
			/* 判断id是否为空，如果有值，调用service来取得id对应的技能信息记录。否则创建一个新的入选人才项目信息对象。 */
			if (StringUtils.isNotBlank(id)) {
				return selectedTalentProjectService.get(id);
			} else {
				return new SelectedTalentProject();
			}

		}
	    /**
		 * 查询用户的全部入选人才项目信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
		 * 
		 * @param selectedTalentProject
		 *            入选人才项目信息
		 * @param model
		 *            视图模型
		 * @return 用户的全部技能信息（不分页）
		 */
		@RequestMapping(value = { "list", "" })
		public String list(SelectedTalentProject  selectedTalentProject, Model model,HttpServletRequest request,HttpServletResponse response,  RedirectAttributes redirectAttributes) {
			  Page<SelectedTalentProject> page = selectedTalentProjectService.findSelectedTalentProject(new Page<SelectedTalentProject>(request, response), selectedTalentProject);
		        /* page信息保存到对应的模型中 */
		        model.addAttribute("page", page);
		      
			/* 返回list页面 */
			return "modules/hr/selectedTalentProjectList";
		}
		
		
		/**
		 * 根据主键，查询到入选人才项目信息，将此信息绑定到model中，在JSP页面中可以读取。
		 * 
		 * @param SelectedTalentProject
		 *            入选人才项目相关信息entity，传递数据。
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "form1")
		public String form1(SelectedTalentProject  selectedTalentProject,  Model model, HttpServletRequest request,
				RedirectAttributes redirectAttributes) {
			return "modules/hr/selectedTalentProjectForm1";
		}
		
		
		@RequestMapping(value = "form2")
		public String form2(SelectedTalentProject  selectedTalentProject,  Model model, HttpServletRequest request,
				RedirectAttributes redirectAttributes) {
			User user = UserUtils.getByLoginName(selectedTalentProject.getUser().getLoginName());
			Employee employee = employeeService.get(user.getId());
			// 将获取到的employee放到user中
			selectedTalentProject.setEmployee(employee);
			// 将user放到honor
			selectedTalentProject.setUser(user);
			// 将honor对象放入到session中去
			request.getSession().setAttribute("selectedTalentProject", selectedTalentProject);
			return "modules/hr/selectedTalentProjectForm2";
		}
		
		@RequestMapping(value = "form3")
		public String form3(SelectedTalentProject selectedTalentProject, Model model, HttpServletRequest request,
				RedirectAttributes redirectAttributes) {
			// 获取到session中保存的honor
			SelectedTalentProject tempSelectedTalentProject = (SelectedTalentProject) request.getSession().getAttribute("selectedTalentProject");
			// 一下俩步其实就是相当于更新了honor 下的User与Employee
			selectedTalentProject.setUser(tempSelectedTalentProject.getUser());
			selectedTalentProject.setEmployee(tempSelectedTalentProject.getEmployee());
			// 将honor对象放入到session中去
			request.getSession().setAttribute("selectedTalentProject", selectedTalentProject);
			return "modules/hr/selectedTalentProjectForm3";
		}
		/**
		 * 删除入选人才项目记录。操作成功后，转至列表页面。
		 * 
		 * @param selectedTalentProject
		 *            入选人才项目entity
		 * @param redirectAttributes
		 *            重定向属性集
		 * @return 操作完成后，重定向到列表页面。
		 */
		@RequestMapping(value = "delete")
		public String delete(SelectedTalentProject selectedTalentProject,
				RedirectAttributes redirectAttributes) {
			/* 调用delete删除当前teacherQualification值 */
			selectedTalentProjectService.delete(selectedTalentProject);
			/* 添加Flash信息 */
			addMessage(redirectAttributes, "删除成功");
			/* 重定向至list页面 */
			return "redirect:" + adminPath + "/hr/selectedTalentProject/list?repage";
		}
		
		/**
		 * 
		 * @param selectedTalentProject
		 *             入选人才项目信息
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
		public String save(SelectedTalentProject  selectedTalentProject, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
			// 获取到form2 中新添加的这个honor
			selectedTalentProject = (SelectedTalentProject) request.getSession().getAttribute("selectedTalentProject");
			// 将honor保存到数据库中
			selectedTalentProjectService.save(selectedTalentProject);
			// 将honor从session中移除
			request.getSession().removeAttribute("selectedTalentProject");
			return "redirect:" + adminPath + "/hr/selectedTalentProject/list?repage";
		}

}
