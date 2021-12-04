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
 * The type Add periodical admin servlet.
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/fileupload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, // 3 MB
        maxFileSize = 1024 * 1024 * 10    // 10 MB
)
public class AddPeriodicalAdminServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(AddPeriodicalAdminServlet.class);
    public static final String PATH_IMAGE = "C:\\Users\\Dima\\Desktop\\periodacals\\src\\main\\webapp\\resources\\images\\";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("PERIODICAL", PeriodicalService.getInstance().getAllPeriodicals());
        Map<String, Integer> publisherMap = PublisherService.getInstance().findAllPublishersWithoutTelephone();
        Map<String, Integer> subjectMap = SubjectService.getInstance().findAllSubjectsFromDB();
        request.getSession().setAttribute("publisherMap", publisherMap);
        request.getSession().setAttribute("subjectMap", subjectMap);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/addPeriodicalAdminPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Integer> publisherMap = (Map<String, Integer>) request.getSession().getAttribute("publisherMap");
        Map<String, Integer> subjectMap = (Map<String, Integer>) request.getSession().getAttribute("subjectMap");

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

        if (periodicalCheck != null) {
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

            ///add publisher to table/find index of publisher
            Integer publisherId = publisherMap.get(publisher);
            if (publisherId == null) {
                publisherId = PublisherService.getInstance().insertPublisherIntoDB(publisher, telephone);
                log.trace("successfully --> inserting publisher into DB");
            }
            periodical.setPublisherId(publisherId);

            try {
                Part filePart = request.getPart("file");
                String fileName = filePart.getSubmittedFileName();
                for (Part part : request.getParts()) {
                    part.write(PATH_IMAGE + fileName);
                    periodical.setImage(fileName);
                    ///response possible
                }
            } catch (IOException | ServletException e) {
                log.error("Error during file inserting");
            }

            ///insert periodical into db
            Integer periodicalId = PeriodicalService.getInstance().insertPeriodicalIntoDB(periodical);
            log.trace("successfully --> inserting periodical into db");
            ///insert subject into db

            for (String s : subject) {
                Integer subjectsId = subjectMap.get(subject.get(subject.indexOf(s)));
                if (subjectsId == null && !subject.get(subject.indexOf(s)).equals("")) {
                    subjectsId = SubjectService.getInstance().insertSubjectIntoDB(subject.get(subject.indexOf(s)));
                    log.trace("successfully --> inserting subject into DB");
                }
                ///insert n:m table con
                if (!subject.get(subject.indexOf(s)).equals("")) {
                    SubjectPeriodicalService.getInstance().insertSubjectIdAndPeriodicalIdIntoDB(subjectsId, periodicalId);
                    log.trace("successfully --> inserting subject and periodical into db");
                }
            }

            request.getSession().removeAttribute("subjectMap");
            request.getSession().removeAttribute("publisherMap");

            langCheck(request, response, lang, "Periodical successfully added to database", "Видання успішно додане");

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
