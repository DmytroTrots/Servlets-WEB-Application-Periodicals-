package com.trots.periodacals.controllers.user;

import com.trots.periodacals.daoimpl.PeriodicalsDaoImpl;
import com.trots.periodacals.entity.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "cartPage", urlPatterns = "/cart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LogManager.getLogger(AddToCartServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DecimalFormat dcf = new DecimalFormat("#.##");
        request.setAttribute("decimalFormat", dcf);
        List<Cart> cart_list = (List<Cart>) request.getSession().getAttribute("cart-list");
        List<Cart> cartPeriodical = null;
        if (cart_list != null) {
            cartPeriodical = PeriodicalsDaoImpl.getInstance().getAllCartPeriodical(cart_list);
            request.getSession().setAttribute("cartPeriodical", cartPeriodical);
            request.setAttribute("cart_list", cart_list);
            double total = PeriodicalsDaoImpl.getInstance().getTotalPriceOfCart(cart_list);
            request.getSession().setAttribute("totalPrice", total);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/cartPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                        out.println("Item already exist in <a href='cartPage.jsp'></a>");
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
