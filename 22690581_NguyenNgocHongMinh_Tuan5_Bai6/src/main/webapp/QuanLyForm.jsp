<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quản lý tin tức</title>
    <link rel="stylesheet" href="./css/style.css"/>
</head>
<body>
<h2>Quản lý tin tức theo danh mục</h2>

<form method="get" action="QuanLyFormServlet">
    <label>Chọn danh mục: </label>
    <select name="madm" onchange="this.form.submit()">
        <option value="">--Chọn--</option>
        <c:forEach var="dm" items="${listDM}">
            <option value="${dm.maDM}" <c:if test="${selectedDM == dm.maDM}">selected</c:if>>
                    ${dm.tenDanhMuc}
            </option>
        </c:forEach>
    </select>
</form>

<c:if test="${not empty listTin}">
    <h3>Danh sách tin tức</h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>Mã TT</th><th>Tiêu đề</th><th>Nội dung</th><th>Liên kết</th><th>Thao tác</th>
        </tr>
        <c:forEach var="t" items="${listTin}">
            <tr>
                <td>${t.maTT}</td>
                <td>${t.tieuDe}</td>
                <td>${t.noiDungTT}</td>
                <td><a href="${t.lienKet}" target="_blank">Xem</a></td>
                <td>
                    <a href="QuanLyFormServlet?delete=${t.maTT}&madm=${selectedDM}"
                       onclick="return confirm('Xóa tin này?')">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="TinTucFormServlet">Thêm tin mới</a> |
<a href="DanhSachTinTucServlet">Xem tất cả tin tức</a>
</body>
</html>
