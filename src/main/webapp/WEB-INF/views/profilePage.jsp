<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp"%>
<div align="center">
    <h1>Orders</h1>
    <table class="table">
        <thead class="bg-light">
        <tr>
            <th scope="col">Title</th>
            <th scope="col">Price</th>
            <th scope="col">Months</th>
            <th scope="col">Status</th>
            <th scope="col">CreateTime</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="receiptList" items="${receiptList}">
            <tr>
                <td>${receiptList.title }</td>
                <td>${receiptList.price }</td>
                <td>${receiptList.months }</td>
                <td>${receiptList.description}</td>
                <td>${receiptList.create_time}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
