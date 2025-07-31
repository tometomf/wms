<%@ page import = "item.model.Item" %>
<%@page import="java.util.List"%>
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
		<div id="wms-title">品目現況</div>
		<div id="search-bar" style="display: flex;">
			<div>
				<button id="search-button">조회</button>
			</div>
		</div>
		<div id="result-table">
			<table>
				<thead>
					<tr>
						<th>품목코드</th>
						<th>품목명</th>
						<th>규격</th>
						<th>분류</th>
						<th>단위</th>
						<th>사용유무</th>
						<th>제조사</th>
						<th>수주기준단가</th>
						<th>출고기준단가</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Item> itemList = (List<Item>) request.getAttribute("itemList");
					if (itemList != null && !itemList.isEmpty()) {
						for (Item item: itemList) {
					%>
					<tr>
						<td><%=item.getItemCd()%></td>
						<td><%=item.getItemNm()%></td>
						<td><%=item.getSpec()%></td>
						<td><%=item.getItemGubun()%></td>
						<td><%=item.getUnit()%></td>
						<td><%=item.getUseYn()%></td>
						<td><%=item.getManufacturer()%></td>
						<td><%=item.getStorePrice()%></td>
						<td><%=item.getShipmentPrice()%></td>
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