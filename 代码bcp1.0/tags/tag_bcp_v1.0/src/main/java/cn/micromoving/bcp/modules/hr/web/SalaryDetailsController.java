package cn.micromoving.bcp.modules.hr.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.NumberUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.dao.SalaryInstanceTaskDao;
import cn.micromoving.bcp.modules.hr.entity.SalaryDetails;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstance;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstanceTask;
import cn.micromoving.bcp.modules.hr.service.SalaryDetailsService;
import cn.micromoving.bcp.modules.hr.service.SalaryInstanceService;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

/**
 * 工资查询Controller
 * 
 * @author shihengji
 */

@Controller
@RequestMapping(value = "${adminPath}/hr/salaryDetails")
public class SalaryDetailsController extends BaseController {

    @Autowired
    private SalaryDetailsService salarydetailsService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private SalaryInstanceTaskDao salaryInstanceTaskDao;

    @Autowired
    private SalaryInstanceService salaryInstanceService;

    /**
     * 根据主键， 取得工资查询。
     * 
     * @param id primary key
     * @return salaryDetails entity
     */
    @ModelAttribute
    public SalaryDetails get(@RequestParam(required = false)
    String id) {
        /* 判断id是否为空，如果有值，调用service来取得id对应的工资查询表。否则创建一个新的工资查询表对象。 */
        SalaryDetails result = new SalaryDetails();
        if (StringUtils.isNotBlank(id)) {
            result.setId(id);
            return salarydetailsService.get(result);
        } else {
            return result;
        }
    }

    /**
     * 根据主键，查询到工资查询表信息，将此信息绑定到model中，在JSP页面中可以读取。
     * 
     * @param salarydetails 工资查询entity，传递数据。
     * @param model
     * @return
     */
    @RequestMapping(value = "form")
    public String form(SalaryDetails salarydetails, Model model) {
        /* 获取工资查询信息绑定到model中 */
        model.addAttribute("salarydetails", salarydetails);
        /* 返回form页面 */
        return "modules/hr/salaryDetails";

    }

    /**
     * 查询用户的全部工资查询信息（分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
     * 
     * @param salarydetails 工资查询
     * @param request http请求
     * @param response http 响应
     * @param model 视图模型
     * @return 用户的全部工资查询信息（不分页）
     */
    @RequestMapping(value = { "list", "" })
    public String list(SalaryDetails salarydetails, SalaryInstanceTask salaryInstanceTask, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	salarydetails.getSalaryInstance().setDataState("3");
        /* 通过salarydetailsService.findemploy()方法将SalaryDetails信息进行分页 */
        Page<SalaryDetails> page = salarydetailsService.findSalaryDetails(new Page<SalaryDetails>(request, response),
                salarydetails);
        
        SalaryDetails sumData = new SalaryDetails();
		for (SalaryDetails tempData : page.getList()) {
			/* 职级工资 */
			sumData.setProfessionalLevelSalary(sumData
					.getProfessionalLevelSalary()
					+ tempData.getProfessionalLevelSalary());

			/* 岗位工资 */
			sumData.setPostSalary(sumData.getPostSalary()
					+ tempData.getPostSalary());
			/* 岗位津贴 */
			sumData.setPostSubside(sumData.getPostSubside()
					+ tempData.getPostSubside());
			/* 新增补贴 */
			sumData.setNewSubsidies(sumData.getNewSubsidies()
					+ tempData.getNewSubsidies());
			/* 院龄补贴 */
			sumData.setSchoolAgeSubside(sumData.getSchoolAgeSubside()
					+ tempData.getSchoolAgeSubside());

			/* 职务补助 */
			sumData.setPositionSubsidies(sumData.getPositionSubsidies()
					+ tempData.getPositionSubsidies());
			/* 卫生费 */
			sumData.setHealthCosts(sumData.getHealthCosts()
					+ tempData.getHealthCosts());
			/* 独子费 */
			sumData.setTheOnlyFee(sumData.getTheOnlyFee()
					+ tempData.getTheOnlyFee());
			/* 班主任费 */
			sumData.setTeacherChargeFee(sumData.getTeacherChargeFee()
					+ tempData.getTeacherChargeFee());
			/* 课酬金 */
			sumData.setClassFee(sumData.getClassFee() + tempData.getClassFee());
			/* 补房基金 */
			sumData.setFillHousingFund(sumData.getFillHousingFund()
					+ tempData.getFillHousingFund());
			/* 扣税金 */
			sumData.setWithholdTaxes(sumData.getWithholdTaxes()
					+ tempData.getWithholdTaxes());
			/* 扣房积金a */
			sumData.setBuckleRoomA(sumData.getBuckleRoomA()
					+ tempData.getBuckleRoomA());
			/* 扣房积金b */
			sumData.setBuckleRoomB(sumData.getBuckleRoomB()
					+ tempData.getBuckleRoomB());
			/* 扣医保 */
			sumData.setBuckleHealthCare(sumData.getBuckleHealthCare()
					+ tempData.getBuckleHealthCare());
			/* 扣失保 */
			sumData.setBuckleUnemploymentInsurance(sumData
					.getBuckleUnemploymentInsurance()
					+ tempData.getBuckleUnemploymentInsurance());
			/* 扣养老 */
			sumData.setBuckleEndowmentInsurance(sumData
					.getBuckleEndowmentInsurance()
					+ tempData.getBuckleEndowmentInsurance());
			/* 补缴社保 */
			sumData.setPaymentSocialSecurity(sumData.getPaymentSocialSecurity()
					+ tempData.getPaymentSocialSecurity());
			/* 扣房租 */
			sumData.setBuckleRent(sumData.getBuckleRent()
					+ tempData.getBuckleRent());
			/* 扣借款 */
			sumData.setBuckleBorrowing(sumData.getBuckleBorrowing()
					+ tempData.getBuckleBorrowing());
			/* 扣缺勤 */
			sumData.setBuckleAbsenteeism(sumData.getBuckleAbsenteeism()
					+ tempData.getBuckleAbsenteeism());

			/* 扣取暖 */
			sumData.setBuckleWarm(sumData.getBuckleWarm()
					+ tempData.getBuckleWarm());
			/* 扣多发 */
			sumData.setBuckleExtraWages(sumData.getBuckleExtraWages()
					+ tempData.getBuckleExtraWages());
			/* 扣职业年金 */
			sumData.setBuckOccupationalAnnuity(sumData
					.getBuckOccupationalAnnuity()
					+ tempData.getBuckOccupationalAnnuity());
			/* 扣生育 */
			sumData.setBuckMaternityInsurance(sumData
					.getBuckMaternityInsurance()
					+ tempData.getBuckMaternityInsurance());
			/* 扣工伤 */
			sumData.setBuckEmpInjuryInsurance(sumData
					.getBuckEmpInjuryInsurance()
					+ tempData.getBuckEmpInjuryInsurance());
			/* 扣公务员医疗补助 */
			sumData.setBuckgongyi(sumData.getBuckgongyi()
					+ tempData.getBuckgongyi());
			/* 值班 */
			sumData.setDuty(sumData.getDuty() + tempData.getDuty());
			/* 合计 */
			sumData.setTotal(sumData.getTotal() + tempData.getTotal());
			/* 备用1 */
			sumData.setDummy1(sumData.getDummy1() + tempData.getDummy1());
			/* 备用2 */
			sumData.setDummy2(sumData.getDummy2() + tempData.getDummy2());
			/* 备用3 */
			sumData.setDummy3(sumData.getDummy3() + tempData.getDummy3());
			/* 备用4 */
			sumData.setDummy4(sumData.getDummy4() + tempData.getDummy4());
			/* 备用5 */
			sumData.setDummy5(sumData.getDummy5() + tempData.getDummy5());
			/* 备用6 */
			sumData.setDummy6(sumData.getDummy6() + tempData.getDummy6());
			/* 备用7 */
			sumData.setDummy7(sumData.getDummy7() + tempData.getDummy7());
			/* 备用8 */
			sumData.setDummy8(sumData.getDummy8() + tempData.getDummy8());
			/* 备用9 */
			sumData.setDummy9(sumData.getDummy9() + tempData.getDummy9());
			/* 备用10 */
			sumData.setDummy10(sumData.getDummy10() + tempData.getDummy10());
			/* 备用11 */
			sumData.setDummy11(sumData.getDummy11() + tempData.getDummy11());
			/* 备用12 */
			sumData.setDummy12(sumData.getDummy12() + tempData.getDummy12());
			/* 备用13 */
			sumData.setDummy13(sumData.getDummy13() + tempData.getDummy13());
			/* 备用14 */
			sumData.setDummy14(sumData.getDummy14() + tempData.getDummy14());
			/* 备用15 */
			sumData.setDummy15(sumData.getDummy15() + tempData.getDummy15());
			/* 备用16 */
			sumData.setDummy16(sumData.getDummy16() + tempData.getDummy16());
			/* 备用17 */
			sumData.setDummy17(sumData.getDummy17() + tempData.getDummy17());
			/* 备用18 */
			sumData.setDummy18(sumData.getDummy18() + tempData.getDummy18());
			/* 备用19 */
			sumData.setDummy19(sumData.getDummy19() + tempData.getDummy19());
			/* 备用20 */
			sumData.setDummy20(sumData.getDummy20() + tempData.getDummy20());
			/* 绩效工资 */
			sumData.setPerformanceSalary(sumData.getPerformanceSalary()
					+ tempData.getPerformanceSalary());
			/* 年终绩效 */

			sumData.setYearEndPerformance(sumData.getYearEndPerformance()
					+ tempData.getYearEndPerformance());
			/* 精神文明奖1 */
			sumData.setSpiritualCivilization1(sumData
					.getSpiritualCivilization1()
					+ tempData.getSpiritualCivilization1());
			/* 精神文明奖2 */
			sumData.setSpiritualCivilization2(sumData
					.getSpiritualCivilization2()
					+ tempData.getSpiritualCivilization2());
			/* 精神文明奖3 */
			sumData.setSpiritualCivilization3(sumData
					.getSpiritualCivilization3()
					+ tempData.getSpiritualCivilization3());
			/* 取暖费用 */
			sumData.setHeatingCosts(sumData.getHeatingCosts()
					+ tempData.getHeatingCosts());
			/* 扣生保 */
			sumData.setBirthInsurance(sumData.getBirthInsurance()
					+ tempData.getBirthInsurance());

			/* 扣除工资 */
			sumData.setBuckleWages(sumData.getBuckleWages()
					+ tempData.getBuckleWages());
			/* 应发工资 */
			sumData.setInitialWages(sumData.getInitialWages()
					+ tempData.getInitialWages());
		}
        List<SalaryInstance> list = salaryInstanceService.findList(new SalaryInstance());
        for (SalaryInstance si : list) {
            salaryInstanceTask.setSalaryInstance(new SalaryInstance());
            salaryInstanceTask.getSalaryInstance().setId(si.getId());
            List<SalaryInstanceTask> taskList = salaryInstanceTaskDao.findList(salaryInstanceTask);
            model.addAttribute("taskList", taskList);
        }

        model.addAttribute("salaryDetails", salarydetails);

        model.addAttribute("salaryList", page.getList());
        model.addAttribute("page", page);
        model.addAttribute("sumData", sumData);

        /* 返回list页面 */
        return "modules/hr/salaryDetailsList";
    }

    /**
     * 查询用户的全部工资查询信息（分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
     * 
     * @param salarydetails 工资查询
     * @param request http请求
     * @param response http 响应
     * @param model 视图模型
     * @return 用户的全部工资查询信息（不分页）
     */
    @RequestMapping(value = "selfList")
    public String selflist(SalaryDetails salarydetails, SalaryInstanceTask salaryInstanceTask,
            HttpServletRequest request, HttpServletResponse response, Model model) {

        String self = UserUtils.getUser().getId();
        salarydetails.getUser().setId(self);
        return this.list(salarydetails, salaryInstanceTask, request, response, model);
    }

    /**
     * 添加、更新工资查询记录。操作成功后，转至列表页面。
     * 
     * @param salarydetails 工资查询entity
     * @param request http请求
     * @param model 视图模型
     * @param redirectAttributes 重定向属性集
     * @return
     */
    @RequestMapping(value = "save")
    public void save(SalaryDetails salarydetails, Model model, HttpServletResponse response) {

        /* 保存工资查询信息 */
        salarydetailsService.save(salarydetails);
        /* 重定向显示消息 */
        addMessage(model, "保存成功");
        /* 重定向到list页面 */

    }

    /**
     * 删除工资查询表。操作成功后，转至列表页面。
     * 
     * @param salarydetails 工资查询entity
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     */
    @RequestMapping(value = "delete")
    public String delete(SalaryDetails salarydetails, RedirectAttributes redirectAttributes) {
        /* 删除工资查询信息 */
        salarydetailsService.delete(salarydetails);
        /* 重定向显示消息 */
        addMessage(redirectAttributes, "删除成功");
        /* 重定向到list页面 */
        return "redirect:" + adminPath + "/hr/salaryDetails/list?repage";
    }

    @RequestMapping(value = "change")
    public void change(SalaryDetails salarydetails, HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String sum = NumberUtils.formatPlainCurrency(salarydetails.getTempSum().toString());
        String initialWages = NumberUtils.formatPlainCurrency(salarydetails.sumInitialWages().toString());
        String buckleWages = NumberUtils.formatPlainCurrency(salarydetails.sumBuckleWages().toString());
        response.getWriter().write("实发工资<br>"+sum+"<br>应发工资<br>"+initialWages+"<br>扣除工资<br>"+buckleWages);

    }
}
