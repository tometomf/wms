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
						<th width = "4%">No.</th>
						<th width = "6%">受注番号</th>
						<th width = "10%">受注名</th>
						<th width = "6%">受注品目</th>
						<th width = "16%">受注品目名</th>
						<th width = "5%">数量</th>
						<th width = "6%">受注価格</th>
						<th width = "8%">受注担当部署</th>
						<th width = "7%">受注担当者</th>
						<th width = "6%">受注日</th>
						<th>備考</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orderList}">
					    <tr ondblclick="rowClicked(this)">
					        <td>${order.no}</td>
					        <td>${order.order_No}</td>
					        <td style = "text-align: left; padding-left: 5px">${order.order_Nm}</td>
					        <td>${order.item_Cd}</td>
					        <td style = "text-align: left; padding-left: 5px">${order.item_Nm}</td>
					        <td style = "text-align: right; padding-right: 5px">${order.qty}</td>
					        <td style = "text-align: right; padding-right: 5px">${order.order_Price}</td>
					        <td>${order.order_Dept}</td>
					        <td>${order.order_User}</td>
					        <td>${order.reg_Ymd}</td>
					        <td style = "text-align: left; padding-left: 5px">${order.descr}</td>
					    </tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>

<script>

	// 行ダブルクリックイベント
  	function rowClicked(row) {
		var cellValue = row.cells[1].innerText; // 最初のセルの値
		location.href = "update.do?OrderNo=" + cellValue;
    	// alert("ダブルクリックした行の最初のセル: " + cellValue);
  	}
</script>