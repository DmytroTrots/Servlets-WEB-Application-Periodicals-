<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp"%>
<table class="table">
    <thead class="bg-light">
    <tr>
        <th scope="col">ReceiptId</th>
        <th scope="col">User_id</th>
        <th scope="col">Name</th>
        <th scope="col">Surname</th>
        <th scope="col">Email</th>
        <th scope="col">Telephone</th>
        <th scope="col">Address</th>
        <th scope="col">Order(ID OF PERIODICAL)</th>
        <th scope="col">Price</th>
        <th scope="col">Create date</th>
        <th scope="col">Status</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${receiptList}">
        <tr>
            <td>${list.id}</td>
            <td>${list.userId }</td>
            <td>${list.name }</td>
            <td>${list.surname }</td>
            <td>${list.email}</td>
            <td>${list.telephoneNumber}</td>
            <td>${list.address}</td>
            <td>${list.allPeriodicalsId}</td>
            <td>${list.pricePerMonth}</td>
            <td>${list.create_time}</td>
            <td>${list.statusName}</td>
            <c:if test="${list.statusName == 'payed'}">
            <form action="accept-order" method="post">
                <td><input type="hidden" name="id" value="${list.id}">
                <input type="submit" value="Accept">
            </form>
            <form action="discard-order" method="post">
                <input type="hidden" name="id" value="${list.id}">
                <input type="hidden" name="userId" value="${list.userId}">
                <input type="hidden" name="price" value="${list.pricePerMonth}">
                <input type="submit" value="Discard"></td>
            </form>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
