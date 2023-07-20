/**
 * 작성자 : 김준희
 * 작성일시 : 2021.07.06
 * 설명 : 사용자 로그인 VO
 */
package kr.co.enders.ums.lgn.vo;

public class LoginVO {
	private String pUserId;
	private String pUserPwd;
	private String pDirectMenu;
	private String pPwInitYn; 
	
	public String getpUserId() {
		return pUserId;
	}
	public void setpUserId(String pUserId) {
		this.pUserId = pUserId;
	}
	public String getpUserPwd() {
		return pUserPwd;
	}
	public void setpUserPwd(String pUserPwd) {
		this.pUserPwd = pUserPwd;
	}
	public String getpDirectMenu() {
		return pDirectMenu;
	}
	public void setpDirectMenu(String pDirectMenu) {
		this.pDirectMenu = pDirectMenu;
	}
	public String getpPwInitYn() {
		return pPwInitYn;
	}
	public void setpPwInitYn(String pPwInitYn) {
		this.pPwInitYn = pPwInitYn;
	}
}
