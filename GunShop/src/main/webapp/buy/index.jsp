<%@ page import="model.Gun" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    Gun gun = (Gun) request.getAttribute("gun");
%>
Вы правда желаете купить <%=gun.getName()%>, калибр <%=gun.getCaliber()%>?

</body>
</html>
