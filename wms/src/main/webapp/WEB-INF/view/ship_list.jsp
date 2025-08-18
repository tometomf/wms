<%@page import="java.util.List"%>
<%@page import="ship.model.ShipViewModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                        <th width = "4%">No.</th>
                        <th width = "6%">出庫番号</th>
                        <th width = "12%">出庫名</th>
						<th width = "8%">出庫品目</th>
						<th width = "12%">出庫品目名</th>
						<th width = "5%">数量</th>
                        <th width = "6%">出庫価格</th>
                        <th width = "6%">出庫部署</th>
						<th width = "6%">出庫社員</th>
                        <th width = "9%">出庫完了有無</th>
                        <th>備考</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    List<ShipViewModel> shipList = (List<ShipViewModel>) request.getAttribute("shipList");
                    if (shipList != null && !shipList.isEmpty()) {
                        for (ShipViewModel ship : shipList) {
                    %>
                        <tr ondblclick="rowClicked(this)">
                            <td><%=ship.getNo()%></td>
                            <td><%=ship.getShipNo()%></td>
                            <td style = "text-align: left; padding-left: 5px"><%=ship.getShipNm()%></td>
                            <td><%=ship.getItemCd()%></td>
                            <td style = "text-align: left; padding-left: 5px"><%=ship.getItemNm()%></td>
                            <td  style = "text-align: right; padding-right: 5px"><%=ship.getShipQty()%></td>
                            <td  style = "text-align: right; padding-right: 5px"><%=ship.getShipPrice()%></td>
                            <td><%=ship.getShipDept()%></td>
                            <td><%=ship.getShipUser()%></td>
                            <td><%=ship.getShipYn()%></td>
                            <td style = "text-align: left; padding-left: 5px"><%=ship.getDescr()%></td>
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
        var shipNo = row.cells[1].innerText;
        location.href = "update.do?shipNo=" + shipNo;
    }
</script>