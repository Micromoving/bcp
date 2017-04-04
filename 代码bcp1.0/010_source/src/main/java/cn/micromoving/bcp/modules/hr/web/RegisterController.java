package cn.micromoving.bcp.modules.hr.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.SafeQuestion;
import cn.micromoving.bcp.modules.hr.service.RegisterService;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${frontPath}/register")
public class RegisterController extends BaseController {
    @Autowired
    private RegisterService registerService;

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "forget")
    public String forget(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        return "modules/hr/first";
    }

    @RequestMapping(value = "first")
    public String first(User user, Model model, RedirectAttributes redirectAttributes) {
        user = systemService.getUserByLoginName(user.getLoginName());
        /* 判断当前用户id是否为空，若不为空则返回该id对应的user对象，否则则返回一个新的user对象 */
        if (null != user) {
            SafeQuestion entity = registerService.getQuestion(user.getId());
            entity.setFirstAnswer("");
            entity.setSecondAnswer("");
            entity.setThirdAnswer("");
            entity.setCustomAnswer("");
            user.setSafeQuestion(entity);
            model.addAttribute("user", user);
            // redirectAttributes.addFlashAttribute("user", user);
            return "modules/hr/second";
            // return "redirect:" + frontPath + "/register/question";
        } else {
            addMessage(redirectAttributes, "用户不存在！");

            return "redirect:" + frontPath + "/register/forget";
        }
    }

    @RequestMapping(value = "second")
    public String second(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {

        SafeQuestion question = registerService.getQuestion(user.getId());

        if (question.getFirstAnswer().equals(user.getSafeQuestion().getFirstAnswer())) {
            return "modules/hr/third";

        } else {
            addMessage(model, "答案错误！");
            model.addAttribute("user", user);
            return "modules/hr/second";
        }

    }

    @RequestMapping(value = "third")
    public String third(User user, Model model) {
        /* 如果原密码和新密码不为空 */
        /* 验证密码 */

        systemService.updatePasswordById(user.getId(), user.getLoginName(), user.getNewPassword());
        /* 弹出信息提示框，内容为:修改密码成功 */
        model.addAttribute("message", "修改密码成功");
        return "redirect:" + adminPath;
    }

    @RequestMapping(value = "save")
    public String save(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes)
            throws Exception {
        /* "2" 用户类型 */
        user.setUserType("2");
        user.setCompany(new Office("1"));
        user.setOffice(new Office("1"));
        user.setPassword(SystemService.entryptPassword(user.getPassword()));
        /* 将对应的user信息进行服务器端验证 */
        if (!beanValidator(model, user)) {
            return register(user, model);
        }
        /* 判断用户的登录名是否已经存在 */

        if (!"true".equals(UserUtils.checkLoginName(user.getOldLoginName(), user.getLoginName()))) {
            addMessage(model, "用户'" + user.getLoginName() + "'注册失败，用户已存在");
            return "modules/hr/register";
        }
        registerService.saveUser(user);
        addMessage(redirectAttributes, "注册成功，请登录。");
        return "redirect:" + adminPath + "/login";

    }

    @RequestMapping(value = "register")
    public String register(User user, Model model) {
        return "modules/hr/register";
    }

}