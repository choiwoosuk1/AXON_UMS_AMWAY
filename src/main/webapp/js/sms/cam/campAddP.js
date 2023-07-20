/**********************************************************
*	작성자 : 김준희
*	작성일시 : 2021.10.18
*	설명 : 캠페인 등록 JavaScript
**********************************************************/

$(document).ready(function(){
	$("#eaiCampNo").on("keyup", function() {
		$(this).val( $(this).val().replace(/[^A-Z0-9]/gi,"") );
		$(this).val( $(this).val().toUpperCase() );
	});
});

// 등록 클릭시
function goAdd() {
	// 입력 폼 검사
	if($("#campTy").val() == "") {
		alert("캠페인목적은 필수입력 항목입니다.");
		$("#campTy").focus();
		return;
	}
	if($("#status").val() == "") {
		alert("상태는 필수입력 항목입니다.");
		$("#status").focus();
		return;
	}
	if($("#deptNo").val() == "0") {
		alert("사용자그룹은 필수입력 항목입니다.");
		$("#deptNo").focus();
		return;
	}
	if($("#userId").val() == "") {
		alert("사용자는 필수입력 항목입니다.");
		$("#userId").focus();
		return;
	}
	if($("#campNm").val() == "") {
		alert("캠페인명은 필수입력 항목입니다.");
		$("#campNm").focus();
		return;
	}
	
	// 입력값 Byte 체크
	if($.byteString($("#campNm").val()) > 100) {
		alert("캠페인명은 100byte를 넘을 수 없습니다.");
		$("#campNm").focus();
		$("#campNm").select();
		return;
	}
	if($.byteString($("#campDesc").val()) > 4000) {
		alert("캠페인 설명은 4000byte를 넘을 수 없습니다.");
		$("#campDesc").focus();
		$("#campDesc").select();
		return;
	}
	
	// 등록 처리
	var frm = $("#campaignInfoForm")[0];
	var frmData = new FormData(frm);
	$.ajax({
		type: "POST",
		url: './campAdd.json',
		data: frmData,
		processData: false,
		contentType: false,
		success: function (res) {
			if(res.result == "Success") {
				alert("등록되었습니다.");
				$("#searchForm").attr("target","").attr("action","./campListP.ums").submit();
			} else {
				alert("등록 처리중 오류가 발생하였습니다.");
			}
		},
		error: function (e) {
			alert("등록 처리중 오류가 발생하였습니다.");
		}
	});
}

// 입력 폼 검사
function checkForm() {

	return false;
}

//  취소 클릭시(리스트로 이동)
function goCancel() {
	alert("등록이 취소되었습니다.");
	document.location.href= "./campListP.ums";
}
