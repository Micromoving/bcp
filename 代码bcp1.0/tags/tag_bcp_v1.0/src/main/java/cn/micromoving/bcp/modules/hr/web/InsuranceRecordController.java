package cn.micromoving.bcp.modules.hr.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.utils.excel.ExportExcel;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.InsuranceRecord;
import cn.micromoving.bcp.modules.hr.service.InsuranceRecordService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.sys.entity.User;

@Controller
@RequestMapping(value = "${adminPath}/hr/insuranceRecord")
public class InsuranceRecordController extends BaseController{
	@Autowired
	private InsuranceRecordService insuranceRecordService;
	
	/**
	 * 根据主键， 取得工作经验记录。
	 * 
	 * @param id
	 *            primary key
	 * @return Work entity
	 */
	@ModelAttribute
	public InsuranceRecord get(@RequestParam(required=false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的工作经验记录。否则创建一个新的工作经验对象。 */
	
		if (StringUtils.isNotBlank(id)) {
			return insuranceRecordService.get(id);
		} else {
			return new InsuranceRecord();
		}
	}
	
	/**
	 * 根据主键，查询到工作经验信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param work
	 *            工作经验entity，传递数据。
	 * @param model 视图模型
	 * @return 返回工作经验编辑页面
	 */
//
//	@RequestMapping(value = "form")
//	public String form(InsuranceRecord insuranceRecord, Model model) {
//		/*将传入的insuranceRecord值存入model中*/
//		model.addAttribute("insuranceRecord", insuranceRecord); //?
//		/*返回form页面*/
//		return "modules/hr/insuranceRecord"; 
//	}
//	
//	
	
	/**
	 * 查询用户的全部工作经验信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * @param insuranceRecord 险种规则
	 * @param request http请求
	 * @param response http 响应
	 * @param model  视图模型
	 * @return  险种规则信息（不分页）
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "list", "" })
	public String list(InsuranceRecord insuranceRecord, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		User user = (User)request.getSession().getAttribute(HrConstant.USER);
		Employee emp = (Employee)request.getSession().getAttribute(HrConstant.EMPLOYEE);
		
		model.addAttribute("user", user);
		model.addAttribute("employee", emp);
		insuranceRecord.setUser(user);
		/*通过service将insuranceRecord信息保存至list*/
		model.addAttribute("list", insuranceRecordService.findList(insuranceRecord));
		/*返回list页面*/
		return "modules/hr/insuranceRecord";
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
	public String exportFile(InsuranceRecord insuranceRecord, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		User user = (User)request.getSession().getAttribute(HrConstant.USER);
		insuranceRecord.setUser(user);
		try {
			String fileName = "参保信息" + DateUtils.getDate("yyyyMMddHHmmss")
					+ ".xlsx";
			Page<InsuranceRecord> page = new Page<InsuranceRecord>();
			page = insuranceRecordService.findPage(page, insuranceRecord);
			new ExportExcel("参保信息", InsuranceRecord.class)
					.setDataList(page.getList()).write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出参保信息信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/hr/insuranceRecord?repage";
	}
}
