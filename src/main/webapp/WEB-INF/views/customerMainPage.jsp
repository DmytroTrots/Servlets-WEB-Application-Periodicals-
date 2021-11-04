<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Customer Page</title>
</head>
<%
    if ((request.getSession(false).getAttribute("Customer") == null)) {
%>
<jsp:forward page="/WEB-INF/views/loginPage.jsp"></jsp:forward>
<%} %>
<body>
<center><h2>Customer Home</h2></center>

Welcome <%=request.getAttribute("userName") %>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/logout">Logout</a></div>

</body>
</html>
