

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f9f9f9; }
        header{ background: #444; color: white; padding: 10px; text-align: center; }
        a { color: red; font-weight: bold; margin: 0 15px; text-decoration: none; font-weight: bold; }
        a:hover { text-decoration: underline; }

        .product {text-align: center; border:1px solid ; padding:8px; margin:8px; width:200px; height: 300px; float:left; }
        .hinh {margin:8px; width:150px; height: 150px;}
    </style>
</head>
<body>
<div>
    <header><h1>IUH BOOKSTORE</h1></header>
    <a href="cart">Xem giỏ hàng</a>
</div>

<div>
    <c:forEach items="${products}" var="p">
        <div class="product">
            <b>${p.title}</b><br/>
            <img src="${pageContext.request.contextPath}/img/${p.img}" class="hinh"/><br/>
            Giá: ${p.price} VND<br/>
            <form action="cart" method="post">
                <input type="hidden" name="id" value="${p.id}"/>
                <input type="hidden" name="action" value="add"/>
                <input type="submit" value="Thêm vào giỏ"/>
            </form>
            <a href="product?id=${p.id}">Chi tiết</a>
        </div>

    </c:forEach>
</div>

</body>

</html>
