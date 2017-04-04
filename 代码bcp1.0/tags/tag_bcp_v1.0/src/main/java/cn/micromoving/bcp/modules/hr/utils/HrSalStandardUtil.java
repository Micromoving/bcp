package cn.micromoving.bcp.modules.hr.utils;

import java.util.List;

import cn.micromoving.bcp.common.utils.CacheUtils;
import cn.micromoving.bcp.common.utils.SpringContextHolder;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.modules.hr.dao.ClassPayStandardDao;
import cn.micromoving.bcp.modules.hr.dao.SubsidiaryStandardDao;
import cn.micromoving.bcp.modules.hr.dao.WarmDao;
import cn.micromoving.bcp.modules.hr.entity.ClassPayStandard;
import cn.micromoving.bcp.modules.hr.entity.SubsidiaryStandard;
import cn.micromoving.bcp.modules.hr.entity.Warm;

public final class HrSalStandardUtil {

    public static final String HR_SAL = "salCache";

    public static final String HR_SUBSIDIARY_STANDARD = "subsidiary";

    public static final String HR_SUBSIDIARY_STANDARD_TYPE = "type_";
    
    public static final String HR_WARM = "warm";

    public static final String HR_WARM_TYPE = "type_";
    
    public static final String HR_CLASSPAY_STANDARD = "classpay";

    public static final String HR_CLASSPAY_STANDARD_TYPE = "type_";

    /* 津贴标准 */
    public static SubsidiaryStandardDao subsidiaryStandardDao = SpringContextHolder
            .getBean(SubsidiaryStandardDao.class);

    /* 取暖标准 */
    public static WarmDao warmDao = SpringContextHolder.getBean(WarmDao.class);

    /* 课酬标准 */
    public static ClassPayStandardDao classPayStandardDao = SpringContextHolder.getBean(ClassPayStandardDao.class);

    /**
     * 获取津贴
     * @param entity
     * @return
     */
    public static SubsidiaryStandard getSubsidiary(SubsidiaryStandard entity) {

        String cacheString = entity.getAllowanceType();
        if (StringUtils.isNotBlank(entity.getAllowanceMainBody())) {
            cacheString = cacheString + entity.getAllowanceMainBody();
        }
        SubsidiaryStandard data = (SubsidiaryStandard) CacheUtils.get(HR_SUBSIDIARY_STANDARD,
                HR_SUBSIDIARY_STANDARD_TYPE + cacheString);
        if (data == null) {
            List<SubsidiaryStandard> list = subsidiaryStandardDao.findList(entity);
            if (list == null) {
                return null;
            }
            for (SubsidiaryStandard subData : list) {
                if (StringUtils.isBlank(subData.getAllowanceMainBody())) {

                    CacheUtils.put(HR_SUBSIDIARY_STANDARD, HR_SUBSIDIARY_STANDARD_TYPE + entity.getAllowanceType(),
                    		subData);
                } else {
                    CacheUtils.put(HR_SUBSIDIARY_STANDARD, HR_SUBSIDIARY_STANDARD_TYPE + entity.getAllowanceType()
                            + subData.getAllowanceMainBody(), subData);

                }
            }
            data = (SubsidiaryStandard) CacheUtils.get(HR_SUBSIDIARY_STANDARD,
                    HR_SUBSIDIARY_STANDARD_TYPE + cacheString);
        }

        return data;

    }
    
    /**
     * 获取取暖
     * @param entity
     * @return
     */
    public static Warm getWarmStandard(Warm entity) {

        String cacheString = entity.getPostType();
            cacheString = cacheString + entity.getClassify();
            Warm data = (Warm) CacheUtils.get(HR_WARM,
            		HR_WARM_TYPE + cacheString);
        if (data == null) {
            List<Warm> list = warmDao.findList(entity);
            if (list == null) {
                return null;
            }
            for (Warm warmData : list) {
            	
                    CacheUtils.put(HR_WARM, HR_WARM_TYPE + entity.getPostType()
                            + warmData.getClassify(), warmData);

            }
            data = (Warm) CacheUtils.get(HR_WARM,
            		HR_WARM_TYPE + cacheString);
        }

        return data;

    }
    
    public static ClassPayStandard getClassPayStandard(ClassPayStandard entity) {

        String cacheString = entity.getTechPosition();
            cacheString = cacheString + entity.getEngageMode();
            ClassPayStandard data = (ClassPayStandard) CacheUtils.get(HR_CLASSPAY_STANDARD,
            		HR_CLASSPAY_STANDARD_TYPE + cacheString);
        if (data == null) {
            List<ClassPayStandard> list = classPayStandardDao.findList(entity);
            if (list == null) {
                return null;
            }
            for (ClassPayStandard classPayData : list) {
            	
                    CacheUtils.put(HR_CLASSPAY_STANDARD, HR_CLASSPAY_STANDARD_TYPE + classPayData.getTechPosition()
                            + classPayData.getEngageMode(), classPayData);

            }
            data = (ClassPayStandard) CacheUtils.get(HR_CLASSPAY_STANDARD,
            		HR_CLASSPAY_STANDARD_TYPE + cacheString);
        }

        return data;

    }
}
