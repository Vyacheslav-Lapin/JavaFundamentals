<%--
  Created by IntelliJ IDEA.
  User: Vyacheslav_Lapin
  Date: 4/7/2016
  Time: 9:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>An Include Jsr310Test</title>
</head>
<body>
<div style="color: blue;">
    The current date and time are
    <%--<%@ include file="date.jsp"%>--%>
    <jsp:include page="date.jsp"/>
</div>
</body>
</html>
