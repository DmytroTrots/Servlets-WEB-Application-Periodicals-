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
        String lang = (String) req.getSession().getAttribute("lang");
        ReceiptService.getInstance().acceptOrderByAdmin(id);
        log.trace("Successfully --> order " + id + " accepted");
        langCheck(req, resp, lang, "Order was accepted", "Замовлення було прийнято");

    }

    private void langCheck(HttpServletRequest request, HttpServletResponse response, String lang, String message1, String message2) throws IOException {
        if (lang == null || lang.equals("en")) {
            request.getSession().setAttribute("ex", message1);
        } else {
            request.getSession().setAttribute("ex", message2);
        }
        response.sendRedirect(request.getContextPath() + "/orders");
    }
}
