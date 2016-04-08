<%--
  Created by IntelliJ IDEA.
  User: Vyacheslav_Lapin
  Date: 4/7/2016
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>UseBean-Demo</title>
</head>
<body>

<%--<jsp:useBean id="naming" class="com.epam.courses.jf.jsp.SimpleBean"/>--%>

<%--<jsp:setProperty property="*" name="naming"/>--%>
<%--<jsp:getProperty property="name" name="naming"/>--%>
<%--<jsp:getProperty property="surname" name="naming"/>--%>
<%--<jsp:getProperty property="date" name="naming"/>--%>

<%--<br/>--%>

<%--<jsp:useBean id="pageDate" class="java.util.Date" />--%>
<%--<jsp:setProperty name="naming" property="date" value="${pageDate}" />--%>
<%--<jsp:getProperty property="date" name="naming"/>--%>

<%--<br/>--%>

<%--${pageScope.naming}--%>

<jsp:useBean id="mybean" class="com.epam.courses.jf.jsp.SimpleBean"
             type="java.lang.Object" scope="request"/>

<%--<jsp:getProperty property="name" name="mybean"/>--%>
${requestScope.mybean.name}

<%--<jsp:getProperty property="surname" name="mybean"/>--%>
${requestScope.mybean.surname}

<%--<jsp:getProperty property="date" name="mybean"/>--%>
${requestScope.mybean.date}

<jsp:useBean id="pageDate" class="java.util.Date"/>

<c:set target="${mybean}" property="date" value="${pageDate}"/>
<jsp:getProperty property="date" name="mybean"/>

${requestScope.mybean.name}

</body>
</html>
