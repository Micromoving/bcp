/**
 * Copyright (c) 2005-2012 springside.org.cn
 */
package cn.micromoving.bcp.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Collections工具集.
 * 在JDK的Collections和Guava的Collections2后, 命名为Collections3.
 * @author calvin
 * @version 2013-01-15
 */
@SuppressWarnings("rawtypes")
public class Collections3 {

	/**
	 * 提取集合中的对象的两个属性(通过Getter函数), 组合成Map.
	 * 
	 * @param collection 来源集合.
	 * @param keyPropertyName 要提取为Map中的Key值的属性名.
	 * @param valuePropertyName 要提取为Map中的Value值的属性名.
	 * @return  返回一个HashMap表
	 */
	@SuppressWarnings("unchecked")
	public static Map extractToMap(final Collection collection, final String keyPropertyName,
			final String valuePropertyName) {
		/*创建一个HashMap的对象，并初始化容量*/
		Map map = new HashMap(collection.size());    
		/*通过FOR-EACH循环来遍历collection容器，并捕获异常*/
		try {
			for (Object obj : collection) {
				/*put方法是想HashMap表里添加元素(一个key，对应一个value)*/
				map.put(PropertyUtils.getProperty(obj, keyPropertyName),    /*<--这个方法是通过obj和keyPropertyName来获取他所对应的值，并作为新的Map容器的Key值*/
						PropertyUtils.getProperty(obj, valuePropertyName));/*<--这个方法是通过obj和valuePropertyName来获取他所对应的值，并作为新的Map容器的Value值*/
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);    /*抛出异常*/
		}

		return map;
	}

	/**
	 * 提取集合中的对象的一个属性(通过Getter函数), 组合成List.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 * @return 返回一个ArrayList容器
	 */
	@SuppressWarnings("unchecked")
	public static List extractToList(final Collection collection, final String propertyName) {
		/*创建一个ArrayList的对象，并初始化容量*/
		List list = new ArrayList(collection.size());
		/*通过FOR-EACH循环来遍历collection容器，并捕获异常*/
		try {
			for (Object obj : collection) {
				/*add方法是向容器中添加元素*/
				list.add(PropertyUtils.getProperty(obj, propertyName));/*<--这个方法是通过obj和keyPropertyName来获取他所对应的值，并添加到对应的ArraysList容器中*/
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);  /*抛出异常*/
		}

		return list;
	}

	/**
	 * 提取集合中的对象的一个属性(通过Getter函数), 组合成由分割符分隔的字符串.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 * @param separator 分隔符.
	 * @return 返回一个字符串
	 */
	
	public static String extractToString(final Collection collection, final String propertyName, final String separator) {
		List list = extractToList(collection, propertyName);    /* 将原容器组合成新的List容器*/
		return StringUtils.join(list, separator);    /*将list容器里的元素以separator作为分隔符组成字符串，并返回*/
	}

	/**
	 * 转换Collection所有元素(通过toString())为String, 中间以 separator分隔。
	 * @param collection  来源集合
	 * @param separator  分隔符
	 * @return 返回一个字符串
	 */
	public static String convertToString(final Collection collection, final String separator) {
		return StringUtils.join(collection, separator);   /*将collection容器里的元素以separator作为分隔符组成字符串，并返回*/
	}

	/**
	 * 转换Collection所有元素(通过toString())为String, 每个元素的前面加入prefix，后面加入postfix，如<div>mymessage</div>。
	 * @param collection  来源集合
	 * @param prefix   将要添加字符串
	 * @param postfix  将要添加字符串
	 * @return  返回一个字符串
	 */
	public static String convertToString(final Collection collection, final String prefix, final String postfix) {
		StringBuilder builder = new StringBuilder();
		for (Object o : collection) {
			builder.append(prefix).append(o).append(postfix);
		}
		return builder.toString();
	}

	/**
	 * 判断是否为空.
	 * @param collection 来源集合
	 * @return  返回一个布尔值
	 */
	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * 取得Collection的第一个元素，如果collection为空返回null.
	 * @param collection 来源集合
	 * @return  返回一个Collection<T>所对应的类型值
	 */
	public static <T> T getFirst(Collection<T> collection) {
		if (isEmpty(collection)) {
			return null;
		}

		return collection.iterator().next();
	}

	/**
	 * 获取Collection的最后一个元素 ，如果collection为空返回null.
	 * @param collection 来源集合
	 * @return  返回一个Collection<T>所对应的类型值
	 */
	public static <T> T getLast(Collection<T> collection) {
		if (isEmpty(collection)) {
			return null;
		}

		//当类型为List时，直接取得最后一个元素 。
		if (collection instanceof List) {      /*instanceof关键字是判断前者是不是后者的实例化对象*/
			List<T> list = (List<T>) collection;
			return list.get(list.size() - 1);
		}

		//其他类型通过iterator滚动到最后一个元素.
		Iterator<T> iterator = collection.iterator();   /*创建迭代器对象*/
		while (true) { 
			T current = iterator.next();    /*判断容器是否有下一个元素*/
			if (!iterator.hasNext()) {       /*如果没有下一个元素，即该元素为最后一个元素，则返回这个元素*/
				return current;
			}
		}
	}

	/**
	 * 返回a+b的新List.
	 * @param a  来源集合①
	 * @param b  来源集合②
	 * @return  返回一个ArrayList<T>对象
	 */
	public static <T> List<T> union(final Collection<T> a, final Collection<T> b) {
		List<T> result = new ArrayList<T>(a);   /*创建一个ArrayList对象result，并将容器a的值赋值给result*/
		result.addAll(b);     /*将容器b中所有的元素添加到result中*/
		return result;
	}

	/**
	 * 返回a-b的新List.
	 * @param a  来源集合①
	 * @param b  来源集合②
	 * @return   返回一个ArrayList<T>对象
	 */
	public static <T> List<T> subtract(final Collection<T> a, final Collection<T> b) {
		List<T> list = new ArrayList<T>(a);     /*创建一个ArrayList对象list，并将容器a的值赋值给result*/
		for (T element : b) {        /*将list容器中在b容器中有的元素，移除掉*/
			list.remove(element);
		}

		return list;
	}

	/**
	 * 返回a与b的交集的新List.
	 * @param a  来源集合①
	 * @param b  来源集合②
	 * @return   返回一个ArrayList<T>对象
	 */
	public static <T> List<T> intersection(Collection<T> a, Collection<T> b) {
		List<T> list = new ArrayList<T>();    /*创建一个ArrayList对象list*/

		for (T element : a) {     
			if (b.contains(element)) {     /*将a容器与b容器共同的对象添加到list容器中*/
				list.add(element);
			}
		}
		return list;
	}
}
