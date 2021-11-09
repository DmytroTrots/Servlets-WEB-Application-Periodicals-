package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.PeriodicalsDaoImpl;
import com.trots.periodacals.daoimpl.ReceiptDaoImpl;
import com.trots.periodacals.daoimpl.UserDaoImpl;
import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.entity.Receipt;
import com.trots.periodacals.entity.User;

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


                UserDaoImpl userDao = new UserDaoImpl();
                PeriodicalsDaoImpl periodicalsDao = new PeriodicalsDaoImpl();
                Double price = periodicalsDao.getPriceById(periodicalId);
                Double actualBalance = (Double) req.getSession().getAttribute("balance");
                if (actualBalance > price * numberOfMonths) {
                    Receipt receipt = new Receipt();
                    receipt.setName(name);
                    receipt.setAdress(address);
                    receipt.setMonths(numberOfMonths);
                    receipt.setPrice(price*numberOfMonths);
                    receipt.setSurname(surname);
                    receipt.setEmail(email);
                    receipt.setTelephoneNumber(telephone);
                    receipt.setPeriodicalId(periodicalId);
                    receipt.setUserId(id);

                    actualBalance = actualBalance-(price*numberOfMonths);
                    userDao.updateFieldBalanceAfterTopUp(id, actualBalance);

                    ReceiptDaoImpl receiptDao = new ReceiptDaoImpl();
                    boolean result = receiptDao.insertReceiptAfterPayment(receipt);

                    if (result) {
                        ArrayList<Cart> cart_list = (ArrayList<Cart>) req.getSession().getAttribute("cart-list");
                        if (cart_list != null) {
                            for (Cart c : cart_list) {
                                if (c.getSellId() == periodicalId) {
                                    cart_list.remove(cart_list.indexOf(c));
                                    break;
                                }
                            }
                        }
                        resp.sendRedirect(req.getContextPath() + "/shop");
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
