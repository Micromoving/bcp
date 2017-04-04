package cn.micromoving.bcp.modules.hr.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import cn.micromoving.bcp.modules.hr.entity.EmpPerformanceLevel;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.service.EmpPerformanceLevelService;
import cn.micromoving.bcp.modules.hr.service.ReportRecordService;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "${adminPath}/hr/empPerformanceLevel")
public class EmpPerformanceLevelController extends BaseController {

	@Autowired
	private EmpPerformanceLevelService empPerformanceLevelService;

	@Autowired
	private ReportRecordService recordService;

	/**
	 * 根据主键， 取得辅导员绩效表记录。
	 * 
	 * @param id
	 *            primary key
	 * @return EmpPerformanceLevelService entity
	 */
	@ModelAttribute
	public EmpPerformanceLevel get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的辅导员绩效表记录。否则创建一个新的辅导员绩效表对象。 */
		if (StringUtils.isNotBlank(id)) {
			return empPerformanceLevelService.get(id);
		} else {
			return new EmpPerformanceLevel();
		}
	}

	/**
	 * 添加、更新辅导员绩效标准记录。操作成功后，转至列表页面。
	 * 
	 * @param empPerformanceLevelService
	 *            , Model model) { return null; 辅导员绩效标准entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 * @throws ParseException 
	 */
	@RequiresPermissions({ "hr:empPerformanceLevel:create",
			"hr:empPerformanceLevel:update" })
	@RequestMapping(value = "batchSave")
	public String batchSave(EmpPerformanceLevel empPerformanceLevel,
			Model model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) throws ParseException {
		if(!StringUtils.isBlank(empPerformanceLevel.getStartDate()) && !StringUtils.isBlank(empPerformanceLevel.getEndDate())){
			SimpleDateFormat sim = new SimpleDateFormat("yyyyMM");
			Date start = sim.parse(empPerformanceLevel.getStartDate());
			Date end = sim.parse(empPerformanceLevel.getEndDate());
			if(!timeCompare(start,"<",end)){
				errorMessages.add(0, "数据验证失败");
				errorMessages.add("开始时间晚于结束时间！");
				addMessage(redirectAttributes);
				errorMessages.removeAll(errorMessages);
				return "redirect:" + adminPath
						+ "/hr/empPerformanceLevel/list?dataClassification=4";
			}
		}else{
			errorMessages.add(0, "数据验证失败");
			errorMessages.add("生效日期年月不能为空!");
			addMessage(redirectAttributes);
			errorMessages.removeAll(errorMessages);
			return "redirect:" + adminPath
					+ "/hr/empPerformanceLevel/list?dataClassification=4";
		}
		/* 判断当前用户编号和年份是否具备编辑条件 */
//		if (org.apache.commons.lang3.StringUtils.isEmpty(empPerformanceLevel
//				.getId())
//				&& StringUtils.isBlank(empPerformanceLevel.getDataState())
//				&& empPerformanceLevelService
//						.existEmpPerformanceLevel(empPerformanceLevel
//								.getReportRecord())) {
//			addMessage(model, "同一年月不可重复上报");
//			return "modules/hr/empPerformanceLevelList";
//		} else {
			if (StringUtils.isBlank(empPerformanceLevel.getReportRecord()
					.getYearMonth())) {
				errorMessages.add(0, "数据验证失败");
				errorMessages.add("上报年月不能为空");
				addMessage(redirectAttributes);
				errorMessages.removeAll(errorMessages);
				return "redirect:" + adminPath
						+ "/hr/empPerformanceLevel/list?dataClassification=4";
			}
			if(empPerformanceLevelService.ccTime(empPerformanceLevel)){
				errorMessages.add(0, "数据验证失败");
				errorMessages.add("历史记录已有该年的记录！");
				addMessage(redirectAttributes);
				errorMessages.removeAll(errorMessages);
				return "redirect:" + adminPath
						+ "/hr/empPerformanceLevel/list?dataClassification=4";
			}
			empPerformanceLevelService.batchSave(empPerformanceLevel);
//		}
		if (empPerformanceLevel.getDataState().equals("2")) {
			try {
				String[] str = { "department_leader", "personnel_clerk",
						"academic_leadership" };
				recordService.save(empPerformanceLevel.getReportRecord(),
						ActUtils.PD_HR_PERFORMANCE, str, "performanceuser");
				addMessage(redirectAttributes, "操作成功，数据已经提交。");
			} catch (Exception e) {
				logger.error("启动流程失败：", e);
				addMessage(redirectAttributes, "系统内部错误！");
			}
		}
		/* 重定向list页面 */
		return "redirect:" + adminPath
				+ "/hr/reportRecord/list?dataClassification=4";
	}

	/**
	 * 根据主键，查询到上报绩效档信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param empPerformanceLevel
	 *            上报绩效档上报绩效档entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回上报绩效档编辑页面
	 */
	@RequiresPermissions({ "hr:empPerformanceLevel:create",
			"hr:empPerformanceLevel:update" })
	@RequestMapping(value = "form")
	public String form(EmpPerformanceLevel empPerformanceLevel,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		/* 定义一个上报id，从empPerformanceLevel中获取reportRecord中的id并赋值 */
		String reportId = empPerformanceLevel.getReportRecord().getId();
		/* 定义一个ReportRecord类型的data，值为recordService中通过reportId查到的值 */
		ReportRecord data = recordService.get(reportId);
		request.getSession().setAttribute("reportRecord.id", reportId);
		data.setAct(new Act());
		data.getAct().setProcInsId(data.getProcessInstanceId());
		empPerformanceLevel.setReportRecord(data);
		List<EmpPerformanceLevel> list = empPerformanceLevelService
				.findList(empPerformanceLevel);
		empPerformanceLevel.setStartDate(list.get(0).getStartDate());
		empPerformanceLevel.setEndDate(list.get(0).getEndDate());

		List<String> dataIdList = Lists.newArrayList();

		List<String> performanceLevelList = Lists.newArrayList();

		List<String> employeeIdList = Lists.newArrayList();
		for (EmpPerformanceLevel entity : list) {
			dataIdList.add(entity.getId());
			performanceLevelList.add(entity.getPerformanceLevel());
			employeeIdList.add(entity.getUser().getId());
		}
		empPerformanceLevel.setPerformanceLevelList(performanceLevelList);
		empPerformanceLevel.setEmployeeIdList(employeeIdList);
		/* 将上报绩效档信息绑定到model中 */
		model.addAttribute("empPerformanceLevel", empPerformanceLevel);

		model.addAttribute("list", list);

		/* 返回list页面 */
		return "modules/hr/empPerformanceLevelList";
	}

	/**
	 * 查询用户的全部教师绩效等级信息（分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param empPerformanceLevel
	 *            教师绩效等级
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部教师绩效等级信息（分页）
	 */
	@RequiresPermissions("hr:empPerformanceLevel:search")
	@RequestMapping(value = "list")
	public String list(EmpPerformanceLevel empPerformanceLevel,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		/* 通过empPerformanceLevelService.findPost()方法将empPerformanceLevel信息进行分页 */
		SalEmpView salEmpView = new SalEmpView();
		salEmpView.setPostType("1");
		Page<SalEmpView> page = empPerformanceLevelService.findSalEmpView(
				new Page<SalEmpView>(request, response), salEmpView);
		List<EmpPerformanceLevel> reportList = Lists.newArrayList();

		for (SalEmpView data : page.getList()) {
			EmpPerformanceLevel entity = new EmpPerformanceLevel();
			User user = new User();
			user.setId(data.getId());
			user.setName(data.getName());
			user.setLoginName(data.getLoginName());
			user.setOffice(data.getOffice());
			entity.setUser(user);
			reportList.add(entity);
		}
		/* page信息保存到对应的模型中 */
		model.addAttribute("list", reportList);
		model.addAttribute("empPerformanceLevel", empPerformanceLevel);
		/* 返回list页面 */
		return "modules/hr/empPerformanceLevelList";
	}

	/**
	 * 
	 * @param empPerformanceLevel
	 * @param model
	 * @return
	 */
	@RequiresPermissions("hr:empPerformanceLevel:import")
	@RequestMapping(value = "upload")
	public String upload(EmpPerformanceLevel empPerformanceLevel, Model model) {
		/* 将上报绩效档信息绑定到model中 */
		model.addAttribute("empPerformanceLevel", empPerformanceLevel);
		/* 返回form页面 */
		return "modules/hr/empPerformanceLevelUpload";
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
	public void exportFile(EmpPerformanceLevel empPerformanceLevel,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "上报绩效档信息" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			Object reportId = request.getSession().getAttribute(
					"reportRecord.id");

			List list = Lists.newArrayList();
			if (reportId == null) {
				Page<SalEmpView> page = new Page<SalEmpView>();
				page = empPerformanceLevelService.findSalEmpView(page,
						new SalEmpView());
				list = page.getList();
			} else {
				empPerformanceLevel.getReportRecord()
						.setId(reportId.toString());
				list = empPerformanceLevelService.findList(empPerformanceLevel);
			}

			new ExportExcel("上报绩效档信息", EmpPerformanceLevel.class)
					.setDataList(list).write(response, fileName).dispose();
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
                return "modules/hr/empPerformanceLevelUpload";
			}
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<EmpPerformanceLevel> list = ei
					.getDataList(EmpPerformanceLevel.class);

			for (EmpPerformanceLevel data : list) {

				User user = UserUtils.getByLoginName(data.getLoginName());

				user.setOffice(data.getOffice());
				data.setUser(user);

			}

			EmpPerformanceLevel empPerformanceLevel = new EmpPerformanceLevel();

			ReportRecord record = new ReportRecord();
			record.setId(request.getSession().getAttribute("reportRecord.id")
					.toString());
			record = recordService.get(record);
			empPerformanceLevel.setReportRecord(record);

			/* page信息保存到对应的模型中 */
			model.addAttribute("list", list);
			model.addAttribute("reportRecord", record);
			model.addAttribute("empPerformanceLevel", empPerformanceLevel);

		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息：" + e.getMessage());
			e.printStackTrace();
			return "modules/hr/empPerformanceLevelUpload";
		}
		return "modules/hr/empPerformanceLevelList";
	}

}
