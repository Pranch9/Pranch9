<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<c:url value="/user-update" var="var"/>
<form action="${var}" method="POST">
    <input type="hidden" name="id" value="${user.id}">

    <label for="phone">Phone</label>
    <input type="text" name="phone" id="phone"><br>

    <label for="department">Department</label>
    <input type="text" name="department" id="department"><br>


    <input type="submit" value="Edit User">
</form>
</body>
</html>