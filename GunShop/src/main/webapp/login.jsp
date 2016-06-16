<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="requestedUrl" type="java.lang.String" scope="request"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login, please...</title>
</head>
<body>
<form method="POST" action="${requestedUrl}">
    Имя пользователя: <input type="text" name="j_username" title="Имя пользователя"/><br/>
    Пароль: <input type="password" name="j_password" autocomplete="off" title="Пароль"/><br/>
    <input type="submit" value="submit"/>
</form>

</body>
</html>