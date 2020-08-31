<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %><%--
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
  <body>
  <a href="../index.jsp">Меню</a>

 <% count++; %>
  <p> Кол-во посещений страницы = <%= count %>
  <p> ServletConfig является уникальным объектом для каждого сервлета, в то время как ServletContext уникальный для всего приложения.</p>
  <p> Данные getServletName: <%= getServletConfig().getServletName()%>
  <p> Данные getResourcePaths: <%= getServletConfig().getServletContext().getResourcePaths("/")%>
  <p> Данные getServerInfo: <%= getServletConfig().getServletContext().getServerInfo()%>
  <p> Данные getContextPath: <%= getServletConfig().getServletContext().getContextPath()%>
  <p> Данные getRealPath: <%= getServletConfig().getServletContext().getRealPath(request.getServletPath())%>
  <p> Данные: <%= getServletInfo()%>

<p>Пользователь: <%= request.getUserPrincipal().getName()%>
  <%= request.getSession().getAttribute("username")%>


  </body>
</html>

<%! static int count = 0; %>
