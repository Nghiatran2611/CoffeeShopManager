/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.DAO;

import coffeeshopmanager.Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tinh
 */
public class UserDAO {
    private DataProvider dp;
    private static User user ;
    
    public UserDAO() throws ClassNotFoundException,SQLException{
        dp = new DataProvider();
    }
    
    //Get all user in database
    public List<User> getAllUser() throws ClassNotFoundException,SQLException{
        List<User> userlist= new ArrayList<>();
        String sql = "SELECT * from user";
        try (ResultSet rs = dp.executeReader(sql)) {
            while(rs.next()){
                int id = rs.getInt("idUser");
                String username = rs.getString("UserName");
                String password = rs.getString("Password");
                String fullname = rs.getString("FullName");
                User u = new User(id,username,password,fullname);
                userlist.add(u);
            }
        }
        dp.closeDB();
       return userlist ; 
    }
    
    //Get User by username
    public User getUserByUserName(String uName) throws ClassNotFoundException,SQLException{
        user = null;
        String sql ="SELECT * FROM user WHERE UserName = ?";
        PreparedStatement ps = dp.getConnection().prepareStatement(sql);
        ps.setString(1, uName);
        try (ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                int id = rs.getInt("idUser");
                String username = rs.getString("UserName");
                String password = rs.getString("Password");
                String fullname = rs.getString("FullName");
                user = new User(id,username,password, fullname);
            }
        }
        dp.closeDB();
        return user;
    }
    
     //Save User
    public static User getLoginUser(){
        return user;
    }
     public static void setLoginUser(User user) {
        UserDAO.user = user;
    }
}
