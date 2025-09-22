<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 22/09/2025
  Time: 9:40 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Department Form</h2>
<form action="${pageContext.request.contextPath}/departments" method="post">
    <input type="hidden" name="action" value="save"/>
    <input type="hidden" name="id" value="${department.id}"/>

    <div class="mb-3">
        <label for="name" class="form-label">Name:</label>
        <input type="text" id="name" name="name" class="form-control"
               value="${department.name}" required/>
    </div>

    <button type="submit" class="btn btn-success">Save</button>
    <a href="${pageContext.request.contextPath}/departments" class="btn btn-secondary">Cancel</a>
</form>


</body>
</html>
