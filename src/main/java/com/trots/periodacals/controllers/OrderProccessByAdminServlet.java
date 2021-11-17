package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.ReceiptHasPeriodicalDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/orders")
public class OrderProccessByAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("receiptList",ReceiptHasPeriodicalDaoImpl.getInstance().getAllReceipts());
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/orderProccessByAdminPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
