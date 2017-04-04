package cn.micromoving.bcp.modules.hr.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
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
import cn.micromoving.bcp.common.utils.FileUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.UploadUtils;
import cn.micromoving.bcp.common.utils.excel.ImportExcel;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.act.entity.Act;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.entity.Attachment;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.ReportedWorkloade;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.service.AttachmentService;
import cn.micromoving.bcp.modules.hr.service.ReportRecordService;
import cn.micromoving.bcp.modules.hr.service.ReportedWorkloadeService;
import cn.micromoving.bcp.modules.hr.service.SalEmpViewService;
import cn.micromoving.bcp.modules.hr.utils.HrEmpUtils;
import cn.micromoving.bcp.modules.sr.entity.Achievement;
import cn.micromoving.bcp.modules.sys.entity.User;

import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "${adminPath}/hr/reportedWorkloade")
public class ReportedWorkloadeController extends BaseController {

    @Autowired
    private ReportedWorkloadeService reportedWorkloadeService;

    @Autowired
    private ReportRecordService recordService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private SalEmpViewService salEmpViewService;

    /**
     * 根据主键，取得上报工作量记录
     * 
     * @param id primary key
     * @return ReportedWorkloade entity
     */
    @ModelAttribute
    public ReportedWorkloade get(@RequestParam(required = false)
    String id) {
        /* 判断id是否为空，如果有值，调用service来取得id对应的上报工作量记录。否则创建一个新的上报工作量记录。 */
        if (StringUtils.isNotBlank(id)) {
            return reportedWorkloadeService.get(id);
        } else {
            return new ReportedWorkloade();
        }
    }

    /**
     * 查询用户的全部上报工作量信息（分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
     * 
     * @param reportedWorkloade 上报工作量
     * @param request http请求
     * @param response http 响应
     * @param model 视图模型
     * @return 用户的全部上报工作量信息（分页）
     */
    @RequiresPermissions(value = { "hr:reportedWorkloade:search", "hr:reportedWorkloade:audit" }, logical = Logical.OR)
    @RequestMapping(value = "list")
    public String list(ReportedWorkloade reportedWorkloade, HttpServletRequest request, HttpServletResponse response,
            Model model) {

        /* 新建视图 */
        SalEmpView salEmpView = new SalEmpView();
        /* 通过reportedWorkloadeService.findSalEmpView()方法将reportedWorkloade信息进行分页 */
        Page<SalEmpView> page = reportedWorkloadeService.findSalEmpView(new Page<SalEmpView>(request, response),
                salEmpView);
        /* 在容器中添加新的上报工作量信息 */
        List<ReportedWorkloade> reportWorkloadList = Lists.newArrayList();
        /* 循环从page中查出的信息 */
        for (SalEmpView data : page.getList()) {
            /* 新建上报工作量 */
            ReportedWorkloade entity = new ReportedWorkloade();
            /* 新建user用户 */
            User user = new User();
            /* 通过视图中的id，给use赋值id */
            user.setId(data.getId());
            /* 通过视图中的name，给user赋值 */
            user.setName(data.getName());
            /* 通过视图中的登录名，给user赋值 */
            user.setLoginName(data.getLoginName());
            /* 通过视图中的office，得到user中的office */
            user.setOffice(data.getOffice());
            /* 上报工作量中得到这个用户的数据 */
            entity.setUser(user);
            /* 在上报容器中添加这个用户 */
            reportWorkloadList.add(entity);
        }
        /* 若点击生成月汇总结果 */
        String flag = request.getParameter("flag");
        if ("0".equals(flag)) {
            /* 1.先找出第一个流程走完的上报明细 */
            ReportRecord reportRecord = new ReportRecord();
            reportRecord.setDataState("3");
            reportRecord.setDataClassification("2");
            List<ReportRecord> list = recordService.findList(reportRecord);
            /* 2.遍历这个list */
            for (ReportRecord r : list) {
                if (Integer.parseInt(r.getDataState()) > 3) {
                    continue;
                }
                /* 通过上报记录id查询到对应的值 */
                ReportedWorkloade entity = new ReportedWorkloade();
                entity.setReportRecord(r);
                List<ReportedWorkloade> listWork = reportedWorkloadeService.findList(entity);
                /* 循环这个listWork */
                for (int i = 0; i < listWork.size(); i++) {
                    /* 如果对应的人员一样，则将其对应的值进行累加 */
                    for (int j = 0; j < reportWorkloadList.size(); j++) {
                        if (listWork.get(i).getUser().getId().equals(reportWorkloadList.get(j).getUser().getId())) {
                            /* 教学 */
                            reportWorkloadList.get(j).setTeaching(
                                    reportWorkloadList.get(j).getTeaching() + listWork.get(i).getTeaching());
                            /* 毕业论文 */
                            reportWorkloadList.get(j).setDissertation(
                                    reportWorkloadList.get(j).getDissertation() + listWork.get(i).getDissertation());
                            /* 导师制 */
                            reportWorkloadList.get(j)
                                    .setTutorialSystem(
                                            reportWorkloadList.get(j).getTutorialSystem()
                                                    + listWork.get(i).getTutorialSystem());
                            /* 公选 */
                            reportWorkloadList.get(j).setOptional(
                                    reportWorkloadList.get(j).getOptional() + listWork.get(i).getOptional());
                            /* 学年论文 */
                            reportWorkloadList.get(j).setTermPaper(
                                    reportWorkloadList.get(j).getTermPaper() + listWork.get(i).getTermPaper());
                            /* 阅卷 */
                            reportWorkloadList.get(j).setMarking(
                                    reportWorkloadList.get(j).getMarking() + listWork.get(i).getMarking());
                            /* 出题 */
                            reportWorkloadList.get(j).setTheTopic(
                                    reportWorkloadList.get(j).getTheTopic() + listWork.get(i).getTheTopic());
                            /* 监考 */
                            reportWorkloadList.get(j).setInvigilator(
                                    reportWorkloadList.get(j).getInvigilator() + listWork.get(i).getInvigilator());
                            /* 小班讨论 */
                            reportWorkloadList.get(j).setSmallClassDiscussion(
                                    reportWorkloadList.get(j).getSmallClassDiscussion()
                                            + listWork.get(i).getSmallClassDiscussion());
                            /* 各类竞赛 */
                            reportWorkloadList.get(j).setAllKindsOfCompetition(
                                    reportWorkloadList.get(j).getAllKindsOfCompetition()
                                            + listWork.get(i).getAllKindsOfCompetition());
                            /* 其他 */
                            reportWorkloadList.get(j).setOther(
                                    reportWorkloadList.get(j).getOther() + listWork.get(i).getOther());
                            /* 工作总量 */
                            reportWorkloadList.get(j).setWorkload(
                                    reportWorkloadList.get(j).getTeaching()
                                            + reportWorkloadList.get(j).getDissertation()
                                            + reportWorkloadList.get(j).getTutorialSystem()
                                            + reportWorkloadList.get(j).getOptional()
                                            + reportWorkloadList.get(j).getTermPaper()
                                            + reportWorkloadList.get(j).getMarking()
                                            + reportWorkloadList.get(j).getTheTopic()
                                            + reportWorkloadList.get(j).getInvigilator()
                                            + reportWorkloadList.get(j).getSmallClassDiscussion()
                                            + reportWorkloadList.get(j).getAllKindsOfCompetition()
                                            + reportWorkloadList.get(j).getOther());

                        }
                    }

                }
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
            reportedWorkloade.setReportRecord(new ReportRecord());
            reportedWorkloade.getReportRecord().setYearMonth(format.format(new Date()));
        }
        /* 容器中信息保存到对应的模型中 */
        model.addAttribute("list", reportWorkloadList);
        /* 上报工作量信息添加到模型中 */
        model.addAttribute("reportedWorkloade", reportedWorkloade);
        /* 返回list页面 */
        return "modules/hr/reportedWorkloadeList";
    }

    /**
     * 根据主键，查询到上报工作量信息，将此信息绑定到model中，在JSP页面中可以读取。
     * 
     * @param reportedWorkloade 上报工作量上报工作量entity，传递数据。
     * @param model 视图模型
     * @return 返回上报工作量编辑页面
     */
    @RequiresPermissions(value = { "hr:reportedWorkloade:create", "hr:reportedWorkloade:update",
            "hr:reportedWorkloade:audit" }, logical = Logical.OR)
    @RequestMapping(value = "form")
    public String form(ReportedWorkloade reportedWorkloade, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        /* 获得上报Id，等于上报工作量中，上报记录的id */
        String reportId = reportedWorkloade.getReportRecord().getId();
        request.getSession().setAttribute("reportRecord.id", reportId);
        /* 根据上报id，得到该用户的上报信息 */
        ReportRecord data = recordService.get(reportId);
        /* 给该用户添加一个工作流 */
        data.setAct(new Act());
        /* 给该用户的工作流id为上报用户的流程id */
        data.getAct().setProcInsId(data.getProcessInstanceId());
        /* 得到上报信息 */
        reportedWorkloade.setReportRecord(data);
        /* 查询上报工作量的列表数据 */
        List<ReportedWorkloade> list = reportedWorkloadeService.findList(reportedWorkloade);
        /* 建立String的dataIdList容器 */
        List<String> dataIdList = Lists.newArrayList();
        /* 建立Double型的workloadList容器 */
        List<Double> workloadList = Lists.newArrayList();
        /* 建立String型的employeeIdList容器 */
        List<String> employeeIdList = Lists.newArrayList();
        /* 循环list中的上报工作量信息 */
        for (ReportedWorkloade entity : list) {
            /* 将上报工作量id添加到dataIdList容器中 */
            dataIdList.add(entity.getId());
            /* 将工作量添加到workloadList容器中 */
            workloadList.add(entity.getWorkload());
            /* 将上报工作量中用户的id添加到employeeIdList容器中 */
            employeeIdList.add(entity.getUser().getId());
        }

        /* 将上报工作量信息绑定到model中 */
        model.addAttribute("reportedWorkloade", reportedWorkloade);
        /* 将list绑定到model中 */
        model.addAttribute("list", list);
        /* 返回list页面 */
        return "modules/hr/reportedWorkloadeList";
    }

    /**
     * 添加、更新上报工作量记录。操作成功后，转至列表页面。
     * 
     * @param reportedWorkloade , Model model) { return null; 上报工作量entity
     * @param request http请求
     * @param model 视图模型
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     * @throws ParseException
     */
    @RequiresPermissions(value = { "hr:reportedWorkloade:create", "hr:reportedWorkloade:update",
            "hr:reportedWorkloade:audit" }, logical = Logical.OR)
    @RequestMapping(value = "batchSave")
    public String batchSave(ReportedWorkloade reportedWorkloade, Model model, HttpServletRequest request,
            RedirectAttributes redirectAttributes) throws ParseException {
        // if (reportedWorkloadeService.ccTime(reportedWorkloade)) {
        // errorMessages.add(0, "数据验证失败");
        // errorMessages.add("历史记录中已有该年月信息！");
        // addMessage(redirectAttributes);
        // errorMessages.removeAll(errorMessages);
        // return "redirect:" + adminPath
        // + "/hr/reportedWorkloade/list?dataClassification=2";
        // }
        /* 判断当前用户编号和年份是否具备编辑条件 */
        /* 判断，如果上报工作量中的id值为空，或判断上报工作量的年份为空 */
        // if (org.apache.commons.lang3.StringUtils.isEmpty(reportedWorkloade
        // .getId())
        // && reportedWorkloadeService
        // .existReportedWorkloade(reportedWorkloade)
        // && StringUtils.isBlank(reportedWorkloade.getDataState())) {
        // /* 跳出提示消息 */
        // addMessage(model, "同一年月不可重复上报");
        // /* 跳转到页面 */
        // return "modules/hr/reoportedWorkloadeList";
        // } else {
        if (StringUtils.isBlank(reportedWorkloade.getReportRecord().getYearMonth())) {
            errorMessages.add(0, "数据验证失败");
            errorMessages.add("上报年月不能为空");
            addMessage(redirectAttributes);
            errorMessages.removeAll(errorMessages);
            return "redirect:" + adminPath + "/hr/reportedWorkloade/list?dataClassification=2";
        }
        /* 否则，调用batchSave批量保存方法进行保存 */
        reportedWorkloadeService.batchSave(reportedWorkloade);

        // }
        Object fileName1 = request.getSession().getAttribute("fileName1");
        Object fileName2 = request.getSession().getAttribute("fileName2");

        if (fileName1 != null && fileName2 != null) {
            Attachment attachment = new Attachment();
            attachment.setAchievement(new Achievement());
            attachment.getAchievement().setAchievementId(reportedWorkloade.getReportRecord().getId());
            attachment.setAttachmentName(fileName1.toString());
            attachment.setAttachmentSource(fileName2.toString());
            attachment.setAttachmentPath(Global.getConfig("uploads.workLoade"));
            attachmentService.save(attachment);
        }

        if (!"0".equals(reportedWorkloade.getFlag())) {
            try {
                String[] str = { "department_leader", "academic_leadership" };
                recordService.save(reportedWorkloade.getReportRecord(), ActUtils.PD_HR_WORKLOAD_1, str,
                        "reportedWorkloadeuser");
                addMessage(redirectAttributes, "操作成功，数据已经提交。");
            } catch (Exception e) {
                logger.error("启动流程失败：", e);
                addMessage(redirectAttributes, "系统内部错误！");
            }
        } else {
            try {
                String[] str = { "personnel_clerk" };
                recordService.save(reportedWorkloade.getReportRecord(), ActUtils.PD_HR_WORKLOAD_2, str, null);
                addMessage(redirectAttributes, "操作成功，数据已经提交。");
            } catch (Exception e) {
                logger.error("启动流程失败：", e);
                addMessage(redirectAttributes, "系统内部错误！");
            }
        }
        /* 重定向list页面 */
        if (reportedWorkloade.getFlag() != null) {
            /* 重定向list页面 */
            return "redirect:" + adminPath + "/hr/reportRecord/list?dataClassification=2&flag=0";
        }
        return "redirect:" + adminPath + "/hr/reportRecord/list?dataClassification=2";
    }

    /**
     * 工单执行（完成任务）
     * 
     * @param reportedWorkloade
     * @param model
     * @param request
     * @return
     */
    @RequiresPermissions("hr:reportedWorkloade:audit")
    @RequestMapping(value = "saveAudit")
    public String saveAudit(ReportedWorkloade reportedWorkloade, ReportRecord reportRecord, Model model,
            HttpServletRequest request) {
        String comment = reportedWorkloade.getReportRecord().getAct().getComment();
        reportedWorkloade.setReportRecord(recordService.get(reportedWorkloade.getReportRecord().getId()));
        reportedWorkloade.getReportRecord().setCreateBy(reportedWorkloade.getReportRecord().getUser());
        reportedWorkloade.getReportRecord().getAct()
                .setProcInsId(reportedWorkloade.getReportRecord().getProcessInstanceId());
        reportedWorkloade.getReportRecord().getAct().setFlag(request.getParameter("flag"));
        reportedWorkloade.getReportRecord().getAct().setComment(comment);
        recordService.saveAudit(reportedWorkloade);
        return "redirect:" + adminPath + "/sys/task/todolist";
    }

    /**
     * 删除上报工作量记录。操作成功后，转至列表页面。
     * 
     * @param reportedWorkloade reportedWorkloade entity
     * @param redirectAttributes 重定向属性集
     * @return 操作完成后，重定向到列表页面。
     */
    @RequiresPermissions("hr:reportedWorkloade:delete")
    @RequestMapping(value = "delete")
    public String delete(ReportedWorkloade reportedWorkloade, RedirectAttributes redirectAttributes) {
        /* 删除上报工作量信息 */
        reportedWorkloadeService.delete(reportedWorkloade);
        /* 重定向显示消息 */
        addMessage(redirectAttributes, "删除成功");
        /* 重定向list页面 */
        return "redirect:" + adminPath + "/hr/reportedWorkloade/list?repage";
    }

    /**
     * 导入上报工作量信息，跳转至reportedWorkloadeUpload页面
     * 
     * @param reportedWorkloade
     * @param model 视图模型
     * @return
     */
    @RequiresPermissions("hr:reportedWorkloade:import")
    @RequestMapping(value = "upload")
    public String upload(ReportedWorkloade reportedWorkloade, Model model) {
        /* 将上报工作量信息绑定到model中 */
        model.addAttribute("reportedWorkloade", reportedWorkloade);
        /* 返回form页面 */
        return "modules/hr/reportedWorkloadeUpload";
    }

    /**
     * 导出用户数据
     * 
     * @param user
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequiresPermissions("hr:reportedWorkloade:export")
    @RequestMapping(value = "export")
    public void exportFile(ReportedWorkloade reportedWorkloade, HttpServletRequest request,
            HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {
        String filePath = request.getSession().getServletContext().getRealPath("/")
                + Global.getConfig("uploads.workLoade.template");
        String downLoadsName = "内聘教师工作量月汇总.xlsx";
        String fileName = "内聘教师工作量月汇总.xlsx";

        // 查询数据
        List<ReportedWorkloade> list = reportedWorkloadeService.findList(reportedWorkloade);
        for (ReportedWorkloade rw : list) {
            rw.setSalEmpView(salEmpViewService.get(rw.getUser().getId()));
        }
        // 若list为空则证明只导出人员信息
        List<SalEmpView> empList = salEmpViewService.findList(new SalEmpView());

        // 设置数据
        Workbook wb = HrEmpUtils.exportWorkLoad(filePath + fileName, list, empList);

        // 实现下载
        FileUtils.downloads(wb, request, response, downLoadsName);
    }

    @RequestMapping(value = "downloade")
    public void downloadeFile(ReportedWorkloade reportedWorkloade, HttpServletRequest request,
            HttpServletResponse response, RedirectAttributes redirectAttributes) {
        String filePath = request.getSession().getServletContext().getRealPath("/")
                + Global.getConfig("uploads.workLoade");
        Attachment attachment = new Attachment();
        attachment.setAchievement(new Achievement());
        attachment.getAchievement().setAchievementId(reportedWorkloade.getReportRecord().getId());
        attachment = attachmentService.get(attachment);
        if (attachment != null) {
            String downLoadsName = attachment.getAttachmentName();
            String fileName = attachment.getAttachmentSource();
            FileUtils.downloads(filePath, fileName, request, response, downLoadsName);
        }
    }

    /**
     * 导入用户数据
     * 
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("hr:reportedWorkloade:import")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(@RequestParam("file")
    MultipartFile file, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        /* 如果为演示模式，不允许操作，重定向到list页面 */
        if (Global.isDemoMode()) {
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/hr/reportedWorkloade/list?repage";
        }
        try {
            if (file.getSize() == 0) {
                errorMessages.add(0, "数据验证失败");
                errorMessages.add("未放入模板文件！");
                addMessage(model);
                errorMessages.removeAll(errorMessages);
                return "modules/hr/reportedWorkloadeUpload";
            }
            /* 标题行号为1，工作表编号为0 */
            ImportExcel ei = new ImportExcel(file, 1, 0);

            UploadUtils upload = new UploadUtils();
            upload.setBasePath(Global.getConfig("uploads.workLoade"));
            String fileName = upload.uploadFile(request, file);

            request.getSession().setAttribute("fileName1", file.getOriginalFilename());
            request.getSession().setAttribute("fileName2", fileName);

            /* 文件获取到导入数据列表，并放置到list容器中 */
            List<ReportedWorkloade> list = ei.getReportedWorkloadeList();
            /* 新建上报工作量 */
            ReportedWorkloade reportedWorkloade = new ReportedWorkloade();
            /* 新建上报记录 */
            ReportRecord record = new ReportRecord();
            /**/
            if (request.getSession().getAttribute("reportRecord.id") != null) {
                record.setId(request.getSession().getAttribute("reportRecord.id").toString());
            }
            reportedWorkloade.setReportRecord(record);
            /* page信息保存到对应的模型中 */
            model.addAttribute("list", list);
            model.addAttribute("reportedWorkloade", reportedWorkloade);

        } catch (Exception e) {
            addMessage(redirectAttributes, "导入失败！失败信息：" + e.getMessage());
            e.printStackTrace();
            return "modules/hr/reportedWorkloadeUpload";
        }
        return "modules/hr/reportedWorkloadeList";
    }
}
