/**
 * 작성자 : 김준희
 * 작성일시 : 2021.07.06
 * 설명 : 사용자 로그인 서비스 구현
 */
package kr.co.enders.ums.lgn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enders.ums.lgn.dao.LoginDAO;
import kr.co.enders.ums.lgn.vo.LoginHistVO;
import kr.co.enders.ums.lgn.vo.LoginVO;
import kr.co.enders.ums.sys.acc.vo.SysMenuVO;
import kr.co.enders.ums.sys.acc.vo.UserProgVO;
import kr.co.enders.ums.sys.acc.vo.UserVO;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO loginDAO;

	@Override
	public UserVO isValidUser(LoginVO loginVO) throws Exception {
		
		UserVO initPwdInfo = loginDAO.getInitPwdInfo(loginVO);
		if(initPwdInfo !=null && "Y".equals(initPwdInfo.getPwInitYn())) {
			loginVO.setpPwInitYn("Y");
		}
		return loginDAO.isValidUser(loginVO);
	}

	@Override
	public UserVO isSSOUser(LoginVO loginVO) throws Exception {
		return loginDAO.isSSOUser(loginVO);
	} 
	
	@Override
	public List<UserProgVO> getUserProgList(String userId) throws Exception {
		return loginDAO.getUserProgList(userId);
	} 

	@Override
	public List<SysMenuVO> getUserMenuList(UserVO userVO) throws Exception {
		return loginDAO.getUserMenuList(userVO);
	}
	
	@Override
	public UserVO procRimanUserInsert(UserVO userVO) throws Exception {
		return loginDAO.procRimanUserInsert(userVO);
	}
	
	@Override
	public UserVO getInitPwdInfo(LoginVO loginVO) throws Exception {
		return loginDAO.getInitPwdInfo(loginVO);
	}

	@Override
	public int updateLoginDt(UserVO userVO) throws Exception {
		return loginDAO.updateLoginDt(userVO);
	}

	@Override
	public int updatePwdErrorCnt(String userId) throws Exception {
		return loginDAO.updatePwdErrorCnt(userId);
	}

	@Override
	public int getPwerrorCnt(String userId) throws Exception {
		return loginDAO.getPwerrorCnt(userId);
	}

	@Override
	public int updateCertilock(String userId) throws Exception {
		return loginDAO.updateCertilock(userId);
	}
}
