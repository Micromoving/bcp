package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.User;

public class CertificateInformation extends DataEntity<CertificateInformation>{
	/**
	 * <code>serialVersionUID</code> 的注释
	 */
	private static final long serialVersionUID = 1L;
	/*编号*/
	private User user;
	/*工号*/
	private Employee employee;
	/*证书类型*/
	private String certificateType;
	/*语言证书名称*/
	private String languageCertificate;
	/*证书名称*/
	private String certificate;
	/*发证年月*/
	private Date dateOfIssuance;
	/*发证单位*/
	private String issuingUnit;
	/*证书编号*/
	private String certificateNumber;
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
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getLanguageCertificate() {
		return languageCertificate;
	}
	public void setLanguageCertificate(String languageCertificate) {
		this.languageCertificate = languageCertificate;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public Date getDateOfIssuance() {
		return dateOfIssuance;
	}
	public void setDateOfIssuance(Date dateOfIssuance) {
		this.dateOfIssuance = dateOfIssuance;
	}
	public String getIssuingUnit() {
		return issuingUnit;
	}
	public void setIssuingUnit(String issuingUnit) {
		this.issuingUnit = issuingUnit;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
