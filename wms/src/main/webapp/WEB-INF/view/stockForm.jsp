<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 등록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">在庫登録</div>
		<div id="search-bar" style="display: flex;">
		</div>
		<form action="insert.do" method="post">
			<div id="regist">
				<div id="regist-menu">
					
					<div id="regist-text">재고번호</div>
					<div id="regist-value">
						<input readonly type="text" name="stockNo" value="${stockNo.stock_No}">
					</div>

					<div id="regist-text">품목코드</div>
					<div id="regist-value">
						<input type="text" name="itemCd" required>
					</div>

					<div id="regist-text">재고수량</div>
					<div id="regist-value">
						<input type="number" name="qty" required>
					</div>

					<div id="regist-text">창고코드</div>
					<div id="regist-value">
						<input type="text" name="wareCd" required>
					</div>

					<div id="regist-text">등록일</div>
					<div id="regist-value">
						<input type="date" name="regYmd" required>
					</div>
					
					<div id="regist-text">비고</div>
					<div id="regist-value">
						<textarea name="descr" rows="4" cols="56"></textarea>
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
