/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Tinh
 */
public class DataProvider {
    private Connection cn = null;
    private Statement stm = null;
    
    public Connection getConnection() {
        return cn;
    }

    public DataProvider() throws ClassNotFoundException,SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlyquancafe", "root", "123456");
    }
     
     public ResultSet executeReader(String sql) throws SQLException, ClassNotFoundException {
        this.stm = cn.createStatement();
        return stm.executeQuery(sql);
    }
     
     public void closeDB() throws SQLException {
        if(stm != null){
            try {
                stm.close();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage(),new ButtonType("OK"));
                alert.showAndWait();
            }
        }
        if(cn != null){
            try {
                cn.close();
            }catch (SQLException e){
                Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage(),new ButtonType("OK"));
                alert.showAndWait();
            }
        }
    }
}
