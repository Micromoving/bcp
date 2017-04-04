package cn.micromoving.bcp.modules.hr.entity;

import java.util.List;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

import com.google.common.collect.Lists;

/**
 * 上报绩效明细表Entity
 * 
 * @author wenyang
 * @version 2016-07-14
 */

public class ReportedWorkloade extends DataEntity<ReportedWorkloade> {

    private static final long serialVersionUID = 1L;

    /* 用户编号 */
    private User user;

    /**/
    private SalEmpView salEmpView;

    /* 部门 */
    private Office office;

    /* 上报记录编号 */
    private ReportRecord reportRecord;

    /* 状态 */
    private String dataState;

    /* 工作量 */
    private Double workload = 0.0;

    /* 姓名 */
    private String name;

    /* 登陆名 */
    private String loginName;

    /* 标记 */
    private String flag;

    /* 教学 */
    private Double teaching = 0.0;

    /* 毕业论文 */
    private Double dissertation = 0.0;

    /* 导师制 */
    private Double tutorialSystem = 0.0;

    /* 公选 */
    private Double optional = 0.0;

    /* 学年论文 */
    private Double termPaper = 0.0;

    /* 阅卷 */
    private Double marking = 0.0;

    /* 出题 */
    private Double theTopic = 0.0;

    /* 监考 */
    private Double invigilator = 0.0;

    /* 小班讨论 */
    private Double smallClassDiscussion = 0.0;

    /* 各类竞赛 */
    private Double allKindsOfCompetition = 0.0;

    /* 其他 */
    private Double other = 0.0;

    /* 数据分类 */
    private String workloadeDataClassification;

    public SalEmpView getSalEmpView() {
        return salEmpView;
    }

    public void setSalEmpView(SalEmpView salEmpView) {
        this.salEmpView = salEmpView;
    }

    private List<String> dataIdList = Lists.newArrayList();

    private List<Double> workloadList = Lists.newArrayList();

    private List<Double> teachingList = Lists.newArrayList();

    private List<Double> dissertationList = Lists.newArrayList();

    private List<Double> tutorialSystemList = Lists.newArrayList();

    private List<Double> optionalList = Lists.newArrayList();

    private List<Double> termPaperList = Lists.newArrayList();

    private List<Double> markingList = Lists.newArrayList();

    private List<Double> theTopicList = Lists.newArrayList();

    private List<Double> invigilatorList = Lists.newArrayList();

    private List<Double> smallClassDiscussionList = Lists.newArrayList();

    private List<Double> allKindsOfCompetitionList = Lists.newArrayList();

    private List<Double> otherList = Lists.newArrayList();

    private List<String> employeeIdList = Lists.newArrayList();

    private String hedDataState;

    private String teDataState;

    public String getHedDataState() {
        return hedDataState;
    }

    public void setHedDataState(String hedDataState) {
        this.hedDataState = hedDataState;
    }

    public String getTeDataState() {
        return teDataState;
    }

    public void setTeDataState(String teDataState) {
        this.teDataState = teDataState;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public ReportRecord getReportRecord() {
        return reportRecord;
    }

    public void setReportRecord(ReportRecord reportRecord) {
        this.reportRecord = reportRecord;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public Double getWorkload() {
        return workload;
    }

    public void setWorkload(Double workload) {
        this.workload = workload;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Double getTeaching() {
        return teaching;
    }

    public void setTeaching(Double teaching) {
        this.teaching = teaching;
    }

    public Double getDissertation() {
        return dissertation;
    }

    public void setDissertation(Double dissertation) {
        this.dissertation = dissertation;
    }

    public Double getTutorialSystem() {
        return tutorialSystem;
    }

    public void setTutorialSystem(Double tutorialSystem) {
        this.tutorialSystem = tutorialSystem;
    }

    public Double getOptional() {
        return optional;
    }

    public void setOptional(Double optional) {
        this.optional = optional;
    }

    public Double getTermPaper() {
        return termPaper;
    }

    public void setTermPaper(Double termPaper) {
        this.termPaper = termPaper;
    }

    public Double getMarking() {
        return marking;
    }

    public void setMarking(Double marking) {
        this.marking = marking;
    }

    public Double getTheTopic() {
        return theTopic;
    }

    public void setTheTopic(Double theTopic) {
        this.theTopic = theTopic;
    }

    public Double getInvigilator() {
        return invigilator;
    }

    public void setInvigilator(Double invigilator) {
        this.invigilator = invigilator;
    }

    public Double getSmallClassDiscussion() {
        return smallClassDiscussion;
    }

    public void setSmallClassDiscussion(Double smallClassDiscussion) {
        this.smallClassDiscussion = smallClassDiscussion;
    }

    public Double getAllKindsOfCompetition() {
        return allKindsOfCompetition;
    }

    public void setAllKindsOfCompetition(Double allKindsOfCompetition) {
        this.allKindsOfCompetition = allKindsOfCompetition;
    }

    public Double getOther() {
        return other;
    }

    public void setOther(Double other) {
        this.other = other;
    }

    public String getWorkloadeDataClassification() {
        return workloadeDataClassification;
    }

    public void setWorkloadeDataClassification(String workloadeDataClassification) {
        this.workloadeDataClassification = workloadeDataClassification;
    }

    public List<String> getDataIdList() {
        return dataIdList;
    }

    public void setDataIdList(List<String> dataIdList) {
        this.dataIdList = dataIdList;
    }

    public List<Double> getWorkloadList() {
        return workloadList;
    }

    public void setWorkloadList(List<Double> workloadList) {
        this.workloadList = workloadList;
    }

    public List<Double> getTeachingList() {
        return teachingList;
    }

    public void setTeachingList(List<Double> teachingList) {
        this.teachingList = teachingList;
    }

    public List<Double> getDissertationList() {
        return dissertationList;
    }

    public void setDissertationList(List<Double> dissertationList) {
        this.dissertationList = dissertationList;
    }

    public List<Double> getTutorialSystemList() {
        return tutorialSystemList;
    }

    public void setTutorialSystemList(List<Double> tutorialSystemList) {
        this.tutorialSystemList = tutorialSystemList;
    }

    public List<Double> getOptionalList() {
        return optionalList;
    }

    public void setOptionalList(List<Double> optionalList) {
        this.optionalList = optionalList;
    }

    public List<Double> getTermPaperList() {
        return termPaperList;
    }

    public void setTermPaperList(List<Double> termPaperList) {
        this.termPaperList = termPaperList;
    }

    public List<Double> getMarkingList() {
        return markingList;
    }

    public void setMarkingList(List<Double> markingList) {
        this.markingList = markingList;
    }

    public List<Double> getTheTopicList() {
        return theTopicList;
    }

    public void setTheTopicList(List<Double> theTopicList) {
        this.theTopicList = theTopicList;
    }

    public List<Double> getInvigilatorList() {
        return invigilatorList;
    }

    public void setInvigilatorList(List<Double> invigilatorList) {
        this.invigilatorList = invigilatorList;
    }

    public List<Double> getSmallClassDiscussionList() {
        return smallClassDiscussionList;
    }

    public void setSmallClassDiscussionList(List<Double> smallClassDiscussionList) {
        this.smallClassDiscussionList = smallClassDiscussionList;
    }

    public List<Double> getAllKindsOfCompetitionList() {
        return allKindsOfCompetitionList;
    }

    public void setAllKindsOfCompetitionList(List<Double> allKindsOfCompetitionList) {
        this.allKindsOfCompetitionList = allKindsOfCompetitionList;
    }

    public List<Double> getOtherList() {
        return otherList;
    }

    public void setOtherList(List<Double> otherList) {
        this.otherList = otherList;
    }

    public List<String> getEmployeeIdList() {
        return employeeIdList;
    }

    public void setEmployeeIdList(List<String> employeeIdList) {
        this.employeeIdList = employeeIdList;
    }

}