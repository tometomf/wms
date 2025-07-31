<%@page import="java.util.List"%>
<%@page import="user.model.User"%>
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
		<div id="wms-title">ユーザー現況</div>
		<div id="search-bar" style="display: flex;">
			<div>
				<button id="search-button">조회</button>
			</div>
		</div>
		<div id="result-table">
			<table>
				<thead>
					<tr>
						<th>사원번호</th>
						<th>사원이름</th>
						<th>부서명</th>
						<th>휴대전화</th>
						<th>이메일</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<User> userList = (List<User>) request.getAttribute("userList");
					if (userList != null && !userList.isEmpty()) {
						for (User user : userList) {
					%>
					<tr>
						<td><%=user.getUserCd()%></td>
						<td><%=user.getUserNm()%></td>
						<td><%=user.getDeptNm()%></td>
						<td><%=user.getPhone()%></td>
						<td><%=user.getEmail()%></td>
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