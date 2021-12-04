<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Add user</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<%@include file="header.jsp"%>
<div class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><fmt:message key="label.addUser"/></div>
                    <div class="card-body">
                        <form action="addUser" method="post">
                            <div class="form-group row">
                                <label for="username"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="label.username"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="username" class="form-control" name="username"
                                           pattern="[a-zA-Z0-9]{6,18}"
                                           title="<fmt:message key="label.usernameValidate"/>" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="email"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="label.email"/></label>
                                <div class="col-md-6">
                                    <input type="email" id="email" class="form-control" name="email"
                                           required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="label.password"/></label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control"
                                           name="password"
                                           pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}$"
                                           title="<fmt:message key="label.passwordValidate"/>" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="surname"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="label.surname"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="surname" class="form-control" name="surname"
                                           pattern="[а-яА-ЯёЁa-zA-Z]{1-25}"
                                           title="<fmt:message key="label.nameValidate"/>" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="name" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="label.name"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="name" class="form-control" name="name"
                                           pattern="[а-яА-ЯёЁa-zA-Z]{1-25}"
                                           title="<fmt:message key="label.nameValidate"/>" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="telephone"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="label.telephone"/></label>
                                <div class="col-md-6">
                                    <input type="tel" id="telephone" class="form-control" name="telephone"
                                           pattern="[0-9]{11,12}"
                                           title="<fmt:message key="label.telephoneValidate"/>" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="address"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="label.address"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="address" class="form-control" name="address"
                                           required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="nameOfRole"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message key="label.role"/></label>
                                <div class="col-md-6">
                                    <select id="nameOfRole" class="form-control" name="nameOfRole" required>
                                        <c:forEach var="roleList" items="${ROLE_LIST}">
                                            <option value="${roleList.toString()}">${roleList.toString()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-md-6 offset-md-4">
                                    <fmt:message key="label.allFieldsValidate"/>
                                </div>
                            </div>

                            <span style="color:red">
                                <c:if test="${not empty sessionScope.ex}">
                                    <div class="alert alert-warning">
                                        <strong><c:out value="${sessionScope.ex}"/></strong>
                                    </div>
                                    <c:set var="ex" value="" scope="session"/>
                                </c:if>
                            </span>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="label.submit"/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</form>
</div>
<h3><fmt:message key="label.userInfoForTable"/></h3>
<table class="table">
    <thead class="bg-light">
    <tr>
        <th scope="col"><fmt:message key="label.username"/></th>
        <th scope="col"><fmt:message key="label.email"/></th>
        <th scope="col"><fmt:message key="label.name"/></th>
        <th scope="col"><fmt:message key="label.surname"/></th>
        <th scope="col"><fmt:message key="label.role"/></th>
        <th scope="col"><fmt:message key="label.telephone"/></th>
        <th scope="col"><fmt:message key="label.balance"/></th>
        <th scope="col"><fmt:message key="label.banStatus"/></th>
        <th scope="col"><fmt:message key="label.action"/></th>
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
            <c:if test="${tempBook.role != 'admin'}">
            <form action="ban-user" method="post">
                <td><input type="hidden" name="id" value="${tempBook.id}">
                <c:choose>
                    <c:when test="${tempBook.banStatus == 'banned'}">
                    <input type="submit" value="<fmt:message key="label.unban"/>">
                    </c:when>
                    <c:otherwise>
                    <input type="submit" value="<fmt:message key="label.ban"/>">
                    </c:otherwise>
                </c:choose>
            </form>
            <form action="delete-user" method="post">
                <input type="hidden" name="id" value="${tempBook.id}">
                <input type="submit" value="<fmt:message key="label.delete"/>"></td>
            </form>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>


