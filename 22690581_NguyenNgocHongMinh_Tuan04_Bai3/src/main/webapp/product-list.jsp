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
    <title>Product List</title>
    <style>
        .product { border:1px solid #ddd; padding:8px; margin:8px; width:200px; float:left; }
        .hinh {margin:8px; width:150px; height: 150px;}
        .clearfix::after{content:"";display:block;clear:both;}
    </style>
</head>
<body>
<h2>Products</h2>
<p><a href="${pageContext.request.contextPath}/cart">View Cart</a></p>
<div class="clearfix">
    <c:forEach items="${products}" var="p">
        <div class="product">
            <b>${p.model}</b><br/>
            <img src="${pageContext.request.contextPath}/img/${p.img}" class="hinh"/>
            Price: ${p.price}<br/>
            <form action="${pageContext.request.contextPath}/cart" method="post">
                <input type="text" size="2" value="1" name="quantity"> <br/>
                <input type="hidden" name="id" value="${p.id}">
                <input type="hidden" name="price" value="${p.price}">
                <input type="hidden" name="model" value="${p.model}">
                <input type="hidden" name="action" value="add"><br/>
                <input type="submit" name="addToCart" value="Add To Cart"><br/>
            </form>
            <a href="${pageContext.request.contextPath}/product?id=${p.id}">Product Detail</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
