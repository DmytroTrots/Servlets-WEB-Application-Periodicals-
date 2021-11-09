<%@ page import="java.util.ArrayList" %>
<%@ page import="com.trots.periodacals.entity.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.trots.periodacals.daoimpl.PeriodicalsDaoImpl" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.trots.periodacals.daoimpl.UserDaoImpl" %>
<%@ page import="com.trots.periodacals.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("decimalFormat", dcf);
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartPeriodical = null;
    if (cart_list != null) {
        PeriodicalsDaoImpl periodicalsDao = new PeriodicalsDaoImpl();
        cartPeriodical = periodicalsDao.getAllCartPeriodical(cart_list);
        request.setAttribute("cart_list", cart_list);
        double total = periodicalsDao.getTotalPriceOfCart(cart_list);
        session.setAttribute("totalPrice", total);
    }
%>

<html>
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
<%@include file="WEB-INF/views/header.jsp" %>
<div class="container">
    <div class="d-flex py-3"><h3>Total price: $ ${(totalPrice>0)?decimalFormat.format(totalPrice):0}</h3><a
            class="mx-3 btn btn-primary" href="#">Check Out</a></div>
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
            <tbody>
            <%
                if (cart_list != null) {
                    for (Cart c : cartPeriodical) {%>
            <tr>
                <td><%= c.getTitle()%>
                </td>
                <td><%= c.getPublisher()%>
                </td>
                <td><%= dcf.format(c.getPricePerMonth())%>
                </td>
                <td>
                    <form action="" method="post" class="form-inline">
                        <input type="hidden" name="id" value="<%= c.getSellId()%>" class="form-input">
                        <div class="form-group d-flex justify-content-between w-50">
                            <a class="btn bnt-sm btn-decre" href="inc-dec?action=dec&id=<%= c.getSellId()%>"><i
                                    class="fas fa-minus-square"></i> </a>
                            <input type="number" name="month" class="form-control w-50" value="<%= c.getMonths()%>"
                                   readonly>
                            <a class="btn bnt-sm btn-incre" href="inc-dec?action=inc&id=<%= c.getSellId()%>"><i
                                    class="fas fa-plus-square"></i> </a>
                        </div>
                    </form>
                </td>
                <td><a class="btn btn-sm btn-danger" href="remove-record?id=<%= c.getSellId()%>"> Remove </a></td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
        <table style="with: 80%">
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" value="${user.getName()}"/></td>
            </tr>
            <tr>
                <td>Surname</td>
                <td><input type="text" name="surname" value="${user.getSurname()}"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" value="${user.getEmail()}"/></td>
            </tr>
            <tr>
                <td>Telephone</td>
                <td><input type="text" name="telephone" value="${user.getTelephone()}"/></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><input type="text" name="address" value="${user.getAddress()}"/></td>
            </tr>
        </table>
        <button type="submit" class="btn btn-primary btn-sm" href="order-all">Buy</button>>
    </form>
</div>

</body>
</html>
