/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author mac
 */
public class SceneMovement {
     //Close current scene and Open new one by ActionEvent 
     public void callNewScene(ActionEvent event, String sceneName) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("View/" + sceneName + ".fxml"));
        Scene scene = new Scene(root);
        stage.setTitle(sceneName);
        stage.setScene(scene);
        stage.show();
    }
     
     //Close current scene and Open new one by MouseEvent
    void callNewScene(MouseEvent event, String sceneName) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("View/" + sceneName));
        Scene scene = new Scene(root);
        stage.setTitle(sceneName);
        stage.setScene(scene);
        stage.show();
    }
}
