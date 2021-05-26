<%--
  Created by IntelliJ IDEA.
  User: Mr.Nguyen
  Date: 5/24/2021
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Register Form</h1>
<form action="LoginServlet" method="post">
    UserName <input type="text" name="txtUser" value=""><br/>
    Password <input type="password" name="txtPass" value=""><br/>
    LastName <input type="text" name="txtLast" value=""><br/>
    Admin <input type="checkbox" name="chkAdmin" value=""><br/>
    <input type="submit" value="create" name="action">
</form>
</body>
</html>
