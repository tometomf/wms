<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 조회</title>
</head>
<body>

	<table border="5">
	<tr>
		<td>재고번호 </td>
		<td>품목코드 </td>
		<td>재고수량 </td>
		<td>창고코드 </td>
		<td>등록일 </td>
	</tr>
		<c:forEach var="stock" items="${stockList.stock}">
			<tr>
				<td>${stock.stock_No}</td>
				<td>${stock.item_Cd}</td>
				<td>${stock.qty}</td>
				<td>${stock.ware_Cd}</td>
				<td>${stock.reg_Ymd}</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>