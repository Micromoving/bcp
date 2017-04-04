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
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;
import cn.micromoving.bcp.modules.hr.service.SalaryPlanService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/salaryPlan")
public class SalaryPlanController extends BaseController {

    @Autowired
    private SalaryPlanService salaryPlanService;

    /**
     * 根据主键， 取得工资方案记录。
     * 
     * @param id primary key
     * @return SalaryPlan entity
     */
    @ModelAttribute
    public SalaryPlan get(@RequestParam(required = false)
    String id) {
        /* 判断id是否为空，如果有值，调用service来取得id对应的工资方案记录。否则创建一个新的工资方案对象。 */
        if (StringUtils.isNotBlank(id)) {
            return salaryPlanService.get(id);
        } else {
            return new SalaryPlan();
        }

    }

    /**
     * 查询工资方案信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
     * 
     * @param salaryPlan 工资方案
     * @param model 视图模型
     * @return 用户的全部工资方案信息（不分页）
     */

    @RequestMapping(value = { "list", "" })
    public String list(SalaryPlan salaryPlan, Model model) {

        model.addAttribute("list", salaryPlanService.findList(salaryPlan));
        /* 返回list页面 */
        return "modules/hr/salaryPlanList";
    }

    @RequestMapping(value = "enable")
    public String enable(SalaryPlan salaryPlan, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        String selectedPlan = request.getParameter("planStatus");
        this.salaryPlanService.enable(selectedPlan);

        /* 重定向至list页面 */
        return "redirect:" + adminPath + "/hr/salaryPlan/list?repage";
    }

    /**
     * 根据主键，查询到工资方案信息，将此信息绑定到model中，在JSP页面中可以读取。
     * 
     * @param salaryPlan 工资方案entity，传递数据。
     * @param model 视图模型
     * @return 返回工资方案编辑页面
     */
    @RequestMapping(value = "form")
    public String form(SalaryPlan salaryPlan, Model model) {
        /* 将传入的salaryPlan值存入model中，返回form页面 */
        model.addAttribute("salaryPlan", salaryPlan);
        return "modules/hr/salaryPlanForm";

    }

    /**
     * 添加、更新工资方案记录。操作成功后，转至列表页面。
     * 
     * @param salaryPlan 工资方案entity
     * @param request http请求
     * @param model 视图模型
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     */
    @RequestMapping(value = "save")
    public String save(SalaryPlan salaryPlan, HttpServletRequest request, Model model,
            RedirectAttributes redirectAttributes) {
        request.getSession().getAttribute("employee.id");
        /* 在model中取得salaryPlan的相关值，进行服务端参数有效性验证。 */
        if (!beanValidator(model, salaryPlan)) {
            return form(salaryPlan, model);
        }
        /* 添加新方案时，默认不可用。 */
        salaryPlan.setPlanStatus("0");
        /* 调用save保存获取到的salaryPlan值 */
        salaryPlanService.save(salaryPlan);
        /* 添加Flash信息 */
        addMessage(redirectAttributes, "保存成功");
        /* 重定向至list页面 */
        return "redirect:" + adminPath + "/hr/salaryPlan/list?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(SalaryPlan salaryPlan, RedirectAttributes redirectAttributes) {
        /* 删除方案，以及方案对应的标准。 */
        salaryPlanService.delete(salaryPlan);
        /* 添加提示消息信息 */
        addMessage(redirectAttributes, "保存成功");
        /* 重定向至list页面 */
        return "redirect:" + adminPath + "/hr/salaryPlan/list?repage";
    }
}
