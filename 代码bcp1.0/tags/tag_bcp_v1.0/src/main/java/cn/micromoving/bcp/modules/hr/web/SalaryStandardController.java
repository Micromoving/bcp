package cn.micromoving.bcp.modules.hr.web;

import javax.servlet.http.HttpServletRequest;

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
import cn.micromoving.bcp.modules.hr.entity.PerformanceLevel;
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;
import cn.micromoving.bcp.modules.hr.entity.SalaryStandard;
import cn.micromoving.bcp.modules.hr.service.PerformanceLevelService;
import cn.micromoving.bcp.modules.hr.service.SalaryStandardService;

@Controller
@RequestMapping(value = "${adminPath}/hr/salaryStandard")
public class SalaryStandardController extends BaseController {

    @Autowired
    private SalaryStandardService salaryStandardService;

    @Autowired
    private PerformanceLevelService performanceLevelService;

    /**
     * 根据主键， 取得工资方案（系列工资）记录。
     * 
     * @param id primary key
     * @return SalaryStandard entity
     */
    @ModelAttribute
    public SalaryStandard get(@RequestParam(required = false)
    String id) {
        /* 判断id是否为空，如果有值，调用service来取得id对应的工资方案（系列工资）记录。否则创建一个新的工资方案（系列工资）对象。 */
        if (StringUtils.isNotBlank(id)) {
            return salaryStandardService.get(id);
        } else {
            return new SalaryStandard();
        }

    }

    /**
     * 查询工资方案（系列工资）信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
     * 
     * @param salaryStandard 工资方案（系列工资）
     * @param model 视图模型
     * @return 用户的全部工资方案（系列工资）信息（不分页）
     */

    @RequestMapping(value = { "list", "" })
    public String list(SalaryStandard salaryStandard, HttpServletRequest request, Model model) {

        /* 方案编号 */
        String salaryPlanId = request.getParameter("salaryPlan.id");
        if (StringUtils.isNotBlank(salaryPlanId)) {
            request.getSession().setAttribute("salaryPlan.id", salaryPlanId);
        } else {
            SalaryPlan sPlan = new SalaryPlan();
            sPlan.setId(request.getSession().getAttribute("salaryPlan.id").toString());
            salaryStandard.setSalaryPlan(sPlan);
        }

        model.addAttribute("list", salaryStandardService.findList(salaryStandard));

        PerformanceLevel perData = new PerformanceLevel();
        /* 设置工资标准对象，绑定查询。 */
        perData.setSalarystandard(salaryStandard);

        model.addAttribute("perList", performanceLevelService.findList(perData));
        /* 返回list页面 */
        return "modules/hr/salaryStandardList" +( salaryStandard.getPostType()!=null?salaryStandard.getPostType():salaryStandard.getPostLevel());
    }

    /**
     * 根据主键，查询到工资方案（系列工资）信息，将此信息绑定到model中，在JSP页面中可以读取。
     * 
     * @param salaryStandard 工资方案（系列工资）entity，传递数据。
     * @param model 视图模型
     * @return 返回工资方案（系列工资）编辑页面
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "form")
    public String form(SalaryStandard salaryStandard, Model model) {
        /* 将传入的salaryStandard值存入model中，返回form页面 */
        model.addAttribute("salaryStandard", salaryStandard);
        return "modules/hr/salaryStandardForm";

    }

    /**
     * 添加、更新工资方案（系列工资）记录。操作成功后，转至列表页面。
     * 
     * @param salaryStandard 工资方案（系列工资）entity
     * @param request http请求
     * @param model 视图模型
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "save")
    public String save(SalaryStandard salaryStandard, HttpServletRequest request, Model model,
            RedirectAttributes redirectAttributes) {
     
        /* 在model中取得salaryStandard的相关值，进行服务端参数有效性验证。 */
        if (!beanValidator(model, salaryStandard)) {
            return form(salaryStandard, model);
        }

        /* 调用save保存获取到的salaryStandard值 */
        salaryStandardService.save(salaryStandard);
        /* 添加Flash信息 */
        addMessage(redirectAttributes, "保存成功");
        /* 重定向至list页面 */
        return "redirect:" + adminPath + "/hr/salaryStandard/list?repage";
    }

    /**
     * 添加、更新工资方案（系列工资）记录。操作成功后，转至列表页面。
     * 
     * @param salaryStandard 工资方案（系列工资）entity
     * @param request http请求
     * @param model 视图模型
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "batchSave")
    public String batchSave(SalaryStandard salaryStandard, HttpServletRequest request, Model model) {
      
        /* 在model中取得salaryStandard的相关值，进行服务端参数有效性验证。 */
        if (!beanValidator(model, salaryStandard)) {
            return form(salaryStandard, model);
        }

        /* 调用save保存获取到的salaryStandard值 */
        salaryStandardService.saveBatch(salaryStandard);
        performanceLevelService.saveBatch(salaryStandard.getPerformanceLevel());
        /* 添加Flash信息 */
        addMessage(model, "保存成功");
        /* 重定向至list页面 */
        return list( salaryStandard,  request,  model);
    }
}
