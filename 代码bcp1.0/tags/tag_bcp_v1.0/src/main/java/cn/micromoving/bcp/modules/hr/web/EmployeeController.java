package cn.micromoving.bcp.modules.hr.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import cn.micromoving.bcp.common.beanvalidator.BeanValidators;
import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.FileUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.ExportExcel;
import cn.micromoving.bcp.common.utils.excel.ImportExcel;
import cn.micromoving.bcp.common.utils.excel.fieldtype.RoleListType;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.dao.AssessmentDao;
import cn.micromoving.bcp.modules.hr.dao.EduDao;
import cn.micromoving.bcp.modules.hr.dao.EmployeeDao;
import cn.micromoving.bcp.modules.hr.dao.InsuranceRecordDao;
import cn.micromoving.bcp.modules.hr.dao.InsuredSituationDao;
import cn.micromoving.bcp.modules.hr.dao.PersonnelAgencyDao;
import cn.micromoving.bcp.modules.hr.dao.ProTechPositionDao;
import cn.micromoving.bcp.modules.hr.dao.RecruitmentDao;
import cn.micromoving.bcp.modules.hr.dao.TeacherQualificationDao;
import cn.micromoving.bcp.modules.hr.dao.WorkDao;
import cn.micromoving.bcp.modules.hr.entity.Assessment;
import cn.micromoving.bcp.modules.hr.entity.Attachment;
import cn.micromoving.bcp.modules.hr.entity.Award;
import cn.micromoving.bcp.modules.hr.entity.Certificate;
import cn.micromoving.bcp.modules.hr.entity.Edu;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.InsuranceRecord;
import cn.micromoving.bcp.modules.hr.entity.InsuranceRule;
import cn.micromoving.bcp.modules.hr.entity.InsuredSituation;
import cn.micromoving.bcp.modules.hr.entity.PersonnelAgency;
import cn.micromoving.bcp.modules.hr.entity.Practice;
import cn.micromoving.bcp.modules.hr.entity.ProTechPosition;
import cn.micromoving.bcp.modules.hr.entity.Recruitment;
import cn.micromoving.bcp.modules.hr.entity.TeacherQualification;
import cn.micromoving.bcp.modules.hr.entity.Title;
import cn.micromoving.bcp.modules.hr.entity.Train;
import cn.micromoving.bcp.modules.hr.entity.Work;
import cn.micromoving.bcp.modules.hr.service.AssessmentService;
import cn.micromoving.bcp.modules.hr.service.AttachmentService;
import cn.micromoving.bcp.modules.hr.service.AwardService;
import cn.micromoving.bcp.modules.hr.service.CertificateService;
import cn.micromoving.bcp.modules.hr.service.EduService;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.hr.service.InsuranceRecordService;
import cn.micromoving.bcp.modules.hr.service.InsuredSituationService;
import cn.micromoving.bcp.modules.hr.service.PersonnelAgencyService;
import cn.micromoving.bcp.modules.hr.service.PracticeService;
import cn.micromoving.bcp.modules.hr.service.ProTechPositionService;
import cn.micromoving.bcp.modules.hr.service.RecruitmentService;
import cn.micromoving.bcp.modules.hr.service.TeacherQualificationService;
import cn.micromoving.bcp.modules.hr.service.TitleService;
import cn.micromoving.bcp.modules.hr.service.TrainService;
import cn.micromoving.bcp.modules.hr.service.WorkService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.hr.utils.HrUtils;
//import cn.micromoving.bcp.modules.sys.entity.Edu;
import cn.micromoving.bcp.modules.sys.entity.Role;
import cn.micromoving.bcp.modules.sys.entity.User;
//import cn.micromoving.bcp.modules.sys.service.EduService;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.DictUtils;
import cn.micromoving.bcp.modules.sys.utils.SysUtils;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

/**
 * 教师用户表Controller
 * 
 * @author meng
 */

@Controller
@RequestMapping(value = "${adminPath}/hr/employee")
public class EmployeeController extends BaseController {
    @Autowired
    private SystemService systemService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private AwardService awardService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private PracticeService practiceService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private TeacherQualificationDao teacherQualificationDao;

    @Autowired
    private ProTechPositionDao proTechPositionDao;

    @Autowired
    private RecruitmentDao recruitmentDao;

    @Autowired
    private InsuredSituationDao insuredSituationDao;

    @Autowired
    private PersonnelAgencyDao personnelAgencyDao;

    @Autowired
    private AssessmentDao assessmentDao;

    @Autowired
    private EduDao eduDao;

    @Autowired
    private WorkDao workDao;

    @Autowired
    private InsuranceRecordDao insuranceRecordDao;

    @Autowired
    private TeacherQualificationService teacherQualificationService;

    @Autowired
    private ProTechPositionService proTechPositionService;

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private InsuredSituationService insuredSituationService;

    @Autowired
    private PersonnelAgencyService personnelAgencyService;

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private EduService eduService;

    @Autowired
    private WorkService workService;

    @Autowired
    private InsuranceRecordService insuranceRecordService;

    public static final String IMGROOT = "uploads" + File.separator;

    public static final double DEFAULT_WEIGHT = 390;

    public static final double DEFAULT_HEIGHT = 570;

    /**
     * 根据主键， 取得教师用户记录。
     * 
     * @param id primary key
     * @return employ entity
     */
    @ModelAttribute
    public Employee get(@RequestParam(required = false) String id) {
        /* 创建一个新的employ对象 */
        /* 判断id是否为空，如果有值，调用service来取得id对应的教育经历记录。否则创建一个新的教育经历对象。 */
        Employee employee = new Employee();
        /* 判断id是否为空，如果有值，调用service来取得id对应的教育经历记录。否则创建一个新的教育经历对象。 */
        if (StringUtils.isNotBlank(id)) {

            employee = employeeService.get(id);
        }
        /* 返回一个employ对象 */
        return employee;
    }

    /**
     * 根据主键，查询到教师用户信息，将此信息绑定到model中，在JSP页面中可以读取。
     * 
     * @param edu 教师用户entity，传递数据。
     * @param model 视图模型
     * @return 返回教师用户编辑页面
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "form")
    public String form(Employee employee, HttpServletRequest request, Model model) {
        /* 根据用户ID，取得用户信息，职员信息。绑定到session 中。 */
        /* 如果没有传入ID，则从session中取得用户信息。 */
        if (null == employee.getId()) {
            /* 获取当前用户proTechPosition信息 */
            User user = (User) request.getSession().getAttribute(HrConstant.USER);
            employee = (Employee) request.getSession().getAttribute(HrConstant.EMPLOYEE);
            model.addAttribute("user", user);
            model.addAttribute("employee", employee);
        } else {
            User user = systemService.getUser(employee.getId());
            employee = systemService.getEmp(employee.getId());
            request.getSession().setAttribute(HrConstant.EMPLOYEE, employee);
            request.getSession().setAttribute(HrConstant.USER, user);
            model.addAttribute("user", user);
            model.addAttribute("employee", employee);
        }

        request.getSession().setAttribute("menuIds", "400");
        /* 将employ对象的信息保存到employ模型中 */
        model.addAttribute("employee", employee);
        /* 返回个人简历页面 */
        return "modules/hr/profile";
    }

    /**
     * 根据主键，查询到教师用户信息，将此信息绑定到model中，在JSP页面中可以读取。
     * 
     * @param edu 教师用户entity，传递数据。
     * @param model 视图模型
     * @return 返回教师用户编辑页面
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "detail")
    public String detail(Employee employee, HttpServletRequest request, Model model) {
        /* 获取到当前employ用户的Id */
        String userId = employee.getId();
        /* 通过用户Id获取到当前用户 */
        User employeeUser = new User(userId);
        /* 此处将个人所有信息都保存到model模型中是为了在profile(个人简介)页面中获取 */
        /* 保存到模型中是为了在界面中显示 */
        /* 通过sercive将当前用户保存到user模型中 */
        model.addAttribute("user", systemService.getUser(userId));

        /* 获取对应的Entity对象 */
        Work workEntity = new Work();
        /* 将该对象与当前用户进行绑定 */
        workEntity.setUser(employeeUser);
        /* 通过sercive将当前用户保存到workList模型中 */
        model.addAttribute("workList", workService.findList(workEntity));

        /* 以下代码性质与上述相同 */

        Edu eduEntity = new Edu();
        eduEntity.setUser(employeeUser);
        model.addAttribute("eduList", eduService.findList(eduEntity));
        
        ProTechPosition proTechPositionEntity = new ProTechPosition();
        proTechPositionEntity.setUser(employeeUser);
        model.addAttribute("proTechPositionList", proTechPositionService.findList(proTechPositionEntity));

        Recruitment recruitmentEntity = new Recruitment();
        recruitmentEntity.setUser(employeeUser);
        model.addAttribute("recruitmentList", recruitmentService.findList(recruitmentEntity));
        
        Assessment assessmentEntity = new Assessment();
        assessmentEntity.setUser(employeeUser);
        model.addAttribute("assessmentList", assessmentService.findList(assessmentEntity));

        model.addAttribute("employee", employee);
        /* 返回个人所有资料的页面 */
        return "modules/hr/employeeDetail";

    }

    /**
     * 添加、更新教师用户记录。操作成功后，转至列表页面。
     * 
     * @param employ 教师用户entity
     * @param request http请求
     * @param model 视图模型
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = "save?")
    public String save(Employee employee, HttpServletRequest request, Model model,
            RedirectAttributes redirectAttributes) {
        /* 将对应的employ信息进行服务器端验证 */
        if (!beanValidator(model, employee)) {
            return form(employee, request, model);
        }
        if (StringUtils.isNotBlank(employee.getPapersNumber())) {
            if ("1".equals(employee.getPapersType())) {
                // 身份证验证
                String str = SysUtils.IDCardValidate(employee.getPapersNumber());
                if (StringUtils.isNotBlank(str)) {
                    errorMessages.add(0, "数据验证失败");
                    errorMessages.add(str);
                    addMessage(model);
                    errorMessages.removeAll(errorMessages);
                    employeeService.save(employee);
                    return "modules/hr/profile";
                }
            }
        }

        User user = (User) request.getSession().getAttribute(HrConstant.USER);
        Employee emp = (Employee) request.getSession().getAttribute(HrConstant.EMPLOYEE);

        model.addAttribute("user", user);
        model.addAttribute("employee", emp);
        /* 将当前employ的信息保存数据库中 */
        employeeService.save(employee);
        /* 弹出信息提示框，内容为:保存成功 */
        addMessage(redirectAttributes, "保存成功");
        /* 重定向到employForm页面，并将当前用户Id传入 */
        return "redirect:" + adminPath + "/hr/employee/form?id=" + employee.getId() + "&repage";
    }

    /**
     * 删除教师用户记录。操作成功后，转至列表页面。
     * 
     * @param edu 教师用户entity
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     */
    @RequestMapping(value = "delete")
    public String delete(Employee employee, RedirectAttributes redirectAttributes) {
        /* 判断当前是否为演示模式 */
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/hr/user/list?repage";
        }
        /* 判断要删除的对象是否为自己本身、超级管理员，如果是则不允许删除 */
        if (UserUtils.getUser().getId().equals(employee.getId())) {
            addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
        } else if (User.isAdmin(employee.getId())) {
            addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
        } else { /* 如果删除的对象并不是以上用户则删除成功，并弹出提示框，内容为:删除用户成功 */
            // employeeService.delete(employ);
            addMessage(redirectAttributes, "删除用户成功");
        }
        /* 重定向到employList页面 */
        return "redirect:" + adminPath + "/hr/employee/list?repage";
    }

    /**
     * 查询全部教师用户信息（分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
     * 
     * @param employ 教师用户entity
     * @param request http请求
     * @param response http响应
     * @param model 视图模型
     * @return 全部教师用户信息（分页）
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = { "list", "" })
    public String list(Employee employee, HttpServletRequest request, HttpServletResponse response, Model model) {
        /* 通过employeeService.findemploy()方法将employ信息进行分页 */
        Page<Employee> page = employeeService.findEmployee(new Page<Employee>(request, response), employee);
        /* page信息保存到对应的模型中 */
        model.addAttribute("page", page);
        /* 返回employList页面 */
        return "modules/hr/employeeList";
    }

    @RequestMapping(value = "export")
    public String export(Employee employee, HttpServletRequest request, HttpServletResponse response,
            RedirectAttributes redirectAttributes, Model model) {
        model.addAttribute("employee", employee);
        return "modules/hr/export";
    }

    @RequestMapping(value = "download")
    public void download(Employee employee, HttpServletRequest request, HttpServletResponse response,
            RedirectAttributes redirectAttributes, Model model) throws IOException {
        InputStream fis = new BufferedInputStream(new FileInputStream(request.getSession().getServletContext()
                .getRealPath("/")
                +Global.getConfig("uploads.template") + "test.xlsx"));
        Workbook wb =new XSSFWorkbook(fis);
        Sheet sheet =  wb.getSheetAt(0);
        Row titleRow = sheet.getRow(1);
        Cell cell = titleRow.getCell(2);
        cell.setCellValue("hello");
        FileUtils.downloads(wb, request, response, "test.xlsx");
        return;
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
    @RequiresPermissions({ "sys:user:view", "hr:edu:export", "hr:employee:export", "hr:proTechPosition:export",
            "hr:teacherQualification:export", "hr:recruitment:export", "hr:assessment:export",
            "hr:insuredSituation:export", "hr:personnelAgency:export", "hr:work:export" })
    @RequestMapping(value = "export/file")
    public String exportFile(Employee employee, HttpServletRequest request, HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        try {
            ExportExcel ee = null;
            String fileName = "教职工信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            for (int i = 0; i < employee.getSelectedItem().size(); i++) {
                String str = employee.getSelectedItem().get(i);
                if (str == null) {
                    continue;
                }
                if (str.equals("personalInformation")) {
                	employee.getUser().setUserType("1");
                	
                    List<Employee> list = employeeDao.findAllList(employee);
                    ee = new ExportExcel(HrUtils.exportSelectedItem.get(str), Employee.class,
                            HrUtils.exportSelectedItem.get(str)).setDataList(list);

                } else if (str.equals("teacherQualification")) {
                    List<TeacherQualification> list = teacherQualificationDao.findAllList(new TeacherQualification());
                    /*
                     * 若ExportExcel为空则创建 否则就在增加一个sheet
                     */
                    if (ee == null) {
                        ee = new ExportExcel(HrUtils.exportSelectedItem.get(str), TeacherQualification.class,
                                HrUtils.exportSelectedItem.get(str)).setDataList(list);

                    } else {
                        List<String> headerList = ee.getHeadList(TeacherQualification.class, 1);
                        ee.initialize2(HrUtils.exportSelectedItem.get(str), headerList,
                                HrUtils.exportSelectedItem.get(str), false);
                        ee.setDataList(list);
                    }
                } else if (str.equals("proTechPosition")) {
                    List<ProTechPosition> list = proTechPositionDao.findAllList(new ProTechPosition());
                    if (ee == null) {
                        ee = new ExportExcel(HrUtils.exportSelectedItem.get(str), ProTechPosition.class,
                                HrUtils.exportSelectedItem.get(str)).setDataList(list);

                    } else {
                        List<String> headerList = ee.getHeadList(ProTechPosition.class, 1);
                        ee.initialize2(HrUtils.exportSelectedItem.get(str), headerList,
                                HrUtils.exportSelectedItem.get(str), false);
                        ee.setDataList(list);
                    }
                } else if (str.equals("recruitment")) {
                    List<Recruitment> list = recruitmentDao.findAllList(new Recruitment());
                    if (ee == null) {
                        ee = new ExportExcel(HrUtils.exportSelectedItem.get(str), Recruitment.class,
                                HrUtils.exportSelectedItem.get(str)).setDataList(list);

                    } else {
                        List<String> headerList = ee.getHeadList(Recruitment.class, 1);
                        ee.initialize2(HrUtils.exportSelectedItem.get(str), headerList,
                                HrUtils.exportSelectedItem.get(str), false);
                        ee.setDataList(list);
                    }
                } else if (str.equals("assessment")) {
                    List<Assessment> list = assessmentDao.findAllList(new Assessment());
                    if (ee == null) {
                        ee = new ExportExcel(HrUtils.exportSelectedItem.get(str), Assessment.class,
                                HrUtils.exportSelectedItem.get(str)).setDataList(list);

                    } else {
                        List<String> headerList = ee.getHeadList(Assessment.class, 1);
                        ee.initialize2(HrUtils.exportSelectedItem.get(str), headerList,
                                HrUtils.exportSelectedItem.get(str), false);
                        ee.setDataList(list);
                    }
                } else if (str.equals("insuredSituation")) {
                    List<InsuredSituation> list = insuredSituationDao.findAllList(new InsuredSituation());
                    if (ee == null) {
                        ee = new ExportExcel(HrUtils.exportSelectedItem.get(str), InsuredSituation.class,
                                HrUtils.exportSelectedItem.get(str)).setDataList(list);

                    } else {
                        List<String> headerList = ee.getHeadList(InsuredSituation.class, 1);
                        ee.initialize2(HrUtils.exportSelectedItem.get(str), headerList,
                                HrUtils.exportSelectedItem.get(str), false);
                        ee.setDataList(list);
                    }
                } else if (str.equals("insuranceRecord")) {
                    List<InsuranceRecord> list = insuranceRecordDao.findAllList(new InsuranceRecord());
                    if (ee == null) {
                        ee = new ExportExcel(HrUtils.exportSelectedItem.get(str), InsuranceRecord.class,
                                HrUtils.exportSelectedItem.get(str)).setDataList(list);

                    } else {
                        List<String> headerList = ee.getHeadList(InsuranceRecord.class, 1);
                        ee.initialize2(HrUtils.exportSelectedItem.get(str), headerList,
                                HrUtils.exportSelectedItem.get(str), false);
                        ee.setDataList(list);
                    }
                } else if (str.equals("personnelAgency")) {
                    List<PersonnelAgency> list = personnelAgencyDao.findAllList(new PersonnelAgency());
                    if (ee == null) {
                        ee = new ExportExcel(HrUtils.exportSelectedItem.get(str), PersonnelAgency.class,
                                HrUtils.exportSelectedItem.get(str)).setDataList(list);

                    } else {
                        List<String> headerList = ee.getHeadList(PersonnelAgency.class, 1);
                        ee.initialize2(HrUtils.exportSelectedItem.get(str), headerList,
                                HrUtils.exportSelectedItem.get(str), false);
                        ee.setDataList(list);
                    }
                } else if (str.equals("edu")) {
                    List<Edu> list = eduDao.findAllList(new Edu());
                    if (ee == null) {
                        ee = new ExportExcel(HrUtils.exportSelectedItem.get(str), Edu.class,
                                HrUtils.exportSelectedItem.get(str)).setDataList(list);

                    } else {
                        List<String> headerList = ee.getHeadList(Edu.class, 1);
                        ee.initialize2(HrUtils.exportSelectedItem.get(str), headerList,
                                HrUtils.exportSelectedItem.get(str), false);
                        ee.setDataList(list);
                    }
                } else if (str.equals("work")) {
                    List<Work> list = workDao.findAllList(new Work());
                    if (ee == null) {
                        ee = new ExportExcel(HrUtils.exportSelectedItem.get(str), Work.class,
                                HrUtils.exportSelectedItem.get(str)).setDataList(list);

                    } else {
                        List<String> headerList = ee.getHeadList(Work.class, 1);
                        ee.initialize2(HrUtils.exportSelectedItem.get(str), headerList,
                                HrUtils.exportSelectedItem.get(str), false);
                        ee.setDataList(list);
                    }
                }

            }
            ee.write(response, fileName).dispose();
            addMessage(redirectAttributes, "导出信息成功");
            return "redirect:" + adminPath + "/hr/employee/list?repage";
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出信息失败！失败信息：" + e.getMessage());
        }
        return null;

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
    public String importFileTemplate(Employee employee, HttpServletResponse response,
            RedirectAttributes redirectAttributes, HttpServletRequest request) {

        String filePath = request.getSession().getServletContext().getRealPath("/")
                + Global.getConfig("uploads.template");
        String str = employee.getSelectedImportTemplateItem();
        String downLoadsName = HrUtils.exportSelectedItem.get(str) + "模板.xlsx";
        String fileName = "import_" + str + ".xlsx";
        FileUtils.downloads(filePath, fileName, request, response, downLoadsName);
        return null;
    }

    /**
     * 选择导入项
     * 
     * @param response
     * @param redirectAttributes
     * @param request
     * @return
     */
    @RequestMapping(value = "import")
    public String selectImportItems(Employee employee, HttpServletRequest request,
            RedirectAttributes redirectAttributes, HttpServletResponse response, Model model) {
        model.addAttribute("employee", employee);
        return "modules/hr/import";

    }

    /**
     * 选择文件
     * 
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "importFile")
    public String selectImportFiles(Employee employee, RedirectAttributes redirectAttributes,
            HttpServletRequest resquest, HttpServletResponse response, Model model) {

        model.addAttribute("employee", employee);
        return "modules/hr/importFile";

    }

    /**
     * 导入用户数据
     * 
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions({ "hr:edu:import", "hr:employee:import", "hr:proTechPosition:import",
            "hr:teacherQualification:import", "hr:recruitment:import", "hr:assessment:import",
            "hr:insuredSituation:import", "hr:personnelAgency:import", "hr:work:import" })
    @RequestMapping(value = "import/file")
    public String importFile(MultipartFile file, Employee employee, RedirectAttributes redirectAttributes) {
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/hr/employee/list?repage";
        }
        try {
            ImportExcel ei = new ImportExcel(file, 1, 0);
            String selectedImportItem = employee.getSelectedImportItem();
            if (selectedImportItem.equals("personalInformation")) {
                /* 若不是提交的该类型文件，则直接抛出异常 */
                if (!file.getOriginalFilename().substring(0, 4).equals("个人资料")) {
                    throw new Exception("未放入正确的模板文件！");
                }
                List<Employee> list = ei.getDataList(Employee.class);
                for (Employee employeeTemp : list) {
                    User user = new User();

                    /* 角色列表，导入用户时，默认类型为普通用户。6:普通用户 */

                    user.setRoleList((List<Role>) RoleListType.getValue("普通用户"));
                    try {
                        if ("true".equals(UserUtils.checkLoginName("", employeeTemp.getUserNo()))) {
                            user.setPassword(SystemService.entryptPassword("123456"));
                            user.setCompany(UserUtils.getUser().getCompany());
                            user.setLoginName(employeeTemp.getUserNo());
                            user.setUserType("1");
                            user.setOffice(employeeTemp.getOffice());
                            user.setName(employeeTemp.getName());

                            BeanValidators.validateWithException(validator, employeeTemp);
                            BeanValidators.validateWithException(validator, user);

                            systemService.saveUser(user);
                            employeeTemp.setId(user.getId());
                            employeeService.save(employeeTemp);

                        }else{
                        	user = UserUtils.getByLoginName(employeeTemp.getUserNo());
                        	systemService.saveUser(user);
                        	employeeTemp.setId(user.getId());
                        	employeeTemp.setUser(user);
                        	employeeTemp.getUser().setEmail(employeeTemp.getEmail());
                            employeeService.save(employeeTemp);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (selectedImportItem.equals("teacherQualification")) {
                if (!file.getOriginalFilename().substring(0, 6).equals("教师资格认定")) {
                    throw new Exception("未放入正确的模板文件！");
                }
                List<TeacherQualification> list = ei.getDataList(TeacherQualification.class);
                for (TeacherQualification temp : list) {
                    if (temp.getUserNo() == null || temp.getUserNo() == "") {
                        break;
                    }
                    User user = UserUtils.getByLoginName(temp.getUserNo());
                    temp.setUser(user);
                    teacherQualificationService.save(temp);
                }
            } else if (selectedImportItem.equals("proTechPosition")) {
                if (!file.getOriginalFilename().substring(0, 4).equals("职称履历")) {
                    throw new Exception("未放入正确的模板文件！");
                }
                List<ProTechPosition> list = ei.getDataList(ProTechPosition.class);
                for (ProTechPosition temp : list) {
                    if (temp.getUserNo() == null || temp.getUserNo() == "") {
                        break;
                    }
                    User user = UserUtils.getByLoginName(temp.getUserNo());
                    temp.setUser(user);
                    proTechPositionService.save(temp);
                }
            } else if (selectedImportItem.equals("recruitment")) {
                if (!file.getOriginalFilename().substring(0, 6).equals("校内岗位履历")) {
                    throw new Exception("未放入正确的模板文件！");
                }
                List<Recruitment> list = ei.getDataList(Recruitment.class);
                for (Recruitment temp : list) {
                    if (temp.getUserNo() == null || temp.getUserNo() == "") {
                        break;
                    }
                    User user = UserUtils.getByLoginName(temp.getUserNo());
                    temp.setUser(user);
                    recruitmentService.save(temp);
                }
            } else if (selectedImportItem.equals("assessment")) {
                if (!file.getOriginalFilename().substring(0, 4).equals("历年考核")) {
                    throw new Exception("未放入正确的模板文件！");
                }
                List<Assessment> list = ei.getDataList(Assessment.class);
                for (Assessment temp : list) {
                    if (temp.getUserNo() == null || temp.getUserNo() == "") {
                        break;
                    }
                    User user = UserUtils.getByLoginName(temp.getUserNo());
                    temp.setUser(user);
                    assessmentService.save(temp);
                }
            } else if (selectedImportItem.equals("insuredSituation")) {
                if (!file.getOriginalFilename().substring(0, 4).equals("社会保障")) {
                    throw new Exception("未放入正确的模板文件！");
                }
                List<InsuredSituation> list = ei.getDataList(InsuredSituation.class);
                for (InsuredSituation temp : list) {
                    if (temp.getUserNo() == null || temp.getUserNo() == "") {
                        break;
                    }
                    User user = UserUtils.getByLoginName(temp.getUserNo());
                    temp.setUser(user);
                    temp.setInsuranceRule(new InsuranceRule());
                    temp.getInsuranceRule().setId(temp.getInsuranceId());
                    if (temp.getUser().getId() != null) {
                        insuredSituationService.save(temp);
                    }
                }
            } else if (selectedImportItem.equals("insuranceRecord")) {
                if (!file.getOriginalFilename().substring(0, 4).equals("参保记录")) {
                    throw new Exception("未放入正确的模板文件！");
                }
                List<InsuranceRecord> list = ei.getDataList(InsuranceRecord.class);
                for (InsuranceRecord temp : list) {
                    if (temp.getUserNo() == null || temp.getUserNo() == "") {
                        break;
                    }
                    User user = UserUtils.getByLoginName(temp.getUserNo());
                    temp.setEmployee(new Employee());
                    temp.getEmployee().setId(user.getId());
                    if (temp.getEmployee().getId() != null) {
                        insuranceRecordService.save(temp);
                    }
                }
            } else if (selectedImportItem.equals("personnelAgency")) {
                if (!file.getOriginalFilename().substring(0, 4).equals("人事档案")) {
                    throw new Exception("未放入正确的模板文件！");
                }
                List<PersonnelAgency> list = ei.getDataList(PersonnelAgency.class);
                for (PersonnelAgency temp : list) {
                    if (temp.getUserNo() == null || temp.getUserNo() == "") {
                        break;
                    }
                    User user = UserUtils.getByLoginName(temp.getUserNo());
                    temp.setUser(user);
                    if (temp.getUser().getId() != null) {

                        personnelAgencyService.save(temp);
                    }
                }
            } else if (selectedImportItem.equals("edu")) {
                if (!file.getOriginalFilename().substring(0, 4).equals("教育经历")) {
                    throw new Exception("未放入正确的模板文件！");
                }
                List<Edu> list = ei.getDataList(Edu.class);
                for (Edu temp : list) {
                    if (temp.getUserNo() == null || temp.getUserNo() == "") {
                        break;
                    }
                    User user = UserUtils.getByLoginName(temp.getUserNo());
                    temp.setUser(user);
                    eduService.save(temp);
                }
            } else if (selectedImportItem.equals("work")) {
                if (!file.getOriginalFilename().substring(0, 4).equals("工作经历")) {
                    throw new Exception("未放入正确的模板文件！");
                }
                List<Work> list = ei.getDataList(Work.class);
                for (Work temp : list) {
                    if (temp.getUserNo() == null || temp.getUserNo() == "") {
                        break;
                    }
                    User user = UserUtils.getByLoginName(temp.getUserNo());
                    temp.setUser(user);
                    workService.save(temp);
                }
            }
            addMessage(redirectAttributes, "导入信息成功");
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入信息失败！失败信息：" + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:" + adminPath + "/hr/employee/list?repage";
    }

    @RequestMapping(value = "word")
    public String importword(Employee employee, String id, HttpServletResponse response, HttpServletRequest request)
            throws IOException {

        Employee employeeEntity = get("1");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        // dataMap.put("image", employeeEntity.getUser().getPhoto());
        dataMap.put("name", employeeEntity.getUser().getName());
        dataMap.put("birthDate", employeeEntity.getBirthDate());
        /*
         * dataMap.put("endEduBackground",DictUtils.getDictLabel(employeeEntity. getEndEduBackground(),
         * "end_edu_background", "")); dataMap.put("academicDegree"
         * ,DictUtils.getDictLabel(employeeEntity.getAcademicDegree(), "academic_degree", ""));
         * dataMap.put("researchArea", employeeEntity.getResearchArea());
         */
        dataMap.put("phone", employeeEntity.getMobile());
        // 工作经历
        Work workEntity = new Work();
        workEntity.setUser(employeeEntity.getUser());
        List<Work> workList = workService.findList(workEntity);
        List<Map<String, Object>> list1 = Lists.newArrayList();
        for (Work w : workList) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("startDate", w.getStartDateString());
            map.put("endDate", w.getEndDateString());
            map.put("companyName", w.getCompanyName());
            map.put("companyNature", DictUtils.getDictLabel(w.getCompanyNature(), "company_nature", ""));
            map.put("position", w.getPosition());
            map.put("remarks", w.getRemarks());
            list1.add(map);
        }
        dataMap.put("table1", list1);

        // 教育经历

        // Edu eduEntity = new Edu();
        // eduEntity.setUser(employeeEntity.getUser());
        // List<Edu> eduList = eduService.findList(eduEntity);
        List<Map<String, Object>> list2 = Lists.newArrayList();
        // for (Edu e : eduList) {
        // Map<String,Object> map = new HashMap<String,Object>();
        // map.put("startDate",e.getStartDateString());
        // map.put("endDate",e.getEndDateString());
        // map.put("schoolName",e.getSchoolName());
        // map.put("specialty", e.getSpecialty());
        // map.put("eduBackground",DictUtils.getDictLabel(e.getEduBackground(),
        // "edu_background", ""));
        // map.put("isFulltime", DictUtils.getDictLabel(e.getIsFulltime(),
        // "is_fulltime", ""));
        // map.put("isOverseas",DictUtils.getDictLabel(e.getIsOverseas(),
        // "is_overseas", ""));
        // map.put("remarks",e.getRemarks());
        // list2.add(map);
        // }
        dataMap.put("table2", list2);

        // 获奖信息
        Award awardEntity = new Award();
        awardEntity.setUser(employeeEntity.getUser());
        List<Award> awardList = awardService.findList(awardEntity);
        List<Map<String, Object>> list3 = Lists.newArrayList();
        for (Award a : awardList) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gainDate", a.getGainDateString());
            map.put("awardsName", a.getAwardsName());
            map.put("levelOne", DictUtils.getDictLabel(a.getLevelOne(), "level_one", ""));
            map.put("levelTwo", a.getLevelTwo());
            list3.add(map);
        }
        dataMap.put("table3", list3);
        // 校内职务
        Title titleEntity = new Title();
        titleEntity.setUser(employeeEntity.getUser());
        List<Title> titleList = titleService.findList(titleEntity);
        List<Map<String, Object>> list4 = Lists.newArrayList();
        for (Title x : titleList) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("startDate", x.getStartDateString());
            map.put("endDate", x.getEndDateString());
            map.put("jobName", x.getJobName());
            map.put("remarks", x.getRemarks());
            list4.add(map);
        }
        dataMap.put("table4", list4);

        // 实践经历
        Practice practiceEntity = new Practice();
        practiceEntity.setUser(employeeEntity.getUser());
        List<Practice> practiceList = practiceService.findList(practiceEntity);
        List<Map<String, Object>> list5 = Lists.newArrayList();
        for (Practice p : practiceList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("startDate", p.getStartDateString());
            map.put("endDate", p.getEndDateString());
            map.put("practiceName", p.getPracticeName());
            map.put("remarks", p.getRemarks());
            list5.add(map);
        }
        dataMap.put("table5", list5);
        // 培训经历
        Train trainEntity = new Train();
        trainEntity.setUser(employeeEntity.getUser());
        List<Train> trainList = trainService.findList(trainEntity);
        List<Map<String, Object>> list6 = Lists.newArrayList();
        for (Train t : trainList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("startDate", t.getStartDateString());
            map.put("endDate", t.getEndDateString());
            // map.put("place", t.getPlace());
            // map.put("trainingAgency", t.getTrainingAgency());
            // map.put("course", t.getCourse());
            // map.put("certificate", t.getCertificate());
            // map.put("detailDescription", t.getDetailDescription());
            list6.add(map);
        }
        dataMap.put("table6", list6);
        // 证书
        Certificate certificateEntity = new Certificate();
        certificateEntity.setUser(employeeEntity.getUser());
        List<Certificate> certificateList = certificateService.findList(certificateEntity);
        List<Map<String, Object>> list7 = Lists.newArrayList();
        for (Certificate c : certificateList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gainDate", c.getGainDateString());
            map.put("certificateName", c.getCertificateName());
            list7.add(map);
        }
        dataMap.put("table7", list7);

        // ImportWord w = new ImportWord();
        // w.createDoc(dataMap, "e:/outFile.doc");
        FileUtils.downloads("e:/", "outFile.doc", request, response, "个人简历.doc");
        return "redirect:" + adminPath + "/hr/employee/detail?repage";
    }

    @RequestMapping(value = { "oced" })
    public String oced(Employee employee, HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = (User) request.getSession().getAttribute(HrConstant.USER);
        Employee emp = (Employee) request.getSession().getAttribute(HrConstant.EMPLOYEE);

        /* 获取当前用户 */
        emp.setUser(user);
        model.addAttribute("employee", emp);
        model.addAttribute("user", user);
        /* 返回独子页面 */
        return "modules/hr/onlyChildEndDate";
    }

    /**
     * 添加、更新教师用户记录。操作成功后，转至列表页面。
     * 
     * @param employ 教师用户entity
     * @param request http请求
     * @param model 视图模型
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     */
    @RequestMapping(value = "saveOced")
    public String saveOced(Employee employee, HttpServletRequest request, Model model,
            RedirectAttributes redirectAttributes) {

        User user = (User) request.getSession().getAttribute(HrConstant.USER);
        Employee emp = (Employee) request.getSession().getAttribute(HrConstant.EMPLOYEE);

        /* 获取当前用户 */
        emp.setUser(user);
        model.addAttribute("employee", emp);
        model.addAttribute("user", user);
        /* 将当前employ的信息保存数据库中 */
        emp.setOnlyChildEndDate(employee.getOnlyChildEndDate());
        employeeService.save(emp);
        /* 弹出信息提示框，内容为:保存成功 */
        addMessage(model, "保存成功");
        /* 重定向到employForm页面，并将当前用户Id传入 */
        return "modules/hr/onlyChildEndDate";
    }
}