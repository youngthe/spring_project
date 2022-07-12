<%--
  Created by IntelliJ IDEA.
  User: whdud
  Date: 2022-07-11
  Time: 오후 7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원 정보 페이지</title>
</head>
<body>
<table>
    <c:forEach var="user" items="${users}" varStatus="status">
        <tr>
            <td>${user.num}</td><td>${user.id}</td>
            <td>
            <input type="button" value="x" onclick="location.href='/user_delete/${user.num}'">
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
