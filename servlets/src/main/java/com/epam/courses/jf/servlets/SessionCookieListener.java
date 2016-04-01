package com.epam.courses.jf.servlets;

import com.epam.courses.jf.servlets.common.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionCookieListener implements HttpSessionListener {

    private static Logger logger = System.out::println;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("SessionCookieListener: SessionID = " + se.getSession().getId());
    }
}
