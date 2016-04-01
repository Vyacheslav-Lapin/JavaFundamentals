package com.epam.courses.jf.servlets;

import com.epam.courses.jf.servlets.common.HttpFilter;
import com.epam.courses.jf.servlets.common.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = {"/PwdShower", "/Cookies"},
        initParams = {@WebInitParam(name = "characterEncoding", value = "utf-8")})
public class ContentTypeFilter implements HttpFilter {

    private String encoding;
    private static Logger logger = System.out::println;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("characterEncoding");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("ContentTypeFilter начал работу");
        response.setContentType("text/html; charset=" + encoding);
        chain.doFilter(request, response);
        logger.info("ContentTypeFilter закончил работу");
    }
}
