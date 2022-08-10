/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.NguoiHoc;
import Utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    @Override
    public void insert(NguoiHoc model) {
        String sql
                = " INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV,NgayDK) VALUES( ?,?,?,?,?,?,?,?,?)";
        XJdbc.update(sql,
                model.getMaNH(),
                model.getHoTen(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getDienThoai(),
                model.getEmail(),
                model.getGhiChu(),
                model.getMaNV(),
                model.getNgayDK());

    }

    @Override
    public void update(NguoiHoc model) {
        String sql = "UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=? WHERE MaNH=?";
        XJdbc.update(sql,
                model.getHoTen(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getDienThoai(),
                model.getEmail(),
                model.getGhiChu(),
                model.getMaNH());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM NguoiHoc WHERE MaNH=?";
        XJdbc.update(sql, id);
    }

    @Override
    public List<NguoiHoc> seletAll() {
        String sql = "Select * from NguoiHoc";
        return seletBySQL(sql);
    }

    @Override
    public NguoiHoc seletByID(String id) {
        String sql = "Select * from NguoiHoc where MaNH = ?";
        List<NguoiHoc> list = seletBySQL(sql, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiHoc> seletBySQL(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    NguoiHoc entity = new NguoiHoc();
                    entity.setMaNH(rs.getString("MaNH"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setNgaySinh(rs.getDate("NgaySinh"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setDienThoai(rs.getString("DienThoai"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayDK(rs.getDate("NgayDK"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<NguoiHoc> selectByCourse(int MaKH) {
        String sql = "SELECT * FROM NguoiHoc WHERE MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return seletBySQL(sql, MaKH);
    }

    public List<NguoiHoc> seletByIDlist(String id) {
        String sql = "Select * from NguoiHoc where MaNH like ?";
        return seletBySQL(sql, id + "%");

    }
}
