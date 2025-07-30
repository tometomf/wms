<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>

/* 사이드바 스타일 */
#sidebar {
  width: 270px;
  height: 100vh;
  background-color: #2c3e50;
  color: white;
  padding: 15px;
  position: fixed;
  top: 0;
  left: 0;
  overflow-y: auto;
  box-sizing: border-box;
}

/* 타이틀 (상단) */
#title {
  font-size: 22px;
  font-weight: bold;
  text-align: center;
  color: #ecf0f1;
  margin-bottom: 20px;
}

/* 메뉴 섹션 */
#menu-section {
  margin-bottom: 15px;
}

/* 메뉴 그룹 타이틀 */
#menu-section > #menu-title {
  font-size: 15px;
  font-weight: bold;
  color: #ffffff;
  margin-bottom: 8px;
  padding-left: 20px;
  position: relative;
  cursor: pointer;
}

#menu-section > #menu-title::before {
  content: "📁";
  position: absolute;
  left: 0;
}

/* 하위 메뉴 */
#menu-section ul {
  list-style: none;
  margin: 0;
  padding-left: 25px;
}

#menu-section li {
  font-size: 14px;
  margin: 4px 0;
  position: relative;
  padding-left: 15px;
}

#menu-section li::before {
  content: "●";
  position: absolute;
  left: 0;
  color: #bdc3c7;
}

/* 링크 */
#menu-section a {
  color: #bdc3c7;
  text-decoration: none;
}

#menu-section a:hover {
  color: #1abc9c;
  text-decoration: underline;
}

</style>
<body>
	${ctxPath = pageContext.request.contextPath ; ''}
	<div id="sidebar">
		<div id="title">物流管理</div>		<!-- 물류관리 -->
		<div id="menu-section">
			<div id="menu-title">&nbsp;ユーザー管理</div> <!-- 사용자 관리 -->
			<ul>
				<li><a href="${ctxPath}/user/write.do">ユーザー登録</a></li> <!-- 사용자 등록 -->
				<li><a href="${ctxPath}/user/list.do">ユーザー現況</a></li> <!-- 사용자 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="menu-title">&nbsp;品目</div> <!-- 품목 -->
			<ul>
				<li><a href="${ctxPath}/item/write.do">品目登録</a></li> <!-- 품목 등록 -->
				<li><a href="${ctxPath}/item/list.do">品目現況</a></li> <!-- 품목 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="menu-title">&nbsp;倉庫</div> <!-- 창고 -->
			<ul>
				<li><a href="${ctxPath}/ware/write.do">倉庫登録</a></li> <!-- 창고 등록 -->
				<li><a href="${ctxPath}/ware/list.do">倉庫別在庫現況</a></li> <!-- 창고별 재고 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="menu-title">&nbsp;在庫</div> <!-- 재고 -->
			<ul>
				<li><a href="${ctxPath}/stock/write.do">在庫登録</a></li> <!-- 재고 등록 -->
				<li><a href="${ctxPath}/stock/list.do">在庫現況</a></li> <!-- 재고 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="menu-title">&nbsp;入庫</div> <!-- 입고 -->
			<ul>
				<li><a href="${ctxPath}/store/write.do">入庫登録</a></li> <!-- 입고 등록 -->
				<li><a href="${ctxPath}/store/list.do">入庫現況</a></li> <!-- 입고 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="menu-title">&nbsp;受注</div> <!-- 수주 -->
			<ul>
				<li><a href="${ctxPath}/order/write.do">受注登録</a></li> <!-- 수주 등록 -->
				<li><a href="${ctxPath}/order/list.do">受注現況</a></li> <!-- 수주 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="menu-title">&nbsp;出庫</div> <!-- 출고 -->
			<ul>
				<li><a href="${ctxPath}/ship/write.do">出庫登録</a></li> <!-- 출고 등록 -->
				<li><a href="${ctxPath}/ship/list.do">出庫現況</a></li> <!-- 출고 현황 -->
			</ul>
		</div>
		<div id="menu-section">
			<div id="menu-title">&nbsp;物流</div> <!-- 물류 -->
			<ul>
				<li><a href="#">物流現況</a></li> <!-- 물류 현황 -->
			</ul>
		</div>
	</div>
</body>
</html>