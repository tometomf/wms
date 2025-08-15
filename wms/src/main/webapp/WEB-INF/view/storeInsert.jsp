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
		<form action="insert.do" method="post">
			<div id="regist">
				<div id="regist-text">入庫番号</div>
				<div id="regist-value">
					<input readonly type="text" name="store_no" value="${store_no}">
				</div>
				<div id="regist-text">入庫名</div>
				<div id="regist-value">
					<input type="text" name="store_nm" required>
				</div>
				<div id="regist-text">品目コード</div>
				<div id="regist-value">
					<input type="text" name="item_cd" required>
				</div>
				<div id="regist-text">品目数</div>
				<div id="regist-value">
					<input type="number" name="item_qty" min="0" required>
				</div>
				<div id="regist-text">入庫担当部署</div>
				<div id="regist-value">
					<input type="text" name="store_dept" required>
				</div>
				<div id="regist-text">入庫担当者名</div>
				<div id="regist-value">
					<input type="text" name="store_user" required>
				</div>
				<div id="regist-text">備考</div>
				<div id="regist-value">
					<input type="text" name="descr">
				</div>
				<div id="regist-text">入庫日</div>
				<div id="regist-value">
					<input type="date" name="reg_ymd" required>
				</div>
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

