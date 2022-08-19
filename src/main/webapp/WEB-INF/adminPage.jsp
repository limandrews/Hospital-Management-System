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
    <title>Hospital Management System</title>
</head>
<body class="bg-dark text-light">
    <div class="container w-25 border rounded-2 mt-4 p-4 pt-0">
        <h1>Welcome to the admin page <c:out value="${currentUser.firstName}"></c:out></h1>

        <table class="table table-dark">
            <h1>Doctors</h1>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="doctor" items="${doctors}">
                    <tr>
                        <td><a href="/admin/${doctor.id}/show">${doctor.firstName} ${doctor.lastName}</a></td>
                        <td>
                            <form action="/admin/${doctor.id}/delete" method ="post">
                                <input type="hidden" name="_method" value="delete">
                                <input type="submit" value="TERMINATE">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <table class="table table-dark">
            <h1>Admins</h1>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="admin" items="${admins}">
                    <tr>
                        <td><a href="/admin/${admin.id}/showAdmin">${admin.firstName} ${admin.lastName}</a></td>
                        <td>
                            <c:if test="${admin.id != currentUser.id}">
                                <form action="/admin/${admin.id}/delete" method ="post">
                                    <input type="hidden" name="_method" value="delete">
                                    <input type="submit" value="TERMINATE">
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <table class="table table-dark">
            <h1>Patients</h1>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="patient" items="${patients}">
                    <tr>
                        <td><a href="/admin/${patient.id}/showPatient">${patient.firstName} ${patient.lastName}</a></td>
                        <td>
                            <form action="/admin/${patient.id}/delete" method ="post">
                                <input type="hidden" name="_method" value="delete">
                                <input type="submit" value="DELETE">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="container d-flex justify-content-between">
            <form id="logoutForm" method="POST" action="/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input class="btn btn-light" type="submit" value="Logout!" />
            </form>
            <a class="btn btn-light" href="/registration">Register new employee/patient</a>
        </div>
    </div>
</body>
</html>