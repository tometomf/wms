<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
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
<title>在庫登録</title>
<link rel="stylesheet" 	href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="/nav.jsp"%>
	
	<div id="main-content">
		<div id="wms-title">在庫登録</div>
		<div id="search-bar" style="display: flex;"></div>
		<form action="insert.do" method="post" onsubmit="return validateForm();">
			<div id="regist">
				<div id="regist-menu">
					<div id="regist-text">在庫番号</div>
					<div id="regist-value">
						<input readonly type="text" name="stockNo" value="${stockNo}">
					</div>
					<div id="regist-text">品目コード</div>
					<div id="regist-value">
						<select name = "itemCd">
							<option value = ""></option>
							<c:forEach var = "itemList" items = "${itemList}">
						   		<option value = "${itemList.itemCd}">${itemList.itemNm}</option>
							</c:forEach>
						</select>
					</div>
					<div id="regist-text">在庫数量</div>
					<div id="regist-value">
						<input type="number" name="qty">
					</div>
					<div id="regist-text">倉庫コード</div>
					<div id="regist-value">
						<select name = "wareCd">
							<option value = ""></option>
							<c:forEach var = "wareList" items = "${wareList}">
						   		<option value = "${wareList.wareCd}">${wareList.wareNm}</option>
							</c:forEach>
						</select>
					</div>
					<div id="regist-text">登録日</div>
					<div id="regist-value">
						<input type="date" name="regYmd"  value="<%= todayStr %>">
					</div>
					<div id="regist-text">備考</div>
					<div id="regist-value">
						<textarea name="descr" rows="4" cols="56"></textarea>
					</div>
				</div>
				<div id="regist-button">
					<div>
						<input type="submit" value="貯蔵">
					</div>
				</div>
			</div>
		</form>
	</div>	
</body>
</html>

<script>

	<!-- 	에러 메세지 알림창 / エラーメッセージ通知ウィンドウ -->
	function validateForm() {
	    var requiredFields = [
	        { name: "itemCd", label: "品目コード" },
	        { name: "qty", label: "在庫数量" },
	        { name: "wareCd", label: "倉庫コード" },
	        { name: "regYmd", label: "登録日" }
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