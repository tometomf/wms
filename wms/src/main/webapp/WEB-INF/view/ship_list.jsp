<%@page import="java.util.List"%>
<%@page import="ship.model.ShipViewModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <div id="search-bar" style="display: flex;">
            <div>
                <button id="search-button">조회</button>
            </div>
        </div>
        <div id="result-table">
            <table>
                <thead>
                    <tr>
                        <th>出庫番号</th>
                        <th>出庫名</th>
                        <th>出庫部署</th>
                        <th>担当者</th>
                        <th>品目コード</th>
                        <th>出庫単価</th>
                        <th>出庫数量</th>
                        <th>備考</th>
                        <th>登録日</th>
                        <th>修正日</th>
                        <th>出庫可否</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    List<ShipViewModel> shipList = (List<ShipViewModel>) request.getAttribute("shipList");
                    if (shipList != null && !shipList.isEmpty()) {
                        for (ShipViewModel ship : shipList) {
                    %>
                        <tr ondblclick="rowClicked(this)">
                            <td><%=ship.getShipNo()%></td>
                            <td><%=ship.getShipNm()%></td>
                            <td><%=ship.getShipDept()%></td>
                            <td><%=ship.getShipUser()%></td>
                            <td><%=ship.getItemCd()%></td>
                            <td><%=ship.getShipPrice()%></td>
                            <td><%=ship.getShipQty()%></td>
                            <td><%=ship.getDescr()%></td>
                            <td><%=ship.getRegYmd()%></td>
                            <td><%=ship.getUpdYmd()%></td>
                            <td><%=ship.getShipYn()%></td>
                        </tr>
                    <%
                        }
                    }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>

<script>
    function rowClicked(row) {
        var shipNo = row.cells[0].innerText;
        location.href = "update.do?shipNo=" + shipNo;
    }
</script>