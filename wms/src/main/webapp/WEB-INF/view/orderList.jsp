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
    <th>���ֹ�ȣ</th>
    <th>���ָ�</th>
    <th>���μ�</th>
    <th>�����</th>
    <th>���ֱ���</th>
    <th>���</th>
    <th>�����</th>
    <th>������</th>
    <th>���� �Ϸ� ����</th>
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