/**
 * 작성자 : 김준희
 * 작성일시 : 2021.07.22
 * 설명 : 캠페인관리 데이터 처리
 */
package kr.co.enders.ums.ems.cam.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.enders.ums.ems.ana.vo.SendLogVO;
import kr.co.enders.ums.ems.apr.vo.SecuApprovalLineVO;
import kr.co.enders.ums.ems.cam.vo.ApprovalOrgVO;
import kr.co.enders.ums.ems.cam.vo.AttachVO;
import kr.co.enders.ums.ems.cam.vo.CampaignTemplateAttachVO;
import kr.co.enders.ums.ems.cam.vo.CampaignTemplateProhibitWordVO;
import kr.co.enders.ums.ems.cam.vo.CampaignTemplateVO;
import kr.co.enders.ums.ems.cam.vo.CampaignTemplateWebAgentVO;
import kr.co.enders.ums.ems.cam.vo.CampaignVO;
import kr.co.enders.ums.ems.cam.vo.EmailClinicVO;
import kr.co.enders.ums.ems.cam.vo.LinkVO;
import kr.co.enders.ums.ems.cam.vo.MailMktChkVO;
import kr.co.enders.ums.ems.cam.vo.ProhibitWordVO;
import kr.co.enders.ums.ems.cam.vo.SendTestLogVO;
import kr.co.enders.ums.ems.cam.vo.TaskVO;
import kr.co.enders.ums.ems.cam.vo.TestUserVO;
import kr.co.enders.ums.ems.cam.vo.WebAgentVO;
import kr.co.enders.ums.sys.acc.vo.UserVO;

@Repository
public class CampaignDAO implements CampaignMapper {
	@Autowired
	private SqlSession sqlSessionEms;

	@Override
	public List<CampaignVO> getCampaignList(CampaignVO campaignVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getCampaignList(campaignVO);
	}

	@Override
	public List<CampaignVO> getCampaignRnsList(CampaignVO campaignVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getCampaignRnsList(campaignVO);
	}
	
	@Override
	public List<CampaignVO> getCampaignEmsOnlyList(CampaignVO campaignVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getCampaignEmsOnlyList(campaignVO);
	}
	
	@Override
	public List<CampaignVO> getCampaignRnsOnlyList(CampaignVO campaignVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getCampaignRnsOnlyList(campaignVO);
	}
	
	@Override
	public CampaignVO getCampaignInfo(CampaignVO campaignVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getCampaignInfo(campaignVO);
	}

	@Override
	public int insertCampaignInfo(CampaignVO campaignVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertCampaignInfo(campaignVO);
	}

	@Override
	public int updateCampaignInfo(CampaignVO campaingVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateCampaignInfo(campaingVO);
	}
	
	public List<CampaignTemplateVO> getCampaignTemplateList(CampaignTemplateVO campaignTemplateVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getCampaignTemplateList(campaignTemplateVO);
	}
	
	@Override
	public CampaignTemplateVO getCampaignTemplateInfo(int tid) throws Exception{
		return sqlSessionEms.getMapper(CampaignMapper.class).getCampaignTemplateInfo(tid);
	}
	
	@Override
	public int getCurrCampaignTemplateTid() throws Exception{
		return sqlSessionEms.getMapper(CampaignMapper.class).getCurrCampaignTemplateTid();
	}
	
	@Override
	public List<CampaignTemplateAttachVO> getCampaignTemplateAttachList(int tid) throws Exception{
		return sqlSessionEms.getMapper(CampaignMapper.class).getCampaignTemplateAttachList(tid);
	}
	
	@Override
	public CampaignTemplateWebAgentVO getCampaignTemplateWebAgentInfo(int tid) throws Exception{
		return sqlSessionEms.getMapper(CampaignMapper.class).getCampaignTemplateWebAgentInfo(tid);
	}
	
	@Override
	public CampaignTemplateVO getCampaignTemplateEaiCampNo(String eaiCampNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getCampaignTemplateEaiCampNo(eaiCampNo);
	}
	
	@Override
	public List<CampaignTemplateProhibitWordVO> getCampaignTemplateProhibitWordList(int tid) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getCampaignTemplateProhibitWordList(tid);
	}
	
	@Override
	public int insertCampaignTemplateInfo(CampaignTemplateVO campaignTemplateVO) throws Exception{
		return sqlSessionEms.getMapper(CampaignMapper.class).insertCampaignTemplateInfo(campaignTemplateVO);
	}
	
	@Override
	public int updateCampaignTemplateInfo(CampaignTemplateVO campaignTemplateVO) throws Exception{
		return sqlSessionEms.getMapper(CampaignMapper.class).updateCampaignTemplateInfo(campaignTemplateVO);
	}
	
	@Override
	public int updateCampaignTemplatePartInfo(CampaignTemplateVO campaignTemplateVO) throws Exception{
		return sqlSessionEms.getMapper(CampaignMapper.class).updateCampaignTemplatePartInfo(campaignTemplateVO);
	}

	@Override
	public int insertCampaignTemplateWebAgent(CampaignTemplateWebAgentVO campaignTemplateWebAgentVO) throws Exception{
		return sqlSessionEms.getMapper(CampaignMapper.class).insertCampaignTemplateWebAgent(campaignTemplateWebAgentVO);
	}
	
	@Override
	public int insertCampaignTemplateAttachInfo(CampaignTemplateAttachVO campaignTemplateAttachVO) throws Exception{
		return sqlSessionEms.getMapper(CampaignMapper.class).insertCampaignTemplateAttachInfo(campaignTemplateAttachVO);
	}
	
	@Override
	public int updateCampaignTemplateStatus(CampaignTemplateVO campaignTemplateVO) throws Exception{
		return sqlSessionEms.getMapper(CampaignMapper.class).updateCampaignTemplateStatus(campaignTemplateVO);
	}

	public int updateCampaignTemplateWorkStatus(CampaignTemplateVO campaignTemplateVO) throws Exception{
		return sqlSessionEms.getMapper(CampaignMapper.class).updateCampaignTemplateWorkStatus(campaignTemplateVO);
	}
	
	@Override
	public int insertCampaignTemplateProhibitWord(CampaignTemplateProhibitWordVO campaignTemplateProhibitWordVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertCampaignTemplateProhibitWord(campaignTemplateProhibitWordVO);
	}

	@Override
	public int deleteCampaignTemplateAttachInfo(int tid) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).deleteCampaignTemplateAttachInfo(tid);
	}
	
	@Override
	public int deleteCampaignTemplateWebAgent(int tid) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).deleteCampaignTemplateWebAgent(tid);
	}
	
	@Override
	public int deleteCampaignTemplateProhibitWord(int tid) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).deleteCampaignTemplateProhibitWord(tid);
	}
	
	@Override
	public List<TaskVO> getMailList(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailList(taskVO);
	}

	@Override
	public int updateTaskStatusAdmit(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateTaskStatusAdmit(taskVO);
	}

	@Override
	public int updateSubTaskStatusAdmit(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateSubTaskStatusAdmit(taskVO);
	}

	@Override
	public int insertTaskInfo(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertTaskInfo(taskVO);
	}

	@Override
	public int getTaskNo() throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getTaskNo();
	}

	@Override
	public int getSubTaskNo(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getSubTaskNo(taskNo);
	}

	@Override
	public int insertSubTaskInfo(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertSubTaskInfo(taskVO);
	}

	@Override
	public int insertAttachInfo(AttachVO attachVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertAttachInfo(attachVO);
	}

	@Override
	public int insertLinkInfo(LinkVO linkVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertLinkInfo(linkVO);
	}

	@Override
	public int updateTaskStatus(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateTaskStatus(taskVO);
	}

	@Override
	public int updateSubTaskStatus(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateSubTaskStatus(taskVO);
	}

	@Override
	public TaskVO getTaskInfo(int seq) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getTaskInfo(seq);
	}

	@Override
	public TaskVO getSubTaskInfo(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getSubTaskInfo(taskVO);
	}

	@Override
	public int insertTaskInfoForCopy(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertTaskInfoForCopy(taskVO);
	}

	@Override
	public int insertSubTaskInfoForCopy(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertSubTaskInfoForCopy(taskVO);
	}

	@Override
	public List<AttachVO> getAttachList(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getAttachList(taskNo);
	}

	@Override
	public List<TestUserVO> getTestUserList(String userId) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getTestUserList(userId);
	}

	@Override
	public int insertTestUserInfo(TestUserVO testUserVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertTestUserInfo(testUserVO);
	}

	@Override
	public int updateTestUserInfo(TestUserVO testUserVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateTestUserInfo(testUserVO);
	}

	@Override
	public int deleteTestUserInfo(TestUserVO testUserVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).deleteTestUserInfo(testUserVO);
	}

	@Override
	public int insertTaskInfoForTestSend(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertTaskInfoForTestSend(taskVO);
	}

	@Override
	public int insertSubTaskInfoForTestSend(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertSubTaskInfoForTestSend(taskVO);
	}
	
	@Override
	public int insertTaskInfoForReSend(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertTaskInfoForReSend(taskVO);
	}	
	
	@Override
	public int insertSubTaskInfoForReSend(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertSubTaskInfoForReSend(taskVO);
	}
	
	@Override
	public TaskVO getMailInfo(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailInfo(taskVO);
	}

	@Override
	public int updateTaskInfo(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateTaskInfo(taskVO);
	}

	@Override
	public int updateSubTaskInfo(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateSubTaskInfo(taskVO);
	}

	@Override
	public int updateTaskInfoDate(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateTaskInfoDate(taskVO);
	}
	

	@Override
	public int updateSubTaskInfoDate(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateSubTaskInfoDate(taskVO);
	}
	
	@Override
	public int deleteAttachInfo(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).deleteAttachInfo(taskNo);
	}

	@Override
	public List<TaskVO> getMailTestTaskList(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailTestTaskList(taskVO);
	}

	@Override
	public List<SendTestLogVO> getMailTestSendLogList(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailTestSendLogList(taskVO);
	}

	@Override
	public List<TaskVO> getCampMailList(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getCampMailList(taskVO);
	}

	@Override
	public List<TaskVO> getMailListUnion(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailListUnion(taskVO);
	}

	@Override
	public List<TaskVO> getMailListOnetime(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailListOnetime(taskVO);
	}

	@Override
	public List<ApprovalOrgVO> getOrgListLvl1() throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getOrgListLvl1();
	}

	@Override
	public List<ApprovalOrgVO> getOrgListChild(String upOrgCd) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getOrgListChild(upOrgCd);
	}

	@Override
	public List<UserVO> getUserListOrg(ApprovalOrgVO approvalOrgVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getUserListOrg(approvalOrgVO);
	}

	@Override
	public List<UserVO> getUserListSearch(UserVO userVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getUserListSearch(userVO);
	}

	@Override
	public WebAgentVO getWebAgentInfo(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getWebAgentInfo(taskNo);
	}

	@Override
	public int insertWebAgent(WebAgentVO webAgentVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertWebAgent(webAgentVO);
	}

	@Override
	public int updateWebAgent(WebAgentVO webAgentVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateWebAgent(webAgentVO);
	}

	@Override
	public int deleteWebAgent(WebAgentVO webAgentVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).deleteWebAgent(webAgentVO);
	}

	@Override
	public int insertEmailClinic(EmailClinicVO emailClinicVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertEmailClinic(emailClinicVO);
	}

	@Override
	public int updateEmailClinic(EmailClinicVO emailClinicVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateEmailClinic(emailClinicVO);
	}

	@Override
	public int deleteEmailClinic(EmailClinicVO emailClinicVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).deleteEmailClinic(emailClinicVO);
	}
	
	@Override
	public List<ProhibitWordVO> getProhibitWordList(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getProhibitWordList(taskNo);
	}

	@Override
	public int insertProhibitWord(ProhibitWordVO prohibitWordVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertProhibitWord(prohibitWordVO);
	}
	
	@Override
	public int deleteProhibitWord(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).deleteProhibitWord(taskNo);
	}
	
	@Override
	public List<SecuApprovalLineVO> getApprovalLineList(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getApprovalLineList(taskVO);
	}

	@Override
	public int insertApprovalLine(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertApprovalLine(approvalLineVO);
	}

	@Override
	public int updateApprovalLine(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateApprovalLine(approvalLineVO);
	}

	@Override
	public int deleteApprovalLine(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).deleteApprovalLine(taskNo);
	}

	@Override
	public EmailClinicVO getEmailClinicInfo(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getEmailClinicInfo(taskNo);
	}

	@Override
	public List<TaskVO> getMailListRepeat(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailListRepeat(taskVO);
	}

	@Override
	public int updateSubmitApproval(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateSubmitApproval(taskVO);
	}

	@Override
	public List<TaskVO> getMailTestList(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailTestList(taskVO);
	}

	@Override
	public int updateSubmitApprovalLine(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateSubmitApprovalLine(taskVO);
	}

	@Override
	public List<TaskVO> getSubTaskList(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getSubTaskList(taskVO);
	}

	@Override
	public List<SendLogVO> getMailTestResultList(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailTestResultList(taskVO);
	}

	@Override
	public TaskVO getMailPeriod(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailPeriod(taskNo);
	}
	
	@Override
	public MailMktChkVO getMailMktChkInfo(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailMktChkInfo(taskNo);
	}

	@Override
	public int insertMailMktChkInfo(MailMktChkVO mailMktChkVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).insertMailMktChkInfo(mailMktChkVO);
	}

	@Override
	public int updateMailMktChkInfo(MailMktChkVO mailMktChkVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).updateMailMktChkInfo(mailMktChkVO);
	}

	@Override
	public int deleteMailMktChkInfo(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).deleteMailMktChkInfo(taskNo);
	}

	@Override
	public String getFirstApprUserId(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getFirstApprUserId(taskNo);
	}
	
	@Override
	public List<TaskVO> getMailSendHist(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailSendHist(taskVO);
	}
	
	@Override
	public List<TaskVO> getMailWorkStatus(String requestKey) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getMailWorkStatus(requestKey);
	}
	
	@Override
	public int getCountRequestKey(String requestKey) throws Exception {
		return sqlSessionEms.getMapper(CampaignMapper.class).getCountRequestKey(requestKey);
	}
}
