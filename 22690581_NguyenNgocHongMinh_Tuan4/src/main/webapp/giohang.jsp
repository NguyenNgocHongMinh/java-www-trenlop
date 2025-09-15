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
</head>
<body>
    <h2>Giỏ hàng</h2>
    <c:if test="${empty cart.items}">
        <p>Giỏ hàng trống!</p>
    </c:if>
    <c:if test="${not empty cart.items}">
        <table>
            <tr>
                <th>Title</th><th>Số lượng</th><th>Giá</th><th>Tổng</th><th>Thao tác</th>
            </tr>
            <c:forEach var="item" items="${cart.items}">
                <tr>
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
                            <input type="submit" value="Xóa"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p><b>Tổng cộng:</b> ${cart.total} VND</p>

        <form action="checkout" method="post">
            <input type="submit" value="Thanh toán"/>
        </form>
    </c:if>
    <a href="products">Tiếp tục mua sách</a>
</body>
</html>
