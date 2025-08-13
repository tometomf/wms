<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出庫登録</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
    <%@ include file="/nav.jsp"%>
    <div id="main-content">
        <div id="wms-title">出庫登録</div>
        <div id="search-bar" style="display: flex;"></div>

        <form action="insert.do" method="post" onsubmit="return validateForm();">
            <div id="regist">
                <div id="regist-menu">

                    <div id="regist-text">出庫番号</div>
                    <div id="regist-value">
                        <input readonly type="text" name="shipNo" value="${shipNo.shipNo}">
                    </div>

                    <div id="regist-text">出庫名</div>
                    <div id="regist-value">
                        <input type="text" name="shipNm">
                    </div>

                    <div id="regist-text">出庫部署</div>
                    <div id="regist-value">
                        <input type="text" name="shipDept">
                    </div>

                    <div id="regist-text">担当者</div>
                    <div id="regist-value">
                        <input type="text" name="shipUser">
                    </div>

                    <div id="regist-text">品目コード</div>
                    <div id="regist-value">
                        <input type="text" name="itemCd">
                    </div>

                    <div id="regist-text">出庫単価</div>
                    <div id="regist-value">
                        <input type="number" name="shipPrice" step="1">
                    </div>

                    <div id="regist-text">出庫数量</div>
                    <div id="regist-value">
                        <input type="number" name="shipQty" step="1">
                    </div>

                    <div id="regist-text">備考</div>
                    <div id="regist-value">
                        <textarea name="descr" rows="4" cols="56"></textarea>
                    </div>

                    <div id="regist-text">出庫可否</div>
                    <div id="regist-value">
                        <select name="shipYn">
                            <option value="Y">出庫可</option>
                            <option value="N">出庫不可</option>
                        </select>
                    </div>

                </div>

                <div id="regist-button">
                    <div>
                        <input type="submit" value="保存">
                    </div>
                </div>
            </div>
        </form>
        
        <script>
function validateForm() {
    const requiredFields = [
        { name: "shipNm", label: "출고명" },
        { name: "shipDept", label: "출고부서" },
        { name: "shipUser", label: "담당자" },
        { name: "itemCd", label: "품목코드" },
        { name: "shipPrice", label: "출고단가" },
        { name: "shipQty", label: "출고수량" }
    ];

    for (let field of requiredFields) {
        let value = document.getElementsByName(field.name)[0].value.trim();
        if (!value) {
            alert(field.label + "을(를) 입력하세요.");
            document.getElementsByName(field.name)[0].focus();
            return false; // 제출 중단
        }
    }
    return true; 
}
</script>

    </div>
</body>
</html>