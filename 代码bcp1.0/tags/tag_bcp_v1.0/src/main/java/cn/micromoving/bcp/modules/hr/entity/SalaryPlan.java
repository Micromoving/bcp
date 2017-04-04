package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;

/**
 * 工资方案表Entity
 * 
 * @author wenyang
 * @version 2016-07-13
 */

public class SalaryPlan extends DataEntity<SalaryPlan> {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /* 方案名称 */
    private String planName;

    /* 方案状态 */
    private String planStatus;

    /* 方案说明 */
    private String planExplain;

    /* 备注信息 */
    private String comments;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatusString) {
        this.planStatus = planStatusString;
    }

    public String getPlanExplain() {
        return planExplain;
    }

    public void setPlanExplain(String planExplain) {
        this.planExplain = planExplain;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreateDateString() {
        return DateUtils.formatDate(createDate, Global.getConfig("web.dateFormat"));
    }

}
