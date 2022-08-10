/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.KhoaHoc;
import Utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer> {

    @Override

    public void insert(KhoaHoc model) {
        String sql = "INSERT INTO KhoaHoc ( MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getMaCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getNgayKG(),
                model.getGhiChu(),
                model.getMaNV(),
                model.getNgayTao());
    }

    @Override
    public void update(KhoaHoc model) {
        String sql = "UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=? WHERE MaKH=?";
        XJdbc.update(sql,
                model.getMaCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getNgayKG(),
                model.getGhiChu(),
                model.getMaNV(),
                model.getMaKH());
    }

    @Override
    public void delete(Integer ID) {
        String sql = "DELETE FROM KhoaHoc WHERE MaKH=?";
        XJdbc.update(sql, ID);
    }

    @Override
    public List<KhoaHoc> seletAll() {
        String sql = "Select * from KhoaHoc";
        return seletBySQL(sql);
    }

    @Override
    public KhoaHoc seletByID(Integer id) {
        String sql = "SELECT * FROM KhoaHoc WHERE MaKH=?";
        List<KhoaHoc> list = seletBySQL(sql, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhoaHoc> seletBySQL(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    KhoaHoc entity = new KhoaHoc();
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setMaCD(rs.getString("MaCD"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    entity.setNgayKG(rs.getDate("NgayKG"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayTao(rs.getDate("NgayTao"));
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

    public List<KhoaHoc> selectKhoaHocByChuyenDe(String maCD) {
        String sql = "Select * from KhoaHoc where MaCD=?";
        return seletBySQL(sql, maCD);
    }

    public List<Integer> selectYears() {
        String sql = "SELECT DISTINCT year (NgayKG) Year from KhoaHoc ORDER BY Year Desc";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
