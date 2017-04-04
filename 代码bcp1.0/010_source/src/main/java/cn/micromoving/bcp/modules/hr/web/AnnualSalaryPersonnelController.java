package cn.micromoving.bcp.modules.hr.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.micromoving.bcp.common.utils.StringUtils;
import cn.micromoving.bcp.common.web.BaseController;
import cn.micromoving.bcp.modules.hr.dao.AnnualSalaryPersonnelDao;
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.entity.AnnualSalaryPersonnel;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.service.AnnualSalaryPersonnelService;
import cn.micromoving.bcp.modules.sys.entity.Office;

import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "${adminPath}/hr/annualSalaryPersonnel")
public class AnnualSalaryPersonnelController extends BaseController {

	@Autowired
	private AnnualSalaryPersonnelService annualSalaryPersonnelService;
	@Autowired
	private AnnualSalaryPersonnelDao annualSalaryPersonnelDao;
	@Autowired
	private SalEmpViewDao salEmpViewDao;

	/**
	 * 根据主键，取得用户年薪制人员
	 * 
	 * @param id
	 *            primary key
	 * @return AnnualSalaryPersonnel entity
	 */
	@ModelAttribute
	public AnnualSalaryPersonnel get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的年薪制人员。否则创建一个新的年薪制人员。 */
		if (StringUtils.isNotBlank(id)) {
			return annualSalaryPersonnelService.get(id);
		} else {
			return new AnnualSalaryPersonnel();
		}
	}

	/**
	 * 查询用户的全部信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param AnnualSalaryPersonnel
	 *            年薪制人员entity
	 * @param request
	 *            http请求
	 * @param response
	 *            http响应
	 * @param model
	 *            视图模型
	 * @return
	 */

	@RequestMapping(value = "list")
	public String list(AnnualSalaryPersonnel annualSalaryPersonnel,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		/**
		 * 通过工号、姓名、身份证查询人员信息
		 * */
		String userNo = null;
		String name = null;
		String papersNumber = null;
		Office office = null;
		List<AnnualSalaryPersonnel> annualSalaryPersonnelList = Lists
				.newArrayList();
		List<SalEmpView> salEmpList = Lists.newArrayList();
		/* 若能从session中获取到值，则说明是保存后重定向过来的，则不用再获取 */
		if (annualSalaryPersonnel.getSalEmpView() != null) {
			userNo = annualSalaryPersonnel.getSalEmpView().getLoginName();
			papersNumber = annualSalaryPersonnel.getSalEmpView().getPapersNumber();
			name = annualSalaryPersonnel.getSalEmpView().getName();
			office = annualSalaryPersonnel.getSalEmpView().getOffice();
		}
		
		SalEmpView sal = new SalEmpView();
		if( office != null || StringUtils.isNotBlank(name)
				|| StringUtils.isNotBlank(papersNumber)){
			AnnualSalaryPersonnel  asp = new AnnualSalaryPersonnel();
			sal.setLoginName(userNo);
			sal.setName(name);
			sal.setOffice(office);
			sal.setPapersNumber(papersNumber);
			model.addAttribute("salEmpView",sal);
			model.addAttribute("annualSalaryPersonnel", asp);
			salEmpList = salEmpViewDao.findList(sal);
			for(SalEmpView temp : salEmpList) {
				asp = new AnnualSalaryPersonnel();
				asp.setSalEmpView(temp);
				annualSalaryPersonnelList.add(asp);
			}
		}
		List<AnnualSalaryPersonnel> lsit = annualSalaryPersonnelService.findList(annualSalaryPersonnel);
           /* 通过service将work信息保存至list */
		model.addAttribute("list", lsit);
		return "modules/hr/annualSalaryPersonnelList";
	}

	/**
	 * 删除记录。操作成功后，转至列表页面。
	 * 
	 * @param annualSalaryPersonnel
	 *            年薪制人员entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */

	@RequestMapping(value = "delete")
	public String delete(AnnualSalaryPersonnel annualSalaryPersonnel,
			RedirectAttributes redirectAttributes) {

		annualSalaryPersonnelService.delete(annualSalaryPersonnel);
		addMessage(redirectAttributes, "删除成功");

		return "redirect:" + adminPath
				+ "/hr/annualSalaryPersonnel/list?repage";
	}

	/**
	 * 根据主键，查询到年薪制人员信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param annualSalaryPersonnel
	 *            年薪制人员entity，传递数据。
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "form")
	public String form(AnnualSalaryPersonnel annualSalaryPersonnel, HttpServletRequest request,Model model) {
		if(StringUtils.isNotBlank(annualSalaryPersonnel.getId())){
			annualSalaryPersonnelService.get(annualSalaryPersonnel);
		}else{
			SalEmpView salEmpView = salEmpViewDao.get(annualSalaryPersonnel.getSalEmpView().getId());
			annualSalaryPersonnel.setSalEmpView(salEmpView);
		}
		
		model.addAttribute("annualSalaryPersonnel", annualSalaryPersonnel);
		return "modules/hr/annualSalaryPersonnelForm";
	}
	/**
	 * 根据主键，查询到年薪制人员信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param annualSalaryPersonnel
	 *            年薪制人员entity，传递数据。
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "detail")
	public String detail(AnnualSalaryPersonnel annualSalaryPersonnel,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		/**
		 * 通过工号、姓名、身份证查询人员信息
		 * */
		String userNo = (String) request.getSession().getAttribute("userNo");
		String name = (String) request.getSession().getAttribute("name");
		String papersNumber = (String) request.getSession().getAttribute(
				"papersNumber");
		Office office = (Office) request.getSession().getAttribute("office");
		List<AnnualSalaryPersonnel> annualSalaryPersonnelList = Lists
				.newArrayList();
		List<SalEmpView> salEmpList = Lists.newArrayList();
		/* 若能从session中获取到值，则说明是保存后重定向过来的，则不用再获取 */
		if (annualSalaryPersonnel.getSalEmpView() != null) {
			userNo = annualSalaryPersonnel.getSalEmpView().getLoginName();
			papersNumber = annualSalaryPersonnel.getSalEmpView().getPapersNumber();
			name = annualSalaryPersonnel.getSalEmpView().getName();
			office = annualSalaryPersonnel.getSalEmpView().getOffice();
			request.getSession().setAttribute("userNo", userNo);
			request.getSession().setAttribute("papersNumber", papersNumber);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("office", office);
		}
		
		SalEmpView sal = new SalEmpView();
		if( office != null || StringUtils.isNotBlank(name)
				|| StringUtils.isNotBlank(papersNumber)){
			AnnualSalaryPersonnel  asp = new AnnualSalaryPersonnel();
			sal.setLoginName(userNo);
			sal.setName(name);
			sal.setOffice(office);
			sal.setPapersNumber(papersNumber);
			model.addAttribute("salEmpView",sal);
			model.addAttribute("annualSalaryPersonnel", asp);
			salEmpList = salEmpViewDao.findList(sal);
			for(SalEmpView temp : salEmpList) {
				asp = new AnnualSalaryPersonnel();
				asp.setSalEmpView(temp);
				annualSalaryPersonnelList.add(asp);
				
			}
			
		}
	
/* 通过service将work信息保存至list */

      	SalEmpView salEmpView = salEmpViewDao.get(annualSalaryPersonnel.getUser().getId());
	    annualSalaryPersonnel.setSalEmpView(salEmpView);
		model.addAttribute("list", annualSalaryPersonnelList);
		
		return "modules/hr/annualSalaryPersonnelDetail";
	}


	/**
	 * 添加、更新年薪制人员信息。操作成功后，转至列表页面。
	 * 
	 * @param Certificate
	 *            年薪制人员信息entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequestMapping(value = "save")
	public String save(AnnualSalaryPersonnel annualSalaryPersonnel,
			HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
	
			SalEmpView sal = salEmpViewDao.get(annualSalaryPersonnel.getSalEmpView().getId());
			
			annualSalaryPersonnel.setSalEmpView(sal);
			if(annualSalaryPersonnelDao.judge(annualSalaryPersonnel) != null){
				String salary = annualSalaryPersonnel.getYearlySalary();
				annualSalaryPersonnel=annualSalaryPersonnelDao.judge(annualSalaryPersonnel);
				
				annualSalaryPersonnel.setYearlySalary(salary);
			}
			
			annualSalaryPersonnelService.save(annualSalaryPersonnel);
			
			addMessage(redirectAttributes, "保存成功");
		
		return "redirect:" + adminPath+ "/hr/annualSalaryPersonnel/list?repage";
				
              }
}