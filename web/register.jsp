<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/1/30
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
    <script type="text/javascript">
        function RefreshCode(obj){
            obj.src = obj.src + "?code=" + Math.random();
        }
    </script>
</head>
<body>
<p align="center" style="color:red;font-weight: 800">${message}</p>
<form action="<c:url value='/RegisterServlet'/>" method="post">
    <input type="hidden" name="method" value="register">
    <div id="register">
        <h1 style="text-align: center ">注册</h1>
        ${errors.username}<input type="text" name="username" placeholder="用户名" value="${user.username}"/><br>
        ${errors.password}<input type="password" name="password" placeholder="密  码" value="${user.password}"/><br>
        ${errors.phone}<input type="text" name="phone" placeholder="手机号码" value="${user.phone}"/><br>
        ${errors.email}<input type="text" name="email" placeholder="邮箱" value="${user.email}"/><br>
        ${errors.verifyCode}<input type="text" name="verifyCode" placeholder="验证码" value="${user.verifyCode}" size="10"/><br>
        <img id="verifyCode" src="RegisterVerifyCodeServlet" title="点击更换" onclick="RefreshCode(this)"/><br>
        <input class="button" style="text-align: center" type="submit" value="注册">
        <input class="button" style="text-align: center" type="reset" value="重置">
    </div>
</form>
</body>
</html>