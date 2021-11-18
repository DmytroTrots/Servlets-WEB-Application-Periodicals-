package com.trots.periodacals.controllers.user;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(LoginServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Login#doGet");

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/loginPage.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        log.trace("login --> " + username );

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        List<User> userList = UserDaoImpl.getInstance().findAllUsers();

        try
        {
            String userValidate = UserDaoImpl.getInstance().authenticateUser(user);

            if(userValidate.equals("admin"))
            {
                System.out.println("Admin's Home");

                HttpSession session = request.getSession(); //Creating a session
                session.setAttribute("Role", userValidate); //setting session attribute
                for (User u : userList){
                    if (u.getUsername().equals(username)){
                        session.setAttribute("ID", u.getId());
                    }
                }
                request.getSession().setAttribute("userName", username);

                log.trace("Success user validation, user --> " + username + ", role --> admin");

                response.sendRedirect(request.getContextPath() + "/shop?currentPage=1&category=0");
            }
            else if(userValidate.equals("manager"))
            {
                System.out.println("Editor's Home");

                HttpSession session = request.getSession();
                session.setAttribute("Role", userValidate);
                for (User u : userList){
                    if (u.getUsername().equals(username)){
                        session.setAttribute("ID", u.getId());
                    }
                }
                request.getSession().setAttribute("userName", username);

                log.trace("Success user validation, user --> " + username + ", role --> manager");

                response.sendRedirect(request.getContextPath() + "/shop?currentPage=1&category=0");
            }
            else if(userValidate.equals("customer"))
            {
                System.out.println("User's Home");

                HttpSession session = request.getSession();
                session.setAttribute("Role", userValidate);
                for (User u : userList){
                    if (u.getUsername().equals(username)){
                        session.setAttribute("ID", u.getId());
                    }
                }
                request.getSession().setAttribute("userName", username);

                log.trace("Success user validation, user --> " + username + ", role --> customer");

                response.sendRedirect(request.getContextPath() + "/shop?currentPage=1&category=0");
            }
            else
            {
                System.out.println("Error message = "+userValidate);
                request.setAttribute("ex", userValidate);

                log.trace("Can not validate user --> " + username);

                request.getRequestDispatcher("/WEB-INF/views/loginPage.jsp").forward(request, response);
            }
        } catch (IOException e1)
        {
            log.error("Cannot enter to the shop page");
        } catch (ServletException e) {
            log.error(e);
        }
    } //End of doPost()
}
