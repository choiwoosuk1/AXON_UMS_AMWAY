/**
 * 작성자 : 김준희
 * 작성일시 : 2021.08.25
 * 설명 : 발송결재라인 처리를 위한 조직 VO
 */
package kr.co.enders.ums.ems.cam.vo;

public class ApprovalOrgVO {
	private String orgCd;		// 조직코드
	private String orgNm;		// 조직명
	private String upOrgCd;		// 상위조직코드
	private int lvlVal;			// 조직레벨
	private int childCnt;		// 하위조직수
	private String uilang;		// 언어권
	private String arrOrgCd[];	// 부서 여러개
	
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getUpOrgCd() {
		return upOrgCd;
	}
	public void setUpOrgCd(String upOrgCd) {
		this.upOrgCd = upOrgCd;
	}
	public int getLvlVal() {
		return lvlVal;
	}
	public void setLvlVal(int lvlVal) {
		this.lvlVal = lvlVal;
	}
	public int getChildCnt() {
		return childCnt;
	}
	public void setChildCnt(int childCnt) {
		this.childCnt = childCnt;
	}
	public String getUilang() {
		return uilang;
	}
	public void setUilang(String uilang) {
		this.uilang = uilang;
	}
	public String[] getArrOrgCd() {
		return arrOrgCd;
	}
	public void setArrOrgCd(String[] arrOrgCd) {
		this.arrOrgCd = arrOrgCd;
	}
}
