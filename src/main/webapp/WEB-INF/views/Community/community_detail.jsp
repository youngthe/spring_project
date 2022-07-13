<%--
  Created by IntelliJ IDEA.
  User: whdud
  Date: 2022-07-12
  Time: 오후 2:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 상세 보기</title>
</head>
<body>
    <table>
        <tr>
            <td>${community.id}</td>
            <td>${community.title}</td>
        </tr>
        <tr><td></td><td>${community.content}</td></tr>
        <tr><td>
            <input type="button" value="수정하기" onclick="location.href='/community/modify/${community.id}'">
        </td><td>
            <input type="button" value="삭제하기" onclick="location.href='/community/delete/${community.id}'">
        </td></tr>
        <tr><td>
            <input type="button" value="돌아가기" onclick="location.href='/community/'">
        </td></tr>
    </table>
</body>
</html>
