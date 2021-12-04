package com.trots.periodacals.util;

import com.trots.periodacals.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * The type Banned and not logged user filter.
 */
@WebFilter(urlPatterns = {"/profile", "/send-message", "/order-periodical", "/buy-now", "/inc-dec", "/order-all", "/top-up"})
public class BannedAndNotLoggedUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Integer id = (Integer) ((HttpServletRequest) servletRequest).getSession().getAttribute("ID");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();
        String lang = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("lang");

        Properties properties;
        if (path.equals("/login") || path.equals("/registration") || path.equals("/") || path.equals("/shop") || path.equals("/cart") || path.equals("/logout")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (id == null) {
            if (lang == null || lang.equals("en")) {
                ((HttpServletRequest) servletRequest).getSession().setAttribute("ex", "You are not logged in");
            }else{
                ((HttpServletRequest) servletRequest).getSession().setAttribute("ex", "Ви не уввійшли у систему");
            }
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        } else if (UserService.getInstance().getSingleUserById(id).getBanStatus() != null) {
            ((HttpServletRequest) servletRequest).getSession().invalidate();
            if (lang == null || lang.equals("en")) {
                ((HttpServletRequest) servletRequest).getSession().setAttribute("ex", "You are banned");
            }else{
                ((HttpServletRequest) servletRequest).getSession().setAttribute("ex", "Вас було заблоковано");
            }
            ((HttpServletRequest) servletRequest).getSession().setAttribute("lang", lang);
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
