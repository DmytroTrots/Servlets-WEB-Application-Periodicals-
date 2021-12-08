package com.trots.periodacals.controllers.user;

import com.trots.periodacals.entity.User;
import com.trots.periodacals.service.UserService;
import com.trots.periodacals.util.CreateReportOrders;
import com.trots.periodacals.util.DeleteSubscriptionsSchedule;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * The type Login servlet.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(LoginServlet.class);

    @Override
    public void init() {
        Timer timer = new Timer();
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal2.set(Calendar.DAY_OF_MONTH, 1);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 1);

        Date timeOnRun = cal.getTime();
        Date timeOnRun2 = cal2.getTime();
        CreateReportOrders createReportOrders = new CreateReportOrders();
        DeleteSubscriptionsSchedule deleteSubscriptionsSchedule = new DeleteSubscriptionsSchedule();
        timer.schedule(deleteSubscriptionsSchedule, timeOnRun2);
        timer.schedule(createReportOrders, timeOnRun);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("ID") != null) {
            response.sendRedirect("/shop?currentPage=1&category=0");
        } else {
            System.out.println("Login#doGet");

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/loginPage.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        log.trace("login --> " + username);

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        List<User> userList = UserService.getInstance().findAllUsers();

        try {
            String userValidate = UserService.getInstance().authenticateUser(user);

            if (userValidate.equals("admin") || userValidate.equals("customer")) {
                HttpSession session = request.getSession(); //Creating a session
                session.setAttribute("Role", userValidate); //setting session attribute
                for (User u : userList) {
                    if (u.getUsername().equals(username)) {
                        session.setAttribute("ID", u.getId());
                    }
                }
                request.getSession().setAttribute("userName", username);

                log.trace("Success user validation, user --> " + username + ", role --> " + userValidate);

                response.sendRedirect(request.getContextPath() + "/shop?currentPage=1&category=0");
            } else {
                System.out.println("Error message = " + userValidate);
                request.getSession().setAttribute("ex", userValidate);

                log.trace("Can not validate user --> " + username);

                request.getRequestDispatcher("/WEB-INF/views/loginPage.jsp").forward(request, response);
            }
        } catch (IOException e1) {
            log.error("Cannot enter to the shop page");
        } catch (ServletException e) {
            log.error(e);
        }
    }
}
