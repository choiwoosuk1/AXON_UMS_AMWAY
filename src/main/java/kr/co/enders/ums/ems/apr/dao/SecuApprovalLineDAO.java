/**
 * 작성자 : 김준희
 * 작성일시 : 2021.08.23
 * 설명 : 메일발송결재 데이터 처리
 */
package kr.co.enders.ums.ems.apr.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.enders.ums.ems.apr.vo.SecuApprovalLineVO;
import kr.co.enders.ums.ems.cam.vo.AttachVO;
import kr.co.enders.ums.ems.cam.vo.TaskVO;

@Repository
public class SecuApprovalLineDAO implements SecuApprovalLineMapper {
	@Autowired
	private SqlSession sqlSessionEms;

	@Override
	public List<SecuApprovalLineVO> getApprovalLineList(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).getApprovalLineList(approvalLineVO);
	}

	@Override
	public List<SecuApprovalLineVO> getApprovalLineEmsList(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).getApprovalLineEmsList(approvalLineVO);
	}
	
	@Override
	public List<SecuApprovalLineVO> getApprovalLineRnsList(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).getApprovalLineRnsList(approvalLineVO);
	}
	
	
	@Override
	public SecuApprovalLineVO getMailInfo(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).getMailInfo(approvalLineVO);
	}

	@Override
	public List<AttachVO> getAttachList(int taskNo) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).getAttachList(taskNo);
	}

	@Override
	public List<SecuApprovalLineVO> getMailApprLineList(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).getMailApprLineList(approvalLineVO);
	}

	@Override
	public int updateMailAprStep(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).updateMailAprStep(approvalLineVO);
	}

	@Override
	public int updateMailAprStepNext(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).updateMailAprStepNext(approvalLineVO);
	}

	@Override
	public int updateTaskStatusAdmit(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).updateTaskStatusAdmit(taskVO);
	}

	@Override
	public int updateSubTaskStatusAdmit(TaskVO taskVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).updateSubTaskStatusAdmit(taskVO);
	}

	@Override
	public int getApprCount(String userId) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).getApprCount(userId);
	}

	@Override
	public String getRejectNm(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).getRejectNm(approvalLineVO);
	}

	
	@Override
	public List<SecuApprovalLineVO> getRnsProhibitInfo(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).getRnsProhibitInfo(approvalLineVO);
	}
	@Override
	public List<SecuApprovalLineVO> getProhibitList(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).getProhibitList(approvalLineVO);
	}
	@Override
	public List<SecuApprovalLineVO> getMailProhibitInfo(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).getMailProhibitInfo(approvalLineVO);
	}
	
	@Override
	public List<SecuApprovalLineVO> nextMailAprStep(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).nextMailAprStep(approvalLineVO);
	}
	
	@Override
	public List<SecuApprovalLineVO> nowMailAprStep(SecuApprovalLineVO approvalLineVO) throws Exception {
		return sqlSessionEms.getMapper(SecuApprovalLineMapper.class).nowMailAprStep(approvalLineVO);
	}
	
}
