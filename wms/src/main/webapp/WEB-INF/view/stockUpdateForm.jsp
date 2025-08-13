<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">
</head>
<body>
<%@ include file="/nav.jsp" %>

<div id="main-content">
    <div id="wms-title">在庫修正</div>
    <form action="update.do" method="post">
        <div id="regist">
            <div id="regist-menu">
                <div id="regist-text">재고번호</div>
                <div id="regist-value">
                    <input readonly type="text" name="stockNo" value="${stock.stock_No}">
                </div>

                <div id="regist-text">품목코드</div>
                <div id="regist-value">
                    <input type="text" name="itemCd" value="${stock.item_Cd}">
                </div>

                <div id="regist-text">재고수량</div>
                <div id="regist-value">
                    <input type="number" name="qty" value="${stock.qty}">
                </div>

                <div id="regist-text">창고코드</div>
                <div id="regist-value">
                    <input type="text" name="wareCd" value="${stock.ware_Cd}">
                </div>

                <div id="regist-text">등록일</div>
                <div id="regist-value">
                    <input type="date" name="regYmd" value="${stock.reg_Ymd}">
                </div>
            </div>
            <div id="regist-button">
                <div>
                    <input type="submit" value="수정">
                    <a href="delete.do?stockNo=${stock.stock_No}" id="button-link">삭제</a>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
