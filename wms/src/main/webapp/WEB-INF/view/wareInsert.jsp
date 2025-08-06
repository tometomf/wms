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
		<form action = "insert.do" method = "post">
			<div id = "regist">
				<div id = "regist-menu">
					<div id = "regist-text">창고코드</div>
					<div id = "regist-value">
						<input readonly type = "text" name = "warecd" value = "${wareCd.wareCd}">
					</div>
					<div id = "regist-text">창고명</div>
					<div id = "regist-value">
						<input type = "text" name = "warenm">
					</div>
					<div id = "regist-text">창고구분</div>
					<div id = "regist-value">
						<input type = "text" name = "waregb">
					</div>
					<div id = "regist-text">사용여부</div>
					<div id = "regist-value">
						<select	name = "useyn">
							<option value = "Y">예</option>
							<option value = "N">아니요</option>
						</select>
					</div>
				</div>
				<div id = "regist-button">
					<div>
						<input type = "submit" value = "저장">
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>