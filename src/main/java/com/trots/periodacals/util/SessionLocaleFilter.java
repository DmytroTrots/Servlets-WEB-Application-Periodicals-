package com.trots.periodacals.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Session locale filter.
 */
@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getParameter("sessionLocale") != null) {
            req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
        }
        chain.doFilter(request, response);
    }

}
