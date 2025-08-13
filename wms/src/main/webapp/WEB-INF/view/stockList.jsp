<%@page import="java.util.List"%>
<%@page import="stock.model.StockPlus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 조회</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">
</head>

<body>
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">在庫現況</div>
		<div id="search-bar" style="display: flex;">
			<div>
				<button id="search-button">조회</button>
			</div>
		</div>

		<div id="result-table">
			<table>
				<thead>
					<tr>
						<th>在庫番号</th>
						<th>品目コード</th>
						<th>品目名</th>
						<th>規格</th>
						<th>区分</th>
						<th>メーカー</th>
						<th>在庫数量</th>
						<th>倉庫コード</th>
						<th>倉庫名</th>
						<th>登録日</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<StockPlus> stockList = (List<StockPlus>) request.getAttribute("stockList");
					if (stockList != null && !stockList.isEmpty()) {
						for (StockPlus stock : stockList) {
					%>
					<tr ondblclick="rowClicked(this)">
						<td><%=stock.getStock_No()%></td>
						<td><%=stock.getItem_Cd()%></td>
						<td><%=stock.getItem_Nm()%></td>
						<td><%=stock.getSpec()%></td>
						<td><%=stock.getItem_Gubun()%></td>
						<td><%=stock.getManufacturer()%></td>
						<td><%=stock.getQty()%></td>
						<td><%=stock.getWare_Cd()%></td>
						<td><%=stock.getWare_Nm()%></td>
						<td><%=stock.getReg_Ymd()%></td>
					</tr>
					<%
						}
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>

<script>
	// 더블클릭 시 해당 품목 코드로 수정 페이지 이동
  	function rowClicked(row) {
		var stockNo = row.cells[0].innerText; // 첫 번째 셀: stockNo
		location.href = "update.do?stockNo=" + stockNo;
  	}
</script>
