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
    <h1>AddUser</h1>
    <form action="<%= request.getContextPath() %>/addUser" method="post">
        <table style="with: 80%">
            <tr>
                <td>Username</td>
                <td><input type="text" name="Name"/></td>
            </tr>
            <tr>
                <td>Username</td>
                <td><input type="text" name="Surname"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"/></td>
            </tr>

            <tr>
                <td>Name</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>Telephone</td>
                <td><input type="text" name="telephone"/></td>
            </tr>
            <tr>
                <td>Role</td>
                <td><select id="inputState" class="form-control" name="nameOfRole" required>
                    <option selected disabled>Choose Category</option>
                    <c:forEach var="roleList" items="${ROLE_LIST}">
                        <option value="${roleList.toString()}">${roleList.toString()}</option>
                    </c:forEach>
                </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>
