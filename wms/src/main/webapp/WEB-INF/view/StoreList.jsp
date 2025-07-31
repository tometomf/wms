<%@page import"java.util.List" %>
<%@page import"store.model.Store %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>입고번호</th>
				<th>입고명</th>
				<th>입고담당부서</th>
				<th>입고담당사원</th>
				<th>비고</th>
				<th>입고일</th>
			</tr>
	</thead>		
	<tbody>
		<%
			List<Store> storeList = (List<Store>) request.getAttribute("storeList");
			//storeList 객체를 만들고, 값 가져오기를 storeList(StoreHandler)에서 가져온다.
			if (storeList != null && !storeList.isEmpty()) {
			//storeList의 값이 null이거나 비어있지 않다면
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
		<%		//이것들의 값을 가져온다.
				}
			} else {
		%>	
			<tr>
				<td colspan="7">No data retrieved.</td>
			</tr>
		<% 
				} //아니면 데이터 없음을 표시한다.
		%>	
		</tbody>	
		</table>
		
</body>
</html>