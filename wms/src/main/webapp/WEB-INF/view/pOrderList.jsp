<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">
</head>

<body>
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">発注現況</div>
		<div id="search-bar" style="display: flex;"></div>
		<div id="result-table">
			<table>
				<thead>
					<tr>
						<th width = "4%">No.</th>
						<th width = "6%">発注番号</th>
						<th width = "13%">発注名</th>
						<th width = "6%">発注品目</th>
						<th width = "16%">発注品目名</th>
						<th width = "5%">数量</th>
						<th width = "8%">発注担当部署</th>
						<th width = "7%">発注担当者</th>
						<th width = "6%">発注日</th>
						<th>備考</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pOrder" items="${pOrderList}">
					    <tr ondblclick="rowClicked(this)">
					        <td>${pOrder.no}</td>
					        <td>${pOrder.purchase_No}</td>
					        <td style = "text-align: left; padding-left: 5px">${pOrder.purchase_Nm}</td>
					        <td>${pOrder.item_Cd}</td>
					        <td style = "text-align: left; padding-left: 5px">${pOrder.item_Nm}</td>
					        <td style = "text-align: right; padding-right: 5px">${pOrder.qty}</td>
					        <td>${pOrder.purchase_Dept}</td>
					        <td>${pOrder.purchase_User}</td>
					        <td>${pOrder.reg_Ymd}</td>
					            <td style = "text-align: left; padding-left: 5px">${pOrder.descr}</td>
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
		var cellValue = row.cells[1].innerText; // 第一のCELLの結果
		location.href = "update.do?purchase_No=" + cellValue;
    	// alert("ダブルクリックした行の第一のCELL: " + cellValue);
  	}
</script>