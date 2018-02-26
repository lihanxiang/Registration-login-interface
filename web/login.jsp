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
<form>
    <table align="center" width="30%">
        <tr>
            <td width="10%">用户名</td>
            <td width="20%">
                <input type="text" name="username"/>
            </td>
        </tr>
        <tr>
            <td width="10%">密码</td>
            <td width="20%">
                <input type="password" name="password"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="登陆">
                <input type="reset" name="重置">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
