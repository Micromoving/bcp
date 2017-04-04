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
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.ExportExcel;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.Teacher;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.service.OfficeService;
import cn.micromoving.bcp.modules.sys.utils.DictUtils;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

/**
 * 机构Controller
 * @author songcm
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/office")
public class OfficeController extends BaseController {

	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute("office")
	public Office get(@RequestParam(required=false) String id) {
		/*如果id不为空则返回id对应的office对象*/
		if (StringUtils.isNotBlank(id)){
			return officeService.get(id);
		}else{
			return new Office();
		}
	}
	
	@RequestMapping(value = {""})
	public String index(Office office, Model model) {
//        model.addAttribute("list", officeService.findAll());
		return "modules/sys/officeIndex";
	}

	@RequiresPermissions("sys:office:search")
	@RequestMapping(value = {"list"})
	public String list(Office office, Model model) {
		/*将office中有权限访问的部门保存到list模型中*/
        model.addAttribute("list", officeService.findList(false));
		return "modules/sys/officeList";
	}
	
	@RequiresPermissions({"sys:office:create","sys:office:update"})
	@RequestMapping(value = "form")
	public String form(Office office, Model model) {
		/*获取当前用户*/
		User user = UserUtils.getUser();
		/*若office的parent属性或parent的id属性为空，则将office属性设置为当前用户的office属性*/
		if (office.getParent()==null || office.getParent().getId()==null){
			office.setParent(user.getOffice());
		}
		/*设置office的parent属性*/
		office.setParent(officeService.get(office.getParent().getId()));
		
		// 自动获取排序号
		/*若当前office的id属性值不为空且office的parent属性也不为空*/
		if (StringUtils.isBlank(office.getId())&&office.getParent()!=null){
			
			int size = 0;
			/*获取到当前用户有权限访问的部门*/
			List<Office> list = officeService.findAll();
			/*循环遍历这个list容器*/
			for (int i=0; i<list.size(); i++){
				Office e = list.get(i);
				/*如果该office的parent属性和parent的id属性都不为空，且与传入的office的parent的id值相等则 size++*/
				if (e.getParent()!=null && e.getParent().getId()!=null
						&& e.getParent().getId().equals(office.getParent().getId())){
					size++;
				}
			}
			/*设置office的code属性值为当前值在后加入特定字符串*/
			office.setCode(office.getParent().getCode() + StringUtils.leftPad(String.valueOf(size > 0 ? size+1 : 1), 3, "0"));
		}
		model.addAttribute("office", office);
		return "modules/sys/officeForm";
	}
	
	@RequiresPermissions({"sys:office:create","sys:office:update"})
	@RequestMapping(value = "save")
	public String save(Office office, Model model, RedirectAttributes redirectAttributes) {
		/*若当前为演示模式，则不允许进行相关操作*/
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/office/";
		}
		/*office中的信息进行服务器端验证
		if (!beanValidator(model, office)){
			return form(office, model);
		}*/
		/*保存office信息*/
		officeService.save(office);
		/*如果office的子部门不为空*/
		if(office.getChildDeptList()!=null){
			Office childOffice = null;
			/*循环这个office的子部门*/
			for(String id : office.getChildDeptList()){
				/*实例化childOffice这个对象*/
				childOffice = new Office();
				/*设置相关属性*/
				/*名字为根据id值从数据字典中取得的对应值*/
				childOffice.setName(DictUtils.getDictLabel(id, "sys_office_common", "未知"));
				/*parent属性为当前office*/
				childOffice.setParent(office);
				
				/*type属性值为"2"*/
				childOffice.setType("2");
				/*grade属性为当前office的grade属性值+1*/
				childOffice.setGrade(String.valueOf(Integer.valueOf(office.getGrade())+1));
				/*将useable属性设置为可用*/
				childOffice.setUseable(Global.YES);
				/*保存用户信息*/
				officeService.save(childOffice);
			}
		}
		/*弹出信息提示框内容为:保存机构+当前office名称+成功*/
		addMessage(redirectAttributes, "保存机构'" + office.getName() + "'成功");
		String id = "0".equals(office.getParentId()) ? "" : office.getParentId();
		return "redirect:" + adminPath + "/sys/office/list?repage";
	}
	
	@RequiresPermissions("sys:office:delete")
	@RequestMapping(value = "delete")
	public String delete(Office office, RedirectAttributes redirectAttributes) {
		/*若当前为演示模式，则不允许进行相关操作*/
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/office/list";
		}
//		if (Office.isRoot(id)){
//			addMessage(redirectAttributes, "删除机构失败, 不允许删除顶级机构或编号空");
//		}else{
		/*删除对应的office信息*/
			officeService.delete(office);
			/*弹出信息提示框内容为:删除机构成功*/
			addMessage(redirectAttributes, "删除机构成功");
//		}
		return "redirect:" + adminPath + "/sys/office/list?id="+office.getParentId()+"&parentIds="+office.getParentIds();
	}

	/**
	 * 获取机构JSON数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		/*创建一个名为mapList的ArrayList空容器*/
		List<Map<String, Object>> mapList = Lists.newArrayList();
		/*通过serive获取到当前用户有权限访问的部门，并将其访日list容器汇总*/
		List<Office> list = officeService.findList(isAll);
		/*循环这个list容器*/
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
			/*若extId不为空，或extId不为null且不等于对应的office的id，且对应的office的parentIds中存在，extId，
			 * 且type等于null或type不等于null且如果type的值为"1"则返回的值为type和对应的office的type值是否相等否则返回true
			 * 且grade值为空  grade值不为空 或对应的office的grade属性小于等于grade的intvalue值
			 * 且对应的office的useable属性为可见
			 * */
			
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1))
					&& (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))
					&& Global.YES.equals(e.getUseable())){
				/*创建一个HashMap对象*/
				Map<String, Object> map = Maps.newHashMap();
				/*将对应的id、parentId、parentIds、name放入map表中*/
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				/*若type不为空，且等于3*/
				if (type != null && "3".equals(type)){
					/*将isParent属性的值为true放入map表中*/
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}
	/**
	 * 导出用户数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "export")
	public String exportFile(Office office, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "部门信息" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			Page<Office> page = officeService.findOffice(new Page<Office>(
					request, response, -1), office);
			new ExportExcel("部门信息", Office.class)
					.setDataList(page.getList()).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出部门失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/office/list?repage";
	}
}
