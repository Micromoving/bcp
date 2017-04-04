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
import cn.micromoving.bcp.modules.hr.entity.Certificate;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.service.CertificateService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.hr.utils.HrUtils;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;
@Controller
@RequestMapping(value = "${adminPath}/hr/certificate")
public class CertificateController extends BaseController{

    @Autowired
    private CertificateService certificateService;

    /**
     * 根据主键， 获得证书列表。
     * @param id primary key
     * @return Certificate entity
     */
    @ModelAttribute
    public Certificate get(@RequestParam(required=false) String id) {
        /* 判断id是否为空，如果有值，调用service来取得id对应的教育经历记录。否则创建一个新的教育经历对象。 */
        if (StringUtils.isNotBlank(id)) {
            return certificateService.get(id);
        } else {
            return new Certificate();
        }
    }

    /**
     * 添加、更新附件信息。操作成功后，转至列表页面。
     * @param Certificate  附件信息entity
     * @param request http请求
     * @param model 视图模型
     * @param redirectAttributes  重定向属性集
     * @return 操作完成后，重定向到列表页面。
     */
    @RequestMapping(value = "save")
    public String save(Certificate certificate, HttpServletRequest request, Model model,
            RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, certificate)) {
            return form(certificate, model,request);
        }
        if(certificate.getGainDate() == null)  {
			Date a = new Date();
			certificate.setGainDate(a);
		}
		/*获取证书取得时间，与今天的日期作比较*/	
		if(certificate.getGainDate().after(new Date())){
			addMessage(model, "证书取得时间晚于现在");
			return form(certificate,model,request);
		}
		if(!dateValidator(model,"1",certificate.getGainDate())) {
			return form(certificate,model,request);
		}
        certificate.setUser(UserUtils.getUser());
        certificateService.save(certificate);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:" + adminPath + "/hr/certificate/list?repage";
    }
    
    /**
     * 根据主键，查询到附件相关信息，将此信息绑定到model中，在JSP页面中可以读取。
     * @param Certificate 附件相关信息entity，传递数据。
     * @param model
     * @return
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "form")
    public String form(Certificate certificate, Model model,HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		/* 获取当前用户 */
		certificate.setUser(user);
        model.addAttribute("certificate", certificate);
        return "modules/hr/certificateForm";
    }
    
    /**
     * 删除附件相关信息。操作成功后，转至列表页面。
     * @param Certificate 附件相关信息entity
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     */
    @RequestMapping(value = "delete")
    public String delete(Certificate certificate, RedirectAttributes redirectAttributes) {
        certificateService.delete(certificate);
        addMessage(redirectAttributes, "删除用户成功");

        return "redirect:" + adminPath + "/hr/certificate/list?repage";
    }
    /**
     * 查询用户的全部附件相关信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
     * @param Certificate 附件相关信息
     * @param request http请求
     * @param response http 响应
     * @param model 视图模型
     * @return 用户的全部附件相关信息（不分页）
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = { "list", "" })
    public String list(Certificate certificate, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		model.addAttribute("user", user);
		model.addAttribute("employee", emp);
        certificate.setUser(UserUtils.getUser());           
        model.addAttribute("list", certificateService.findList(certificate));
        return "modules/hr/certificateList";
    }


    
}



