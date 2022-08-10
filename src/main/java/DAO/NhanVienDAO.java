/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.NhanVien;
import Utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String> {

    final String INSERT_SQL = "insert into NhanVien (MaNV,MatKhau,HoTen,VaiTro) values(?,?,?,?)";
    final String UPDATE_SQL = "update NhanVien set HoTen =?,MatKhau=?,VaiTro=? where MaNV =?";
    final String DELETE_SQL = "delete from NhanVien where MaNV=?";
    final String SELECALL_SQL = "Select * from NhanVien";
    final String SELLECTBYID_SQL = "Select * from NhanVien where MaNV=?";

    @Override
    public void insert(NhanVien entity) {
        XJdbc.update(INSERT_SQL,
                entity.getMaNV(),
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.isVaiTro());

    }

    @Override
    public void update(NhanVien entity) {
        XJdbc.update(UPDATE_SQL,
                entity.getHoTen(),
                entity.getMatKhau(),
                entity.isVaiTro(),
                entity.getMaNV()
        );
    }

    @Override
    public void delete(String ID) {
        XJdbc.update(DELETE_SQL, ID);
    }

    @Override
    public List<NhanVien> seletAll() {
        return seletBySQL(SELECALL_SQL);
    }

    @Override
    public NhanVien seletByID(String id) {
        List<NhanVien> list = seletBySQL(SELLECTBYID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> seletBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
