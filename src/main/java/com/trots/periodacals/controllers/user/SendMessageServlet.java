package com.trots.periodacals.controllers.user;

import com.trots.periodacals.util.Mailer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/send-message")
public class SendMessageServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(SendMessageServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/sendMessagePage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String to = req.getParameter("to");
        String subject = req.getParameter("subject");
        String message = req.getParameter("message");

        try {
            Mailer.send(to, subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/send-message");
    }
}