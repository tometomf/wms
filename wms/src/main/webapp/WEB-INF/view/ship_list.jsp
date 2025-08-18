<%@page import="java.util.List"%>
<%@page import="ship.model.ShipViewModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出庫現況</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">
</head>

<body>
    <%@ include file="/nav.jsp"%>
    <div id="main-content">
        <div id="wms-title">出庫現況</div>
        <div id="search-bar" style="display: flex;"></div>

        <div id="result-table">
            <table>
                <thead>
                    <tr>
                        <th width="4%">No.</th>
                        <th width="6%">出庫番号</th>
                        <th width="12%">出庫名</th>
                        <th width="8%">出庫品目</th>
                        <th width="12%">出庫品目名</th>
                        <th width="5%">数量</th>
                        <th width="6%">出庫価格</th>
                        <th width="6%">出庫部署</th>
                        <th width="6%">出庫社員</th>
                        <th width="8%">出庫日</th> 
                        <th>備考</th>
                    </tr>
                </thead>

                <tbody>
                    <c:if test="${not empty shipList}">
                        <c:forEach var="ship" items="${shipList}">
                            <tr ondblclick="rowClicked(this)">
                                <td>${ship.no}</td>
                                <td>${ship.shipNo}</td>
                                <td style="text-align: left; padding-left: 5px">${ship.shipNm}</td>
                                <td>${ship.itemCd}</td>
                                <td style="text-align: left; padding-left: 5px">${ship.itemNm}</td>
                                <td style="text-align: right; padding-right: 5px">${ship.shipQty}</td>
                                <td style="text-align: right; padding-right: 5px">${ship.shipPrice}</td>
                                <td>${ship.shipDept}</td>
                                <td>${ship.shipUser}</td>
                                <td><fmt:formatDate value="${ship.regYmd}" pattern="yyyy-MM-dd"/></td>
                                <td style="text-align: left; padding-left: 5px">${ship.descr}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>

<script>
    function rowClicked(row) {
        var shipNo = row.cells[1].innerText.trim();
        location.href = "update.do?shipNo=" + shipNo;
    }
</script>