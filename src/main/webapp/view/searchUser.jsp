<%--
  Created by IntelliJ IDEA.
  User: Mr.Nguyen
  Date: 5/25/2021
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<font color="red"> Wellcome, ${sessionScope.USER}</font>
<h1>Search Page</h1>
<form action="LoginServlet" method="post">
    Search Value <input type="text" name="txtSearchValue" value=""><br/>
    <input type="submit" value="search" name="action" >
</form>
</body>
</html>
