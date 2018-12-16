/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.DAO;

import coffeeshopmanager.Model.Menu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tinh
 */
public class MenuDAO {
    private DataProvider dp;
    private static Menu menu;
    
     //Connect to database
    public MenuDAO() throws ClassNotFoundException, SQLException {
        dp = new DataProvider();
    }
    
    //Get all Menu in database
    public List<Menu> getAllMenu() throws SQLException, ClassNotFoundException {
        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try (ResultSet rs = dp.executeReader(sql)) {
            while (rs.next()) {
                int id = rs.getInt("MaMon");
                String tenMon = rs.getString("TenMon");
                int giaTien = rs.getInt("GiaTien");
                int tinhTrang = rs.getInt("TinhTrang");
                int loai = rs.getInt("Loai");
                String status = "";
                if(tinhTrang == 1){
                    status = "Đã hết";
                }
                else if(tinhTrang == 0){
                    status = "Còn món";
                }
                Menu mn = new Menu(id, tenMon, giaTien, loai, status);
                menuList.add(mn);
            }
        }
        dp.closeDB();
        return menuList;
    }
    
    //Get all Menu in database by loai
    public List<Menu> getAllMenuByType(int Type) throws SQLException, ClassNotFoundException {
        List<Menu> menu = new ArrayList<>();
        String sql = "SELECT * FROM menu WHERE Loai = ?";
        PreparedStatement ps = dp.getConnection().prepareStatement(sql);
        ps.setInt(1, Type);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("MaMon");
                String tenMon = rs.getString("TenMon");
                int giaTien = rs.getInt("GiaTien");
                int tinhTrang = rs.getInt("TinhTrang");
                String status = "";
                if(tinhTrang == 1){
                    status = "Đã hết";
                }
                else if(tinhTrang == 0){
                    status = "Còn món";
                }
                int loai = rs.getInt("Loai");
                Menu qt = new Menu(id, tenMon, giaTien, loai, status);
                menu.add(qt);
            }
        }
        dp.closeDB();
        return menu;
    }
    //Insert Menu Item
    public boolean insertMenuItem(Menu menu) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO menu (TenMon, GiaTien, Loai, TinhTrang) "
                + "VALUES (?,?,?,?)";
        int i;
        int tt = 0;
        try (PreparedStatement ps = dp.getConnection().prepareStatement(sql)) {
            ps.setString(1, menu.getName());
            ps.setInt(2, menu.getGiaTien());
            ps.setInt(3, menu.getLoai());
            if( menu.getTinhTrang() == "Đã hết")
                tt = 1;
            ps.setInt(4, tt);            
            i = 0;
            i = ps.executeUpdate();
        }
        dp.closeDB();
        return i != 0;
    }
    
    //Update item Menu
    public boolean updateTableInfoItem(Menu menu) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE menu SET TenMon = ?, GiaTien = ?, Loai = ?, TinhTrang = ? " 
                + "WHERE MaMon = ?";
        int i;
        int tt = 0;
        try (PreparedStatement ps = dp.getConnection().prepareStatement(sql)) {
            ps.setString(1, menu.getName());
            ps.setInt(2, menu.getGiaTien());
            ps.setInt(3, menu.getLoai());
            if( menu.getTinhTrang() == "Đã hết")
                tt = 1;
            ps.setInt(4, tt);
             ps.setInt(5, menu.getId());
            i = 0;
            i = ps.executeUpdate();
        }
        dp.closeDB();
        return i != 0;
    }
    
    //Make deleted Menu item
    public boolean reverseMenuItem(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM menu WHERE MaMon = ?";
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
