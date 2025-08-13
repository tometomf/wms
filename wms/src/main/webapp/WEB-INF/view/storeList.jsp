<%@page import="java.util.List"%>
<%@page import="store.model.Store"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入庫現況</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/wms.css">

</head>
<body>

<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">入庫現況</div>
		<div id="search-bar" style="display: flex;">
		</div>
		<div id="result-table">
	<table border="1">
		<thead>
			<tr>
				<th>入庫番号</th>
				<th>入庫名</th>
				<th>品目コード</th>
				<th>品目数</th>
				<th>入庫担当部署</th>
				<th>入庫担当者</th>
				<th>備考</th>
				<th>入庫日</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<Store> storeList = (List<Store>) request.getAttribute("storeList");
			//storeListインスタンスを作って、StoreHandlerの中にあるstoreListから結果を持ってくる
			if (storeList != null && !storeList.isEmpty()) {
				//storeListがnullか相手なければ
				for (Store store : storeList) {
			%>
			<tr onclick="rowClicked(this)">
				<td><%=store.getStore_no()%></td>
				<td><%=store.getStore_nm()%></td>
				<td><%=store.getItem_cd()%></td>
				<td><%=store.getItem_qty()%></td>
				<td><%=store.getStore_dept()%></td>
				<td><%=store.getStore_user()%></td>
				<td><%=store.getDescr()%></td>
				<td><%=store.getReg_ymd()%></td>
			</tr>
			<%
			//データを持ってくる
			}
			} else {
			%>
			<tr>
				<td colspan="7">No data retrieved.</td>
			</tr>
			<%
			} //それともデータなしを見せる
			%>
		</tbody>
	</table>
	</div>
	</div>
</body>
</html>

<script>
	function rowClicked(row) {
		var cellValue = row.cells[0].innerText;
		location.href = "update.do?store_no=" + cellValue;
	}
</script>