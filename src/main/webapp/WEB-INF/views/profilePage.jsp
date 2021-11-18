<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp"%>
<div align="center">
    <h1><fmt:message key="label.orders"/></h1>
    <table class="table">
        <thead class="bg-light">
        <tr>
            <th scope="col"><fmt:message key="label.title"/></th>
            <th scope="col"><fmt:message key="label.publisher"/></th>
            <th scope="col"><fmt:message key="label.priceOnly"/></th>
            <th scope="col"><fmt:message key="label.months"/></th>
            <th scope="col"><fmt:message key="label.status"/></th>
            <th scope="col"><fmt:message key="label.createDate"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="receiptList" items="${receiptList}">
            <tr>
                <td>${receiptList.title }</td>
                <td>${receiptList.publisherName}</td>
                <td>${receiptList.pricePerMonth }</td>
                <td>${receiptList.months }</td>
                <td>${receiptList.statusName}</td>
                <td>${receiptList.create_time}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
