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
	${ctxPath = pageContext.request.contextPath ; ''}
	<div id="sidebar">
		<div id="title">物流管理</div>		<!-- 물류관리 -->
		<div id="menu-section">
			<div id="mid_title">ユーザー管理</div> <!-- 사용자 관리 -->
			<ul id = "mid_ui">
				<li><a href="${ctxPath}/user/write.do">ユーザー登録</a></li> <!-- 사용자 등록 -->
				<li><a href="${ctxPath}/user/list.do">ユーザー現況</a></li> <!-- 사용자 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">品目</div> <!-- 품목 -->
			<ul id = "mid_ui">
				<li><a href="${ctxPath}/item/write.do">品目登録</a></li> <!-- 품목 등록 -->
				<li><a href="${ctxPath}/item/list.do">品目現況</a></li> <!-- 품목 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">倉庫</div> <!-- 창고 -->
			<ul>
				<li><a href="${ctxPath}/ware/write.do">倉庫登録</a></li> <!-- 창고 등록 -->
				<li><a href="${ctxPath}/ware/list.do">倉庫別在庫現況</a></li> <!-- 창고별 재고 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">在庫</div> <!-- 재고 -->
			<ul>
				<li><a href="${ctxPath}/stock/write.do">在庫登録</a></li> <!-- 재고 등록 -->
				<li><a href="${ctxPath}/stock/list.do">入庫現況</a></li> <!-- 재고 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">入庫</div> <!-- 입고 -->
			<ul>
				<li><a href="${ctxPath}/store/write.do">入庫登録</a></li> <!-- 입고 등록 -->
				<li><a href="${ctxPath}/store/list.do">入庫現況</a></li> <!-- 입고 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">受注</div> <!-- 수주 -->
			<ul>
				<li><a href="${ctxPath}/order/write.do">受注登録</a></li> <!-- 수주 등록 -->
				<li><a href="${ctxPath}/order/list.do">受注現況</a></li> <!-- 수주 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">出庫</div> <!-- 출고 -->
			<ul>
				<li><a href="${ctxPath}/ship/write.do">出庫登録</a></li> <!-- 출고 등록 -->
				<li><a href="${ctxPath}/ship/list.do">出庫現況</a></li> <!-- 출고 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="mid_title">物流</div> <!-- 물류 -->
			<ul>
				<li><a href="#">物流現況</a></li> <!-- 물류 현황 -->
			</ul>
		</div>
	</div>
</body>
</html>