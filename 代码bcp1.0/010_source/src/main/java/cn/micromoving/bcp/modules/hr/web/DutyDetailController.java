package cn.micromoving.bcp.modules.hr.web;

import java.text.ParseException;
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

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.ExportExcel;
import cn.micromoving.bcp.common.utils.excel.ImportExcel;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.act.entity.Act;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.dao.DutyDetailDao;
import cn.micromoving.bcp.modules.hr.entity.DutyDetail;
import cn.micromoving.bcp.modules.hr.entity.ReportPerformance;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.service.DutyDetailService;
import cn.micromoving.bcp.modules.hr.service.ReportRecordService;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "${adminPath}/hr/dutyDetail")
public class DutyDetailController extends BaseController {

	@Autowired
	private DutyDetailService dutyDetailService;

	@Autowired
	private ReportRecordService recordService;

	@Autowired
	private DutyDetailDao dutyDetailDao;

	/**
	 * 根据主键，取得上报值班记录
	 * 
	 * @param id
	 *            primary key
	 * @return Practice entity
	 */
	@ModelAttribute
	public DutyDetail get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的实践经验记录。否则创建一个新的上报值班记录。 */
		if (StringUtils.isNotBlank(id)) {
			return dutyDetailService.get(id);
		} else {
			return new DutyDetail();
		}
	}

	/**
	 * 查询用户的全部上报值班信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param practice
	 *            实践经验
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部实践经验信息（不分页）
	 */
	@RequiresPermissions("hr:dutyDetail:search")
	@RequestMapping(value = "list")
	public String list(DutyDetail dutyDetail, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		/* 通过dutyDetailService.findSalEmpView()方法将dutyDetail信息进行分页 */
		SalEmpView salEmpView = new SalEmpView();

		Page<SalEmpView> page = dutyDetailService.findSalEmpView(
				new Page<SalEmpView>(request, response), salEmpView);
		List<DutyDetail> dutyList = Lists.newArrayList();
		/* 通过for循环设置用户各项信息 */
		for (SalEmpView data : page.getList()) {
			DutyDetail entity = new DutyDetail();
			User user = new User();
			user.setId(data.getId());
			user.setName(data.getName());
			user.setLoginName(data.getLoginName());
			user.setOffice(data.getOffice());
			entity.setUser(user);
			dutyList.add(entity);
		}
		/* 将list中的信息保存到对应的模型中 */
		model.addAttribute("list", dutyList);
		model.addAttribute("dutyDetail", dutyDetail);
		/* 返回list页面 */
		return "modules/hr/dutyDetailList";
	}

	/**
	 * 添加、更新上报值班记录。操作成功后，转至列表页面。
	 * 
	 * @param practice
	 *            , Model model) { return null; 实践经验entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequiresPermissions({ "hr:dutyDetail:create", "hr:dutyDetail:update" })
	@RequestMapping(value = "save")
	public String save(DutyDetail dutyDetail, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		/* 获取当前用户 */
		dutyDetail.setUser(UserUtils.getUser());
		/* 保存上报值班信息 */
		dutyDetailService.save(dutyDetail);
		/* 重定向显示消息 */
		addMessage(redirectAttributes, "保存成功");

		/* 重定向list页面 */
		return "redirect:" + adminPath + "/hr/dutyDetail/list?repage";
	}

	/**
	 * 添加、更新上报值班记录。操作成功后，转至列表页面。
	 * 
	 * @param reportedWorkloade
	 *            , Model model) { return null; 上报值班entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 * @throws ParseException 
	 */
	@RequiresPermissions({ "hr:dutyDetail:create", "hr:dutyDetail:update" })
	@RequestMapping(value = "batchSave")
	public String batchSave(DutyDetail dutyDetail, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) throws ParseException {
		if (StringUtils.isBlank(dutyDetail.getReportRecord()
				.getYearMonth())) {
			errorMessages.add(0, "数据验证失败");
			errorMessages.add("上报年月不能为空");
			addMessage(redirectAttributes);
			errorMessages.removeAll(errorMessages);
			return "redirect:" + adminPath
					+ "/hr/dutyDetail/list?dataClassification=3";
		}
		if(dutyDetailService.ccTime(dutyDetail)){
			errorMessages.add(0, "数据验证失败");
			errorMessages.add("历史记录中已有该年月信息！");
			addMessage(redirectAttributes);
			errorMessages.removeAll(errorMessages);
			return "redirect:" + adminPath
					+ "/hr/dutyDetail/list?dataClassification=3";
		}
		dutyDetailService.batchSave(dutyDetail);

		/* 判断当前用户编号和年份是否具备编辑条件 */
		if (dutyDetail.getDataState().equals("2")) {
			try {
				String[] str = { "department_leader", "personnel_clerk" };
				recordService.save(dutyDetail.getReportRecord(),
						ActUtils.PD_HR_DUTY, str, "performanceuser");
				addMessage(redirectAttributes, "操作成功，数据已经提交。");
			} catch (Exception e) {
				logger.error("启动流程失败：", e);
				addMessage(redirectAttributes, "系统内部错误！");
			}
		}
		/* 重定向list页面 */
		return "redirect:" + adminPath
				+ "/hr/reportRecord/list?dataClassification=3";
	}

	/**
	 * 根据主键，查询到上报值班信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param DutyDetail
	 *            教师用户entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回教师用户编辑页面
	 */
	@RequestMapping(value = "detail")
	public String detail(DutyDetail dutyDetail, HttpServletRequest request,
			Model model) {

		model.addAttribute("list", dutyDetailService.findList(dutyDetail));
		/* 返回个人所有资料的页面 */
		return "modules/hr/dutyDetailDetail";

	}

	/**
	 * 工单执行（完成任务）
	 * 
	 * @param reportedWorkloade
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("hr:dutyDetail:audit")
	@RequestMapping(value = "saveAudit")
	public String saveAudit(DutyDetail dutyDetail, ReportRecord reportRecord,
			Model model, HttpServletRequest request) {
		String comment = dutyDetail.getReportRecord().getAct().getComment();
		dutyDetail.setReportRecord(recordService.get(dutyDetail
				.getReportRecord().getId()));
		dutyDetail
				.getReportRecord()
				.getAct()
				.setProcInsId(
						dutyDetail.getReportRecord().getProcessInstanceId());
		dutyDetail.getReportRecord().getAct()
				.setFlag(request.getParameter("flag"));
		dutyDetail.getReportRecord().setCreateBy(
				dutyDetail.getReportRecord().getUser());
		dutyDetail.getReportRecord().getAct().setComment(comment);
		recordService.saveAudit(dutyDetail);
		return "redirect:" + adminPath + "/sys/task/todolist";
	}

	@RequiresPermissions("hr:dutyDetail:import")
	@RequestMapping(value = "upload")
	public String upload(DutyDetail dutyDetail, Model model) {
		/* 将上报值班信息绑定到model中 */
		model.addAttribute("dutyDetail", dutyDetail);
		/* 返回form页面 */
		return "modules/hr/dutyDetailUpload";
	}

	/**
	 * 根据主键，查询到上报值班信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param reportedWorkloade
	 *            上报值班上报值班entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回上报值班编辑页面
	 */
	@RequiresPermissions({ "hr:dutyDetail:create", "hr:dutyDetail:update" })
	@RequestMapping(value = "form")
	public String form(DutyDetail dutyDetail, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		ReportRecord rr = recordService.get(dutyDetail.getReportRecord()
				.getId());
		request.getSession().setAttribute("reportRecord.id", rr.getId());
		rr.setAct(new Act());
		rr.getAct().setProcInsId(
				dutyDetail.getReportRecord().getProcessInstanceId());
		dutyDetail.setReportRecord(rr);
		dutyDetail
				.getReportRecord()
				.getAct()
				.setProcInsId(
						dutyDetail.getReportRecord().getProcessInstanceId());
		List<DutyDetail> list = dutyDetailService.findList(dutyDetail);
		List<String> userIdList = Lists.newArrayList();
		for (DutyDetail temp : list) {
			if (!userIdList.contains(temp.getUser().getId())) {
				userIdList.add(temp.getUser().getId());
			}
		}
		List<User> userList = Lists.newArrayList();
		for (String temp : userIdList) {
			userList.add(UserUtils.get(temp));
		}
		List<DutyDetail> dutyList = Lists.newArrayList();
		/* 通过for循环设置用户各项信息 */
		for (User data : userList) {
			DutyDetail entity = new DutyDetail();
			User user = new User();
			user.setId(data.getId());
			user.setName(data.getName());
			user.setLoginName(data.getLoginName());
			user.setOffice(data.getOffice());
			entity.setUser(user);
			for (DutyDetail tempData : list) {
				if (entity.getUser().getId().equals(tempData.getUser().getId())) {
					if ("1".equals(tempData.getDutyType())) {
						entity.setNoonDutyDays(tempData.getDutyDays());
					} else if ("2".equals(tempData.getDutyType())) {
						entity.setNightDutyDays(tempData.getDutyDays());
					} else if ("3".equals(tempData.getDutyType())) {
						entity.setWeekendsDutyDays(tempData.getDutyDays());
					} else if ("4".equals(tempData.getDutyType())) {
						entity.setHolidayDutyDays(tempData.getDutyDays());
					}
				}
			}

			dutyList.add(entity);
		}

		/* 将list中的信息保存到对应的模型中 */
		model.addAttribute("list", dutyList);
		model.addAttribute("dutyDetail", dutyDetail);
		/* 返回list页面 */
		return "modules/hr/dutyDetailList";
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
	@RequestMapping(value = "export")
	public void exportFile(DutyDetail dutyDetail, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "上报值班信息" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			Object reportId = request.getSession().getAttribute(
					"reportRecord.id");

			List list = Lists.newArrayList();
			if (reportId == null) {
				Page<SalEmpView> page = new Page<SalEmpView>();
				page = dutyDetailService.findSalEmpView(page, new SalEmpView());
				list = page.getList();
			} else {
				dutyDetail.getReportRecord().setId(reportId.toString());
				List<DutyDetail> tempList = dutyDetailService
						.findList(dutyDetail);
				for (int i = 0; i < tempList.size(); i += 4) {
					DutyDetail tempData = tempList.get(i);
					tempData.setNoonDutyDays(tempList.get(i).getDutyDays());
					tempData.setNightDutyDays(tempList.get(i + 1).getDutyDays());
					tempData.setWeekendsDutyDays(tempList.get(i + 2)
							.getDutyDays());
					tempData.setHolidayDutyDays(tempList.get(i + 3)
							.getDutyDays());
					list.add(tempData);
				}
			}

			new ExportExcel("上报值班信息", DutyDetail.class).setDataList(list)
					.write(response, fileName).dispose();
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出失败！失败信息：" + e.getMessage());
		}

	}

	/**
	 * 导入用户数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("hr:dutyDetail:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath
					+ "/hr/reportPerformance/list?repage";
		}
		try {
			if (file.getSize()==0) {
				errorMessages.add(0, "数据验证失败");
                errorMessages.add("未放入模板文件！");
                addMessage(model);
                errorMessages.removeAll(errorMessages);
                return "modules/hr/dutyDetailUpload"; 
			}
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<DutyDetail> list = ei.getDataList(DutyDetail.class);

			for (DutyDetail data : list) {
				User user = UserUtils.getByLoginName(data.getLoginName());
				user.setOffice(data.getOffice());
				data.setUser(user);
			}

			DutyDetail dutyDetail = new DutyDetail();

			ReportRecord record = new ReportRecord();
			record.setId(request.getSession().getAttribute("reportRecord.id")
					.toString());
			record = recordService.get(record);
			dutyDetail.setReportRecord(record);

			/* page信息保存到对应的模型中 */
			model.addAttribute("list", list);
			model.addAttribute("reportRecord", record);
			model.addAttribute("dutyDetail", dutyDetail);

		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息：" + e.getMessage());
		}
		return "modules/hr/dutyDetailList";
	}

}
