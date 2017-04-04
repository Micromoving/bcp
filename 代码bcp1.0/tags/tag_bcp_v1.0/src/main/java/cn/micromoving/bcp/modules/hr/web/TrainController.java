package cn.micromoving.bcp.modules.hr.web;

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

import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.Train;
import cn.micromoving.bcp.modules.hr.service.TrainService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/train")
public class TrainController extends BaseController {
	@Autowired
	private TrainService trainService;

	/**
	 * 根据主键， 取得培训经验记录。
	 * 
	 * @param id
	 *            primary key
	 * @return Work entity
	 */
	@ModelAttribute
	public Train get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的工作经验记录。否则创建一个新的工作经验对象。 */
		if (StringUtils.isNotBlank(id)) {
			return trainService.get(id);
		} else {
			return new Train();
		}
	}

	/**
	 * 根据主键，查询到培训经验信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param work
	 *            培训经验entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回培训经验编辑页面
	 */
	@RequiresPermissions("hr:train:update")
	@RequestMapping(value = "form")
	public String form(Train train, Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		/* 获取当前用户 */
		train.setUser(user);
		model.addAttribute("train", train);
		return "modules/hr/trainForm";
	}

	/**
	 * 添加、更新工作经验记录。操作成功后，转至列表页面。
	 * 
	 * @param work
	 *            培训经验entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("hr:train:create")
	@RequestMapping(value = "save")
	public String save(Train train, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, train)) {
			return form(train, model,request);
		}
		if (train.getEndDate() == null) {
			Date a = new Date();
			train.setEndDate(a);
		}
		if (!dateValidator(model, "0", train.getStartDate(), train.getEndDate())) {
			return form(train, model,request);
		}
		train.setUser(UserUtils.getUser());
		trainService.save(train);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/hr/train/list?repage";
	}

	/**
	 * 删除培训经验记录。操作成功后，转至列表页面。
	 * 
	 * @param train
	 *            培训经验entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions("hr:train:delete")
	@RequestMapping(value = "delete")
	public String delete(Train train, RedirectAttributes redirectAttributes) {
		trainService.delete(train);
		addMessage(redirectAttributes, "删除成功");

		return "redirect:" + adminPath + "/hr/train/list?repage";
	}

	/**
	 * 查询用户的全部培训经验信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param train
	 *            培训经验
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部工作经验信息（不分页）
	 */
	@RequiresPermissions("hr:train:search")
	@RequestMapping(value = { "list", "" })
	public String list(Train train, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		model.addAttribute("user", user);
		model.addAttribute("employee", emp);
		train.setUser(UserUtils.getUser());
		List<Train> list = trainService.findList(train);
		model.addAttribute("list",list);
		return "modules/hr/trainList";
	}
}
