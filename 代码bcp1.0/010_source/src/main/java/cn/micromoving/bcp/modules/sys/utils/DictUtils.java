/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.modules.sys.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cn.micromoving.bcp.common.mapper.JsonMapper;
import cn.micromoving.bcp.common.utils.CacheUtils;
import cn.micromoving.bcp.common.utils.SpringContextHolder;
import cn.micromoving.bcp.modules.sys.dao.DictDao;
import cn.micromoving.bcp.modules.sys.entity.Dict;

/**
 * 字典工具类
 * 
 * @author songcm
 * @version 2013-5-29
 */
public class DictUtils {

    private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);

    public static final String CACHE_DICT_MAP = "dictMap";

    public static final String CACHE_DICT_MAP_PID = "dictMapPId";

    public static String getDictLabel(String value, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)) {
            for (Dict dict : getDictList(type)) {
                if (type.equals(dict.getType()) && value.equals(dict.getValue())) {
                    return dict.getLabel();
                }
            }
        }
        return defaultValue;
    }

    public static String getDictLabels(String values, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)) {
            List<String> valueList = Lists.newArrayList();
            for (String value : StringUtils.split(values, ",")) {
                valueList.add(getDictLabel(value, type, defaultValue));
            }
            return StringUtils.join(valueList, ",");
        }
        return defaultValue;
    }

    public static String getDictValue(String label, String type, String defaultLabel) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)) {
            for (Dict dict : getDictList(type)) {
                if (type.equals(dict.getType()) && label.equals(dict.getLabel())) {
                    return dict.getValue();
                }
            }
        }
        return defaultLabel;
    }

    public static List<Dict> getDictList(String type) {
        @SuppressWarnings("unchecked")
        /* 从缓存中取得所有的数据字典项，保存在dictMap映射对象中。 */
        Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>) CacheUtils.get(CACHE_DICT_MAP);
        /* 如果dictMap在缓存中不存在。从数据库中重新取，并将数据库中的字典项绑定至dictMap中。 */
        if (dictMap == null) {
            dictMap = Maps.newHashMap();
            /* 从数据库中取出所有的字典项，遍历全部项，对各字典项进行处理，保存至dictMap中 */
            for (Dict dict : dictDao.findAllList(new Dict())) {
                /* 取得dictMap中，当前字典项类型对应的字典列表，如果列表为空，表示尚未保存访字典。 */
                List<Dict> dictList = dictMap.get(dict.getType());
                if (dictList != null) {
                    /* 如果当前字典类型存在，将当前字典项保存至列表中（并不是直接放到dictMap），因为dictList对象中新增了一个元素，所以dictMap中对应的值也做了变化。 */
                    dictList.add(dict);
                } else {

                    /* 当前字典项不存在，对list进行初始化后，保存至dictMap */
                    dictMap.put(dict.getType(), Lists.newArrayList(dict));
                }
            }
            CacheUtils.put(CACHE_DICT_MAP, dictMap);
        }
        List<Dict> dictList = dictMap.get(type);
        if (dictList == null) {
            dictList = Lists.newArrayList();
        }
        return dictList;
    }

    /**
     * 根据父字典类型，值取得对应的子字典列表。
     * @param type 父字典类型
     * @param value 父字典value
     * @return 返回父字典对应的子字典列表
     */
    public static List<Dict> getSubDictList(String type, String value) {
        List<Dict> result = Lists.newArrayList();
        List<Dict> dictList = getDictList(type);

        for (Dict dict : dictList) {
            if (value.equals(dict.getValue())) {
                result = getDictListByParentId(dict.getId());
                break;
            }
        }
        return result;

    }

    private static List<Dict> getDictListByParentId(String parentId) {
        @SuppressWarnings("unchecked")
        /* 从缓存中取得所有的数据字典项，保存在dictMap映射对象中。 */
        Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>) CacheUtils.get(CACHE_DICT_MAP_PID);
        /* 如果dictMap在缓存中不存在。从数据库中重新取，并将数据库中的字典项绑定至dictMap中。 */
        if (dictMap == null) {
            dictMap = Maps.newHashMap();
            /* 从数据库中取出所有的字典项，遍历全部项，对各字典项进行处理，保存至dictMap中 */
            for (Dict dict : dictDao.findAllList(new Dict())) {
                /* 取得dictMap中，当前字典项类型对应的字典列表，如果列表为空，表示尚未保存访字典。 */
                List<Dict> dictList = dictMap.get(dict.getParentId());
                if (dictList != null) {
                    /* 如果当前字典类型存在，将当前字典项保存至列表中（并不是直接放到dictMap），因为dictList对象中新增了一个元素，所以dictMap中对应的值也做了变化。 */
                    dictList.add(dict);
                } else {

                    /* 当前字典项不存在，对list进行初始化后，保存至dictMap */
                    dictMap.put(dict.getParentId(), Lists.newArrayList(dict));
                }
            }
            CacheUtils.put(CACHE_DICT_MAP_PID, dictMap);
        }
        List<Dict> dictList = dictMap.get(parentId);
        if (dictList == null) {
            dictList = Lists.newArrayList();
        }
        return dictList;
    }

    /**
     * 返回字典列表（JSON）
     * 
     * @param type
     * @return
     */
    public static String getDictListJson(String type) {
        return JsonMapper.toJsonString(getDictList(type));
    }

}
