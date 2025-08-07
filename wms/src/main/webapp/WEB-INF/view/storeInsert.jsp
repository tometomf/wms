<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入庫登録</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">入庫登録</div>
		<div id="search-bar" style="display: flex;"></div>
		<form action="regist.do" method="post">
			<div id="regist-text">入庫番号</div>
			<div id="regist-value">
				<input type="text" name="store_no"
					value="${store_no.store_no}">
			</div>
			<div id="regist-text">入庫名</div>
			<div id="regist-value">
				<input type="text" name="store_nm"
					value="${store_nm.store_nm}">
			</div>
			<div id="regist-text">入庫担当部署</div>
			<div id="regist-value">
				<input type="text" name="store_dept"
					value="${store_dept.store_dept}">
			</div>
			<div id="regist-text">入庫担当者名</div>
			<div id="regist-value">
				<input type="text" name="store_user"
					value="${store_user.store_user}">
			</div>
			<div id="regist-text">備考</div>
			<div id="regist-value">
				<input type="text" name="Descr" value="${Descr.Descr}">
			</div>
			<div id="regist-text">入庫日</div>
			<div id="regist-value">
				<input type="text" name="Reg_ymd"
					value="${Reg_ymd.Reg_ymd}">
			</div>
			<div id="regist-button">
				<div>
					<input type="submit" value="저장">
				</div>
			</div>
		</form>
	</div>
</body>
</html>