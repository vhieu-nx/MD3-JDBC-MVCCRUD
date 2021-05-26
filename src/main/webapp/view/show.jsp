<%--
  Created by IntelliJ IDEA.
  User: Mr.Nguyen
  Date: 5/25/2021
  Time: 1:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="form-inline my-2 my-lg-0" method="post" action="LoginServlet?action=search">
    <input class="form-control mr-sm-2" type="search" name="txtSearchValue" placeholder="Search" aria-label="Search">
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit" value="search">Search</button>
</form>
<div>
    <table border="1" cellpadding="5">

        <tr>
            <th>UserName </th>
            <th>Password </th>
            <th>LastName </th>
            <th>Roles </th>

        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td>${user.getUsername()}</td>
                <td>${user.getPassword()}</td>
                <td>${user.getLastname()}</td>
                <td>${user.isRoles()}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/LoginServlet?action=update&id=${user.username}">Edit</a>
                    <a href="${pageContext.request.contextPath}/LoginServlet?action=delete&id=${user.username}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
