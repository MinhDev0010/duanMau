/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.HocVien;
import Entity.NhanVien;
import Utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class HocVienDAO extends EduSysDAO<HocVien, Integer> {

    @Override
    public void insert(HocVien model) {
        String sql = "INSERT INTO HocVien(MaKH, MaNH, Diem) VALUES(?, ?, ?)";
        XJdbc.update(sql,
                model.getMaKH(),
                model.getMaNH(),
                model.getDiem());
    }

    @Override
    public void update(HocVien model) {
        String sql = "UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
        XJdbc.update(sql,
                model.getMaKH(),
                model.getMaNH(),
                model.getDiem(),
                model.getMaHV());
    }

    @Override
    public void delete(Integer MaHV) {
        String sql = "DELETE FROM HocVien WHERE MaHV=?";
        XJdbc.update(sql, MaHV);
    }

    @Override
    public List<HocVien> seletAll() {
        String sql = "Select * from HocVien";
        return seletBySQL(sql);

    }

    @Override
    public HocVien seletByID(Integer id) {
        String sql = "Select * from HocVien where MaHV=?";
        List<HocVien> list = seletBySQL(sql, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    @Override
    public List<HocVien> seletBySQL(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                HocVien entity = new HocVien();
                entity.setMaHV(rs.getInt("MaHV"));
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaNH(rs.getString("MaNH"));
                entity.setDiem(rs.getDouble("Diem"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
public List<HocVien> selectByKhoaHoc(int maKH) {
        String sql = "Select * from HocVien where MaKH=?";
        return this.seletBySQL(sql, maKH);
    }
}
