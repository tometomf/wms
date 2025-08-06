<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>출고 등록</title>
</head>
<body>
    <h2>출고 등록</h2>

    <form action="regist.do" method="post">
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>출고번호</th>
                <td>
                    <input type="text" name="shipNo" value="${nextShipNo}" readonly />
                </td>
            </tr>
            <tr>
                <th>출고명</th>
                <td>
                    <input type="text" name="shipNm" required />
                </td>
            </tr>
            <tr>
                <th>담당부서</th>
                <td>
                    <input type="text" name="shipDept" required />
                </td>
            </tr>
            <tr>
                <th>담당사원</th>
                <td>
                    <input type="text" name="shipUser" required />
                </td>
            </tr>
            <tr>
                <th>비고</th>
                <td>
                    <textarea name="descr" rows="4" cols="40"></textarea>
                </td>
            </tr>
        </table>

        <br/>
        <input type="submit" value="登録" />
    </form>
</body>
</html>