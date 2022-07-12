<%--
  Created by IntelliJ IDEA.
  User: whdud
  Date: 2022-07-12
  Time: 오후 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>커뮤니티 페이지</title>
    <table>
        <c:forEach var="list" items="${community_list}" varStatus="status">
            <tr>
                <td>${list.id}</td><td><a href="/community/detail/${list.id}">${list.title}</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td><input type="button" onclick="location.href='/community/write'"  value="글 작성"></td>
        </tr>
    </table>
</head>
<body>

</body>
</html>
