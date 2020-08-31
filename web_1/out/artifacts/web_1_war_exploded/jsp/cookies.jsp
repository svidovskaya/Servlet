<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 14.02.2020
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Инфо</title>
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">  </head>
  </head>

  <a href="index.jsp">Меню</a>
<%--${cookie.prod_id.value}--%>
  <table border="2">
    <c:forEach var="products" items="${product}">
      <tr>
        <td>${products.id}</td>
        <td>${products.shtrih}</td>
        <td>${products.kode}</td>
        <td>${products.name}</td>
        <td>${products.price}</td>
        <td>${products.price_stom}</td>
        <td>${products.manuf}</td>
        <form action="/servlets.servlet.Servlet_cookie" method="get">
          <td><input type="hidden" name="del" value="${products.id}"></td>
          <td><input type="submit" name="del" value="DEL"></td>
        </form>


      </tr>

    </c:forEach>

  </table>





  </body>
</html>

