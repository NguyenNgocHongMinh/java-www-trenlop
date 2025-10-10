<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 23/09/2025
  Time: 5:00 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Thêm tin tức</title>
    <link rel="stylesheet" href="./css/style.css"/>
</head>
<body>
<h2>Thêm tin tức mới</h2>
<form action="TinTucFormServlet" method="post">
    Tiêu đề: <input type="text" name="tieude" required/><br/>
    Nội dung: <input type="text" name="noidung" maxlength="255" required/><br/>
    Liên kết: <input type="url" name="lienket" pattern="http://.*" required/><br/>
    Danh mục:
    <select name="madm" required>
        <c:forEach var="dm" items="${listDanhMuc}">
            <option value="${dm.maDM}">${dm.tenDanhMuc}</option>
        </c:forEach>
    </select><br/><br/>
    <input type="submit" value="Thêm"/>
</form>
<a href="DanhSachTinTucServlet">Về danh sách</a>
</body>
</html>
