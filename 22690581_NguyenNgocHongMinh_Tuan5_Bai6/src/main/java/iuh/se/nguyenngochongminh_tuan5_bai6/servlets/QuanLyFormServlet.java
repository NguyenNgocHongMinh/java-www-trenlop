package iuh.se.nguyenngochongminh_tuan5_bai6.servlets;

import iuh.se.nguyenngochongminh_tuan5_bai6.dao.DanhSachTinTucQuanLy;
import iuh.se.nguyenngochongminh_tuan5_bai6.model.DanhMuc;
import iuh.se.nguyenngochongminh_tuan5_bai6.model.TinTuc;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/QuanLyFormServlet")
public class QuanLyFormServlet extends HttpServlet {
    @Resource(name="jdbc/quanlydanhmuc")
    private DataSource dataSource;

    private DanhSachTinTucQuanLy dao;

    @Override
    public void init() throws ServletException {
        dao = new DanhSachTinTucQuanLy(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteId = req.getParameter("delete");
        String maDMParam = req.getParameter("madm");

        // Nếu có xóa tin
        if (deleteId != null) {
            int maTT = Integer.parseInt(deleteId);
            dao.deleteTinTuc(maTT);
        }
        List<DanhMuc> listDM = dao.getAllDanhMuc();
        req.setAttribute("listDM", listDM);
        if (maDMParam != null) {
            int maDM = Integer.parseInt(maDMParam);
            List<TinTuc> listTin = dao.getTinTucByDanhMuc(maDM);
            req.setAttribute("listTin", listTin);
            req.setAttribute("selectedDM", maDM);
        }

        req.getRequestDispatcher("QuanLyForm.jsp").forward(req, resp);
    }
}
