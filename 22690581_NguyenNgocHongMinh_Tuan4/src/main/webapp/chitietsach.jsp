<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 15/09/2025
  Time: 8:43 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Chi tiet sach</title>
  <style>
    body { font-family: Arial, sans-serif; background: #f9f9f9; }
    header, nav { background: #444; color: white; padding: 10px; text-align: center; }
    nav a { color: white; margin: 0 15px; text-decoration: none; font-weight: bold; }
    nav a:hover { text-decoration: underline; }
    h2 { text-align: center;}
    .product-detail { background: white; padding: 20px; border: 1px solid #ccc; margin: 20px auto; width: 400px; }
    .product-detail img { display: block; margin: auto;  width: 200px; height: 250px; }
  </style>
</head>
<body>
<header><h1>IUH BOOKSTORE</h1></header>
<h2>Chi tiết sách</h2>
<div class="product-detail">
  <c:if test="${not empty product}">
    <h3>${product.title}</h3>
    <img src="${pageContext.request.contextPath}/img/${product.img}" />
    <p>Tác giả: ${product.author}</p>
    <p>Giá: ${product.price} VND</p>
    <p>Mô tả: ${product.description}</p>
    <p>Số lượng trong kho: ${product.quantity}</p>
  </c:if>
</div>

<a href="products">Quay lại danh sách</a>
</body>
</html>
