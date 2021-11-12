package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.PeriodicalsDaoImpl;
import com.trots.periodacals.daoimpl.SubjectDaoImpl;
import com.trots.periodacals.daoimpl.SubjectPeriodicalsDaoImpl;
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
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Integer> subjectsMap = SubjectDaoImpl.getInstance().findAllSubjectsFromDB();
        request.setAttribute("subjectMap", subjectsMap);
        int category = Integer.parseInt(request.getParameter("category"));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = 12;
        String searchField = (String) request.getSession().getAttribute("searchField");

        String sort = (String) request.getSession().getAttribute("sort");

        List<Periodical> paginList;

        if (category == 0) {
            paginList = PeriodicalsDaoImpl.getInstance().getRecordsForPagination();
        } else {
            paginList = PeriodicalsDaoImpl.getInstance().getRecordsForPaginationBySubject(category);
        }

        if (searchField != null && !searchField.equals("")) {
            paginList.clear();
            Periodical periodical = PeriodicalsDaoImpl.getInstance().getRecordPeriodicalByName(searchField);
            if (periodical != null) {
                int periodicalId = periodical.getSellId();
                List<String> listOfPeriodicalSubject = SubjectPeriodicalsDaoImpl.getInstance().findAllSubjectOfPeriodicalById(periodicalId);
                {
                    for (String s : listOfPeriodicalSubject) {
                        if (subjectsMap.get(s) == category || category == 0) {
                            paginList.add(periodical);
                            break;
                        }
                    }
                }
            }
        }

        if (!(sort == null)) {
            if (sort.equals("ws")) {
                paginList.stream().sorted();
            } else if (sort.equals("prLtH")) {
                paginList.sort(Comparator.comparing(Periodical::getPricePerMonth));
            } else if (sort.equals("prHtL")) {
                paginList.sort(Comparator.comparing(Periodical::getPricePerMonth).reversed());
            } else if (sort.equals("nza")) {
                paginList.sort(Comparator.comparing(Periodical::getTitle).reversed());
            } else if (sort.equals("naz")) {
                paginList.sort(Comparator.comparing(Periodical::getTitle));
            } else if (sort.equals("rating")) {
                paginList.sort(Comparator.comparing(Periodical::getRating).reversed());
            }
        }

        int rows = paginList.size();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {

            nOfPages++;
        }

        if ((currentPage - 1) == (rows / recordsPerPage)) {
            paginList = paginList.subList((currentPage * recordsPerPage - recordsPerPage), paginList.size());
        } else {
            paginList = paginList.subList((currentPage * recordsPerPage - recordsPerPage), currentPage * recordsPerPage);
        }

        request.setAttribute("PERIODICAL", paginList);
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("ID");


        request.getSession().setAttribute("category", category);
        request.setAttribute("noOfPages", nOfPages);
        request.getSession().setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);


        User user = null;
        if (id != null) {
            user = UserDaoImpl.getInstance().getSingleUserById(id);
            if (user.getBanStatus() == null) {
                request.getSession().setAttribute("user", user);
            } else {
                request.setAttribute("errMessage", "You are banned, sorry");
                request.getRequestDispatcher("/WEB-INF/views/loginPage.jsp").forward(request, response);
            }
        }


        if (id != null) {
            Double actualBalance = UserDaoImpl.getInstance().getBalanceOfUserById(id);
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
        req.getSession().setAttribute("sort", req.getParameter("sort"));
        req.getSession().setAttribute("searchField", req.getParameter("searchField"));
        doGet(req, resp);
    }
}
