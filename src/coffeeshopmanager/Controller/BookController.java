/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.Controller;

import coffeeshopmanager.DAO.ChiTietHoaDonDAO;
import coffeeshopmanager.DAO.HoaDonDAO;
import coffeeshopmanager.DAO.MenuDAO;
import coffeeshopmanager.Model.ChiTietHoaDon;
import coffeeshopmanager.Model.HoaDon;
import coffeeshopmanager.Model.Menu;
import coffeeshopmanager.Model.TableInfo;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class BookController implements Initializable {
    @FXML private TableView<ChiTietHoaDon> tbCTHD;
    
    public TableColumn<Menu, String> maCTHoaDon;
    public TableColumn<Menu, String> maHoaDon;
    public TableColumn<Menu, String> maMon;
    public TableColumn<Menu, String> soLuong;
    public ObservableList<ChiTietHoaDon> list = getHoaDon();
    
    @FXML private TableView<Menu> tbMenu;
    public TableColumn<Menu, String> maMon2;
    public TableColumn<Menu, String> tenMon;
    public TableColumn<Menu, String> giaTien;
    public TableColumn<Menu, String> tinhTrang;
    public TableColumn<Menu, String> soLuong2;
    private ObservableList<Menu> list2 = getMenu();
    private ChiTietHoaDonDAO chitietDAO;
    private HoaDonDAO hDon;
    private MenuDAO menu;
    private HoaDon hoadon;
    SceneMovement sm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCTHD();
        loadMenu();
    }    
    @FXML private void callBack(ActionEvent event) throws IOException {
        sm = new SceneMovement();
        sm.callNewScene(event, "HoaDon");
    }
    private ObservableList<ChiTietHoaDon> getHoaDon() {
        try {
            chitietDAO = new ChiTietHoaDonDAO();
            List<ChiTietHoaDon> chitiet;
            chitiet = chitietDAO.getAllChiTietHoaDonByMaHoaDon(HoaDonDAO.getHoaDon().getMaHoaDon());
            list = FXCollections.observableList(chitiet);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        }
        return list;
    } 
    private ObservableList<Menu> getMenu() {
        try {
            menu = new MenuDAO();
            List<Menu> mn;
            mn = menu.getAllMenu();
            list2 = FXCollections.observableList(mn);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        }
        return list2;
    } 
    
     private void loadCTHD() {
        maCTHoaDon.setCellValueFactory(new PropertyValueFactory<>("maChiTietHoaDon"));
        maHoaDon.setCellValueFactory(new PropertyValueFactory<>("maHoaDon"));
        maMon.setCellValueFactory(new PropertyValueFactory<>("maMon"));
        soLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        tbCTHD.setItems(list);
    }
    
    private void loadMenu() {
        maMon2.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenMon.setCellValueFactory(new PropertyValueFactory<>("name"));
        giaTien.setCellValueFactory(new PropertyValueFactory<>("giaTien"));
        tinhTrang.setCellValueFactory(new PropertyValueFactory<>("tinhTra0ng"));
        tbMenu.setItems(list2);
        
    }
    
    @FXML private void addSoLuong(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        menu = new MenuDAO();
        Menu mn = tbMenu.getSelectionModel().getSelectedItem();
//        int idHD = HoaDonDAO.getHoaDon().getMaHoaDon();
        if(mn != null){
            menu.setMenuItem(mn);
            sm = new SceneMovement();
            sm.callNewScene(event, "ThemSoLuong");
        }
        else {
            sm = new SceneMovement();
            sm.callConfirmAlert("Chọn một Hóa đơn để xem chi tiết!");
        }
        
    }
    
     @FXML private void removeItem(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        chitietDAO = new ChiTietHoaDonDAO();
        ChiTietHoaDon chitietHD = tbCTHD.getSelectionModel().getSelectedItem();
       if(chitietHD != null){
        int id = chitietHD.getMaChiTietHoaDon();
        if (chitietDAO.reverseChiTietHoaDonItem(id)) {
            sm = new SceneMovement();
            sm.callConfirmAlert("Xóa thành công");
        } else {
            sm = new SceneMovement();
            sm.callErrorAlert("Xóa thất bại");
        }}
       else{
           sm = new SceneMovement();
            sm.callErrorAlert("Chọn 1 hóa đơn muốn xóa");
       }
          
        list = getHoaDon();
        loadCTHD();
    }
    
}
