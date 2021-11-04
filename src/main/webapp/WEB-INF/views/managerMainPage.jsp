<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Page</title>
</head>
<% //In case, if User session is not set, redirect to Login page.
    if ((request.getSession(false).getAttribute("Manager") == null)) {
%>
<jsp:forward page="/WEB-INF/views/loginPage.jsp"></jsp:forward>
<%} %>
<body>
<center><h2>Manager Home</h2></center>
Welcome <%=request.getAttribute("userName") %>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/logout">Logout</a></div>

</body>
</html>
