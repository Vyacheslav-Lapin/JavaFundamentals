package com.epam.courses.jf.servlets.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/PwdShower")
public class PwdShower extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try (PrintWriter out = response.getWriter()) {
            out.println("Your login (твой логин): " + login);
            out.println("<br />Your password (твой пароль): " + password);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
