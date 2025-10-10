package iuh.se.nguyenngochongminh_tuan5_bai6.dao;

import iuh.se.nguyenngochongminh_tuan5_bai6.model.DanhMuc;
import iuh.se.nguyenngochongminh_tuan5_bai6.model.TinTuc;
import iuh.se.nguyenngochongminh_tuan5_bai6.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DanhSachTinTucQuanLy {

    private DBUtil dbutil;
    public DanhSachTinTucQuanLy(DataSource dataSource) {
        dbutil = new DBUtil(dataSource);
    }

    public List<TinTuc> getAllTinTuc () {
        List<TinTuc> list = new ArrayList<>();
        String sql = "SELECT * FROM TINTUC";
        try (Connection con = dbutil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TinTuc t = new TinTuc(
                        rs.getInt("MATT"),
                        rs.getString("TIEUDE"),
                        rs.getString("NOIDUNGTT"),
                        rs.getString("LIENKET"),
                        rs.getInt("MADM")
                );
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TinTuc> getTinTucByDanhMuc(int maDM) {
        List<TinTuc> list = new ArrayList<>();
        String sql = "SELECT * FROM TINTUC WHERE MADM=?";
        try (Connection con = dbutil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maDM);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TinTuc t = new TinTuc(
                        rs.getInt("MATT"),
                        rs.getString("TIEUDE"),
                        rs.getString("NOIDUNGTT"),
                        rs.getString("LIENKET"),
                        rs.getInt("MADM")
                );
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean addTinTuc(TinTuc t) {
        String sql = "INSERT INTO TINTUC (TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES (?,?,?,?)";
        try (Connection con = dbutil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTieuDe());
            ps.setString(2, t.getNoiDungTT());
            ps.setString(3, t.getLienKet());
            ps.setInt(4, t.getMaDM());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteTinTuc(int maTT) {
        String sql = "DELETE FROM TINTUC WHERE MATT=?";
        try (Connection con = dbutil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maTT);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<DanhMuc> getAllDanhMuc() {
        List<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT * FROM DANHMUC";
        try (Connection con = dbutil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DanhMuc dm = new DanhMuc(
                        rs.getInt("MADM"),
                        rs.getString("TENDANHMUC"),
                        rs.getString("NGUOIQUANLY"),
                        rs.getString("GHICHU")
                );
                list.add(dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public String findDanhMucByID(int maDM) {
        String ten = null;
        String sql = "SELECT TENDANHMUC FROM DANHMUC WHERE MADM = ?";
        try (Connection con = dbutil.getConnection()) {
             PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maDM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ten = rs.getString("TENDANHMUC");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ten;
    }
}
