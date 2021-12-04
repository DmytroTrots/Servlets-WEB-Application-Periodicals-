package com.trots.periodacals.controllers.admin;

import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.service.PeriodicalService;
import com.trots.periodacals.service.PublisherService;
import com.trots.periodacals.service.SubjectPeriodicalService;
import com.trots.periodacals.service.SubjectService;
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


/**
 * The type Update periodical admin servlet.
 */
@WebServlet("/update-periodical")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 4, // 1 MB
        maxFileSize = 1024 * 1024 * 10    // 10 MB
)
public class UpdatePeriodicalAdminServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(UpdatePeriodicalAdminServlet.class);
    public static final String IMAGE_PATH = "C:\\Users\\Dima\\Desktop\\periodacals\\src\\main\\webapp\\resources\\images\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("publisherMap", PublisherService.getInstance().findAllPublishersWithoutTelephone());
        req.getSession().setAttribute("subjectMap", SubjectService.getInstance().findAllSubjectsFromDB());
        String url = req.getRequestURI();
        req.setAttribute("url", url);
        Integer id = Integer.valueOf(req.getParameter("id"));
        req.getSession().setAttribute("sellId", id);
        req.getSession().setAttribute("periodicalInf", PeriodicalService.getInstance().getPeriodicalById(id));
        req.getSession().setAttribute("existedSubject", SubjectPeriodicalService.getInstance().findAllSubjectOfPeriodicalById(id));

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

        String title = request.getParameter("title");
        int numberOfPages = Integer.parseInt(request.getParameter("numberOfPages"));
        int periodicityPerYear = Integer.parseInt(request.getParameter("periodicityPerYear"));
        int percentageOfAdvertising = Integer.parseInt(request.getParameter("percentageOfAdvertising"));
        double pricePerMonth = Double.parseDouble(request.getParameter("pricePerMonth"));
        String description = request.getParameter("description");
        double rating = Double.parseDouble(request.getParameter("rating"));
        String publisher = request.getParameter("publisher");
        String telephone = request.getParameter("telephone");
        String lang = (String) request.getSession().getAttribute("lang");

        Periodical periodicalCheck = PeriodicalService.getInstance().getPeriodicalByTitle(title);

        if (periodicalCheck != null && !title.equals(periodicalOld.getTitle())) {
            langCheck(request, response, lang, "Periodical with such title already exist", "Видання з такою назвою вже існує");
        } else if (!title.matches("[A-Za-z0-9_ ]{2,64}")) {
            langCheck(request, response, lang, "Periodical's title has incorrect format", "Некоректний формат назви видання");
        } else if (numberOfPages < 1 || numberOfPages > 1000) {
            langCheck(request, response, lang, "Specify correct number of pages", "Вкажіть правильну кількість сторінок");
        } else if (periodicityPerYear != 1 && periodicityPerYear != 2 && periodicityPerYear != 3 && periodicityPerYear != 4 && periodicityPerYear != 6 && periodicityPerYear != 12) {
            langCheck(request, response, lang, "Specify correct periodicity per year", "Вкажіть правильну періодичність за рік");
        } else if (percentageOfAdvertising < 0 || percentageOfAdvertising > 100) {
            langCheck(request, response, lang, "Specify correct percentage of advertising", "Вкажіть правильний відсоток реклами");
        } else if (pricePerMonth < 1) {
            langCheck(request, response, lang, "Specify correct price", "Вкажіть правильну ціну за один місяць");
        } else if (description.length() > 1024) {
            langCheck(request, response, lang, "Specify correct description", "Вкажіть правильний опис видання");
        } else if (rating < 0 || rating > 5) {
            langCheck(request, response, lang, "Specify correct rating", "Вкажіть правильний рейтинг");
        } else if (!publisher.matches("[а-яА-ЯёЁa-zA-Z0-9]{1,25}")) {
            langCheck(request, response, lang, "Specify correct name of publisher", "Вкажіть правильну назву видавця");
        } else {

            Periodical periodical = new Periodical(title, numberOfPages, periodicityPerYear, percentageOfAdvertising, pricePerMonth, description, rating);

            List<String> subject = Arrays.asList(request.getParameterValues("subject"));

            String image = null;

            try {
                Part filePart = request.getPart("file");
                String fileName = filePart.getSubmittedFileName();
                image = fileName;
                for (Part part : request.getParts()) {
                    part.write(IMAGE_PATH + fileName);
                    periodical.setImage(fileName);
                }
            } catch (IOException | ServletException e) {
                log.error("Error during file inserting");
            }
            Integer sellId = (Integer) request.getSession().getAttribute("sellId");

            ///check publisher -> add(and get generated key)
            Integer publisherId = publisherMap.get(publisher);
            if (publisherId == null) {
                publisherId = PublisherService.getInstance().insertPublisherIntoDB(publisher, telephone);
                log.trace("successfully --> inserting publisher into DB");
            }
            periodical.setPublisherId(publisherId);

            ///update periodical
            PeriodicalService.getInstance().updatePeriodicalAdmin(periodical, image, oldImage, sellId);

            ///check subject -> add(and get generated key)
            List<String> existedSubj = (List<String>) request.getSession().getAttribute("existedSubject");
            for (String s : subject) {
                Integer subjectsId = subjectMap.get(subject.get(subject.indexOf(s)));
                if (subjectsId == null && !subject.get(subject.indexOf(s)).equals("")) {
                    subjectsId = SubjectService.getInstance().insertSubjectIntoDB(subject.get(subject.indexOf(s)));
                    log.trace("successfully --> inserting subject into DB");
                }

                if (!subject.get(subject.indexOf(s)).equals("") && !existedSubj.contains(subject.get(subject.indexOf(s)))) {
                    SubjectPeriodicalService.getInstance().insertSubjectIdAndPeriodicalIdIntoDB(subjectsId, sellId);
                    log.trace("successfully --> inserting subject and periodical into db");
                }
            }

            request.getSession().removeAttribute("subjectMap");
            request.getSession().removeAttribute("publisherMap");

            ///add Subject<->Periodical records
            langCheck(request, response, lang, "Periodical was successfully updated", "Видання було успішно оновлено");
        }
    }

    private void langCheck(HttpServletRequest request, HttpServletResponse response, String lang, String message1, String message2) throws IOException {
        if (lang == null || lang.equals("en")) {
            request.getSession().setAttribute("ex", message1);
        } else {
            request.getSession().setAttribute("ex", message2);
        }
        response.sendRedirect("/fileupload");
    }
}
