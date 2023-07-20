/**********************************************************
*	작성자 : 이혜민
*	작성일시 : 2022.06.23
*	설명 : SMS 템플릿 신규등록 JavaScript
**********************************************************/
$(document).ready(function() {

	$("#tempSubject").on("keyup", function() {
		checkMergeFunc();
	});

	$("#tempContent").on("keyup", function() {
		checkMergeFunc();
	});

});
// 등록 클릭시
function goAdd() {
	if(checkForm() == true) return;
	$("#tempCd").attr("disabled",false);
	var mergeItem = $("#mergeItem").text()
	$("#smsTemplateInfoForm input[name='mergeItem']").val(mergeItem);
	$("#smsTemplateInfoForm").attr("target","iFrmMail").attr("action","./smsTemplateAdd.ums").submit();
	$("#tempCd").attr("disabled",true);
}

// 목록 클릭시(리스트로 이동)
function goCancel() {
	$("#smsTemplateInfoForm").attr("target","").attr("action","./smsTemplateListP.ums").submit();
}

// SMS 템플릿 코드 중복확인 
function checkSmsTempCd() {	
 
	if($("#tempCd").val() == "") {
		alert("템플릿 코드를 입력해주세요");
		return;
	} 
	
	var tempCd= $("#tempCd").val();
	
	$.getJSON("./checkSmsTempCd.json?tempCd=" + tempCd, function(data) {	
		if(data.result == "Success") {
			alert("등록 가능한 템플릿 코드입니다.");	 
			$("#checkTempCd").val("Y");
			$("#checkTempCd").text("확인");
			$("#tempCd").attr("disabled",true);
			$("#checkTempCd").attr("disabled",true);			
		} else if(data.result == "Fail") {
			alert("사용중인 템플릿 코드입니다. 코드를 확인해주세요");
		}
	}); 
}

// 입력 폼 검사
function checkForm() {
	var tempContent = $("#tempContent").val();
	var tempCd = $("#tempCd").val();
	var tempNm = $("#tempNm").val();
	var regex = /^[a-z|A-Z|0-9|_|-]+$/;
	var result = null;

	if($.trim($("#deptNo").val()) == "0") {
		alert("사용자그룹은 필수입력 항목입니다.");
		return true;
	}
	if($.trim($("#campNo").val()) == "0") {
		alert("캠페인은 필수입력 항목입니다.");
		return true;
	}
	if($.trim($("#segNoc").val()) == "") {
		alert("수신자그룹은 필수입력 항목입니다.");
		return true;
	}
	
	if($.trim($("#userId").val()) == "") {
		alert("사용자는 필수입력 항목입니다.");
		return true;
	}
	
	if($.trim(tempCd) == "") {
		alert("템플릿 코드는 필수입력 항목입니다.");
		$("#tempCd").focus();
		return true;
	}
	
	if(!(regex.test(tempCd))) {
		alert("템플릿 코드는 숫자와 영문, 언더바만 가능합니다.");
		return true;
	}
	
	if($("#checkTempCd").val() == "N") {
		alert("템플릿 코드 중복확인을 해주세요.");	
		return true;
	}
	
	if($.trim(tempNm) == "") {
		alert("템플릿명은 필수입력 항목입니다.");
		$("#tempNm").focus();
		return true;
	}
	
	if($.byteString(tempNm) > 128) {
		alert("템플릿명은 128byte를 넘을 수 없습니다.");
		$("#tempNm").focus();
		return true;
	}
	
	if($.trim($("#tempSubject").val()) == "") {
		alert("제목은 필수입력 항목입니다.");
		return true;
	}
	 
	if($.trim(tempContent) == "") {
		alert("템플릿 내용은 필수입력 항목입니다.");
		$("#tempContent").focus();
		return true;

	}else{
		var messageByte = byteCheck($.trim(tempContent));
		setSmsGubun(messageByte);
		
	}
	
	if($.byteString(tempContent) > 2000) {
		alert("템플릿 내용은 2000byte를 넘을 수 없습니다.");
		$("#tempNm").focus();
		return true;
	}
	
	return result;
}

function setSmsGubun(messageByte) {
	if (messageByte > 2000) {
		alert("문자 길이는 2000Byte를 넘을수 없습니다");
		return;
	} else {
		if (messageByte > 80) {
			$("#gubun").val("001");
		} else {
			$("#gubun").val("000");
		}
	}
}

function byteCheck(smsMessage){
	var codeByte = 0;
	for (var idx = 0; idx < smsMessage.length; idx++) {
		var oneChar = escape(smsMessage.charAt(idx));
		if (oneChar.length == 1) {
			codeByte++;
		} else if (oneChar.indexOf("%u") != -1) {
			codeByte += 2;
		} else if (oneChar.indexOf("%") != -1) {
			codeByte++;
		}
	}
	return codeByte;
}

// SMS 템플릿 미리보기
function goSmsTemplatePreview() {

	// 미리보기 관련 설정 초기화
	var initHtml = "";
	var tempSub  = $("#tempSubject").val(); // 템플릿 제목
	var tempContent  = $("#tempContent").val(); // 템플릿 내용
		
	initHtml += "<div class='messageboxT'>";
	initHtml += "<textarea class='message' readonly>" + tempContent + "</textarea>";
	initHtml += "</div>";
	
	$("#popupTemplateNm").html(tempSub);
	$("#previewContent").html(initHtml);
	 
	fn.popupOpen('#popup_sms_previewtemplate');
}

// 사용자그룹 선택시 사용자 목록 조회
function getUserList(deptNo) {
	$.getJSON("../../com/getUserList.json?deptNo=" + deptNo, function(data) {
		$("#userId").children("option:not(:first)").remove();
		$.each(data.userList, function(idx,item){
			var option = new Option(item.userNm,item.userId);
			$("#userId").append(option);
		});
	});
}
// 캠페인을 선택하였을 때 발생하는 이벤트
function goCamp() {
	if($("#campInfo").val() == "") {
		$("#campNo").val("");
		$("#campTy").val("");
	} else {
		var tmp = $("#campInfo").val();
		var campNo = tmp.substring(0, tmp.indexOf("|"));
		tmp = tmp.substring(tmp.indexOf("|") + 1);
		var campTy = tmp.substring(0, tmp.indexOf("|"));

		$("#campNo").val( campNo );
		$("#campTy").val( campTy );
	}
}

// 수신자그룹을 선택하였을 경우 머지부분 처리
function goMerge() {
	if($("#segNoc").val() == "") {
		alert("수신자그룹을 선택하세요.");
		return;
	}
	
	// 머지입력 초기화
	$("#mergeKeySms").children().remove();
	
	var condHTML = "";
	var merge = $("#segNoc").val().substring($("#segNoc").val().indexOf("|")+1);
	var pos = merge.indexOf(",");
	while(pos != -1) {
		condHTML = "<option value='$:"+merge.substring(0,pos)+":$'>"+merge.substring(0,pos)+"</option>";
		$("#mergeKeySms").append(condHTML);
		merge = merge.substring(pos+1);
		pos = merge.indexOf(",");
	}
	condHTML = "<option value='$:"+merge+":$'>"+merge+"</option>";
	$("#mergeKeySms").append(condHTML);
}


// 제목 클릭시(제목 내용 추가)
function goTitleMerge() {
	if($("#segNoc").val() == "") {
		alert("수신자그룹을 선택하세요.");
		return;
	}
	if($("#tempSubject").val() == "") {
		$("#tempSubject").val( $("#mergeKeySms").val() );
	} else {
		var cursorPosition = $("#tempSubject")[0].selectionStart;
		$("#tempSubject").val( $("#tempSubject").val().substring(0,cursorPosition) + $("#mergeKeySms").val() + $("#tempSubject").val().substring(cursorPosition) );
	}
}

//본문 클릭시(본문 내용 추가)
function goContMerge() {
	if($("#segNoc").val() == "") {
		alert("수신자그룹을 선택하세요.");
		return;
	}
	addSmsMessage($("#mergeKeySms").val(), 2);
}
function addSmsMessage(addMessage, pos) {
	var element = document.getElementById("tempContent");
	var strOriginal = element.value;
	var iStartPos = element.selectionStart;
	var iEndPos = element.selectionEnd;
	if(pos == 0 ) { //(광고) 클릭시 넣기 
		iStartPos = pos;
		iEndPos = pos;
	} else if (pos == 1) { //(광고) 클릭시 수신거부 내역 		
		iStartPos = element.value.length;
		iEndPos = iStartPos; 
	}  
	var strFront = "";
	var strEnd = "";

	if(iStartPos == iEndPos) {
		strFront = strOriginal.substring(0, iStartPos);
		strEnd = strOriginal.substring(iStartPos, strOriginal.length);
	} else return;
		
	element.value = strFront + addMessage + strEnd;
}

function addSmsMessage(addMessage, pos) {
	var element = document.getElementById("tempContent");
	var strOriginal = element.value;
	var iStartPos = element.selectionStart;
	var iEndPos = element.selectionEnd;
	if(pos == 0 ) { //(광고) 클릭시 넣기 
		iStartPos = pos;
		iEndPos = pos;
	} else if (pos == 1) { //(광고) 클릭시 수신거부 내역 		
		iStartPos = element.value.length;
		iEndPos = iStartPos; 
	}  
	var strFront = "";
	var strEnd = "";

	if(iStartPos == iEndPos) {
		strFront = strOriginal.substring(0, iStartPos);
		strEnd = strOriginal.substring(iStartPos, strOriginal.length);
	} else return;
		
	element.value = strFront + addMessage + strEnd;
}

//머지 함수 및 글자수 체크
function checkMergeFunc() {
	var regExp = /[^$:][a-zA-Z]+(?=[:][$])/g;
	let msgRegResult =  $("#tempContent").val().match(regExp);
	let subRegResult =  $("#tempSubject").val().match(regExp);
	let mergeResult = new Array();
	let mergeList = $("#segNoc").val().substring($("#segNoc").val().indexOf("|")+1);
	var mergeItems = "";
	
	if(msgRegResult != null ){
		if(subRegResult != null ){ //둘다 null 이 아닐 때
			msgRegResult.push.apply(msgRegResult,subRegResult);	
			mergeResult = removeDuplicatesArray(msgRegResult);
		}else{ // 제목이 null 일때
			mergeResult = removeDuplicatesArray(msgRegResult);
		}
	}else{
		if(subRegResult != null ){ // 본문은 null 제목은 null X
			mergeResult = removeDuplicatesArray(subRegResult);
		}else{ // 둘다 null
		}
	}
	for(var i=0; i < mergeResult.length; i++) {
		var mergeItem = mergeResult[i];
		
		if(mergeList.indexOf(mergeItem) > -1){
			mergeItems += "$:" + mergeItem + ":$,";
		}
	}
	
	mergeItems = mergeItems.substring(0, mergeItems.length-1);
	$("#mergeItem").text(mergeItems);
}

function removeDuplicatesArray(arr) {
	var tempArr = [];
	for (var i = 0; i < arr.length; i++) {
		if (tempArr.length == 0) {
			tempArr.push(arr[i]);
		} else {
			var duplicatesFlag = true;
			for (var j = 0; j < tempArr.length; j++) {
				if (tempArr[j] == arr[i]) {
					duplicatesFlag = false;
					break;
				}
			}
			if (duplicatesFlag) {
				tempArr.push(arr[i]);
			}
		}
	}
	return tempArr;
}

/***************************************************************************************************
* 2022.04.10 추가
* 팝업작업 처리
***************************************************************************************************/
// 캠페인명 선택 클릭시(캠페인선택 팝업)
function popCampSelect() {
	$("#popCampSearchForm input[name='searchStartDt']").val("");
	$("#popCampSearchForm input[name='searchEndDt']").val("");
	$("#popCampSearchForm input[name='searchCampNm']").val("");
	goPopCampSearch("1");
	fn.popupOpen("#popup_smsTemplate_campaign");
}

// 팝업 캠페인 목록 검색
function goPopCampSearch(pageNum) {
	$("#popCampSearchForm input[name='page']").val(pageNum);
	var param = $("#popCampSearchForm").serialize();
	$.ajax({
		type : "GET",
		url : "./pop/popCampList.ums?" + param,
		dataType : "html",
		success : function(pageHtml){
			$("#divPopContCamp").html(pageHtml);
		},
		error : function(){
			alert("Page Loading Error!!");
		}
	});
}


// 팝업 캠페인 목록에서 선택 클릭시
function setPopCampInfo(campNo, campNm, campTy) {
	$("#campNo").val(campNo);
	$("#campTy").val(campTy);
	$("#txtCampNm").html(campNm);
	
	fn.popupClose("#popup_smsTemplate_campaign");
}

// 팝업 캠페인 목록 페이징
function goPageNumPopCamp(pageNum) {
	goPopCampSearch(pageNum);
}

// 팝업 캠페인 신규등록화면/수정화면 목록 클릭시
function goPopCampList() {
	var param = $("#popCampSearchForm").serialize();
	$.ajax({
		type : "GET",
		url : "./pop/popCampList.ums?" + param,
		dataType : "html",
		success : function(pageHtml){
			$("#divPopContCamp").html(pageHtml);
		},
		error : function(){
			alert("Page Loading Error!!");
		}
	});
}

// 수신자그룹 선택 클릭시(수신자그룹선택 팝업)
function popSegSelect() {
	$(".fileupload .inputfile ~ button.btn").click();

	$("#popSegSearchForm input[name='searchStartDt']").val("");
	$("#popSegSearchForm input[name='searchEndDt']").val("");
	$("#popSegSearchForm input[name='searchSegNm']").val("");
	goPopSegSearch("1");
	fn.popupOpen("#popup_seg_segment");
}

// 팝업 수신자그룹 목록 검색
function goPopSegSearch(pageNum) {
	$("#popSegSearchForm input[name='page']").val(pageNum);
	var param = $("#popSegSearchForm").serialize();
	$.ajax({
		type : "GET",
		url : "./pop/popSegList.ums?" + param,
		dataType : "html",
		success : function(pageHtml){
			$("#divPopContSeg").html(pageHtml);
		},
		error : function(){
			alert("Page Loading Error!!");
		}
	});
}

// 팝업 수신자그룹 목록에서 선택 클릭시
function setPopSegInfo(segNoc, segNm) {
	$("#segNoc").val(segNoc);
	$("#txtSegNm").html(segNm);
	
	// 머지입력 초기화
	$("#mergeKeySms").children().remove();
	
	var condHTML = "";
	var merge = segNoc.substring(segNoc.indexOf("|")+1);
	var pos = merge.indexOf(",");
	while(pos != -1) {
		condHTML = "<option value='$:"+merge.substring(0,pos)+":$'>"+merge.substring(0,pos)+"</option>";
		$("#mergeKeySms").append(condHTML);
		merge = merge.substring(pos+1);
		pos = merge.indexOf(",");
	}
	condHTML = "<option value='$:"+merge+":$'>"+merge+"</option>";
	$("#mergeKeySms").append(condHTML);
	
	fn.popupClose("#popup_seg_segment");
}

// 팝업 수신자그룹 목록 페이징
function goPageNumPopSeg(pageNum) {
	goPopSegSearch(pageNum);
}

// 팝업 템플릿 신규등록화면/수정화면 목록 클릭시
function goPopSegList() {
	var param = $("#popSegSearchForm").serialize();
	$.ajax({
		type : "GET",
		url : "./pop/popSegList.ums?" + param,
		dataType : "html",
		success : function(pageHtml){
			$("#divPopContSeg").html(pageHtml);
		},
		error : function(){
			alert("Page Loading Error!!");
		}
	});
}
