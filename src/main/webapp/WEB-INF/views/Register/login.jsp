<%--
  Created by IntelliJ IDEA.
  User: whdud
  Date: 2022-07-10
  Time: 오후 1:42
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
        if(form.id.value == ""){
            form.id.focus();
            alert("아이디를 입력하세요 ! ");
            return false;
        }else if(form.pw.value == ""){
            form.pw.focus();
            alert("비밀번호를 입력하세요 ! ");
            return false;
        }else{
            return true;
        }
    }
</script>
    <form name = form action="/login" method="post">
        <table>
            <tr>
                <td>id</td><td><input type="text" name="id" id="id"></td>
            </tr>
            <tr>
                <td>pw</td><td><input type="password" name="pw" id="pw"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="로그인"></td>
            </tr>
        </table>

    </form>
</body>
</html>
