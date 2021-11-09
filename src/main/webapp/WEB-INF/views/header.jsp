<div>
    <div class="header-dark">
        <nav class="navbar navbar-dark navbar-expand-md navigation-clean-search">
            <div class="container"><a class="navbar-brand" href="#">Periodicals</a>
                <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                        class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse"
                     id="navcol-1">
                    <ul class="nav navbar-nav">
                        <li class="nav-item" role="presentation"><a class="nav-link" href="/shop">Shop</a></li>
                        <%
                            if ((request.getSession(false).getAttribute("Role") != null)) {
                        %>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="#">Profile</a>
                        </li>
                        <%} %>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="cartPage.jsp">Cart<span class="badge badge-danger">${(cart_list.size()>0)?cart_list.size():null}</span></a>
                        </li>
                        <%
                            if ((request.getSession(false).getAttribute("Role") == "admin")) {
                        %>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="/shop">Administration</a>
                        </li>
                        <%} %>

                        <%
                            if ((request.getSession(false).getAttribute("Role") == "manager")) {
                        %>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="/shop">Orders</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="/shop">Feedback</a></li>
                        <%} %>

                        <%
                            if ((request.getSession(false).getAttribute("Role") == "customer")) {
                        %>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="/shop">Send massage</a></li>
                        <%} %>

                        <%
                            if ((request.getSession(false).getAttribute("Role") != null)) {
                        %>
                        <a class="btn btn-light action-button" role="button" href="/top-up">Balance: ${(balance>0)?balance:0}</a>
                        <%} %>

                    </ul>
                    <%
                        if ((request.getSession(false).getAttribute("Role") == null)) {
                    %>
                    <span class="navbar-text">
                        <a href="/login" class="login">Log In</a>
                    </span>
                    <a class="btn btn-light action-button" role="button" href="/registration">Sign Up</a>
                    <%} else {%>
                    <span class="navbar-text">
                        <a href="/logout" class="login">LogOut</a>
                    </span>
                    <%} %>
                </div>
            </div>
        </nav>
    </div>
</div>
