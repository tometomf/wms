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
		<div id="wms-title">品目登録</div>
		<div id="search-bar" style="display: flex;"></div>
		<form action="insert.do" method="post">
			<div id="regist">
				<div id="regist-menu">
					<div id="regist-text">品目コード</div>
					<div id="regist-value">
						<input readonly type="text" name="itemCd" value="${itemCd.itemCd}">
					</div>
					<div id="regist-text">品目名</div>
					<div id="regist-value">
						<input type="text" name="itemNm">
					</div>
					<div id="regist-text">規格</div>
					<div id="regist-value">
						<input type="text" name="spec">
					</div>
					<div id="regist-text">分類</div>
					<div id="regist-value">
						<input type="text" name="itemGubun">
					</div>
					<div id="regist-text">単位</div>
					<div id="regist-value">
						<input type="text" name="unit">
					</div>
					<div id="regist-text">使用有無</div>
					<div id="regist-value">
						<select name="useYn">
							<option value="Y">使用</option>
							<option value="N">未使用</option>
						</select>
					</div>
					<div id="regist-text">メーカー</div>
					<div id="regist-value">
						<input type="text" name="manufacturer">
					</div>
					<div id="regist-text">受注基準単価</div>
					<div id="regist-value">
						<input type="number" name="storePrice">
					</div>
					<div id="regist-text">出庫基準単価</div>
					<div id="regist-value">
						<input type="number" name="shipmentPrice">
					</div>
				</div>
				<div id="regist-button">
					<div>
						<input type="submit" value="保存">
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>