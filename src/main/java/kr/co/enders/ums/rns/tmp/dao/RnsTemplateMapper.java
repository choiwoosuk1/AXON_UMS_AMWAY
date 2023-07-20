/**
 * 작성자 : 김준희
 * 작성일시 : 2021.08.21
 * 설명 : RNS 템플릿관리 매퍼
 */
package kr.co.enders.ums.rns.tmp.dao;

import java.util.List;

import kr.co.enders.ums.rns.tmp.vo.RnsTemplateVO;

public interface RnsTemplateMapper {
	/**
	 * RNS 템플릿 목록 조회
	 * @param templateVO
	 * @return
	 * @throws Exception
	 */
	public List<RnsTemplateVO> getTemplateList(RnsTemplateVO templateVO) throws Exception;
	
	/**
	 * RNS 템플릿 정보 조회
	 * @param templateVO
	 * @return
	 * @throws Exception
	 */
	public RnsTemplateVO getTemplateInfo(RnsTemplateVO templateVO) throws Exception;
	
	/**
	 * RNS 템플릿 정보 등록
	 * @param templateVO
	 * @return
	 * @throws Exception
	 */
	public int insertTemplateInfo(RnsTemplateVO templateVO) throws Exception;
	
	/**
	 * RNS 템플릿 정보 수정
	 * @param templateVO
	 * @return
	 * @throws Exception
	 */
	public int updateTemplateInfo(RnsTemplateVO templateVO) throws Exception;
}
