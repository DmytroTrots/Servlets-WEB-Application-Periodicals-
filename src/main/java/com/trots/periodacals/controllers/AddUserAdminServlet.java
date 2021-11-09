package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.UserDaoImpl;
import com.trots.periodacals.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addUser")
public class AddUserAdminServlet extends HttpServlet {

    UserDaoImpl userDaoImpl = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AddUser#doGet");

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/addUserAdminPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nameOfRole = request.getParameter("nameOfRole");
        String telephone = request.getParameter("telephone");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        User user = new User();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setName(name);
        user.setSurname(surname);
        user.setRole(nameOfRole);

        userDaoImpl.addUser(user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/addUserAdminPage.jsp");
        dispatcher.forward(request, response);
    }
}
