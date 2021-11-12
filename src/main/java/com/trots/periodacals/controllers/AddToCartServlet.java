package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.UserDaoImpl;
import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "cartPage", urlPatterns = "/cart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ArrayList<Cart> cartList = new ArrayList<>();

            int id = Integer.parseInt(request.getParameter("id"));
            Integer currentPage = (Integer) request.getSession().getAttribute("currentPage");

            Cart cm = new Cart();
            cm.setSellId(id);
            cm.setMonths(1);

            HttpSession session = request.getSession();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

            if (cart_list == null) {
                cartList.add(cm);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect(request.getContextPath()+"/shop?currentPage="+currentPage+"&category="+request.getSession().getAttribute("category"));
            } else {
                cartList = cart_list;
                boolean exist = false;
                for (Cart c : cartList) {
                    if (c.getSellId()==id){
                        exist = true;
                        out.println("<h3 style='text-align:center'>Item already exist in <a href='cartPage.jsp'>your Cart</a><h3>");
                    }
                }
                if(!exist){
                    cartList.add(cm);
                    response.sendRedirect(request.getContextPath()+"/shop?currentPage="+currentPage+"&category="+request.getSession().getAttribute("category"));
                }
            }

        }
    }
}
