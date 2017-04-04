/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Cache工具类
 * @author songcm
 * @version 2013-5-29
 */
public class EhCacheUtils {

	private static CacheManager cacheManager = ((CacheManager)SpringContextHolder.getBean("cacheManager"));
	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	private static final String SYS_CACHE = "sysCache";

	/**
	 * 根据ID获取SYS_CACHE缓存
	 * @param key 缓存ID
	 * @return 返回缓存和缓存ID
	 */
	public static Object get(String key) {
		return get(SYS_CACHE, key);
	}
	
	/**
	 * 将缓存ID、数据写入SYS_CACHE缓存
	 * @param key 缓存ID 
	 */
	public static void put(String key, Object value) {
		put(SYS_CACHE, key, value);
	}
	
	/**
	 * 将缓存和ID从SYS_CACHE缓存中移除
	 * @param key 缓存ID
	 */
	public static void remove(String key) {
		remove(SYS_CACHE, key);
	}
	
	/**
	 * 根据缓存名获取缓存ID返回缓存数据
	 * @param cacheName 缓存名
	 * @param key 缓存ID
	 * @return 返回缓存数据
	 */
	public static Object get(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		return element==null?null:element.getObjectValue();
	}

	/**
	 * 给缓存创建一个ID和缓存数据
	 * @param cacheName 缓存名
	 * @param key 缓存ID
	 * @param value 缓存数据
	 */
	public static void put(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	/**
	 * 获取缓存名将缓存ID从缓存中移除
	 * @param cacheName 缓存名
	 * @param key 缓存ID
	 */
	public static void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}
	
	/**
	 * 获得一个Cache，没有则创建一个。
	 * @param cacheName 缓存名
	 * @return 返回一个缓存
	 */
	private static Cache getCache(String cacheName){
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null){
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
//			创建一个缓存
		}
		return cache;
	}

	public static CacheManager getCacheManager() {
		return cacheManager;
	}
	
}
