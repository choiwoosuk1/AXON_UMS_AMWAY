/**
 * 작성자 : 김준희
 * 작성일시 : 2021.09.16
 * 설명 : DB DbConnUserVO   
 */
package kr.co.enders.ums.sys.dbc.vo;

import kr.co.enders.ums.com.vo.CommonVO;

public class DbConnUserVO extends CommonVO {
	private int dbConnNo;			// DB Connection번호 
	private String userId;		// 아이디
	private String userNm;		// 사용자이름
	private int deptNo;			// 부서번호
	private String deptNm;		// 부서명
	private String status;		// 상태(000:정상, 001:사용중지, 002:삭제)
	private String statusNm;	// 상태명 
	
	private String orgCd;		// 조직코드
	private String orgKorNm;	// 조직한글명
	private String orgEngNm;	// 조직영문명
	private String positionGb;	// 직급
	private String positionNm;	// 직급명
	private String jobGb;		// 직책코드
	private String jobNm;		// 직책명 
	private String uilang;		// UI언어권
	
	public int getDbConnNo() {
		return dbConnNo;
	}
	public void setDbConnNo(int dbConnNo) {
		this.dbConnNo = dbConnNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusNm() {
		return statusNm;
	}
	public void setStatusNm(String statusNm) {
		this.statusNm = statusNm;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getOrgKorNm() {
		return orgKorNm;
	}
	public void setOrgKorNm(String orgKorNm) {
		this.orgKorNm = orgKorNm;
	}
	public String getOrgEngNm() {
		return orgEngNm;
	}
	public void setOrgEngNm(String orgEngNm) {
		this.orgEngNm = orgEngNm;
	}
	public String getPositionGb() {
		return positionGb;
	}
	public void setPositionGb(String positionGb) {
		this.positionGb = positionGb;
	}
	public String getPositionNm() {
		return positionNm;
	}
	public void setPositionNm(String positionNm) {
		this.positionNm = positionNm;
	}
	public String getJobGb() {
		return jobGb;
	}
	public void setJobGb(String jobGb) {
		this.jobGb = jobGb;
	}
	public String getJobNm() {
		return jobNm;
	}
	public void setJobNm(String jobNm) {
		this.jobNm = jobNm;
	}
	public String getUilang() {
		return uilang;
	}
	public void setUilang(String uilang) {
		this.uilang = uilang;
	}

}
