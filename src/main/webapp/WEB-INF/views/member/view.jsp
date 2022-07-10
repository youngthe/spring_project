<%@page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<table>

<c:forEach var="list" items="${list}" varStatus="status">
    <tr>
        <td>${list.id}</td>
        <td>${list.name}</td>
    </tr>
</c:forEach>
    <tr>
        <td colspan="2">
            <input type="button" value="돌아가기" onclick="location.href='/'">
        </td>
    </tr>

</table>
</body>
</html>
