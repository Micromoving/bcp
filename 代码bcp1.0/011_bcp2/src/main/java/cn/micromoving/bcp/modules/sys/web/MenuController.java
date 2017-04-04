/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.modules.sys.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.sys.entity.Menu;
import cn.micromoving.bcp.modules.sys.service.SystemService;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

/**
 * 菜单Controller
 * @author songcm
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/menu")
public class MenuController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@ModelAttribute("menu")
	public Menu get(@RequestParam(required=false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的菜单管理信息。否则创建一个新的菜单管理对象。*/
		if (StringUtils.isNotBlank(id)){
			return systemService.getMenu(id);
		}else{
			return new Menu();
		}
	}
	
	@RequestMapping(value={"bizMenu"})
	public String bizMenu(Menu menu,Model model){
		//1.查询出所有一级菜单
		List<Menu> menus1 = UserUtils.getMenuListByParentId(menu.getId());
		List<Menu> menus2 = new ArrayList<Menu>();
		for (Menu m : menus1) {
			//2.根据一级菜单查询出对应的二级菜单
			List<Menu> tempMenu = 
					UserUtils.getMenuListByParentId(m.getId());
			//添加所有菜单到menus中
			menus2.addAll(tempMenu);
		}
		model.addAttribute("menus1", menus1);
		model.addAttribute("menus2", menus2);
		return "modules/sys/bizMenu";
		
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Model model) {
		/*创建一个空的ArrayList容器*/
		List<Menu> list = Lists.newArrayList();
		/*查询当前所有的菜单管理menu信息，并放入sourcelist容器中*/
		List<Menu> sourcelist = systemService.findAllMenu();	
		/*将sourcelist中待排序的数据进行排序，结果放入list中，获取根节点，并对子节点进行排序*/
		Menu.sortList(list, sourcelist, Menu.getRootId(), true);
		/*将传入的list值存入model中*/
        model.addAttribute("list", list);
        /*返回list页面*/
		return "modules/sys/menuList";
	}
	@RequestMapping(value = "form")
	public String form(Menu menu, Model model) {
		/*如果父级菜单不为空或id不为空，则获取当前根节点的父级菜单*/
		if (menu.getParent()==null||menu.getParent().getId()==null){
			menu.setParent(new Menu(Menu.getRootId()));
		}
		/*通过systemService.getMenu获得当前父级菜单的id值*/
		menu.setParent(systemService.getMenu(menu.getParent().getId()));
		// 获取排序号，最末节点排序号+30
		if (StringUtils.isBlank(menu.getId())){
			/*创建一个空的ArrayList容器*/
			List<Menu> list = Lists.newArrayList();
			/*查询当前所有的菜单管理menu信息，并放入sourcelist容器中*/
			List<Menu> sourcelist = systemService.findAllMenu();
			/*将sourcelist中待排序的数据进行排序，结果放入list中，获取根节点，不对子节点排序*/
			Menu.sortList(list, sourcelist, menu.getParentId(), false);
			/*如果有菜单管理信息，在最末节点排序号+30*/
			if (list.size() > 0){
				menu.setSort(list.get(list.size()-1).getSort() + 30);
			}
		}
		/*将传入的menu值保存至model中*/
		model.addAttribute("menu", menu);
		/*返回form页面*/
		return "modules/sys/menuForm";
	}
	@RequestMapping(value = "save")
	public String save(Menu menu, Model model, RedirectAttributes redirectAttributes) {
		/*如果当前用户不为管理员，则不允许添加或修改数据*/
		if(!UserUtils.getUser().isAdmin()){
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能添加或修改数据！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		/*若当前为演示模式则不允许操作*/
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/menu/";
		}
		/*在model中取得menu的相关值，进行服务端参数有效性验证。*/
		if (!beanValidator(model, menu)){
			return form(menu, model);
		}
		/*调用save保存当前menu值*/
		systemService.saveMenu(menu);
		/*添加Flash信息*/
		addMessage(redirectAttributes, "保存菜单'" + menu.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/menu/";
	}

	@RequestMapping(value = "delete")
	public String delete(Menu menu, RedirectAttributes redirectAttributes) {
		/*若当前为演示模式则不允许操作*/
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/menu/";
		}
//		if (Menu.isRoot(id)){
//			addMessage(redirectAttributes, "删除菜单失败, 不允许删除顶级菜单或编号为空");
//		}else{
		/*调用delete删除当前menu值*/
			systemService.deleteMenu(menu);
			/*添加Flash信息*/
			addMessage(redirectAttributes, "删除菜单成功");
//		}
		return "redirect:" + adminPath + "/sys/menu/";
	}

	
	
	/**
	 * 批量修改菜单排序
	 */
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts, RedirectAttributes redirectAttributes) {
		/*若当前为演示模式则不允许操作*/
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/menu/";
		}
		/*对全部menu信息进行菜单排序*/
    	for (int i = 0; i < ids.length; i++) {
    		Menu menu = new Menu(ids[i]);
    		menu.setSort(sorts[i]);
    		systemService.updateMenuSort(menu);
    	}
    	/*添加Flash信息*/
    	addMessage(redirectAttributes, "保存菜单排序成功!");
		return "redirect:" + adminPath + "/sys/menu/";
	}
	
	/**
	 * isShowHide是否显示隐藏菜单
	 * @param extId
	 * @param isShowHidden
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,@RequestParam(required=false) String isShowHide, HttpServletResponse response) {
		/*创建一个空的ArrayList容器*/
		List<Map<String, Object>> mapList = Lists.newArrayList();
		/*查询当前所有的菜单管理menu信息，并放入list容器中*/
		List<Menu> list = systemService.findAllMenu();
		for (int i=0; i<list.size(); i++){
			Menu e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				if(isShowHide != null && isShowHide.equals("0") && e.getIsShow().equals("0")){
					continue;
				}
				/*创建一个空的HashMap容器*/
				Map<String, Object> map = Maps.newHashMap();
				/*分别将用户的id、ParentId、name放入map中*/
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		/*返回mapList*/
		return mapList;
	}
}
