<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>在庫登録結果</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="/nav.jsp" %>
	<div id="main-content">
		<h2>在庫登録結果</h2>
		
		<p style="font-size: 1.2em; color: navy;">
			${msg}
		</p>
		
		<a href="${pageContext.request.contextPath}/stock/list.do">
			<button>在庫リストに戻る</button>
		</a>
	</div>
</body>
</html>
