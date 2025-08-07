<%@page import="java.util.List"%>
<%@page import="stock.model.Stock"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 조회</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/wms.css">
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
						<th>在庫数量</th>
						<th>倉庫コード</th>
						<th>登録日</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Stock> stockList = (List<Stock>) request.getAttribute("stockList");
					if (stockList != null && !stockList.isEmpty()) {
						for (Stock stock : stockList) {
					%>
				<tr ondblclick="rowClicked(this)">
						<td><%=stock.getStock_No()%></td>
						<td><%=stock.getItem_Cd()%></td>
						<td><%=stock.getQty()%></td>
						<td><%=stock.getWare_Cd()%></td>
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
	// OnRowDblClick Event
  	function rowClicked(row) {
		var cellValue = row.cells[0].innerText; // 첫 번째 셀 값
		location.href = "update.do?stockNo=" + cellValue;
    	// alert("더블클릭한 행의 첫 번째 셀: " + cellValue);
  	}
</script>