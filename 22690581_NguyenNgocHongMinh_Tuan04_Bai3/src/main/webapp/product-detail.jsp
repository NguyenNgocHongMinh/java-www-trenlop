<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 15/09/2025
  Time: 10:02 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Product Detail</title>
    <style>
        .product { border:1px solid #ddd; padding:8px; margin:8px; width:200px; float:left; }
        .clearfix::after{content:"";display:block;clear:both;}
        .hinh {margin:8px; width:150px; height: 150px;}
    </style>
</head>
<body>
<h2>Product Detail</h2>
<c:if test="${not empty product}">
    <ul>
        <li>Id: ${product.id}</li>
        <li>Model: ${product.model}</li>
        <li>Description: ${product.description}</li>
        <li>Quantity: ${product.quantity}</li>
        <li>Price: ${product.price}</li>
        <img src="${pageContext.request.contextPath}/img/${product.img}"
             alt="${product.model}" class="hinh"/>
    </ul>
    <form action="${pageContext.request.contextPath}/cart" method="post">
        <input type="hidden" name="id" value="${product.id}"/>
        <input type="hidden" name="action" value="add"/>
        <input type="submit" value="Add To Cart"/>
    </form>
</c:if>
<p><a href="${pageContext.request.contextPath}/products">Back to Product List</a></p>
</div>
</body>
</html>
