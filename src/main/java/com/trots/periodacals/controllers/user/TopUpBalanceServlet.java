package com.trots.periodacals.controllers.user;

import com.trots.periodacals.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * The type Top up balance servlet.
 */
@WebServlet("/top-up")
public class TopUpBalanceServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(TopUpBalanceServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/topUpBalancePage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Double balance = Double.parseDouble(request.getParameter("balance"));
        String lang = (String) request.getSession().getAttribute("lang");

        int id = (int) request.getSession().getAttribute("ID");

        Double actualBalance = (Double) request.getSession().getAttribute("balance");
        if (actualBalance != null) {
            actualBalance += balance;
            UserService.getInstance().updateFieldBalanceAfterTopUp(id, actualBalance);
            request.getSession().setAttribute("balance", actualBalance);
        }
        if (lang == null || lang.equals("en")) {
            request.getSession().setAttribute("ex", "Balance replenished ");
        } else {
            request.getSession().setAttribute("ex", "Баланс поповнено");
        }
        response.sendRedirect(request.getContextPath() + "/top-up");
    }

}
