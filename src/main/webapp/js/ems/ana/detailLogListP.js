/**********************************************************
	*	작성자 : 김준희
	*	작성일시 : 2022.01.03
*	설명 : 상세로그 JavaScript
**********************************************************/

$(document).ready(function() {
	
	$("input[name='isSendTerm']:radio").change(function () {
		if($("input[name='isSendTerm']").eq(1).is(":checked")) {
			$("#searchServiceGb").val("20");
		} else {
			$("#searchServiceGb").val("10");
		}
		$("#searchCampNo").val("0");
		$("#txtCampNm").html("선택된 캠페인이 없습니다");
	});
		
	// 화면 로딩시 검색 실행
	goSearch("1");
});

function goChagePerPageRows(rows){
	$("#searchForm input[name='rows']").val(rows);
	goSearch("1");
}

// 검색 버튼 클릭
function goSearch(pageNo) {
	if($("#searchStartDt").val() > $("#searchEndDt").val()) {
		alert("검색 시 시작일은 종료일보다 클 수 없습니다.");
		return;
	}
	
	var endDt = $("#searchEndDt").val().split('.');
	var startDt = $("#searchStartDt").val().split('.');
	var endDate = new Date(endDt[0], endDt[1], endDt[2]);
	var startDate = new Date(startDt[0], startDt[1], startDt[2]);
	
	var diff = endDate - startDate;
	var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
	if ( parseInt(diff/currDay) > 31 ){
		alert("검색 기간은 1개월 넘길수 없습니다");
		return;
	}
	
	if($("input[name='isSendTerm']").eq(1).is(":checked")) {
		$("#searchServiceGb").val("20");
	} else {
		$("#searchServiceGb").val("10");
	}
	
	$("#searchForm input[name='page']").val(pageNo);
	var param = $("#searchForm").serialize();
	$.ajax({
		type : "GET",
		url : "./detailLogList.ums?" + param,
		dataType : "html",
		success : function(pageHtml){
			$("#divLogList").html(pageHtml);
		},
		error : function(){
			alert("List Data Error!!");
		}
	});
}

function goMailDetil(taskNo, subTaskNo, attCnt, serviceGb, webAgent, contFlPath, obj ){

	var sendRepeatNm =""; //메일유형
	var sendDt =""; //발송일시
	var mailTitle =""; //메일명
	var campNm=""; //캠페인명
	var custId =""; //고객ID
	var custNm = ""; //고객명
	var custEmail = ""; //고객이메일
	var sendRcodeNm = ""; //발송결과 
	var bizkey = ""; //비즈키
	var webAgentTyp = ""; //웹 에이전트타입 
	var sendRcode = ""; //발송결과코드
	var respAmt = "0"; //메읽 읽어본 횟수 
	var segReal =  0;
	
	var selTr = $(obj.parentNode.parentNode); 
	var sell = $(selTr.children("td"));	 
	
	for(var j= 0; j < sell.length; j ++){
		var idx = $(sell[j]).index();
		var val = $(sell[j]).text();
		
		if( idx == 1 ){ //메일유형 
			sendRepeatNm = val;
		}else if( idx == 2 ){ //발송일시
			sendDt  = val;
		}else if( idx == 3 ){ //메일명
			mailTitle  = val;
		}else if( idx == 4 ){ //캠페인명 + 서비스명
			campNm  = val;
		}else if( idx == 5){ //고객ID
			custId  = val;
		}else if( idx == 6 ){ //고객명
			custNm  = val;
		}else if( idx == 7 ){ //고객이메일주소
			custEmail  = val;
		}else if( idx == 10 ){ //발송결과
			sendRcodeNm = val;
		}else if( idx == 13 ){ //비즈키
			bizkey  = val;
		}else if( idx == 14 ){ //웹 에이전트타입 
			webAgentTyp  = val;
		}else if( idx == 15 ){ //발송결과코드
			sendRcode  = val;
		}else if( idx == 16 ){ //수신확인횟수
			respAmt = val;
		} else if( idx == 17 ){ //대용량개별발송 쿼리 존재여부
			segReal = val;
		}
	}
	
	$("#popSendRepeatNm").text(sendRepeatNm);
	$("#popSendDate").text(sendDt);
	$("#popMailTitle").text(mailTitle);
	$("#popCampNm").text(campNm);
	$("#popCustId").text(custId);
	$("#popCustNm").text(custNm);
	$("#popCustEmail").text(custEmail);	
	$("#popAttCnt").text('(파일 개수 : ' + attCnt + '개)');
	
	$("#popWebAgent").text(webAgent);
	$("#previewWebAgentUrl").html(webAgent);
	
	$("#popServiceGb").val(serviceGb);
	$("#popTaskNo").val(taskNo);
	$("#popSubTaskNo").val(subTaskNo);
	$("#popCustIdd").val(custId);
	$("#popCustEm").val(custEmail);
	$("#popBizkey").val(bizkey);
	
	$("#popTid").val(taskNo);
	$("#popMid").val(subTaskNo);
	$("#popRid").val(custId);
	
	$("#popSegReal").val(segReal);
	
	//popResponseLogNm
	
	if(respAmt == "" || respAmt =="0" ) {
		$("#popResponseLogNm").text("미확인");
	} else {
		$("#popResponseLogNm").text("확인");
	}
	if(webAgent == "") {
		$("#popWebAgentDesc").text("첨부파일 형식이 지정되지 않았습니다");
	} else {
		if (webAgentTyp == "NONE") {
			$("#popWebAgentDesc").text( "(" + webAgentTyp + ") 본문삽입으로 지정되었습니다.");
		} else {
			$("#popWebAgentDesc").text( "(" + webAgentTyp + ") 첨부파일 형식이 지정되었습니다");	
		}
	}
	
	var reSendEnable = false;
	if (serviceGb == "10") {
		var enableCode = new Array("000", "004", "005", "007", "008");
		var findIndesx = enableCode.indexOf(sendRcode);
		if(findIndesx > -1){
			if(	bizkey == "NoEAI" || bizkey == "null" || bizkey == ""){
				reSendEnable = false;
				sendRcodeNm = sendRcodeNm; // + "(EAI 연계데이터가 존재 하지 않아 재발송 할 수 없습니다)";
			} else {
					//neo_segment_real 이 없을때 ...
					//sendRcodeNm = sendRcodeNm + "(EAI 연계 SQL이 존재 하지 않아 재발송 할 수 없습니다)";
				if( segReal  == 0) {
					reSendEnable = false;
					sendRcodeNm = sendRcodeNm; //+"(EAI 연계 SQL이 존재 하지 않아 재발송 할 수 없습니다)";
				} else{
					reSendEnable = true;
				}
			}
		}
	} else {
		var enableCode = new Array(1, 2, 7, 8, 13, 17, 19);
		var findIndesx = enableCode.indexOf(Number(sendRcode));
		if( findIndesx > -1){
			reSendEnable = true;
		}
	}

	if(reSendEnable){
		$("#btnReSend").show();
		$("#popResendEnabeDesc").text(sendRcodeNm);
	} else {
		$("#btnReSend").hide();
		$("#popResendEnabeDesc").text(sendRcodeNm);
	} 

	$("#btnReSend").hide();
	$("#popResendEnabeDesc").text(sendRcodeNm);
	
	if (serviceGb == "10") {
		param = "/ems/cam/mailFileView.ums?contFlPath=" + contFlPath;
	} else {
		param = "/rns/svc/mailFileView.ums?contentsPath=" + contFlPath;
	}
	
	$.getJSON(param, function(res) {
		if(res.result == 'Success') {
			$("#popContents").html( res.contVal );
		} else {
			$("#popContents").html( "<span>메일 미리보기에 실패했습니다</span>" ); 
		}
	});
	
	const btnAgentPreview = document.getElementById('btnWebAgentPreview');
	btnAgentPreview.style.display = 'none';

	const btnResend = document.getElementById('btnReSend');
	btnResend.style.display = 'none';
	
	fn.popupOpen('#popup_mail_detail_ems');
	
}

function popWebAgentPreview(){
	if(	$("#popWebAgent").text() == ""){
		alert("등록된 보안메일이 없습니다");
		return;
	}
	$("#iFrmWebAgent").empty();	
	iFrmWebAgent.location.href = $("#popWebAgent").text();
	fn.popupOpen('#popup_preview_webagent');
}
// 초기화 버튼 클릭
function goInit() {
	$("#searchForm")[0].reset();
	/*hidden reest*/
	$("#searchCampNo").val("0");
	$("#searchServiceGb").val("10");
	$("#txtCampNm").html("선택된 캠페인이 없습니다.");
	$("#taskNo").val("0");
	$("#subTaskNo").val("1");
	$("#sendRepeats").val("");
}

// 페이징
function goPageNum(pageNum) {
	goSearch(pageNum);
}

// 엑셀다운로드 클릭시
function excelDownload() {
	if($("#searchStartDt").val() > $("#searchEndDt").val()) {
		alert("검색 시 시작일은 종료일보다 클 수 없습니다.");
		return;
	}
	
	
	
	var endDt = $("#searchEndDt").val().split('.');
	var startDt = $("#searchStartDt").val().split('.');
	var endDate = new Date(endDt[0], endDt[1], endDt[2]);
	var startDate = new Date(startDt[0], startDt[1], startDt[2]);
	
	var diff = endDate - startDate;
	var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
	if ( parseInt(diff/currDay) > 365 ){
		alert("엑셀 다운로드 기간은 1년을 넘길수 없습니다");
		return;
	}
	
	var a = confirm("엑셀 다운로드는 시간이 오래 걸립니다 진행하시겠습니까?");
	if ( a ) {
		$("#searchForm").attr("target","iFrmExcel").attr("action","./detailLogExcel.ums").submit();
	} else return;
}

// 캠페인명 선택 클릭시(캠페인+RNS 서비스 선택 팝업)
function popCampRnsSelect() {

	var serviceGb = ""; 
	
	if($("input[name='isSendTerm']").eq(1).is(":checked")) {
		serviceGb ="20";
	} else {
		serviceGb ="10";
	}
	
	$("#popCampRnsSearchForm input[name='searchStartDt']").val("");
	$("#popCampRnsSearchForm input[name='searchEndDt']").val("");
	$("#popCampRnsSearchForm input[name='searchCampNm']").val("");
	$("#popCampRnsSearchForm input[name='searchServiceGb']").val(serviceGb);
	
	goPopCampRnsSearch("1", serviceGb);
	fn.popupOpen("#popup_campaign_rns");
}
// 캠페인명 초기화  클릭시 
function initCampRnsSelect() {
	$("#searchCampNo").val(0);
	$("#searchServiceGb").val("");
	$("#txtCampNm").html("선택된 캠페인이 없습니다.");
}

// 팝업 캠페인 + RNS 서비스 목록 검색
function goPopCampRnsSearch(pageNum, serviceGb) {
	$("#popCampRnsSearchForm input[name='page']").val(pageNum);
	var param = $("#popCampRnsSearchForm").serialize();
	
	if(param  == "" ){
		param = "searchServiceGb=" + serviceGb;
	}	
	$.ajax({
		type : "GET",
		url : "/ems/cam/pop/popCampRnsList.ums?" + param,
		dataType : "html",
		success : function(pageHtml){
			$("#divPopContCampRns").html(pageHtml);
		},
		error : function(){
			alert("Page Loading Error!!");
		}
	});
}

// 팝업 캠페인 목록에서 선택 클릭시
function setPopCampRnsInfo(campNo, campNm, serviceGb) {
	$("#searchCampNo").val(campNo);
	$("#searchServiceGb").val(serviceGb);
	$("#txtCampNm").html(campNm);
	
	fn.popupClose("#popup_campaign_rns");
}


// 팝업 캠페인 목록 페이징
function goPageNumPopCampRns(pageNum) {
	goPopCampRnsSearch(pageNum);
}

// 팝업 캠페인 목록 페이징
function mailReSend() {
	
	//대용량
	var param = $("#mailDetailForm").serialize();

	if ($("#popServiceGb").val() == "10"){
		if($("#reSendAuth").val() == "N"){
			alert("대용량 메일 재발송 권한이 없습니다");
			return;
		}
		param = "/ems/cam/mailReSend.json?" + param; 
	} else {
		if($("#rnsReSendAuth").val() == "N"){
			alert("실시간 메일 재발송 권한이 없습니다");
			return;
		}
		param = "/rns/svc/mailReSend.json?" + param; 
	}
	
	var a = confirm("재발송 하시겠습니까?");
	if ( a ) {
		$.getJSON(param, function(data) {
			if(data.result == "Success") {
				alert("재발송 되었습니다");
				fn.popupClose('#popup_mail_detail_ems');
			} else {
				alert("재발송에 실패하였습니다");
			}
		});	
	} else return;
}
