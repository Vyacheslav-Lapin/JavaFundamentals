<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="static listeners.ConnectionPoolDistributor.POOL_NAME" %>
<%@ page import="com.epam.courses.jf.jdbc.cp.ConnectionPool" %>
<%@ page import="dao.h2.H2GunDao" %>
<%@ page import="dao.interfaces.GunDao" %>
<%@ page import="model.Gun" %>
<html>
  <head>
    <title>Hello, World!</title>
  </head>
  <body>
  <h1>Добро пожаловать!</h1>

  Можете выбрать одну из следующих моделей огнестрельного оружия:

  <table>
      <tr>
          <th>Название модели</th>
          <th>Калибр</th>
      </tr>
      <%
          final ConnectionPool connectionPool = (ConnectionPool) application.getAttribute(POOL_NAME);
          final GunDao gunDao = new H2GunDao(connectionPool);
          for (Gun gun: gunDao.getAll()) {
            %><tr><td><%=gun.getName()%></td><td><%=gun.getCaliber()%></td></tr><%
          }
      %>
  </table>

  </body>
</html>
