<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/wms.css">
</head>

<body>
	${ctxPath = pageContext.request.contextPath; ''}
	<%@ include file="/nav.jsp"%>
	<div id="main-content">
		<div id="wms-title">品目現況</div>
			<form action="${ctxPath}/item/list.do">
				<div id="search-bar" style="display: flex;">
					<div>
						<label for="start-date">品目コード</label> 
						<input type="text" id="itemCd" name = "itemCd" value = "${itemCd}">  
					</div>
					<div>
						<button id = "search-button" type = "submit">照会</button>
					</div>
				</div>
			</form>
		<div id="result-table" style= "height: 780px; overflow-y: auto">
			<table>
				<thead>
					<tr>
						<th width = "4%" style = "position: sticky; top: 0; z-index: 2">No.</th>
						<th width = "8%" style = "position: sticky; top: 0; z-index: 2">品目コード</th>
						<th width = "14%" style = "position: sticky; top: 0; z-index: 2">品目名</th>
						<th width = "12%" style = "position: sticky; top: 0; z-index: 2">規格</th>
						<th width = "12%" style = "position: sticky; top: 0; z-index: 2">分類</th>
						<th width = "8%" style = "position: sticky; top: 0; z-index: 2">単位</th>
						<th width = "8%" style = "position: sticky; top: 0; z-index: 2">使用有無</th>
						<th width = "14%" style = "position: sticky; top: 0; z-index: 2">メーカー</th>
						<th width = "10%" style = "position: sticky; top: 0; z-index: 2">受注基準単価</th>
						<th width = "10%" style = "position: sticky; top: 0; z-index: 2">出庫基準単価</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${itemList}">
					    <tr ondblclick="rowClicked(this)">
					        <td>${item.no}</td>
					        <td>${item.itemCd}</td>
					        <td>${item.itemNm}</td>
					        <td>${item.spec}</td>
					        <td>${item.itemGubun}</td>
					        <td>${item.unit}</td>
					        <td>${item.useYn}</td>
					        <td>${item.manufacturer}</td>
					        <td>${item.storePrice}</td>
				            <td>${item.shipmentPrice}</td>
					    </tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>

<script>
	// OnRowDblClick Event
  	function rowClicked(row) {
		var cellValue = row.cells[1].innerText; 
		location.href = "update.do?itemCd=" + cellValue;　//맨앞 셀의 값을 가져와 update로 이동. 最初のセルの値(コード)をセットしてupdateに移動 
  	}
</script>