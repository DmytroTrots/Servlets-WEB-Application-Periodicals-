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

@WebServlet("/ban-user")
public class banUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        User user = UserDaoImpl.getInstance().getSingleUserById(id);
        String banStatus = user.getBanStatus();
        if (banStatus == null) {
            UserDaoImpl.getInstance().updateBanStatusOfUser("banned", id);
        } else {
            UserDaoImpl.getInstance().updateBanStatusOfUser(null, id);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/addUserAdminPage.jsp");
        dispatcher.forward(req, resp);
    }
}
