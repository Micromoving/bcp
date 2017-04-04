package cn.micromoving.bcp.modules.hr.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adobe.xmp.impl.Utils;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.utils.FileUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.UploadUtils;
import cn.micromoving.bcp.common.utils.excel.ImportExcel;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.entity.Attachment;
import cn.micromoving.bcp.modules.hr.entity.ClassTeacherFee;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.ReportedWorkloade;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.entity.Warm;
import cn.micromoving.bcp.modules.hr.service.ClassTeacherFeeService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.hr.utils.HrUtils;
import cn.micromoving.bcp.modules.sr.entity.Achievement;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/classTeacherFee")
public class ClassTeacherFeeController extends BaseController {
	@Autowired
	private ClassTeacherFeeService classTeacherFeeService;
	@Autowired
	private SalEmpViewDao salEmpViewDao;

	/**
	 * 根据主键， 取得工作经验记录。
	 * 
	 * @param id
	 *            primary key
	 * @return Work entity
	 */
	@ModelAttribute
	public ClassTeacherFee get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的工作经验记录。否则创建一个新的工作经验对象。 */
		if (StringUtils.isNotBlank(id)) {
			return classTeacherFeeService.get(id);
		} else {
			return new ClassTeacherFee();
		}
	}

	/**
	 * 根据主键，查询到工作经验信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param work
	 *            工作经验entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回工作经验编辑页面
	 */
	// @RequiresPermissions("hr:work:update")
	// @RequestMapping(value = "form")
	// public String form(Work work, Model model, HttpServletRequest request) {
	// User user = (User) request.getSession().getAttribute(HrConstant.USER);
	// Employee emp = (Employee) request.getSession().getAttribute(
	// HrConstant.EMPLOYEE);
	// /* 获取当前用户 */
	// work.setUser(user);
	// /* 将传入的work值存入model中 */
	// model.addAttribute("work", work);
	// /* 返回form页面 */
	// return "modules/hr/workForm";
	// }

	/**
	 * 添加、更新工作经验记录。操作成功后，转至列表页面。
	 * 
	 * @param work
	 *            工作经验entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	// @RequiresPermissions({"hr:work:create","hr:work:update"})
	@RequestMapping(value = "save")
	public String save(ClassTeacherFee classTeacherFee,
			HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		/* 在model中取得work的相关值，进行服务端参数有效性验证。 */
		// if (!beanValidator(model, classTeacherFee)) {
		// return form(classTeacherFee, model,request);
		// }
		// if(work.getStartDate() != null && work.getEndDate() != null){
		// /* 在model中获取起始日期和终止日期，进行服务端参数有效性验证（既比较日期的先后顺序，又与今天的日期作比较） */
		// if (!dateValidator(model, "0", work.getStartDate(),
		// work.getEndDate())) {
		// return form(work, model,request);
		// }
		// }

		/* 获取当前用户work信息 */
		/* 调用save保存获取到的work值 */
		classTeacherFeeService.save(classTeacherFee);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "保存成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/classTeacherFee/list?repage";
	}

	/**
	 * 添加、更新取暖标准记录。操作成功后，转至列表页面。
	 * 
	 * @param classTeacherFee
	 *            , Model model) { return null; 取暖标准entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */

	@RequestMapping(value = "batchSave")
	public String batchSave(ClassTeacherFee classTeacherFee, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		/* 保存上报工作量信息 */
		classTeacherFeeService.batchSave(classTeacherFee);
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "保存成功");
		/* 重定向list页面 */
		return "redirect:" + adminPath + "/hr/ classTeacherFee/list?repage";
	}

	/**
	 * 删除教工作经验记录。操作成功后，转至列表页面。
	 * 
	 * @param classTeacherFee
	 *            工作经验entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	// @RequiresPermissions("hr:work:delete")
	@RequestMapping(value = "delete")
	public String delete(ClassTeacherFee classTeacherFee,
			RedirectAttributes redirectAttributes) {
		/* 调用delete删除当前work值 */
		classTeacherFeeService.delete(classTeacherFee);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/classTeacherFee/list?repage";
	}

	/**
	 * 查询用户的全部工作经验信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param work
	 *            工作经验
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部工作经验信息（不分页）
	 */
	// @RequiresPermissions("hr:classTeacherFee:search")
	@RequestMapping(value = { "list" })
	public String list(ClassTeacherFee classTeacherFee,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if (classTeacherFee.getUser() != null) {
			SalEmpView salEmpView = new SalEmpView();
			salEmpView.setName(classTeacherFee.getUser().getName());
			List<SalEmpView> list = salEmpViewDao.findList(salEmpView);
			model.addAttribute("slist", list);

		}
		/* 通过service将classTeacherFee信息保存至list */
		List<ClassTeacherFee> sd = classTeacherFeeService
				.findList(classTeacherFee);
		model.addAttribute("list", sd);
		/* 返回list页面 */
		return "modules/hr/classTeacherFeeList";
	}

	/**
	 * 导入班主任费信息，跳转至classTeacherFeeUpload页面
	 * 
	 * @param classTeacherFee
	 * @param model
	 *            视图模型
	 * @return
	 */
	@RequestMapping(value = "upload")
	public String upload(ClassTeacherFee classTeacherFee, Model model) {
		/* 将班主任费信息绑定到model中 */
		model.addAttribute("classTeacherFee", classTeacherFee);
		/* 返回form页面 */
		return "modules/hr/classTeacherFeeUpload";
	}

	@RequestMapping(value = "downloade")
	public String downloadeFile(ClassTeacherFee classTeacherFee,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		String filePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ Global.getConfig("uploads.classTeacherFee");
        String downLoadsName ="班主任费模板.xlsx";
        String fileName = "import_classTeacherFeeUpload.xlsx";
		FileUtils.downloads(filePath, fileName, request, response,
				downLoadsName);
		return "modules/hr/classTeacherFeeUpload";
	}

	/**
	 * 导入用户数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(@RequestParam("file") MultipartFile file,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		/* 如果为演示模式，不允许操作，重定向到list页面 */
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/hr/classTeacherFee/list?repage";
		}
		try {
			if (file.getSize() == 0) {
				errorMessages.add(0, "数据验证失败");
				errorMessages.add("未放入模板文件！");
				addMessage(model);
				errorMessages.removeAll(errorMessages);
				return "modules/hr/classTeacherFeeUpload";
			}
			/* 标题行号为1，工作表编号为0 */
			ImportExcel ei = new ImportExcel(file, 1, 0);

			UploadUtils upload = new UploadUtils();
			upload.setBasePath(Global.getConfig("uploads.classTeacherFee"));


			/* 文件获取到导入数据列表，并放置到list容器中 */
			List<ClassTeacherFee> list = ei.getDataList(ClassTeacherFee.class);
			/* 新建班主任费 */
			ClassTeacherFee classTeacherFee = new ClassTeacherFee();
			/* 新建上报记录 */
			/* page信息保存到对应的模型中 */
			model.addAttribute("list", list);
			model.addAttribute("classTeacherFee", classTeacherFee);

		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息：" + e.getMessage());
			e.printStackTrace();
			return "modules/hr/classTeacherFeeUpload";
		}
		return "modules/hr/classTeacherFeeList";
	}
}
