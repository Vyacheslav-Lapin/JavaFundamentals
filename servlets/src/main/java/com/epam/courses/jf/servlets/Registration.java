package com.epam.courses.jf.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Optional.ofNullable;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ofNullable(request.getParameter("login"))
                .flatMap(login -> ofNullable(request.getParameter("password"))
                        .map(password -> "Your login (твой логин): " + login + "<br/>Your password (твой пароль): " + password))
                .ifPresent(response.getWriter()::write);

        request.getRequestDispatcher("/registration/index.html").include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
