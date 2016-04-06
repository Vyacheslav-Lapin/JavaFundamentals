<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorize</title>
</head>
<body>
<form action="/BeanShower" method="post">
    <input type="hidden" name="command"
           value="naming"/> Введите имя:<br/>
    <input type="text" name="name" value=""/>
    <br/> Введите фамилию:<br/>
    <input type="text" name="surname" value=""/><br/>
    <input type="submit" value="Отправить"/><br/>
</form>
</body>
</html>
