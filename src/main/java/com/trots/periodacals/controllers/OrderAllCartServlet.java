package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.PeriodicalsDaoImpl;
import com.trots.periodacals.daoimpl.ReceiptDaoImpl;
import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Receipt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/order-all")
public class OrderAllCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()){
            ArrayList<Cart> cart_list = (ArrayList<Cart>) req.getSession().getAttribute("cart_list");

            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            String telephone = req.getParameter("telephone");
            String address = req.getParameter("address");

            Integer id = (Integer) req.getSession().getAttribute("ID");
            PeriodicalsDaoImpl periodicalsDao = new PeriodicalsDaoImpl();
            Double actualBalance = (Double) req.getSession().getAttribute("balance");
            Double totalPrice = (Double) req.getSession().getAttribute("totalPrice");
            if (cart_list !=null && id !=null && actualBalance>totalPrice){
                for (Cart c : cart_list){
                    Receipt receipt = new Receipt();
                    receipt.setPeriodicalId(c.getSellId());
                    Double price = periodicalsDao.getPriceById(c.getSellId())*c.getMonths();
                    receipt.setUserId(id);
                    receipt.setMonths(c.getMonths());
                    receipt.setPrice(price);
                    receipt.setName(name);
                    receipt.setAdress(address);
                    receipt.setSurname(surname);
                    receipt.setEmail(email);
                    receipt.setTelephoneNumber(telephone);

                    ReceiptDaoImpl receiptDao = new ReceiptDaoImpl();
                    boolean result = receiptDao.insertReceiptAfterPayment(receipt);
                    if(!result){
                        break;
                    }
                }
                cart_list.clear();
                resp.sendRedirect("cartPage.jsp");
            }else{
                if (id == null){
                    resp.sendRedirect(req.getContextPath() + "/login");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
