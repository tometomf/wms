<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" 	href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">倉庫登録</div>
		<div id="search-bar" style="display: flex;">
		</div>
		<form action = "update.do" method = "post">
			<div id = "regist">
				<div id = "regist-menu">
					<div id = "regist-text">창고코드</div>
					<div id = "regist-value">
						<input readonly type = "text" name = "warecd" value = "${ware.wareCd}">
					</div>
					<div id = "regist-text">창고명</div>
					<div id = "regist-value">
						<input type = "text" name = "warenm" value = "${ware.wareNm}">
					</div>
					<div id = "regist-text">창고구분</div>
					<div id = "regist-value">
						<input type = "text" name = "waregb" value = "${ware.wareGubun}">
					</div>
					<div id = "regist-text">사용여부</div>
					<div id = "regist-value">
						<select	name = "useyn">
							<option value="Y" ${ware.useYn == 'Y' ? 'selected' : ''}>예</option>
    						<option value="N" ${ware.useYn == 'N' ? 'selected' : ''}>아니요</option>
						</select>
					</div>
				</div>
				<div id = "regist-button">
					<div>
						<input type = "submit" value = "수정">
						<a href="delete.do?wareCd=${ware.wareCd}" id="button-link">삭제</a>
					</div>
				</div>	
			</div>
		</form>
	</div>
</body>
</html>