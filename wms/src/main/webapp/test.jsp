<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" 	href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">ユーザー登録</div>
		<div id="search-bar" style="display: flex;">
			<div>
				<label for="start-date">청구일자</label> 
				<input type="date" id="start-date" name="start-date"> 
				~ 
				<input type="date" id="end-date" name="end-date">
			</div>
			<div>
				<button id = "search-button">조회</button>
			</div>
		</div>
		<div id="result-table">
			<table>
				<thead>
					<tr>
						<th>1</th>
						<th>2</th>
						<th>3</th>
						<th>4</th>
						<th>5</th>
						<th>6</th>
						<th>7</th>
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
		<div id = "regist-menu">
			<div id = "regist-text">
				정기권번호(자동발생) : 
			</div>
			<div id = "regist-value">
				<input readonly type = "text" name = "tno" value = "${parkingJoin.tno}">
			</div>
			<div>
				차량번호 : <input type = "text" name = "carno">
			</div>
			<div>
				차주전화 : <input type = "text" name = "phone">
			</div>
			<div>
				주차등급(M/Y) : 
				<select	name = "grade">
					<option value = ""></option>
					<option value = "M">월회원</option>
					<option value = "Y">연회원</option>
				</select>
			</div>
			<div>
				정기권상태 (Y/N) : 
				<select name = "tstat">
					<option value = "Y">Y</option>
					<option value = "N">N</option>
				</select>
			</div>
			<div>
				시작일 : <input type = "date" name = "startDate">
			</div>
			<div>
				종료일 : <input type = "date" name = "endDate">
			</div>
			<input type = "submit" value = "가입">
			${ctxPath = pageContext.request.contextPath; ''}
			<input type = "submit" formaction="${ctxPath }/list.do" value = "조회">
		</div>
	</div>
</body>
</html>