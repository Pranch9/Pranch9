<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <table>
        <tr>
            <th>id |</th>
            <th>username |</th>
            <th>firstName |</th>
            <th>lastName |</th>
            <th>patronymic |</th>
            <th>department |</th>
            <th>phone |</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <h1>
                    <td>${user.id} |</td>
                    <td>${user.username} |</td>
                    <td>${user.firstName} |</td>
                    <td>${user.lastName} |</td>
                    <td>${user.patronymic} |</td>
                    <td>${user.department} |</td>
                    <td>${user.phone} |</td>
                </h1>
                <td>
                    <a href="/user-update/${user.id}">Edit</a>
                    <a href="/user-delete/${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/admin">Back</a>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Admin Page ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </c:if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>