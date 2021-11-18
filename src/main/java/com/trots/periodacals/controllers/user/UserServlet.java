package com.trots.periodacals.controllers.user;

import com.trots.periodacals.daoimpl.UserDaoImpl;
import com.trots.periodacals.entity.User;
import com.trots.periodacals.util.PBKDF2PasswordHashing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet("/registration")
public class UserServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/registrationPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = new User();

        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        String password = null;
        try {
            password = PBKDF2PasswordHashing.generateStorngPasswordHash(request.getParameter("password"));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Cant hash password");
        }
        user.setPassword(password);
        user.setTelephone(request.getParameter("telephone"));
        user.setRole(request.getParameter("role"));
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setAddress(request.getParameter("address"));

        UserDaoImpl.getInstance().addUser(user);

        log.trace("User " + request.getParameter("username") + " registered");

        response.sendRedirect(request.getContextPath() + "/login");
    }
}
