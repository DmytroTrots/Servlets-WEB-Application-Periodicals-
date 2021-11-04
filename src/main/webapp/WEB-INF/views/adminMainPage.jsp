<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Admin Page</title>
</head>
<%
    if ((request.getSession(false).getAttribute("Admin") == null)) {
%>
<jsp:forward page="/WEB-INF/views/loginPage.jsp"></jsp:forward>
<%} %>
<body>
<center><h2>Admin's Home</h2></center>

Welcome <%=request.getAttribute("userName") %>

<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
</body>
</html>
