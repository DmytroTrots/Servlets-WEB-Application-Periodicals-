package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.UserDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/top-up")
public class TopUpBalanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/topUpBalancePage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Double balance = Double.parseDouble(request.getParameter("balance"));

        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("ID");


        Double actualBalance = UserDaoImpl.getInstance().getBalanceOfUserById(id);
        if (actualBalance != null) {
            actualBalance += balance;
            UserDaoImpl.getInstance().updateFieldBalanceAfterTopUp(id, actualBalance);
            session.setAttribute("balance", actualBalance);
        } response.sendRedirect("cartPage.jsp");
    }

}
