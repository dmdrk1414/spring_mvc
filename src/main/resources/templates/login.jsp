<html xmlns:th="http://www.thymeleaf.org">

<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<!--form에 login.jsp action 삽입-->
<form action="LoginServlet" method="get">
    <table align=center>
        <tr>
            <td align=center colspan=2 height=40><b>로그인</b>
            <td>
        </tr>
        <tr>
            <td align=right>아이디&nbsp;</td>
            <td><input name="id" placeholder="Email address" required type="text"></td>
            ₩
        </tr>
        <tr>
            <td align=right>패스워드&nbsp;</td>
            <td><input name="ps" required type="password"></td>
        </tr>
        <tr>
            <td align=center colspan=2 height=50>
                <input type="submit" value="로그인하기">
            </td>
        </tr>
    </table>
</form>
</body>
</html>