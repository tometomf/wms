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
<title>入庫登録</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">入庫登録</div>
		<div id="search-bar" style="display: flex;"></div>
		<form action="insert.do" method="post" onsubmit = "return validateForm();">
			<div id="regist">
				<div id = "regist-menu">
					<div id="regist-text">入庫番号</div>
					<div id="regist-value">
						<input readonly type="text" name="store_no" value="${store_no}">
					</div>
					<div id="regist-text">入庫名</div>
					<div id="regist-value">
						<input type="text" name="store_nm">
					</div>
					<div id="regist-text">입고품목</div>
					<div id="regist-value">
						<select name = "item_cd">
							<option value = ""></option>
							<c:forEach var = "itemList" items = "${itemList}">
						   		<option value = "${itemList.itemCd}">${itemList.itemNm}</option>
							</c:forEach>
						</select>
					</div>
					<div id="regist-text">입고수량</div>
					<div id="regist-value">
						<input type="number" name="item_qty" min="0">
					</div>
					<div id="regist-text">입고금액</div>
					<div id="regist-value">
						<input type="number" name="store_price" min="0">
					</div>
					<div id="regist-text">入庫担当部署</div>
					<div id="regist-value">
						<input type="text" name="store_dept">
					</div>
					<div id="regist-text">入庫担当者</div>
					<div id="regist-value">
						<input type="text" name="store_user">
					</div>
					<div id="regist-text">備考</div>
					<div id="regist-value">
						<textarea name="descr" rows="4" cols="56"></textarea>
					</div>
					<div id="regist-text">수주등록일</div>
					<div id="regist-value">
						<input type="date" name="reg_ymd" value="<%= todayStr %>">
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

<script>
	function validateForm() {
		
	    var requiredFields = [
	        { name: "store_nm", label: "入庫名" },
	        { name: "shipDept", label: "出庫部署" },
	        { name: "shipUser", label: "担当者" },
	        { name: "itemCd", label: "品目コード" },
	        { name: "shipPrice", label: "出庫単価" },
	        { name: "shipQty", label: "出庫数量" }
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
