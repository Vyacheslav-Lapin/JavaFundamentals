package com.epam.courses.jf.servlets;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Защита от доступа со стороны пользовательских script`ов
 */
@WebListener
public class SessionCookieHider implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().getServletContext().getSessionCookieConfig().setHttpOnly(true);
    }
}
