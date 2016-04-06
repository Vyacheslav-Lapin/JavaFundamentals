package com.epam.courses.jf.servlets;

import com.epam.courses.jf.servlets.common.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupListener implements ServletContextListener {

    private static final Logger logger = System.out::println;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Application is started!");
    }
}
