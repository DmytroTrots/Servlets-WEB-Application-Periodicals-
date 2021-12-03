package com.trots.periodacals.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Authentication filter.
 */
@WebFilter(urlPatterns = {"/addUser", "/fileupload", "/update-periodical", "/delete-periodical", "/ban-user", "/orders"}, initParams = @WebInitParam(name = "role", value = "admin"))
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
        Integer id = (Integer) ((HttpServletRequest) servletRequest).getSession().getAttribute("ID");
        String lang = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("lang");
        servletResponse.setCharacterEncoding("UTF-16");
        PrintWriter out = servletResponse.getWriter();


        if(id == null){
            if(lang == null || lang.equals("en")){
                out.println("You are not logged in");
            }
            else{
                out.println("Ви не уввійшли у систему");
            }
        }
        if(roleRequest.equals(role)){
            filterChain.doFilter(servletRequest, servletResponse);//sends request to next resource
        }
        else{
            if(lang == null || lang.equals("en")){
                out.println("U are not allowed to visit this page");
            }
            else{
                out.println("Вам заборонено відвідувати цю сторінку");
            }
        }
    }

}
