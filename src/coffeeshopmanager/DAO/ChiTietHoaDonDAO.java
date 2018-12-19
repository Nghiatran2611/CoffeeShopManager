/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.DAO;

import coffeeshopmanager.Model.ChiTietHoaDon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tinh
 */
public class ChiTietHoaDonDAO {
    private DataProvider dp;
    private static ChiTietHoaDonDAO hoadon;
    
     //Connect to database
    public ChiTietHoaDonDAO() throws ClassNotFoundException, SQLException {
        dp = new DataProvider();
    }

    
    
    //Get all CTHD in database
    public List<ChiTietHoaDon> getAllChiTietHoaDonByMaHoaDon(int maHD) throws SQLException, ClassNotFoundException {
        List<ChiTietHoaDon> chitiet = new ArrayList<>();
        String sql = "SELECT * FROM chitiethoadon WHERE MaHoaDon = ?";
        PreparedStatement ps = dp.getConnection().prepareStatement(sql);
        ps.setInt(1, maHD);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("MaChiTietHoaDon");
                int maHoaDon = rs.getInt("MaHoaDon");
                int maMon = rs.getInt("MaMon");
                int soLuong = rs.getInt("SoLuong");
                ChiTietHoaDon qt = new ChiTietHoaDon(id, maHoaDon, maMon, soLuong);
                chitiet.add(qt);
            }
        }
        dp.closeDB();
        return chitiet;
    }
    //Insert CTHD
    public boolean insertCTHD(int maHoaDon, int maMon, int soLuong) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO chitiethoadon (MaHoaDon, MaMon, SoLuong)"
                + "VALUES (?,?,?)";
        int i;
        int tt = 0;
        try (PreparedStatement ps = dp.getConnection().prepareStatement(sql)) {
            ps.setInt(1,maHoaDon);
            ps.setInt(2,maMon);
            ps.setInt(3,soLuong);
            i = 0;
            i = ps.executeUpdate();
        }
        dp.closeDB();
        return i != 0;
    }
    
    //Make deleted ChiTietHoaDon item
    public boolean reverseChiTietHoaDonItem(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM chitiethoadon WHERE MaChiTietHoaDon = ?";
        int i;
        try (PreparedStatement ps = dp.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            i = 0;
            i = ps.executeUpdate();
        }
        dp.closeDB();
        return i != 0;
    }
}
