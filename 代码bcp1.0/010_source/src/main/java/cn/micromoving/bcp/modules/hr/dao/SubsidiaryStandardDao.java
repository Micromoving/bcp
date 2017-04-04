package cn.micromoving.bcp.modules.hr.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.SalaryPlan;
import cn.micromoving.bcp.modules.hr.entity.SubsidiaryStandard;

/**
 * 离职表Dao接口
 * 
 * @author wangzhichen
 * @version 2016-06-03
 */

@MyBatisDao
public interface SubsidiaryStandardDao extends CrudDao<SubsidiaryStandard> {
    /**
     * 根据工资方案编号，删除对应的标准记录。
     * 
     * @param planId 工资方案编号
     */
    void deleteByPlanId(SalaryPlan entity);

    /**
     * 根据津贴类型，津贴主体，查询对应的津贴实体<br/>
     * 【津贴类型】:001 ：院龄津贴 ； 002 ：职务津贴； 003 ：追加津贴；<br/>
     * 004 ：精神文明（一）； 005 ：独子费； 006 ：卫生费；<br/>
     * 007 ：班主任费； 008 ：精神文明（二）； 009： 精神文明（三）； <br/>
     * 【津贴主体】:002:主任;053:院长;054: 副院长;003: 副主任;<br/>
     * 033 :主任助理;056 :院长助理;068 :教研室主任;069 :主任辅导员;<br/>
     * 
     * @param entity 津贴实体，保存津贴类型，津贴主体，
     * @return 查询到的津贴实体entity
     */
    SubsidiaryStandard getSubsidiary(SubsidiaryStandard entity);
}