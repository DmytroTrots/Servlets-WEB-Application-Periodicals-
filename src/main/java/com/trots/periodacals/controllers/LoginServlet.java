package com.trots.periodacals.controllers;

import com.trots.periodacals.dao.UserDao;
import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao();
        userDao.setConnectionBuilder(new ConnectionPool());
    }

    public LoginServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/loginPage.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        UserDao userDao = new UserDao();

        try {
            String userValidate = userDao.authenticateUser(user);

            if (userValidate.equals("admin")) {
                System.out.println("Admin's Home");

                HttpSession session = request.getSession(); //Creating a session
                session.setAttribute("Role", userValidate); //setting session attribute
                request.setAttribute("userName", username);

                response.sendRedirect(request.getContextPath() + "/shop");
            } else if (userValidate.equals("manager")) {
                System.out.println("Editor's Home");

                HttpSession session = request.getSession();
                session.setAttribute("Role", userValidate);
                request.setAttribute("userName", username);

                response.sendRedirect(request.getContextPath() + "/shop");
            } else if (userValidate.equals("customer")) {
                System.out.println("User's Home");

                HttpSession session = request.getSession();
                session.setAttribute("Role", userValidate);
                request.setAttribute("userName", username);

                response.sendRedirect(request.getContextPath() + "/shop");
            } else {
                System.out.println("Error message = " + userValidate);
                request.setAttribute("errMessage", userValidate);

                request.getRequestDispatcher("/WEB-INF/views/loginPage.jsp").forward(request, response);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
