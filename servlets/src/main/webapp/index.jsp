<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>MyJSP</title>
</head>
<body>
<form action="/PwdShower" method="get">
    <input type="hidden" name="command" value="forward"/>
    Enter login:<br/>
    <input type="text" name="login" value="" title="User name"/><br/>
    Enter password:<br/>
    <input type="password" name="password" value="" title="User Password"/><br/>
    <input type="submit" value="Отправить"/><br/>
</form>
</body>
</html>
