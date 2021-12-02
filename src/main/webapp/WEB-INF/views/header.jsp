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
                    <ul class="nav navbar-navk navbar-nav">
                        <c:if test="${sessionScope['Role'] == null}">
                            <li class="nav-item" role="presentation"><a class="nav-link"
                                                                        href="/shop?currentPage=1&category=0"><fmt:message
                                    key="label.shop"/></a>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="cart"><fmt:message
                                    key="label.cart"/><span
                                    class="badge badge-danger">${(cart_list.size()>0)?cart_list.size():null}</span></a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope['Role'] == 'customer'}">
                            <li class="nav-item" role="presentation"><a class="nav-link"
                                                                        href="/shop?currentPage=1&category=0"><fmt:message
                                    key="label.shop"/></a>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="profile"><fmt:message
                                    key="label.profile"/></a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="cart"><fmt:message
                                    key="label.cart"/><span
                                    class="badge badge-danger">${(cart_list.size()>0)?cart_list.size():null}</span></a>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link"
                                                                        href="send-message"><fmt:message
                                    key="label.sendMessage"/></a>
                            </li>
                            <a class="btn btn-light action-button" role="button"
                               href="top-up"><fmt:message key="label.balanceAcc"/> ${(balance>0)?balance:0}</a>
                        </c:if>
                        <c:if test="${sessionScope['Role'] == 'admin'}">
                            <li class="nav-item" role="presentation"><a class="nav-link"
                                                                        href="/fileupload"><fmt:message
                                    key="label.periodicals"/></a>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="/addUser"><fmt:message
                                    key="label.users"/></a>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link" href="/orders"><fmt:message
                                    key="label.orders"/></a></li>
                        </c:if>
                    </ul>
                    <c:choose>
                        <c:when test="${sessionScope['Role'] == null}">
                            <span class="navbar-text"><a href="/login" class="login"><fmt:message
                                    key="label.logIn"/></a></span>
                            <a class="btn btn-light action-button" role="button" href="/registration"><fmt:message
                                    key="label.signUp"/></a>
                        </c:when>
                        <c:otherwise>
                            <span class="navbar-text"><a href="/logout" class="login"><fmt:message
                                    key="label.logOut"/></a></span>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${requestScope['url'] !=null && requestScope['url']=='/shop'}">
                            <ul>
                                <li><a href="shop?currentPage=${currentPage}&category=${category}&sessionLocale=en"><img
                                        src="/resources/images/flag.png"></a></li>
                                <li><a href="shop?currentPage=${currentPage}&category=${category}&sessionLocale=ua"><img
                                        src="/resources/images/UA-Ukraine-Flag-icon.png"></a></li>
                            </ul>
                        </c:when>
                        <c:when test="${requestScope['url'] !=null && requestScope['url']=='/update-periodical'}">
                            <ul>
                                <li><a href="update-periodical?id=${sellId}&sessionLocale=en"><img
                                        src="/resources/images/flag.png"></a></li>
                                <li><a href="update-periodical?id=${sellId}&sessionLocale=ua"><img
                                        src="/resources/images/UA-Ukraine-Flag-icon.png"></a></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <ul>
                                <li><a href="?sessionLocale=en"><img src="/resources/images/flag.png"></a></li>
                                <li><a href="?sessionLocale=ua"><img
                                        src="/resources/images/UA-Ukraine-Flag-icon.png"></a></li>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>
    </div>
</div>
