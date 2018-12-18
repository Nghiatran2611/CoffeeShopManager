/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.DAO;

import coffeeshopmanager.Model.HoaDon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tinh
 */
public class HoaDonDAO {
    private DataProvider dp;
    private static HoaDon hoadon;
    
     //Connect to database
    public HoaDonDAO() throws ClassNotFoundException, SQLException {
        dp = new DataProvider();
    }
    
    //Get all HoaDon in database
    public List<HoaDon> getAllHoaDon() throws SQLException, ClassNotFoundException {
        List<HoaDon> hoadon = new ArrayList<>();
        String sql = "SELECT * FROM hoadon";
        try (ResultSet rs = dp.executeReader(sql)) {
            while (rs.next()) {
                int id = rs.getInt("MaHoaDOn");
                int maBan = rs.getInt("MaBan");
                HoaDon hd = new HoaDon(id, maBan);
                hoadon.add(hd);
            }
        }
        dp.closeDB();
        return hoadon;
    }
    
    public boolean insertHoaDon(int id) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO hoadon (MaHoaDon, MaBan) "
                + "VALUES (?,?)";
        int i;
        int tt = 0;
        try (PreparedStatement ps = dp.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, id);
                 
            i = 0;
            i = ps.executeUpdate();
        }
        dp.closeDB();
        return i != 0;
    }
    
    public boolean reverseHoaDonItem(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM hoadon WHERE MaHoaDon = ?";
        int i;
        try (PreparedStatement ps = dp.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            i = 0;
            i = ps.executeUpdate();
        }
        dp.closeDB();
        return i != 0;
    }
    
    public static HoaDon getHoaDon(){
        return hoadon;
    }
     public static void setHoaDon(HoaDon hDon) {
        HoaDonDAO.hoadon = hDon;
    }
}
