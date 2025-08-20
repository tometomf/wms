<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<%
    Date today = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String todayStr = sdf.format(today);
%>

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
		<div id="wms-title">発注登録</div>
		<div id="search-bar" style="display: flex;">
		</div>
		<form action = "insert.do" method = "post" onsubmit="return validateForm();">
			<div id = "regist">
				<div id = "regist-menu">
					<div id = "regist-text">発注番号</div>
					<div id = "regist-value">
						<input readonly type = "text" name = "purchase_no" value = "${purchaseNo.purchase_No}">
					</div>
					<div id = "regist-text">発注名</div>
					<div id = "regist-value">
						<input type = "text" name = "purchase_nm">
					</div>
					<div id = "regist-text">発注品目</div>
					<div id = "regist-value">
						<select name = "item_cd">
							<option value = ""></option>
							<c:forEach var = "itemList" items = "${itemList}">
						   		<option value = "${itemList.itemCd}">${itemList.itemNm}</option>
							</c:forEach>
						</select>
					</div>
					<div id = "regist-text">数量</div>
					<div id = "regist-value">
						<input type = "number" name = "qty">
					</div>
					<div id = "regist-text">発注担当部署</div>
					<div id = "regist-value">
						<input type = "text" name = "purchase_dept">
					</div>
					<div id = "regist-text">発注担当者</div>
					<div id = "regist-value">
						<input type = "text" name = "purchase_user">
					</div>
					<div id="regist-text">発注日</div>
					<div id="regist-value">
						<input type="date" name="reg_ymd" value="<%= todayStr %>">
					</div>
					<div id = "regist-text">備考</div>
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
	        { name: "purchase_nm", label: "発注名" },
	        { name: "item_cd", label: "発注品目" },
	        { name: "qty", label: "数量" },
	        { name: "purchase_dept", label: "発注担当部署" },
	        { name: "purchase_user", label: "発注担当者" },
	        { name: "reg_ymd", label: "発注日" },
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