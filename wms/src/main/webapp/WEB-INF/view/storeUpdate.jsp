<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>データ修正</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">入庫登録</div>
		<div id="search-bar" style="display: flex;"></div>
		<form action="update.do" method="post" onsubmit = "return validateForm();">
			<div id="regist">
				<div id = "regist-menu">
					<div id="regist-text">入庫番号</div>
					<div id="regist-value">
						<input readonly type="text" name="store_no" value="${store.store_no}">
					</div>
					<div id="regist-text">入庫名</div>
					<div id="regist-value">
						<input type="text" name="store_nm" value="${store.store_nm}">
					</div>
					<div id="regist-text">入庫品目</div>
					<div id="regist-value">
						<input readonly type="text" name="item_cd" value="${store.item_cd}">
					</div>
					<div id="regist-text">入庫数量</div>
					<div id="regist-value">
						<input type="number" name="qty" min="0" value="${store.qty}">
					</div>
					<div id="regist-text">入庫単価</div>
					<div id="regist-value">
						<input type="number" name="store_price" min="0" value="${store.store_price}">
					</div>
					<div id="regist-text">入庫担当部署</div>
					<div id="regist-value">
						<input type="text" name="store_dept" value="${store.store_dept}"
							required>
					</div>
					<div id="regist-text">入庫担当者</div>
					<div id="regist-value">
						<input type="text" name="store_user" value="${store.store_user}"
							required>
					</div>
					<div id="regist-text">入庫日</div>
					<div id="regist-value">
						<input type="date" name="reg_ymd" value="${store.reg_ymd}"/>
					</div>
					<div id="regist-text">備考</div>
					<div id="regist-value">
						<input type="text" name="descr" value="${store.descr}">
					</div>
				</div>
				<div id="regist-button">
					<div>
						<input type="submit" value="修正"> 
						<a href="delete.do?store_no=${store.store_no}" id="button-link">削除</a>
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
	        { name: "item_cd", label: "入庫品目" },
	        { name: "qty", label: "入庫数量" },
	        { name: "store_price", label: "入庫単価" },
	        { name: "store_dept", label: "入庫担当部署" },
	        { name: "store_user", label: "入庫担当者" }
	        { name: "reg_ymd", label: "入庫日" }
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