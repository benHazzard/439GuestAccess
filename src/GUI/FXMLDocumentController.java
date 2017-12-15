/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;
import org.apache.commons.mail.EmailException;
//Import for Private_Project_Guest
import private_project_guest.getter;
//Import for demo
import private_project_guest.Demo;
/**
 *
 * @author Ben
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private JFXTextField username;
 
    @FXML
    private JFXPasswordField password;
    
    @FXML
    private JFXTextField phoneNumber;
    
    @FXML
    private ChoiceBox serviceProvider;
    
    @FXML
    private ImageView image;
    
    public void choiceButtonPushed(){
        serviceProvider.getValue();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serviceProvider.getItems().add("T Mobile");
        serviceProvider.getItems().add("Virgin Mobile");
        serviceProvider.getItems().add("Vingularme");
        serviceProvider.getItems().add("Sprint");
        serviceProvider.getItems().add("Verizon");
        serviceProvider.getItems().add("Nextel");
    }  
    @FXML
    private void button(ActionEvent event) throws EmailException{
        getter g = new getter(username.getText(), password.getText(), phoneNumber.getText(), serviceProvider.getValue().toString());

    } 
    @FXML
    private void demoButtonAction(ActionEvent event) throws EmailException, InterruptedException{
        Demo d = new Demo();
        d.demoRun();
    }

    
  
    
}
