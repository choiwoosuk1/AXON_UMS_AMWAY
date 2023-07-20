/**
 * 작성자 : 김준희
 * 작성일시 : 2021.08.23
 * 설명 : 메일발송결재 매퍼
 */
package kr.co.enders.ums.ems.apr.dao;

import java.util.List;

import kr.co.enders.ums.ems.apr.vo.SecuApprovalLineVO;
import kr.co.enders.ums.ems.cam.vo.AttachVO;
import kr.co.enders.ums.ems.cam.vo.TaskVO;

public interface SecuApprovalLineMapper {
	
	/**
	 * 메일발송결재 목록 조회
	 * @param approvalLineVO
	 * @return
	 * @throws Exception
	 */
	public List<SecuApprovalLineVO> getApprovalLineList(SecuApprovalLineVO approvalLineVO) throws Exception;
	
	/**
	 * 메일발송결재 목록 조회
	 * @param approvalLineVO
	 * @return
	 * @throws Exception
	 */
	public List<SecuApprovalLineVO> getApprovalLineEmsList(SecuApprovalLineVO approvalLineVO) throws Exception;
	
	/**
	 * 메일발송결재 목록 조회
	 * @param approvalLineVO
	 * @return
	 * @throws Exception
	 */
	public List<SecuApprovalLineVO> getApprovalLineRnsList(SecuApprovalLineVO approvalLineVO) throws Exception;	
	
	/**
	 * 메일 정보 조회
	 * @param approvalLineVO
	 * @return
	 * @throws Exception
	 */
	public SecuApprovalLineVO getMailInfo(SecuApprovalLineVO approvalLineVO) throws Exception;
	
	/**
	 * 첨부파일 목록 조회
	 * @param taskNo
	 * @return
	 * @throws Exception
	 */
	public List<AttachVO> getAttachList(int taskNo) throws Exception;
	
	/**
	 * 메일별 결재목록 조회
	 * @param taskNo
	 * @return
	 * @throws Exception
	 */
	public List<SecuApprovalLineVO> getMailApprLineList(SecuApprovalLineVO approvalLineV) throws Exception;
	
	/**
	 * 메일 결재라인 스탭 상태 변경
	 * @param approvalLineVO
	 * @return
	 * @throws Exception
	 */
	public int updateMailAprStep(SecuApprovalLineVO approvalLineVO) throws Exception;
	
	/**
	 * 메일 결재라인 스탭 상태 변경(다음 스탭 상태 요청으로 변경)
	 * @param approvalLineVO
	 * @return
	 * @throws Exception
	 */
	public int updateMailAprStepNext(SecuApprovalLineVO approvalLineVO) throws Exception;
	
	/**
	 * 캠페인 주업무 발송승인
	 * @param taskVO
	 * @return
	 * @throws Exception
	 */
	public int updateTaskStatusAdmit(TaskVO taskVO) throws Exception;
	
	/**
	 * 캠페인 보조업무 발송승인
	 * @param taskVO
	 * @return
	 * @throws Exception
	 */
	public int updateSubTaskStatusAdmit(TaskVO taskVO) throws Exception;
	
	/**
	 * 사용자의 결재요청 건수 조회
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int getApprCount(String userId) throws Exception;
	
	/**
	 * 결재반려사유코드명 조회
	 * @param rejectCd
	 * @return
	 * @throws Exception
	 */
	public String getRejectNm(SecuApprovalLineVO approvalLineVO) throws Exception;
	
	/**
	 * 메일발송결제 준법감시 rns
	 * @param approvalLineVO
	 * @return
	 * @throws Exception
	 */
	public List<SecuApprovalLineVO> getRnsProhibitInfo(SecuApprovalLineVO approvalLineVO) throws Exception;
	
	/**
	 * 메일발송결제 준법감시 금지어
	 * @param approvalLineVO
	 * @return
	 * @throws Exception
	 */
	public List<SecuApprovalLineVO> getProhibitList(SecuApprovalLineVO approvalLineVO) throws Exception;
	
	/**
	 * 메일발송결제 준법감시 mail 
	 * @param approvalLineVO
	 * @return
	 * @throws Exception
	 */
	public List<SecuApprovalLineVO> getMailProhibitInfo(SecuApprovalLineVO approvalLineVO) throws Exception;
	
	
	public List<SecuApprovalLineVO> nextMailAprStep(SecuApprovalLineVO approvalLineVO) throws Exception;
	
	public List<SecuApprovalLineVO> nowMailAprStep(SecuApprovalLineVO approvalLineVO) throws Exception;
}
