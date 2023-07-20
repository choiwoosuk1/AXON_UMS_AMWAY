<%--
	/**********************************************************
	*	작성자 : 김준희
	*	작성일시 : 2022.02.07
	*	설명 :준법심의 결과 내부
	**********************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp" %>

<!-- 팝업// -->
<div class="cont">
	<!-- 심의 조건// -->
	<input type="hidden" id="popProhibitTitleCnt"  value="<c:out value='${prohibitTitleCnt}'/>">
	<input type="hidden" id="popProhibitTitleDesc" value="<c:out value='${prohibitTitleDesc}'/>">
	<input type="hidden" id="popProhibitTextCnt"   value="<c:out value='${prohibitTextCnt}'/>">
	<input type="hidden" id="popProhibitTextDesc"  value="<c:out value='${prohibitTextDesc}'/>">
	<div class="graybox">
		<div class="grid-area">
			<table class="grid">
				<caption>그리드 정보</caption>
				<colgroup>
					<col style="width:120px;">
					<col style="width:208px;">
					<col style="width:120px;">
					<col style="width:auto;">
				</colgroup>
				<tbody>
					<tr>
						<th scope="col">첨부 파일</th>
						<td  id="popProhibitAttach"><c:out value='${attachNm}'/></td>
						<th scope="col">이미지 포함</th>
						<td id="popProhibitImage"><c:out value='${imgChkYn}'/></td>
					</tr>
					<!-- 
					<tr>
						<th scope="col">마케팅 동의유형</th>
						<td id="popProhibitMarket"><c:out value='${mailMktNm}'/></td>
						
					</tr>
					 -->
				</tbody>
			</table>
		</div>
	</div>
	<!-- //심의 조건 -->
	
	<!-- 금지어// -->
	<h3 class="pop-title">금칙어</h3>
	
	<div class="graybox-wrap clear">
		<!-- 제목// -->
		<div class="graybox">
			<div class="title-area">
				<h3 class="h3-title"><c:out value='${titleDesc}'/></h3>
			</div>
			<div class="grid-area">
				<table class="grid">
					<caption>그리드 정보</caption>
					<tbody>
						<c:if test="${not empty titleList}">
								<c:forEach items="${titleList}" var="title">
								<tr><td><c:out value='${title}'/></td></tr> 
								</c:forEach>
						</c:if>
						<c:if test="${empty titleList}">
							<!-- 데이터가 없을 경우// -->
							<tr>
								<td class="no_data">등록된 내용이 없습니다.</td>
							</tr>
							<!-- //데이터가 없을 경우 -->
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<!-- //제목 -->
	
		<!-- 본문// -->
		<div class="graybox">
			<div class="title-area">
				<h3 class="h3-title"><c:out value='${textDesc}'/></h3>
			</div>
			<div class="grid-area">
				<table class="grid">
					<caption>그리드 정보</caption>
					<tbody>
						<c:if test="${not empty textList}">
							<c:forEach items="${textList}" var="text">
							<tr><td><c:out value='${text}'/></td></tr> 
							</c:forEach>
						</c:if>
						<c:if test="${empty textList}">
							<!-- 데이터가 없을 경우// -->
							<tr>
								<td class="no_data">등록된 내용이 없습니다.</td>
							</tr>
							<!-- //데이터가 없을 경우 -->
						</c:if>						
					</tbody>
				</table>
			</div>
		</div>
		<!-- //본문 -->
	</div>
	<!-- //금지어 -->
	
	<!-- 경고문구// -->
	<p id="popProhibitDesc" class="caution"><!-- 3종의 문구 중 심의 결과에 맞는 문구가 노출됩니다. -->
		* 금칙어 정보가 발생하였습니다.<br>
		* 메일 내용에 금칙어 포함된 경우 취소 후 수정 바랍니다.<br> 
	</p>
	<!-- //경고문구 -->
	
	<!-- btn-wrap// -->
	<div class="btn-wrap">
		<button type="button" class="btn big fullblue" onclick="popProhibitSave();">확정</button>
		<button type="button" class="btn big" onclick="popProhibitCancel();">취소</button>
	</div>
	<!-- //btn-wrap -->
	</div>
 