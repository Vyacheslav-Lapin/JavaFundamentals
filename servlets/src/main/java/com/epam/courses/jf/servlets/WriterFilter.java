package com.epam.courses.jf.servlets;

import com.epam.courses.jf.servlets.common.HttpFilter;
import com.epam.courses.jf.servlets.common.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter({"/PwdShower", "/Cookies"})
public class WriterFilter implements HttpFilter {

    private static Logger logger = System.out::println;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("WriterFilter начал работу");
        try (PrintWriter out = response.getWriter()) {
            request.setAttribute("writer", out);
            chain.doFilter(request, response);
        }
        logger.info("WriterFilter закончил работу");
    }
}
