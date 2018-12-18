/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.Controller;

import coffeeshopmanager.DAO.HoaDonDAO;
import coffeeshopmanager.DAO.MenuDAO;
import coffeeshopmanager.DAO.TableInfoDAO;
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
 * @author Tinh
 */
public class HoaDonController implements Initializable {
    
    @FXML private TableView<HoaDon> listHoaDon;
    @FXML private TableView<TableInfo> tbTableInfo;
    public TableColumn<TableInfo, String> maBan2;
    public TableColumn<TableInfo, String> sucChua;
    public TableColumn<TableInfo, String> viTri;
    public TableColumn<TableInfo, String> tinhTrang;
    private ObservableList<TableInfo> list2 = getTableInfo();
    public TableColumn<HoaDon, String> maHoaDon;
    public TableColumn<HoaDon, String> maBan;
    
    private ObservableList<HoaDon> list1 = getHoaDon();
    
    private TableInfoDAO tableInfoDAO;
    private HoaDonDAO hdDAO;
    SceneMovement sm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadHoaDon();
        loadtableInfo();
    }   
    
    private ObservableList<HoaDon> getHoaDon() {
        try {
            hdDAO = new HoaDonDAO();
            List<HoaDon> hd;
            hd = hdDAO.getAllHoaDon();
            list1 = FXCollections.observableList(hd);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        }
        return list1;
    } 
    
    private void loadHoaDon() {
        maHoaDon.setCellValueFactory(new PropertyValueFactory<>("maHoaDon"));
        maBan.setCellValueFactory(new PropertyValueFactory<>("maBan"));
        
        listHoaDon.setItems(list1);
        
    }
    
    private ObservableList<TableInfo> getTableInfo() {
        try {
            tableInfoDAO = new TableInfoDAO();
            List<TableInfo> table;
            table = tableInfoDAO.getAllMenu();
            list2 = FXCollections.observableList(table);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        }
        return list2;
    } 
     private void loadtableInfo() {
        maBan2.setCellValueFactory(new PropertyValueFactory<>("maBan"));
        sucChua.setCellValueFactory(new PropertyValueFactory<>("sucChua"));
        viTri.setCellValueFactory(new PropertyValueFactory<>("viTri"));
        tinhTrang.setCellValueFactory(new PropertyValueFactory<>("tinhTrang"));
        tbTableInfo.setItems(list2);
        
    }
    
    @FXML private void callChiTiet(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        hdDAO = new HoaDonDAO();
        HoaDon hDon = listHoaDon.getSelectionModel().getSelectedItem();
        if(hDon != null){
            hdDAO.setHoaDon(hDon);
        sm = new SceneMovement();
        sm.callNewScene(event, "Book");
        }
        else {
            sm = new SceneMovement();
            sm.callConfirmAlert("Chọn một Hóa đơn để xem chi tiết!");
        }
        
    }
    
    @FXML private void callBack(ActionEvent event) throws IOException {
        sm = new SceneMovement();
        sm.callNewScene(event, "Home");
    }
   
    @FXML
    public void addHoaDon(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        TableInfo tb = tbTableInfo.getSelectionModel().getSelectedItem();
        int id = tb.getMaBan();
        hdDAO = new HoaDonDAO();
        if (hdDAO.insertHoaDon(id)) {
            sm = new SceneMovement();
            sm.callConfirmAlert("Thêm thành công");
        } else {
            sm = new SceneMovement();
            sm.callErrorAlert("Thêm thất bại");
        }
        list1 = getHoaDon();
        loadHoaDon();
    }
    
    @FXML
    public void deleteHoaDonItem(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        hdDAO = new HoaDonDAO();
        HoaDon hDon = listHoaDon.getSelectionModel().getSelectedItem();
       
        int id = hDon.getMaHoaDon();
        if (hdDAO.reverseHoaDonItem(id)) {
            sm = new SceneMovement();
            sm.callConfirmAlert("Xóa thành công");
        } else {
            sm = new SceneMovement();
            sm.callErrorAlert("Xóa thất bại");
        }
          
        list1 = getHoaDon();
        loadHoaDon();
    }
}
