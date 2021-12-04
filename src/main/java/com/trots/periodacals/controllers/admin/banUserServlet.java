package com.trots.periodacals.controllers.admin;

import com.trots.periodacals.entity.User;
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
 * The type Ban user servlet.
 */
@WebServlet("/ban-user")
public class banUserServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(banUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/shopPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String lang = (String) req.getSession().getAttribute("lang");
        User user = UserService.getInstance().getSingleUserById(id);
        String banStatus = user.getBanStatus();
        if (banStatus == null) {
            UserService.getInstance().updateBanStatusOfUser("banned", id);
            langCheck(req, resp, lang, "User was banned", "Користувач був заблокований");
            log.trace("Successfully --> user " + id + " banned");
        } else {
            UserService.getInstance().updateBanStatusOfUser(null, id);
            langCheck(req, resp, lang, "User was unbanned", "Користувач був розблокований");
            log.trace("Successfully --> user " + id + " unbanned");
        }
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
