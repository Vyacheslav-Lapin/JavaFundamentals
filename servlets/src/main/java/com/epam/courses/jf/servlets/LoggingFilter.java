package com.epam.courses.jf.servlets;

import com.epam.courses.jf.servlets.common.HttpFilter;
import com.epam.courses.jf.servlets.common.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/PwdShower", "/Cookies"})
public class LoggingFilter implements HttpFilter {

    private static Logger logger = System.out::println;

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//          logger = filterConfig.getServletContext()::log; // TODO: 3/31/2016 разобраться, куда пишет и где это настраивается
//    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Пришёл клиент с Java session id = " + request.getSession(true).getId());
        chain.doFilter(request, response);
    }
}
