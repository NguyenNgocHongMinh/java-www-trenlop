<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/22/2025
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Information</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">

    <h2>Employee Form</h2>

    <form action="${pageContext.request.contextPath}/employees" method="post" class="mb-3">
        <input type="hidden" name="id" value="${employee.id}"/>

        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <input type="text" name="name" class="form-control" id="name" value="${employee.name}" required/>
        </div>

        <div class="mb-3">
            <label for="salary" class="form-label">Salary:</label>
            <input type="number" step="0.01" name="salary" class="form-control" id="salary" value="${employee.salary}" required/>
        </div>

        <div class="mb-3">
            <label for="department" class="form-label">Department:</label>
            <select name="departmentId" class="form-select" id="department" required>
                <c:forEach var="dep" items="${departments}">
                    <option value="${dep.id}"
                            <c:if test="${employee.department != null and employee.department.id == dep.id}">selected</c:if>>
                            ${dep.name}
                    </option>
                </c:forEach>
            </select>

        </div>

        <button type="submit" class="btn btn-success">Save</button>
        <a href="${pageContext.request.contextPath}/employees" class="btn btn-secondary">Cancel</a>
    </form>

</div>
</body>
</html>

