package com.trots.periodacals.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * The type Context listener.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String path = servletContext.getRealPath("/WEB-INF/log4j2.log");
        System.setProperty("logFile", path);
        final Logger logger = LogManager.getLogger(ContextListener.class);
        logger.debug("path = " + path);
    }
}
