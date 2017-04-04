package cn.micromoving.bcp.modules.hr.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.SpringContextHolder;
import cn.micromoving.bcp.modules.hr.dao.DutyDao;
import cn.micromoving.bcp.modules.hr.dao.DutyDetailDao;
import cn.micromoving.bcp.modules.hr.dao.EmployeeDao;
import cn.micromoving.bcp.modules.hr.dao.InsuredSituationDao;
import cn.micromoving.bcp.modules.hr.dao.ReportPerformanceDao;
import cn.micromoving.bcp.modules.hr.dao.ReportedWorkloadeDao;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.dao.SalViewDao;
import cn.micromoving.bcp.modules.hr.dao.SalaryDetailsDao;
import cn.micromoving.bcp.modules.hr.dao.SalaryInstanceDao;
import cn.micromoving.bcp.modules.hr.entity.ClassPayStandard;
import cn.micromoving.bcp.modules.hr.entity.Duty;
import cn.micromoving.bcp.modules.hr.entity.DutyDetail;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.InsuredSituation;
import cn.micromoving.bcp.modules.hr.entity.ReportPerformance;
import cn.micromoving.bcp.modules.hr.entity.ReportRecord;
import cn.micromoving.bcp.modules.hr.entity.ReportedWorkloade;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.entity.SalView;
import cn.micromoving.bcp.modules.hr.entity.SalaryDetails;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstance;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstanceTask;
import cn.micromoving.bcp.modules.hr.entity.SubsidiaryStandard;
import cn.micromoving.bcp.modules.hr.entity.Warm;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class HrUtils {

    /* 低职高聘 */
    private static Map<String, String> low2HighMap = Maps.newHashMap();

    /* 高职低聘 */
    private static Map<String, String> high2LowMap = Maps.newHashMap();

    /**
     * 取暖费计算Map。按照职称等级，映射对应的级别。
     */
    private static Map<String, String> warmMap = Maps.newHashMap();

    /* 职级对应职称级别 */
    private static Map<String, String> porLevel2PosLevelMap = Maps.newHashMap();

    /* 导出勾选项 */
    public static Map<String, String> exportSelectedItem = Maps.newHashMap();

    /* 人员 */
    public static SalEmpViewDao salEmpViewDao = SpringContextHolder.getBean(SalEmpViewDao.class);

    /* 4项工资 */
    public static SalViewDao salViewDao = SpringContextHolder.getBean(SalViewDao.class);

    /* 工资详情DAO */
    public static SalaryDetailsDao salaryDetailsDao = SpringContextHolder.getBean(SalaryDetailsDao.class);

    /* 工资实例DAO */
    public static SalaryInstanceDao salaryInstanceDao = SpringContextHolder.getBean(SalaryInstanceDao.class);

    /* 上报工作量DAO */
    public static ReportedWorkloadeDao reportedWorkloadeDao = SpringContextHolder.getBean(ReportedWorkloadeDao.class);

    /* 上报值班DAO */
    public static DutyDetailDao dutyDetailDao = SpringContextHolder.getBean(DutyDetailDao.class);

    /* 职务DAO */
    public static DutyDao dutyDao = SpringContextHolder.getBean(DutyDao.class);

    /* 上报绩效DAO */
    public static ReportPerformanceDao reportPerformanceDao = SpringContextHolder.getBean(ReportPerformanceDao.class);

    /* 社保DAO */
    public static InsuredSituationDao insuredSituationDao = SpringContextHolder.getBean(InsuredSituationDao.class);

    /* 人员DAO */
    public static EmployeeDao employeeDao = SpringContextHolder.getBean(EmployeeDao.class);

    /**
     * 根据提供的entity获取工资对应系数的值
     * 
     * @return
     */
    public Double salaryValue(Double value, SalaryInstanceTask salaryInstanceTask) {

        return value * Double.valueOf(salaryInstanceTask.getCoefficient());
    }

    static {
        /*
         * HrConstant.TECHPOSITIONLEVEL_ONE：初级； HrConstant.TECHPOSITIONLEVEL_TWO：中级；
         * HrConstant.TECHPOSITIONLEVEL_THREE：副高； HrConstant.TECHPOSITIONLEVEL_FOUR：正高
         */
        /*
         * HrConstant.LEVEL_ONE :1级 HrConstant.LEVEL_TWO：2级 HrConstant.LEVEL_THREE ：3级 HrConstant.LEVEL_FOUR：4级
         * HrConstant.LEVEL_FIVE：５级 HrConstant.LEVEL_SIX：６级 HrConstant.LEVEL_SEVEN ：７级 HrConstant.LEVEL_EIGHT ：８级
         * HrConstant.LEVEL_NINE ：９级
         */
        high2LowMap.put(HrConstant.TECHPOSITIONLEVEL_TWO, HrConstant.LEVEL_SEVEN);
        high2LowMap.put(HrConstant.TECHPOSITIONLEVEL_THREE, HrConstant.LEVEL_FIVE);
        high2LowMap.put(HrConstant.TECHPOSITIONLEVEL_FOUR, HrConstant.LEVEL_THREE);
        low2HighMap.put(HrConstant.TECHPOSITIONLEVEL_ONE, HrConstant.LEVEL_EIGHT);
        low2HighMap.put(HrConstant.TECHPOSITIONLEVEL_TWO, HrConstant.LEVEL_SIX);
        low2HighMap.put(HrConstant.TECHPOSITIONLEVEL_THREE, HrConstant.LEVEL_FOUR);
        porLevel2PosLevelMap.put(HrConstant.LEVEL_NINE, HrConstant.TECHPOSITIONLEVEL_ONE);
        porLevel2PosLevelMap.put(HrConstant.LEVEL_EIGHT, HrConstant.TECHPOSITIONLEVEL_ONE);
        porLevel2PosLevelMap.put(HrConstant.LEVEL_SEVEN, HrConstant.TECHPOSITIONLEVEL_TWO);
        porLevel2PosLevelMap.put(HrConstant.LEVEL_SIX, HrConstant.TECHPOSITIONLEVEL_TWO);
        porLevel2PosLevelMap.put(HrConstant.LEVEL_FIVE, HrConstant.TECHPOSITIONLEVEL_THREE);
        porLevel2PosLevelMap.put(HrConstant.LEVEL_FOUR, HrConstant.TECHPOSITIONLEVEL_THREE);
        porLevel2PosLevelMap.put(HrConstant.LEVEL_THREE, HrConstant.TECHPOSITIONLEVEL_FOUR);
        porLevel2PosLevelMap.put(HrConstant.LEVEL_TWO, HrConstant.TECHPOSITIONLEVEL_FOUR);
        porLevel2PosLevelMap.put(HrConstant.LEVEL_ONE, HrConstant.TECHPOSITIONLEVEL_FOUR);

        /*
         * HrConstant.TECHPOSITIONLEVEL_ONE：初级； HrConstant.TECHPOSITIONLEVEL_TWO：中级；
         * HrConstant.TECHPOSITIONLEVEL_THREE：副高； HrConstant.TECHPOSITIONLEVEL_FOUR：正高
         */
        warmMap.put(HrConstant.TECHPOSITIONLEVEL_ONE, "08");
        warmMap.put(HrConstant.TECHPOSITIONLEVEL_TWO, "06");
        warmMap.put(HrConstant.TECHPOSITIONLEVEL_THREE, "04");
        warmMap.put(HrConstant.TECHPOSITIONLEVEL_FOUR, "01");

        /**
         * personalInformation:个人资料 teacherQualification:教师资格认定 proTechPosition:职称履历 recruitment:校内岗位履历 assessment:历年考核
         * insuredSituation:社会保障 personnelAgency:人事档案 edu:教育经历 work:工作经历
         */
        exportSelectedItem.put("personalInformation", "个人资料");
        exportSelectedItem.put("teacherQualification", "教师资格认定");
        exportSelectedItem.put("proTechPosition", "职称履历");
        exportSelectedItem.put("recruitment", "校内岗位履历");
        exportSelectedItem.put("assessment", "历年考核");
        exportSelectedItem.put("insuredSituation", "社会保障");
        exportSelectedItem.put("insuranceRecord", "参保记录");
        exportSelectedItem.put("personnelAgency", "人事档案");
        exportSelectedItem.put("edu", "教育经历");
        exportSelectedItem.put("work", "工作经历");
    }

    /**
     * 根据用户工作量，调整用户的课酬金。<br/>
     * 
     * @param salaryInsId 工资实例ID
     * @param userId 用户ID
     * @param work 用户工作量
     */
    public static void synchWorkSalary(String salaryInsId, String userId, double work, double workLine) {
        SalEmpView salEmp = salEmpViewDao.get(userId);
        SalaryDetails sDetailsData = new SalaryDetails();
        sDetailsData.setUser(new User(userId));
        sDetailsData.getSalaryInstance().setId(salaryInsId);
        /* 取得当前职员的本次工资详情。 */
        sDetailsData = salaryDetailsDao.get(sDetailsData);
        // /* 如果当前职员岗位类型为教师（1：教师岗位）根据工作量，调整岗位工资。 */
        // /* HrConstant.POST_TYPE_1 :教师 */
        // if (HrConstant.POST_TYPE_1.equals(salEmp.getPostType())) {
        // double salPost = calPostSalary(salEmp, work);
        // sDetailsData.setPostSalary(salPost);
        //
        // }
        double classFee = calcClassFee(salEmp, work, workLine);
        sDetailsData.setClassFee(classFee);
        salaryDetailsDao.update(sDetailsData);

    }

    /**
     * 调整【低职高聘】 【 高职低聘】人员的职级工资，绩效工资<br/>
     * 根据聘任情况，调整工资中对应的职级工资级别（修改职级等级）。<br/>
     * 低职高聘下：初级-（八级）08；中级-（六级）06；副高-（四级）04； 高职低聘下：中级-（七级）07；副高-（五级）05；正高-（三级）03 根据职级获取职级工资、绩效工资，调整工资。
     * 
     * @param data 人员信息
     * @param sal 4项工资
     */

    public static void calcProSalary(SalView sal, SalEmpView data) {
        SalEmpView salemp = new SalEmpView();
        String result = new String();
        /* positionLevel 职称级别 */
        String positionLevel = data.getMaxTechPositionLevel();
        /* engageSituation 聘任情况（1:低职高聘;2:高职低聘） */
        String engageSituation = data.getEngageSituation();
        /*
         * HrConstant.ENGAGE_LOW_TO_HIGH:低职高聘; HrConstant.ENGAGE_HIGH_TO_LOW:高职低聘
         */
        if (HrConstant.ENGAGE_LOW_TO_HIGH.equals(engageSituation)) {
            result = low2HighMap.get(positionLevel);
            /* 修改职级等级 */
            salemp.setProfessionalLevel(result);
        } else if (HrConstant.ENGAGE_HIGH_TO_LOW.equals(engageSituation)) {
            result = high2LowMap.get(positionLevel);
            /* 修改职级等级 */
            salemp.setProfessionalLevel(result);
        }
        /*
         * 绑定查询条件 1.用户名 2.岗位类型
         */
        if (data.getPostType() != null) {

            salemp.setId(data.getId());
            salemp.setPostType(data.getPostType());
            SalView tempSal = salViewDao.findSalByUser(salemp);
            if (tempSal == null) {
                sal.setProfessionalLevelSalary(0);
                sal.setPerformanceSalary(0);
            } else {
                /* 职级工资 */
                sal.setProfessionalLevelSalary(tempSal.getProfessionalLevelSalary());
                /* 绩效工资 */
                sal.setPerformanceSalary(tempSal.getPerformanceSalary());
            }
        }

    }

    /**
     * 根据职员试用期时间，调整岗位工资对应的级别（岗位等级） 试用期岗位工资降一级。 根据岗位等级获取岗位工资，调整工资。
     * 
     * @param data人员信息
     * @param sal 4项工资
     */
    private static void calcPostSalary(SalView sal, SalEmpView data) {
        if (data.getPostLevel() != null && data.getPostType() != null) {

            SalEmpView salemp = new SalEmpView();
            /* 岗位等级 */
            String postLevel = data.getPostLevel();
            /* 岗位等级降一级 */
            int level = Integer.parseInt(postLevel) + 1;
            if (level > 9) {
                level = 9;
            }
            postLevel = "0" + level;

            /* 修改岗位等级 */
            salemp.setPostLevel(postLevel);
            salemp.setId(data.getId());
            salemp.setPostType(data.getPostType());
            SalView tempSal = salViewDao.findSalByUser(salemp);
            sal.setPostSalary(tempSal.getPostSalary());
        }
    }

    /**
     * 调整职员职级工资对应的级别（职级等级） 如果职员的职级与岗位等级不同，则根据职级调整职级工资。
     * 
     * @param data人员信息
     * @param sal 4项工资
     */
    private static void calcClerkProSalary(SalView sal, SalEmpView data) {
        SalEmpView salemp = new SalEmpView();
        /* 职级 */
        salemp.setProfessionalLevel(data.getProfessionalLevel());
        salemp.setPostType(data.getPostType());
        salemp.setId(data.getId());
        SalView tempSal = salViewDao.findSalByUser(salemp);
        sal.setProfessionalLevelSalary(tempSal.getProfessionalLevelSalary());
    }

    /**
     * 根据工作量调整岗位工资，如果工作量小于40，则按比例获取工资 实际/应做 *工资标准
     * 
     * @param work 工作量
     * @param salEmp 人员信息
     * @return 修改后岗位工资
     */
    // public static double calPostSalary(SalEmpView salEmp, double work) {
    // SalView salview = new SalView();
    // double salary = 0;
    // salview = salViewDao.get(salEmp.getId());
    // if (work < 40) {
    // salary = salview.getPostSalary() * work / 40;
    // }
    // return salary;
    // }

    /**
     * 根据工作量调整岗位津贴，如果工作量小于200，则按比例获取津贴 实际/应做 *津贴标准
     * 
     * @param salEmp 人员信息
     * @param sal 4项工资
     */
    private static void calcPostSubside(SalView sal, SalEmpView salEmp) {
    	if(salEmp.getId().equals("f28b6556d4af4e03ab20095aef734b15")){
    		System.out.println();
    	}

        Calendar nowCal = Calendar.getInstance();
        nowCal.add(Calendar.MONTH, -6);
        String yearMonth = DateUtils.formatDate(nowCal.getTime(), "yyyyMM");
        ReportedWorkloade data = new ReportedWorkloade();
        data.setId(salEmp.getId());
        data.getReportRecord().setYearMonth(yearMonth);
        /* 取得该用户近6个月的工作量总和。 */
        Integer work = reportedWorkloadeDao.sumWorkHalfYear(data);
        SalaryDetails details = new SalaryDetails();
        details.setUser(UserUtils.get(salEmp.getId()));
        details.setYearMonth(yearMonth);
        /*取得该用户近5个月的岗位工资总和。*/
        double postSalary = salaryDetailsDao.getHalfYearPostSalary(details);
        /*取得该用户近5个月的课酬金总和。*/
        double classFee = salaryDetailsDao.getHalfYearClassFee(details);
        /*前5个月岗位工资和课酬金的总和*/
        double sum1 = postSalary + classFee;
        /*前5个月的岗位工资和岗位津贴（本月）的总和*/
        double s1 = postSalary + sal.getPostSubside();
        double salary = 0;
        if(work ==null){
        	salary=0;
        }else if(work < 200) {
            salary = (work / 200.0 - 1) * s1;
        }else{
        	salary = (work - 200.0) * taxclass(salEmp).getClassPay();
        }
        double sum2 = salary + s1;
        sal.setPostSubside(sum2 - sum1);

    }

    /**
     * 计算班主任费
     * 
     * @param salEmp
     * @return
     */
    private static double teacherChargeFee(SalEmpView salEmp) {
        /* 带班1月5个，5个月，半年结算一次，一个班300 */
        return 0;
    }

    /**
     * 通过工作量，计算应发课酬。<br/>
     * 先取得职员对应的课酬标准。再根据岗位类型计算课酬金。<br/>
     * 教师岗位：判断work的值，是否大于 教师岗位每月基本工作量参考线（每月40个课时）。如果大于，计算课酬金。否则，课酬金为0.<br/>
     * 非教师岗位：按照实际工作量乘以课酬标准。<br/>
     * 
     * @param salEmp 人员信息
     * @param work 工作量
     * @return 课酬金
     */
    public static double calcClassFee(SalEmpView salEmp, double work, double workLine) {
        double salary = 0;
   
        ClassPayStandard classPayStandard = taxclass(salEmp);
        /* HrConstant.POST_TYPE_1 ：教师 */
        /* HrConstant.POST_TYPE_6======6 ： 非在编专任教师 */
        if (salEmp.getPostType().equals(HrConstant.POST_TYPE_1)
                || salEmp.getPostType().equals(HrConstant.POST_TYPE_6)) {
            /* 判断work的值，是否大于 教师岗位每月基本工作量参考线（每月40个课时）。如果大于，计算课酬金。否则，课酬金为0. */
            /* HrConstant.CLASS_WORK_MONTH_BASE_LINE 教师岗位每月基本工作量参考线:每月40个课时。 */

            if (work > workLine) {
                /* 计算课酬金。work的值减去基本线后，再乘以标准。 */
                double extrawork = work - workLine;
                salary = extrawork * classPayStandard.getClassPay();
            } else {
                SalView salview = new SalView();
                double coefficient = (workLine - work) / 200 * 5.8 / 0.8;
                salview = salViewDao.get(salEmp.getId());
                salary = -coefficient * salview.getPostSalary();
                salary = salary + salview.getPostSubside() * (workLine / 200);
            }
        } else {
            /* 非教师岗位，直接计算课酬金。按照实际工作量乘以课酬标准。 */
            salary = work * classPayStandard.getClassPay();
        }
        return salary;
    }
    /** 取得职员对应的课酬标准
     * @param salEmp
     * @return
     */
    public static ClassPayStandard taxclass(SalEmpView salEmp){
        ClassPayStandard classPayStandard = new ClassPayStandard();
        /*
         * 设置查询条件。按照 职称级别:1 :初级; 2 : 中级; 3 : 副高;4: 正高; 聘任方式:1 : 内聘; 2 : 外聘;
         * 特别指出，计算见习期人员的课酬标准时，将其【职称】值设置为‘0’，【聘任方式】为‘1’即可。
         */
        /* HrConstant.ENGAGE_IN：内聘 */
        classPayStandard.setEngageMode(HrConstant.ENGAGE_IN);
        /*
         * HrConstant.POST_LEVEL_NOVITIATE:见习期 HrConstant.POST_LEVEL_NOVITIATE_EXPIRY:见习期满
         */
        if (HrConstant.POST_LEVEL_NOVITIATE.equals(salEmp.getPostLevel())
                || HrConstant.POST_LEVEL_NOVITIATE_EXPIRY.equals(salEmp.getPostLevel())) {
            /* HrConstant.INITIAL_TECHPOSITION :0（见习期职称） */
            classPayStandard.setTechPosition(HrConstant.INITIAL_TECHPOSITION);
        } else {
            /* 取得该职员对应的职级。因为存在高职低聘、低职高聘现象，故按照现聘职级，取得对应的职称级别。 */
            /* 取得职级对应的职称级别 */
            String profLevel = porLevel2PosLevelMap.get(salEmp.getProfessionalLevel());

            classPayStandard.setTechPosition(profLevel);
            /* 非教师岗位的职员，课酬标准默认按照（1:初级职称）标准 */
            /* HrConstant.POST_TYPE_1 : 教师 */
            if (!salEmp.getPostType().equals(HrConstant.POST_TYPE_1)) {

                /* 非教师岗位的职员，如果有职称，按照对应的职称系列标准计算课酬。 没有则按初级职称计算课酬。 */
                /* HrConstant.TECHPOSITIONLEVEL_ONE:初级（职称级别） */
                classPayStandard.setTechPosition(salEmp.getMaxTechPositionLevel() == null
                        ? HrConstant.TECHPOSITIONLEVEL_ONE : salEmp.getMaxTechPositionLevel());
            }
        }
        /* 取得对应的课酬标准 */
        classPayStandard = HrSalStandardUtil.getClassPayStandard(classPayStandard);
        return classPayStandard;
    }

    /**
     * 根据岗位类型，职级获取取暖费用
     * 
     * @param salEmp 人员信息
     * @return 取暖费用
     */
    private static double heatingCosts(SalEmpView salEmp) {
        double salary = 0;
        /* 岗位类型为6 ： 非在编专任教师； 7 ： 外聘教师;的两种职员，不发放取暖费。 */
        if (HrConstant.POST_TYPE_6.equals(salEmp.getPostType())
                || HrConstant.POST_TYPE_7.equals(salEmp.getPostType())) {
            return salary;
        }
        Warm warm = new Warm();
        warm.setPostType(salEmp.getPostType());
        /* HrConstant.POST_TYPE_CLERK:职员 */
        if (HrConstant.POST_TYPE_2.equals(salEmp.getPostType())) {
            warm.setClassify(salEmp.getPostLevel());
        } else {
            warm.setClassify(salEmp.getProfessionalLevel());

        }
        if (warm.getClassify() != null) {
            warm = HrSalStandardUtil.getWarmStandard(warm);
            salary = warm.getChargeStandard();
        }
        return salary;
    }

    /**
     * 根据职员职称获取取暖费用 1：初级；2：中级； 3：副高；4：正高
     * 
     * @param salEmp 人员信息
     * @return 取暖费用
     */
    private static double poheatingCosts(SalEmpView salEmp) {
        double salary = 0;
        Warm warm = new Warm();
        warm.setPostType(salEmp.getPostType());
        warm.setClassify(warmMap.get(salEmp.getMaxTechPositionLevel()));
        warm = HrSalStandardUtil.getWarmStandard(warm);
        salary = warm.getChargeStandard();
        return salary;
    }

    /**
     * 高职低聘取出职称级别对应的职级，根据职级获取取暖费用
     * 
     * @param salEmp 人员信息
     * @return 取暖费用
     */
    private static double teheatingCosts(SalEmpView salEmp) {
        String result = new String();
        double salary = 0;
        Warm warm = new Warm();
        result = high2LowMap.get(salEmp.getMaxTechPositionLevel());
        warm.setPostType(salEmp.getPostType());
        warm.setClassify(result);
        warm = HrSalStandardUtil.getWarmStandard(warm);
        salary = warm.getChargeStandard();
        return salary;
    }

    /**
     * 计算取暖费用
     * 
     * @param salEmp 人员信息
     * @return 取暖费用
     */
    private static double calCosts(SalEmpView salEmp) {
        double salary = 0;

        /* 取暖费用 1:低职高聘;2:高职低聘 目前只有 1:教师 3:教辅岗位存在这种现象。 */
        /* HrConstant.ENGAGE_HIGH_TO_LOW :高职低聘 */
        if (HrConstant.ENGAGE_HIGH_TO_LOW.equals(salEmp.getEngageSituation())) {
            salary = teheatingCosts(salEmp);
        }
        /* 担任主任助理及以上的教师即教师的岗位等级小于6时，取暖费用为3200. */
        /* HrConstant.POST_TYPE_1 :教师 */
        if (HrConstant.POST_TYPE_1.equals(salEmp.getPostType()) && null != salEmp.getPostLevel()) {
            if (Integer.parseInt(salEmp.getPostLevel()) < 6) {
                salary = 3200;
            }

        }
        /* HrConstant.POST_TYPE_CLERK:职员 */
        if (HrConstant.POST_TYPE_2.equals(salEmp.getPostType()) && salEmp.getMaxTechPositionLevel() != null) {
            salary = poheatingCosts(salEmp);
        }
        /* 取暖费用脱产减半 是否脱产 1:是;2:否 */
        /* HrConstant.ISOFFJOB_SITUATION:是 */
        if (HrConstant.ISOFFJOB_SITUATION.equals(salEmp.getIsOffJob())) {
            salary = salary / 2;
        }
        return salary;
    }

    /**
     * 计算 教师,辅导员职务补助.
     * 
     * @param salEmp 人员信息
     * @return 职务补助费用
     */
    private static double positionSubsidies(SalEmpView salEmp) {
        double salary = 0;
        if (StringUtils.isBlank(salEmp.getDuties())) {
            return salary;
        }
        if (!(HrConstant.POST_TYPE_1.equals(salEmp.getPostType())||HrConstant.POST_TYPE_3.equals(salEmp.getPostType())
                || HrConstant.POST_TYPE_4.equals(salEmp.getPostType()))) {
            return salary;
        }
        SubsidiaryStandard subsidiary = new SubsidiaryStandard();
        /* HrConstant.POSITION_SUBSIDIES:职务补助 */
        subsidiary.setAllowanceType(HrConstant.POSITION_SUBSIDIES);
        /* 职务补助 */
        /* HrConstant.POST_TYPE_1 :教师 */
        if (HrConstant.POST_TYPE_1.equals(salEmp.getPostType())||HrConstant.POST_TYPE_3.equals(salEmp.getPostType())) {
            subsidiary.setAllowanceMainBody(salEmp.getDuties());
        }
        /* HrConstant.POST_TYPE_COUNSELOR :专职辅导员 */
        /* HrConstant.SUBSIDIES_BODY_COUNSELOR:主任辅导员 */
        if (HrConstant.POST_TYPE_4.equals(salEmp.getPostType())
                && HrConstant.SUBSIDIES_BODY_COUNSELOR.equals(salEmp.getDuties())) {
            subsidiary.setAllowanceMainBody(salEmp.getDuties());
        }
        if (subsidiary != null) {
            subsidiary = HrSalStandardUtil.getSubsidiary(subsidiary);
        }
        if (subsidiary != null) {
            salary = subsidiary.getChargeStandard();
        }
        return salary;
    }

    /**
     * 根据独子到期时间是否晚于现在获取独子费
     * 
     * @param salEmp 人员信息
     * @return 独子费
     */
    private static double theOnlyFee(SalEmpView salEmp) {
        double salary = 0;
        SubsidiaryStandard subsidiary = new SubsidiaryStandard();
        if (salEmp.getOnlyChildEndDate() != null) {
            if (salEmp.getOnlyChildEndDate().after(new Date())) {
                /* HrConstant.THE_ONLY_FEE :独子费 */
                subsidiary.setAllowanceType(HrConstant.THE_ONLY_FEE);
                subsidiary = HrSalStandardUtil.getSubsidiary(subsidiary);
                salary = subsidiary.getChargeStandard();
            }
        }
        return salary;
    }

    /**
     * 根据人员性别为女获取卫生费
     * 
     * @param salEmp 人员信息
     * @return 卫生费
     */
    public static double healthCosts(SalEmpView salEmp) {
        double salary = 0;
        if (salEmp.getGender() != null) {
            SubsidiaryStandard subsidiary = new SubsidiaryStandard();
            /* HrConstant.GENDER_FEMALE :女 */
            if (HrConstant.GENDER_FEMALE.equals(salEmp.getGender())) {
                /* HrConstant.HEALTH_COSTS :卫生费 */
                subsidiary.setAllowanceType(HrConstant.HEALTH_COSTS);
                subsidiary = HrSalStandardUtil.getSubsidiary(subsidiary);
                salary = subsidiary.getChargeStandard();
            }
        }
        return salary;
    }

    /**
     * 计算院龄津贴。 获取院龄津贴根据院龄返回真实的院龄津贴
     * 
     * @param salEmp 人员信息
     * @return 院龄津贴
     */
    private static double schoolAgeSubside(SalEmpView salEmp) {
        int schoolAge = 0;
        /* 计算院龄 */
        if (salEmp.getInSchoolDate() != null) {
            schoolAge = HrUtils.schoolAgeYear(salEmp.getInSchoolDate(), salEmp.getDifferenceValue());
        }
        /* 获取津贴标准 */
        SubsidiaryStandard entity = new SubsidiaryStandard();
        /* HrConstant.SCHOOLAGE_SUBSIDIES :院龄津贴 */
        entity.setAllowanceType(HrConstant.SCHOOLAGE_SUBSIDIES);
        entity = HrSalStandardUtil.getSubsidiary(entity);
        double salary = entity.getChargeStandard();

        return schoolAge * salary;
    }

    /**
     * 计算院龄 院龄 = 当前时间-到校时间 -差值
     * 
     * @param inSchoolDate 到校时间
     * @param diff 差值
     * @return 院龄
     */
    public static int schoolAgeYear(Date inSchoolDate, String diff) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        if (diff == null) {
            diff = "0";
        }
        int inSchool = Integer.valueOf(sdf.format(inSchoolDate));
        int sysDate = Integer.valueOf(sdf.format(new Date()));
        int schoolAge = sysDate - inSchool - Integer.valueOf(diff) + 1;
        return schoolAge;

    }

    /**
     * 获取新增补贴
     * 
     * @return 新增补贴
     */
    private static double newSubsidies() {
        SubsidiaryStandard entity = new SubsidiaryStandard();
        /* HrConstant.NEW_SUBSIDIES:新增补贴 */
        entity.setAllowanceType(HrConstant.NEW_SUBSIDIES);
        entity = HrSalStandardUtil.getSubsidiary(entity);
        double salary = entity.getChargeStandard();
        return salary;
    }

    /**
     * 根据精神文明奖的类型获取精神文明奖
     * 
     * @param type 精神文明奖的类型
     * @return 精神文明奖补助
     */
    private static double spiritualCivilization(String type, SalEmpView data) {
        double salary = 0;

        SubsidiaryStandard subsidiary = new SubsidiaryStandard();
        subsidiary.setAllowanceType(type);
        subsidiary = HrSalStandardUtil.getSubsidiary(subsidiary);
        salary = subsidiary.getChargeStandard();
        /* 是否脱产 1:是;2:否 */
        /* HrConstant.ISOFFJOB_SITUATION :是 */
        if (HrConstant.ISOFFJOB_SITUATION.equals(data.getIsOffJob())) {
            salary = salary / 2;
        }
        Calendar nowCal = Calendar.getInstance();
        if (data.getInSchoolDate() != null) {

            String inSchool = DateUtils.formatDate(data.getInSchoolDate(), "yyyyMMdd");

            /* HrConstant.SPIRITUAL_CIVILIZATION_TYPE_ONE:精神文明奖1 */
            /* 当年7月1日后到校的教职工按标准减半发放 */
            /* HrConstant.SPIRITUAL_CIVILIZATION_STANDARD_MONTH_DAY : 0701(7月1号) */
            if (HrConstant.SPIRITUAL_CIVILIZATION_TYPE_ONE.equals(type)) {

                String standardDate = nowCal.get(Calendar.YEAR) + HrConstant.SPIRITUAL_CIVILIZATION_STANDARD_MONTH_DAY;

                if (standardDate.compareTo(inSchool) <= 0) {
                    salary = salary / 2;
                }
            } else if (HrConstant.SPIRITUAL_CIVILIZATION_TYPE_TWO.equals(type)) {
                /* HrConstant.SPIRITUAL_CIVILIZATION_TYPE_TWO:精神文明奖2 */
                /* 到校刚满或未满六个月人员按标准减半发放。 */

                nowCal.add(Calendar.MONTH, -6);
                String standardDate = DateUtils.formatDate(nowCal.getTime(), "yyyyMMdd");
                if (standardDate.compareTo(inSchool) <= 0) {
                    salary = salary / 2;
                }
            }
        }
        return salary;
    }

    /**
     * 根据脱产情况调整绩效工资（是否脱产 1:是;2:否 ） 脱产则将绩效档级改为3档，再根据绩效档级获取绩效工资
     * 
     * @param sal 4项工资
     * @param data 人员信息
     */
    private static void calcOffJobPerformance(SalView sal, SalEmpView data) {
        if (data.getPostType() != null) {

            SalEmpView salemp = new SalEmpView();
            /* 修改绩效档级 */
            /* HrConstant.PERFORMANCE_GRADES:绩效档级 3档 */
            salemp.setProformanceLevel(HrConstant.PERFORMANCE_GRADES);
            salemp.setId(data.getId());
            salemp.setPostType(data.getPostType());

            SalView tempSal = salViewDao.findSalByUser(salemp);
            sal.setPerformanceSalary(tempSal.getPerformanceSalary());
        }

    }

    /**
     * 根据用户编号获取险种，并获取对应的基数和缴费比例 扣除的钱 = 基数 * 比例 基本养老保险、 基本医疗保险、失业保险、工伤保险、生育保险、职业年金、公务员医疗补助的比例是个人比例
     * 住房公积金的比例是单位比例是住房公积金a，个人比例是住房公积金b INSUREDSITUATION_1：基本养老保险 INSUREDSITUATION_2： 基本医疗保险 INSUREDSITUATION_3：失业保险
     * INSUREDSITUATION_4：工伤保险 INSUREDSITUATION_5：生育保险 INSUREDSITUATION_6：职业年金 INSUREDSITUATION_7：公务员医疗补助
     * INSUREDSITUATION_8：住房公积金
     */
    private static void insuredSituation(SalEmpView emp, SalaryDetails salData) {

        InsuredSituation data = new InsuredSituation();
        data.setUser(new User());
        data.getUser().setId(emp.getId());
        List<InsuredSituation> insuredSituationList = insuredSituationDao.findList(data);
        /* 无社保记录则扣钱为0 */
        if (insuredSituationList == null) {
            salData.setBuckleEndowmentInsurance(0.0);
            salData.setBuckleHealthCare(0.0);
            salData.setBuckleUnemploymentInsurance(0.0);
            salData.setBirthInsurance(0.0);
            salData.setBuckleRoomA(0.0);
        } else {
            for (InsuredSituation insuredSituation : insuredSituationList) {
                double date1 = Double.valueOf(DateUtils.formatDate("yyyy", insuredSituation.getSchoolInsuredDate()));
                double date2 = Double.valueOf(DateUtils.formatDate("yyyy", new Date())) - 1;
                if (date2 == date1) {

                    if (insuredSituation.getBase() < 2648) {
                        insuredSituation.setBase(2648);
                        taxInsuredSituation(insuredSituation, salData);
                    } else if (insuredSituation.getBase() > 13240) {
                        insuredSituation.setBase(13240);
                        taxInsuredSituation(insuredSituation, salData);
                    } else {
                        taxInsuredSituation(insuredSituation, salData);
                    }

                }
            }
            if (HrConstant.ESTABLISHMENTSITUATION_1.equals(emp.getEstablishmentSituation())) {
                cInsuredSituation(emp, insuredSituationList, salData);
            }
            /* 计算补缴社保 */
            paymentSocialSecurity(emp, salData);
        }

    }

    /**
     * 根据用户编号获取险种，并获取对应的基数和缴费比例 扣除的钱 = 基数 * 比例
     * 
     * @param insuredSituation
     * @param salData
     */
    public static void taxInsuredSituation(InsuredSituation insuredSituation, SalaryDetails salData) {

        double data = insuredSituation.getInsuranceRule().getPeopleProportion();
        double salary = insuredSituation.getBase() * data / 100;
        if (HrConstant.INSUREDSITUATION_1.equals(insuredSituation.getInsuranceRule().getId())) {
            salData.setBuckleEndowmentInsurance(salary);
        } else if (HrConstant.INSUREDSITUATION_2.equals(insuredSituation.getInsuranceRule().getId())) {
            salData.setBuckleHealthCare(salary);
        } else if (HrConstant.INSUREDSITUATION_3.equals(insuredSituation.getInsuranceRule().getId())) {
            salData.setBuckleUnemploymentInsurance(salary);
        } else if (HrConstant.INSUREDSITUATION_4.equals(insuredSituation.getInsuranceRule().getId())) {
            salData.setBuckEmpInjuryInsurance(salary);
        } else if (HrConstant.INSUREDSITUATION_5.equals(insuredSituation.getInsuranceRule().getId())) {
            salData.setBuckMaternityInsurance(salary);
        } else if (HrConstant.INSUREDSITUATION_6.equals(insuredSituation.getInsuranceRule().getId())) {
            salData.setBuckOccupationalAnnuity(salary);
        } else if (HrConstant.INSUREDSITUATION_7.equals(insuredSituation.getInsuranceRule().getId())) {
            salData.setBuckgongyi(salary);
        } else if (HrConstant.INSUREDSITUATION_8.equals(insuredSituation.getInsuranceRule().getId())) {
            salData.setBuckleRoomB(salary);
            double data1 = insuredSituation.getInsuranceRule().getUnitProportion();
            double salary1 = insuredSituation.getBase() * data1 / 100;
            salData.setBuckleRoomA(salary1);
            salData.setFillHousingFund(salary1);
        }
    }

    public static void cInsuredSituation(SalEmpView emp, List<InsuredSituation> insuredSituationList,
            SalaryDetails salData) {
        for (InsuredSituation insuredSituation : insuredSituationList) {
            double date1 = Double.valueOf(DateUtils.formatDate("yyyy", insuredSituation.getSchoolInsuredDate()));
            double date2 = Double.valueOf(DateUtils.formatDate("yyyy", new Date())) - 2;
            if (date2 == date1) {
                double data = insuredSituation.getInsuranceRule().getPeopleProportion();
                double salary = insuredSituation.getBase() * data / 100;
                if (HrConstant.INSUREDSITUATION_1.equals(insuredSituation.getInsuranceRule().getId())) {
                    salData.setBuckleEndowmentInsurance(salary);
                } else if (HrConstant.INSUREDSITUATION_6.equals(insuredSituation.getInsuranceRule().getId())) {
                    salData.setBuckOccupationalAnnuity(salary);
                }
            }
        }
    }

    /**
     * 计算补缴社保 （比例改变月医保、养保、失保的和 -前一个月医保、养保、失保的和）* 月间隔数
     * 
     * @param emp
     * @param salData
     */
    public static void paymentSocialSecurity(SalEmpView emp, SalaryDetails salData) {
        double socialSecurity1 = (salData.getBuckleHealthCare() + salData.getBuckleUnemploymentInsurance()
                + salData.getBuckleEndowmentInsurance());
        SalaryDetails data = new SalaryDetails();
        Calendar nowCal = Calendar.getInstance();
        nowCal.add(Calendar.MONTH, -1);
        String time = DateUtils.formatDate("yyyyMM", nowCal.getTime());
        data.setUser(UserUtils.get(emp.getId()));
        data.setYearMonth(time);
        double socialSecurity2 = salaryDetailsDao.getLatestMaxInsure(data);
        if (socialSecurity2 > 0) {
            double tax = Double.valueOf(DateUtils.getMonth()) - 1;
            double salary = (socialSecurity1 - socialSecurity2) * tax;
            salData.setPaymentSocialSecurity(salary);
        }
    }

    public static List<SalaryDetails> calculateSalary() {
        /* 工资信息 */
        List<SalaryDetails> salDetailsList = Lists.newArrayList();
        /* 4项工资 */
        List<SalView> salList = salViewDao.findList(new SalView());

        for (SalView sal : salList) {
            SalaryDetails salData = new SalaryDetails();
            String userId = sal.getId();
            SalEmpView emp = salEmpViewDao.get(userId);
            /* 在编教师计算社保、独子费、卫生费、院龄津贴、取暖费用 */
            if (HrConstant.STAFF_TYPE_1.equals(emp.getStaffType())) {
                /* 计算社保的扣除 */
                insuredSituation(emp, salData);
                /* 独子费 */
                salData.setTheOnlyFee(theOnlyFee(emp));
                /* 卫生费 */
                salData.setHealthCosts(healthCosts(emp));
                /* 院龄津贴 */
                salData.setSchoolAgeSubside(schoolAgeSubside(emp));
                /* 取暖费用 */
                salData.setHeatingCosts(heatingCosts(emp));

                /* 新增补贴 */
                salData.setNewSubsidies(newSubsidies());
                /*
                 * 精神文明奖1 HrConstant.SPIRITUAL_CIVILIZATION_TYPE_ONE:精神文明奖1
                 */
                salData.setSpiritualCivilization1(
                        spiritualCivilization(HrConstant.SPIRITUAL_CIVILIZATION_TYPE_ONE, emp));
                /*
                 * 精神文明奖2 HrConstant.SPIRITUAL_CIVILIZATION_TYPE_TWO:精神文明奖2
                 */
                salData.setSpiritualCivilization2(
                        spiritualCivilization(HrConstant.SPIRITUAL_CIVILIZATION_TYPE_TWO, emp));
                /*
                 * 精神文明奖3 HrConstant.SPIRITUAL_CIVILIZATION_TYPE_THREE: 精神文明奖3
                 */
                salData.setSpiritualCivilization3(
                        spiritualCivilization(HrConstant.SPIRITUAL_CIVILIZATION_TYPE_THREE, emp));
                /* 职务补助 */
                salData.setPositionSubsidies(positionSubsidies(emp));

                if (salData.getHeatingCosts() < calCosts(emp)) {
                    salData.setHeatingCosts(calCosts(emp));
                }
            }
            /* 如果该职员是见习期、见习期满，不对四项工资进行调整 */
            /*
             * HrConstant.POST_LEVEL_NOVITIATE：见习期，HrConstant. POST_LEVEL_NOVITIATE_EXPIRY:见习期满
             */
            if (!(HrConstant.POST_LEVEL_NOVITIATE.equals(emp.getPostLevel())
                    || HrConstant.POST_LEVEL_NOVITIATE_EXPIRY.equals(emp.getPostLevel()))) {

                /* HrConstant.POST_TYPE_1:教师 */
                if (HrConstant.POST_TYPE_1.equals(emp.getPostType())) {
                    /*
                     * HrConstant.ENGAGE_LOW_TO_HIGH :低职高聘;HrConstant.ENGAGE_LOW_TO_HIGH:高职低聘
                     */
                    /* 根据高职低聘、低职高聘调整职级工资和绩效工资 */
                    if (HrConstant.ENGAGE_LOW_TO_HIGH.equals(emp.getEngageSituation())
                            || HrConstant.ENGAGE_HIGH_TO_LOW.equals(emp.getEngageSituation())) {
                        calcProSalary(sal, emp);
                    }
                    /*
                     * 绩效工资 是否脱产 HrConstant.ISOFFJOB_SITUATION:是
                     */
                    if (HrConstant.ISOFFJOB_SITUATION.equals(emp.getIsOffJob())) {
                        calcOffJobPerformance(sal, emp);
                    }
                    /* 计算近6个月的工作量总数，调整岗位津贴 */
                    calcPostSubside(sal, emp);
                }
                /* HrConstant.POST_TYPE_CLERK:职员 */
                if (HrConstant.POST_TYPE_2.equals(emp.getPostType())) {
                    /* 职级工资 */
                    /* 职员的职级与岗位等级不相等时，根据职员的职级调整职级工资 */
                    if (emp.getPostLevel() != null && emp.getProfessionalLevel() != null) {

                        if (!emp.getPostLevel().equals(emp.getProfessionalLevel())) {
                            calcClerkProSalary(sal, emp);
                        }
                    }
                    /* 岗位工资 */
                    /* 根据职员的试用期截止时间是否晚于现在，是则调整岗位工资降一级 */
                    if (emp.getTryOutDate() != null && emp.getTryOutDate().after(new Date())) {
                        calcPostSalary(sal, emp);
                    }
                }
                /* HrConstant.POST_TYPE_SUPPLEMENTARY:教辅 */
                if (HrConstant.POST_TYPE_3.equals(emp.getPostType())) {
                    /*
                     * HrConstant.ENGAGE_LOW_TO_HIGH :低职高聘;HrConstant.ENGAGE_LOW_TO_HIGH:高职低聘
                     */
                    /* 根据高职低聘、低职高聘调整职级工资和绩效工资 */
                    if (HrConstant.ENGAGE_LOW_TO_HIGH.equals(emp.getEngageSituation())
                            || HrConstant.ENGAGE_LOW_TO_HIGH.equals(emp.getEngageSituation())) {
                        calcProSalary(sal, emp);
                    }
                }
            }
            /* 将岗位工资存入职员工资信息中 */
            salData.setPostSalary(sal.getPostSalary());
            /* 将职级工资存入职员工资信息中 */
            salData.setProfessionalLevelSalary(sal.getProfessionalLevelSalary());
            /* 将绩效工资存入职员工资信息中 */
            salData.setPerformanceSalary(sal.getPerformanceSalary());
            /* 将岗位津贴存入职员工资信息中 */
            salData.setPostSubside(sal.getPostSubside());
            /* 职员ID */
            salData.setUser(new User(sal.getId()));
            /* 将各项工资存入总的工资信息表中 */
            salDetailsList.add(salData);
        }
        return salDetailsList;
    }

    /**
     * 计算值班的钱
     * 
     * @param dutyDetail
     * @param id
     * @return
     */
    public static double calculateDutyDetail(DutyDetail dutyDetail, String id) {
        double sumDutyDetailSalary = 0;
        List<DutyDetail> list = dutyDetailDao.findList(dutyDetail);
        List<Duty> listDuty = dutyDao.findList(new Duty());
        for (DutyDetail data : list) {
            for (Duty duty : listDuty) {
                /* 如果取到的id为要计算的人的id则再判断值班类型，若两者都相等则将值班天数×值班标准，并返回 */
                if (data.getUser().getId().equals(id) && duty.getDutyType().equals(data.getDutyType())) {
                    sumDutyDetailSalary += data.getDutyDays() * duty.getChargeStandard();
                }
            }
        }
        return sumDutyDetailSalary;
    }

    /**
     * 同步对应的上报的信息，并修改其所影响的工资值
     * 
     * @param reportRecord 上报记录实例
     */
    public static void synchronousInfo(ReportRecord reportRecord) {
        /* 取出该条记录对应的上报类型 */
        /* 上报绩效档，在此处不做任何改动 */
        String dataClassification = reportRecord.getDataClassification();
        /* 1.若为上报绩效(包括辅导员绩效) */
        if (dataClassification.equals("1") || dataClassification.equals("5")) {
            /* 通过上报记录id查询到对应的上报绩效记录 */
            ReportPerformance reportPerformance = new ReportPerformance();
            reportPerformance.setReportRecord(reportRecord);
            List<ReportPerformance> list = reportPerformanceDao.findList(reportPerformance);
            for (ReportPerformance tempData : list) {
                /* 找出此人对应的工资详情记录 */
                SalaryDetails salaryDetails = new SalaryDetails();
                salaryDetails.setUser(new User(tempData.getUser().getId()));
                salaryDetails.setSalaryInstance(reportRecord.getSalaryInstance());
                salaryDetails = salaryDetailsDao.get(salaryDetails);
                /* 修改其绩效工资 */
                salaryDetails.setPerformanceSalary(tempData.getPerformanceSalary());
                /* 保存 */
                salaryDetailsDao.update(salaryDetails);

            }
        }
        /* 2.若为上报工作量 */
        else if (dataClassification.equals("2")) {
            /* 通过上报记录id查询到对应的上报工作量记录 */
            ReportedWorkloade reportedWorkloade = new ReportedWorkloade();
            reportedWorkloade.setReportRecord(reportRecord);
            List<ReportedWorkloade> list = reportedWorkloadeDao.findList(reportedWorkloade);
            /* 循环这个list */
            for (ReportedWorkloade tempData : list) {
                SalaryInstance data = salaryInstanceDao.get(reportRecord.getSalaryInstance());
                reportRecord.setSalaryInstance(data);
                /* 调用synchWorkSalary()方法将此人于工作量有关的工资保存入库 */
                System.out.println(tempData.getName());
                HrUtils.synchWorkSalary(reportRecord.getSalaryInstance().getId(), tempData.getUser().getId(),
                        tempData.getWorkload(), reportRecord.getSalaryInstance().getReferenceWorkLoade());
            }
        }
        /* 3.若为上报值班 */
        else if (dataClassification.equals("3")) {
            /* 通过上报记录id查询到对应的上报值班记录 */
            DutyDetail dutyDetail = new DutyDetail();
            dutyDetail.setReportRecord(reportRecord);
            List<DutyDetail> list = dutyDetailDao.findList(dutyDetail);
            /* 循环该list */
            for (DutyDetail tempData : list) {
                /* 计算出值班费 */
                double dutyDetailMoney = HrUtils.calculateDutyDetail(tempData, tempData.getUser().getId());
                /* 找出此人对应的工资详情记录 */
                SalaryDetails salaryDetails = new SalaryDetails();
                salaryDetails.setUser(new User(tempData.getUser().getId()));
                salaryDetails.setSalaryInstance(reportRecord.getSalaryInstance());
                salaryDetails = salaryDetailsDao.get(salaryDetails);
                /* 修改其值班费 */
                salaryDetails.setDuty(dutyDetailMoney);
                /* 保存 */
                salaryDetailsDao.update(salaryDetails);
            }
        }

    }

    /**
     * 计算扣税金 应纳个人所得税税额 = 应纳税所得额 * 适用税率 -速算扣除数 应纳税所得额 = 获得的月收入 -扣除标准（3500）
     * 
     * @param salaryDetails
     * @return
     */
    public static double taxDeduction(SalaryDetails salaryDetails) {
        Employee emp = employeeDao.get(salaryDetails.getUser().getId());
        double deduction = salaryDetails.getTotal() + salaryDetails.getBuckleRent() - 3500;
        if (HrConstant.IS.equals(emp.getIsSpecial())) {
            deduction = 0;
            return deduction;
        }
        if (salaryDetails.getTheOnlyFee() != null) {
            deduction = deduction - salaryDetails.getTheOnlyFee();
        }
        if (salaryDetails.getHealthCosts() != null) {
            deduction = deduction - salaryDetails.getHealthCosts();
        }
        if (deduction < 0) {
            deduction = 0;
        } else if (deduction <= 1500) {
            deduction = deduction * 0.03;
        } else if (deduction <= 4500) {
            deduction = deduction * 0.1 - 105;
        } else if (deduction <= 9000) {
            deduction = deduction * 0.2 - 555;
        } else if (deduction <= 35000) {
            deduction = deduction * 0.25 - 1005;
        } else if (deduction <= 55000) {
            deduction = deduction * 0.3 - 2755;
        } else if (deduction <= 80000) {
            deduction = deduction * 0.35 - 5505;
        } else {
            deduction = deduction * 0.45 - 13505;
        }
        return deduction;
    }

}
