<%--
	/**********************************************************
	*	작성자 : 김준희
	*	작성일시 : 2022.03.21
	*	설명 : SMS발송 내부 캠페인 목록
	**********************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp" %>

<div class="cont">
	<form id="popCampSearchForm" name="popCampSearchForm" method="post">
		<input type="hidden" name="page" value="${searchVO.page}">
		<input type="hidden" name="campNo" value="0">
		<fieldset>
			<legend>캠페인 선택</legend>

			<!-- 검색// -->
			<div class="graybox" style="margin-top:0;">
				<div class="title-area">
					<h3 class="h3-title">검색</h3>
				</div>
				
				<div class="list-area type-short">
					<ul>
						<li>
							<label>등록일시</label>
							<div class="list-item">
								<!-- datepickerrange// -->
								<div class="datepickerrange fromDate">
									<label>
										<fmt:parseDate var="startDt" value="${searchVO.searchStartDt}" pattern="yyyyMMdd"/>
										<fmt:formatDate var="searchStartDt" value="${startDt}" pattern="yyyy.MM.dd"/> 
										<input type="text" name="searchStartDt" value="<c:out value='${searchStartDt}'/>" readonly>
									</label>
								</div>
								<span class="hyppen date"></span>
								<div class="datepickerrange toDate">
									<label>
										<fmt:parseDate var="endDt" value="${searchVO.searchEndDt}" pattern="yyyyMMdd"/>
										<fmt:formatDate var="searchEndDt" value="${endDt}" pattern="yyyy.MM.dd"/> 
										<input type="text" name="searchEndDt" value="<c:out value='${searchEndDt}'/>" readonly>
									</label>
								</div>
								<!-- //datepickerrange -->
							</div>
						</li>
						<li>
							<label>캠페인명</label>
							<div class="list-item">
								<input type="text" name="searchCampNm" value="<c:out value='${searchVO.searchCampNm}'/>" placeholder="캠페인명을 입력해주세요.">
							</div>
						</li>
					</ul>

				</div>
			</div>
			<!-- //검색 -->

			<!-- btn-wrap// -->
			<div class="btn-wrap mgt10 tar">
				<button type="button" class="btn fullgreen" onclick="goPopCampSearch('1');">검색</button>
			</div>
			<!-- //btn-wrap -->

			<!-- 목록// -->
			<div class="graybox">
				<div class="title-area">
					<h3 class="h3-title">목록</h3>
					<span class="total">Total: <em><c:out value="${pageUtil.totalRow}"/></em></span>

					<!-- 버튼// -->
					<div class="btn-wrap">
						<button type="button" class="btn fullgreen plus" onclick="goPopCampAdd();">신규등록</button>
					</div>
					<!-- //버튼 -->
				</div>

				<div class="grid-area">
					<table class="grid">
						<caption>그리드 정보</caption>
						<colgroup>
							<col style="width:5%;">
							<col style="width:28%;">
							<col style="width:10%;">
							<col style="width:8%;">
							<!-- <col style="width:8%;"> -->
						</colgroup>
						<thead>
							<tr>
								<th scope="col">NO</th>
								<th scope="col">캠페인명</th>
								<th scope="col">캠페인목적</th>
								<th scope="col">상태</th>
								<!-- <th scope="col">수정</th> -->
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(campList) > 0}">
								<!-- 데이터가 있을 경우// -->
								<c:forEach items="${campList}" var="camp" varStatus="campStatus">
									<tr>
										<td><c:out value="${pageUtil.totalRow - (pageUtil.currPage-1)*pageUtil.pageRow - campStatus.index}"/></td>
										<td>
											<c:if test="${'000' eq camp.status}">
												<a href="javascript:setPopCampInfo('<c:out value="${camp.campNo}"/>','<c:out value="${camp.campNm}"/>','<c:out value="${camp.campTy}"/>');" class="bold"><c:out value="${camp.campNm}"/></a>
											</c:if>
										</td>
										<td><c:out value="${camp.campTyNm}"/></td>
										<td><c:out value="${camp.statusNm}"/></td>
										<%-- <td><a href="javascript:goPopCampUpdate('<c:out value="${camp.campNo}"/>');" class="medium">수정</a></td> --%>
									</tr>
								</c:forEach>
								<!-- //데이터가 있을 경우 -->
							</c:if>
							<c:if test="${empty campList}">
								<!-- 데이터가 없을 경우// -->
								<tr>
									<td colspan="4" class="no_data">등록된 내용이 없습니다.</td>
								</tr>
								<!-- //데이터가 없을 경우 -->
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<!-- //목록 -->
		
			<!-- 페이징// -->
			<div class="paging">
				${pageUtil.pageHtml}
			</div>
			<!-- //페이징 -->

		</fieldset>
	</form>
</div>
<script type="text/javascript">
	//시작일
	$('.datepickerrange.fromDate input').datepicker({
		dateFormat: 'yy.mm.dd', //날짜 포맷이다.
		closeText: '닫기', // 닫기 버튼 텍스트 변경
		currentText: '오늘', // 오늘 텍스트 변경
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],	//한글 캘린더중 월 표시를 위한 부분
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],	//한글 캘린더 중 월 표시를 위한 부분
		dayNames: ['일', '월', '화', '수', '목', '금', '토'],	//한글 캘린더 요일 표시 부분
		dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],	//한글 요일 표시 부분
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],	// 한글 요일 표시 부분
		showMonthAfterYear: true,	// true : 년 월	false : 월 년 순으로 보여줌
		yearSuffix: '년',
		showButtonPanel: true,	// 오늘로 가는 버튼과 달력 닫기 버튼 보기 옵션

		onClose: function( selectedDate ) {    
			// 시작일(fromDate) datepicker가 닫힐때
			// 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
			$(".datepickerrange.toDate input").datepicker( "option", "minDate", selectedDate );
		}  
	});

	//종료일
	$('.datepickerrange.toDate input').datepicker({
		dateFormat: 'yy.mm.dd', //날짜 포맷이다.
		closeText: '닫기', // 닫기 버튼 텍스트 변경
		currentText: '오늘', // 오늘 텍스트 변경
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],	//한글 캘린더중 월 표시를 위한 부분
		monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],	//한글 캘린더 중 월 표시를 위한 부분
		dayNames: ['일', '월', '화', '수', '목', '금', '토'],	//한글 캘린더 요일 표시 부분
		dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],	//한글 요일 표시 부분
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],	// 한글 요일 표시 부분
		showMonthAfterYear: true,	// true : 년 월	false : 월 년 순으로 보여줌
		yearSuffix: '년',
		showButtonPanel: true,	// 오늘로 가는 버튼과 달력 닫기 버튼 보기 옵션

		onClose: function( selectedDate ) {
			// 종료일(toDate) datepicker가 닫힐때
			// 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
			$(".datepickerrange.fromDate input").datepicker( "option", "maxDate", selectedDate );
		}                
	});
</script>
