package cn.micromoving.bcp.modules.hr.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.utils.FileUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.UploadUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.Attachment;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.service.AttachmentService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.hr.utils.HrUtils;
import cn.micromoving.bcp.modules.sr.entity.Achievement;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/attachment")
public class AttachmentController extends BaseController {
	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 根据主键，取得用户附件
	 * 
	 * @param id
	 *            primary key
	 * @return Attachment entity
	 */
	@ModelAttribute
	public Attachment get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的附件。否则创建一个新的附件。 */
		if (StringUtils.isNotBlank(id)) {
			return attachmentService.get(id);
		} else {
			return new Attachment();
		}

	}

	/**
	 * 根据主键，查询到附件，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param Attachment
	 *            附件entity，传递数据。
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "form")
	public String form(Attachment attachment, Model model,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		/* 获取当前用户 */
		attachment.setUser(user);
		model.addAttribute("attachment", attachment);
		return "modules/hr/attachmentForm";
	}

	/**
	 * 添加、更新教育经历记录。操作成功后，转至列表页面。
	 * 
	 * @param Attachment
	 *            附件entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向集
	 * @return 操作完成后，重定向到列表页面。
	 */

	@RequestMapping(value = "save")
	public String save(Attachment attachment,
			MultipartHttpServletRequest request, Model model,
			@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) throws ServletException,
			IOException {
		if (!beanValidator(model, attachment)) {
			return form(attachment, model, request);
		}
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		/* 如果用户更新附件，执行覆盖操作。先上传，接着删除原来的文件。 */
		if (file.getSize() > 0 && file != null) {
			UploadUtils upload = new UploadUtils();
			upload.setBasePath(Global.getConfig("uploads.attach.user"));
			attachment.setAttachmentPath(Global
					.getConfig("uploads.attach.user"));
			String fileName = upload.uploadFile(request, file);
			Achievement achievement = new Achievement();
			achievement.setAchievementId(user.getId());
			attachment.setAttachmentName(attachment.getAttachmentName());
			attachment.setAchievement(achievement);
			if (null != attachment.getAttachmentSource()) {
				FileUtils.deleteFile(request.getSession().getServletContext()
						.getRealPath("/")
						+ Global.getConfig("uploads.attach.user")
						+ attachment.getAttachmentSource());
			}
			attachment.setAttachmentSource(fileName);
		}

		attachment.setUser(UserUtils.getUser());
		/* 如果用户提交附件为空，提示“文件不能为空！”，若不为空完成保存 */
		if (null == attachment.getAttachmentSource()) {
			addMessage(model, "文件不能为空！");
			return form(attachment, model, request);
		} else {
			attachmentService.save(attachment);
			addMessage(redirectAttributes, "保存成功");
		}
		return "redirect:" + adminPath + "/hr/attachment/list?repage";
	}

	/**
	 * 删除附件记录。操作成功后，转至列表页面。
	 * 
	 * @param Attachment
	 *            附件entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */

	@RequestMapping(value = "delete")
	public String delete(Attachment attachment, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		FileUtils.deleteFile(request.getSession().getServletContext()
				.getRealPath("/")
				+ Global.getConfig("uploads.attach.user")
				+ attachment.getAttachmentSource());
		attachmentService.delete(attachment);

		addMessage(redirectAttributes, "删除附件成功");

		return "redirect:" + adminPath + "/hr/attachment/list?repage";
	}

	/**
	 * 查询用户的全部附件信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param Attachment
	 *            附件entity
	 * @param request
	 *            http请求
	 * @param response
	 *            http响应
	 * @param model
	 *            视图模型
	 * @return
	 */

	@RequestMapping(value = { "list", "" })
	public String list(Attachment attachment, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = (User) request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee) request.getSession().getAttribute(
				HrConstant.EMPLOYEE);
		model.addAttribute("user", user);
		model.addAttribute("employee", emp);
		attachment.setUser(UserUtils.getUser());
		model.addAttribute("list", attachmentService.findList(attachment));
		return "modules/hr/attachmentList";
	}

	@RequestMapping(value = "downloads")
	public String downLoads(Attachment attachment, HttpServletRequest request,
			HttpServletResponse response) {
		/* 获取要下载的附件全路径 */
		String filePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ Global.getConfig("uploads.attach.user");
		/* 获取对应附件的后缀名 */
		String ext = attachment.getAttachmentSource().substring(
				attachment.getAttachmentSource().lastIndexOf("."));
		/* 设置新的下载名 */
		String downLoadsName = attachment.getAttachmentName() + ext;
		/* 下载到本地 */
		FileUtils.downloads(filePath, attachment.getAttachmentSource(),
				request, response, downLoadsName);
		return null;
	}
}
