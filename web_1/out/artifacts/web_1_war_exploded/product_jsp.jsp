<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset=utf-8" />
    <title>JSTL</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">  </head>

</head>
<body>
<a href="index.jsp">Меню</a>
<form action="Servlet_products_show" method="GET">
    Поиск по названию: <input type="text" name="filter_name"><br>
    Поиск по производителю:
    <input type="checkbox" name="filter_m" value="All" checked > Все
    <c:forEach var="manufacturers" items="${manufacturer}">
        <input type="checkbox" name="filter_m" value="${manufacturers.name}" > ${manufacturers.name}
    </c:forEach> <br>
    Цена <select name="filter_pr"><option name="filter_pr">По возрастанию</option> <option name="filter_pr" selected>По убыванию</option> </select> <br>
    <input type="submit" value="Поиск" />
</form>
<table>
    <tr>

    </tr>

    <c:forEach var="products" items="${product}">
        <tr>
            <td>${products.id}</td>
            <td>${products.shtrih}</td>
            <td>${products.kode}</td>
            <td>${products.name}</td>
            <td>${products.price}</td>
            <td>${products.price_stom}</td>
            <td>${products.manuf}</td>
            <form action="Servlet_products_show" method="get">
            <td><input type="hidden" name="add" value="${products.id}"></td>
            <td><input type="submit" name="add" value="ADD"></td>
            </form>

        </tr>

    </c:forEach>

</table>
</body>
</html>