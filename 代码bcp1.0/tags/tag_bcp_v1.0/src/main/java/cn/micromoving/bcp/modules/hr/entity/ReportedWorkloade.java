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
    /*用户编号*/
    private User user;
    /*部门*/
    private Office office;
    /* 上报记录编号 */
    private ReportRecord reportRecord;
    /* 状态 */
    private String dataState;
    /* 工作量 */
    private Double workload;
    /* 姓名 */
    private String name;
    /*登陆名*/
    private String loginName;
    /*标记*/
    private String flag;
    

    private List<String> dataIdList = Lists.newArrayList();

    private List<Double> workloadList = Lists.newArrayList();

    private List<String> employeeIdList = Lists.newArrayList();
    
    
    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<String> getEmployeeIdList() {
        return employeeIdList;
    }

    public void setEmployeeIdList(List<String> employeeIdList) {
        this.employeeIdList = employeeIdList;
    }

    public ReportedWorkloade() {
        super();
        office = new Office();
        user = new User();
        reportRecord = new ReportRecord();

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 获取。
     * @return loginName 
     */
    @ExcelField(title = "职员编号", type = 0, align = 2, sort = 20,value="user.loginName")    
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置loginName。
     * @param loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return
     */
    public String getDataState() {
        return dataState;
    }

    /**
     * @param dataState
     */
    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    @ExcelField(title = "工作量", type = 0, align = 2, sort = 50)
    public Double getWorkload() {
        return workload;
    }

    public void setWorkload(Double workload) {
        this.workload = workload;
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

    public ReportRecord getReportRecord() {
        return reportRecord;
    }

    public void setReportRecord(ReportRecord reportRecord) {
        this.reportRecord = reportRecord;
    }

    @ExcelField(title = "姓名", type = 0, align = 2, sort = 30,value="user.name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title = "院系", type = 0, align = 2, sort = 40,value="user.office.name")
    public Office getOffice() {
        return office;
    }

   
    public void setOffice(Office office) {
        this.office = office;
    }

}