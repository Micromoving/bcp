package cn.micromoving.bcp.modules.hr.web;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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
import cn.micromoving.bcp.modules.hr.dao.SalEmpViewDao;
import cn.micromoving.bcp.modules.hr.entity.NotSeriesSalaryItems;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.hr.entity.SalaryItems;
import cn.micromoving.bcp.modules.hr.entity.Work;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.hr.service.NotSeriesSalaryItemsService;
import cn.micromoving.bcp.modules.hr.service.SalaryItemsService;
import cn.micromoving.bcp.modules.hr.utils.HrConstant;
import cn.micromoving.bcp.modules.sys.entity.Office;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "${adminPath}/hr/notSeriesSalaryItems")
public class NotSeriesSalaryItemsController extends BaseController {
	@Autowired
	private NotSeriesSalaryItemsService notSeriesSalaryItemsService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private SalaryItemsService salaryItemsService;
	@Autowired
	private SalEmpViewDao salEmpViewDao;

	/**
	 * 根据主键， 取得工作经验记录。
	 * 
	 * @param id
	 *            primary key
	 * @return Work entity
	 */
	@ModelAttribute
	public NotSeriesSalaryItems get(@RequestParam(required = false) String id) {
		/* 判断id是否为空，如果有值，调用service来取得id对应的工作经验记录。否则创建一个新的工作经验对象。 */
		if (StringUtils.isNotBlank(id)) {
			return notSeriesSalaryItemsService.get(id);
		} else {
			return new NotSeriesSalaryItems();
		}
	}

	/**
	 * 根据主键，查询到工作经验信息，将此信息绑定到model中，在JSP页面中可以读取。
	 * 
	 * @param work
	 *            工作经验entity，传递数据。
	 * @param model
	 *            视图模型
	 * @return 返回工作经验编辑页面
	 */
	@RequestMapping(value = "form")
	public String form(Work work, Model model, HttpServletRequest request) {

		/* 返回form页面 */
		return "";
	}

	/**
	 * 添加、更新工作经验记录。操作成功后，转至列表页面。
	 * 
	 * @param work
	 *            工作经验entity
	 * @param request
	 *            http请求
	 * @param model
	 *            视图模型
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "save")
	public String save(NotSeriesSalaryItems notSeriesSalaryItems,
			HttpServletRequest request, Model model,
			HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws UnsupportedEncodingException {
		SalEmpView sal = new SalEmpView();
		for (int k = 0; k < notSeriesSalaryItems.getEmployeeList().size(); k++) {
			StringBuffer sb = new StringBuffer();
			List<String> tempSelectedItems = Lists.newArrayList();
			/* 由于相同勾选项会放在一个值里，因此需要拆分 */
			for (String str : notSeriesSalaryItems.getSelectedItems()) {
				if (StringUtils.isBlank(str)) {
					tempSelectedItems.add("0");
				} else {
					String[] strs = str.split(",");
					String flag = null;
					for (String string : strs) {
						if (string.contains(notSeriesSalaryItems
								.getEmployeeList().get(k))) {
							flag = "1";
						}
					}
					if ("1".equals(flag)) {
						tempSelectedItems.add("1");
					} else {
						tempSelectedItems.add("0");
					}
				}
			}
			/* 生成工资项 */
			for (int j = 0; j < 20; j++) {
				if (tempSelectedItems.size() <= j) {
					sb.append("0");
					continue;
				}

				String str = tempSelectedItems.get(j);
				if (!str.equals("0")) {
					sb.append("1");
				} else {
					sb.append("0");
				}
			}
			sal = salEmpViewDao.get(notSeriesSalaryItems.getEmployeeList().get(
					k));
			notSeriesSalaryItems.setSal(sal);

			NotSeriesSalaryItems temp = notSeriesSalaryItemsService
					.get(notSeriesSalaryItems);
			NotSeriesSalaryItems notSeriesSalaryItems2 = new NotSeriesSalaryItems();
			if (temp != null) {
				notSeriesSalaryItems2 = temp;
			} else {
				notSeriesSalaryItems2 = notSeriesSalaryItems;
				notSeriesSalaryItems2.setId(null);
			}
			notSeriesSalaryItems2.setNotSeriesSalaryItems(sb.toString());
			/* 调用save保存获取到的work值 */
			notSeriesSalaryItemsService.save(notSeriesSalaryItems2);
		}
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "保存成功");
		/* 重定向至list页面 */
		return "redirect:" + adminPath + "/hr/notSeriesSalaryItems/list";
	}

	/**
	 * 删除教工作经验记录。操作成功后，转至列表页面。
	 * 
	 * @param work
	 *            工作经验entity
	 * @param redirectAttributes
	 *            重定向属性集
	 * @return 操作完成后，重定向到列表页面。
	 */
	@RequestMapping(value = "delete")
	public String delete(NotSeriesSalaryItems notSeriesSalaryItems,
			RedirectAttributes redirectAttributes) {
		/* 调用delete删除当前work值 */
		notSeriesSalaryItemsService.delete(notSeriesSalaryItems);
		/* 添加Flash信息 */
		addMessage(redirectAttributes, "删除成功");
		/* 重定向至list页面 */
		return "";
	}

	/**
	 * 查询用户的全部工作经验信息（不分页），以list的方式返回，并在JSP页面中通过循环来显示list中的各条记录值。
	 * 
	 * @param work
	 *            工作经验
	 * @param request
	 *            http请求
	 * @param response
	 *            http 响应
	 * @param model
	 *            视图模型
	 * @return 用户的全部工作经验信息（不分页）
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = { "list", "" })
	public String list(NotSeriesSalaryItems notSeriesSalaryItems,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws UnsupportedEncodingException {
		/**
		 * 通过工号、姓名、身份证查询人员信息
		 * */
		String userNo = (String) request.getSession().getAttribute("userNo");
		String name = (String) request.getSession().getAttribute("name");
		String papersNumber = (String) request.getSession().getAttribute(
				"papersNumber");
		Office office = (Office) request.getSession().getAttribute("office");
		List<NotSeriesSalaryItems> notSeriesSalaryItemsList = Lists
				.newArrayList();
		List<SalEmpView> salEmpList = Lists.newArrayList();
		/* 若能从session中获取到值，则说明是保存后重定向过来的，则不用再获取 */
		if (notSeriesSalaryItems.getSal() != null) {
			userNo = notSeriesSalaryItems.getSal().getLoginName();
			papersNumber = notSeriesSalaryItems.getSal().getPapersNumber();
			name = notSeriesSalaryItems.getSal().getName();
			office = notSeriesSalaryItems.getSal().getOffice();
			request.getSession().setAttribute("userNo", userNo);
			request.getSession().setAttribute("papersNumber", papersNumber);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("office", office);
		}
		/* 查询并设置人员类型 */
		SalEmpView salTemp = new SalEmpView();
		if (StringUtils.isNotBlank(userNo) || StringUtils.isNotBlank(name)
				|| StringUtils.isNotBlank(papersNumber) || office != null) {
			NotSeriesSalaryItems tempNotSeriesSalaryItems = new NotSeriesSalaryItems();
			salTemp.setLoginName(userNo);
			salTemp.setName(name);
			salTemp.setPapersNumber(papersNumber);
			salTemp.setOffice(office);
			salTemp.setStaffType(HrConstant.STAFF_TYPE_2);
			tempNotSeriesSalaryItems.setSal(salTemp);
			model.addAttribute("sal", salTemp);
			model.addAttribute("notSeriesSalaryItems", tempNotSeriesSalaryItems);
			salEmpList = salEmpViewDao.findList(salTemp);

			for (SalEmpView temp : salEmpList) {
				tempNotSeriesSalaryItems = new NotSeriesSalaryItems();
				tempNotSeriesSalaryItems.setSal(temp);
				notSeriesSalaryItemsList.add(tempNotSeriesSalaryItems);
			}
		}

		// 查询非在编人员需要确定的工资项
		SalaryItems itemTtemp = new SalaryItems();
		itemTtemp.setIsNotSeries("1");
		List<SalaryItems> salaryItemsList = salaryItemsService
				.findList(itemTtemp);
		// 设置勾选项
		for (int i = 0; i < notSeriesSalaryItemsList.size(); i++) {
			NotSeriesSalaryItems notSeriesSalaryItemsTemp = notSeriesSalaryItemsList
					.get(i);
			notSeriesSalaryItemsTemp = notSeriesSalaryItemsService
					.get(notSeriesSalaryItemsTemp);
			if (notSeriesSalaryItemsTemp != null
					&& notSeriesSalaryItemsTemp.getNotSeriesSalaryItems() != null) {
				List<SalaryItems> salaryItemsListTemp = salaryItemsService
						.findList(itemTtemp);
				String[] strs = notSeriesSalaryItemsTemp
						.getNotSeriesSalaryItems().split("");
				for (SalaryItems temp : salaryItemsListTemp) {
					if (temp.getSalaryItemsName().equals("卫生费")) {
						temp.setUseable(strs[3]);
						continue;
					} else if (temp.getSalaryItemsName().equals("新增补贴")) {
						temp.setUseable(strs[1]);
						continue;
					} else if (temp.getSalaryItemsName().equals("院龄津贴")) {
						temp.setUseable(strs[0]);
						continue;
					} else if (temp.getSalaryItemsName().equals("独子费")) {
						temp.setUseable(strs[2]);
						continue;
					}
				}
				notSeriesSalaryItemsList.get(i).setSalaryItemsList(
						salaryItemsListTemp);
			} else {
				for (SalaryItems temp : salaryItemsList) {
					temp.setUseable("0");
				}
				notSeriesSalaryItemsList.get(i).setSalaryItemsList(
						salaryItemsList);
			}
		}

		/* 通过service将work信息保存至list */
		
		model.addAttribute("list", notSeriesSalaryItemsList);
		/* 返回list页面 */
		return "modules/hr/notSeriesSalarList";
	}
}
