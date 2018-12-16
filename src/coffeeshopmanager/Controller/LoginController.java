/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeshopmanager.Controller;

import coffeeshopmanager.DAO.UserDAO;
import coffeeshopmanager.Model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField txtUserName;
    @FXML private PasswordField txtPassword;
    
    private UserDAO uDAO;
    SceneMovement sm;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     //Move to Admin scene or User scene
    @FXML
    private void callMainScene(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        uDAO = new UserDAO();
        String name = txtUserName.getText();
        String password = txtPassword.getText();
        User user = uDAO.getUserByUserName(name);
        if (user != null) {
            if (user.getUsername().equals(name) && user.getPassword().equals(password)) {
                UserDAO.setLoginUser(user);
                sm = new SceneMovement();
                sm.callNewScene(event, "Home");
            } else {
                sm = new SceneMovement();
                sm.callErrorAlert("UserName hoặc Password không đúng!");
            }
        }
        else {
            sm = new SceneMovement();
            sm.callErrorAlert("UserName hoặc Password không đúng!");
        }

    }
}
