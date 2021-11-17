package com.trots.periodacals.controllers;

import com.trots.periodacals.daoimpl.PeriodicalsDaoImpl;
import com.trots.periodacals.daoimpl.PublisherDaoImpl;
import com.trots.periodacals.daoimpl.SubjectDaoImpl;
import com.trots.periodacals.daoimpl.SubjectPeriodicalsDaoImpl;
import com.trots.periodacals.entity.Periodical;
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


@WebServlet("/update-periodical")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 4, // 1 MB
        maxFileSize = 1024 * 1024 * 10    // 10 MB
)
public class UpdatePeriodicalAdminServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(UpdatePeriodicalAdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("publisherMap", PublisherDaoImpl.getInstance().findAllPublishersWithoutTelephone());
        req.getSession().setAttribute("subjectMap", SubjectDaoImpl.getInstance().findAllSubjectsFromDB());
        Integer id = Integer.valueOf(req.getParameter("id"));
        req.getSession().setAttribute("sellId", id);
        req.getSession().setAttribute("periodicalInf", PeriodicalsDaoImpl.getInstance().getPeriodicalById(id));
        req.getSession().setAttribute("existedSubject", SubjectPeriodicalsDaoImpl.getInstance().findAllSubjectOfPeriodicalById(id));

        List<String> list = (List<String>) req.getSession().getAttribute("existedSubject");

        req.setAttribute("subj1", list.get(0));
        if (list.size() > 1) req.setAttribute("subj2", list.get(1));
        if (list.size() > 2) req.setAttribute("subj3", list.get(2));

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/UpdatePeriodicalAdminPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Periodical periodicalOld = (Periodical) request.getSession().getAttribute("periodicalInf");
        Map<String, Integer> publisherMap = (Map<String, Integer>) request.getSession().getAttribute("publisherMap");
        Map<String, Integer> subjectMap = (Map<String, Integer>) request.getSession().getAttribute("subjectMap");

        String oldImage = periodicalOld.getImage();

        Periodical periodical = new Periodical(request.getParameter("title"),
                Integer.parseInt(request.getParameter("numberOfPages")),
                Integer.parseInt(request.getParameter("periodicityPerYear")),
                Integer.parseInt(request.getParameter("percentageOfAdvertising")),
                Double.parseDouble(request.getParameter("pricePerMonth")),
                request.getParameter("description"),
                Double.parseDouble(request.getParameter("rating")));

        String publisher = request.getParameter("publisher");
        String telephone = request.getParameter("telephone");

        List<String> subject = Arrays.asList(request.getParameterValues("subject"));

        String image = null;

        try {
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            image = fileName;
            for (Part part : request.getParts()) {
                part.write("C:\\Users\\Dima\\Desktop\\periodacals\\src\\main\\webapp\\resources\\images\\" + fileName);
                periodical.setImage(fileName);
            }
        } catch (IOException | ServletException e) {
            log.error("Error during file inserting");
        }
        Integer sellId = (Integer) request.getSession().getAttribute("sellId");

        ///check publisher -> add(and get generated key)
        Integer publisherId = publisherMap.get(publisher);
        if (publisherId == null) {
            publisherId = PublisherDaoImpl.getInstance().insertPublisherIntoDB(publisher, telephone);
            log.trace("successfully --> inserting publisher into DB");
        }
        periodical.setPublisherId(publisherId);

        ///update periodical
        PeriodicalsDaoImpl.getInstance().updatePeriodicalAdmin(periodical, image, oldImage, sellId);

        ///check subject -> add(and get generated key)
        List<String> existedSubj = (List<String>) request.getSession().getAttribute("existedSubject");
        for (String s : subject) {
            Integer subjectsId = subjectMap.get(subject.get(subject.indexOf(s)));
            if (subjectsId == null && !subject.get(subject.indexOf(s)).equals("")) {
                subjectsId = SubjectDaoImpl.getInstance().insertSubjectIntoDB(subject.get(subject.indexOf(s)));
                log.trace("successfully --> inserting subject into DB");
            }

            if (!subject.get(subject.indexOf(s)).equals("") && !existedSubj.contains(subject.get(subject.indexOf(s)))) {
                SubjectPeriodicalsDaoImpl.getInstance().insertSubjectIdAndPeriodicalIdIntoDB(subjectsId, sellId);
                log.trace("successfully --> inserting subject and periodical into db");
            }
        }

        request.getSession().removeAttribute("subjectMap");
        request.getSession().removeAttribute("publisherMap");

        ///add Subject<->Periodical records

        try {
            response.sendRedirect(request.getContextPath() + "/fileupload");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
