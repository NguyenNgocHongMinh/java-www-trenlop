package iuh.se.nguyenngochongminh_tuan5_bai6.servlets;

import iuh.se.nguyenngochongminh_tuan5_bai6.dao.DanhSachTinTucQuanLy;
import iuh.se.nguyenngochongminh_tuan5_bai6.model.TinTuc;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
@WebServlet("/TinTucFormServlet")
public class TinTucFormServlet extends HttpServlet {
    @Resource(name="jdbc/quanlydanhmuc")
    private DataSource dataSource;

    private DanhSachTinTucQuanLy dao;

    @Override
    public void init() throws ServletException {
        dao = new DanhSachTinTucQuanLy(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listDanhMuc", dao.getAllDanhMuc());
        req.getRequestDispatcher("TinTucForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tieuDe = req.getParameter("tieude");
        String noiDung = req.getParameter("noidung");
        String lienKet = req.getParameter("lienket");
        String madmStr = req.getParameter("madm");

        int madm = Integer.parseInt(madmStr);
        TinTuc t = new TinTuc();
        t.setTieuDe(tieuDe);
        t.setNoiDungTT(noiDung);
        t.setLienKet(lienKet);
        t.setMaDM(madm);
        boolean ok = dao.addTinTuc(t);
        if (ok) {
            req.setAttribute("msg", "Thêm tin tức thành công!");
        } else {
            req.setAttribute("msg", "Có lỗi khi thêm tin tức!");
        }
        req.getRequestDispatcher("KetQua.jsp").forward(req, resp);
    }
}
