<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table border="1">
  <tr>
    <th>수주번호</th>
    <th>수주명</th>
    <th>담당부서</th>
    <th>담당사원</th>
    <th>수주구분</th>
    <th>비고</th>
    <th>등록일</th>
    <th>수정일</th>
    <th>수주 완료 유무</th>
  </tr>
  <c:forEach var="order" items="${orderList}">
    <tr>
      <td>${order.orderNo}</td>
      <td>${order.orderNm}</td>
      <td>${order.orderDept}</td>
      <td>${order.orderUser}</td>
      <td>${order.orderGubun}</td>
      <td>${order.descr}</td>
      <td>${order.regYmd}</td>
      <td>${order.updYmd}</td>
      <td>${order.storeYn}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>