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
						<th width = "4%">No</th>
						<th width = "6%">발주번호</th>
						<th width = "18%">발주명</th>
						<th width = "6%">발주품목</th>
						<th width = "16%">발주품목명</th>
						<th width = "5%">수량</th>
						<th width = "">담당부서</th>
						<th width = "">담당자</th>
						<th width = "8%">등록일</th>
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
		var cellValue = row.cells[1].innerText; // 첫 번째 셀 값
		location.href = "update.do?purchase_No=" + cellValue;
    	// alert("더블클릭한 행의 첫 번째 셀: " + cellValue);
  	}
</script>