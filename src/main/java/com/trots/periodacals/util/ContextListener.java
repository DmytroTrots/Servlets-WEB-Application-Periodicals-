package com.trots.periodacals.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.*;


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
