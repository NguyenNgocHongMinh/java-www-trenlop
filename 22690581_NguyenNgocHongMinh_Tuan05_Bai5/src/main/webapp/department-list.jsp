<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 22/09/2025
  Time: 9:32 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Departments List</h2>
<a href="${pageContext.request.contextPath}/departments?action=new" class="btn btn-primary mb-3">Add Department</a>

<form action="${pageContext.request.contextPath}/departments" method="get" class="mb-3">
  <input type="hidden" name="action" value="search"/>
  <label for="keyword">Tìm phòng ban:</label>
  <input type="text" name="keyword" id="keyword"/>
  <button type="submit">Search</button>
  <a href="${pageContext.request.contextPath}/departments" style="margin-left: 10px;">
    <button type="button">Làm mới</button>
  </a>
</form>

<table class="table table-bordered">
  <thead>
  <tr>
    <th>DEPT ID</th>
    <th>Name Department</th>
    <th>Action</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="dept" items="${departments}">
    <tr>
      <td>${dept.id}</td>
      <td>${dept.name}</td>
      <td>
        <a href="${pageContext.request.contextPath}/departments?action=edit&id=${dept.id}">Edit</a> |
        <a href="${pageContext.request.contextPath}/departments?action=delete&id=${dept.id}"
           onclick="return confirm('Are you sure?')">Delete</a> |
        <a href="${pageContext.request.contextPath}/employees?action=viewbyid&deptId=${dept.id}">Employees</a>



      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
