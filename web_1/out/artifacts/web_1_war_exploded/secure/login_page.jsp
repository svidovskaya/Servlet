<%@ page import="model.User" %>
<%@ page import="javax.jms.*" %>
<%@ page import="java.io.Serializable" %><%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 21.02.2020
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<form action="j_security_check" method="post">
    Логин: <input type="text" name="j_username"/><br/>
    Пароль: <input type="password" name="j_password"/><br/>
    <input type="submit" value="login"/>
</form>
</body>
</html>
