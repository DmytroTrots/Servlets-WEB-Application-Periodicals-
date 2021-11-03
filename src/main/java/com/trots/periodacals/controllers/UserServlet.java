package com.trots.periodacals.controllers;

import com.trots.periodacals.dao.UserDao;
import com.trots.periodacals.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
public class UserServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserServlet#doGet");
        List<User> list = userDao.findAllUsers();
        System.out.println(list.get(0).getId());

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/registrationPage.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telephone = request.getParameter("telephone");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setRoleId(2);
        user.setName(name);
        user.setSurname(surname);

        userDao.registerUser(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/shopPage.jsp");
        dispatcher.forward(request, response);
    }



}
