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
    <select name="category" id="category">
        <option ${sessionScope['category'] == 0 ? 'selected':''} value="shop?currentPage=1&category=0">
            <fmt:message key="label.allCategory"/></option>
        <c:forEach var="category" items="${subjectMap}">
            <option ${sessionScope['category'] == category.value ? 'selected':''}
                    value="shop?currentPage=1&category=${category.value}">
                    ${category.key}</option>
        </c:forEach>
    </select>
    <form action="shop" method="post">
        </table>
        <select name="sort">
            <option value="ws" ${sessionScope['sort'] == 'ws' ? 'selected':''}><fmt:message
                    key="label.withoutSort"/></option>
            <option value="prLtH" ${sessionScope['sort'] == 'prLtH' ? 'selected':''}><fmt:message
                    key="label.priceLH"/></option>
            <option value="prHtL" ${sessionScope['sort'] == 'prHtL' ? 'selected':''}><fmt:message
                    key="label.priceHL"/></option>
            <option value="nza" ${sessionScope['sort'] == 'nza' ? 'selected':''}><fmt:message
                    key="label.nameZA"/></option>
            <option value="naz" ${sessionScope['sort'] == 'naz' ? 'selected':''}><fmt:message
                    key="label.nameAZ"/></option>
            <option value="rating" ${sessionScope['sort'] == 'rating' ? 'selected':''}><fmt:message
                    key="label.rating"/></option>
        </select>
        <label for="Search"><fmt:message key="label.search"/></label>
        <input type="text" id="Search" name="searchField" value="${searchField}">
        <input type="hidden" value="1" name="currentPage">
        <input type="hidden" value="${category}" name="category">
        <input type="submit" value="<fmt:message key="label.go"/>">
    </form>
</div>


<div class="container">
    <span style="color:red">
        <c:if test="${not empty sessionScope.ex}">
            <div class="alert alert-warning">
                <strong><c:out value="${sessionScope.ex}"/></strong>
            </div>
            <c:set var="ex" value="" scope="session"/>
        </c:if>
    </span>
    <div class="card-header my-3"><fmt:message key="label.periodicals"/></div>
    <div class="row">
        <c:forEach var="list" items="${PERIODICAL}">
            <div class="col-md-3 my-3">
                <div class="card w-100">
                    <img class="card-img-top" src="/resources/images/${list.image}"
                         alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">${list.title}</h5>
                        <h6 class="price"><fmt:message key="label.totalPrice"/>${list.pricePerMonth}</h6>
                        <h6 class="category"><fmt:message key="label.publisherShop"/> ${list.publisher}</h6>
                        <h6 class="rating"><fmt:message key="label.ratingShop"/> ${list.rating}</h6>
                        <h6 class="rating"><fmt:message key="label.periodicity"/> ${list.periodicityPerYear}</h6>
                        <div class="mt-3 d-flex justify-content-between">
                            <form method="post" action="cart">
                                <input name="id" type="hidden" value="${list.sellId}">
                                <input type="hidden" value="${currentPage}" name="currentPage">
                                <input type="hidden" value="${category}" name="category">
                                <input class="btn btn-dark" type="image" src="/resources/images/shopping-icon.png">
                            </form>
                            <a class="btn-primary a-button"
                               href="order-periodical?month=1&id=${list.sellId}&name=${user.getName()}
                                &surname=${user.getSurname()}&address=${user.getAddress()}&email=${user.getEmail()}
                                &telephone=${user.getTelephone()}"><img class="image-buy"
                                                                        src="/resources/images/Cash-icon.png"></a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<c:if test="${requestScope['noOfPages'] != 1}">
    <nav aria-label="pagination">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="shop?currentPage=${currentPage-1}&category=${category}"><fmt:message
                        key="label.previous"/></a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only"><fmt:message key="label.current"/></span></a>
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
                                         href="shop?currentPage=${currentPage+1}&category=${category}"><fmt:message
                        key="label.next"/></a>
                </li>
            </c:if>
        </ul>
    </nav>
</c:if>

<script type="text/javascript">
    var urlmenu = document.getElementById('category');
    urlmenu.onchange = function () {
        document.location.href = this.value;
    };
</script>

</body>
</html>
