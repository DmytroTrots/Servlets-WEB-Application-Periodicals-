package com.trots.periodacals.controllers.user;


import com.trots.periodacals.entity.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Month inc dec servlet.
 */
@WebServlet("/inc-dec")
public class MonthIncDecServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(MonthIncDecServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));

        ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

        if (action != null && id >= 1) {
            if (action.equals("inc")) {
                for (Cart c : cart_list) {
                    if (c.getSellId() == id && c.getMonths() < 12) {
                        int month = c.getMonths();
                        month++;
                        c.setMonths(month);
                        break;
                    }
                }
                response.sendRedirect(request.getContextPath() + "/cart");
            }
            else if (action.equals("dec")) {
                for (Cart c : cart_list) {
                    if (c.getSellId() == id && c.getMonths() > 1) {
                        int month = c.getMonths();
                        month--;
                        c.setMonths(month);
                        break;
                    }
                }
                response.sendRedirect(request.getContextPath() + "/cart");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/cart");
        }
    }
}
