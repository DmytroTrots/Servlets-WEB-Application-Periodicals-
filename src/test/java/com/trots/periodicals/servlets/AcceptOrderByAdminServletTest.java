package com.trots.periodicals.servlets;

import com.trots.periodacals.controllers.admin.AcceptOrderByAdminServlet;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcceptOrderByAdminServletTest {

    @Test
    public void doGetTest() throws ServletException, IOException {
        final AcceptOrderByAdminServlet acceptOrderByAdminServlet = new AcceptOrderByAdminServlet();

        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(request.getRequestDispatcher("WEB-INF/views/shopPage.jsp")).thenReturn(dispatcher);

        acceptOrderByAdminServlet.doGet(request, response);
        Mockito.verify(request, Mockito.never()).getSession();
        Mockito.verify(dispatcher).forward(request,response);
    }

}
