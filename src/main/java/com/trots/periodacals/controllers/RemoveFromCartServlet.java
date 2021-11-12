package com.trots.periodacals.controllers;

import com.trots.periodacals.entity.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/remove-record")
public class RemoveFromCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            if (cart_list != null) {
                for (Cart c : cart_list) {
                    if (c.getSellId() == Integer.parseInt(id)) {
                        cart_list.remove(cart_list.indexOf(c));
                        break;
                    }
                }
                response.sendRedirect("cartPage.jsp");
            }
        } else {
            response.sendRedirect("cartPage.jsp");
        }
    }

}
