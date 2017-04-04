package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;
import cn.micromoving.bcp.modules.hr.entity.Warm;

/**
 * 取暖标准Dao接口
 * 
 * @author wangzhichen
 * @version 2016-06-03
 */

@MyBatisDao
public interface WarmDao extends CrudDao<Warm> {
    /**
     * 根据工资方案编号，删除对应的标准记录。
     * 
     * @param planId 工资方案编号
     */
    void deleteByPlanId(SalaryPlan entity);

    /**
     * 根据职员的岗位类型，职级|岗位等级，取得对应的取暖费（直接取标准，无业务）。 <br/>
     * 岗位类型：1 ： 教师； 2： 职员； 3 ： 教辅； 4 ： 专职辅导员； 5 ： 工勤；<br/>
     * 注意，岗位类型为6 ： 非在编专任教师； 7 ： 外聘教师;的两种职员，不发放取暖费。<br/>
     * 职级|岗位等级：01-09对应职员的职级|岗位等级。<br/>
     * 见习期职员， 职级|岗位等级对应为10--【见习期】，11--【见习期满】
     * 特别指出，对于2： 职员，按照岗位等级关联。其他职员，按职级关联。<br/>
     * 如发生高职低聘职员，先取得高职中对应的职级，再调用本方法取得取暖费。
     * @param entity 取暖标准实体
     * @return 返回职员应当发放的取暖标准费。
     */
    Warm getWarmStandard(Warm entity);
}