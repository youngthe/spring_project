<%--
  Created by IntelliJ IDEA.
  User: whdud
  Date: 2022-07-12
  Time: 오후 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<html>
<head>
    <title>글작성</title>
    <link href="${path}/resources/css/community_main.css" rel="stylesheet"/>
</head>
<body>
<section>
    <table>
        <form name="form" action="/community/write/upload" method="post" enctype="multipart/form-data">
            <tr>
            <td>글 작성하기</td>
        </tr>
            <tr>
                <td>제목</td>
                <td><input type = "text" name = "title" id="title"></td>
            </tr>
            <tr>
                <td>컨텐츠</td>
                <td><textarea name="content" id="content" rows="15" cols="33"></textarea></td>
            </tr>
            <tr>
                <td></td><td><input type="file" name="file" multiple="multiple"/></td>
            </tr>
            <tr>
                <td><input type="button" value="돌아가기" onclick="history.back()"></td>
                <td><input type="submit" value = "작성완료"></td>
            </tr>
        </form>
    </table>
</section>
</body>
</html>
