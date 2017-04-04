/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.common.utils.excel.fieldtype;

import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 字段类型转换
 * @author songcm
 * @version 2013-03-10
 */
public class UserType {

	

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((User)val).getName() != null){
			return ((User)val).getName();
		}
		return "";
	}
}
