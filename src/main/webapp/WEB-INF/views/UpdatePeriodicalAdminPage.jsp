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
    <form action="update-periodical" method="post" enctype="multipart/form-data">
        <table style="width: 80%">
            <tr>
                <td>Title</td>
                <td><input type="text" name="title" value="${periodicalInf.getTitle()}" required/></td>
            </tr>
            <tr>
                <td>Number of pages</td>
                <td><input type="number" value="${periodicalInf.getNumberOfPages()}" name="numberOfPages" autocomplete="off" required/></td>
            </tr>
            <tr>
                <td>Periodicity per year</td>
                <td><input type="number" value="${periodicalInf.getPeriodicityPerYear()}" name="periodicityPerYear" autocomplete="off" required/></td>
            </tr>
            <tr>
                <td>Percentage of advertising</td>
                <td><input type="number" value="${periodicalInf.getPercentageOfAdvertising()}" name="percentageOfAdvertising" autocomplete="off" required/></td>
            </tr>
            <tr>
                <td>Price per Month</td>
                <td><input type="number" value="${periodicalInf.getPricePerMonth()}" name="pricePerMonth" autocomplete="off" required/></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" value="${periodicalInf.getDescription()}" name="description" autocomplete="off" required/></td>
            </tr>
            <tr>
                <td>Rating</td>
                <td><input type="text" value="${periodicalInf.getRating()}" name="rating" autocomplete="off" required/></td>
            </tr>
            <tr>
                <td>Language</td>
                <td><input type="text" value="${periodicalInf.getLanguage()}" name="language" list="languageList" autocomplete="off" required>
                    <datalist id="languageList">
                        <option value="ru">
                        <option value="ukr">
                    </datalist>
                </td>
            </tr>
            <tr>
                <td>Publisher</td>
                <td><input type="text" value="${periodicalInf.getPublisher()}" name="publisher" list="publisherList" autocomplete="off" required>
                </td>
            </tr>
            <tr>
                <td>Telephone(if you cant find publisher in the list)</td>
                <td><input type="text" value="${periodicalInf.getTelephonePub()}" name="telephone" autocomplete="off"/></td>
            </tr>
            <tr>
                <td>Subject</td>
                <td><input type="text" value="${subj1}" name="subject" list="subjectList" autocomplete="off" required>
            </tr>
            <tr>
                <td>Subject</td>
                <td><input type="text" value="${subj2}" name="subject" list="subjectList" autocomplete="off">
            </tr>
            <tr>
                <td>Subject</td>
                <td><input type="text" value="${subj3}" name="subject" list="subjectList" autocomplete="off">
            </tr>
            <tr>
                <td>Image</td>
                <td><input type="file" name="file" required>
            </tr>
        </table>
        <input type="submit" value="Submit"/>
    </form>
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
