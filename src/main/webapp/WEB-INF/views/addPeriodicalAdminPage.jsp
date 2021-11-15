<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>
<div align="center">
    <h1>AddUser</h1>
    <form action="fileupload" method="post" enctype="multipart/form-data">
        <table style="width: 80%">
            <tr>
                <td>Title</td>
                <td><input type="text" name="title" pattern="[A-Za-z]{2,64}"
                           title="Title should start with upper case letter and it should be 2-64 symbols size"
                           required/></td>
            </tr>
            <tr>
                <td>Number of pages</td>
                <td><input type="number" name="numberOfPages" min="1" required/></td>
            </tr>
            <tr>
                <td>Periodicity per year</td>
                <td><input type="number" name="periodicityPerYear" min="1" required/></td>
            </tr>
            <tr>
                <td>Percentage of advertising</td>
                <td><input type="number" name="percentageOfAdvertising" min="0" required/></td>
            </tr>
            <tr>
                <td>Price per Month</td>
                <td><input type="" name="pricePerMonth" min="1" step="0.01" pattern="[+-]?([0-9]*[.])?[0-9]{1,2}"
                           title="Example: 16.10" required/></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="description" required/></td>
            </tr>
            <tr>
                <td>Rating</td>
                <td><input type="number" name="rating" min="0" step="0.01" pattern="[+-]?([0-9]*[.])?[0-9]{1}"
                           title="Example: 4.1" required/></td>
            </tr>
            <tr>
                <td>Language</td>
                <td><input type="text" name="language" list="languageList" required>
                    <datalist id="languageList">
                        <option value="">
                    </datalist>
                </td>
            </tr>
            <tr>
                <td>Publisher</td>
                <td><input type="text" name="publisher" list="publisherList" pattern="[а-яА-ЯёЁa-zA-Z]{1-25}"
                           title="Shoud be 1-25 symbols" required>
                </td>
            </tr>
            <tr>
                <td>Telephone(if you cant find publisher in the list)</td>
                <td><input type="text" name="telephone" pattern="[0-9]{11,12}"
                           title="Start with code of country. Telephone should not contain letters, use digits from 0 to 9, size should be 11-12 symbols"/>
                </td>
            </tr>
            <tr>
                <td>Subject</td>
                <td><input type="text" name="subject" list="subjectList" pattern="[а-яА-ЯёЁa-zA-Z]{1-25}" required>
            </tr>
            <tr>
                <td>Subject</td>
                <td><input type="text" name="subject" list="subjectList" pattern="[а-яА-ЯёЁa-zA-Z]{1-25}">
            </tr>
            <tr>
                <td>Subject</td>
                <td><input type="text" name="subject" list="subjectList" pattern="[а-яА-ЯёЁa-zA-Z]{1-25}">
            </tr>
            <tr>
                <td>Image</td>
                <td><input type="file" name="file" required/></td>
            </tr>
        </table>
        <input type="submit" value="Submit"/>
    </form>
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

<h3>
    Book Information From Database</h3>
<table class="table" style="table-layout: fixed">
    <thead class="bg-light">
    <tr>
        <th scope="col">Sell_Id</th>
        <th scope="col">Title</th>
        <th scope="col">Pages</th>
        <th scope="col">Periodicity</th>
        <th scope="col">Advertising(%)</th>
        <th scope="col">Price(1/12)</th>
        <th scope="col">Description</th>
        <th scope="col">Rating</th>
        <th scope="col">Language</th>
        <th scope="col">Publisher</th>
        <th scope="col">Action</th>
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
            <td>${periodical.language}</td>
            <td>${periodical.publisher}</td>
            <form action="update-periodical" method="get">
                <input name="id" type="hidden" value="${periodical.sellId}">
                <td><input type="submit" value="Update"/>
            </form>
            <form action="delete-periodical" method="post">
                <input name="id" type="hidden" value="${periodical.sellId}">
                <input type="submit" value="Delete"/></td>
            </form>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
