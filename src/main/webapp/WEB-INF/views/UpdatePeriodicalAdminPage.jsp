<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Update periodical</title>
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet"
          id="bootstrap-css">
</head>
<body>
<%@include file="header.jsp" %>
<div class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><fmt:message key="label.updatePeriodical"/></div>
                    <div class="card-body">
                        <form action="update-periodical" method="post" enctype="multipart/form-data">
                            <div class="form-group row">
                                <label for="title"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message key="label.title"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="title" class="form-control" value="${periodicalInf.getTitle()}" name="title"
                                           pattern="[A-Za-z]{2,64}" title="<fmt:message key="label.titleValidate"/>"
                                           required autocomplete="off" autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="numberOfPages" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.numberOfPages"/></label>
                                <div class="col-md-6">
                                    <input type="number" id="numberOfPages" value="${periodicalInf.getNumberOfPages()}" class="form-control" name="numberOfPages"
                                           min="1" required autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="periodicityPerYear" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.periodicityPerYear"/></label>
                                <div class="col-md-6">
                                    <input type="number" id="periodicityPerYear" value="${periodicalInf.getPeriodicityPerYear()}" class="form-control" name="periodicityPerYear"
                                           min="1" required autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="percentageOfAdvertising" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.percentageOfAdvertising"/></label>
                                <div class="col-md-6">
                                    <input type="number" id="percentageOfAdvertising" value="${periodicalInf.getPercentageOfAdvertising()}"  class="form-control" name="percentageOfAdvertising"
                                           min="0" max="100" autocomplete="off" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="pricePerMonth" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.pricePerMonth"/></label>
                                <div class="col-md-6">
                                    <input type="number" id="pricePerMonth" value="${periodicalInf.getPricePerMonth()}" class="form-control" name="pricePerMonth"
                                           min="1" step="0.01" pattern="[+-]?([0-9]*[.])?[0-9]{1,2}"
                                           title="<fmt:message key="label.priceValidate"/>" required autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="description" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.description"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="description" value="${periodicalInf.getDescription()}" class="form-control" name="description"
                                           required autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="rating" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.rating"/></label>
                                <div class="col-md-6">
                                    <input type="number" id="rating" value="${periodicalInf.getRating()}" class="form-control" name="rating"
                                           min="0" step="0.01" pattern="[+-]?([0-9]*[.])?[0-9]{1}"
                                           title="<fmt:message key="label.ratingValidate"/>" autocomplete="off"
                                           required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="publisher" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.publisher"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="publisher" value="${periodicalInf.getPublisher()}" class="form-control" name="publisher"
                                           list="publisherList" pattern="[а-яА-ЯёЁa-zA-Z]{1-25}"
                                           title="<fmt:message key="label.publisherValidate"/>" autocomplete="off" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="telephone" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.telephonePublisher"/></label>
                                <div class="col-md-6">
                                    <input type="tel" id="telephone" value="${periodicalInf.getTelephonePub()}" class="form-control" name="telephone"
                                           pattern="[0-9]{11,12}" title="<fmt:message key="label.telephoneValidate"/>"
                                           autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="subject" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.subject"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="subject" value="${subj1}" class="form-control" name="subject"
                                           list="subjectList" pattern="[а-яА-ЯёЁa-zA-Z]{1-25}" autocomplete="off" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="subject2" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.subject"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="subject2" value="${subj2}" class="form-control" name="subject"
                                           list="subjectList" pattern="[а-яА-ЯёЁa-zA-Z]{1-25}" autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="subject3" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.subject"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="subject3" value="${subj3}" class="form-control" name="subject"
                                           list="subjectList" pattern="[а-яА-ЯёЁa-zA-Z]{1-25}" autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="file" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.image"/></label>
                                <div class="col-md-6">
                                    <input type="file" id="file" class="form-control" name="file" required>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="label.submit"/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<datalist id="subjectList">
    <c:forEach items="${subjectMap}" var="subject" >
    <option value="${subject.key}">
        </c:forEach>
</datalist>

<datalist id="publisherList">
    <c:forEach items="${publisherMap}" var="publisher">
    <option value="${publisher.key}">
        </c:forEach>

</datalist>
</body>
</html>
