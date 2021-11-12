package com.trots.periodacals.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

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
