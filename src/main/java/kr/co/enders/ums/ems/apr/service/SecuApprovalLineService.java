/**
 * 작성자 : 김준희
 * 작성일시 : 2021.08.23
 * 설명 : 메일발송결재 서비스 인터페이스
 */
package kr.co.enders.ums.ems.apr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.enders.ums.ems.apr.vo.SecuApprovalLineVO;
import kr.co.enders.ums.ems.cam.vo.AttachVO;
import kr.co.enders.ums.ems.cam.vo.TaskVO;
import kr.co.enders.ums.rns.svc.vo.RnsAttachVO;
import kr.co.enders.ums.rns.svc.vo.RnsSecuApprovalLineVO;
import kr.co.enders.ums.rns.svc.vo.RnsServiceVO;

@Service
public interface SecuApprovalLineService {
	
	/**
	 * 메일발송결재 목록 조회
	 * @param approvalLineVO
	 * @return
	 * @throws Exception
	 */
	public List<SecuApprovalLineVO> getApprovalLineList(SecuApprovalLineVO approvalLineVO) throws Exception;
	
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
	@Transactional(value="transactionManager")
	public int updateMailAprStep(SecuApprovalLineVO approvalLineVO) throws Exception;
	
	/**
	 * 사용자의 결재요청 건수 조회
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int getApprCount(String userId) throws Exception;
	
	/**
	 * 결재반려사유코드명 조회
	 * @param approvalLineVO
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
	
}
