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
		<div id="wms-title">受注登録</div>
		<div id="search-bar" style="display: flex;">
		</div>
		<form action = "insert.do" method = "post">
			<div id = "regist">
				<div id = "regist-menu">
					<div id = "regist-text">수주번호</div>
					<div id = "regist-value">
						<input readonly type = "text" name = "order_no" value = "${orderNo.order_No}">
					</div>
					<div id = "regist-text">수주명</div>
					<div id = "regist-value">
						<input type = "text" name = "order_nm">
					</div>
					<div id = "regist-text">수주품목번호</div>
					<div id = "regist-value">
						<input type = "text" name = "item_cd">
					</div>
					<div id = "regist-text">수주가격</div>
					<div id = "regist-value">
						<input type = "number" name = "order_price">
					</div>
					<div id = "regist-text">담당부서</div>
					<div id = "regist-value">
						<input type = "text" name = "order_dept">
					</div>
					<div id = "regist-text">담당사원</div>
					<div id = "regist-value">
						<input type = "text" name = "order_user">
					</div>
					<div id = "regist-text">수주구분</div>
					<div id = "regist-value">
						<input type = "text" name = "order_gubun">
					</div>
					<div id = "regist-text">수주완료유무</div>
					<div id = "regist-value">
						<select	name = "store_yn">
							<option value="N">未完了</option>
							<option value="Y">完了</option>
						</select>
					</div>
					<div id = "regist-text">비고</div>
					<div id = "regist-value">
						<textarea name="descr" rows="4" cols="55"></textarea>
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