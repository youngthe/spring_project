<%--
  Created by IntelliJ IDEA.
  User: whdud
  Date: 2022-07-10
  Time: 오전 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/deleteMember" method = post>
        <table>
            <tr>
                <td>삭제 할 ID</td><td><input type = "text" name = "id" ></td>
            </tr>
            <tr>
                <td><input type = "submit" value = "삭제하기"></td>
            </tr>
        </table>
    </form>
</body>
</html>
