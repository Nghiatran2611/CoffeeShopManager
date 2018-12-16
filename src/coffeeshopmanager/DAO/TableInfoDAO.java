/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.DAO;

import coffeeshopmanager.Model.TableInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tinh
 */
public class TableInfoDAO {
    private DataProvider dp;
    private static TableInfo table;
    
    private static List<TableInfo> tableInfo = new ArrayList<>();
    
     //Connect to database
    public TableInfoDAO() throws ClassNotFoundException, SQLException {
        dp = new DataProvider();
    }
    //Get all tableinfo in database
    public List<TableInfo> getAllMenu() throws SQLException, ClassNotFoundException {
        List<TableInfo> tableList = new ArrayList<>();
        String sql = "SELECT * FROM tableinfo";
        try (ResultSet rs = dp.executeReader(sql)) {
            while (rs.next()) {
                int id = rs.getInt("MaBan");
                String sucChua = rs.getString("SucChua");
                String viTri = rs.getString("ViTri");
                int tinhTrang = rs.getInt("TinhTrang");
                String status = "";
                if(tinhTrang == 1){
                    status = "Đã đặt";
                }
                else if(tinhTrang == 0){
                    status = "Còn bàn";
                }
                TableInfo ti = new TableInfo(id, sucChua, viTri, status);
                tableList.add(ti);
            }
        }
        dp.closeDB();
        
        return tableList;
    }
    
     //Insert TableInfo
    public boolean inserttableInfo(TableInfo tableinfo) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tableinfo (SucChua, ViTri, TinhTrang)"
                + "VALUES (?,?,?)";
        int i;
        int tt = 0;
        try (PreparedStatement ps = dp.getConnection().prepareStatement(sql)) {
            ps.setString(1, tableinfo.getSucChua());
            ps.setString(2, tableinfo.getViTri());
            if( tableinfo.getTinhTrang() == "Đã hết")
                tt = 1;
            ps.setInt(3, tt);
            i = 0;
            i = ps.executeUpdate();
        }
        dp.closeDB();
        return i != 0;
    }
    
    //Update item TableIndo
    public boolean updateTableInfoItem(TableInfo tableinfo) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tableinfo SET SucChua = ?, ViTri = ?, TinhTrang = ? " 
                + "WHERE MaBan = ?";
        int i;
        int tt = 0;
        try (PreparedStatement ps = dp.getConnection().prepareStatement(sql)) {
            ps.setString(1, tableinfo.getSucChua());
            ps.setString(2, tableinfo.getViTri());
            if( tableinfo.getTinhTrang() == "Đã hết")
                tt = 1;
            ps.setInt(3, tt);
             ps.setInt(4, tableinfo.getMaBan());
            i = 0;
            i = ps.executeUpdate();
        }
        dp.closeDB();
        return i != 0;
    }
    
    //Make deleted TableInfo item
    public boolean reverseTableInfo(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM tableinfo WHERE MaBan = ?";
        int i;
        try (PreparedStatement ps = dp.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            i = 0;
            i = ps.executeUpdate();
        }
        dp.closeDB();
        return i != 0;
    }
    
    public static List getTableInfoUse() {
        return tableInfo;
    }

    public static void setTableInfoUse(TableInfo tableinfo) {
        TableInfoDAO.tableInfo.add(tableinfo);
    }

    public static void setTableInfoNull() {
        tableInfo = new ArrayList<>();
    }
    
    //Saving a tableinfo to new scene
    public static TableInfo getTableInfo() {
        return table;
    }
    public static void setTableInfo(TableInfo tb) {
        TableInfoDAO.table = tb;
    }
}
