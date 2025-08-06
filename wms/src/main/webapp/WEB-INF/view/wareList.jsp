<%@page import="java.util.List"%>
<%@page import="ware.model.Ware"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<div id="wms-title">倉庫現況</div>
		<div id="search-bar" style="display: flex;">
			<div>
				<button id="search-button">조회</button>
			</div>
		</div>
		<div id="result-table">
			<table>
				<thead>
					<tr>
						<th>창고코드</th>
						<th>창고명</th>
						<th>창고구분</th>
						<th>사용여부</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Ware> wareList = (List<Ware>) request.getAttribute("wareList");
					if (wareList != null && !wareList.isEmpty()) {
						for (Ware ware : wareList) {
					%>
					<tr ondblclick="rowClicked(this)">
						<td><%=ware.getWareCd()%></td>
						<td><%=ware.getWareNm()%></td>
						<td><%=ware.getWareGubun()%></td>
						<td><%=ware.getUseYn()%></td>

					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="7">조회된 데이터가 없습니다.</td>
					</tr>
					<%
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
		location.href = "update.do?wareCd=" + cellValue;
    	// alert("더블클릭한 행의 첫 번째 셀: " + cellValue);
  	}
</script>