/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.sys.entity.Dict;
import cn.micromoving.bcp.modules.sys.service.DictService;

/**
 * 字典Controller
 * 
 * @author songcm
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;

	@ModelAttribute
	public Dict get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的数据字典信息。否则创建一个新的数据字典对象。 */
		if (StringUtils.isNotBlank(id)) {
			return dictService.get(id);
		} else {
			return new Dict();
		}
	}


	@RequestMapping(value = { "list", "" })
	public String list(Dict dict, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		/*将查询到的字段类型列表放至List容器中*/
		List<String> typeList = dictService.findTypeList();
		/*将传入的typeList值存入model中*/
		model.addAttribute("typeList", typeList);
		/*通过dictService.findPage方法将Dict信息分页*/
		Page<Dict> page = dictService.findPage(
				new Page<Dict>(request, response), dict);
		/*将传入的page值存入model中*/
		model.addAttribute("page", page);
		/*返回list页面*/
		return "modules/sys/dictList";
	}

	@RequestMapping(value = "form")
	public String form(Dict dict, Model model) {
		/*将传入的dict值存入model中*/
		model.addAttribute("dict", dict);
		/*返回form页面*/
		return "modules/sys/dictForm";
	}


	@RequestMapping(value = "save")
	// @Valid
	public String save(Dict dict, Model model,
			RedirectAttributes redirectAttributes) {
		/*若当前为演示模式则不允许操作*/
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/dict/?repage&type="
					+ dict.getType();
		}
		/*在model中取得dict的相关值，进行服务端参数有效性验证*/
		if (!beanValidator(model, dict)) {
			return form(dict, model);
		}
		/*调用save保存当前dict值*/
		dictService.save(dict);
		/*添加Flash信息*/
		addMessage(redirectAttributes, "保存字典'" + dict.getLabel() + "'成功");
		return "redirect:" + adminPath + "/sys/dict/?repage&type="
				+ dict.getType();
	}


	@RequestMapping(value = "delete")
	public String delete(Dict dict, RedirectAttributes redirectAttributes) {
		/*若当前为演示模式则不允许操作*/
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/dict/?repage";
		}
		/*调用delete删除当前dict值*/
		dictService.delete(dict);
		/*添加Flash信息*/
		addMessage(redirectAttributes, "删除字典成功");
		return "redirect:" + adminPath + "/sys/dict/?repage&type="
				+ dict.getType();
	}



	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String type,
			HttpServletResponse response) {
		/*创建一个ArrayList容器*/
		List<Map<String, Object>> mapList = Lists.newArrayList();
		/*创建一个dict对象*/
		Dict dict = new Dict();
		/*设置dict属性为type*/
		dict.setType(type);
		/*查询当前dict的列表数据，并将dict值放入List容器中*/
		List<Dict> list = dictService.findList(dict);
		for (int i = 0; i < list.size(); i++) {
			Dict e = list.get(i);
			/*新建一个HashMap容器*/
			Map<String, Object> map = Maps.newHashMap();
			/*分别将用户的id、ParentId、name放入map中*/
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", StringUtils.replace(e.getLabel(), " ", ""));
			mapList.add(map);
		}
		/*返回map列表*/
		return mapList;
	}


	@RequestMapping(value = "listData")
	public List<Dict> listData(@RequestParam(required = false) String type) {
		/*新建一个dict对象*/
		Dict dict = new Dict();
		/*设置dict类型为type*/
		dict.setType(type);
		/*返回service查询到的当前dict值*/
		return dictService.findList(dict);
	}

}
