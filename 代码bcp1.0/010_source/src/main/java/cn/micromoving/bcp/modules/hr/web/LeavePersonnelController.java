package cn.micromoving.bcp.modules.hr.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.Attendance;
import cn.micromoving.bcp.modules.hr.entity.LeavePersonnel;
import cn.micromoving.bcp.modules.hr.entity.SalaryDetails;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstance;
import cn.micromoving.bcp.modules.hr.service.AttendanceService;
import cn.micromoving.bcp.modules.hr.service.SalaryDetailsService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Lists;

/**
 * 区域Controller
 * 
 * @author mujinyao
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/leavePersonnel")
public class LeavePersonnelController extends BaseController {

	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private SalaryDetailsService salaryDetailsService;

	@RequestMapping(value = { "list", " " })
	public String list(LeavePersonnel leavePersonnel,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		/* 获取工资实例Id */
		String salaryInstanceId = leavePersonnel.getSalaryInstanceId();

		List<Attendance> attendanceList = attendanceService
				.findList(new Attendance());
		List<LeavePersonnel> list = Lists.newArrayList();
		for (Attendance attendance : attendanceList) {

			leavePersonnel = new LeavePersonnel();
			/* 此处只查询对应月份请假记录 */
//			String[] sysdate = DateUtils.formatDate(new Date(), "yyyy/MM/dd")
//					.split("/");
			String[] sysdate = {"2016","11","01"};
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, Integer.parseInt(sysdate[0]));
			c.set(Calendar.MONTH, Integer.parseInt(sysdate[1]) - 3);
			c.set(Calendar.DATE, 1);

			if (c.getTimeInMillis() > attendance.getEndDate().getTime()) {
				continue;
			}

			boolean isNew = false;
			for (LeavePersonnel personnel : list) {
				if (attendance.getUser().getId()
						.equals(personnel.getUser().getId())) {
					if (attendance.getStartDate().getTime() == attendance
							.getEndDate().getTime()) {
						personnel.setLeaveDate(personnel.getLeaveDate()
								+ " / "
								+ DateUtils.formatDate("yyyy.MM.dd",
										attendance.getStartDate()));
					} else {
						personnel.setLeaveDate(personnel.getLeaveDate()
								+ " / "
								+ DateUtils.formatDate("yyyy.MM.dd",
										attendance.getStartDate())
								+ "-"
								+ DateUtils.formatDate("yyyy.MM.dd",
										attendance.getEndDate()));

					}
					isNew = true;
				}
			}
			if (isNew) {
				continue;
			}

			leavePersonnel.setUser(attendance.getUser());
			if (attendance.getOffice().getGrade().equals("3")) {
				attendance.setOffice(UserUtils.getOfficeById(attendance
						.getOffice().getParent().getId()));
			}
			leavePersonnel.setOffice(attendance.getOffice());
			leavePersonnel.setPostType(attendance.getPostType());
			leavePersonnel.setLeaveType(attendance.getLeaveType());
			if (attendance.getStartDate().getTime() == attendance.getEndDate()
					.getTime()) {
				leavePersonnel.setLeaveDate(DateUtils.formatDate("yyyy.MM.dd",
						attendance.getStartDate()));
			} else {
				leavePersonnel.setLeaveDate(DateUtils.formatDate("yyyy.MM.dd",
						attendance.getStartDate())
						+ "-"
						+ DateUtils.formatDate("yyyy.MM.dd",
								attendance.getEndDate()));

			}
			leavePersonnel.setReportDate(attendance.getReportDate());

			SalaryDetails sd = new SalaryDetails();
			sd.setSalaryInstance(new SalaryInstance());
			sd.getSalaryInstance().setId(salaryInstanceId);
			sd.setUser(leavePersonnel.getUser());
			sd = salaryDetailsService.get(sd);
			/* 设置值 */
			leavePersonnel.setBuckleAbsenteeism(sd.getBuckleAbsenteeism());

			list.add(leavePersonnel);
		}

		model.addAttribute("list", list);
		return "modules/hr/leavePersonnelList";
	}

	/**
	 * 
	 * 扣考勤
	 * 
	 * @param leavePersonnel
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(LeavePersonnel leavePersonnel, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		/* 获取工资实例ID */
		String salaryInstanceId = leavePersonnel.getSalaryInstanceId();

		List<String> employeeList = leavePersonnel.getEmployeeList();
		List<Double> buckleAbsenteeismList = leavePersonnel
				.getBuckleAbsenteeismList();
		for (int i = 0; i < employeeList.size(); i++) {
			SalaryDetails sd = new SalaryDetails();
			sd.setUser(UserUtils.get(employeeList.get(i)));
			sd.setSalaryInstance(new SalaryInstance());
			sd.getSalaryInstance().setId(salaryInstanceId);

			SalaryDetails salaryDetails = salaryDetailsService.get(sd);
			salaryDetails.setBuckleAbsenteeism(salaryDetails
					.getBuckleAbsenteeism() + buckleAbsenteeismList.get(i));
			salaryDetailsService.save(salaryDetails);
		}
		/* 重定向list页面 */
		return "redirect:" + adminPath
				+ "/hr/leavePersonnel/list?salaryInstanceId="
				+ salaryInstanceId + "&repage";
	}

}
