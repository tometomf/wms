<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
	margin: 0;
	font-family: Arial, sans-serif;
}

/* 왼쪽 고정 사이드바 */
#sidebar {
	width: 230px;
	height: 100vh;
	background-color: #2c3e50;
	color: white;
	padding: 15px;
	position: fixed;
	top: 0;
	left: 0;
	overflow-y: auto;
}

/* 상단 제목 */
#title {
	font-size: 25px;
	font-weight: bold;
	margin-bottom: 15px;
	text-align: center;
	color: #ecf0f1;
}

/* 중간 섹션 타이틀 (품목, 창고 등) */
#mid_title {
	font-size: 20px;
	font-weight: bold;
	margin-bottom: 5px;
	color: #ecf0f1;
}

/* 각 메뉴 섹션 */
#menu-section {
	margin-top: 20px;
	margin-bottom: 20px;
	margin-left: 10px;
}

/* 메뉴 리스트 */
#menu-section ul {
	list-style: none;
	padding-left: 12px;
	margin: 0;
}

/* 메뉴 항목 */
#menu-section li {
	margin-bottom: 3px;
}

/* 링크 스타일 */
#menu-section a {
	color: #bdc3c7;
	text-decoration: none;
	font-size: 15px;
}

#menu-section a:hover {
	color: #ffffff;
	text-decoration: underline;
}

#menu-section #mid_ui {
	margin-top: 10px;
}

/* 본문 내용 공간 확보 */
.content {
	margin-left: 250px;
	padding: 20px;
}
</style>
<body>
	<div id="sidebar">
		<div id="title">물류관리</div>
		<div id="menu-section">
			<div id="mid_title">품목</div>
			<ul id = "mid_ui">
				<li><a href="#">품목 등록</a></li>
				<li><a href="#">품목 현황</a></li>
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">창고</div>
			<ul>
				<li><a href="#">창고 등록</a></li>
				<li><a href="#">창고별 재고 현황</a></li>
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">재고</div>
			<ul>
				<li><a href="#">재고 등록</a></li>
				<li><a href="#">재고 현황</a></li>
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">입고</div>
			<ul>
				<li><a href="#">입고 등록</a></li>
				<li><a href="#">입고 현황</a></li>
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">수주</div>
			<ul>
				<li><a href="#">수주 등록</a></li>
				<li><a href="#">수주 현황</a></li>
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">출고</div>
			<ul>
				<li><a href="#">출고 등록</a></li>
				<li><a href="#">출고 현황</a></li>
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">물류</div>
			<ul>
				<li><a href="#">물류 현황</a></li>
			</ul>
		</div>
	</div>
</body>
</html>