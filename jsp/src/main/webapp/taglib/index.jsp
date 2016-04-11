<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         import="com.epam.courses.jf.jsp.JSPSetBean" %>
<%@ taglib uri="http://epam.com/courses/jf/jsp/myTagLib" prefix="mytag"%>
<html>
<head>
    <title>TagLib example</title>
</head>
<body>

<%--<jsp:useBean id="userBean" class="com.epam.courses.jf.jsp.JSPSetBean" scope="request"/>--%>
<%
    final JSPSetBean userBean = (JSPSetBean) request.getAttribute("userBean");
    pageContext.setAttribute("userBean", userBean);
%>

${userBean.size}

<mytag:jspset set="${userBean}"/>

<br/>

<mytag:bodyJspSet num="${userBean.size}">
    ${userBean.element}
</mytag:bodyJspSet>

</body>
</html>
