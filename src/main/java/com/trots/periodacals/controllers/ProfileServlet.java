package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.ReceiptDaoImpl;
import com.trots.periodacals.entity.Receipt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = (Integer) req.getSession().getAttribute("ID");
        if (id != null) {
            List<Receipt> receiptList = ReceiptDaoImpl.getInstance().getAllOrdersOfUserById(id);
            if (!receiptList.isEmpty()) {
                req.setAttribute("receiptList", receiptList);
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/profilePage.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}