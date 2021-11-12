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
import java.io.PrintWriter;

@WebServlet("/ban-user")
public class banUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(PrintWriter out = resp.getWriter()){
            Integer id = Integer.parseInt(req.getParameter("id"));
            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.getSingleUserById(id);
            String banStatus = user.getBanStatus();
            if (banStatus == null){
                userDao.updateBanStatusOfUser("banned", id);
            }else{
                userDao.updateBanStatusOfUser(null, id);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/addUserAdminPage.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
