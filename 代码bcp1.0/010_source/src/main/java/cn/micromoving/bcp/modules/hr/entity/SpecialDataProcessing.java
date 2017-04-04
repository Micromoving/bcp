package cn.micromoving.bcp.modules.hr.entity;

/**
 * 特殊数据处理Entity
 * 
 * @author 微动信息 王志辰
 * @version $Revision: $ $Date: $
 * 
 *          <pre>
 * ■变更记录■
 * 2016年12月11日 创建
 * </pre>
 */
public class SpecialDataProcessing {
	/* 数据分类 */
	private String dataClassification;
	private String salaryInstanceId;

	public String getSalaryInstanceId() {
		return salaryInstanceId;
	}

	public void setSalaryInstanceId(String salaryInstanceId) {
		this.salaryInstanceId = salaryInstanceId;
	}

	public String getDataClassification() {
		return dataClassification;
	}

	public void setDataClassification(String dataClassification) {
		this.dataClassification = dataClassification;
	}

}
