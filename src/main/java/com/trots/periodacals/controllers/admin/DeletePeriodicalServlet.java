package com.trots.periodacals.controllers.admin;

import com.trots.periodacals.service.PeriodicalService;
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
 * The type Delete periodical servlet.
 */
@WebServlet("/delete-periodical")
public class DeletePeriodicalServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(DeletePeriodicalServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/shopPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        PeriodicalService.getInstance().deletePeriodicalFromAdminPage(id);
        log.trace("Successfully --> periodical " + id + " deleted");

        resp.sendRedirect(req.getContextPath() + "/fileupload");

    }
}
