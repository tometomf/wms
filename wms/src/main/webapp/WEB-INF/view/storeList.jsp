<%@page import="java.util.List" %>
<%@page import="store.model.Store" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入庫現況</title>
  <style>
    table { border-collapse: collapse; width: 100%; margin-top: 30px; }
    th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
    th { background-color: #f2f2f2; }
    h1 { margin-bottom: 20px; }
  </style>
</head>
<body>

<h1>入庫現況</h1>

	<table border="1">
		<thead>
			<tr>
				<th>入庫番号</th>
				<th>入庫名</th>
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
			<tr>
				<td><%= store.getStore_no()	%></td>
				<td><%= store.getStore_nm()	%></td>
				<td><%= store.getStore_dept()	%></td>
				<td><%= store.getStore_user()	%></td>
				<td><%= store.getDescr()	%></td>
				<td><%= store.getReg_ymd()	%></td>
			</tr>
		<%		//データを持ってくる
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
		
</body>
</html>