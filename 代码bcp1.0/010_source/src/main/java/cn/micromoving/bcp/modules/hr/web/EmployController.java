package cn.micromoving.bcp.modules.hr.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.mapper.JsonMapper;
import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.FileUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.ExportExcel;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.common.web.CommonConstant;
import cn.micromoving.bcp.modules.hr.entity.Employ;
import cn.micromoving.bcp.modules.hr.service.EmployService;
import cn.micromoving.bcp.modules.sys.entity.Dict;
import cn.micromoving.bcp.modules.sys.utils.DictUtils;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = { "${adminPath}/hr/employ", "${frontPath}","${frontPath}/hr/employ" })
public class EmployController extends BaseController {
    @Autowired
    private EmployService employService;

    private static String ISSHOW = "0";

    /**
     * 根据主键， 取得招聘记录。
     * 
     * @param id primary key
     * @return Employ entity
     */
    @ModelAttribute
    public Employ get(@RequestParam(required = false)
    String id) {
        /* 判断id是否为空，如果有值，调用service来取得id对应的教育经历记录。否则创建一个新的教育经历对象。 */
        if (StringUtils.isNotBlank(id)) {
            return employService.get(id);
        } else {
            return new Employ();
        }

    }

    /**
     * 根据主键，查询到招聘信息，将此信息绑定到model中，在JSP页面中可以读取。
     * 
     * @param employ 招聘信息entity，传递数据。
     * @param model 视图模型
     * @return 返回招聘信息编辑页面
     * @throws Exception
     */
    @RequestMapping(value = "form")
    public String form(Employ employ, Model model, HttpServletRequest request) throws Exception {

        if (DateUtils.isdeadLineRegister(Global.getConfig("deadLineRegister"))) {
            model.addAttribute("flag", "1");
        } else {
            model.addAttribute("flag", "0");
        }

        /* 获取当前用户的Id值 */
        String userId = UserUtils.getUser().getId();
        /* 将employ对象通过获取到的Id值重新获取 */
        employ = employService.getByUser(userId);
        if (null == employ.getIDCardNumber() && UserUtils.getUser().getLoginName().length() == 18) {
            /* 通过用户名（身份证号），绑定到用户的身份证号，同时取得对应的生日、性别。 */
            employ.setIDCardNumber(UserUtils.getUser().getLoginName());
            employ.setBirthDateString(DateUtils.getBirthDateByIDCard(employ.getIDCardNumber(),
                    Global.getConfig("web.dateFormat")));
            /* 取得身份证号第17位的性别标识。 */
            String gender = employ.getIDCardNumber().substring(16, 16);
            employ.setGender(gender);
            employ.setName(UserUtils.getUser().getName());

        }

        /* 将employ对象的信息保存到employ模型中 */
        model.addAttribute("employ", employ);
        /* 返回个人简历页面 */
        return "modules/hr/employForm";

    }

    /**
     * 添加、更新招聘信息记录。操作成功后，转至列表页面。
     * 
     * @param Employ employ entity
     * @param request http请求
     * @param model 视图模型
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     * @throws Exception
     */
    @RequestMapping(value = "save")
    public String save(Employ employ, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes)
            throws Exception {

        employ.setAuditingStatus("1");
        /* 在model中取得Employ的相关值，进行服务端参数有效性验证。 */
        if (!beanValidator(model, employ)) {
            return "modules/hr/employForm";
        }
        /* 如果Employ中获取的终止日期为空，则新建date类型的对象a，将终止日期获取为当前日期 */
        if (employ.getFirstEndDate() == null) {
            Date a = new Date();
            employ.setFirstEndDate(a);
        }
        /* 判断时间是否合法 1：只和今天的日期比较；2：只比较俩个日期的先后顺序;0：既比较日期的先后顺序，又与今天的日期作比较。验证成功：返回true；严重失败：将错误信息添加到 message 中 */
        if (!dateValidator(model, "0", employ.getFirstStartDate(), employ.getFirstStartDate())) {
            return "modules/hr/employForm";
        }
        /* 在model中获取起始日期和终止日期，进行服务端参数有效性验证（既比较日期的先后顺序，又与今天的日期作比较） */
        if (!dateValidator(model, "0", employ.getFirstStartDate(), employ.getHighestStartDate())) {
            return "modules/hr/employForm";
        }
        if (!dateValidator(model, "0", employ.getFirstEndDate(), employ.getHighestEndDate())) {
            return "modules/hr/employForm";
        }

        if (!dateValidator(model, "0", employ.getHighestDegreeStartDate(), employ.getHighestDegreeEndDate())) {
            return "modules/hr/employForm";
        }
        if (!dateValidator(model, "0", employ.getHighestDegreeStartDate(), employ.getHighestDegreeEndDate())) {
            return "modules/hr/employForm";
        }

        /* 获取当前用户Employ信息 */
        employ.setUser(UserUtils.getUser());
        /* 调用save保存获取到的Employ值 */
        employService.saveEmploy(employ);
        /* 添加Flash信息 */
        addMessage(redirectAttributes, "简历提交成功");
        /* 重定向至list页面 */
        return "redirect:" + frontPath + "/hr/employ/form";
    }

    /**
     * 查询全部招聘用户信息（分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
     * 
     * @param employ 招聘entity
     * @param request http请求
     * @param response http响应
     * @param model 视图模型
     * @return 全部教师用户信息（分页）
     */
    @RequiresPermissions("hr:employ:search")
    @RequestMapping(value = { "list", "" })
    public String list(Employ employ, HttpServletRequest request, HttpServletResponse response, Model model) {
        /* 通过employService.findEmploy()方法将teacher信息进行分页 */
        Page<Employ> page = employService.findEmploy(new Page<Employ>(request, response), employ);
        /* page信息保存到对应的模型中 */
        model.addAttribute("page", page);
        /* 返回empolyList页面 */
        return "modules/hr/employList";
    }

    /**
     * 导出招聘数据
     * 
     * @param user
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */

    @RequestMapping(value = "export")
    public String exportFile(Employ employ, HttpServletRequest request, HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        try {
            String fileName = "招聘信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            Page<Employ> page = new Page<Employ>();
            page = employService.findEmploy(page, employ);
            CommonConstant.EXPORT_INDEX_VALUE = 1;
            new ExportExcel("招聘信息", Employ.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出招聘信息失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + adminPath + "/hr/employ/list?repage";
    }

    /**
     * 根据主键，查询到教师用户信息，将此信息绑定到model中，在JSP页面中可以读取。
     * 
     * @param edu 教师用户entity，传递数据。
     * @param model 视图模型
     * @return 返回教师用户编辑页面
     */
    @RequestMapping(value = "detail")
    public String detail(Employ employ, HttpServletRequest request, Model model) {

        model.addAttribute("employ", employ);
        /* 返回个人所有资料的页面 */
        return "modules/hr/employDetail";

    }
    @RequiresPermissions("hr:employ:audit")
    @RequestMapping(value = "audit")
    public String audit(Employ employ, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {

        employ.setAuditDate(new Date());
        /* 调用save保存获取到的Employ值 */
        employService.save(employ);
        /* 重定向至list页面 */
        return "redirect:" + frontPath + "/hr/employ/list";
    }

    @RequestMapping(value = "word")
    public void importWord(Employ employ, String id, HttpServletResponse response, HttpServletRequest request)
            throws IOException {

        String path = request.getSession().getServletContext().getRealPath("/");
        employService.importWord(employ, path);
        FileUtils.downloads(path + Global.getConfig("uploads.user") + "employ/", employ.getId() + ".doc", request,
                response, employ.getName() + "_个人简历.doc");
       
    }

    @RequestMapping(value = "subList")
    public void getSubList(HttpServletResponse response, HttpServletRequest request) {

        String mainSelect=request.getParameter("mainSelect");
       
        List<Dict> list = DictUtils.getSubDictList("employ_post_type", mainSelect);
        try {
            PrintWriter pw = response.getWriter();
            pw.write(JsonMapper.toJsonString(list));
        } catch (IOException ioe) {

        }
    }

}
