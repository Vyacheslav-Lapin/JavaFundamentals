<%@ page import="static servlets.Buy.GUN" %>
<%@ page import="model.Gun" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final Gun gun = (Gun) request.getAttribute(GUN);
%>
<html>
<head>
    <title>Gun <%=gun.getName()%></title>
</head>
<body>

<h1>Gun <%=gun.getName()%></h1>

Название: <%=gun.getName()%><br/>
Калибр: <%=gun.getCaliber()%>

</body>
</html>
