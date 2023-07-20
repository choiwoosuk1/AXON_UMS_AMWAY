/**
 * 작성자 : 김준희
 * 작성일시 : 2021.07.06
 * 설명 : 사용자 로그인 서비스 인터페이스
 */
package kr.co.enders.ums.lgn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.enders.ums.lgn.vo.LoginHistVO;
import kr.co.enders.ums.lgn.vo.LoginVO;
import kr.co.enders.ums.sys.acc.vo.SysMenuVO;
import kr.co.enders.ums.sys.acc.vo.UserProgVO;
import kr.co.enders.ums.sys.acc.vo.UserVO;

@Service
public interface LoginService {
	/**
	 * 사용자 아이디 비밀번호 확인
	 * @param loginVO
	 * @return
	 * @throws Exception
	 */
	public UserVO isValidUser(LoginVO loginVO) throws Exception;
	
	/**
	 * SSO 사용자 아이디 확인
	 * @param loginVO
	 * @return
	 * @throws Exception
	 */
	public UserVO isSSOUser(LoginVO loginVO) throws Exception;
	
	/**
	 * 사용자 프로그램 사용 권한 조회
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<UserProgVO> getUserProgList(String userId) throws Exception;
		
	/**
	 * 사용자 권한 매핑 메뉴 목록 조회
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<SysMenuVO> getUserMenuList(UserVO userVO) throws Exception;
	/**
	 * 사용자 로그인 일자 update
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public int updateLoginDt(UserVO userVO) throws Exception;
	/**
	 * 비밀번호 error cnt 업데이트
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int updatePwdErrorCnt(String userId) throws Exception;
	/**
	 * 비밀번호 조회
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int getPwerrorCnt(String userId) throws Exception;
	
	/**
	 * 사용자 인증잠금 
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	public int updateCertilock(String userId) throws Exception;
	/**
	 * IAM 인증 사용자확인, 등록 프로시져 호출  
	 * @param loginVO
	 * @return
	 * @throws Exception
	 */
	public UserVO procRimanUserInsert(UserVO userVO) throws Exception;
	
	/**
	 * 암호 초기화 정보
	 * @param loginVO
	 * @return
	 * @throws Exception
	 */
	public UserVO getInitPwdInfo(LoginVO loginVO) throws Exception;
	
}
