
package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;
import cn.micromoving.bcp.modules.sr.entity.Achievement;
import cn.micromoving.bcp.modules.sys.entity.User;

/**
 * 附件表Entity
 * @author 附件表	
 * @version 2016-04-5
 */
public class Attachment extends DataEntity<Attachment>{
	private static final long serialVersionUID = 1L;
	/*用户编号*/
	private User user;
	/*附件名称*/
	private String attachmentName; 
	/*附件来源*/
	private String attachmentSource; 
	/*附件描述*/
	private String comments;
	/*成果id*/
	private Achievement achievement;
	/*附件路径*/
	private String attachmentPath;

	public Attachment(){
		super();		
	}

	public Attachment(String id){
		super(id);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getAttachmentSource() {
		return attachmentSource;
	}

	public void setAttachmentSource(String attachmentSource) {
		this.attachmentSource = attachmentSource;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Achievement getAchievement() {
		return achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
