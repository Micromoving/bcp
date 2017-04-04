package cn.micromoving.bcp.modules.hr.entity;

import cn.micromoving.bcp.common.persistence.DataEntity;
/**
 * 离退休表Entity
 * @author baoke
 * @version 2016-06-01
 */

public class SafeQuestion extends DataEntity<SafeQuestion>{
	
	private static final long serialVersionUID = 1L;
	/*第一验证问题	*/													
	private String firstVerificationQuestion;													
	/*第二验证问题*/														
	private String secondVerificationQuestion;													
	/*第三验证问题*/														
	private String thirdVerificationQuestion	;												
	/*自定义问题*/														
	private String customQuestion;													
	/*第一答案	*/													
	private String firstAnswer;													
	/*第一答案	*/													
	private String secondAnswer;													
	/*第一答案	*/													
	private String thirdAnswer;													
	/*自定义答案*/														
	private String customAnswer;
	public String getFirstVerificationQuestion() {
		return firstVerificationQuestion;
	}
	public void setFirstVerificationQuestion(String firstVerificationQuestion) {
		this.firstVerificationQuestion = firstVerificationQuestion;
	}
	public String getSecondVerificationQuestion() {
		return secondVerificationQuestion;
	}
	public void setSecondVerificationQuestion(String secondVerificationQuestion) {
		this.secondVerificationQuestion = secondVerificationQuestion;
	}
	public String getThirdVerificationQuestion() {
		return thirdVerificationQuestion;
	}
	public void setThirdVerificationQuestion(String thirdVerificationQuestion) {
		this.thirdVerificationQuestion = thirdVerificationQuestion;
	}
	public String getCustomQuestion() {
		return customQuestion;
	}
	public void setCustomQuestion(String customQuestion) {
		this.customQuestion = customQuestion;
	}
	public String getFirstAnswer() {
		return firstAnswer;
	}
	public void setFirstAnswer(String firstAnswer) {
		this.firstAnswer = firstAnswer;
	}
	public String getSecondAnswer() {
		return secondAnswer;
	}
	public void setSecondAnswer(String secondAnswer) {
		this.secondAnswer = secondAnswer;
	}
	public String getThirdAnswer() {
		return thirdAnswer;
	}
	public void setThirdAnswer(String thirdAnswer) {
		this.thirdAnswer = thirdAnswer;
	}
	public String getCustomAnswer() {
		return customAnswer;
	}
	public void setCustomAnswer(String customAnswer) {
		this.customAnswer = customAnswer;
	}
	
}