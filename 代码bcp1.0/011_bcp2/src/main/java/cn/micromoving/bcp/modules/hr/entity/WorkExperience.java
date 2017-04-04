package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

public class WorkExperience extends DataEntity<WorkExperience> {
    /**
     * <code>serialVersionUID</code> 的注释
     */
    private static final long serialVersionUID = 1L;

    /* 编号 */
    private User user;

    /* 工号 */
    private Employee employee;
    
    /* 部门 */
    private Office office;

    /* 任职单位名称 */
    private String nameOfServiceUnit;

    /* 任职开始年月 */
    private Date startDate;

    /* 任职结束年月 */
    private Date endDate;

    /* 单位性质类别 */
    private String companyNature;

    /* 任职岗位 */
    private String post;

    /* 工作描述 */
    private String jobDescription;

    /* 工作类型 */
    private String jobType;

    /* 备注 */
    private String remarks;

    /* 归属部门 */
    private String companyDepartment;
    /* 开始时间字符 */
    private String startDateString;
    /* 结束时间 字符 */
    private String endDateString;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getNameOfServiceUnit() {
        return nameOfServiceUnit;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public void setNameOfServiceUnit(String nameOfServiceUnit) {
        this.nameOfServiceUnit = nameOfServiceUnit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCompanyDepartment() {
        return companyDepartment;
    }

    public void setCompanyDepartment(String companyDepartment) {
        this.companyDepartment = companyDepartment;
    }
    public String getStartDateString() {
        this.startDateString = DateUtils.formatDate(startDate,
                Global.getConfig("web.dateShortFormatCHN"));
        return this.startDateString;
    }

    public void setStartDateString(String startDateString) {
        this.startDate = StringUtils.toDate(startDateString,
                Global.getConfig("web.dateShortFormatCHN"));
    }

    public String getEndDateString() {
        this.endDateString = DateUtils.formatDate(endDate,
                Global.getConfig("web.dateShortFormatCHN"));
        return this.endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDate = StringUtils.toDate(endDateString,
                Global.getConfig("web.dateShortFormatCHN"));
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
