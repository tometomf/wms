<%@page import="java.util.List"%>
<%@page import="user.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <table border="1">
        <thead>
            <tr>
                <th>사원번호</th>
                <th>사원이름</th>
                <th>부서명</th>
                <th>휴대전화</th>
                <th>이메일</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<User> userList = (List<User>) request.getAttribute("userList");
            	if (userList != null && !userList.isEmpty()) {
                    for (User user : userList) {
            %>
                <tr>
                    <td><%= user.getUserCd() %></td>
                    <td><%= user.getUserNm() %></td>
                    <td><%= user.getDeptNm() %></td>
                    <td><%= user.getPhone() %></td>
                    <td><%= user.getEmail() %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="7">조회된 데이터가 없습니다.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>