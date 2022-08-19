<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <title>Document</title>
</head>
<body class="bg-dark text-light">
    <div class="container w-25 border rounded-2 mt-4 p-4 pt-0">
        <h1>Register!</h1>
        
        <p><form:errors path="user.*"/></p>
        
        <form:form method="POST" action="/registration" modelAttribute="user">
            <p>
                <label for="role">Register as:</label>
                <select name="role">
                    <option value=0>Patient</option>
                    <option value=1>Doctor</option>
                    <option value=2>Admin</option>
                </select>
            </p>
            <p>
                <form:label class="form-label" path="firstName">First Name</form:label>
                <form:input class="form-control" type="text" id="firstName" path="firstName"/>
            </p>
            <p>
                <form:label class="form-label" path="lastName">Last Name</form:label>
                <form:input class="form-control" type="text" id="lastName" path="lastName"/>
            </p>
            <p>
                <form:label class="form-label" path="email">Email</form:label>
                <form:input class="form-control" type="text" id="email" path="email"/>
            </p>
            <p>
                <form:label class="form-label" path="password">Password:</form:label>
                <form:password class="form-control" path="password"/>
            </p>
            <p>
                <form:label class="form-label" path="passwordConfirmation">Password Confirmation:</form:label>
                <form:password class="form-control" path="passwordConfirmation"/>
            </p>
            <input type="submit" value="Register!"/>
        </form:form>
    </div>
</body>
</html>