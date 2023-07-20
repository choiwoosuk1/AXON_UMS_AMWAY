<%--
	/**********************************************************
	*	작성자 : 김준희
	*	작성일시 : 2022.03.20
	*	설명 : 카카오템플릿 등록 처리 후 화면
	**********************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<script type="text/javascript" src="<c:url value='/js/jquery_2.1.1.min.js'/>"></script>
<script type="text/javascript">
<c:choose>
	<c:when test="${result eq 'Success'}">
		alert("등록되었습니다.");
		$("#searchForm", parent.document).attr("target","").attr("action","./kakaoTemplateListP.ums").submit();
		//window.parent.location.href = "<c:url value='/sys/seg/kakaoTemplateListP.ums'/>";
	</c:when>
	<c:when test="${result eq 'Exist'}">
		alert("이미 등록되어 있는 템플릿코드입니다");
	</c:when>
	<c:when test="${result eq 'filter'}">
		alert("사용 할수 없는 스크립트가 포함되어 있습니다");
	</c:when>
	<c:otherwise>
		<c:if test="${FILE_SIZE eq 'EXCESS' }">
			alert("파일 크기가 제한용량을 초과하였습니다.");
		</c:if>
		<c:if test="${FILE_SIZE ne 'EXCESS' }">
			alert("등록 처리중 오류가 발생하였습니다.");
		</c:if>
	</c:otherwise>
</c:choose>
</script>
