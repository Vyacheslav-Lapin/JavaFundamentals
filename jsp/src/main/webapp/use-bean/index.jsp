<%--
  Created by IntelliJ IDEA.
  User: Vyacheslav_Lapin
  Date: 4/7/2016
  Time: 8:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Use-bean Demo</title>
</head>
<body>

<form action="/ServletForJSPElement" method="post">

    <input type="hidden" name="command" value="naming" />

    Введите имя:<br/><input type="text" name="name" title="имя"/>

    <br/>
    Введите фамилию:<br/><input type="text" name="surname" title="фамилия"/>

    <br/>
    <input type="submit" value="Отправить"/><br />
</form>


</body>
</html>
