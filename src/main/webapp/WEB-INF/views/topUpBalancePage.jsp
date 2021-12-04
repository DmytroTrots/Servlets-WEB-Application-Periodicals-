<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Top up balance</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<%@include file="header.jsp" %>
<div align="center">
    <h1><fmt:message key="label.topUpBalance"/></h1>
    <form action="top-up" method="post">
        <input type="number" name="balance" min="1"/>
        <input type="submit" value="<fmt:message key="label.submit"/>"/>
    </form>

    <span style="color:red">
        <c:if test="${not empty sessionScope.ex}">
            <div class="alert alert-warning">
                <strong><c:out value="${sessionScope.ex}"/></strong>
            </div>
            <c:set var="ex" value="" scope="session"/>
        </c:if>
    </span>
</div>
</body>
</html>
