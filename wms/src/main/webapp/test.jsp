<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="nav.jsp" %>
	<div id = "main-content">
		<div id = "wms-title">ユーザー登録</div>
		<div id = "search-bar">
			<label for="start-date">청구일자</label>
		  	<input type="date" id="start-date" name="start-date">
		  	~
		  	<input type="date" id="end-date" name="end-date">
		</div>
		<div id = "result-table">
			<table>
				<thead>
					<tr>
						<td width = "10%">1</td>
						<td width = "10%">2</td>
						<td>3</td>
						<td>4</td>
						<td>5</td>
						<td>6</td>
						<td>7</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>5</td>
						<td>6</td>
						<td>7</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>