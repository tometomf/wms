<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>出庫現況</title>
  <style>
    table { border-collapse: collapse; width: 100%; margin-top: 30px; }
    th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
    th { background-color: #f2f2f2; }
    h1 { margin-bottom: 20px; }
  </style>
</head>
<body>

<h1>出庫現況</h1>

<table>
  <thead>
    <tr>
      <th>出荷番号</th>
      <th>出荷名</th>
      <th>部署</th>
      <th>担当者</th>
      <th>説明</th>
      <th>登録日</th>
      <th>修正日</th>
      <th>出庫可否</th>
      <th>品目コード</th>
      <th>単価</th>
      <th>数量</th>
      <th>区分</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="row" items="${shipList}">
      <tr>
        <td>${row.shipNo}</td>
        <td>${row.shipNm}</td>
        <td>${row.shipDept}</td>
        <td>${row.shipUser}</td>
        <td>${row.descr}</td>
        <td>${row.regYmd}</td>
        <td>${row.updYmd}</td>
        <td>${row.shipYn}</td>
        <td>${row.itemCd}</td>
        <td>${row.shipPrice}</td>
        <td>${row.shipQty}</td>
        <td>${row.shipGubun}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>
