<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="${sessionScope.lang}">
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
    <table>
        <tr>
            <td>
                <a href="shop?currentPage=${currentPage}&category=0">All category</a>
            </td>
        </tr>
        <c:forEach var="category" items="${subjectMap}">
            <tr>
                <td>
                    <a href="shop?currentPage=${currentPage}&category=${category.value}">${category.key}</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="container">
    <form action="shop" method="post">

        <table>
            <tr>
                <td>
                    <label for="WithoutSort">WithoutSort</label>
                    <input type="radio" id="WithoutSort" name="sort" value="ws" checked>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="Price(lowToHigh)">Price(lowToHigh)</label>
                    <input type="radio" id="Price(lowToHigh)" name="sort" value="prLtH">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="Price(HighToLow)">Price(HighToLow)</label>
                    <input type="radio" id="Price(HighToLow)" name="sort" value="prHtL">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="Name(Z-A)">Name(Z-A)</label>
                    <input type="radio" id="Name(Z-A)" name="sort" value="nza">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="Name(A-Z)">Name(A-Z)</label>
                    <input type="radio" id="Name(A-Z)" name="sort" value="naz">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="Rating">Rating</label>
                    <input type="radio" id="Rating" name="sort" value="rating">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="Search">Search</label>
                    <input type="text" id="Search" name="searchField" value="${searchField}">
                </td>
            </tr>

        </table>
        <input type="hidden" value="${currentPage}" name="currentPage">
        <input type="hidden" value="${category}" name="category">
        <input type="submit" value="GO">
    </form>
</div>

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
                        <h6 class="rating">Rating: ${list.rating}</h6>
                        <div class="mt-3 d-flex justify-content-between">
                            <form method="post" action="cart">
                                <input name="id" type="hidden" value="${list.sellId}">
                                <input type="hidden" value="${currentPage}" name="currentPage">
                                <input type="hidden" value="${category}" name="category">
                                <input class="btn btn-dark" type="submit" value="Add to cart">
                            </form>
                             <a
                                class="btn btn-primary"
                                href="order-periodical?month=1&id=${list.sellId}&name=${user.getName()}
                                &surname=${user.getSurname()}&address=${user.getAddress()}&email=${user.getEmail()}
                                                                            &telephone=${user.getTelephone()}">Buy
                            Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<nav aria-label="pagination" style="">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="shop?currentPage=${currentPage-1}&category=${category}">Previous</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="shop?currentPage=${i}&category=${category}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="shop?currentPage=${currentPage+1}&category=${category}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>
</body>
</html>
