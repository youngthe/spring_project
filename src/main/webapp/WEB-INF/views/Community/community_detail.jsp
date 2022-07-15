<%--
  Created by IntelliJ IDEA.
  User: whdud
  Date: 2022-07-12
  Time: 오후 2:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>

<head>
    <title>게시글 상세 보기</title>
    <link href="${path}/resources/css/community_sub.css" rel="stylesheet"/>
</head>
<section class="notice">
<div class="container">

    <body>
        <table class="board-table">
            <tr>
                <th>글 번호</th><td>${community.id}</td>
                <th>조회수</th><td>${community.hits}</td>
            </tr>
            <tr>
                <th>작성자</th><td>${community.writer}</td>
                <th>작성시간</th><td>${community.date}</td>
            </tr>
            <tr>
                <th>제목</th><td colspan="3">${community.title}</td>
            </tr>
            <tr>
                <td colspan="4">${community.content}</td>
            </tr>

        </table>
        <div class="bottom">
            <input type="button" id="bt1" value="수정하기" onclick="location.href='/community/modify/${community.id}'">
            <input type="button" id="bt2" value="삭제하기" onclick="location.href='/community/delete/${community.id}'">
            <input type="button" id="bt3" value="돌아가기" onclick="location.href='/community/'">
        </div>
        <div class="input_comment">
            <form action="/community/comments/${community.id}" method="post">
                <input type="text" name="comments">
                <input type="submit" value="작성">
            </form>
        </div>
        <div class="view_comments">
            <table class="table_comments">

                <c:forEach var="comment" items="${comments}" varStatus="status">
                            <tr>
                                <th>닉네임 : ${comment.writer}</th>
                            </tr>
                            <tr>
                                <td id="date">${comment.date}</td>
                            </tr>
                            <tr>
                                </td><th id="board-comment"> ${comment.comment}</th>
                            </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</div>
</section>
</html>
