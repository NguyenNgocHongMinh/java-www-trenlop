<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/22/2025
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Employees</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">


    <h2 class="mb-3">Employees List</h2>

    <a class="btn btn-primary mb-3" href="${pageContext.request.contextPath}/employees?action=new">Add Employee</a>

    <table class="table table-bordered table-striped table-hover">
        <thead class="table-primary">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Salary</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emp" items="${employees}">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.name}</td>
                <td>${emp.salary}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/employees?action=edit&id=${emp.id}" class="btn btn-sm btn-warning">Edit</a>
                    <a href="${pageContext.request.contextPath}/employees?action=delete&id=${emp.id}" class="btn btn-sm btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/departments">Department</a>
</div>
</body>
</html>