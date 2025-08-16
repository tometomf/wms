<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
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
		<div id="wms-title">발주등록</div>
		<div id="search-bar" style="display: flex;">
		</div>
		<form action = "insert.do" method = "post" onsubmit="return validateForm();">
			<div id = "regist">
				<div id = "regist-menu">
					<div id = "regist-text">발주번호</div>
					<div id = "regist-value">
						<input readonly type = "text" name = "purchase_no" value = "${purchaseNo.purchase_No}">
					</div>
					<div id = "regist-text">발주명</div>
					<div id = "regist-value">
						<input type = "text" name = "purchase_nm">
					</div>
					<div id = "regist-text">발주품목</div>
					<div id = "regist-value">
						<select name = "item_cd">
							<option value = ""></option>
							<c:forEach var = "itemList" items = "${itemList}">
						   		<option value = "${itemList.itemCd}">${itemList.itemNm}</option>
							</c:forEach>
						</select>
					</div>
					<div id = "regist-text">수량</div>
					<div id = "regist-value">
						<input type = "number" name = "qty">
					</div>
					<div id = "regist-text">발주담당부서</div>
					<div id = "regist-value">
						<input type = "text" name = "purchase_dept">
					</div>
					<div id = "regist-text">발주담당자</div>
					<div id = "regist-value">
						<input type = "text" name = "purchase_user">
					</div>
					<div id = "regist-text">비고</div>
					<div id = "regist-value">
						<textarea name="descr" rows="4" cols="56"></textarea>
					</div>
				</div>
				<div id = "regist-button">
					<div>
						<input type = "submit" value = "貯蔵">
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>

<script>

	function validateForm() {
	    var requiredFields = [
	        { name: "purchase_nm", label: "발주명" },
	        { name: "item_cd", label: "발주품목" },
	        { name: "qty", label: "수량" },
	        { name: "purchase_dept", label: "발주담당부서" },
	        { name: "purchase_user", label: "발주담당자" }
	    ];
	
	    for (let field of requiredFields) {
	        let value = document.getElementsByName(field.name)[0].value.trim();
	        if (!value) {
	            alert(field.label + "を入力してください");
	            document.getElementsByName(field.name)[0].focus();
	            return false; 
	        }
	    }
	    return true; 
	}
</script>