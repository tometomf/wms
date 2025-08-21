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
		<div id="wms-title">受注現況</div> <!--受注現況 (주문 현황)-->
		<div id="search-bar" style="display: flex;">
		</div>
		<div id="result-table">
			<table>
				<thead>
					<tr>
						<th width = "4%">No.</th> <!-- 行番号 (행 번호) -->
						<th width = "6%">受注番号</th> <!-- 注文番号 (주문 번호) -->
						<th width = "10%">受注名</th> <!-- 注文名 (주문 이름) -->
						<th width = "6%">受注品目</th> <!-- 品目コード (품목 코드) -->
						<th width = "16%">受注品目名</th> <!-- 品目名 (품목명) -->
						<th width = "5%">数量</th> <!-- 数量 (수량) -->
						<th width = "6%">受注価格</th> <!-- 注文価格 (주문 가격) -->
						<th width = "8%">受注担当部署</th> <!-- 注文担当部署 (주문 담당 부서) -->
						<th width = "7%">受注担当者</th> <!-- 注文担当者 (주문 담당자) -->
						<th width = "6%">受注日</th> <!-- 注文日 (주문 일자) -->
						<th>備考</th> <!-- 備考 (비고) -->
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
	// 行ダブルクリックイベント (행 더블클릭 이벤트)
  	function rowClicked(row) {
		var cellValue = row.cells[1].innerText; // 最初のセルの値 (첫 번째 셀 값)
		location.href = "update.do?OrderNo=" + cellValue;
    	// alert("ダブルクリックした行の最初のセル: " + cellValue); (더블클릭한 행의 첫 번째 셀: 값)
  	}
</script>