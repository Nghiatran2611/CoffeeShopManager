/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.Controller;

import coffeeshopmanager.DAO.TableInfoDAO;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class TableInfoController implements Initializable {
    @FXML private TableView<TableInfo> tbTableInfo;
    @FXML private TextField txtSucChua;
    @FXML private TextField txtViTri;
    @FXML private ToggleGroup tinhtrang;
    @FXML private RadioButton rdCon;
    @FXML private RadioButton rdHet;
    
    public TableColumn<TableInfo, String> maBan;
    public TableColumn<TableInfo, String> sucChua;
    public TableColumn<TableInfo, String> viTri;
    public TableColumn<TableInfo, String> tinhTrang;
    private ObservableList<TableInfo> list1 = getTableInfo();
    public boolean checkUpdate = false;
    public int id;
    
    private TableInfoDAO tableInfoDAO;
    
    SceneMovement sm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadtableInfo();
    }    
    
    private ObservableList<TableInfo> getTableInfo() {
        try {
            tableInfoDAO = new TableInfoDAO();
            List<TableInfo> table;
            table = tableInfoDAO.getAllMenu();
            list1 = FXCollections.observableList(table);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), new ButtonType("OK"));
            alert.showAndWait();
        }
        return list1;
    } 
     private void loadtableInfo() {
        maBan.setCellValueFactory(new PropertyValueFactory<>("maBan"));
        sucChua.setCellValueFactory(new PropertyValueFactory<>("sucChua"));
        viTri.setCellValueFactory(new PropertyValueFactory<>("viTri"));
        tinhTrang.setCellValueFactory(new PropertyValueFactory<>("tinhTrang"));
        tbTableInfo.setItems(list1);
        
    }
    
    @FXML private void callBack(ActionEvent event) throws IOException {
        sm = new SceneMovement();
        sm.callNewScene(event, "Home");
    }
    
    //Check before update or insert
    public boolean completeCheck() {
        sm = new SceneMovement();
        if (sm.dataTextFieldCheck(txtSucChua, "sức chứa") == false) {
                return false;
            }
        if (sm.dataTextFieldCheck(txtViTri, "vị trí") == false) {
            return false;
        } 
        return true;
    }
    
     //Update TableInfo
    @FXML
    public void updateTableInfo(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        TableInfo tb = tbTableInfo.getSelectionModel().getSelectedItem();
        id = tb.getMaBan();
        txtSucChua.setText(tb.getSucChua());
        txtViTri.setText(tb.getViTri());
        if(tb.getTinhTrang() == "Còn bàn")
            tinhtrang.selectToggle(rdCon);
        else if(tb.getTinhTrang() == "Đã đặt")
           tinhtrang.selectToggle(rdHet);
        checkUpdate = true;
    }
    
    //Insert or Update TableInfo
    @FXML
    public void addTableInfo(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
            if (completeCheck()) {
                tableInfoDAO = new TableInfoDAO();
                RadioButton selectedRB = (RadioButton) tinhtrang.getSelectedToggle();
                String selectedValue = selectedRB.getText();
                if(checkUpdate == false){
                    TableInfo table = new TableInfo(txtSucChua.getText(), txtViTri.getText(), selectedValue);
                    if (tableInfoDAO.inserttableInfo(table)) {
                        sm = new SceneMovement();
                        sm.callConfirmAlert("Thêm thành công");
                    } else {
                        sm = new SceneMovement();
                        sm.callErrorAlert("Thêm thất bại");
                    }
                }
                else {
                    TableInfo table1 = new TableInfo(id, txtSucChua.getText(), txtViTri.getText(), selectedRB.getText());
                    if (tableInfoDAO.updateTableInfoItem(table1)) {
                        sm = new SceneMovement();
                        sm.callConfirmAlert("Chỉnh sửa thành công");
                    } else {
                        sm = new SceneMovement();
                        sm.callErrorAlert("Chỉnh sửa thất bại");
                    }
                }
                checkUpdate = false; 
                txtSucChua.setText("");
                txtViTri.setText("");
                tinhtrang.selectToggle(rdCon);
            }   
            list1 = getTableInfo();
            loadtableInfo();
    }
    
    //Delete TableInfo Item
    @FXML
    public void deleteTableInfo(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        tableInfoDAO = new TableInfoDAO();
        TableInfo tb = tbTableInfo.getSelectionModel().getSelectedItem();
        int id = tb.getMaBan();
        if (tableInfoDAO.reverseTableInfo(id)) {
            sm = new SceneMovement();
            sm.callConfirmAlert("Xóa thành công");
        } else {
            sm = new SceneMovement();
            sm.callErrorAlert("Xóa thất bại");
        }
        list1 = getTableInfo();
        loadtableInfo();
    }
}
