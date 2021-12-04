package com.trots.periodacals.controllers.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Logout servlet.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String lang = (String) request.getSession().getAttribute("lang");

        if(request.getSession()!=null) //If session is not null
        {
            request.getSession().invalidate(); //removes all session attributes bound to the session
            if (lang == null || lang.equals("en")) {
                request.getSession().setAttribute("ex", "You are successfully logged out");
            }else{
                request.getSession().setAttribute("ex", "Ви успішно вийшли з системи");
            }
            log.trace("Successfully logged out");
            request.getSession().setAttribute("lang", lang);
            response.sendRedirect(request.getContextPath()+"/login");
        }
    }
}
