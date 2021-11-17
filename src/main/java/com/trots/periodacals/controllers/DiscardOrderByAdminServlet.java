package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.ReceiptDaoImpl;
import com.trots.periodacals.daoimpl.UserDaoImpl;
import com.trots.periodacals.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/discard-order")
public class DiscardOrderByAdminServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(banUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/shopPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        Double price = Double.parseDouble(req.getParameter("price"));
        ReceiptDaoImpl.getInstance().discardOrderByAdmin(id);
        User user = UserDaoImpl.getInstance().getSingleUserById(userId);
        Double actualBalance = user.getBalance();
        actualBalance = actualBalance + price;
        UserDaoImpl.getInstance().updateFieldBalanceAfterTopUp(userId, actualBalance);
        log.trace("Successfully --> order " + id + " discarded");

        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}

