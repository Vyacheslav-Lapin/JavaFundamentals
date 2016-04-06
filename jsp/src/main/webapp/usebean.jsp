<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="com.epam.courses.jf.jsp.SimpleBean" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<html>
<head>
    <title>BeanViewer</title>
</head>
<body>
<jsp:useBean id="naming" class="com.epam.courses.jf.jsp.SimpleBean"/>

<jsp:setProperty property="*" name="naming"/>
<jsp:getProperty property="name" name="naming"/>
<jsp:getProperty property="surname" name="naming"/>
<jsp:getProperty property="date" name="naming"/>

<br/>

<jsp:useBean id="pageDate" class="java.util.Date"/>
<jsp:setProperty name="naming" property="date" value="${pageDate}"/>
<jsp:getProperty property="date" name="naming"/>
<br/>
<%--<c:out value="${pageScope.naming.name}"/>--%>

${pageScope.naming.name}
${pageScope.naming.surname}
${pageScope.naming.date}
 
</body>
</html>
