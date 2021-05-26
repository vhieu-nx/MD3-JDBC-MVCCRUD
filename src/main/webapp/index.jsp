<%--
  Created by IntelliJ IDEA.
  User: Mr.Nguyen
  Date: 5/25/2021
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
  <h1>Login Form</h1>
  <a href="LoginServlet?action=create"> create </a>
  <form action="LoginServlet" method="post">
    UserName <input type="text" name="txtUser" placeholder="Enter your username" ><br/>
    Password <input type="password" name="txtPass" placeholder="Enter your password"><br/>
    <input type="submit" value="Login" name="action">
    <input type="reset" value="Reset">
  </form>
  </body>
</html>
