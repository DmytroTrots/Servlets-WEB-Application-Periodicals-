package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.UserDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-user")
public class deleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        UserDaoImpl.getInstance().deleteUserFromAdminPage(id);

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/addUserAdminPage.jsp");
        dispatcher.forward(req, resp);
    }
}
