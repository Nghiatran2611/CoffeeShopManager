/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author mac
 */
public class HomeController implements Initializable {
    SceneMovement sm;
    @FXML
    private void callMenuScene(ActionEvent event) throws IOException {
        sm = new SceneMovement();
        sm.callNewScene(event, "Menu");
    }
    
    @FXML
    private void callTableInfoScene(ActionEvent event) throws IOException {
        sm = new SceneMovement();
        sm.callNewScene(event, "TableInfo");
    }
    
    @FXML
    private void callBookScene(ActionEvent event) throws IOException {
        sm = new SceneMovement();
        sm.callNewScene(event, "Book");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}