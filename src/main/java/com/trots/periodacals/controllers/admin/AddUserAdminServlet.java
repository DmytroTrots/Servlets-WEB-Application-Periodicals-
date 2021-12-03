package com.trots.periodacals.controllers.admin;

import com.trots.periodacals.entity.User;
import com.trots.periodacals.service.UserService;
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Add user admin servlet.
 */
@WebServlet("/addUser")
public class AddUserAdminServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AddUserAdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> list = UserService.getInstance().findAllUsers();
        request.setAttribute("USERS_LIST", list);

        Set<String> uniqueList = list.stream().map(User::getRole).collect(Collectors.toCollection(LinkedHashSet::new));

        request.setAttribute("ROLE_LIST", uniqueList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/addUserAdminPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setRole(request.getParameter("nameOfRole"));
        user.setAddress(request.getParameter("address"));

        UserService.getInstance().addUser(user);
        log.trace("Successfully --> adding new user --> " + user.getUsername());

        response.sendRedirect(request.getContextPath() + "/addUser");
    }
}
