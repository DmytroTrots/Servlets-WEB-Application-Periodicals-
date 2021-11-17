package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.PeriodicalsDaoImpl;
import com.trots.periodacals.daoimpl.ReceiptDaoImpl;
import com.trots.periodacals.daoimpl.ReceiptHasPeriodicalDaoImpl;
import com.trots.periodacals.daoimpl.UserDaoImpl;
import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Receipt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/order-periodical")
public class OrderServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(OrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()) {
            Integer id = (Integer) req.getSession().getAttribute("ID");
            if (id != null) {
                int periodicalId = Integer.parseInt(req.getParameter("id"));
                int numberOfMonths = Integer.parseInt(req.getParameter("month"));
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                String email = req.getParameter("email");
                String telephone = req.getParameter("telephone");
                String address = req.getParameter("address");
                if (numberOfMonths <= 0) {
                    numberOfMonths = 1;
                }

                Integer currentPage = (Integer) req.getSession().getAttribute("currentPage");

                Double price = PeriodicalsDaoImpl.getInstance().getPriceById(periodicalId);
                Double actualBalance = (Double) req.getSession().getAttribute("balance");
                if (actualBalance > price * numberOfMonths) {
                    Receipt receipt = new Receipt();
                    receipt.setName(name);
                    receipt.setAdress(address);
                    receipt.setMonths(numberOfMonths);
                    receipt.setPricePerMonth(price*numberOfMonths);
                    receipt.setSurname(surname);
                    receipt.setEmail(email);
                    receipt.setTelephoneNumber(telephone);
                    receipt.setPeriodicalSellId(periodicalId);
                    receipt.setUserId(id);
                    log.trace("User " + req.getSession().getAttribute("userName") + " ordered periodical " + periodicalId);

                    actualBalance = actualBalance-(price*numberOfMonths);
                    UserDaoImpl.getInstance().updateFieldBalanceAfterTopUp(id, actualBalance);

                    Integer receiptID = ReceiptDaoImpl.getInstance().insertReceiptAfterPayment(receipt);

                    if (receiptID!=null) {
                        ReceiptHasPeriodicalDaoImpl.getInstance().insertReceiptAndPeriodical(receipt, receiptID);
                        ArrayList<Cart> cart_list = (ArrayList<Cart>) req.getSession().getAttribute("cart-list");
                        if (cart_list != null) {
                            for (Cart c : cart_list) {
                                if (c.getSellId() == periodicalId) {
                                    cart_list.remove(cart_list.indexOf(c));
                                    break;
                                }
                            }
                        }
                        resp.sendRedirect(req.getContextPath() + "/shop?currentPage="+currentPage+"&category="+req.getSession().getAttribute("category"));
                    }
                } else {
                    out.println("Failed");
                }

            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
