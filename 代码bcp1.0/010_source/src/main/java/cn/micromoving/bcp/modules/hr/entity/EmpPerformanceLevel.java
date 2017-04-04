package cn.micromoving.bcp.modules.hr.entity;

import java.util.List;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

import com.google.common.collect.Lists;

/**
 * 教师绩效等级表Entity
 * 
 * @author wangshuting
 * @version 2016-06-14
 */
public class EmpPerformanceLevel extends DataEntity<EmpPerformanceLevel> {

    private static final long serialVersionUID = 1L;

    /* 用户 */
    private User user;

    /* 职员编号 */
    private Employee employee;

    /* 档级 */
    private String performanceLevel;

    /* 备注 */
    private String comments;

    /* 生效时间 */
    private String startDate;

    /* 失效时间 */
    private String endDate;

    /* 上报记录编号 */
    private ReportRecord reportRecord;

    /**/
    private String dataState;

    /* 姓名(导入导出使用) */
    private String name;

    /* 部门(导入导出使用) */
    private Office office;

    /* 职员编号(导入导出 使用) */
    private String loginName;

    /* 职级 */
    private String professionalLevel;

    private List<String> dataIdList = Lists.newArrayList();

    private List<String> performanceLevelList = Lists.newArrayList();

    private List<String> employeeIdList = Lists.newArrayList();

    public String getProfessionalLevel() {
        return professionalLevel;
    }

    public void setProfessionalLevel(String professionalLevel) {
        this.professionalLevel = professionalLevel;
    }

    public List<String> getDataIdList() {
        return dataIdList;
    }

    public void setDataIdList(List<String> dataIdList) {
        this.dataIdList = dataIdList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ReportRecord getReportRecord() {
        return reportRecord;
    }

    public void setReportRecord(ReportRecord reportRecord) {
        this.reportRecord = reportRecord;
    }

    @ExcelField(title = "绩效档级", type = 0, align = 2, sort = 40, dictType = "performance_level")
    public String getPerformanceLevel() {
        return performanceLevel;
    }

    public void setPerformanceLevel(String performanceLevel) {
        this.performanceLevel = performanceLevel;
    }

    public List<String> getPerformanceLevelList() {
        return performanceLevelList;
    }

    public void setPerformanceLevelList(List<String> performanceLevelList) {
        this.performanceLevelList = performanceLevelList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ExcelField(title = "姓名", type = 0, align = 2, sort = 20, value = "user.name")
    public String getName() {
        return name;
    }

    @ExcelField(title = "职员编号", type = 0, align = 2, sort = 10, value = "user.loginName")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title = "院系", type = 0, align = 2, sort = 30, value = "user.office.name")
    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public List<String> getEmployeeIdList() {
        return employeeIdList;
    }

    public void setEmployeeIdList(List<String> employeeIdList) {
        this.employeeIdList = employeeIdList;
    }

}
