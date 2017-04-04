/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.common.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.beanvalidator.BeanValidators;
import cn.micromoving.bcp.common.mapper.JsonMapper;
import cn.micromoving.bcp.common.utils.DateUtils;

import com.google.common.collect.Lists;

/**
 * 控制器支持类
 * 
 * @author songcm
 * @version 2013-3-23
 */
public abstract class BaseController {
	/**
	 * 错误消息列表， 保存Controller关联验证的各项出错消息。
	 */
	protected List<String> errorMessages = Lists.newArrayList();
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 管理基础路径
	 */
	@Value("${adminPath}")
	protected String adminPath;

	/**
	 * 前端基础路径
	 */
	@Value("${frontPath}")
	protected String frontPath;

	/**
	 * 前端URL后缀
	 */
	@Value("${urlSuffix}")
	protected String urlSuffix;

	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;

	/**
	 * 服务端参数有效性验证
	 * 
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidator(Model model, Object object,
			Class<?>... groups) {
		try {
			BeanValidators.validateWithException(validator, object, groups);
		} catch (ConstraintViolationException ex) {
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(
					ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(model, list.toArray(new String[] {}));
			return false;
		}
		return true;
	}

	/**
	 * 服务端参数有效性验证
	 * 
	 * @param rules
	 *            验证规则 1：只和今天的日期比较；2：只比较俩个日期的先后顺序;0：既比较日期的先后顺序，又与今天的日期作比较。
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean dateValidator(Model model, String rules, Date... groups) {
		List<String> errorMessages = Lists.newArrayList();
		/* 只和今天的日期比较；早于现在日期。 */
		if ("0".equals(rules) || "1".equals(rules)) {
			Date now = new Date();
			for (Date group : groups) {
				if (group.after(now)) {
					errorMessages.add("时间晚于现在");
					break;
				}
			}
		}
		/* 只比较俩个日期的先后顺序，起始时间比结束时间早 */
		if ("0".equals(rules) || "2".equals(rules)) {
			if (groups.length < 2) {
				return true;
			}
			for (int i = 1; i < groups.length; i++) {
				if (groups[i - 1].after(groups[i])) {
					errorMessages.add("时间先后顺序不对");
					break;
				}
			}

		}
		/* 如果错误消息容器中有内容，则将容器中的内容加上，并在第一句加上数据验证失败 */
		if (errorMessages.size() > 0) {
			errorMessages.add(0, "数据验证失败：");
			addMessage(model, errorMessages.toArray(new String[] {}));
			return false;
		}
		return true;
	}

	/**
	 * 服务端参数有效性验证
	 * 
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes,
			Object object, Class<?>... groups) {
		try {
			BeanValidators.validateWithException(validator, object, groups);
		} catch (ConstraintViolationException ex) {
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(
					ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(redirectAttributes, list.toArray(new String[] {}));
			return false;
		}
		return true;
	}

	/**
	 * 服务端参数有效性验证
	 * 
	 * @param object
	 *            验证的实体对象
	 * @param groups
	 *            验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidators.validateWithException(validator, object, groups);
	}

	/**
	 * 添加Model消息
	 * 
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		for (String message : errorMessages) {
			sb.append(message).append(errorMessages.size() > 1 ? "<br/>" : "");
		}
		model.addAttribute("message", sb.toString());
	}

	/**
	 * 添加Flash消息
	 * 
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes,
			String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		for (String message : errorMessages) {
			sb.append(message).append(errorMessages.size() > 1 ? "<br/>" : "");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}

	/**
	 * 客户端返回JSON字符串
	 * 
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JsonMapper.toJsonString(object),
				"application/json");
	}

	/**
	 * 客户端返回字符串
	 * 
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string,
			String type) {
		try {
			response.reset();
			response.setContentType(type);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({ BindException.class,
			ConstraintViolationException.class, ValidationException.class })
	public String bindException() {
		return "error/400";
	}

	/**
	 * 授权登录异常
	 */
	@ExceptionHandler({ AuthenticationException.class })
	public String authenticationException() {
		return "error/403";
	}

	/**
	 * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setAutoGrowCollectionLimit(2048);
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils
						.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
			// @Override
			// public String getAsText() {
			// Object value = getValue();
			// return value != null ? DateUtils.formatDateTime((Date)value) :
			// "";
			// }
		});
	}

	public boolean dateCompare(Date date1, String compare, Date date2) {
		boolean data = false;
		String time1;
		String time2;
		/*将时间转换为字符串，比较是否相等   相等返回true*/
		if ("eq".equals(compare) || "==".equals(compare)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			time1 = sdf.format(date1);
			time2 = sdf.format(date2);
			if (time1.equals(time2)) {
				data = true;
			}
		} 
		return data;
	}

	public boolean timeCompare(Date date1, String compare, Date date2) {
		boolean data = false;
		/*
		 * ==或eq 相等 !=或ne 不等 <或lt 小于 >或gt 大于 <=或le 小于等于 >=或ge 大于等于
		 */
		if ("eq".equals(compare) || "==".equals(compare)) {
			dateCompare(date1,compare,date2);
		} else if ("ne".equals(compare) || "!=".equals(compare)) {
			data = !dateCompare(date1,compare,date2);
		} else if ("lt".equals(compare) || "<".equals(compare)) {
			if (date1.before(date2)) {
				data = true;
			}
		} else if ("gt".equals(compare) || ">".equals(compare)) {
			if (date1.after(date2)) {
				data = true;
			}
		} else if ("le".equals(compare) || "<=".equals(compare)) {
			if (!(date1.after(date2))){
				data = true;
			}
		} else if ("ge".equals(compare) || ">=".equals(compare)) {
			if (!(date1.before(date2))) {
				data = true;
			}
		}
		return data;
	}

}
