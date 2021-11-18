package com.trots.periodacals.controllers.admin;

import com.trots.periodacals.daoimpl.UserDaoImpl;
import com.trots.periodacals.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        User user = UserDaoImpl.getInstance().getSingleUserById(id);
        String banStatus = user.getBanStatus();
        if (banStatus == null) {
            UserDaoImpl.getInstance().updateBanStatusOfUser("banned", id);
            log.trace("Successfully --> user " + id + " banned");
        } else {
            UserDaoImpl.getInstance().updateBanStatusOfUser(null, id);
            log.trace("Successfully --> user " + id + " unbanned");
        }
        resp.sendRedirect(req.getContextPath() + "/addUser");
    }
}
