/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.Controller;

import coffeeshopmanager.Model.Menu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void loadTableView() {
        TableColumn tenMon = new TableColumn("Tên Món");
        tenMon.setPrefWidth(300);
        tenMon.setCellValueFactory(new PropertyValueFactory("name"));
        
        TableColumn giaTien = new TableColumn("Giá Tiền");
        giaTien.setCellValueFactory(new PropertyValueFactory("giaTien"));
    }
}
