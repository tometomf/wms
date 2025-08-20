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
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">倉庫登録</div>
		<div id="search-bar" style="display: flex;">
		</div>
		<form action = "insert.do" method = "post" onsubmit="return validateForm();">
			<div id = "regist">
				<div id = "regist-menu">
					<div id = "regist-text">倉庫コード</div>
					<div id = "regist-value">
						<input readonly type = "text" name = "warecd" value = "${wareCd.wareCd}">
					</div>
					<div id = "regist-text">倉庫名</div>
					<div id = "regist-value">
						<input type = "text" name = "warenm">
					</div>
					<div id = "regist-text">倉庫区分</div>
					<div id = "regist-value">
						<input type = "text" name = "waregb">
					</div>
					<div id = "regist-text">使用可否</div>
					<div id = "regist-value">
						<select	name = "useyn">
							<option value="Y">利用あり</option>
							<option value="N">利用なし</option>
						</select>
					</div>
					<div id = "regist-text">備考</div>
					<div id = "regist-value">
						<textarea name="descr" rows="4" cols="56"></textarea>
					</div>
				</div>
				<div id = "regist-button">
					<div>
						<input type = "submit" value = "保存">
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>

<script>

	function validateForm() {
	    var requiredFields = [
	        { name: "warenm", label: "倉庫名" },
	        { name: "waregb", label: "倉庫区分" }
	    ];
	
	    for (let field of requiredFields) {
	        let value = document.getElementsByName(field.name)[0].value.trim();
	        if (!value) {
	            alert(field.label + "を入力してください");
	            document.getElementsByName(field.name)[0].focus();
	            return false; 
	        }
	    }
	    return true; 
	}
</script>