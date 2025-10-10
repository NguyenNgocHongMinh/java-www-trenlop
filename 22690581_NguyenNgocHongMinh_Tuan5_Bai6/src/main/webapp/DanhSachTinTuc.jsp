<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 23/09/2025
  Time: 5:00 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Danh sách tin tức</title>
    <link rel="stylesheet" href="./css/style.css"/>
</head>
<br>
<h2>Danh sách tin tức</h2>
<table border="1" cellpadding="5">
    <tr>
        <th>Mã TT</th><th>Tiêu đề</th><th>Nội dung</th><th>Liên kết</th><th>Danh mục</th>
    </tr>
    <c:forEach var="t" items="${listTin}">
        <tr>
            <td>${t.maTT}</td>
            <td>${t.tieuDe}</td>
            <td>${t.noiDungTT}</td>
            <td><a href="${t.lienKet}" target="_blank">Xem</a></td>
            <td>${mapTenDM[t.maDM]}</td>
        </tr>
    </c:forEach>
</table>
<a href="TinTucFormServlet">Thêm tin tức mới</a> <br><a href="QuanLyFormServlet">Quản lý danh mục</a>
</body>
</html>
