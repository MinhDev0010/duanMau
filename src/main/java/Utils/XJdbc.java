/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.sql.*;
/**
 *
 * @author DELL
 */
public class XJdbc {
   private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl="jdbc:sqlserver://localhost;database=Polypro";
    private static String username="sa";
    private static String password="123456";
     static{
        try {            
            Class.forName(driver);
        } 
        catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    
 public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{
        Connection connection = DriverManager.getConnection(dburl, username, password);
        PreparedStatement pstmt = null;
        if(sql.trim().startsWith("{")){ 
            pstmt = connection.prepareCall(sql);// store procedure
        }
        else{
            pstmt = connection.prepareStatement(sql);//
        }
        for(int i=0;i<args.length;i++){
            pstmt.setObject(i + 1, args[i])
;//  ps.setString(1,hv.getHoten());
        }
        return pstmt;
    }
public static void update(String sql, Object...args) {//insert, update, detele
        try {
            PreparedStatement stmt = XJdbc.getStmt(sql, args);
            try {
                stmt.executeUpdate();
            } 
            finally{
                stmt.getConnection().close();
            }
        } 
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
public static ResultSet query(String sql, Object...args) {
        try {
            PreparedStatement stmt = XJdbc.getStmt(sql, args);
            return stmt.executeQuery();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
public static Object value(String sql, Object...args) {
        try {
            ResultSet rs = XJdbc.query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
