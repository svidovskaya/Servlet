<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 14.02.2020
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="my" uri="http://mycompany.com//myTag" %>

<html>
  <head>
    <title>Инфо юзер</title>
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">  </head>

  </head>
  <body>
  <a href="index.jsp">Меню</a>
<table>
<form action="User_Controller" method="post">
  <tr> <td>Имя: </td><td><input type="text" name="u_name" value="${user.name}"></td></tr>
  <tr><td>Фамилия: </td><td><input type="text" name="u_surname" value="${user.surname}"></td></tr>
  <tr><td>Отчество: </td><td><input type="text" name="u_middlename" value="${user.middlename}"></td></tr>
  <tr><td>Телефон: </td><td><input type="text" name="u_phone" value="${user.phone}"></td></tr>
  <tr><td>Почта: </td><td><input type="text" name="u_mail" value="${user.mail}"></td></tr>
  <tr><td>Роль: </td><td><input type="text" name="u_role" readonly value="${user.role}"></td></tr>
  <tr><td><input type="hidden" name="id" value="${user.id}"></td><td><input type="submit" name="action" value="update"></td></tr>
  <tr><td><input type="hidden" name="login" value="${user.username}"></td><td></td> </tr>
</form>
</table>

<p>Кол-во посещений в сессии: ${sessionScope.values()}
<p>Кол-во пользователей  ${applicationScope.count}

 <p> <my:simple></my:simple>





  </body>
</html>

