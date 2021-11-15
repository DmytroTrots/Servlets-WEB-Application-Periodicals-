<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/send-message" method="post">
    <input type="hidden" name="to" value="dtrots27@gmail.com"/><br/>
    <input type="hidden" name="subject" value="Username -> ${sessionScope['userName']}, ID -> ${sessionScope['ID']}, Message from customer" ><br/>
    <textarea rows="10" cols="70" name="message"></textarea><br/>
    <input type="submit" value="Send message"/>
</form>
</body>
</html>
