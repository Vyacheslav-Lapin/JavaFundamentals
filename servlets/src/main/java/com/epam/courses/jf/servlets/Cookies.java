package com.epam.courses.jf.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

@WebServlet("/cookies")
public class Cookies extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ofNullable(request.getParameter("key"))
                .flatMap(key -> ofNullable(request.getParameter("value"))
                        .map(value -> new Cookie(key, value)))
        .ifPresent(response::addCookie);

//        ((PrintWriter) request.getAttribute("writer"))
        try (PrintWriter out = response.getWriter()) {
            out.println(
                    ofNullable(request.getCookies())
                            .map(cookies -> stream(cookies)
                                    .map(cookie -> cookie.getName() + " = " + cookie.getValue())
                                    .collect(joining("<br/>")))
                            .orElse("No cookies"));

            request.getRequestDispatcher("/cookies/index.html").include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
