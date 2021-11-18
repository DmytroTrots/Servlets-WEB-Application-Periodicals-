<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Send message</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<%@include file="header.jsp" %>
<form class="send-message"action="/send-message" method="post">
    <input type="hidden" name="to" value="dtrots27@gmail.com"/><br/>
    <input type="hidden" name="subject" value="<fmt:message key="label.username"/> -> ${sessionScope['userName']}, ID -> ${sessionScope['ID']}, <fmt:message key="label.messageFromCustomer"/>" ><br/>
    <label for="message"><fmt:message key="label.sendMessageLabel"/></label></br>
    <textarea class="textarea" rows="10" cols="70" id="message" name="message"></textarea><br/>
    <input type="submit" value="<fmt:message key="label.sendMessage"/>"/>
</form>
</body>
</html>
