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

<% pageContext.setAttribute("collection", asList("Мама", "мыла", "раму1"));%>

collection[0] - ${collection[0]}
<br/>

<c:if test="${not empty collection[2] and collection[2] eq 'раму'}">
    <c:redirect url="/index.html"/>
</c:if>


<%--${}--%>

</body>
</html>
