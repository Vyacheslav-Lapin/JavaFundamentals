package com.epam.courses.jf.jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletForJSPElement")
public class ServletForJSPElement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html");
//        if ("naming".equals(request.getParameter("command"))) request.
//                getRequestDispatcher("use-bean/usebean.jsp").
//                forward(request, response);

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        SimpleBean s = new SimpleBean();
        s.setName(request.getParameter("name"));
        s.setSurname(request.getParameter("surname"));
        request.setAttribute("mybean", s);
        if ("naming".equals(request.getParameter("command"))) request.
                getRequestDispatcher("/use-bean/usebean.jsp").
                forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
