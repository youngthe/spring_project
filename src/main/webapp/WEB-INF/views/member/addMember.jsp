<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    function Checkform(){
        if(form.name.value == ""){
            form.name.focus();
            alert("이름을 입력하세요 ! ");
            return false;
        }else
            return true;
    }
</script>

    <form name = "form" action="/addMember" method="post" onsubmit= "return Checkform()">
        <table>
            <tr>
                <td>이름</td>
                <td><input type="text" width="100" name="name" id="name"></td>
            </tr>
            <tr>
                <td><input type="button" value="돌아가기" onclick="location.href='/'"></td>
                <td>
                    <input type="submit" value="이름 추가하기">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
