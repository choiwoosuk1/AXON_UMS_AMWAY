/**********************************************************
*	작성자 : 김준희
*	작성일시 : 2021.09.07
*	설명 : 통계분석 캠페인별 분석 목록 화면 JavaScript
**********************************************************/

$(document).ready(function() {
	goSearch("1");
});

function goChagePerPageRows(rows){
	$("#searchForm input[name='rows']").val(rows);
	goSearch("1");
}
// 검색 버튼 클릭시
function goSearch(pageNum) {
	if( $("#searchStartDt").val() > $("#searchEndDt").val() ) {
		alert("검색 시 시작일은 종료일보다 클 수 없습니다.");		// COMJSALT017
		return;
	}
	
	var endDt = $("#searchEndDt").val().split('.');
	var startDt = $("#searchStartDt").val().split('.');
	var endDate = new Date(endDt[0], endDt[1], endDt[2]);
	var startDate = new Date(startDt[0], startDt[1], startDt[2]);
	
	var diff = endDate - startDate;
	var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
	if ( parseInt(diff/currDay) > 365 ){
		alert("검색 기간은 1년을 넘길수 없습니다");
		return;
	}
	
	$("#searchForm input[name='page']").val(pageNum);
	var param = $("#searchForm").serialize();
	$.ajax({
		type : "GET",
		url : "./campList.ums?" + param,
		dataType : "html",
		success : function(pageHtml){
			$("#divCampList").html(pageHtml);
		},
		error : function(){
			alert("List Data Error!!");
		}
	});
}

// 초기화 버튼 클릭시
function goInit() {
	$("#searchForm")[0].reset();
}

// 목록에서 전체선택 클릭시
function goAll() {
	$("#searchForm input[name='campNos']").each(function(idx,item){
		$(item).prop("checked", $("#searchForm input[name='isAll']").is(":checked"));
	});
}

// 목록에서 캠페인명 클릭시
function goCampStat(campNo) {
	$("#campNo").val(campNo);
	$("#searchForm").attr("target","").attr("action","./campSummP.ums").submit();
}

// 병합분석 클릭시
function goJoin() {
	var checkCnt = 0;
	
	$("#searchForm input[name='campNos']").each(function(idx,item){
		if($(item).is(":checked")) {
			checkCnt++;
		}
	});

    if(checkCnt < 2) {
        alert("병합할 대상을 2개이상 선택하세요.");
    } else {
		$("#searchForm").attr("target","").attr("action","./campJoinP.ums").submit();
	}
}


// 페이징
function goPageNum(pageNum) {
	goSearch(pageNum);
}
