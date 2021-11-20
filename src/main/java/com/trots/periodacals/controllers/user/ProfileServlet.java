package com.trots.periodacals.controllers.user;

import com.trots.periodacals.daoimpl.ReceiptDaoImpl;
import com.trots.periodacals.entity.Receipt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ProfileServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = (Integer) req.getSession().getAttribute("ID");
        if (id != null) {
            List<Receipt> receiptList = ReceiptDaoImpl.getInstance().getAllOrdersOfUserById(id);
            log.trace("Successfully --> get orders of user for profile page");
            if (!receiptList.isEmpty()) {
                req.setAttribute("receiptList", receiptList);
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/profilePage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}