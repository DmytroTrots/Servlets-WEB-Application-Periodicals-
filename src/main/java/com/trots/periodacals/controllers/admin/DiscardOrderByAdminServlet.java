package com.trots.periodacals.controllers.admin;

import com.trots.periodacals.entity.User;
import com.trots.periodacals.service.ReceiptService;
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
 * The type Discard order by admin servlet.
 */
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
        String lang = (String) req.getSession().getAttribute("lang");
        ReceiptService.getInstance().discardOrderByAdmin(id);
        User user = UserService.getInstance().getSingleUserById(userId);
        Double actualBalance = user.getBalance();
        actualBalance = actualBalance + price;
        UserService.getInstance().updateFieldBalanceAfterTopUp(userId, actualBalance);
        log.trace("Successfully --> order " + id + " discarded");
        langCheck(req, resp, lang, "Order was discarded", "Замовлення було відхилено");
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

