/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.modules.sys.web;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.beanvalidator.BeanValidators;
import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.FileUtils;
import cn.micromoving.bcp.common.utils.ImageCut;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.UploadUtils;
import cn.micromoving.bcp.common.utils.excel.ExportExcel;
import cn.micromoving.bcp.common.utils.excel.ImportExcel;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.Role;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 用户Controller
 * 
 * @author songcm
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value ={ "${adminPath}/sys/user","${frontPath}/employ" })
public class UserController extends BaseController {

	@Autowired
	private SystemService systemService;

	@ModelAttribute
	public User get(@RequestParam(required = false) String id) {
		/*判断当前用户id是否为空，若不为空则返回该id对应的user对象，否则则返回一个新的user对象*/
		if (StringUtils.isNotBlank(id)) {
			return systemService.getUser(id);
		} else {
			return new User();
		}
	}

	@RequestMapping(value = { "index" })
	public String index(User user, Model model) {
		return "modules/sys/userIndex";
	}

	@RequestMapping(value = { "list", "" })
	public String list(User user, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		Page<User> page = systemService.findUser(new Page<User>(request,
				response), user);
		model.addAttribute("page", page);
		return "modules/sys/userList";
	}

	@ResponseBody
	@RequestMapping(value = { "listData" })
	public Page<User> listData(User user, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		/*通过systemService.findUser()方法将user的所有信息进行分页*/
		Page<User> page = systemService.findUser(new Page<User>(request,
				response), user);
		return page;
	}

	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		/*若user的company属性或company的id属性为空，则设置为当前用户的company属性值*/
		if (user.getCompany() == null || user.getCompany().getId() == null) {
			user.setCompany(UserUtils.getUser().getCompany());
		}
		/*若user的office属性或office的id属性为空，则设置为当前用户的office属性值*/
		if (user.getOffice() == null || user.getOffice().getId() == null) {
			user.setOffice(UserUtils.getUser().getOffice());
		}
		/*将user的信息保存到名为:user的模型中*/
		model.addAttribute("user", user);
		/*将用户的所有角色信息保存到名为:allRoles的模型中*/
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/hr/employeeForm";
	}

	@RequestMapping(value = "save")
	public String save(User user, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		/*判断当前是否为演示模式，若是则不允许执行相关操作*/
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		// TODO 等用户的弹出jbox显示正常后，此处再做相关改动。
		user.setCompany(new Office(request.getParameter("company.id")));
		user.setOffice(new Office(request.getParameter("office.id")));

		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user
					.getNewPassword()));
		}
		/*将对应的user信息进行服务器端验证*/
		if (!beanValidator(model, user)) {
			return form(user, model);
		}
		/*判断用户的登录名是否已经存在*/
		if (!"true".equals(checkLoginName(user.getOldLoginName(),
				user.getLoginName()))) {
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，用户已存在");
			return form(user, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		/* 取得用户在表单中输入的角色列表内容 */
		List<String> roleIdList = user.getRoleIdList();
		/* 遍历当前用户所具备的所有权限。对权限进行过滤。表单中所输入的角色，与用户所拥有的角色做交集。 */
		for (Role r : systemService.findAllRole()) {
			/* 如果表单中所输入的角色包含用户权限，将此权限保存 */
			if (roleIdList.contains(r.getId())) {
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		// 保存用户信息
		systemService.saveUser(user);
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())) {
			UserUtils.clearCache();
			// UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		/*判断用户类型*/
		/* 1:教师用户。2：学生 */
		if ("1".equals(user.getUserType())) {
			return "redirect:" + adminPath + "/hr/employee/list?repage";
		} else {
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}

	}

	@RequestMapping(value = "delete")
	public String delete(User user, RedirectAttributes redirectAttributes) {
		/*判断当前是否为演示模式，若是则不允许执行相关操作*/
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		/*判断用户要删除的用户是否合法*/
		if (UserUtils.getUser().getId().equals(user.getId())) {
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
		} else if (User.isAdmin(user.getId())) {
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
		} else {
			systemService.deleteUser(user);
			addMessage(redirectAttributes, "删除用户成功");
		}
		/*判断用户类型*/
		/* 1:教师用户。2：学生 */
		if ("1".equals(user.getUserType())) {
			return "redirect:" + adminPath + "/hr/employee/list?repage";
		} else {
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
	}

	/**
	 * 导出用户数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hr:employee:export")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(User user, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "用户数据" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			Page<User> page = systemService.findUser(new Page<User>(request,
					response, -1), user);
			new ExportExcel("用户数据", User.class).setDataList(page.getList())
					.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 导入用户数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hr:employee:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file,
			RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<User> list = ei.getDataList(User.class);
			for (User user : list) {
				try {
					if ("true".equals(checkLoginName("", user.getLoginName()))) {
						user.setPassword(SystemService
								.entryptPassword("123456"));
						BeanValidators.validateWithException(validator, user);
						systemService.saveUser(user);
						successNum++;
					} else {
						failureMsg.append("<br/>登录名 " + user.getLoginName()
								+ " 已存在; ");
						failureNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>登录名 " + user.getLoginName()
							+ " 导入失败：");
					List<String> messageList = BeanValidators
							.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>登录名 " + user.getLoginName()
							+ " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户"
					+ failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 下载导入用户数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "用户数据导入模板.xlsx";
			List<User> list = Lists.newArrayList();
			list.add(UserUtils.getUser());
			new ExportExcel("用户数据", User.class, 2).setDataList(list)
					.write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 验证登录名是否有效
	 * 
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		return UserUtils.checkLoginName(oldLoginName, loginName);
	}

	/**
	 * 用户信息显示及保存
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "info")
	public String info(User user, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		/*取得当前用户*/
		User currentUser = UserUtils.getUser();
		/*判断当前用户的name属性是否为空*/
		if (StringUtils.isNotBlank(user.getName())) {
			/*若当前为演示模式，则不允许进行相关操作*/
			if (Global.isDemoMode()) {
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userInfo";
			}
			/*保存用户信息*/
			systemService.updateUserInfo(currentUser);
			/*弹出信息提示框，内容为:保存用户信息成功*/
			model.addAttribute("message", "保存用户信息成功");
		}
		/*将currentUser信息保存到user模型中*/
		model.addAttribute("user", currentUser);
		/*将Global信息保存到Global模型中*/
		model.addAttribute("Global", new Global());
		/*判断用户类型*/
		/* 1:教师;2:学生 ;3:注册用户*/
		if ("0".equals(currentUser.getUserType())||"1".equals(currentUser.getUserType())) {
			
			request.getSession().setAttribute("menuIds",
					request.getParameter("menuIds"));
			return "redirect:" + adminPath + "/hr/employee/form?id="
					+ currentUser.getId();
		}
		return "modules/hr/employeeDetail";
	}

	/**
	 * 返回用户信息
	 * 
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return UserUtils.getUser();
	}

	/**
	 * 修改个人用户密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		/*获取当前用户*/
		User user = UserUtils.getUser();
		/*如果原密码和新密码不为空*/
		if (StringUtils.isNotBlank(oldPassword)
				&& StringUtils.isNotBlank(newPassword)) {
			/*若当前为演示模式，则不允许进行相关操作*/
			if (Global.isDemoMode()) {
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userModifyPwd";
			}
			/*验证密码*/
			if (SystemService.validatePassword(oldPassword, user.getPassword())) {
				/*通过用户id和登录名修改密码*/
				systemService.updatePasswordById(user.getId(),
						user.getLoginName(), newPassword);
				/*弹出信息提示框，内容为:修改密码成功*/
				model.addAttribute("message", "修改密码成功");
			} else {
				/*弹出信息提示框，内容为:修改密码失败，旧密码错误*/
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		/*将用户信息保存到user模型中*/
		model.addAttribute("user", user);
		return "modules/sys/userModifyPwd";
	}

	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String officeId,
			HttpServletResponse response) {
		/*创建一个ArrayList容器*/
		List<Map<String, Object>> mapList = Lists.newArrayList();
		/*通过用户的officeId获取到该用户，并将该用户放入List容器中*/
		List<User> list = systemService.findUserByOfficeId(officeId);
		for (int i = 0; i < list.size(); i++) {
			User e = list.get(i);
			/*新建一个HashMap容器*/
			Map<String, Object> map = Maps.newHashMap();
			/*分别将用户的id、officeId、name放入map中*/
			map.put("id", "u_" + e.getId());
			map.put("pId", officeId);
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

	@RequestMapping(value = "upload")
	public String upload(User user, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws ServletException, IOException, Exception {
		/*获取当前用户*/
		user = UserUtils.get(user.getId());
		/*将当前用户信息保存到user模型中*/
		model.addAttribute("user", user);
		/*返回上传头像的界面*/
		return "modules/sys/userPhoto";
	}

	@RequestMapping(value = "imgUpload", method = RequestMethod.POST)
	public String imgUpload(@RequestParam("file") MultipartFile file,
			User user, HttpServletRequest request,
			RedirectAttributes redirectAttributes, Model model)
			throws ServletException, IOException {
		/*
		 * 用户头像上传中，将图片名称命名。命名规则：temp_用户登录名+随机生成的唯一性的长整型数字+原文件后缀名
		 * (此处命名规则是为了方便以后进行相关操作)
		 */
		String fileName = "temp_" + UserUtils.getUser().getLoginName() + "_"
				+ UploadUtils.getUniqueFileName() + "."
				+ UploadUtils.getExtName(file.getOriginalFilename());
		/* 头像上传的保存路径。uploads.user=uploads/user/ (由于后期要保存缩略图和裁剪图，所以需要提前准备两个保存路径) */
		String filePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ Global.getConfig("uploads.user");
		/* 保存用户上传的图片（原图）。 */
		file.transferTo(new File(filePath + fileName)); // 原图
		/* 按固定宽、高进行缩略。缩略后，原图将被覆盖 */
		ImageCut.cut(filePath + fileName);
		/* 将当前用户的缩略图文件名保存到用户模型的头像属性。 */
		user.setPhoto(fileName);
		/* 将user保存到“user”模型中为了在jsp页面中显示 */
		model.addAttribute("user", user);
		/* 返回上传头像的主页面 */
		return "modules/sys/userPhoto";
	}

	@RequestMapping(value = "savePhoto")
	public String savePhoto(User user, RedirectAttributes redirectAttributes,
			MultipartHttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Image img;
		ImageFilter cropFilter;

		/* 头像上传的保存路径。uploads.user=uploads/user/ */
		String filePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ Global.getConfig("uploads.user");
		/* 获取页面中的坐标以及宽、高的值 */
		int x = Integer.parseInt(request.getParameter("x"));
		int y = Integer.parseInt(request.getParameter("y"));
		int w = Integer.parseInt(request.getParameter("w"));
		int h = Integer.parseInt(request.getParameter("h"));

		try {
			// 读取源图像，此时，用户头像文件名有前缀temp
			File srcFile = new File(filePath + user.getPhoto());
			/* 将源文件读入到ImageIO流中 */
			BufferedImage bi = ImageIO.read(srcFile);
			int srcWidth = bi.getWidth();// 原图宽度
			int srcHeight = bi.getHeight();// 原图高度
			Image image = bi.getScaledInstance(srcWidth, srcHeight,
					Image.SCALE_DEFAULT);
			/* 保存用户的头像小图 */
			cropFilter = new CropImageFilter(x, y, w, h);
			img = Toolkit.getDefaultToolkit().createImage(
					new FilteredImageSource(image.getSource(), cropFilter));
			BufferedImage tag = new BufferedImage(w, h,
					BufferedImage.TYPE_INT_BGR);
			Graphics g = tag.getGraphics();
			g.drawImage(img, 0, 0, null);
			g.dispose();
			/* 去掉用户头像文件中的前缀temp */
			if(user.getPhoto().startsWith("temp_")){
				user.setPhoto(user.getPhoto().substring(5));
			}
			/* url:用户头像缩略图全路径。包括应用服务器根目录/文件目录/文件名 */
			String url = filePath + "profile/" + user.getPhoto();

			/* 在保存图片前，先将之前的图片做删除处理。删除两个目录，分别是user, */
			/* 如果用户以前上传过头像，删除相关的过时图片。 */
			/* 如果用户以前没有上传过头像，则不处理。 */
			FileUtils.deleteFiles(filePath, user.getLoginName(),user.getPhoto());
			FileUtils.deleteFiles(filePath + "/profile", user.getLoginName(),user.getPhoto());

			/* 保存小图 */
			ImageIO.write(tag, "JPEG", new File(url));
			/* 保存大图 */
			srcFile.renameTo(new File(filePath + user.getPhoto()));
			/* 将该用户的最新信息保存到数据库 */
			systemService.updateUser(user);

		} catch (IOException e) {
			e.printStackTrace();
		}
		/*注册用户跳转路径。2：注册用户。*/
		if("2".equals(UserUtils.getUser().getUserType())){
		    return "redirect:" + adminPath + "/hr/employ/form";
		}
		/* 重定向到employeeForm页面(将用户id传入) */
		return "redirect:" + adminPath + "/hr/employee/form?id="
				+ user.getId();
	}

}
