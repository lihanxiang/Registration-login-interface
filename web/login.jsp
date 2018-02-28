<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/1/30
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="text-align: center ">欢迎来到登陆界面</h1>
<p align="center" style="color:red;font-weight: 800">${message}</p>
<form action="<c:url value='/LoginServlet'/>" method="post">
    <table align="center" width="30%">
        <input type="hidden" name="method" value="login">
        <tr>
            <td>
                用户名:
            </td>
            <td>
                <input type="text" name="username" value="${user.username}"/>${errors.username}<br/>
            </td>
        </tr>
        <tr>
            <td>
                密  码:
            </td>
            <td>
                <input type="password" name="password" value="${user.password}"/>${errors.password}<br/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="登录"><br/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
