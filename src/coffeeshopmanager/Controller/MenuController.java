/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.Controller;

import coffeeshopmanager.DAO.MenuDAO;
import coffeeshopmanager.Model.Menu;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TableView<Menu> tbThucAn;
    @FXML private TableView<Menu> tbDoUong;
    @FXML private TextField txtTenMon;
    @FXML private TextField txtGiaTien;
     @FXML private ToggleGroup loai;
    @FXML private RadioButton rdDoAn;
    @FXML private RadioButton rdDoUong;
    @FXML private ToggleGroup tinhtrang;
    @FXML private RadioButton rdCon;
    @FXML private RadioButton rdHet;
    
    public boolean checkUpdate = false;
    public int id;
    
    public TableColumn<Menu, String> maMon;
    public TableColumn<Menu, String> tenMon;
    public TableColumn<Menu, String> giaTien;
    public TableColumn<Menu, String> tinhTrang;
    public TableColumn<Menu, String> maMon2;
    public TableColumn<Menu, String> tenMon2;
    public TableColumn<Menu, String> giaTien2;
    public TableColumn<Menu, String> tinhTrang2;
    private ObservableList<Menu> list1 = getMenuThuAn();
    private ObservableList<Menu> list2 = getMenuThucUong();
    
    public Menu mn = null;

    private MenuDAO menuDAO;
    
    SceneMovement sm;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadMenuDoAn();
        loadMenuThucUong();
    }    
    
     private ObservableList<Menu> getMenuThuAn() {
        try {
            menuDAO = new MenuDAO();
            List<Menu> menu;
            menu = menuDAO.getAllMenuByType(0);
            list1 = FXCollections.observableList(menu);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        }
        return list1;
    } 
     
     private ObservableList<Menu> getMenuThucUong() {
        try {
            menuDAO = new MenuDAO();
            List<Menu> menu;
            menu = menuDAO.getAllMenuByType(1);
            list2 = FXCollections.observableList(menu);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        }
        return list2;
    } 
    
    private void loadMenuDoAn() {
        maMon.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenMon.setCellValueFactory(new PropertyValueFactory<>("name"));
        giaTien.setCellValueFactory(new PropertyValueFactory<>("giaTien"));
        tinhTrang.setCellValueFactory(new PropertyValueFactory<>("tinhTrang"));
        tbThucAn.setItems(list1);
        
    }
    private void loadMenuThucUong() {
        maMon2.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenMon2.setCellValueFactory(new PropertyValueFactory<>("name"));
        giaTien2.setCellValueFactory(new PropertyValueFactory<>("giaTien"));
        tinhTrang2.setCellValueFactory(new PropertyValueFactory<>("tinhTrang"));
        tbDoUong.setItems(list2);
        
    }
    
    @FXML private void callBack(ActionEvent event) throws IOException {
        sm = new SceneMovement();
        sm.callNewScene(event, "Home");
    }
    @FXML 
    private void handleClickTbDoUong() { 
    tbDoUong.setOnMouseClicked((MouseEvent event) -> { 
      if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1){ 
        mn = tbDoUong.getSelectionModel().getSelectedItem();
      } 
    }); 
    }  
    
    @FXML 
    private void handleClickTbThucAn() { 
    tbThucAn.setOnMouseClicked((MouseEvent event) -> { 
      if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1){ 
        mn = tbThucAn.getSelectionModel().getSelectedItem();
      } 
    }); 
    }  
    

    //Update menu
    @FXML private void updateMenu(ActionEvent event) throws IOException {
        if(mn == null){
            sm = new SceneMovement();
            sm.callConfirmAlert("Click thêm 1 lần vào món muốn chỉnh sửa để chỉnh sửa!");
        }
        else{
        id = mn.getId();
        txtTenMon.setText(mn.getName());
        String giatien = String.valueOf(mn.getGiaTien());
        txtGiaTien.setText(giatien);
        if(mn.getLoai() == 0)
            loai.selectToggle(rdDoAn);
        else if(mn.getLoai() == 1)
           loai.selectToggle(rdDoUong);
        if(mn.getTinhTrang() == "Còn món")
            tinhtrang.selectToggle(rdCon);
        else if(mn.getTinhTrang() == "Đã hết")
           tinhtrang.selectToggle(rdHet);
        checkUpdate = true;
        }
        
    }
    
    //Insert or Update Menu
    @FXML
    public void addMenuItem(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
            if (completeCheck()) {
                menuDAO = new MenuDAO();
                RadioButton selectedLoaiRB = (RadioButton) loai.getSelectedToggle();
                RadioButton selectedTTRB = (RadioButton) tinhtrang.getSelectedToggle();
                int giaTien = Integer.valueOf(txtGiaTien.getText());
                int l = 0;
                if(selectedLoaiRB.getText() == "Nước uống") l = 1;
                Menu menu = new Menu(txtTenMon.getText(), giaTien, l, selectedTTRB.getText());
                Menu menu1 = new Menu(id, txtTenMon.getText(), giaTien, l, selectedTTRB.getText());
                if(checkUpdate == false){
                    if (menuDAO.insertMenuItem(menu)) {
                        sm = new SceneMovement();
                        sm.callConfirmAlert("Thêm thành công");
                    } else {
                        sm = new SceneMovement();
                        sm.callErrorAlert("Thêm thất bại");
                    }
                }
                else {
                    if (menuDAO.updateTableInfoItem(menu1)) {
                        sm = new SceneMovement();
                        sm.callConfirmAlert("Chỉnh sửa thành công");
                    } else {
                        sm = new SceneMovement();
                        sm.callErrorAlert("Chỉnh sửa thất bại");
                    }
                }
                checkUpdate = false; 
                txtTenMon.setText("");
                txtGiaTien.setText("");
                loai.selectToggle(rdDoAn);
                tinhtrang.selectToggle(rdCon);
            }        
    }
    
    //Delete Menu Item
    @FXML
    public void deleteMenuItem(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        menuDAO = new MenuDAO();
        if(mn == null){
            sm = new SceneMovement();
            sm.callConfirmAlert("Click thêm 1 lần vào món muốn xóa để xóa!");
        }
        else{
            int id = mn.getId();
            if (menuDAO.reverseMenuItem(id)) {
            sm = new SceneMovement();
            sm.callConfirmAlert("Xóa thành công");
        } else {
            sm = new SceneMovement();
            sm.callErrorAlert("Xóa thất bại");
        }
        }        
    }
    
    //Check before update or insert
    public boolean completeCheck() {
        sm = new SceneMovement();
        if (sm.dataTextFieldCheck(txtTenMon, "tên món") == false) {
                return false;
            }
        if (sm.dataTextFieldCheck(txtGiaTien, "giá tiền") == false) {
            return false;
        } 
        return true;
    }
}
