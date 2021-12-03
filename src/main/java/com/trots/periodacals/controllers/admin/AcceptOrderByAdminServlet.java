package com.trots.periodacals.controllers.admin;


import com.trots.periodacals.service.ReceiptService;
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
 * The type Accept order by admin servlet.
 */
@WebServlet("/accept-order")
public class AcceptOrderByAdminServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(banUserServlet.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/shopPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        ReceiptService.getInstance().acceptOrderByAdmin(id);
        req.getSession().setAttribute("ex", "Order accepted");
        log.trace("Successfully --> order " + id + " accepted");
        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}
