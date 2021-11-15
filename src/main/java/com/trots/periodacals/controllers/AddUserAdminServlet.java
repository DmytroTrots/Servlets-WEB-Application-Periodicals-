package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.UserDaoImpl;
import com.trots.periodacals.entity.User;
import com.trots.periodacals.util.CheckRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/addUser")
public class AddUserAdminServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AddUserAdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckRole checkRoleObj = new CheckRole();
        checkRoleObj.checkRole(request, response, log);
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> list = userDao.findAllUsers();
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
        user.setPassword(request.getParameter("password"));
        user.setTelephone(request.getParameter("telephone"));
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setRole(request.getParameter("nameOfRole"));
        user.setAddress(request.getParameter("address"));

        UserDaoImpl.getInstance().addUser(user);
        log.trace("Successfully --> adding new user --> " + user.getUsername());

        response.sendRedirect(request.getContextPath() + "/addUser");
    }
}
