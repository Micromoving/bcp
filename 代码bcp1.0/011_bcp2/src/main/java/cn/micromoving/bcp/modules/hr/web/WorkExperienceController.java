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
import cn.micromoving.bcp.modules.hr.entity.WorkExperience;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.hr.service.WorkExperienceService;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;
/**
 * 
 * 
 * @author 微动信息 luyihao
 * @version $Revision:  $ $Date:  $
 * <pre>
 * ■变更记录■
 * 2017年3月18日 创建
 * </pre>
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/workExperience")
public class WorkExperienceController extends BaseController {
    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * 根据主键， 取得工作经历信息记录。
     * 
     * @param id primary key
     * @return workexperienceDetail entity
     */
    @ModelAttribute
    public WorkExperience get(@RequestParam(required = false)
    String id) {
        /* 判断id是否为空，如果有值，调用service来取得id对应的工作经历信息记录。否则创建一个新的工作经历信息对象。 */
        if (StringUtils.isNotBlank(id)) {
            return workExperienceService.get(id);
        } else {
            return new WorkExperience();
        }

    }

    /**
     * 查询用户的全部工作经历信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
     * 
     * @param workexperience 工作经历信息
     * @param model 视图模型
     * @return 用户的全部工作经历信息（不分页）
     */
    @RequestMapping(value = { "list", "" })
    public String list(WorkExperience workExperience, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        Page<WorkExperience> page = workExperienceService.findWorkExperience(
                new Page<WorkExperience>(request, response), workExperience);
        /* page信息保存到对应的模型中 */
        model.addAttribute("page", page);
        /* 获取当前用户work信息 */
        return "modules/hr/workExperienceList";
    }

    /**
     * 根据主键，查询到工作经历信息，将此信息绑定到model中，在JSP页面中可以读取。
     * 
     * @param workexperience 工作经历相关信息entity，传递数据。
     * @param model
     * @return
     */
    @RequestMapping(value = "form1")
    public String form1(WorkExperience workExperience, Model model, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        model.addAttribute("workExperience", workExperience);
        System.out.println("form1");
        return "modules/hr/workExperienceForm1";
    }

    @RequestMapping(value = "form2")
    public String form2(WorkExperience workExperience, Model model, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        User user = UserUtils.getByLoginName(workExperience.getUser().getLoginName());
        Employee employee = employeeService.get(user.getId());
        workExperience.setEmployee(employee);
        workExperience.setUser(user);
        request.getSession().setAttribute("workExperience", workExperience);
        return "modules/hr/workExperienceForm2";
    }

    @RequestMapping(value = "form3")
    public String form3(WorkExperience workExperience, Model model, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        // 获取到session中保存的honor
        WorkExperience tempWorkExperience = (WorkExperience) request.getSession().getAttribute("workExperience");
        // 一下俩步其实就是相当于更新了honor下的User与Employee
        workExperience.setUser(tempWorkExperience.getUser());
        workExperience.setEmployee(tempWorkExperience.getEmployee());
        // 将honor对象放入到session中去
        request.getSession().setAttribute("workExperience", workExperience);
        return "modules/hr/workExperienceForm3";
    }

    /**
     * 删除工作经历记录。操作成功后，转至列表页面。
     * 
     * @param workexperience 工作经历entity
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     */
    @RequestMapping(value = "delete")
    public String delete(WorkExperience workExperience, RedirectAttributes redirectAttributes) {

        /* 调用delete删除当前workExperience */
        workExperienceService.delete(workExperience);
        /* 添加Flash信息 */
        addMessage(redirectAttributes, "删除成功");
        /* 重定向至list页面 */
        return "redirect:" + adminPath + "/hr/workExperience/list?repage";
    }

    /**
     * @param workexperience 工作经历信息
     * @param model 视图模型
     * @param request http请求
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     */
    @RequestMapping(value = "save")
    public String save(WorkExperience workExperience, Model model, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        // 获取到form2 中新添加的这个 workExperience
        workExperience = (WorkExperience) request.getSession().getAttribute("workExperience");
        /* 保存workExperience信息 */
        workExperienceService.save(workExperience);
        // 将workExperience从session中移除
        request.getSession().removeAttribute("workExperience");
        addMessage(redirectAttributes, "保存成功");
        /* 重定向list页面 */
        return "redirect:" + adminPath + "/hr/workExperience/list?repage";
    }

}
