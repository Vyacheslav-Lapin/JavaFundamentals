<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Localize</title>
</head>
<body>

<fmt:setLocale value="${param.local}"/>
<fmt:setBundle basename="locale" var="loc"/>
<fmt:message bundle="${loc}" key="local.message" var="message"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>

<form action="." method="post">
    <input type="hidden" name="local" value="ru" />
    <input type="submit" value="${ru_button}" /><br />
</form>
 
<form action="." method="post">
    <input type="hidden" name="local" value="en" />
    <input type="submit" value="${en_button}" /><br />
</form>
 
${message}

</body>
</html>
