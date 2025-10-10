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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/DanhSachTinTucServlet")
public class DanhSachTinTucServlet extends HttpServlet {
    @Resource(name="jdbc/quanlydanhmuc")
    private DataSource dataSource;

    private DanhSachTinTucQuanLy dao;

    @Override
    public void init() throws ServletException {
        dao = new DanhSachTinTucQuanLy(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<TinTuc> list = dao.getAllTinTuc();
        Map<Integer, String> mapTenDM = new HashMap<>();
        for (TinTuc t : list) {
            mapTenDM.put(t.getMaDM(), dao.findDanhMucByID(t.getMaDM()));
        }
        req.setAttribute("listTin", list);
        req.setAttribute("mapTenDM", mapTenDM);
        req.getRequestDispatcher("DanhSachTinTuc.jsp").forward(req, resp);

    }
}