<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Login</title>
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
                <c:if test="${sessionScope['lang']==null}">
                    <c:set var="lang" value="en" scope="session"/>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<div class="login-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><fmt:message key="label.login"/></div>
                    <div class="card-body">
                        <form action="login" method="post">
                            <div class="form-group row">
                                <label for="login"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="label.username"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="login" class="form-control" name="username"
                                           required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="label.password"/></label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password"
                                           required>
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
                                    <fmt:message key="label.logIn"/>
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
