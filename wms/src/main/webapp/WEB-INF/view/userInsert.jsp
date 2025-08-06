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
		<div id="wms-title">ユーザー登録</div>
		<div id="search-bar" style="display: flex;">
		</div>
		<form action = "insert.do" method = "post">
			<div id = "regist">
				<div id = "regist-menu">
					<div id = "regist-text">사원번호</div>
					<div id = "regist-value">
						<input readonly type = "text" name = "userCd" value = "${userCd.userCd}">
					</div>
					<div id = "regist-text">사원이름</div>
					<div id = "regist-value">
						<input type = "text" name = "userNm">
					</div>
					<div id = "regist-text">부서명</div>
					<div id = "regist-value">
						<input type = "text" name = "deptNm">
					</div>
					<div id = "regist-text">휴대전화</div>
					<div id = "regist-value">
						<input type = "text" name = "phone">
					</div>
					<div id = "regist-text">이메일</div>
					<div id = "regist-value">
						<input type = "text" name = "email">
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