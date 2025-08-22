<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/wms.css">

</head>
<body>
	<%@ include file="nav.jsp" %>
	<div id="main-content">
		<div style = "display: flex; gap: 10px; flex-wrap: wrap;">
			<div style = "flex: 0 0 calc(50% - 5px);">
				<div id="wms-title">お知らせ</div>
				<div id="result-table" style = "height: 375px; overflow-y:auto; border: solid 1px #d3d3d3">
					<table style = "margin-top: 0px;">
						<thead>
							<tr>
								<th style = "width:30%;">タイトル</th>
								<th style = "width:70%;">内容</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div style = "flex: 0 0 calc(50% - 5px);">
				<div id="wms-title">倉庫別在庫現況</div>
				<div id="result-table" style = "height: 375px; overflow-y:auto; border: solid 1px #d3d3d3">
					<table style = "margin-top: 0px;">
						<thead>
							<tr>
								<th style = "width: 13%; position: sticky; top: 0; z-index: 2">品目コード</th>
								<th style = "position: sticky; top: 0; z-index: 2">品目名</th>
								<th style = "width: 13%; position: sticky; top: 0; z-index: 2">倉庫コード</th>
								<th style = "position: sticky; top: 0; z-index: 2">倉庫名</th>
								<th style = "position: sticky; top: 0; z-index: 2">数量</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${wareItemList}">
							    <tr>
							        <td align="center">${item.item_Cd}</td>
							        <td align="left">${item.item_Nm}</td>
							        <td align="center">${item.ware_Cd}</td>
							        <td align="left">${item.ware_Nm}</td>
							        <td align="right">${item.qty}</td>
							    </tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div style = "flex: 0 0 calc(50% - 5px);">
				<div id="wms-title">入庫予定目録</div>
				<div id="result-table" style = "height: 375px; overflow-y:auto; border: solid 1px #d3d3d3;">
					<table style = "margin-top: 0px;">
						<thead>
							<tr>
								<th style = "position: sticky; top: 0; z-index: 2">品目コード</th>
								<th style = "position: sticky; top: 0; z-index: 2">品目名</th>
								<th style = "position: sticky; top: 0; z-index: 2">入庫数量</th>
								<th style = "position: sticky; top: 0; z-index: 2">メーカー</th>
								<th style = "position: sticky; top: 0; z-index: 2">入庫単액</th>
								<th style = "position: sticky; top: 0; z-index: 2">入庫日</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="store" items="${storeExpectList}">
							    <tr>
							        <td align="center">${store.item_Cd}</td>
							        <td align="center">${store.item_Nm}</td>
							        <td align="center">${store.item_Qty}</td>
							        <td align="center">${store.manufacturer}</td>
							        <td align="center">${store.store_Price}</td>
							        <td align="center">${store.reg_Ymd}</td>
							    </tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div style = "flex: 0 0 calc(50% - 5px);">
				<div id="wms-title">출고예정목록</div>
				<div id="result-table" style = "height: 375px; overflow-y:auto; border: solid 1px #d3d3d3;">
					<table style = "margin-top: 0px;">
						<thead>
							<tr>
								<th style = "position: sticky; top: 0; z-index: 2">品目コード</th>
								<th style = "position: sticky; top: 0; z-index: 2">品目名</th>
								<th style = "position: sticky; top: 0; z-index: 2">出庫数量</th>
								<th style = "position: sticky; top: 0; z-index: 2">メーカー</th>
								<th style = "position: sticky; top: 0; z-index: 2">出庫単価</th>
								<th style = "position: sticky; top: 0; z-index: 2">出庫日</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ship" items="${shipExpectList}">
							    <tr>
							        <td align="center">${ship.item_Cd}</td>
							        <td align="center">${ship.item_Nm}</td>
							        <td align="center">${ship.ship_Qty}</td>
							        <td align="center">${ship.manufacturer}</td>
							        <td align="center">${ship.ship_Price}</td>
							        <td align="center">${ship.reg_Ymd}</td>
							    </tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>