<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Add periodical</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
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
                    <div class="card-header"><fmt:message key="label.addPeriodical"/></div>
                    <div class="card-body">
                        <form action="fileupload" method="post" enctype="multipart/form-data">
                            <div class="form-group row">
                                <label for="title"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message key="label.title"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="title" class="form-control" name="title"
                                           pattern="[A-Za-z0-9_ ]{2,64}" title="<fmt:message key="label.titleValidate"/>"
                                           required autocomplete="off" autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="numberOfPages" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.numberOfPages"/></label>
                                <div class="col-md-6">
                                    <input type="number" id="numberOfPages" class="form-control" name="numberOfPages"
                                           min="1" max="1000" required autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="periodicityPerYear" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.periodicityPerYear"/></label>
                                <div class="col-md-6">
                                    <select class="form-control" name="periodicityPerYear" id="periodicityPerYear" required>
                                        <option selected value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="6">6</option>
                                        <option value="12">12</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="percentageOfAdvertising" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.percentageOfAdvertising"/></label>
                                <div class="col-md-6">
                                    <input type="number" id="percentageOfAdvertising" class="form-control" name="percentageOfAdvertising"
                                           min="0" max="100" autocomplete="off" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="pricePerMonth" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.pricePerMonth"/></label>
                                <div class="col-md-6">
                                    <input type="number" id="pricePerMonth" class="form-control" name="pricePerMonth"
                                           min="1" step="0.01" pattern="[+-]?([0-9]*[.])?[0-9]{1,2}"
                                           title="<fmt:message key="label.priceValidate"/>" required autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="description" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.description"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="description" class="form-control" name="description" maxlength="1024"
                                           required autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="rating" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.rating"/></label>
                                <div class="col-md-6">
                                    <input type="number" id="rating" class="form-control" name="rating"
                                           min="0" step="0.01" max="5" pattern="[+-]?([0-9]*[.])?[0-9]{1}"
                                           title="<fmt:message key="label.ratingValidate"/>" autocomplete="off"
                                           required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="publisher" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.publisher"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="publisher" class="form-control" name="publisher"
                                           list="publisherList" pattern="[а-яА-ЯёЁa-zA-Z0-9]{1,25}"
                                           title="<fmt:message key="label.publisherValidate"/>" autocomplete="off" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="telephone" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.telephonePublisher"/></label>
                                <div class="col-md-6">
                                    <input type="tel" id="telephone" class="form-control" name="telephone"
                                           pattern="[0-9]{11,12}" title="<fmt:message key="label.telephoneValidate"/>"
                                           autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="subject" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.subject"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="subject" class="form-control" name="subject"
                                           list="subjectList" pattern="[а-яА-ЯёЁa-zA-Z0-9]{1,25}" autocomplete="off" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="subject2" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.subject"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="subject2" class="form-control" name="subject"
                                           list="subjectList" pattern="[а-яА-ЯёЁa-zA-Z0-9]{1,25}" autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="subject3" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.subject"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="subject3" class="form-control" name="subject"
                                           list="subjectList" pattern="[а-яА-ЯёЁa-zA-Z0-9]{1,25}" autocomplete="off">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="file" class="col-md-4 col-form-label text-md-right"><fmt:message key="label.image"/></label>
                                <div class="col-md-6">
                                    <input type="file" id="file" class="form-control" name="file" required>
                                </div>
                            </div>

                            <span style="color:red">
                                <c:if test="${not empty sessionScope.ex}">
                                    <div class="alert alert-warning">
                                        <strong><c:out value="${sessionScope.ex}"/></strong>
                                    </div>
                                    <c:set var="ex" value="" scope="session"/>
                                </c:if>
                            </span>

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
    <c:forEach items="${subjectMap}" var="subject">
    <option value="${subject.key}">
        </c:forEach>
</datalist>

<datalist id="publisherList">
    <c:forEach items="${publisherMap}" var="publisher">
    <option value="${publisher.key}">
        </c:forEach>
</datalist>

<h3><fmt:message key="label.periodicalsInfoForTable"/></h3>
<table class="table" style="table-layout: auto">
    <thead class="bg-light">
    <tr>
        <th scope="col"><fmt:message key="label.sellId"/></th>
        <th scope="col"><fmt:message key="label.title"/></th>
        <th scope="col"><fmt:message key="label.pages"/></th>
        <th scope="col"><fmt:message key="label.periodicity"/></th>
        <th scope="col"><fmt:message key="label.advertising"/></th>
        <th scope="col"><fmt:message key="label.price"/></th>
        <th scope="col"><fmt:message key="label.description"/></th>
        <th scope="col"><fmt:message key="label.rating"/></th>
        <th scope="col"><fmt:message key="label.publisher"/></th>
        <th scope="col"><fmt:message key="label.action"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="periodical" items="${PERIODICAL}">
        <tr>
            <td>${periodical.sellId }</td>
            <td class="text-table">${periodical.title }</td>
            <td>${periodical.numberOfPages }</td>
            <td>${periodical.periodicityPerYear}</td>
            <td>${periodical.percentageOfAdvertising}</td>
            <td>${periodical.pricePerMonth}</td>
            <td class="text-table">${periodical.description}</td>
            <td>${periodical.rating}</td>
            <td>${periodical.publisher}</td>
            <form action="update-periodical" method="get">
                <input name="id" type="hidden" value="${periodical.sellId}">
                <td><input type="submit" value="<fmt:message key="label.update"/>">
            </form>
            <form action="delete-periodical" method="post">
                <input name="id" type="hidden" value="${periodical.sellId}">
                <input type="submit" value="<fmt:message key="label.delete"/>"></td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>