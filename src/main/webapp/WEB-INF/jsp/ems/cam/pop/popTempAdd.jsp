<%--
	/**********************************************************
	*	작성자 : 김준희
	*	작성일시 : 2021.10.14
	*	설명 : 메일작성 내부 템플릿 목록
	**********************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/taglib.jsp" %>

<div class="cont">
	<form id="popTempSearchForm" name="popTempSearchForm" method="post">
		<input type="hidden" name="page" value="${searchVO.page}">
		<input type="hidden" name="searchStartDt" value="${searchVO.searchStartDt}">
		<input type="hidden" name="searchEndDt" value="${searchVO.searchEndDt}">
		<input type="hidden" name="searchTempNm" value="${searchVO.searchTempNm}">
	</form>
	<form id="popTempInfoForm" name="popTempInfoForm" method="post">
		<input type="hidden" name="deptNo" value="<c:out value='${NEO_DEPT_NO}'/>">
		<input type="hidden" name="userId" value="<c:out value='${NEO_USER_ID}'/>">
		<input type="hidden" name="status" value="000">
		<input type="hidden" name="channel" value="000">
		<input type="hidden" name="tempVal" value="">
		<fieldset>
			<legend>템플릿 신규등록</legend>
			<h3 class="pop-title">템플릿 신규등록</h3>

			<!-- 조건// -->
			<div class="graybox">
				<div class="title-area">
					<h3 class="h3-title">조건</h3>
					<span class="required">*필수입력 항목</span>
				</div>
				
				<div class="list-area">
					<ul>
						<li class="col-full">
							<label class="required">템플릿명</label>
							<div class="list-item">
								<input type="text" name="tempNm" placeholder="템플릿명을 입력해주세요.">
							</div>
						</li>
						<li class="col-full">
							<label>설명</label>
							<div class="list-item">
								<textarea name="tempDesc" placeholder="템플릿 설명을 입력해주세요."></textarea>
							</div>
						</li>
						<li class="col-full">
							<div class="list-item clear">
								<button type="button" class="btn fr" onclick="popUploadHtml('temp');">HTML등록</button>
							</div>
						</li>
						<li class="col-full">
							<!-- 에디터 영역// -->
							<div class="editbox">
								<textarea name="ir2" id="ir2" rows="10" cols="100" style="text-align: center; width: 100%; height: 280px; display: none; border: none;" ></textarea>
								<script type="text/javascript">
								var oEditors2 = [];
					
								// 추가 글꼴 목록
								//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
					
								nhn.husky.EZCreator.createInIFrame({
									oAppRef: oEditors2,
									elPlaceHolder: "ir2",
									sSkinURI: "<c:url value='/smarteditor/SmartEditor2Skin.html'/>",	
									htParams : {
										bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
										bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
										bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
										//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
										fOnBeforeUnload : function(){
										//alert("완료!");
									}
								}, //boolean
								fOnAppLoad : function(){
									//예제 코드
									//oEditors2.getById["ir2"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
									//body_loaded();
								},
								fCreator: "createSEditor2"
								});
					
								function pasteHTML(obj) {
									var sHTML = "<img src='<c:out value='${DEFAULT_DOMAIN}'/>/img/upload/"+obj+"'>";
									oEditors2.getById["ir2"].exec("PASTE_HTML", [sHTML]);
								}
					
								function showHTML() {
									var sHTML = oEditors2.getById["ir2"].getIR();
									alert(sHTML);
								}
					
								function submitContents(elClickedObj) {
									oEditors2.getById["ir2"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
					
									// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
					
									try {
										elClickedObj.form.submit();
									} catch(e) {}
									}
					
								function setDefaultFont() {
									var sDefaultFont = '궁서';
									var nFontSize = 24;
									oEditors2.getById["ir2"].setDefaultFont(sDefaultFont, nFontSize);
								}
								</script>
							</div>
							<!-- //에디터 영역 -->
						</li>
					</ul>

				</div>
			</div>
			<!-- //조건 -->
			<!-- btn-wrap// -->
			<div class="btn-wrap">
				<button type="button" class="btn big fullblue" onclick="goPopTempInfoAdd();">등록</button>
				<button type="button" class="btn big" onclick="goPopTempList();">목록</button>
			</div>
			<!-- //btn-wrap -->

		</fieldset>
	</form>
</div>
