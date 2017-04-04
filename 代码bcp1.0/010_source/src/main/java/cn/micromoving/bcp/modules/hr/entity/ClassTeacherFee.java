package cn.micromoving.bcp.modules.hr.entity;

import java.util.ArrayList;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

import com.google.common.collect.Lists;

/**
 * 工作经验表 Entity
 * 
 * @author 工作经验表
 * @version 2016-04-5
 */
public class ClassTeacherFee extends DataEntity<ClassTeacherFee> {

	private static final long serialVersionUID = 1L;
	private SalEmpView salEmpView;
	/*用户编号*/
	private User user;
	/*部门*/
	private Office office;
	/* 员工编号 */
	private Employee employee;
	/* 带班数 */
	private String classNumber;
	/* 岗位类型 */
	private String postType;
	/*带班数*/
	private ArrayList<String>  classNumberList=Lists.newArrayList();
	
	private ArrayList<String>  dataIdList=Lists.newArrayList();
	
	
	public SalEmpView getSalEmpView() {
        return salEmpView;
    }

    public void setSalEmpView(SalEmpView salEmpView) {
        this.salEmpView = salEmpView;
    }

    @ExcelField(title = "部门", type = 0, align = 2, sort = 40)
    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    @ExcelField(title = "工号", type = 0, align = 2, sort = 20)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ExcelField(title = "带班数", type = 0, align = 2, sort = 60)
	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

    public ArrayList<String> getClassNumberList() {
        return classNumberList;
    }

    public void setClassNumberList(ArrayList<String> classNumberList) {
        this.classNumberList = classNumberList;
    }

    public ArrayList<String> getDataIdList() {
        return dataIdList;
    }

    public void setDataIdList(ArrayList<String> dataIdList) {
        this.dataIdList = dataIdList;
    }
	@ExcelField(title = "岗位类型", type = 0, align = 2, sort = 50)
	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

}
