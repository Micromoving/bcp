package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;

/**
 * 
 * @author 微动信息 王志辰
 * @version $Revision:  $ $Date:  $
 * <pre>
 * ■变更记录■
 * 2017年3月8日 创建
 * </pre>
 */
public class EmpPerformance3View extends DataEntity<EmpPerformance3View> {

	private static final long serialVersionUID = 1L;
	private String name;
	private String loginName;
	private String officeId;
	private String officeName;
	private Double latestSal;
	private Double stdSal;

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

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Double getLatestSal() {
		return latestSal;
	}

	public void setLatestSal(Double latestSal) {
		this.latestSal = latestSal;
	}

	public Double getStdSal() {
		return stdSal;
	}

	public void setStdSal(Double stdSal) {
		this.stdSal = stdSal;
	}


}