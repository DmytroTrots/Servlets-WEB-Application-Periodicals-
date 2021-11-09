<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="card-header my-3">All Products</div>
    <div class="row">
        <c:forEach var="list" items="${PERIODICAL}">
            <div class="col-md-3 my-3">
                <div class="card w-100">
                    <img class="card-img-top" src="/resources/images/${list.image}"
                         alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">${list.title}</h5>
                        <h6 class="price">Price: $${list.pricePerMonth}</h6>
                        <h6 class="category">Publisher: ${list.publisher}</h6>
                        <div class="mt-3 d-flex justify-content-between">
                            <a class="btn btn-dark" href="cart?id=${list.sellId}">Add to Cart</a> <a
                                class="btn btn-primary" href="order-periodical?month=1&id=${list.sellId}&name=${user.getName()}
                                &surname=${user.getSurname()}&address=${user.getAddress()}&email=${user.getEmail()}
                                                                            &telephone=${user.getTelephone()}">Buy Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
