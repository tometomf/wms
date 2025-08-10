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
					<div id="regist-text">품목코드</div>
					<div id="regist-value">
						<input readonly type="text" name="itemCd" value="${item.itemCd}">
					</div>
					<div id="regist-text">품목명</div>
					<div id="regist-value">
						<input type="text" name="itemNm" value="${item.itemNm}">
					</div>
					<div id="regist-text">규격</div>
					<div id="regist-value">
						<input type="text" name="spec" value="${item.spec}">
					</div>
					<div id="regist-text">품목구분</div>
					<div id="regist-value">
						<input type="text" name="itemGubun" value="${item.itemGubun}">
					</div>
					<div id="regist-text">단위</div>
					<div id="regist-value">
						<input type="text" name="unit" value="${item.unit}">
					</div>
					<div id="regist-text">사용여부</div>
					<div id="regist-value">
						<select name="useYn">
							<option value="Y" ${item.useYn == 'Y' ? 'selected' : ''}>利用あり</option>
							<option value="N" ${item.useYn == 'N' ? 'selected' : ''}>利用なし</option>
						</select>
					</div>
					<div id="regist-text">제조사</div>
					<div id="regist-value">
						<input type="text" name="manufacturer" value="${item.manufacturer}">
					</div>
					<div id="regist-text">수주기준단가</div>
					<div id="regist-value">
						<input type="text" name="storePrice" value="${item.storePrice}">
					</div>
					<div id="regist-text">출고기준단가</div>
					<div id="regist-value">
						<input type="text" name="shipmentPrice" value="${item.shipmentPrice}">
					</div>
				</div>
				<div id="regist-button">
					<div>
						<input type="submit" value="수정"> <a
							href="delete.do?itemCd=${item.itemCd}" id="button-link">삭제</a>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>