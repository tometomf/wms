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
		<form action="update.do" method="post">
			<div id="regist">
				<div id="regist-menu">
					<div id="regist-text">品目コード</div>
					<div id="regist-value">
						<input readonly type="text" name="itemCd" value="${item.itemCd}">
					</div>
					<div id="regist-text">品目名</div>
					<div id="regist-value">
						<input type="text" name="itemNm" value="${item.itemNm}">
					</div>
					<div id="regist-text">規格</div>
					<div id="regist-value">
						<input type="text" name="spec" value="${item.spec}">
					</div>
					<div id="regist-text">分類</div>
					<div id="regist-value">
						<input type="text" name="itemGubun" value="${item.itemGubun}">
					</div>
					<div id="regist-text">単位</div>
					<div id="regist-value">
						<input type="text" name="unit" value="${item.unit}">
					</div>
					<div id="regist-text">使用有無</div>
					<div id="regist-value">
						<select name="useYn">
							<option value="Y" ${item.useYn == 'Y' ? 'selected' : ''}>利用あり</option>
							<option value="N" ${item.useYn == 'N' ? 'selected' : ''}>利用なし</option>
						</select>
					</div>
					<div id="regist-text">メーカー</div>
					<div id="regist-value">
						<input type="text" name="manufacturer" value="${item.manufacturer}">
					</div>
					<div id="regist-text">受注基準単価</div>
					<div id="regist-value">
						<input type="text" name="storePrice" value="${item.storePrice}">
					</div>
					<div id="regist-text">出庫基準単価</div>
					<div id="regist-value">
						<input type="text" name="shipmentPrice" value="${item.shipmentPrice}">
					</div>
				</div>
				<div id="regist-button">
					<div>
						<input type="submit" value="修正"> <a
							href="delete.do?itemCd=${item.itemCd}" id="button-link">削除</a>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>