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

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String password = request.getParameter("password");
        String role = request.getParameter("nameOfRole");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");

        String lang = (String) request.getSession().getAttribute("lang");

        User userCheckUsername = UserService.getInstance().getSingleUserByUsername(username);
        User userCheckMail = UserService.getInstance().getSingleUserByEmail(email);
        User userCheckTelephone = UserService.getInstance().getSingleUserByTelephone(telephone);

        if (userCheckUsername != null) {
            langCheck(request, response, lang, "User with such username already exist", "Користувач з таким логіном вже існує");
        } else if (userCheckMail != null) {
            langCheck(request, response, lang, "User with such email already exist", "Користувач з таким емейлом вже існує");
        } else if (userCheckTelephone != null) {
            langCheck(request, response, lang, "User with such telephone already exist", "Користувач з таким телефоном вже існує");
        } else if (!username.matches("[a-zA-Z0-9]{6,18}")) {
            langCheck(request, response, lang, "Incorrect username format", "Неправильний формат імені користувача");
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            langCheck(request, response, lang, "Incorrect email format", "Неправильний формат електронної пошти");
        } else if (!telephone.matches("[0-9]{11,12}")) {
            langCheck(request, response, lang, "Incorrect telephone format", "Неправильний формат телефону");
        } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}$")) {
            langCheck(request, response, lang, "Incorrect password format", "Неправильний формат паролю");
        } else if (!name.matches("[а-яА-ЯёЁa-zA-Z]{1,25}")) {
            langCheck(request, response, lang, "Incorrect name format", "Неправильний формат імені");
        } else if (!surname.matches("[а-яА-ЯёЁa-zA-Z]{1,25}")) {
            langCheck(request, response, lang, "Incorrect surname format", "Неправильний формат фамілії");
        } else if (address.length() > 1024) {
            langCheck(request, response, lang, "Incorrect address format", "Неправильний формат адреси");
        } else {
            user.setUsername(username);
            user.setEmail(email);
            try {
                password = PBKDF2PasswordHashing.generateStorngPasswordHash(password);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                log.error("Cant hash password");
            }
            user.setPassword(password);
            user.setTelephone(telephone);
            user.setName(name);
            user.setSurname(surname);
            user.setRole(role);
            user.setAddress(address);

            UserService.getInstance().addUser(user);
            log.trace("Successfully --> adding new user --> " + user.getUsername());

            response.sendRedirect(request.getContextPath() + "/addUser");
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
