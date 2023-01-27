<%--
  Created by IntelliJ IDEA.
  User: xiaoxin
  Date: 2022/11/24
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%--    <base href="http://localhost:8080/schooldorm/">--%>
    <base href="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%>/<%=request.getContextPath()%>/">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./less/login.css">
    <title>Document</title>
</head>

<body>
<div id="app">

    <form action="/schooldorm/user/login" method="post" id="form" autocomplete="off">
        <h1 id="loginMsg">LOGIN IN</h1>
        <p style="text-align: center; color: red;letter-spacing: 3px">${login_msg}${filter_msg}</p>
        <table>
            <tr>
                <td align="right">Username:</td>
                <td>
                    <input type="text" name="userName" required="required">
                </td>
            </tr>
            <tr>
                <td align="right">Password:</td>
                <td>
                    <input type="password" name="passWord" required="required">
                </td>
            </tr>

            <tr>
                <td align="right">Type:</td>
                <td>
                    <label><input type="radio" name="type" value="1" checked>宿管</label>
                    <label><input type="radio" name="type" value="2">管理员</label>
                </td>
            </tr>

            <tr>
                <td align="right"></td>
                <td>
                    <input type="submit" value="提交">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>


    </form>

</div>
</body>
<script src="./js/vue.js"></script>
<script src="./element-ui/lib/index.js"></script>
<link rel="stylesheet" href="./element-ui/lib/theme-chalk/index.css">
</html>
