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
		<div id="search-bar" style="display: flex;"></div>
		<div id="result-table">
			<table>
				<thead>
					<tr>
						<th width = "4%">No</th>
						<th width = "8%">倉庫コード</th>
						<th width = "18%">倉庫名</th>
						<th width = "12%">倉庫区分</th>
						<th width = "8%">使用可否</th>
						<th width = "50%">備考</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Ware> wareList = (List<Ware>) request.getAttribute("wareList");
					if (wareList != null && !wareList.isEmpty()) {
						for (Ware ware : wareList) {
					%>
							<tr ondblclick="rowClicked(this)">
								<td><%=ware.getNo()%></td>
								<td><%=ware.getWareCd()%></td>
								<td style = "text-align: left; padding-left: 5px"><%=ware.getWareNm()%></td>
								<td><%=ware.getWareGubun()%></td>
								<td><%=ware.getUseYn()%></td>
								<td style = "text-align: left; padding-left: 5px"><%=ware.getDescr()%></td>
							</tr>
						<%
						}
						%>	
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