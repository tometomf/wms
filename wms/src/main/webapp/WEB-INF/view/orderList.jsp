<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/wms.css">
</head>

<body>
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">受注現況</div>
		<div id="search-bar" style="display: flex;">
		</div>
		<div id="result-table">
			<table>
				<thead>
					<tr>
						<th>수주번호</th>
						<th>수주명</th>
						<th>수주품목번호</th>
						<th>수주품목명</th>
						<th>수주가격</th>
						<th>담당부서</th>
						<th>담당사원</th>
						<th>등록일</th>
						<th>수주완료유무</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orderList}">
					    <tr ondblclick="rowClicked(this)">
					        <td align="center">${order.order_No}</td>
					        <td align="left">${order.order_Nm}</td>
					        <td align="center">${order.item_Cd}</td>
					        <td align="center">${order.item_Nm}</td>
					        <td align="left">${order.order_Price}</td>
					        <td align="right">${order.order_Dept}</td>
					        <td align="right">${order.order_User}</td>
					        <td align="right">${order.reg_Ymd}</td>
					        <td align="right">${order.store_Yn}</td>
					    </tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>

<script>

	// OnRowDblClick Event
  	function rowClicked(row) {
		var cellValue = row.cells[0].innerText; // 첫 번째 셀 값
		location.href = "update.do?OrderNo=" + cellValue;
    	// alert("더블클릭한 행의 첫 번째 셀: " + cellValue);
  	}
</script>