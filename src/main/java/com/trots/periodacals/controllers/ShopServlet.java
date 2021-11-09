package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.PeriodicalsDaoImpl;
import com.trots.periodacals.daoimpl.UserDaoImpl;
import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PeriodicalsDaoImpl pd = new PeriodicalsDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        List<Periodical> periodicalList = pd.getAllPeriodicals();
        request.setAttribute("PERIODICAL", periodicalList);

        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("ID");

        User user = null;
        if (id!=null){
            user = userDao.getSingleUserById(id);
        }
        request.getSession().setAttribute("user", user);

        if (id != null){
            Double actualBalance = userDao.getBalanceOfUserBiId(id);
            session.setAttribute("balance", actualBalance);
        }
        ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
        if (cart_list != null) {
            session.setAttribute("cart_list", cart_list);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/shopPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
