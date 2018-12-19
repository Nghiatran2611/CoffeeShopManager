/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.Controller;

import coffeeshopmanager.DAO.ChiTietHoaDonDAO;
import coffeeshopmanager.DAO.HoaDonDAO;
import coffeeshopmanager.DAO.MenuDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class ThemSoLuongController implements Initializable {
    
    @FXML private TextField txtSoLuong;
    private ChiTietHoaDonDAO chitietDAO;
    private HoaDonDAO hDon;
    private MenuDAO menu;
    SceneMovement sm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML private void addSoLuong(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        chitietDAO = new ChiTietHoaDonDAO();
        int soluong = Integer.valueOf(txtSoLuong.getText());
        if(soluong > 0){
        if(chitietDAO.insertCTHD(HoaDonDAO.getHoaDon().getMaHoaDon(), MenuDAO.getMenuItem().getId(), soluong)){
            sm = new SceneMovement();
            sm.callConfirmAlert("Thêm thành công");
            sm.callNewScene(event, "Book");
        } else {
            sm = new SceneMovement();
            sm.callErrorAlert("Thêm thất bại");
        }}
        else {
            sm = new SceneMovement();
            sm.callErrorAlert("Vui lòng nhập số lượng món");
        }
        
        
    }

    
}
