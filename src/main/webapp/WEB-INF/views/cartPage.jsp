<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
          integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="d-flex py-3"><h3>Total price: $ ${(totalPrice>0)?decimalFormat.format(totalPrice):0}</h3></div>
    <form action="/order-all" method="post">
        <table class="table table-loght">
            <thead>
            <tr>
                <th scope="col">Title</th>
                <th scope="col">Publisher</th>
                <th scope="col">Price</th>
                <th scope="col">Buy now</th>
                <th scope="col">Cancel</th>
            </tr>
            </thead>

            <c:if test="${sessionScope['cart_list'] != null && not empty sessionScope['cart_list']}">
                <c:forEach items="${cartPeriodical}" var="list">
                    <tbody>
                    <td>${list.title}</td>
                    <td>${list.publisher}</td>
                    <td>${list.pricePerMonth}</td>
                    <td>
                        <input type="hidden" name="id" value="${list.sellId}" class="form-input">
                        <div class="form-group d-flex justify-content-between w-50">
                            <a class="btn bnt-sm btn-decre" href="inc-dec?action=dec&id=${list.sellId}"><i
                                    class="fas fa-minus-square"></i> </a>
                            <input type="number" name="month" class="form-control w-50" value="${list.months}"
                                   readonly>
                            <a class="btn bnt-sm btn-incre" href="inc-dec?action=inc&id=${list.sellId}"><i
                                    class="fas fa-plus-square"></i> </a>
                        </div>
                    </td>
                    <td><a class="btn btn-sm btn-danger" href="remove-record?id=${list.sellId}"> Remove </a></td>
                    </tbody>
                </c:forEach>
        </table>
        <table style="with: 80%">
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" value="${user.getName()}" pattern="[а-яА-ЯёЁa-zA-Z]{1-25}"
                           title="Name should be 1-25 size" required/></td>
            </tr>
            <tr>
                <td>Surname</td>
                <td><input type="text" name="surname" value="${user.getSurname()}" pattern="[а-яА-ЯёЁa-zA-Z]{1-25}"
                           title="Surname should be 1-25 size" required/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" value="${user.getEmail()}" required/></td>
            </tr>
            <tr>
                <td>Telephone</td>
                <td><input type="tel" name="telephone" value="${user.getTelephone()}" pattern="[0-9]{11,12}"
                           title="Start with code of your country. Telephone should not contain letters, use digits from 0 to 9, size should be 11-12 symbols"
                           required/></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><input type="text" name="address" value="${user.getAddress()}" required/></td>
            </tr>
        </table>
        <button type="submit" class="btn btn-primary btn-sm" href="order-all">Buy</button>
            </c:if>
    </form>
</div>

</body>
</html>
