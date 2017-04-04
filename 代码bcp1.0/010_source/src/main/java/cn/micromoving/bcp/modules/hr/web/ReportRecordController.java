package cn.micromoving.bcp.modules.hr.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.act.utils.ActUtils;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstance;
import cn.micromoving.bcp.modules.hr.service.ReportRecordService;
import cn.micromoving.bcp.modules.hr.service.SalaryInstanceService;
import cn.micromoving.bcp.modules.hr.utils.HrUtils;

@Controller
@RequestMapping(value = "${adminPath}/hr/reportRecord")
public class ReportRecordController extends BaseController {

    @Autowired
    private ReportRecordService reportRecordService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private SalaryInstanceService salaryInstanceService;

    /**
     * 根据主键， 取得上报记录记录。
     * 
     * @param id primary key
     * @return ReportRecord entity
     */
    @ModelAttribute
    public ReportRecord get(@RequestParam(required = false)
    String id) {
        /* 判断id是否为空，如果有值，调用service来取得id对应的上报记录记录。否则创建一个新的上报记录对象。 */
        if (StringUtils.isNotBlank(id)) {
            return reportRecordService.get(id);
        } else {
            return new ReportRecord();
        }

    }

    /**
     * 查询用户的全部上报记录信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
     * 
     * @param ReportRecord 上报记录
     * @param request http请求
     * @param response http 响应
     * @param model 视图模型
     * @return 用户的全部上报记录信息（不分页）
     */

    @RequestMapping(value = { "list", "" })
    public String list(ReportRecord reportRecord, HttpServletRequest request, SalEmpView salEmpView,
            HttpServletResponse response, Model model) {
        request.getSession().setAttribute("reportRecord.postType", reportRecord.getPostType());
        /* 通过reportRecordService.findreportRecord()方法将上报记录信息进行分页 */
        // Page<ReportRecord> page = reportRecordService.findreportRecord(
        // new Page<ReportRecord>(request, response), reportRecord);
        List<ReportRecord> records = reportRecordService.findList(reportRecord);
        /* 工作量管理时只查询走完流程的信息 */
        for (int i = 0; i < records.size(); i++) {
            
            //若为上报明细，则只查询走完流程的记录
            if (StringUtils.isBlank(reportRecord.getDataClassification())) {
                if (Integer.parseInt(records.get(i).getDataState()) != 6
                       && Integer.parseInt(records.get(i).getDataState()) != 3) {
                    records.remove(i);
                    i--;
                    continue;
                }
            }

            String flag = request.getParameter("flag");
            // 如果是上报明细,则只查询工作量管理的记录，过滤掉上报工作量的记录
            if ("2".equals(records.get(i).getDataClassification()) && StringUtils.isBlank(flag)
                    && StringUtils.isBlank(reportRecord.getDataClassification())) {
                if (Integer.parseInt(records.get(i).getDataState()) != 6) {
                    records.remove(i);
                    i--;
                    continue;
                }
            }
            // 如果是工作量管理(过滤所有上报工作量的记录)
            else if ("2".equals(records.get(i).getDataClassification()) && StringUtils.isNotBlank(flag)
                    && StringUtils.isNotBlank(reportRecord.getDataClassification())) {
                if (Integer.parseInt(records.get(i).getDataState()) < 5) {
                    records.remove(i);
                    i--;
                    continue;
                }
            }
            // 如果是上报工作量(过滤所有工作量管理的记录)
            else if ("2".equals(records.get(i).getDataClassification()) && StringUtils.isBlank(flag)
                    && StringUtils.isNotBlank(reportRecord.getDataClassification())) {
                if (Integer.parseInt(records.get(i).getDataState()) > 4) {
                    records.remove(i);
                    i--;
                    continue;
                }
            }

            if (records.get(i).getDataState().equals("4") && StringUtils.isNotBlank(flag)) {
                records.get(i).setFlag("0");
            }
        }
        /* 找出工资实例对应的信息，在上报明细页面显示 */
        for (ReportRecord tempData : records) {
            if (tempData.getSalaryInstance() != null) {
                tempData.setSalaryInstance(salaryInstanceService.get(tempData.getSalaryInstance().getId()));
            }
        }
        /* page信息保存到对应的模型中 */
        model.addAttribute("records", records);
        /* 取出未完成的工资实例 */
        List<SalaryInstance> list = Lists.newArrayList();
        List<SalaryInstance> salaryList = salaryInstanceService.findList(new SalaryInstance());
        for (SalaryInstance temp : salaryList) {
            if (!"3".equals(temp.getDataState())) {
                list.add(temp);
            }
        }

        model.addAttribute("salaryList", list);
        /* 通过数据分类判断是哪个流程 */
        if (reportRecord.getDataClassification() != null) {
            if (reportRecord.getDataClassification().equals("1") || reportRecord.getDataClassification().equals("6")) {
                List<ProcessDefinition> processList = repositoryService.createProcessDefinitionQuery().latestVersion()
                        .active().orderByProcessDefinitionKey().asc().list();
                for (ProcessDefinition processDefinition : processList) {
                    if (processDefinition.getKey().equals(ActUtils.PD_HR_PERFORMANCE[0])) {
                        model.addAttribute("process", processDefinition);
                    }
                }
            }

            else if (reportRecord.getDataClassification().equals("5")) {
                List<ProcessDefinition> processList = repositoryService.createProcessDefinitionQuery().latestVersion()
                        .active().orderByProcessDefinitionKey().asc().list();
                for (ProcessDefinition processDefinition : processList) {
                    if (processDefinition.getKey().equals(ActUtils.PD_HR_PERFORMANCE_2[0])) {
                        model.addAttribute("process", processDefinition);
                    }
                }
            }

            else if (reportRecord.getDataClassification().equalsIgnoreCase("3")) {
                List<ProcessDefinition> processList = repositoryService.createProcessDefinitionQuery().latestVersion()
                        .active().orderByProcessDefinitionKey().asc().list();
                for (ProcessDefinition processDefinition : processList) {
                    if (processDefinition.getKey().equals(ActUtils.PD_HR_DUTY[0])) {
                        model.addAttribute("process", processDefinition);
                    }
                }
            } else if (reportRecord.getDataClassification().equalsIgnoreCase("2")) {
                List<ProcessDefinition> processList = repositoryService.createProcessDefinitionQuery().latestVersion()
                        .active().orderByProcessDefinitionKey().asc().list();
                for (ProcessDefinition processDefinition : processList) {
                    String flag = request.getParameter("flag");
                    if (ActUtils.PD_HR_WORKLOAD_1[0].equals(processDefinition.getKey())) {
                        model.addAttribute("process", processDefinition);
                    } else if (ActUtils.PD_HR_WORKLOAD_2[0].equals(processDefinition.getKey()) && "0".equals(flag)) {
                        model.addAttribute("process", processDefinition);
                        model.addAttribute("flag", flag);
                    }
                }
            }
        }
        model.addAttribute("salEmpView", salEmpView);
        model.addAttribute("reportRecored", reportRecord);
        /* 返回empolyList页面 */
        return "modules/hr/reportRecordList";
    }

    /**
     * 将上报记录与工资实例关联
     * 
     * @param reportRecord
     * @param request
     * @param salEmpView
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = { "save" })
    public String save(ReportRecord reportRecord, HttpServletRequest request, Model model,
            RedirectAttributes redirectAttributes) {
        if (reportRecord.getRecordIdList() != null) {

            /* 批量保存相关信息 */
            for (int i = 0; i < reportRecord.getRecordIdList().size(); i++) {
                /* 判断该上报记录是否已经同步过，若同步过，则直接下一个 */
                if (reportRecord.getRecordIdList().get(i) != null) {

                    ReportRecord r = reportRecordService.get(reportRecord.getRecordIdList().get(i));
                    /* 若该流程还没有完，则不同步工资 */
                    if (!r.getDataState().equals("3") && !r.getDataState().equals("6")) {
                        continue;
                    }
                    r.setCreateBy(r.getUser());
                    r.setSalaryInstance(new SalaryInstance());
                    r.getSalaryInstance().setId(reportRecord.getSalaryIdList().get(i));
                    /* 同步工资 */
                    HrUtils.synchronousInfo(r);
                    /* 保存 */
                    reportRecordService.save(r);
                }
            }
        }
        return "redirect:" + adminPath + "/hr/reportRecord/list?dataClassification="
                + reportRecord.getDataClassification() + "&postType=" + reportRecord.getPostType();

    }

}
