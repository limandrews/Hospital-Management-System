<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <title>Hospital Management System</title>
</head>
<body class="bg-dark text-light">
    <div class="container w-25 border rounded-2 mt-4 p-4 pt-0">
        <c:if test="${logoutMessage != null}">
            <c:out value="${logoutMessage}"></c:out>
        </c:if>
        <h1>Login</h1>
        <c:if test="${errorMessage != null}">
            <c:out value="${errorMessage}"></c:out>
        </c:if>
        <form method="POST" action="/login">
            <p>
                <label class="form-label" for="email">Email</label>
                <input class="form-control" type="text" id="email" name="email">
            </p>
            <p>
                <label class="form-label" for="password">Password</label>
                <input class="form-control" type="password" id="password" name="password"/>
            </p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Login!"/>
        </form>
    </div>
</body>
</html>
