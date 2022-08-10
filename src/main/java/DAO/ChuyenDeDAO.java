/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.ChuyenDe;
import Utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {

    final String INSERT_SQL = "insert into ChuyenDe(MaCD,TenCD,HocPhi,ThoiLuong,MoTa,Hinh) values(?,?,?,?,?,?)";
    final String UPDATE_SQL = "update ChuyenDe set TenCD=?,HocPhi=?,Hinh=?,ThoiLuong=?,MoTa=? where MaCD=?";
    final String DELETE_SQL = "delete from ChuyenDe where MaCD =?";
    final String SELECALL_SQL = "select * from ChuyenDe";
    final String SELLECTBYID_SQL = "select * from ChuyenDe where MaCD =?";

    @Override
    public void insert(ChuyenDe entity) {
        XJdbc.update(INSERT_SQL,
                entity.getMaCD(),
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getMoTa(),
                entity.getHinh()
        );
    }

    @Override
    public void update(ChuyenDe entity) {
        XJdbc.update(UPDATE_SQL,
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getHinh(),
                entity.getThoiLuong(),
                entity.getMoTa(),
                entity.getMaCD()
        );
    }

    @Override
    public void delete(String ID) {
        XJdbc.update(DELETE_SQL, ID);
    }

    @Override
    public List<ChuyenDe> seletAll() {
        return seletBySQL(SELECALL_SQL);
    }

    @Override
    public ChuyenDe seletByID(String id) {
        List<ChuyenDe> list = seletBySQL(SELLECTBYID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> seletBySQL(String sql, Object... args) {
        List<ChuyenDe> ls = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                ChuyenDe cd = new ChuyenDe();
                cd.setMaCD(rs.getString("MaCD"));
                cd.setTenCD(rs.getString("TenCD"));
                cd.setHocPhi(rs.getDouble("HocPhi"));
                cd.setHinh(rs.getString("Hinh"));
                cd.setThoiLuong(rs.getInt("ThoiLuong"));
                cd.setMoTa(rs.getString("MoTa"));
                ls.add(cd);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ls;
    }

}
