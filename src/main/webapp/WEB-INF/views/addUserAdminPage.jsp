<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AddUser</title>
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
                <td><input type="text" name="username" required pattern="[a-zA-Z0-9]{6,18}"
                           title="Username should contain only Aa-Zz letters and 0-9 number, size should be from 6 to 18 symbols" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" required/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}$" title="Password should be 8-16 symbols, with at least 1 upper case, 1 lower case, 1 special symbol, 1 number" required/></td>
            </tr>
            <tr>
                <td>Surname</td>
                <td><input type="text" name="surname" required pattern="[а-яА-ЯёЁa-zA-Z]{1-25}" title="Surname should be 1-25 size"/></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" required pattern="[а-яА-ЯёЁa-zA-Z]{1-25}" title="Surname should be 1-25 size"/></td>
            </tr>
            <tr>
                <td>Telephone</td>
                <td><input type="text" name="telephone" pattern="[0-9]{11,12}" title="Start with code of your country. Telephone should not contain letters, use digits from 0 to 9, size should be 11-12 symbols" required/></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><input type="text" name="address" required/></td>
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

</form>
</div>
<h3>
    Book Information From Database</h3>
<table class="table">
    <thead class="bg-light">
    <tr>
        <th scope="col">Username</th>
        <th scope="col">Email</th>
        <th scope="col">Name</th>
        <th scope="col">Surname</th>
        <th scope="col">Role</th>
        <th scope="col">Telephone</th>
        <th scope="col">Balance</th>
        <th scope="col">BanStatus</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="tempBook" items="${USERS_LIST}">
        <tr>
            <td>${tempBook.username }</td>
            <td>${tempBook.email }</td>
            <td>${tempBook.name }</td>
            <td>${tempBook.surname}</td>
            <td>${tempBook.role}</td>
            <td>${tempBook.telephone}</td>
            <td>${tempBook.balance}</td>
            <td>${tempBook.banStatus}</td>
            <form action="ban-user" method="post">
                <td><input type="hidden" name="id" value="${tempBook.id}">
                <c:choose>
                    <c:when test="${tempBook.banStatus == 'banned'}">
                        <input type="submit" value="Unban">
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Ban">
                    </c:otherwise>
                </c:choose>
            </form>
            <form action="delete-user" method="post">
                <input type="hidden" name="id" value="${tempBook.id}">
                <input type="submit" value="Delete"></td>
            </form>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>


