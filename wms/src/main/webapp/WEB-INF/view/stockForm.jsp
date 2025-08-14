<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫登録</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">在庫登録</div>
		<div id="search-bar" style="display: flex;"></div>
		<form action="insert.do" method="post" novalidate>
			<div id="regist">
				<div id="regist-menu">

					<div id="regist-text">在庫番号</div>
					<div id="regist-value">
						<input readonly type="text" name="stockNo"
							value="${stockNo.stock_No}">
					</div>

					<div id="regist-text">品目コード</div>
					<div id="regist-value">
						<input type="text" name="itemCd" required>
					</div>

					<div id="regist-text">在庫数量</div>
					<div id="regist-value">
						<input type="number" name="qty" required>
					</div>

					<div id="regist-text">倉庫コード</div>
					<div id="regist-value">
						<input type="text" name="wareCd" required>
					</div>

					<div id="regist-text">登録日</div>
					<div id="regist-value">
						<input type="date" name="regYmd" required>
					</div>

					<div id="regist-text">備考</div>
					<div id="regist-value">
						<textarea name="descr" rows="4" cols="56"></textarea>
					</div>
				</div>
				<div id="regist-button">
					<div>
						<input type="submit" value="貯蔵">
					</div>
				</div>
			</div>
		</form>
	</div>
	
<!-- 	에러 메세지 알림창 / エラーメッセージ通知ウィンドウ -->
	<script>
		window.onload = function() {
			<c:choose>
			<c:when test="${errors.itemCd}">
			alert("品目コードを入力してください。");
			</c:when>
			<c:when test="${errors.qty}">
			alert("在庫数量を入力してください。");
			</c:when>
			<c:when test="${errors.wareCd}">
			alert("倉庫コードを入力してください。");
			</c:when>
			<c:when test="${errors.regYmd}">
			alert("登録日を入力してください。");
			</c:when>
			<c:when test="${errors.invalidNumber}">
			alert("数量または在庫番号は数字で入力してください。");
			</c:when>
			<c:when test="${errors.invalidQtyNegative}">
			alert("数量は0以上で入力してください。");
			</c:when>
			<c:when test="${errors.invalidDate}">
			alert("登録日の日付形式が正しくありません。");
			</c:when>
			<c:when test="${errors.insertError}">
			alert("登録中にエラーが発生しました。もう一度お試しください。");
			</c:when>
			</c:choose>
		};
	</script>
</body>
</html>
