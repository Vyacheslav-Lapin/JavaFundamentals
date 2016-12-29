<%@ page import="dao.interfaces.GunDao" %>
<%@ page import="static listeners.DaoProvider.GUN_DAO" %>
<%@ page import="model.Gun" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>nashorn.Hello, World!</title>
  </head>
  <body>
  <h1>Добро пожаловать!</h1>

  <table style="border: 1px solid #000;">
      <tr>
          <th style="border: 1px solid #000;">Название модели</th>
          <th style="border: 1px solid #000;">Калибр</th>
      </tr>
      <% GunDao gunDao = (GunDao) application.getAttribute(GUN_DAO);
          for (Gun gun : gunDao.getAll()) {%>
          <tr>
            <td style="border: 1px solid #000;"><a href="/buy?id=<%=gun.getId()%>"><%=gun.getName()%></a></td>
            <td style="border: 1px solid #000;"><%=gun.getCaliber()%></td>
          </tr>
      <% }%>
  </table>

  </body>
</html>
