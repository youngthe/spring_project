<%--
  Created by IntelliJ IDEA.
  User: whdud
  Date: 2022-07-10
  Time: 오후 3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>로그인 완료</td>
    </tr>
    <tr>
        <td><input type="button" onclick="location.href='/user_info'"  value="관리자 페이지"></td>
    </tr>
    <tr>
        <td><input type="button" onclick="location.href='/community'"  value="게시판 페이지"></td>
    </tr>
</table>
<form action="/main" method = post>

    <input type="submit" value = "로그아웃">

</form>
<img src=${pageContext.request.contextPath}"/resources/image/test.jpg">

</body>
</html>


