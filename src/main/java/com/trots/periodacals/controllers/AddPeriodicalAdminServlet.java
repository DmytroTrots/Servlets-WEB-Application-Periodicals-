package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.PeriodicalsDaoImpl;
import com.trots.periodacals.daoimpl.PublisherDaoImpl;
import com.trots.periodacals.daoimpl.SubjectDaoImpl;
import com.trots.periodacals.daoimpl.SubjectPeriodicalsDaoImpl;
import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.util.CheckRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/fileupload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, // 1 MB
        maxFileSize = 1024 * 1024 * 10    // 10 MB
)
public class AddPeriodicalAdminServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AddPeriodicalAdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckRole checkRoleObj = new CheckRole();
        checkRoleObj.checkRole(request, response, log);
        request.setAttribute("PERIODICAL", PeriodicalsDaoImpl.getInstance().getAllPeriodicals());
        Map<String, Integer> publisherMap = PublisherDaoImpl.getInstance().findAllPublishersWithoutTelephone();
        Map<String, Integer> subjectMap = SubjectDaoImpl.getInstance().findAllSubjectsFromDB();
        request.getSession().setAttribute("publisherMap", publisherMap);
        request.getSession().setAttribute("subjectMap", subjectMap);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/addPeriodicalAdminPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Integer> publisherMap = (Map<String, Integer>) request.getSession().getAttribute("publisherMap");
        Map<String, Integer> subjectMap = (Map<String, Integer>) request.getSession().getAttribute("subjectMap");

        Periodical periodical = new Periodical(request.getParameter("title"),
                Integer.parseInt(request.getParameter("numberOfPages")),
                Integer.parseInt(request.getParameter("periodicityPerYear")),
                Integer.parseInt(request.getParameter("percentageOfAdvertising")),
                Double.parseDouble(request.getParameter("pricePerMonth")),
                request.getParameter("description"),
                Double.parseDouble(request.getParameter("rating")),
                request.getParameter("language"));

        String publisher = request.getParameter("publisher");
        String telephone = request.getParameter("telephone");
        List<String> subject = Arrays.asList(request.getParameterValues("subject"));

        ///add publisher to table/find index of publisher
        Integer publisherId = publisherMap.get(publisher);
        if (publisherId == null) {
            publisherId = PublisherDaoImpl.getInstance().insertPublisherIntoDB(publisher, telephone);
            log.trace("successfully --> inserting publisher into DB");
        }
        periodical.setPublisherId(publisherId);

        try {
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            for (Part part : request.getParts()) {
                part.write("C:\\Users\\Dima\\Desktop\\periodacals\\src\\main\\webapp\\resources\\images\\" + fileName);
                periodical.setImage(fileName);
                ///response possible
            }
        } catch (IOException | ServletException e) {
            log.error("Error during file inserting");
        }

        ///insert periodical into db
        Integer periodicalId = PeriodicalsDaoImpl.getInstance().insertPeriodicalIntoDB(periodical);
        log.trace("successfully --> inserting periodical into db");
        ///insert subject into db

        for (String s : subject) {
            Integer subjectsId = subjectMap.get(subject.get(subject.indexOf(s)));
            if (subjectsId == null && !subject.get(subject.indexOf(s)).equals("")) {
                subjectsId = SubjectDaoImpl.getInstance().insertSubjectIntoDB(subject.get(subject.indexOf(s)));
                log.trace("successfully --> inserting subject into DB");
            }
            ///insert n:m table con
            if (!subject.get(subject.indexOf(s)).equals("")) {
                SubjectPeriodicalsDaoImpl.getInstance().insertSubjectIdAndPeriodicalIdIntoDB(subjectsId, periodicalId);
                log.trace("successfully --> inserting subject and periodical into db");
            }
        }

        request.getSession().removeAttribute("subjectMap");
        request.getSession().removeAttribute("publisherMap");

        try {
            response.sendRedirect(request.getContextPath() + "/fileupload");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
