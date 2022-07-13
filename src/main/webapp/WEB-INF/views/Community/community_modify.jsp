<%--
  Created by IntelliJ IDEA.
  User: whdud
  Date: 2022-07-13
  Time: 오후 2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 수정하기</title>
<table>
    <form name="form" action="/community/modify/${community.id}" method="post">
        <tr>
            <td>글 수정하기</td>
        </tr>
        <tr>
            <td>제목</td>
            <td><input type = "text" name = "title" id="title" value="${community.title}"></td>
        </tr>
        <tr>
            <td>컨텐츠</td>
            <td><textarea name="content" id="content" rows="15" cols="33" >${community.content}</textarea></td>
        </tr>
        <tr>
            <td><input type="button" value="돌아가기" onclick="location.href='/community'"></td><td><input type="submit" value = "수정하기"></td>
        </tr>
    </form>
</table>
</head>
<body>

</body>
</html>
