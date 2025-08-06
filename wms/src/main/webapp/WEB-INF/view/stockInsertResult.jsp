<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>재고 등록 결과</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="/nav.jsp" %>
	<div id="main-content">
		<h2>재고 등록 결과</h2>
		
		<p style="font-size: 1.2em; color: navy;">
			${msg}
		</p>
		
		<a href="${pageContext.request.contextPath}/stock/list.do">
			<button>재고 목록으로 돌아가기</button>
		</a>
	</div>
</body>
</html>
