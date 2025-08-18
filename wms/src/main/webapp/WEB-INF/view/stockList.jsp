<%@page import="java.util.List"%>
<%@page import="stock.model.StockPlus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫現況</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">
</head>

<body>
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">在庫現況</div>
		<div id="search-bar" style="display: flex;">
	
		</div>

		<div id="result-table">
			<table>
				<thead>
					<tr>
						<th width = "4%">No.</th> 
						<th width = "8%">在庫番号</th> 
						<th width = "8%">品目コード</th>
						<th width = "16%">品目名</th>
						<th width = "5%">数量</th>
						<th width = "8%">倉庫コード</th>
						<th width = "16%">倉庫名</th>
						<th width = "8%">登録日</th>
						<th>備考</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<StockPlus> stockList = (List<StockPlus>) request.getAttribute("stockList");
					if (stockList != null && !stockList.isEmpty()) {
						for (StockPlus stock : stockList) {
					%>
					<tr ondblclick="rowClicked(this)">
						<td><%=stock.getNo()%></td>
						<td><%=stock.getStock_No()%></td>
						<td><%=stock.getItem_Cd()%></td>
						<td style = "text-align: left; padding-left: 5px"><%=stock.getItem_Nm()%></td>
						<td style = "text-align: right; padding-right: 5px"><%=stock.getQty()%></td>
						<td><%=stock.getWare_Cd()%></td>
						<td style = "text-align: left; padding-left: 5px"><%=stock.getWare_Nm()%></td>
						<td><%=stock.getReg_Ymd()%></td>
						<td style = "text-align: left; padding-left: 5px"><%=stock.getDescr()%></td>
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
		var stockNo = row.cells[1].innerText; // 첫 번째 셀: stockNo
		location.href = "update.do?stockNo=" + stockNo;
  	}
</script>
