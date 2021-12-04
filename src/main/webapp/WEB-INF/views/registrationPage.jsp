<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Registration</title>
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet"
          id="bootstrap-css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
    <div class="container">
        <a class="navbar-brand" href="/shop?currentPage=1&category=0">Periodicals</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/login"><fmt:message key="label.logIn"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/registration"><fmt:message key="label.signUp"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="?sessionLocale=en"><img src="/resources/images/flag.png"></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="?sessionLocale=ua"><img src="/resources/images/UA-Ukraine-Flag-icon.png"></a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><fmt:message key="label.customerRegisterForm"/></div>
                    <div class="card-body">
                        <form action="registration" method="post">
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
                                           pattern="[а-яА-ЯёЁa-zA-Z]{1,25}"
                                           title="<fmt:message key="label.nameValidate"/>" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="name" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="label.name"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="name" class="form-control" name="name"
                                           pattern="[а-яА-ЯёЁa-zA-Z]{1,25}"
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
                                    <input type="text" id="address" class="form-control" name="address" maxlength="300"
                                           required>
                                </div>
                            </div>

                            <input type="hidden" name="role" value="customer"/>

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
</body>
</html>
