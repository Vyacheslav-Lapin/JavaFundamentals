<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="static java.util.Arrays.asList, java.util.*"%>
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

<br/>param.name : ${param.name}
<br/>paramValues.name[1] : ${paramValues.name[1]}
<br/>request.getParameter("name") : <%=request.getParameter("name")%>>

<br/>${header["host"]}
<br/>

<% pageContext.setAttribute("strings", Arrays.asList("Мама", "мыла", "раму1")); %>

${strings[0]}

<c:if test="${not empty strings and strings[2] != 'раму1'}">
    <c:redirect url="/index.html"/>
</c:if>

<br/><br/><br/><br/><br/>

<%--${not empty strings and strings[2] != 'раму'}--%>

</body>
</html>
