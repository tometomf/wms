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
		<div id="search-bar" style="display: flex;"></div>
		<div id="result-table">
			<table>
				<thead>
					<tr>
						<th width = "4%">No.</th>
						<th width = "8%">品目コード</th>
						<th width = "14%">品目名</th>
						<th width = "12%">規格</th>
						<th width = "12%">分類</th>
						<th width = "8%">単位</th>
						<th width = "8%">使用有無</th>
						<th width = "14%">メーカー</th>
						<th width = "10%">受注基準単価</th>
						<th width = "10%">出庫基準単価</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Item> itemList = (List<Item>) request.getAttribute("itemList"); //”itemList”名前で探す。string形式ので型変換してList<item>として扱う。
					if (itemList != null && !itemList.isEmpty()) {
						for (Item item: itemList) {
					%>
					<tr ondblclick="rowClicked(this)">　<!-- ダブルクリックのイベント -->
						<td><%=item.getNo() %></td>
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

<script>
	// OnRowDblClick Event
  	function rowClicked(row) {
		var cellValue = row.cells[0].innerText; 
		location.href = "update.do?itemCd=" + cellValue;　//最初のセルの値(コード)をセットしてupdateに移動 
  	}
</script>