<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="/addMember" method="post">
        <table>
            <tr>
                <td><input type="text" width="100" name="name"></td>
                <td>이름</td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="이름 추가하기">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
