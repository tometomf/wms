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
		<div id="wms-title">倉庫登録</div>
		<div id="search-bar" style="display: flex;">
		</div>
		<form action = "update.do" method = "post" onsubmit="return validateForm();">
			<div id = "regist">
				<div id = "regist-menu">
					<div id = "regist-text">발주번호</div>
					<div id = "regist-value">
						<input readonly type = "text" name = "purchase_no" value = "${pOrder.purchase_No}">
					</div>
					<div id = "regist-text">발주명</div>
					<div id = "regist-value">
						<input type = "text" name = "purchase_nm" value = "${pOrder.purchase_Nm}">
					</div>
					<div id = "regist-text">발주품목</div>
					<div id = "regist-value">
						<input readonly type = "text" name = "purchase_no" value = "${pOrder.item_Nm}">
					</div>
					<div id = "regist-text">수량</div>
					<div id = "regist-value">
						<input type = "number" name = "qty" value = "${pOrder.qty}">
					</div>
					<div id = "regist-text">발주담당부서</div>
					<div id = "regist-value">
						<input type = "text" name = "purchase_dept" value = "${pOrder.purchase_Dept}">
					</div>
					<div id = "regist-text">발주담당자</div>
					<div id = "regist-value">
						<input type = "text" name = "purchase_user" value = "${pOrder.purchase_User}">
					</div>
					<div id = "regist-text">비고</div>
					<div id = "regist-value">
						<textarea name="descr" rows="4" cols="56">${pOrder.descr}</textarea>
					</div>
				</div>
				<div id = "regist-button">
					<div>
						<input type = "submit" value = "修整">
						<a href="delete.do?purchaseNo=${pOrder.purchase_No}" id="button-link">削除</a>
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