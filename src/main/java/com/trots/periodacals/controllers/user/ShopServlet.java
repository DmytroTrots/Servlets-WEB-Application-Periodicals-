package com.trots.periodacals.controllers.user;

import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.entity.User;
import com.trots.periodacals.service.PeriodicalService;
import com.trots.periodacals.service.SubjectService;
import com.trots.periodacals.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ShopServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Integer> subjectsMap = SubjectService.getInstance().findAllSubjectsFromDB();
        request.setAttribute("subjectMap", subjectsMap);

        int category = Integer.parseInt(request.getParameter("category"));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int recordsPerPage = 12;
        String url = request.getRequestURI();
        String searchField = (String) request.getSession().getAttribute("searchField");
        String sort = (String) request.getSession().getAttribute("sort");
        log.debug("category --> " + category + "; current page --> " + currentPage + "; url --> " + url + "; searchField -->" + searchField + "; sort --> " + sort + ".");

        StringBuilder query = new StringBuilder("SELECT `periodical`.`sell_id`, periodical.rating, `periodical`.`title`, `periodical`.`price_per_month`,`periodical`.`images`, `publisher`.`name` FROM periodical_has_subject JOIN periodical ON periodical_has_subject.periodical_id = periodical.sell_id JOIN publisher ON periodical.publisher_id = publisher.id JOIN `subject` ON periodical_has_subject.subject_id = `subject`.id");

        ///Choose periodicals according to category and title
        if (category != 0 && searchField != null && !searchField.equals("")) {
            query.append(" WHERE `subject`.id = ").append(category).append(" and `periodical`.`title` like").append(" '%").append(searchField).append("%'");
        } else if (category != 0 && (searchField == null || searchField.equals(""))) {
            query.append(" WHERE `subject`.id = ").append(category);
        } else if (category == 0 && searchField != null && !searchField.equals("")) {
            query.append(" WHERE `periodical`.`title` like").append(" '%").append(searchField).append("%'");
        }

        query.append(" group by periodical.sell_id");
        ///Sorting according to user's choice
        if (!(sort == null)) {
            if (sort.equals("ws")) {
            } else if (sort.equals("prLtH")) {
                query.append(" order by price_per_month asc");
            } else if (sort.equals("prHtL")) {
                query.append(" order by price_per_month desc");
            } else if (sort.equals("nza")) {
                query.append(" order by title desc");
            } else if (sort.equals("naz")) {
                query.append(" order by title asc");
            } else if (sort.equals("rating")) {
                query.append(" order by rating desc");
            }
        }

        int rows = PeriodicalService.getInstance().getNumbersOfRows();
        log.trace("Successfully --> get numbers of rows of periodicals --> " + rows);
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        ///If last page -> subList with list.size() to avoid index exception
        List<Periodical> paginList = new ArrayList<>();
        if (rows >= 12) {
            if ((currentPage - 1) == (rows / recordsPerPage)) {
                query.append(" limit ").append(currentPage * recordsPerPage - recordsPerPage).append(",").append(rows);
            } else {
                query.append(" limit ").append(currentPage * recordsPerPage - recordsPerPage).append(",").append(currentPage * recordsPerPage);
            }
            paginList = PeriodicalService.getInstance().getRecordsForPagination(String.valueOf(query));
            log.trace("Successfully --> get list of periodicals --> " + paginList.size());
        }

        if (paginList.size() != 12) {
            nOfPages = currentPage;
        } else if (paginList.size() == 0) {
            nOfPages = 0;
        }

        Integer id = (Integer) request.getSession().getAttribute("ID");
        request.setAttribute("url", url);
        request.setAttribute("PERIODICAL", paginList);
        request.getSession().setAttribute("category", category);
        request.setAttribute("noOfPages", nOfPages);
        request.getSession().setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);


        ///Check if user is banned
        if (id != null) {
            User user = UserService.getInstance().getSingleUserById(id);
            if (user.getBanStatus() == null) {
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("balance", user.getBalance());
            } else {
                request.setAttribute("ex", "You are banned, sorry");
                request.getSession().invalidate();
                request.getRequestDispatcher("WEB-INF/views/loginPage.jsp").forward(request, response);
            }
        }

        List<Cart> cart_list = (List<Cart>) request.getSession().getAttribute("cart-list");

        if (cart_list != null) {
            request.getSession().setAttribute("cart_list", cart_list);
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
