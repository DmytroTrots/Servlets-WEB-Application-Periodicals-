package com.trots.periodacals.util;

import com.trots.periodacals.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

@WebFilter(urlPatterns = "/*")
public class BannedAndNotLoggedUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Integer id = (Integer) ((HttpServletRequest) servletRequest).getSession().getAttribute("ID");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();
        String lang = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("lang");

        if(path.endsWith(".css")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        Properties properties;
        if (path.equals("/login") || path.equals("/registration") || path.equals("/") || path.equals("/shop") || path.equals("/cart") || path.equals("/logout")){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if(id == null){
            servletRequest.setAttribute("ex", "You are not logged in");
            RequestDispatcher rd= servletRequest.getRequestDispatcher("WEB-INF/views/error.jsp");
            rd.include(servletRequest, servletResponse);
        }
        else if(UserService.getInstance().getSingleUserById(id).getBanStatus() != null){
            servletRequest.setAttribute("ex", "You are banned");
            RequestDispatcher rd= servletRequest.getRequestDispatcher("WEB-INF/views/error.jsp");
            rd.include(servletRequest, servletResponse);
        }
        else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
