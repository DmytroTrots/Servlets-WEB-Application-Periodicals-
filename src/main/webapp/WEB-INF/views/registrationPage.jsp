<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div align="center">
    <h1>Customer Register Form</h1>
    <form action="<%= request.getContextPath() %>/registration" method="post">
        <table style="with: 80%">
            <tr>
                <td>Username</td>
                <td><input type="text" name="username" required pattern="[a-zA-Z0-9]{6,18}"
                           title="Username should contain only Aa-Zz letters and 0-9 number, size should be from 6 to 18 symbols"/>
                </td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" required/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,16}$" title="Password should be 8-16 symbols, with at least 1 upper case, 1 lower case, 1 special symbol, 1 number" required/></td>
            </tr>
            <tr>
                <td>Surname</td>
                <td><input type="text" name="surname" required pattern="[а-яА-ЯёЁa-zA-Z]{1-25}" title="Surname should be 1-25 size"/></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" required pattern="[а-яА-ЯёЁa-zA-Z]{1-25}" title="Surname should be 1-25 size"/></td>
            </tr>
            <tr>
                <td>Telephone</td>
                <td><input type="tel" name="telephone" pattern="[0-9]{11,12}" title="Start with code of your country. Telephone should not contain letters, use digits from 0 to 9, size should be 11-12 symbols" required/></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><input type="text" name="address" required /></td>
            </tr>
            <input type="hidden" name="role" value="customer"/>
        </table>
        <input type="submit" value="Submit"/>
    </form>
</div>
Все поля обязательны для заполнения
</body>
</html>
