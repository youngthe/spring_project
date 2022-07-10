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
<script>
    function Checkform(){
        if(frm.id.value == ""){
            frm.id.focus();
            alert("id를 입력하세요 ! ");
            return false;
        }else{
            return true;
        }
    }
</script>
    <form name = "frm" action="/deleteMember" method = "post" onsubmit="return Checkform()">
        <table>
            <tr>
                <td>삭제 할 ID</td><td><input type = "text" name = "id" id = "id"></td>
            </tr>

            <tr>
                <td><input type = "button" value="돌아가기" onclick="location.href='/'"></td>
                <td><input type = "submit" value = "삭제하기"></td>
            </tr>
        </table>
    </form>
</body>
</html>
