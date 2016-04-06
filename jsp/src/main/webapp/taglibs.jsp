<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Taglibs</title>
</head>
<body>

<c:set var="salary" scope="request" value="40"/>
${requestScope.salary}

<c:set var="salary" scope="session" value="30"/>
${sessionScope.salary}

</body>
</html>
