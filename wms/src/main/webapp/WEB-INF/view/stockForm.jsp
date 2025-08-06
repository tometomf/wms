<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>재고 등록</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>

	<%@ include file="/nav.jsp" %>

	<div id="main-content">
		<h2>재고 등록</h2>
		<form action="${pageContext.request.contextPath}/stock/write.do" method="post">
			<table>
				<tr>
					<td>품목코드</td>
					<td><input type="text" name="itemCd" required></td>
				</tr>
				<tr>
					<td>재고수량</td>
					<td><input type="number" name="qty" required></td>
				</tr>
				<tr>
					<td>창고코드</td>
					<td><input type="text" name="wareCd" required></td>
				</tr>
				<tr>
					<td>등록일</td>
					<td><input type="date" name="regYmd" required></td>
				</tr>
			</table>
			<br>
			<button type="submit">등록하기</button>
		</form>
	</div>

</body>
</html>
