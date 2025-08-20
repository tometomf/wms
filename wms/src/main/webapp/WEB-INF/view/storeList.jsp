<%@page import="java.util.List"%>
<%@page import="store.model.Store"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入庫現況一覧</title>
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
				<th width = "4%">No.</th>
				<th width = "6%">入庫番号</th>
				<th width = "8%">入庫名</th>
				<th width = "8%">入庫品目</th>
				<th width = "8%">入庫品目名</th>
				<th width = "7%">入庫数量</th>
				<th width = "7%">入庫単価</th>
				<th width = "10%">入庫担当部署</th>
				<th width = "9%">入庫担当者</th>
				<th width = "6%">入庫日</th>
				<th>備考</th>
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
			<tr ondblclick = "rowClicked(this)">
				<td><%=store.getNo()%></td>
				<td><%=store.getStore_no()%></td>
				<td style = "text-align: left; padding-left: 5px"><%=store.getStore_nm()%></td>
				<td><%=store.getItem_cd()%></td>
				<td style = "text-align: left; padding-left: 5px"><%=store.getItem_nm()%></td>
				<td style = "text-align: right; padding-right: 5px"><%=store.getQty()%></td>
				<td style = "text-align: right; padding-right: 5px"><%=store.getStore_price()%></td>
				<td><%=store.getStore_dept()%></td>
				<td><%=store.getStore_user()%></td>
				<td><%=store.getReg_ymd()%></td>
				<td style = "text-align: left; padding-left: 5px"><%=store.getDescr()%></td>
			</tr>
			<%
			//データを持ってくる
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
	function rowClicked(row) {
		var cellValue = row.cells[1].innerText;
		location.href = "update.do?store_no=" + cellValue;
	}
</script>

