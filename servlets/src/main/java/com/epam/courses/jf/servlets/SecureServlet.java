package com.epam.courses.jf.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/secure/account")
//@ServletSecurity(
//        value=@HttpConstraint(rolesAllowed = ""),
//        httpMethodConstraints={
//                @HttpMethodConstraint(value="GET", rolesAllowed="R2"),
//                @HttpMethodConstraint(value="POST", rolesAllowed={"R3", "R4"})})
public class SecureServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
