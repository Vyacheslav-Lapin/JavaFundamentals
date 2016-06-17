<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="gun" type="model.Gun" scope="request"/>
<html>
<head>
    <title>Gun ${gun.name}</title>
</head>
<body>

<h1>Gun ${gun.name}</h1>

Название: ${gun.name}<br/>
Калибр: ${gun.caliber}

</body>
</html>