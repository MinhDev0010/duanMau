/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAO.*;
import Entity.*;
import java.util.List;

/**
 *
 * @author DELL
 */
public class test {
    public static void main(String[] args) {
        ChuyenDeDAO dao = new ChuyenDeDAO();
//        dao.insert(new ChuyenDe("JAVA6","Lập trình java",400,90,"Hinh.png","Hay"));
//        NhanVienDAO dao = new NhanVienDAO();
//        dao.insert(new NhanVien("Minh55","123456" ,"Le Hoang", true));
        List<ChuyenDe> ls = dao.seletAll();
        for (ChuyenDe l : ls) {
            System.out.println("==>"+l.toString());
        }
           
    }
}
