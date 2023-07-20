/**
 * 작성자 : 김준희
 * 작성일시 : 2021.07.07
 * 설명 : 코드 조회 서비스 구현
 */
package kr.co.enders.ums.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enders.ums.com.dao.CodeDAO;
import kr.co.enders.ums.com.vo.CodeGroupVO;
import kr.co.enders.ums.com.vo.CodeVO;
import kr.co.enders.ums.ems.cam.vo.CampaignVO;
import kr.co.enders.ums.push.cam.vo.PushCampaignVO;
import kr.co.enders.ums.sms.cam.vo.SmsCampaignVO;

@Service
public class CodeServiceImpl implements CodeService {
	@Autowired
	private CodeDAO codeDAO;

	@Override
	public List<CodeVO> getCodeList(CodeVO codeVO) throws Exception {
		return codeDAO.getCodeList(codeVO);
	}
	
	@Override
	public CodeVO getCodeInfo(CodeVO codeVO) throws Exception {
		return codeDAO.getCodeInfo(codeVO);
	}
	
	@Override
	public CodeGroupVO getCodeGrpInfo(CodeGroupVO codeGroupVO) throws Exception {
		return codeDAO.getCodeGrpInfo(codeGroupVO);
	}
	
	@Override
	public List<CodeVO> getCodeGrpList(CodeVO codeVO) throws Exception {
		return codeDAO.getCodeGrpList(codeVO);
	}
	
	@Override
	public List<CodeVO> getCodeListByUpCd(CodeVO codeVO) throws Exception {
		return codeDAO.getCodeListByUpCd(codeVO);
	}
	
	@Override
	public List<CodeVO> getTimezoneList(CodeVO code) throws Exception {
		return codeDAO.getTimezoneList(code);
	}

	@Override
	public List<CodeVO> getDeptList(CodeVO codeVO) throws Exception {
		return codeDAO.getDeptList(codeVO);
	}
	
	@Override
	public List<CodeVO> getUserList(CodeVO codeVO) throws Exception {
		return codeDAO.getUserList(codeVO);
	}

	@Override
	public List<CodeVO> getAuthGroupList() throws Exception {
		return codeDAO.getAuthGroupList();
	}

	@Override
	public List<CodeVO> getProgramList(CodeVO codeVO) throws Exception {
		return codeDAO.getProgramList(codeVO);
	}

	@Override
	public List<CampaignVO> getCampaignList(CampaignVO campaignVO) throws Exception {
		return codeDAO.getCampaignList(campaignVO);
	}

	@Override
	public List<SmsCampaignVO> getCampaignSmsList(SmsCampaignVO smsCampaignVO) throws Exception {
		return codeDAO.getCampaignSmsList(smsCampaignVO);
	}
	
	@Override
	public List<PushCampaignVO> getCampaignPushList(PushCampaignVO pushCampaignVO) throws Exception {
		return codeDAO.getCampaignPushList(pushCampaignVO);
	}
	
	@Override
	public List<CodeVO> getWorkStatusList(CodeVO codeVO) throws Exception {
		return codeDAO.getWorkStatusList(codeVO);
	}

	@Override
	public String getUserFuncAuth(CodeVO codeVO) throws Exception {
		return codeDAO.getUserFuncAuth(codeVO);
	}
	
	@Override
	public List<CodeVO> getRcodeList(CodeVO codeVO) throws Exception {
		return codeDAO.getRcodeList(codeVO);
	}
	
	@Override
	public List<CodeVO> getKakaoTemplateList(CodeVO codeVO) throws Exception {
		return codeDAO.getKakaoTemplateList(codeVO);
	}
}
