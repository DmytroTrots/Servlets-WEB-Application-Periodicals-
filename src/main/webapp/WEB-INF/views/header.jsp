<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
    <div class="header-dark">
        <nav class="navbar navbar-dark navbar-expand-md navigation-clean-search">
            <div class="container"><a class="navbar-brand" href="/shop?currentPage=1&category=0">Periodicals</a>
                <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span
                        class="sr-only">Toggle navigation</span><span
                        class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse"
                     id="navcol-1">
                    <ul class="nav navbar-nav">
                        <c:if test="${sessionScope['Role'] == 'customer'}">
                            <li class="nav-item" role="presentation"><a class="nav-link"
                                                                        href="/shop?currentPage=1&category=0">Shop</a>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="profile">Profile</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="cart">Cart<span
                                    class="badge badge-danger">${(cart_list.size()>0)?cart_list.size():null}</span></a>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="send-message">Send massage</a>
                            </li>
                            <a class="btn btn-light action-button" role="button"
                               href="top-up">Balance: ${(balance>0)?balance:0}</a>
                        </c:if>
                        <c:if test="${sessionScope['Role'] == 'admin'}">
                            <li class="nav-item" role="presentation"><a class="nav-link"
                                                                        href="/fileupload">Periodicals</a>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="/addUser">Users</a>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="/shop">Orders</a></li>
                        </c:if>
                        <c:if test="${sessionScope['Role'] == 'manager'}">
                            <li class="nav-item" role="presentation"><a class="nav-link"
                                                                        href="/fileupload">Periodicals</a>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="/shop">Orders</a></li>
                        </c:if>
                    </ul>
                    <c:choose>
                        <c:when test="${sessionScope['Role'] == null}">
                            <span class="navbar-text"><a href="/login" class="login">Log In</a></span>
                            <a class="btn btn-light action-button" role="button" href="/registration">Sign Up</a>
                        </c:when>
                        <c:otherwise>
                            <span class="navbar-text"><a href="/logout" class="login">LogOut</a></span>
                        </c:otherwise>
                    </c:choose>
                    <ul>
                        <li><a href="?sessionLocale=en"><fmt:message key="label.lang_en"/></a></li>
                        <li><a href="?sessionLocale=ua"><fmt:message key="label.lang_ua"/> </a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>
