package cn.micromoving.bcp.modules.hr.entity;

import java.util.Date;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.NumberUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 工资信息表Entity
 * 
 * @author baoke
 * @version 2016-07-13
 */
public class SalaryDetails extends DataEntity<SalaryDetails> {

	private static final long serialVersionUID = 1L;

	/* 工资实例编号 */
	private SalaryInstance salaryInstance;

	/* 年月 */
	private String yearMonth;

	private SalEmpView salEmp;
	/* 员工 */
	private User user;

	/* 职级工资 */
	private Double professionalLevelSalary = 0.0;

	/* 岗位工资 */
	private Double postSalary = 0.0;

	/* 岗位津贴 */
	private Double postSubside = 0.0;

	/* 新增补贴 */
	private Double newSubsidies = 0.0;

	/* 院龄补贴 */
	private Double schoolAgeSubside = 0.0;

	public SalEmpView getSalEmp() {
        return salEmp;
    }

    public void setSalEmp(SalEmpView salEmp) {
        this.salEmp = salEmp;
    }

    /* 职务补助 */
	private Double positionSubsidies = 0.0;

	/* 卫生费 */
	private Double healthCosts = 0.0;

	/* 独子费 */
	private Double theOnlyFee = 0.0;

	/* 班主任费 */
	private Double teacherChargeFee = 0.0;

	/* 课酬金 */
	private Double classFee = 0.0;

	/* 补房基金 */
	private Double fillHousingFund = 0.0;

	/* 扣税金 */
	private Double withholdTaxes = 0.0;

	/* 扣房积金a */
	private Double buckleRoomA = 0.0;

	/* 扣房积金b */
	private Double buckleRoomB = 0.0;

	/* 扣医保 */
	private Double buckleHealthCare = 0.0;

	/* 扣失保 */
	private Double buckleUnemploymentInsurance = 0.0;

	/* 扣养老 */
	private Double buckleEndowmentInsurance = 0.0;

	/* 补缴社保 */
	private Double paymentSocialSecurity = 0.0;

	/* 扣房租 */
	private Double buckleRent = 0.0;

	/* 扣借款 */
	private Double buckleBorrowing = 0.0;

	/* 扣缺勤 */
	private Double buckleAbsenteeism = 0.0;

	/* 扣取暖 */
	private Double buckleWarm = 0.0;

	/* 扣多发 */
	private Double buckleExtraWages = 0.0;

	/* 扣职业年金 */
	private Double buckOccupationalAnnuity = 0.0;

	/* 扣生育 */
	private Double buckMaternityInsurance = 0.0;

	/* 扣工伤 */
	private Double buckEmpInjuryInsurance = 0.0;

	/* 扣公务员医疗补助 */
	private Double buckgongyi = 0.0;

	/* 值班 */
	private Double duty = 0.0;

	/* 合计 */
	private Double total = 0.0;

	/* 备用1 */
	private Double dummy1 = 0.0;

	/* 备用2 */
	private Double dummy2 = 0.0;

	/* 备用3 */
	private Double dummy3 = 0.0;

	/* 备用4 */
	private Double dummy4 = 0.0;

	/* 备用5 */
	private Double dummy5 = 0.0;

	/* 备用6 */
	private Double dummy6 = 0.0;

	/* 备用7 */
	private Double dummy7 = 0.0;

	/* 备用8 */
	private Double dummy8 = 0.0;

	/* 备用9 */
	private Double dummy9 = 0.0;

	/* 备用10 */
	private Double dummy10 = 0.0;

	/* 绩效工资 */
	private Double performanceSalary = 0.0;

	/* 年终绩效 */
	private Double yearEndPerformance = 0.0;

	/* 开始时间字符串 */
	private String startDateString;

	/* 结束时间字符串 */
	private String endDateString;

	/* 精神文明奖1 */
	private Double spiritualCivilization1 = 0.0;

	/* 精神文明奖2 */
	private Double spiritualCivilization2 = 0.0;

	/* 精神文明奖3 */
	private Double spiritualCivilization3 = 0.0;

	/* 取暖费用 */
	private Double heatingCosts = 0.0;

	/* 备用11 */
	private Double dummy11 = 0.0;

	/* 备用12 */
	private Double dummy12 = 0.0;

	/* 备用13 */
	private Double dummy13 = 0.0;

	/* 备用14 */
	private Double dummy14 = 0.0;

	/* 备用15 */
	private Double dummy15 = 0.0;

	/* 备用16 */
	private Double dummy16 = 0.0;

	/* 备用17 */
	private Double dummy17 = 0.0;

	/* 备用18 */
	private Double dummy18 = 0.0;

	/* 备用19 */
	private Double dummy19 = 0.0;

	/* 备用20 */
	private Double dummy20 = 0.0;
	/* 扣生保 */
	private Double birthInsurance = 0.0;

	/* 扣除工资 */
	private Double buckleWages = 0.0;

	/* 应发工资 */
	private Double initialWages = 0.0;

	public SalaryDetails() {
		this.user = new User();
		this.salaryInstance = new SalaryInstance();

	}

	public SalaryInstance getSalaryInstance() {
		return salaryInstance;
	}

	public void setSalaryInstance(SalaryInstance salaryInstance) {
		this.salaryInstance = salaryInstance;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	@ExcelField(title = "上报年月", type = 0, align = 2, sort = 20)
	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	@ExcelField(title = "姓名", type = 0, align = 2, sort = 10, value = "user.name")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ExcelField(title = "职级工资", type = 0, align = 2, sort = 3)
	public Double getProfessionalLevelSalary() {
		return professionalLevelSalary;
	}

	public void setProfessionalLevelSalary(Double professionalLevelSalary) {
		this.professionalLevelSalary = professionalLevelSalary;
	}

	@ExcelField(title = "岗位工资", type = 0, align = 2, sort = 40)
	public Double getPostSalary() {
		return postSalary;
	}

	public void setPostSalary(Double postSalary) {
		this.postSalary = postSalary;
	}

	@ExcelField(title = "岗位津贴", type = 0, align = 2, sort = 50)
	public Double getPostSubside() {
		return postSubside;
	}

	public void setPostSubside(Double postSubside) {
		this.postSubside = postSubside;
	}

	@ExcelField(title = "新增补贴", type = 1, align = 2, sort = 60)
	public Double getNewSubsidies() {
		return newSubsidies;
	}

	public void setNewSubsidies(Double newSubsidies) {
		this.newSubsidies = newSubsidies;
	}

	@ExcelField(title = "院龄补贴", type = 0, align = 2, sort = 70)
	public Double getSchoolAgeSubside() {
		return schoolAgeSubside;
	}

	public void setSchoolAgeSubside(Double schoolAgeSubside) {
		this.schoolAgeSubside = schoolAgeSubside;
	}

	@ExcelField(title = "职务补助", type = 0, align = 2, sort = 80)
	public Double getPositionSubsidies() {
		return positionSubsidies;
	}

	public void setPositionSubsidies(Double positionSubsidies) {
		this.positionSubsidies = positionSubsidies;
	}

	@ExcelField(title = "卫生费", type = 0, align = 2, sort = 90)
	public Double getHealthCosts() {
		return healthCosts;
	}

	public void setHealthCosts(Double healthCosts) {
		this.healthCosts = healthCosts;
	}

	@ExcelField(title = "独子费", type = 0, align = 2, sort = 100)
	public Double getTheOnlyFee() {
		return theOnlyFee;
	}

	public void setTheOnlyFee(Double theOnlyFee) {
		this.theOnlyFee = theOnlyFee;
	}

	@ExcelField(title = "班主任费", type = 0, align = 2, sort = 120)
	public Double getTeacherChargeFee() {
		return teacherChargeFee;
	}

	public void setTeacherChargeFee(Double teacherChargeFee) {
		this.teacherChargeFee = teacherChargeFee;
	}

	@ExcelField(title = "课酬金", type = 0, align = 2, sort = 130)
	public Double getClassFee() {
		return classFee;
	}

	public void setClassFee(Double classFee) {
		this.classFee = classFee;
	}

	@ExcelField(title = "补房基金", type = 0, align = 2, sort = 140)
	public Double getFillHousingFund() {
		return fillHousingFund;
	}

	public void setFillHousingFund(Double fillHousingFund) {
		this.fillHousingFund = fillHousingFund;
	}

	public Double getWithholdTaxes() {
		return withholdTaxes;
	}

	public void setWithholdTaxes(Double withholdTaxes) {
		this.withholdTaxes = withholdTaxes;
	}

	public Double getBuckleRoomA() {
		return buckleRoomA;
	}

	public void setBuckleRoomA(Double buckleRoomA) {
		this.buckleRoomA = buckleRoomA;
	}

	public Double getBuckleRoomB() {
		return buckleRoomB;
	}

	public void setBuckleRoomB(Double buckleRoomB) {
		this.buckleRoomB = buckleRoomB;
	}

	public Double getBuckleHealthCare() {
		return buckleHealthCare;
	}

	public void setBuckleHealthCare(Double buckleHealthCare) {
		this.buckleHealthCare = buckleHealthCare;
	}

	public Double getBuckleUnemploymentInsurance() {
		return buckleUnemploymentInsurance;
	}

	public void setBuckleUnemploymentInsurance(
			Double buckleUnemploymentInsurance) {
		this.buckleUnemploymentInsurance = buckleUnemploymentInsurance;
	}

	public Double getBuckleEndowmentInsurance() {
		return buckleEndowmentInsurance;
	}

	public void setBuckleEndowmentInsurance(Double buckleEndowmentInsurance) {
		this.buckleEndowmentInsurance = buckleEndowmentInsurance;
	}

	public Double getBuckleRent() {
		return buckleRent;
	}

	public void setBuckleRent(Double buckleRent) {
		this.buckleRent = buckleRent;
	}

	public Double getBuckleBorrowing() {
		return buckleBorrowing;
	}

	public void setBuckleBorrowing(Double buckleBorrowing) {
		this.buckleBorrowing = buckleBorrowing;
	}

	public Double getBuckleAbsenteeism() {
		return buckleAbsenteeism;
	}

	public void setBuckleAbsenteeism(Double buckleAbsenteeism) {
		this.buckleAbsenteeism = buckleAbsenteeism;
	}

	public Double getBuckleWarm() {
		return buckleWarm;
	}

	public void setBuckleWarm(Double buckleWarm) {
		this.buckleWarm = buckleWarm;
	}

	public Double getBuckleExtraWages() {
		return buckleExtraWages;
	}

	public void setBuckleExtraWages(Double buckleExtraWages) {
		this.buckleExtraWages = buckleExtraWages;
	}

	public Double getDummy1() {
		return dummy1;
	}

	public void setDummy1(Double dummy1) {
		this.dummy1 = dummy1;
	}

	public Double getDummy2() {
		return dummy2;
	}

	public void setDummy2(Double dummy2) {
		this.dummy2 = dummy2;
	}

	public Double getDummy3() {
		return dummy3;
	}

	public void setDummy3(Double dummy3) {
		this.dummy3 = dummy3;
	}

	public Double getDummy4() {
		return dummy4;
	}

	public void setDummy4(Double dummy4) {
		this.dummy4 = dummy4;
	}

	public Double getDummy5() {
		return dummy5;
	}

	public void setDummy5(Double dummy5) {
		this.dummy5 = dummy5;
	}

	public Double getDummy6() {
		return dummy6;
	}

	public void setDummy6(Double dummy6) {
		this.dummy6 = dummy6;
	}

	public Double getDummy7() {
		return dummy7;
	}

	public void setDummy7(Double dummy7) {
		this.dummy7 = dummy7;
	}

	public Double getDummy8() {
		return dummy8;
	}

	public void setDummy8(Double dummy8) {
		this.dummy8 = dummy8;
	}

	public Double getDummy9() {
		return dummy9;
	}

	public void setDummy9(Double dummy9) {
		this.dummy9 = dummy9;
	}

	public Double getDummy10() {
		return dummy10;
	}

	public void setDummy10(Double dummy10) {
		this.dummy10 = dummy10;
	}

	@ExcelField(title = "绩效工资", type = 0, align = 2, sort = 160)
	public Double getPerformanceSalary() {
		return performanceSalary;
	}

	public void setPerformanceSalary(Double performanceSalary) {
		this.performanceSalary = performanceSalary;
	}

	public Double getPaymentSocialSecurity() {
		return paymentSocialSecurity;
	}

	public void setPaymentSocialSecurity(Double paymentSocialSecurity) {
		this.paymentSocialSecurity = paymentSocialSecurity;
	}

	@ExcelField(title = "年终绩效", type = 0, align = 2, sort = 170)
	public Double getYearEndPerformance() {
		return yearEndPerformance;
	}

	public void setYearEndPerformance(Double yearEndPerformance) {
		this.yearEndPerformance = yearEndPerformance;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@ExcelField(title = "值班费", type = 0, align = 2, sort = 150)
	public Double getDuty() {
		return duty;
	}

	public void setDuty(Double duty) {
		this.duty = duty;
	}

	public void setSpiritualCivilization1(Double spiritualCivilization1) {
		this.spiritualCivilization1 = spiritualCivilization1;
	}

	@ExcelField(title = "精神文明奖1", type = 0, align = 2, sort = 180)
	public Double getSpiritualCivilization1() {
		return spiritualCivilization1;
	}

	@ExcelField(title = "精神文明奖2", type = 0, align = 2, sort = 190)
	public Double getSpiritualCivilization2() {
		return spiritualCivilization2;
	}

	@ExcelField(title = "精神文明奖3", type = 0, align = 2, sort = 200)
	public Double getSpiritualCivilization3() {
		return spiritualCivilization3;
	}

	@ExcelField(title = "取暖费", type = 0, align = 2, sort = 210)
	public Double getHeatingCosts() {
		return heatingCosts;
	}

	public void setSpiritualCivilization2(Double spiritualCivilization2) {
		this.spiritualCivilization2 = spiritualCivilization2;
	}

	public void setSpiritualCivilization3(Double spiritualCivilization3) {
		this.spiritualCivilization3 = spiritualCivilization3;
	}

	public void setHeatingCosts(Double heatingCosts) {
		this.heatingCosts = heatingCosts;
	}

	public Double getDummy11() {
		return dummy11;
	}

	public void setDummy11(Double dummy11) {
		this.dummy11 = dummy11;
	}

	public Double getDummy12() {
		return dummy12;
	}

	public void setDummy12(Double dummy12) {
		this.dummy12 = dummy12;
	}

	public Double getDummy13() {
		return dummy13;
	}

	public void setDummy13(Double dummy13) {
		this.dummy13 = dummy13;
	}

	public Double getDummy14() {
		return dummy14;
	}

	public void setDummy14(Double dummy14) {
		this.dummy14 = dummy14;
	}

	public Double getDummy15() {
		return dummy15;
	}

	public void setDummy15(Double dummy15) {
		this.dummy15 = dummy15;
	}

	public Double getDummy16() {
		return dummy16;
	}

	public void setDummy16(Double dummy16) {
		this.dummy16 = dummy16;
	}

	public Double getDummy17() {
		return dummy17;
	}

	public void setDummy17(Double dummy17) {
		this.dummy17 = dummy17;
	}

	public Double getDummy18() {
		return dummy18;
	}

	public void setDummy18(Double dummy18) {
		this.dummy18 = dummy18;
	}

	public Double getDummy19() {
		return dummy19;
	}

	public void setDummy19(Double dummy19) {
		this.dummy19 = dummy19;
	}

	public Double getDummy20() {
		return dummy20;
	}

	public void setDummy20(Double dummy20) {
		this.dummy20 = dummy20;
	}

	public Double getTempSum() {
		Double sum = 0.0;
		sum = sum + this.professionalLevelSalary + postSalary + postSubside
				+ newSubsidies + schoolAgeSubside + positionSubsidies
				+ healthCosts + theOnlyFee + teacherChargeFee + classFee
				+ fillHousingFund + duty + performanceSalary
				+ yearEndPerformance + spiritualCivilization1
				+ spiritualCivilization2 + spiritualCivilization3
				+ heatingCosts - paymentSocialSecurity;

		sum = sum - withholdTaxes - buckleRoomA - buckleRoomB
				- buckleHealthCare - buckleUnemploymentInsurance
				- buckleEndowmentInsurance - buckleRent - buckleBorrowing
				- buckleAbsenteeism - buckleWarm - buckleExtraWages
				- birthInsurance;
		sum = sum + dummy1 + dummy2 + dummy3 + dummy4 + dummy5 + dummy6
				+ dummy7 + dummy8 + dummy9 + dummy10 + dummy11 + dummy12
				+ dummy13 + dummy14 + dummy15 + dummy16 + dummy17 + dummy18
				+ dummy19 + dummy20;

		return sum;
	}

	public Double getBirthInsurance() {
		return birthInsurance;
	}

	public void setBirthInsurance(Double birthInsurance) {
		this.birthInsurance = birthInsurance;
	}

	public Double getBuckleWages() {
		if (buckleWages == 0.0) {
			buckleWages = sumBuckleWages();
		}
		return buckleWages;
	}

	public void setBuckleWages(Double buckleWages) {
		this.buckleWages = buckleWages;
	}

	public Double getInitialWages() {
		if (initialWages == 0.0) {
			initialWages = sumInitialWages();
		}
		return initialWages;
	}

	public void setInitialWages(Double initialWages) {
		this.initialWages = initialWages;
	}

	public Double getBuckOccupationalAnnuity() {
		return buckOccupationalAnnuity;
	}

	public void setBuckOccupationalAnnuity(Double buckOccupationalAnnuity) {
		this.buckOccupationalAnnuity = buckOccupationalAnnuity;
	}

	public Double getBuckMaternityInsurance() {
		return buckMaternityInsurance;
	}

	public void setBuckMaternityInsurance(Double buckMaternityInsurance) {
		this.buckMaternityInsurance = buckMaternityInsurance;
	}

	public Double getBuckEmpInjuryInsurance() {
		return buckEmpInjuryInsurance;
	}

	public void setBuckEmpInjuryInsurance(Double buckEmpInjuryInsurance) {
		this.buckEmpInjuryInsurance = buckEmpInjuryInsurance;
	}

	public Double getBuckgongyi() {
		return buckgongyi;
	}

	public void setBuckgongyi(Double buckgongyi) {
		this.buckgongyi = buckgongyi;
	}

	public Double sumInitialWages() {
		Double sum = 0.0;
		sum = sum + this.professionalLevelSalary + postSalary + postSubside
				+ newSubsidies + schoolAgeSubside + positionSubsidies
				+ healthCosts + theOnlyFee + teacherChargeFee + classFee
				+ fillHousingFund + duty + performanceSalary
				+ yearEndPerformance + spiritualCivilization1
				+ spiritualCivilization2 + spiritualCivilization3
				+ heatingCosts + paymentSocialSecurity + dummy1 + dummy2
				+ dummy3 + dummy4 + dummy5 + dummy6 + dummy7 + dummy8 + dummy9
				+ dummy10 + dummy11 + dummy12 + dummy13 + dummy14 + dummy15 + dummy16
				+ dummy17 + dummy18 + dummy19 + dummy20;

		return sum;
	}

	public Double sumBuckleWages() {
		Double sum = 0.0;
		sum = sum + withholdTaxes + buckleRoomA + buckleRoomB
				+ buckleHealthCare + buckleUnemploymentInsurance
				+ buckleEndowmentInsurance + buckleRent + buckleBorrowing
				+ buckleAbsenteeism + buckleWarm + buckleExtraWages
				+ birthInsurance;
		return sum;
	}
}
