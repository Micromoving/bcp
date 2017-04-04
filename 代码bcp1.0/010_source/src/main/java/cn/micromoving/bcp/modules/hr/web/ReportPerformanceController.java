package cn.micromoving.bcp.modules.hr.web;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.apache.shiro.authz.annotation.Logical;
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
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.entity.EmpPerformance2View;
import cn.micromoving.bcp.modules.hr.entity.EmpPerformance3View;
import cn.micromoving.bcp.modules.hr.entity.EmpPerformance4View;
import cn.micromoving.bcp.modules.hr.entity.ReportPerformance;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.service.EmpPerformance2ViewService;
import cn.micromoving.bcp.modules.hr.service.EmpPerformance3ViewService;
import cn.micromoving.bcp.modules.hr.service.EmpPerformance4ViewService;
import cn.micromoving.bcp.modules.hr.service.ReportPerformanceService;
import cn.micromoving.bcp.modules.hr.service.ReportRecordService;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "${adminPath}/hr/reportPerformance")
public class ReportPerformanceController extends BaseController {

	@Autowired
	private ReportPerformanceService reportPerformanceService;

	@Autowired
	private SalEmpViewDao salEmpViewDao;

	@Autowired
	private ReportRecordService recordService;

	@Autowired
	private EmpPerformance2ViewService empPerformance2ViewService;

	@Autowired
	private EmpPerformance3ViewService empPerformance3ViewService;

	@Autowired
	private EmpPerformance4ViewService empPerformance4ViewService;

	@Autowired
	private RepositoryService repositoryService;

	/**
	 * 根据主键， 取得工资信息。
	 * 
	 * @param id
	 *            primary key
	 * @return SalaryInstance entity
	 */
	@ModelAttribute
	public ReportPerformance get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return reportPerformanceService.get(id);
		} else {
			return new ReportPerformance();
		}
	}

	/**
	 * 查询工资信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param salaryInstance
	 *            工资
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部教育经历信息（不分页）
	 */
	@RequiresPermissions("hr:reportPerformance:search")
	@RequestMapping(value = { "list", "" })
	public String list(ReportPerformance reportPerformance,
			SalEmpView salEmpView, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		List<ReportPerformance> reportList = Lists.newArrayList();
		if ("2".equals(salEmpView.getPostType())) {
			List<EmpPerformance2View> list_2 = empPerformance2ViewService
					.findList(new EmpPerformance2View());
			for (int i = 0; i < list_2.size(); i++) {
				ReportPerformance entity = new ReportPerformance();
				User user = UserUtils.getByLoginName(list_2.get(i)
						.getLoginName());
				entity.setUser(user);
				entity.setStdSal(list_2.get(i).getStdSal());
				entity.setLatestSal(list_2.get(i).getLatestSal());
				reportList.add(entity);
			}
			/* 将信息保存到对应的模型中 */
			model.addAttribute("list", reportList);
			/* 返回list页面 */
			return "modules/hr/reportPerformance2List";
		} else if ("3".equals(salEmpView.getPostType())) {
			List<EmpPerformance3View> list_3 = empPerformance3ViewService
					.findList(new EmpPerformance3View());
			for (int i = 0; i < list_3.size(); i++) {
				ReportPerformance entity = new ReportPerformance();
				User user = UserUtils.getByLoginName(list_3.get(i)
						.getLoginName());
				entity.setUser(user);
				entity.setStdSal(list_3.get(i).getStdSal());
				entity.setLatestSal(list_3.get(i).getLatestSal());
				reportList.add(entity);
			}
			/* 将信息保存到对应的模型中 */
			model.addAttribute("list", reportList);
			/* 返回list页面 */
			return "modules/hr/reportPerformance3List";
		} else if ("4".equals(salEmpView.getPostType())) {
			List<EmpPerformance4View> list_4 = empPerformance4ViewService
					.findList(new EmpPerformance4View());
			Map<String, ReportPerformance> map = Maps.newHashMap();
			for (int i = 0; i < list_4.size(); i++) {
				ReportPerformance entity = new ReportPerformance();
				User user = UserUtils.getByLoginName(list_4.get(i)
						.getLoginName());
				entity.setLatestSal(list_4.get(i).getLatestSal());
				entity.setUser(user);
				if (!map.containsKey(list_4.get(i).getId())) {
					entity.setStdSal1(list_4.get(i).getStdSal());
					map.put(list_4.get(i).getId(), entity);
				} else {
					entity = map.get(list_4.get(i).getId());
					if ("2".equals(list_4.get(i).getPerformanceLevel())) {
						entity.setStdSal2(list_4.get(i).getStdSal());
					} else if ("3".equals(list_4.get(i).getPerformanceLevel())) {
						entity.setStdSal3(list_4.get(i).getStdSal());
					}
				}
			}
			for (Map.Entry<String, ReportPerformance> entry : map.entrySet()) {
				reportList.add(entry.getValue());
			}
			/* 将信息保存到对应的模型中 */
			model.addAttribute("list", reportList);
			return "modules/hr/reportPerformance4List";
		}

		return null;
	}

	/**
	 * 根据主键，查询到上报工作量信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param reportedWorkloade
	 *            上报工作量上报工作量entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回上报工作量编辑页面
	 */
	@RequiresPermissions(value={ "hr:reportPerformance:create",
			"hr:reportPerformance:update","hr:reportPerformance:audit" },logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(SalEmpView salEmpView,
			ReportPerformance reportPerformance, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String reportId = reportPerformance.getReportRecord().getId();
		request.getSession().setAttribute("reportRecord.id", reportId);
		ReportRecord data = recordService.get(reportId);
		reportPerformance.setReportRecord(data);

		List<ReportPerformance> list = reportPerformanceService
				.findList(reportPerformance);
		// if (salEmpView.getPostType() != null) {
		// for (int i = 0; i < list.size(); i++) {
		// String id = list.get(i).getUser().getId();
		// SalEmpView sev = salEmpViewDao.get(id);
		// if (!sev.getPostType().equals("4")) {
		// list.remove(i);
		// }
		// }
		// }
		if ("2".equals(salEmpView.getPostType())) {
			List<EmpPerformance2View> list_2 = empPerformance2ViewService
					.findList(new EmpPerformance2View());
			for (int i = 0; i < list_2.size(); i++) {
				if (i >= list.size()) {
					continue;
				}
				ReportPerformance entity = list.get(i);
				entity.setStdSal(list_2.get(i).getStdSal());
				entity.setLatestSal(list_2.get(i).getLatestSal());
			}
		} else if ("3".equals(salEmpView.getPostType())) {
			List<EmpPerformance3View> list_3 = empPerformance3ViewService
					.findList(new EmpPerformance3View());
			for (int i = 0; i < list_3.size(); i++) {
				if (i >= list.size()) {
					continue;
				}
				ReportPerformance entity = list.get(i);
				entity.setStdSal(list_3.get(i).getStdSal());
				entity.setLatestSal(list_3.get(i).getLatestSal());
			}
		} else if ("4".equals(salEmpView.getPostType())) {
			List<EmpPerformance4View> list_4 = empPerformance4ViewService
					.findList(new EmpPerformance4View());
			for (int i = 0; i < list.size(); i++) {
				ReportPerformance entity = list.get(i);
				entity.setLatestSal(list_4.get(i).getLatestSal());
				for (EmpPerformance4View empPerformance4View : list_4) {
					if (entity.getUser().getId()
							.equals(empPerformance4View.getId())
							&& "1".equals(empPerformance4View
									.getPerformanceLevel())) {
						entity.setStdSal1(empPerformance4View.getStdSal());
					} else if (entity.getUser().getId()
							.equals(empPerformance4View.getId())
							&& "2".equals(empPerformance4View
									.getPerformanceLevel())) {
						entity.setStdSal2(empPerformance4View.getStdSal());
					} else if (entity.getUser().getId()
							.equals(empPerformance4View.getId())
							&& "3".equals(empPerformance4View
									.getPerformanceLevel())) {
						entity.setStdSal3(empPerformance4View.getStdSal());
					}
				}
			}
		}

		List<String> dataIdList = Lists.newArrayList();

		List<Double> performanceSalaryList = Lists.newArrayList();

		List<String> employeeIdList = Lists.newArrayList();
		for (ReportPerformance entity : list) {
			dataIdList.add(entity.getId());
			performanceSalaryList.add(entity.getPerformanceSalary());
			employeeIdList.add(entity.getUser().getId());
		}

		/* 将上报工作量信息绑定到model中 */
		model.addAttribute("reportPerformance", reportPerformance);
		model.addAttribute("list", list);
		/* 返回list页面 */
		if ("2".equals(salEmpView.getPostType())) {

			return "modules/hr/reportPerformance2List";
		} else if ("4".equals(salEmpView.getPostType())) {

			return "modules/hr/reportPerformance4List";
		} else if ("3".equals(salEmpView.getPostType())) {

			return "modules/hr/reportPerformance3List";
		}
		return null;

	}

	/**
	 * 添加、更新上报工作量记录。操作成功后，转至列表页面。
	 * 
	 * @param reportedWorkloade
	 *            , Model model) { return null; 上报工作量entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 * @throws ParseException
	 */
	@RequiresPermissions({ "hr:reportPerformance:create",
			"hr:reportPerformance:update" })
	@RequestMapping(value = "batchSave")
	public String batchSave(ReportPerformance reportPerformance, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes)
			throws ParseException {

		// if (StringUtils.isBlank(reportPerformance.getReportRecord()
		// .getYearMonth())) {
		// errorMessages.add(0, "数据验证失败");
		// errorMessages.add("上报年月不能为空");
		// addMessage(redirectAttributes);
		// errorMessages.removeAll(errorMessages);
		// return "redirect:" + adminPath
		// + "/hr/reportPerformance/list?dataClassification=1";
		// } else if (reportPerformanceService.ccTime(reportPerformance)) {
		// errorMessages.add(0, "数据验证失败");
		// errorMessages.add("历史记录中已有该年月信息！");
		// addMessage(redirectAttributes);
		// errorMessages.removeAll(errorMessages);
		// return "redirect:" + adminPath
		// + "/hr/reportedWorkloade/list?dataClassification=1";
		// }
		/* 否则，调用batchSave批量保存方法进行保存 */
		reportPerformanceService.batchSave(reportPerformance);

		if ("5".equals(reportPerformance.getReportRecord()
				.getDataClassification())) {
			if ("2".equals(reportPerformance.getDataState())) {
				try {
					String[] str = { "department_secretary", "studepartment_leadership","personnel_clerk" };

					recordService.save(reportPerformance.getReportRecord(),
							ActUtils.PD_HR_PERFORMANCE_2, str, "performanceuser");
					addMessage(redirectAttributes, "操作成功，数据已经提交。");
				} catch (Exception e) {
					logger.error("启动流程失败：", e);
					addMessage(redirectAttributes, "系统内部错误！");
				}
			}

		} else if ("1".equals(reportPerformance.getReportRecord()
				.getDataClassification()) ||"6".equals(reportPerformance.getReportRecord()
						.getDataClassification())) {
			if (reportPerformance.getDataState().equals("2")) {
				try {
					String[] str = { "department_leader", "personnel_clerk" };

					recordService.save(reportPerformance.getReportRecord(),
							ActUtils.PD_HR_PERFORMANCE, str, "performanceuser");
					addMessage(redirectAttributes, "操作成功，数据已经提交。");
				} catch (Exception e) {
					logger.error("启动流程失败：", e);
					addMessage(redirectAttributes, "系统内部错误！");
				}
			}

		}
		/* 重定向list页面 */
		return "redirect:" + adminPath
				+ "/hr/reportRecord/list?dataClassification="
				+ reportPerformance.getReportRecord().getDataClassification();
	}

	/**
	 * 工单执行（完成任务）
	 * 
	 * @param reportedWorkloade
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("hr:reportPerformance:audit")
	@RequestMapping(value = "saveAudit")
	public String saveAudit(ReportPerformance reportPerformance,
			ReportRecord reportRecord, Model model, HttpServletRequest request) {
		String comment = reportPerformance.getReportRecord().getAct()
				.getComment();
		reportPerformance.setReportRecord(recordService.get(reportPerformance
				.getReportRecord().getId()));
		reportPerformance.getReportRecord().setCreateBy(
				reportPerformance.getReportRecord().getUser());
		reportPerformance.getReportRecord().getAct()
				.setFlag(request.getParameter("flag"));
		reportPerformance.getReportRecord().getAct().setComment(comment);
		recordService.saveAudit(reportPerformance, ActUtils.PD_HR_PERFORMANCE);
		return "redirect:" + adminPath + "/sys/task/todolist";
	}

	@RequiresPermissions("hr:reportPerformance:import")
	@RequestMapping(value = "upload")
	public String upload(ReportPerformance reportPerformance, Model model) {
		/* 将上报工作量信息绑定到model中 */
		model.addAttribute("reportPerformance", reportPerformance);
		/* 返回form页面 */
		return "modules/hr/reportPerformanceUpload";
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
	@RequiresPermissions("hr:reportPerformance:export")
	@RequestMapping(value = "export")
	public void exportFile(ReportPerformance reportPerformance,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "上报绩效信息" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			Object reportId = request.getSession().getAttribute(
					"reportRecord.id");

			List list = Lists.newArrayList();
			if (reportId == null) {
				Page<SalEmpView> page = new Page<SalEmpView>();
				SalEmpView data = new SalEmpView();
				/* 2:职员 */
				data.setPostType(request.getSession()
						.getAttribute("reportRecord.postType").toString());
				page = reportPerformanceService.findSalEmpView(page, data);
				list = page.getList();
			} else {
				reportPerformance.getReportRecord().setId(reportId.toString());
				list = reportPerformanceService.findList(reportPerformance);
			}

			new ExportExcel("上报绩效信息", ReportPerformance.class)
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
	@RequiresPermissions("hr:reportPerformance:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, Model model,
			SalEmpView salEmpView, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath
					+ "/hr/reportPerformance/list?repage";
		}
		try {
			if (file.getSize() == 0) {
				errorMessages.add(0, "数据验证失败");
				errorMessages.add("未放入模板文件！");
				addMessage(model);
				errorMessages.removeAll(errorMessages);
				return "modules/hr/reportPerformanceUpload";
			}
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<ReportPerformance> list = ei
					.getDataList(ReportPerformance.class);

			for (ReportPerformance data : list) {

				User user = UserUtils.getByLoginName(data.getLoginName());

				user.setOffice(data.getOffice());
				data.setUser(user);

			}

			ReportPerformance reportPerformance = new ReportPerformance();

			ReportRecord record = new ReportRecord();
			if (request.getSession().getAttribute("reportRecord.id") != null) {
				record.setId(request.getSession()
						.getAttribute("reportRecord.id").toString());
			}
			record = recordService.get(record);
			reportPerformance.setReportRecord(record);
			salEmpView.setId(list.get(0).getUser().getId());
			salEmpView = salEmpViewDao.get(salEmpView);
			/* page信息保存到对应的模型中 */
			model.addAttribute("reportRecord", record);
			model.addAttribute("reportPerformance", reportPerformance);

			if ("2".equals(salEmpView.getPostType())) {

				List<EmpPerformance2View> list_2 = empPerformance2ViewService
						.findList(new EmpPerformance2View());
				for (int i = 0; i < list_2.size(); i++) {
					ReportPerformance entity = list.get(i);
					User user = UserUtils.getByLoginName(list_2.get(i)
							.getLoginName());
					entity.setUser(user);
					entity.setStdSal(list_2.get(i).getStdSal());
					entity.setLatestSal(list_2.get(i).getLatestSal());
				}
				/* 将信息保存到对应的模型中 */
				model.addAttribute("list", list);
				/* 返回list页面 */
				return "modules/hr/reportPerformance2List";
			} else if ("4".equals(salEmpView.getPostType())) {
				List<EmpPerformance4View> list_4 = empPerformance4ViewService
						.findList(new EmpPerformance4View());
				Map<String, ReportPerformance> map = Maps.newHashMap();
				for (int i = 0; i < list_4.size(); i++) {
					ReportPerformance entity = new ReportPerformance();
					User user = UserUtils.getByLoginName(list_4.get(i)
							.getLoginName());
					entity.setLatestSal(list_4.get(i).getLatestSal());
					entity.setUser(user);
					if (!map.containsKey(list_4.get(i).getId())) {
						entity.setStdSal1(list_4.get(i).getStdSal());
						map.put(list_4.get(i).getId(), entity);
					} else {
						entity = map.get(list_4.get(i).getId());
						if (list_4.get(i).getPerformanceLevel().equals("2")) {
							entity.setStdSal2(list_4.get(i).getStdSal());
						} else if (list_4.get(i).getPerformanceLevel()
								.equals("3")) {
							entity.setStdSal3(list_4.get(i).getStdSal());
						}
					}
				}

				for (int i = 0; i < list.size(); i++) {
					ReportPerformance entity = map.get(list.get(i).getUser()
							.getId());
					list.get(i).setStdSal1(entity.getStdSal1());
					list.get(i).setStdSal2(entity.getStdSal2());
					list.get(i).setStdSal3(entity.getStdSal3());
				}
				/* 将信息保存到对应的模型中 */
				model.addAttribute("list", list);
				return "modules/hr/reportPerformance4List";
			}
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息：" + e.getMessage());
			e.printStackTrace();
			return "modules/hr/reportPerformanceUpload";
		}
		return "modules/hr/reportPerformanceUpload";
	}

}
