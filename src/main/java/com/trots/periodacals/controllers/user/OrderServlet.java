package com.trots.periodacals.controllers.user;

import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Receipt;
import com.trots.periodacals.service.PeriodicalService;
import com.trots.periodacals.service.ReceiptHasPeriodicalService;
import com.trots.periodacals.service.ReceiptService;
import com.trots.periodacals.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The type Order servlet.
 */
@WebServlet("/order-periodical")
public class OrderServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(OrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = (Integer) req.getSession().getAttribute("ID");
        if (id != null) {
            int periodicalId = Integer.parseInt(req.getParameter("id"));
            int numberOfMonths = Integer.parseInt(req.getParameter("month"));
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            String telephone = req.getParameter("telephone");
            String address = req.getParameter("address");
            String lang = (String) req.getSession().getAttribute("lang");
            if (numberOfMonths <= 0) {
                numberOfMonths = 1;
            }

            Integer currentPage = (Integer) req.getSession().getAttribute("currentPage");

            Double price = PeriodicalService.getInstance().getPriceById(periodicalId);
            Double actualBalance = (Double) req.getSession().getAttribute("balance");
            if (actualBalance > price * numberOfMonths) {
                Receipt receipt = new Receipt();
                receipt.setName(name);
                receipt.setAdress(address);
                receipt.setMonths(numberOfMonths);
                receipt.setPricePerMonth(price * numberOfMonths);
                receipt.setSurname(surname);
                receipt.setEmail(email);
                receipt.setTelephoneNumber(telephone);
                receipt.setPeriodicalSellId(periodicalId);
                receipt.setUserId(id);
                log.trace("User " + req.getSession().getAttribute("userName") + " ordered periodical " + periodicalId);

                actualBalance = actualBalance - (price * numberOfMonths);
                UserService.getInstance().updateFieldBalanceAfterTopUp(id, actualBalance);

                Integer receiptID = ReceiptService.getInstance().insertReceiptAfterPayment(receipt);

                if (receiptID != null) {
                    ReceiptHasPeriodicalService.getInstance().insertReceiptAndPeriodical(receipt, receiptID);
                    List<Cart> cart_list = (List<Cart>) req.getSession().getAttribute("cart-list");
                    if (cart_list != null) {
                        for (Cart c : cart_list) {
                            if (c.getSellId() == periodicalId) {
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }
                    langCheck(req, currentPage, resp, lang, "Periodical was successfully ordered", "Видання успішно було замовлене");
                }
            } else {
                langCheck(req, currentPage, resp, lang, "You cant order this periodical, check balance", "Ви не можете самовити це видання, перевірте баланс");
            }

        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void langCheck(HttpServletRequest request, int currentPage, HttpServletResponse response, String lang, String message1, String message2) throws IOException {
        if (lang == null || lang.equals("en")) {
            request.getSession().setAttribute("ex", message1);
        } else {
            request.getSession().setAttribute("ex", message2);
        }
        response.sendRedirect(request.getContextPath() + "/shop?currentPage=" + currentPage + "&category=" + request.getSession().getAttribute("category"));
    }
}
