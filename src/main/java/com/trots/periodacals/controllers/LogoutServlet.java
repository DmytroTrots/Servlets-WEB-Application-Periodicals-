package com.trots.periodacals.controllers;

import com.trots.periodacals.dao.UserDao;
import com.trots.periodacals.dbconnection.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao();
        userDao.setConnectionBuilder(new ConnectionPool());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); //Fetch session object

        if (session != null) //If session is not null
        {
            session.invalidate(); //removes all session attributes bound to the session
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/shopPage.jsp");
            requestDispatcher.forward(request, response);
            System.out.println("Logged out");
        }
    }
}
