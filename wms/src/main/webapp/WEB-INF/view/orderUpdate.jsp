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
		<form action = "update.do" method = "post">
			<div id = "regist">
				<div id = "regist-menu">
					<div id = "regist-text">수주번호</div>
					<div id = "regist-value">
						<input readonly type = "text" name = "order_no" value = "${order.order_No}">
					</div>
					<div id = "regist-text">수주명</div>
					<div id = "regist-value">
						<input type = "text" name = "order_nm" value = "${order.order_Nm}">
					</div>
					<div id = "regist-text">수주품목번호</div>
					<div id = "regist-value">
						<input type = "text" name = "item_cd" value = "${order.item_Cd}">
					</div>
					<div id = "regist-text">수주가격</div>
					<div id = "regist-value">
						<input type = "number" name = "order_price" value = "${order.order_Price}">
					</div>
					<div id = "regist-text">담당부서</div>
					<div id = "regist-value">
						<input type = "text" name = "order_dept" value = "${order.order_Dept}">
					</div>
					<div id = "regist-text">담당사원</div>
					<div id = "regist-value">
						<input type = "text" name = "order_user" value = "${order.order_User}">
					</div>
					<div id = "regist-text">수주구분</div>
					<div id = "regist-value">
						<input type = "text" name = "order_gubun" value = "${order.order_Gubun}">
					</div>
					<div id = "regist-text">수주완료유무</div>
					<div id = "regist-value">
						<select	name = "useyn">
							<option value="Y" ${order.store_Yn == 'Y' ? 'selected' : ''}>利用あり</option>
    						<option value="N" ${order.store_Yn == 'N' ? 'selected' : ''}>利用なし</option>
						</select>
					</div>
					<div id = "regist-text">비고</div>
					<div id = "regist-value">
						<textarea name="descr" rows="4" cols="55">${order.descr}</textarea>
					</div>
				</div>
				<div id = "regist-button">
					<div>
						<input type = "submit" value = "수정">
						<a href="delete.do?orderNo=${order.order_No}" id="button-link">삭제</a>
					</div>
				</div>	
			</div>
		</form>
	</div>
</body>
</html>