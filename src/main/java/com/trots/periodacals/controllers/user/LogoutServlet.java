package com.trots.periodacals.controllers.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession(false); //Fetch session object

        if(session!=null) //If session is not null
        {
            session.invalidate(); //removes all session attributes bound to the session
            response.sendRedirect(request.getContextPath()+"/shop?currentPage=1&category=0");
            System.out.println("Logged out");
        }
    }
}
