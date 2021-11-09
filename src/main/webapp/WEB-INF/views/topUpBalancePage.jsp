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
    <form action="<%= request.getContextPath() %>/top-up" method="post">
        <input type="number" name="balance"/>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>
