<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%
    Date today = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String todayStr = sdf.format(today);  // ▶ yyyy-MM-dd 形式の今日の日付(형식의 오늘 날짜)
%>

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

                    <div id="regist-text">出庫品目</div>
                    <div id="regist-value">
                        <select name="itemCd">
                            <option value=""></option>
                            <c:forEach var="itemList" items="${itemList}">
                                <option value="${itemList.itemCd}">${itemList.itemNm}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="regist-text">出庫数量</div>
                    <div id="regist-value">
                        <input type="number" name="shipQty" step="1">
                    </div>

                    <div id="regist-text">出庫単価</div>
                    <div id="regist-value">
                        <input type="number" name="shipPrice" step="1">
                    </div>

                    <div id="regist-text">出庫担当部署</div>
                    <div id="regist-value">
                        <input type="text" name="shipDept">
                    </div>

                    <div id="regist-text">出庫担当者</div>
                    <div id="regist-value">
                        <input type="text" name="shipUser">
                    </div>

                    <!-- ▼ 今日の日付の自動表示(오늘 날짜 자동 표시) -->
                    <div id="regist-text">出庫日</div>
                    <div id="regist-value">
                        <input type="date" name="regYmd" value="<%= todayStr %>">
                    </div>
                  

                    <div id="regist-text">備考</div>
                    <div id="regist-value">
                        <textarea name="descr" rows="4" cols="56"></textarea>
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

<script>
function validateForm() {
    const requiredFields = [
        { name: "shipNm",    label: "出庫名" },
        { name: "shipDept",  label: "出庫部署" },
        { name: "shipUser",  label: "担当者" },
        { name: "itemCd",    label: "品目コード" },
        { name: "shipPrice", label: "出庫単価" },
        { name: "shipQty",   label: "出庫数量" },
        { name: "regYmd",    label: "出庫日" } 
    ];

    for (let field of requiredFields) {
        let el = document.getElementsByName(field.name)[0];
        let value = (el && el.value) ? el.value.trim() : "";
        if (!value) {
            alert(field.label + "を入力してください");
            if (el) el.focus();
            return false;
        }
    }
    return true;
}
</script>