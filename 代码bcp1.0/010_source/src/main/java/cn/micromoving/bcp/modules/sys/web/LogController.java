/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.sys.entity.Log;
import cn.micromoving.bcp.modules.sys.service.LogService;

/**
 * 日志Controller
 * @author songcm
 * @version 2013-6-2
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;
	
	@RequestMapping(value = {"list", ""})
	public String list(Log log, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*通过logService.findPage()方法获取到log中的所有信息并进行分页*/
		Page<Log> page = logService.findPage(new Page<Log>(request, response), log); 
		/*将page信息保存到page模型中*/
        model.addAttribute("page", page);
		return "modules/sys/logList";
	}

}
