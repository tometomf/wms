<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">品目登録</div>
		<div id="search-bar" style="display: flex;"></div>
		<form action="insert.do" method="post">
			<div id="regist">
				<div id="regist-menu">
					<div id="regist-text">품목코드</div>
					<div id="regist-value">
						<input readonly type="text" name="itemCd" value="${itemCd.itemCd}">
					</div>
					<div id="regist-text">품목명</div>
					<div id="regist-value">
						<input type="text" name="itemNm">
					</div>
					<div id="regist-text">규격</div>
					<div id="regist-value">
						<input type="text" name="spec">
					</div>
					<div id="regist-text">분류</div>
					<div id="regist-value">
						<input type="text" name="itemGubun">
					</div>
					<div id="regist-text">단위</div>
					<div id="regist-value">
						<input type="text" name="unit">
					</div>
					<div id="regist-text">사용여부</div>
					<div id="regist-value">
						<select name="useYn">
							<option value="Y">예</option>
							<option value="N">아니요</option>
						</select>
					</div>
					<div id="regist-text">제조사</div>
					<div id="regist-value">
						<input type="text" name="manufacturer">
					</div>
					<div id="regist-text">수주기준단가</div>
					<div id="regist-value">
						<input type="text" name="storePrice">
					</div>
					<div id="regist-text">출고기준단가</div>
					<div id="regist-value">
						<input type="text" name="shipmentPrice">
					</div>
				</div>
				<div id="regist-button">
					<div>
						<input type="submit" value="저장">
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>