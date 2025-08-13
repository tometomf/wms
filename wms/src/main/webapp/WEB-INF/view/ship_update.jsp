<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出庫修正</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">出庫修正</div>

		<form action="update.do" method="post" onsubmit="return validateForm();">
			<div id="regist">
				<div id="regist-menu">

					<div id="regist-text">出庫番号</div>
					<div id="regist-value">
						<input readonly type="text" name="shipNo" value="${ship.shipNo}">
					</div>

					<div id="regist-text">出庫名</div>
					<div id="regist-value">
						<input type="text" name="shipNm" value="${ship.shipNm}">
					</div>

					<div id="regist-text">品目コード</div>
					<div id="regist-value">
						<input type="text" name="itemCd" value="${ship.itemCd}">
					</div>

					<div id="regist-text">出庫単価</div>
					<div id="regist-value">
						<input type="number" name="shipPrice" value="${ship.shipPrice}">
					</div>

					<div id="regist-text">出庫数量</div>
					<div id="regist-value">
						<input type="number" name="shipQty" value="${ship.shipQty}">
					</div>

					<div id="regist-text">出庫部署</div>
					<div id="regist-value">
						<input type="text" name="shipDept" value="${ship.shipDept}">
					</div>

					<div id="regist-text">担当者</div>
					<div id="regist-value">
						<input type="text" name="shipUser" value="${ship.shipUser}">
					</div>

					<div id="regist-text">備考</div>
					<div id="regist-value">
						<textarea name="descr" rows="4" cols="56">${ship.descr}</textarea>
					</div>

					<div id="regist-text">出庫可否</div>
					<div id="regist-value">
						<select name="shipYn">
							<option value="Y" ${ship.shipYn == 'Y' ? 'selected' : ''}>利用あり</option>
							<option value="N" ${ship.shipYn == 'N' ? 'selected' : ''}>利用なし</option>
						</select>
					</div>

				</div>

				<div id="regist-button">
					<div>
						<input type="submit" value="修整">
						<a href="delete.do?shipNo=${ship.shipNo}" id="button-link">削除</a>
					</div>
				</div>
			</div>
		</form>

		<script>
		function validateForm() {
		    const requiredFields = [
		        { name: "shipNm", label: "出庫名" },
		        { name: "itemCd", label: "品目コード" },
		        { name: "shipPrice", label: "出庫単価" },
		        { name: "shipQty", label: "出庫数量" },
		        { name: "shipDept", label: "出庫部署" },
		        { name: "shipUser", label: "担当者" }
		    ];

		    for (let field of requiredFields) {
		        const el = document.getElementsByName(field.name)[0];
		        if (!el || el.value.trim() === "") {
		            alert(field.label + "を入力してください");
		            if (el) el.focus();
		            return false; // 提出中断
		        }
		    }
		    return true; // 通過すれば提出
		}
		</script>
	</div>
</body>
</html>