<%--
	/**********************************************************
	*	작성자 : 김준희
	*	작성일시 : 2021.08.02
	*	설명 : 통계분석 정기메일분석 화면
	**********************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/inc/header.jsp" %>

<style type="text/css">
.tab {
	background-color:#cccccc;
}
</style>

<script type="text/javascript" src="<c:url value='/js/ems/ana/taskListP.js'/>"></script>

<body onload="goOz('tab1', './taskSummP.ums','<c:out value='${taskNo}'/>');">

<table width="1000" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<div id="tab">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="100">
							<div id="click_tab1" style="display : none">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr height="23">
										<td width="6"></td>
										<td width="110" align="center" class="tab"><spring:message code='ANABTN003'/></td><!-- 결과요약 -->
										<td width="6"></td>
									</tr>
								</table>
							</div>
							<div id="tab1" style="display:">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr height="23">
										<td width="6"></td>
										<td width="110" align="center"><a href="javascript:goOzTab('tab1','/ems/ana/taskSummP.ums')"><spring:message code='ANABTN003'/></a><!-- 결과요약 -->
										<td width="6"></td>
									</tr>
								</table>
							</div>
						</td>
						<td width="1"></td>
						<td width="100">
							<div id="click_tab2" style="display : none">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="6"></td>
										<td width="110" align="center" class="tab"><spring:message code='ANABTN004'/></td><!-- 세부에러 -->
										<td width="6"></td>
									</tr>
								</table>
							</div>
							<div id="tab2" style="display:">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="6"></td>
										<td width="110" align="center"><a href="javascript:goOzTab('tab2','/ems/ana/taskErrorP.ums')"><spring:message code='ANABTN004'/></a><!-- 세부에러 -->
										<td width="6"></td>
									</tr>
								</table>
							</div>
						</td>
						<td width="1"></td>
						<td width="100">
							<div id="click_tab3" style="display : none">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="6"></td>
										<td width="110" align="center" class="tab"><spring:message code='ANABTN005'/></td><!-- 도메인별 -->
										<td width="6"></td>
									</tr>
								</table>
							</div>
							<div id="tab3" style="display:">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="6"></td>
										<td width="110" align="center"><a href="javascript:goOzTab('tab3','/ems/ana/taskDomainP.ums')"><spring:message code='ANABTN005'/></a><!-- 도메인별 -->
										<td width="6"></td>
									</tr>
								</table>
							</div>
						</td>
						<td width="1"></td>
						<td width="100">
							<div id="click_tab4" style="display : none">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="6"></td>
										<td width="110" align="center" class="tab"><spring:message code='ANABTN006'/></td><!-- 발송시간별 -->
										<td width="6"></td>
									</tr>
								</table>
							</div>
							<div id="tab4" style="display:">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="6"></td>
										<td width="110" align="center"><a href="javascript:goOzTab('tab4','/ems/ana/taskSendP.ums')"><spring:message code='ANABTN006'/></a><!-- 발송시간별 -->
										<td width="6"></td>
									</tr>
								</table>
							</div>
						</td>
						<td width="1"></td>
						<td width="100">
							<div id="click_tab5" style="display : none">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="6"></td>
										<td width="110" align="center" class="tab"><spring:message code='ANABTN007'/></td><!-- 응답시간별 -->
										<td width="6"></td>
									</tr>
								</table>
							</div>
							<div id="tab5" style="display:">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="6"></td>
										<td width="110" align="center"><a href="javascript:goOzTab('tab5','/ems/ana/taskRespP.ums')"><spring:message code='ANABTN007'/></a><!-- 응답시간별 -->
										<td width="6"></td>
									</tr>
								</table>
							</div>
						</td>
						<td align="right">&nbsp;</td>
					</tr>
				</table>
			</div>
			<div id="notab" style="display:none">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="right">&nbsp;
							</td>
					</tr>
				</table>
			</div>
		</td>
	</tr>
	<tr>
		<td height=5></td>
	</tr>
	<tr>
		<td>
			<iframe name="iFrmReport" border='0' frameborder='1' scrolling='no' width='100%' height='1100'></iframe>
		</td>
	</tr>
</table>

</body>
</html>
