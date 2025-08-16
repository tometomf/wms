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
		<div id="wms-title">受注登録</div>
		<div id="search-bar" style="display: flex;">
		</div>
		<form action = "insert.do" method = "post">
			<div id = "regist">
				<div id = "regist-menu">
					<div id = "regist-text">受注番号</div>
					<div id = "regist-value">
						<input readonly type = "text" name = "order_no" value = "${orderNo}">
					</div>
					<div id = "regist-text">受注名</div>
					<div id = "regist-value">
						<input type = "text" name = "order_nm">
					</div>
					<div id = "regist-text">受注品目</div>
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
					<div id = "regist-text">受注価格</div>
					<div id = "regist-value">
						<input type = "number" name = "order_price">
					</div>
					<div id = "regist-text">担当部署</div>
					<div id = "regist-value">
						<input type = "text" name = "order_dept">
					</div>
					<div id = "regist-text">担当社員</div>
					<div id = "regist-value">
						<input type = "text" name = "order_user">
					</div>
					<div id = "regist-text">備考</div>
					<div id = "regist-value">
						<textarea name="descr" rows="4" cols="55"></textarea>
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
	        { name: "order_nm", label: "受注名" },
	        { name: "item_cd", label: "受注品目" },
	        { name: "qty", label: "数量" },
	        { name: "order_price", label: "발주담당부서" },
	        { name: "order_dept", label: "担当部署" },
	        { name: "order_user", label: "担当社員" }
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