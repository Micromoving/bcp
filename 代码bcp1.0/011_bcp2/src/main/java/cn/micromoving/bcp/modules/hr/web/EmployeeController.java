package cn.micromoving.bcp.modules.hr.web;

import java.io.File;

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
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.SysUtils;

/**
 * 教师用户表Controller
 * 
 * @author lu
 */

@Controller
@RequestMapping(value = "${adminPath}/hr/employee")
public class EmployeeController extends BaseController {
    @Autowired
    private SystemService systemService;
    
    @Autowired
    private EmployeeService employeeService;


    public static final String IMGROOT = "uploads" + File.separator;

    public static final double DEFAULT_WEIGHT = 390;

    public static final double DEFAULT_HEIGHT = 570;

    /**
     * 根据主键， 取得教师用户记录。
     * 
     * @param id primary key
     * @return employ entity
     */
    @ModelAttribute
    public Employee get(@RequestParam(required = false) String id) {
        /* 创建一个新的employ对象 */
        /* 判断id是否为空，如果有值，调用service来取得id对应的教育经历记录。否则创建一个新的教育经历对象。 */
        Employee employee = new Employee();
        /* 判断id是否为空，如果有值，调用service来取得id对应的教育经历记录。否则创建一个新的教育经历对象。 */
        if (StringUtils.isNotBlank(id)) {

            employee = employeeService.get(id);
        }
        /* 返回一个employ对象 */
        return employee;
    }

    /**
     * 查询全部教师用户信息（分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
     * 
     * @param employ 教师用户entity
     * @param request http请求
     * @param response http响应
     * @param model 视图模型
     * @return 全部教师用户信息（分页）
     */
    @RequestMapping(value = { "list", "" })
    public String list(Employee employee, HttpServletRequest request, HttpServletResponse response, Model model) {
        /* 通过employeeService.findemploy()方法将employ信息进行分页 */
        Page<Employee> page = employeeService.findEmployee(new Page<Employee>(request, response), employee);
        /* page信息保存到对应的模型中 */
        model.addAttribute("page", page);
        model.addAttribute("employee", employee);
        /* 返回employList页面 */
        return "modules/hr/employeeList";
    }
    
    /**
     * 根据主键，查询到教师用户信息，将此信息绑定到model中，在JSP页面中可以读取。
     * 
     * @param edu 教师用户entity，传递数据。
     * @param model 视图模型
     * @return 返回教师用户编辑页面
     */
    @RequestMapping(value = "form")
    public String form(Employee employee, HttpServletRequest request, Model model) {
        /* 根据用户ID，取得用户信息，职员信息。绑定到session 中。 */
        /* 如果没有传入ID，则从session中取得用户信息。 */
        if (null == employee.getId()) {
            /* 获取当前用户proTechPosition信息 */
            User user = (User) request.getSession().getAttribute(HrConstant.USER);
            employee = (Employee) request.getSession().getAttribute(HrConstant.EMPLOYEE);
            model.addAttribute("user", user);
            model.addAttribute("employee", employee);
        } else {
            User user = systemService.getUser(employee.getId());
            employee = systemService.getEmp(employee.getId());
            request.getSession().setAttribute(HrConstant.EMPLOYEE, employee);
            request.getSession().setAttribute(HrConstant.USER, user);
            model.addAttribute("user", user);
            model.addAttribute("employee", employee);
        }

        request.getSession().setAttribute("menuIds", "400");
        /* 将employ对象的信息保存到employ模型中 */
        model.addAttribute("employee", employee);
        /* 返回个人简历页面 */
        return "modules/hr/profile";
    }
    
    @RequestMapping(value = "save")
    public String save(Employee employee, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        /* 将对应的employ信息进行服务器端验证 */
        if (!beanValidator(model, employee)) {
            return form(employee, request, model);
        }
        if (StringUtils.isNotBlank(employee.getIdNumber())) {
            if ("1".equals(employee.getIdentityDocumentType())) {
                // 身份证验证
                String str = SysUtils.IDCardValidate(employee.getIdNumber());
                if (StringUtils.isNotBlank(str)) {
                    errorMessages.add(0, "数据验证失败");
                    errorMessages.add(str);
                    addMessage(model);
                    errorMessages.removeAll(errorMessages);
                    employeeService.save(employee);
                    return "modules/hr/profile";
                }
            }
        }

        User user = (User) request.getSession().getAttribute(HrConstant.USER);
        Employee emp = (Employee) request.getSession().getAttribute(HrConstant.EMPLOYEE);

        model.addAttribute("user", user);
        model.addAttribute("employee", emp);
        /* 将当前employ的信息保存数据库中 */
        employeeService.save(employee);
        /* 弹出信息提示框，内容为:保存成功 */
        addMessage(redirectAttributes, "保存成功");
        /* 重定向到employForm页面，并将当前用户Id传入 */
        return "redirect:" + adminPath + "/hr/employee/form?id=" + employee.getId() + "&repage";
    }
}