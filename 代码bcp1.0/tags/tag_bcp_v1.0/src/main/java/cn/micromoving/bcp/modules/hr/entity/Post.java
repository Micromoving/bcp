package cn.micromoving.bcp.modules.hr.entity;

import java.util.List;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 岗位职数表Entity
 * @author luyihao
 * @version 2016-06-01
 */
public class Post extends DataEntity<Post>{
	
	private static final long serialVersionUID = 1L;	 
    /* 部门 */
    private Office office;    
	/*岗位等级*/
	private String postLevel;
	/*岗位名称*/
	private String postName;
	/*新岗位名称*/
	private String newPostName;
	/*备注*/
	private String comments;	
	/*岗位设置*/
	private String positionSetting;												
	/*职数设置*/
	private Double postNumberSetting;
    /*现聘数*/
	private Double recruitNum;
  
	private List<String> postLevelList;
	
	public String getPositionSetting() {
		return positionSetting;
	}
	public void setPositionSetting(String positionSetting) {
		this.positionSetting = positionSetting;
	}
	
	public Double getPostNumberSetting() {
		return postNumberSetting;
	}
	public void setPostNumberSetting(Double postNumberSetting) {
		this.postNumberSetting = postNumberSetting;
	}
	public Double getRecruitNum() {
		return recruitNum;
	}
	
	
	public List<String> getPostLevelList() {
		return postLevelList;
	}
	public void setPostLevelList(List<String> postLevelList) {
		this.postLevelList = postLevelList;
	}
	public void setRecruitNum(Double recruitNum) {
		this.recruitNum = recruitNum;
	}
	
	public String getPostLevel() {
		return postLevel;
	}
	public void setPostLevel(String postLevel) {
		this.postLevel = postLevel;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}	
	
	public String getNewPostName() {
		return newPostName;
	}
	public void setNewPostName(String newPostName) {
		this.newPostName = newPostName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
}
