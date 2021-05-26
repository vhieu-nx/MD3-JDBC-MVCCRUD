<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mr.Nguyen
  Date: 5/26/2021
  Time: 2:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Form</title>
</head>
<body>

<form method="post" action="LoginServlet" accept-charset="UTF-8">

UserName <input type="text" name="txtUser" value="<c:out value='${user.getUsername()}'/>" disabled><br/>
Password <input type="password" name="txtPass" value="<c:out value='${user.getPassword()}'/>"><br/>
LastName <input type="text" name="txtLast" value="<c:out value='${user.getLastname()}'/>"><br/>
Admin <input type="checkbox" name="chkAdmin" value="<c:out value='${user.isRoles()}'/>"><br/>
<input type="submit" value="update" name="action">
<%--getUsername--%>
<%--getPassword--%>
<%--getLastname--%>
    <%--isRoles--%>
</form>
</body>
</html>
