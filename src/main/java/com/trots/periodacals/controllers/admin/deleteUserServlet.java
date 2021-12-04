package com.trots.periodacals.controllers.admin;

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

/**
 * The type Delete user servlet.
 */
@WebServlet("/delete-user")
public class deleteUserServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(deleteUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/shopPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        UserService.getInstance().deleteUserFromAdminPage(id);
        String lang = (String) req.getSession().getAttribute("lang");
        log.trace("Successfully --> user " + id + " deleted");
        langCheck(req, resp, lang, "User was deleted", "Користувач був видалений");
    }

    private void langCheck(HttpServletRequest request, HttpServletResponse response, String lang, String message1, String message2) throws IOException {
        if (lang == null || lang.equals("en")) {
            request.getSession().setAttribute("ex", message1);
        } else {
            request.getSession().setAttribute("ex", message2);
        }
        response.sendRedirect("/addUser");
    }
}
