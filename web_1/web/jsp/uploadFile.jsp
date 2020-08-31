<%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 20.02.2020
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE >
<html>
<head>
    <title>Загрузка</title>
</head>
<body>
<a href="index.jsp">Меню</a><br />
<div style="padding:5px; color:red;font-style:italic;">
    ${errorMessage}
</div>
<form method="post" action="${pageContext.request.contextPath}/uploadFile"
      enctype="multipart/form-data">
    Выберите файл:
    <br />
    <input type="file" name="file"  />


    <br />
    <input type="submit" value="Загрузить" />
</form>

</body>
</html>
