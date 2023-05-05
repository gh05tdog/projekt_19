package application;

import app.SoftwareApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;


public class AddUserController {
    public void setModelAndView(Model theModel, View view) {
    }

    @FXML
    private Label userNameLabel;

    public void setUserNameLabel(String name) {
        userNameLabel.setText(name);
    }
}

