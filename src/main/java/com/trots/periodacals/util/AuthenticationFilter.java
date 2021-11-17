package com.trots.periodacals.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/addUser", "/fileupload", "/update-periodical", "/delete-periodical", "/ban-user", "/order"}, initParams = @WebInitParam(name = "role", value = "admin"))
public class AuthenticationFilter implements Filter {

    private static final Logger log = LogManager.getLogger(AuthenticationFilter.class);

    private String role;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        role = filterConfig.getInitParameter("role");
        log.trace("Filter#Role was initialized --> " + role);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String roleRequest = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("Role");

        if(roleRequest == null){
            System.out.println("U r't logged in");
            servletRequest.setAttribute("ex", "U r't logged in");
            RequestDispatcher rd= servletRequest.getRequestDispatcher("error.jsp");
            rd.include(servletRequest, servletResponse);
        }
        else if(roleRequest.equals(role)){
            filterChain.doFilter(servletRequest, servletResponse);//sends request to next resource
        }
        else{
            System.out.println("Access problem");
            servletRequest.setAttribute("ex", "U are not allowed to visit this page");
            RequestDispatcher rd= servletRequest.getRequestDispatcher("error.jsp");
            rd.include(servletRequest, servletResponse);
        }
    }

}
