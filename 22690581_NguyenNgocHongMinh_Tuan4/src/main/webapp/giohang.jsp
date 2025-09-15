<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 15/09/2025
  Time: 8:47 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Giỏ hàng</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f9f9f9; }
        header, nav { background: #444; color: white; padding: 10px; text-align: center; }
        nav a { color: white; margin: 0 15px; text-decoration: none; font-weight: bold; }
        nav a:hover { text-decoration: underline; }
        table { width: 90%; margin: auto; border-collapse: collapse; background: white; }
        table th, table td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        table th { background: #f2f2f2; }
        input[type=submit] { background: #444; color: white; border: none; padding: 5px 10px; cursor: pointer; }
        input[type=submit]:hover { background: #666; }
    </style>
</head>
<body>
    <div>
        <header><h1>IUH BOOKSTORE</h1></header>
    </div>
    <h2  style="text-align:center;">Giỏ hàng</h2>
    <c:if test="${empty cart.items}">
        <p style="text-align:center;">Giỏ hàng trống!</p>
        <a href="products"><button>Continue Shopping</button></a>
    </c:if>
    <c:if test="${not empty cart.items}">
        <table>
            <tr>
                <th>Product id</th><th>Product name</th><th>Price</th><th>Qty</th><th>Total</th><th>Remove</th>
            </tr>
            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td>${item.product.id}</td>
                    <td>${item.product.title}</td>
                    <td>
                        <form action="cart" method="post">
                            <input type="hidden" name="action" value="update"/>
                            <input type="hidden" name="productId" value="${item.product.id}"/>
                            <input type="number" name="quantity" value="${item.quantity}" min="1"/>
                            <input type="submit" value="Cập nhật"/>
                        </form>
                    </td>
                    <td>${item.product.price}</td>
                    <td>${item.subtotal}</td>
                    <td>
                        <form action="cart" method="post">
                            <input type="hidden" name="action" value="remove"/>
                            <input type="hidden" name="productId" value="${item.product.id}"/>
                            <input type="submit" value="Remove"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p style="text-align:center; margin-top:10px;"><b>Tổng cộng:</b> ${cart.total} VND</p>
        <div style="text-align:center;">
            <a href="thanhtoan.jsp"><button>Checkout</button></a>
            <a href="products"><button>Continue Shopping</button></a>
        </div>

    </c:if>

</body>
</html>
