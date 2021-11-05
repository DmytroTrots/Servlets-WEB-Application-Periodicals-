
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddUser</title>
</head>
<body>
<div align="center">
    <h1>AddUser</h1>
    <form action="<%= request.getContextPath() %>/addUser" method="post">
        <table style="with: 80%">
            <tr>
                <td>Username</td>
                <td><input type="text" name="username" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" name="email" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>Surname</td>
                <td><input type="text" name="surname" /></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>Telephone</td>
                <td><input type="text" name="telephone" /></td>
            </tr>
            <tr>
                <td>Role</td>
                <td><input type="text" name="nameOfRole" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
