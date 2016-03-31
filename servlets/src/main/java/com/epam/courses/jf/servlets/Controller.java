package com.epam.courses.jf.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String cookieKey;
        String cookieValue;

        cookieKey = request.getParameter("cookiekey");
        cookieValue = request.getParameter("cookievalue");

        if ((cookieKey != null) && (cookieValue != null)) {
            Cookie cookie = new Cookie(cookieKey, cookieValue);
            response.addCookie(cookie);
        }

        Cookie[] reqCookie = request.getCookies();
        if (reqCookie != null) {
            for (Cookie c : reqCookie) {
                response.getWriter()
                        .println(c.getName() + " - "
                                + c.getValue() + "<br/>");
            }
        } else {
            response.getWriter().println("No cookies");
        }
        request.getRequestDispatcher("/index.html")
                .include(request, response);

    }
}
