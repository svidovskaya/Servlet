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
    <title>Создать продукт</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">  </head>

  </head>
  <body>
  <a href="index.jsp">Меню</a>
  <a href="products">Продукты</a>
  <form action="Servlet_create_prod" method="GET">
    <p>Назание: <input type="text" name="name"></p>
    <p>Цена: <input type="number" name="pr"></p>
    <p>Цена стоматолога: <input type="number" name="pr1"></p>
    <p>Производитель: <select name="manuf">
      <%
      String URL = "jdbc:mysql://localhost/dental?useUnicode=true&serverTimezone=UTC";
      String USERNAME = "root";
      String PASSWORD = "4549";

      try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           Statement statement = connection.createStatement()) {
        String sql_manuf = "Select manuf_id from manufacturer";
        ResultSet resSet = statement.executeQuery(sql_manuf);
        int c =0;
        while (resSet.next()) {
          c += 1;
        }
        String[][] result =new String[c][2];

        sql_manuf = "select manuf_id, manuf_name from manufacturer";
        resSet = statement.executeQuery(sql_manuf);
        int i = 0;
        while (resSet.next()) {
          result [i][1] = resSet.getString("manuf_name");
          PrintWriter writer = response.getWriter();
          result [i][0] = String.valueOf(resSet.getInt("manuf_id"));
          out.println("<option value=\""+result[i][0]+"\">"+result[i][1]+"</option>");

          i+=1;
        }



      } catch (SQLException e) {
        e.printStackTrace();
      }


    %></select></p>
    <input type="submit" value="Отправить" />

  </form>

  </body>
</html>
