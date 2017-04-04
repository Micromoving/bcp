package cn.micromoving.bcp.modules.hr.utils;

import org.apache.poi.ss.usermodel.Workbook;

import cn.micromoving.bcp.common.utils.SpringContextHolder;
import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.service.EmployeeService;
import cn.micromoving.bcp.modules.sys.entity.User;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

/**
 * 
 * @author 微动信息 Thinkpad
 * @version $Revision:  $ $Date:  $
 * <pre>
 * ■变更记录■
 * Oct 22, 2016 创建
 * </pre>
 */
public class HrEmpUtils {
    private static EmployeeService employeeService = SpringContextHolder.getBean(EmployeeService.class);


    
    public static Workbook exportEmpDetails(Employee data){
        return null;
    }
    public static Employee getEmpByLoginName(String loginName){
        
        User user = UserUtils.getByLoginName(loginName);
        Employee employee = employeeService.get(user.getId());
        employee.setUser(user);
        return employee;
    }
}
