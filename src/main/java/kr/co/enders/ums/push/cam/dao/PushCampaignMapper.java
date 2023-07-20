/**
 * 작성자 : 김재환
 * 작성일시 : 2021.12.27
 * 설명 : 캠페인관리 매퍼
 */
package kr.co.enders.ums.push.cam.dao;

import java.util.List;

import kr.co.enders.ums.push.cam.vo.PushCampaignVO;
import kr.co.enders.ums.push.cam.vo.PushVO;

public interface PushCampaignMapper {
 
    
    /**
     * 캠페인 목록(문자)조회
     * @param campaignVO
     * @return
     * @throws Exception
     */
    public List<PushCampaignVO> getCampaignList(PushCampaignVO pushCampaignVO) throws Exception; 
    
    
    /**
     * 캠페인 정보 조회
     * @param pushCampaignVO
     * @return
     * @throws Exception
     */
    public PushCampaignVO getCampaignInfo(PushCampaignVO pushCampaignVO) throws Exception;
    
    
    /**
     * 캠페인 정보 등록
     * @param pushCampaignVO
     * @return
     * @throws Exception
     */
    public int insertCampaignInfo(PushCampaignVO pushCampaignVO) throws Exception;
    
    
    /**
     * 캠페인 정보 수정
     * @param pushCampaignVO
     * @return
     * @throws Exception
     */
    public int updateCampaignInfo(PushCampaignVO pushCampaignVO) throws Exception;
    
	/**
	 * PUSH 목록 조회
	 * @param pushVO
	 * @return
	 * @throws Exception
	 */
	public List<PushVO> getPushList(PushVO pushVO) throws Exception;
	 
	/**
	 * PUSH 정보를 등록
	 * @param pushVO
	 * @return
	 * @throws Exception
	 */
	public int insertPushInfo(PushVO pushVO) throws Exception;
 
	/**
	 * PUSH 문자상태 상태변경(정상, 삭제)
	 * @param pushVO
	 * @return
	 * @throws Exception
	 */
	public int updatePushStatus(PushVO pushVO) throws Exception;
	 
	/**
	 * PUSH 상태의 상태변경(발송대기, 발송승인, 발송요청, 발송완료)
	 * @param pushVO
	 * @return
	 * @throws Exception
	 */
	public int updatePushStatusAdmit(PushVO pushVO) throws Exception;
	
	/**
	 * PUSH 정보 조회
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	public PushVO getPushInfo(PushVO pushVO) throws Exception;

	/**
	 * PUSH 정보를 수정한다
	 * @param pushVO
	 * @return
	 * @throws Exception
	 */
	public int updatePushInfo(PushVO pushVO) throws Exception;
	
	/**
	 * 키값 기준 갯수 
	 * @param keygen
	 * @return
	 * @throws Exception
	 */
	public int getCountRequestKey(String requestKey) throws Exception;	
}
