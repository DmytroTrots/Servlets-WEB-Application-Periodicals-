package com.trots.periodacals.util;

import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckRole {

    public void checkRole(HttpServletRequest request, HttpServletResponse response, Logger log) throws ServletException, IOException {
        if (request.getSession().getAttribute("ID") == null) {
            request.setAttribute("ex", "You are not logged in");
            log.trace("Access denied(add periodical, admin page), user is not logged in");
            request.getRequestDispatcher("WEB-INF/views/loginPage.jsp").forward(request, response);
        } else if (request.getSession().getAttribute("Role").equals("customer") || request.getSession().getAttribute("Role").equals("manager")) {
            request.setAttribute("ex", "Access denied. You are not allowed to visit this page");
            log.trace("Access denied, user is no admin --> " + request.getSession().getAttribute("userName"));
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
