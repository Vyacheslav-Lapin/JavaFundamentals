<%--
  Created by IntelliJ IDEA.
  User: Vyacheslav_Lapin
  Date: 4/7/2016
  Time: 9:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Expression Language</title>
</head>
<body>

<c:set var="salary" scope="request" value="40"/>
${requestScope.salary}

<br/>

<c:set var="salary" scope="session" value="30"/>
${sessionScope.salary}

<br/>

${header["host"]}

</body>
</html>
